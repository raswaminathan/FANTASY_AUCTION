package football;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Roster {

	List<Player> allPlayers = new ArrayList<Player>();
	List<Player> qbPlayers = new ArrayList<Player>();
	List<Player> rbPlayers = new ArrayList<Player>();
	List<Player> wrPlayers = new ArrayList<Player>();
	List<Player> tePlayers = new ArrayList<Player>();
	List<Player> starters = new ArrayList<Player>();
	List<Player> qbStarters = new ArrayList<Player>();
	List<Player> rbStarters = new ArrayList<Player>();
	List<Player> wrStarters = new ArrayList<Player>();
	List<Player> teStarters = new ArrayList<Player>();
	List<Player> qbBench = new ArrayList<Player>();
	List<Player> rbBench = new ArrayList<Player>();
	List<Player> wrBench = new ArrayList<Player>();
	List<Player> teBench = new ArrayList<Player>();

	List<Player> bench;
	public static final int ROSTER_LIMIT_DEFAULT = 14;
	public static final int NUM_QBS_LIMIT = 3;
	public static final int NUM_RBS_LIMIT = 6;
	public static final int NUM_WRS_LIMIT = 6;
	public static final int NUM_TES_LIMIT = 3;
	public static final double PERCENTAGE_OF_GAME_MISSED_QB = .125;
	public static final double PERCENTAGE_OF_GAME_MISSED_RB = .125;
	public static final double PERCENTAGE_OF_GAME_MISSED_WR = .175;
	public static final double PERCENTAGE_OF_GAME_MISSED_TE = .175;

	public Roster() {
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Player p : allPlayers) {
			builder.append(p.toString());
			builder.append("  ");
		}
		return builder.toString();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Roster) {
			Roster other = (Roster) obj;
			if (other.toString().equals(this.toString()))
				return true;
			return false;
		}
		return false;
	}

	public void addPlayers(List<Player> players) {
		for (Player p : players) {
			addPlayer(p);
		}
	}

	public void addPlayer(Player p) {
		if (allPlayers.size() < ROSTER_LIMIT_DEFAULT) {
			if (p instanceof QBPlayer && qbPlayers.size() < NUM_QBS_LIMIT)
				qbPlayers.add(p);
			else if (p instanceof RBPlayer && rbPlayers.size() < NUM_RBS_LIMIT)
				rbPlayers.add(p);
			else if (p instanceof WRPlayer && wrPlayers.size() < NUM_WRS_LIMIT)
				wrPlayers.add(p);
			else if (p instanceof TEPlayer && tePlayers.size() < NUM_TES_LIMIT)
				tePlayers.add(p);
			else {
				System.err.println("CANT ADD PLAYER..PLAYER NOT ADDED");
				return;
			}
			allPlayers.add(p);
		} else
			System.err.println("ROSTER LIMIT EXCEEDED..PLAYER NOT ADDED");

	}

	public void setPlayers(List<Player> a) {
		allPlayers = a;
	}

	public void removePlayer(Player p) {
		allPlayers.remove(p);
	}

	public double getTeamValue() {
		findStartersAndBench();
		Random r = new Random();
		double seasonTotal = 0;
		System.out.println(qbStarters.size());
		System.out.println(rbStarters.size());
		System.out.println(wrStarters.size());
		System.out.println(teStarters.size());
		for (int i = 0; i < 16; i++) {
			double weekTotal = 0;
			Set<Player> playersPlayed = new HashSet<Player>();
			qbloop: for (Player p : qbStarters) {
				int rand = r.nextInt(100);
				if (PERCENTAGE_OF_GAME_MISSED_QB * 100 < rand) {
					weekTotal += p.getPointsPerGame();
					continue;
				} else {
					for (Player bp : qbBench) {
						rand = r.nextInt(100);
						if (PERCENTAGE_OF_GAME_MISSED_QB * 100 < rand) {
							weekTotal += bp.getPointsPerGame();
							continue qbloop;
						}
						playersPlayed.add(bp);
					}
					weekTotal += QBPlayer.BASELINE_QB_SCORE / 16;
				}
			}
			rbloop: for (Player p : rbStarters) {
				int rand = r.nextInt(100);
				if (PERCENTAGE_OF_GAME_MISSED_RB * 100 < rand) {
					weekTotal += p.getPointsPerGame();
					continue;
				} else {
					for (Player bp : rbBench) {
						rand = r.nextInt(100);
						if (PERCENTAGE_OF_GAME_MISSED_RB * 100 < rand
								&& !playersPlayed.contains(bp)) {
							weekTotal += bp.getPointsPerGame();
							playersPlayed.add(bp);
							continue rbloop;
						}
						playersPlayed.add(bp);
					}
					weekTotal += RBPlayer.BASELINE_RB_SCORE / 16;
				}
			}
			wrloop: for (Player p : wrStarters) {
				int rand = r.nextInt(100);
				if (PERCENTAGE_OF_GAME_MISSED_WR * 100 < rand) {
					weekTotal += p.getPointsPerGame();
					continue;
				} else {
					for (Player bp : wrBench) {
						rand = r.nextInt(100);
						if (PERCENTAGE_OF_GAME_MISSED_WR * 100 < rand
								&& !playersPlayed.contains(bp)) {
							weekTotal += bp.getPointsPerGame();
							playersPlayed.add(bp);
							continue wrloop;
						}
						playersPlayed.add(bp);
					}
					weekTotal += WRPlayer.BASELINE_WR_SCORE / 16;
				}
			}
			teloop: for (Player p : teStarters) {
				int rand = r.nextInt(100);
				if (PERCENTAGE_OF_GAME_MISSED_TE * 100 < rand) {
					weekTotal += p.getPointsPerGame();
					continue;
				} else {
					for (Player bp : teBench) {
						rand = r.nextInt(100);
						if (PERCENTAGE_OF_GAME_MISSED_TE * 100 < rand) {
							weekTotal += bp.getPointsPerGame();
							continue teloop;
						}
						playersPlayed.add(bp);
					}
					weekTotal += TEPlayer.BASELINE_TE_SCORE / 16;
				}
			}
			System.out.println(weekTotal);
			seasonTotal += weekTotal;
		}
		return seasonTotal / 16;
	}

	public int getStartersPoints() {
		return getStartingQBPoints() + getStartingRBPoints()
				+ getStartingWRPoints() + getStartingTEPoints();
	}

	public int getStartingQBPoints() {
		findStartersAndBench();
		int total = 0;
		for (Player p : starters) {
			if (p instanceof QBPlayer)
				total += p.getTotalPoints();
		}
		return total;
	}

	public int getStartingRBPoints() {
		findStartersAndBench();
		int total = 0;
		for (Player p : starters) {
			if (p instanceof RBPlayer)
				total += p.getTotalPoints();
		}
		return total;
	}

	public int getStartingWRPoints() {
		findStartersAndBench();
		int total = 0;
		for (Player p : starters) {
			if (p instanceof WRPlayer)
				total += p.getTotalPoints();
		}
		return total;
	}

	public int getStartingTEPoints() {
		findStartersAndBench();
		int total = 0;
		for (Player p : starters) {
			if (p instanceof TEPlayer)
				total += p.getTotalPoints();
		}
		return total;
	}

	public List<Player> findNBestPlayers(int n, List<Player> players) {
		Collections.sort(players);
		List<Player> toReturn = new ArrayList<Player>();
		for (int i = 0; i < n; i++) {
			toReturn.add(players.get(i));
		}
		return toReturn;
	}

	public void findStartersAndBench() {
		starters = new ArrayList<Player>();
		bench = new ArrayList<Player>();

		starters.addAll(findNBestPlayers(1, qbPlayers));
		qbStarters.addAll(findNBestPlayers(1, qbPlayers));
		starters.addAll(findNBestPlayers(2, rbPlayers));
		rbStarters.addAll(findNBestPlayers(2, rbPlayers));
		starters.addAll(findNBestPlayers(2, wrPlayers));
		wrStarters.addAll(findNBestPlayers(2, wrPlayers));
		starters.addAll(findNBestPlayers(1, tePlayers));
		teStarters.addAll(findNBestPlayers(1, tePlayers));

		for (Player p : allPlayers) {
			if (!starters.contains(p))
				bench.add(p);
		}

		for (Player p : bench) {
			if (p instanceof QBPlayer)
				qbBench.add(p);
			if (p instanceof RBPlayer)
				rbBench.add(p);
			if (p instanceof WRPlayer)
				wrBench.add(p);
			if (p instanceof TEPlayer)
				teBench.add(p);
		}

		Collections.sort(bench);
		int i = 0;
		while (true) {
			if (bench.get(i) instanceof RBPlayer
					|| bench.get(i) instanceof WRPlayer) {
				starters.add(bench.get(i));
				bench.remove(i);
				break;
			}
			i++;
		}
		Collections.sort(starters);
	}
}