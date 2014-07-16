package football;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class AuctionEnvironment {

	List<Team> allTeams;
	Team whoseTurn = null;
	int currentBid = 0;
	int currentIndex = 0;
	int startingIndex = 0;
	Team startingTurn = null;
	Set<Team> teamsOutOfBidding = new HashSet<Team>();
	Player playerUp = null;
	Team winningBidTeam = null;
	int playerIndex = 0;
	Set<Team> teamsOutForGood = new HashSet<Team>();
	Set<Team> teamsOutForQB = new HashSet<Team>();
	Set<Team> teamsOutForRB = new HashSet<Team>();
	Set<Team> teamsOutForWR = new HashSet<Team>();
	Set<Team> teamsOutForTE = new HashSet<Team>();
	List<Player> allPlayers = new ArrayList<Player>();

	public AuctionEnvironment() {
		allTeams = new ArrayList<Team>();
	}

	public void addTeam(Team... t) {
		allTeams.addAll(Arrays.asList(t));
	}

	public void clearRosters() {
		for (Team t : allTeams)
			t.reset();
	}

	public void start() {
		teamsOutForGood = new HashSet<Team>();
		teamsOutForQB = new HashSet<Team>();
		teamsOutForRB = new HashSet<Team>();
		teamsOutForWR = new HashSet<Team>();
		teamsOutForTE = new HashSet<Team>();
		allPlayers = new ArrayList<Player>();
		populateAllPlayers();
		clearRosters();
		teamsOutOfBidding = new HashSet<Team>();
		winningBidTeam = null;
		Random rand = new Random();
		int r = rand.nextInt(10);
		currentIndex = r;
		startingIndex = r;
		whoseTurn = allTeams.get(currentIndex);
		startingTurn = allTeams.get(startingIndex);

		playerIndex = 0;
		playerUp = allPlayers.get(playerIndex);
		currentBid = 0;
		promptBid();
	}

	public void checkAllTeamsStatus() {
		for (Team t : allTeams) {
			if (t.moneyLeft == 0
					|| t.getNumPlayers() == Roster.ROSTER_LIMIT_DEFAULT)
				teamsOutForGood.add(t);
			if (t.countQBs() == Roster.NUM_QBS_LIMIT)
				teamsOutForQB.add(t);
			if (t.countRBs() == Roster.NUM_RBS_LIMIT)
				teamsOutForRB.add(t);
			if (t.countWRs() == Roster.NUM_WRS_LIMIT)
				teamsOutForWR.add(t);
			if (t.countTEs() == Roster.NUM_TES_LIMIT)
				teamsOutForTE.add(t);
		}
	}

	public Set<Team> teamsOutForPlayer(Player p) {
		checkAllTeamsStatus();
		if (p instanceof QBPlayer)
			return teamsOutForQB;
		if (p instanceof RBPlayer)
			return teamsOutForRB;
		if (p instanceof WRPlayer)
			return teamsOutForWR;
		else
			return teamsOutForTE;
	}

	public void promptBid() {
		checkAllTeamsStatus();
		teamsOutOfBidding.addAll(teamsOutForGood);
		teamsOutOfBidding.addAll(teamsOutForPlayer(playerUp));
		if (teamsOutOfBidding.size() == allTeams.size() - 1) {
			endTurn();
			return;
		}
		if (whoseTurn.equals(winningBidTeam)
				|| teamsOutOfBidding.contains(whoseTurn)) {
			updateCurrentIndex();
			return;
		} else {
			int onTurnMaxBid = whoseTurn.getMaxBidForPlayer(playerUp);
			if (onTurnMaxBid > currentBid) {
				currentBid++;
				winningBidTeam = whoseTurn;
			} else {
				teamsOutOfBidding.add(whoseTurn);
			}
			if (teamsOutOfBidding.size() == allTeams.size() - 1) {
				endTurn();
				return;
			}
			updateCurrentIndex();
		}
	}

	public void updateCurrentIndex() {
		if (currentIndex != allTeams.size() - 1)
			currentIndex++;
		else
			currentIndex = 0;
		whoseTurn = allTeams.get(currentIndex);
		promptBid();
	}

	public void endTurn() {
		if (winningBidTeam != null)
			winningBidTeam.addPlayer(playerUp, currentBid);
		checkAllTeamsStatus();
		playerIndex++;
		if (checkIfDone() || teamsOutForGood.size() == allTeams.size()) {
			return;
		}
		incrementStartingIndex();
		currentBid = 0;
		teamsOutOfBidding.clear();
		playerUp = allPlayers.get(playerIndex);
		whoseTurn = allTeams.get(startingIndex);
		startingTurn = allTeams.get(startingIndex);
		winningBidTeam = null;
		int count = 0;
		while (teamsOutForGood.contains(whoseTurn)
				|| teamsOutForPlayer(playerUp).contains(whoseTurn)) {
			incrementStartingIndex();
			whoseTurn = allTeams.get(startingIndex);
			startingTurn = allTeams.get(startingIndex);
			count++;
			if (count == allTeams.size()) {
				Set<Team> ugh = new HashSet<Team>();
				ugh.addAll(teamsOutForGood);
				ugh.addAll(teamsOutForPlayer(playerUp));
				System.out.println(playerUp);
				System.out.println(allTeams);

				return;
			}
		}

		winningBidTeam = whoseTurn;
		currentBid = 1;
		promptBid();
	}

	public void incrementStartingIndex() {
		if (startingIndex != allTeams.size() - 1)
			startingIndex++;
		else
			startingIndex = 0;
	}

	public boolean checkIfDone() {
		if (playerIndex > allPlayers.size() - 1) {
			return true;
		}
		if (getTotalBudget() == 0) {
			return true;
		}
		return false;
	}

	public int getTotalBudget() {
		int total = 0;
		for (Team t : allTeams) {
			total += t.moneyLeft;
		}
		return total;
	}

	public void populateAllPlayers() {
		List<Player> allQBs = CalculateStats.parseQBProperties();
		List<Player> allRBs = CalculateStats.parseRBProperties();
		List<Player> allWRs = CalculateStats.parseWRProperties();
		List<Player> allTEs = CalculateStats.parseTEProperties();
		allPlayers.addAll(allQBs);
		allPlayers.addAll(allWRs);
		allPlayers.addAll(allRBs);
		allPlayers.addAll(allTEs);
		Collections.sort(allPlayers);
	}

	public static void main(String[] args) {

		Team t = new VBDRATeam(198);
		Team tt = new VBDRNTeam(198);
		Team ttt = new DefaultTeam(198);
		AuctionEnvironment e = new AuctionEnvironment();
		e.populateAllPlayers();
		double totalValue = 0;
		Map<Integer, Set<Player>> playerToDifferenceMap = new TreeMap<Integer, Set<Player>>();
		for (Player p : e.allPlayers) {
			totalValue += t.getMaxBidForPlayer(p);
			/*
			 * System.out.println("VBDRA " + p + "  " + t.getMaxBidForPlayer(p)
			 * + "  " + p.getPPGRelativeToBaseline());
			 * System.out.println("VBDRN " + p + "  " +
			 * tt.getMaxBidForPlayer(p)); System.out.println("DEFAULT " + p +
			 * "  " + ttt.getMaxBidForPlayer(p));
			 * System.out.println("DIFFERENCE:  " + (t.getMaxBidForPlayer(p) -
			 * ttt.getMaxBidForPlayer(p)));
			 */
			int difference = (t.getMaxBidForPlayer(p) - ttt
					.getMaxBidForPlayer(p));
			if (!playerToDifferenceMap.containsKey(difference))
				playerToDifferenceMap.put(difference, new HashSet<Player>());
			playerToDifferenceMap.get(difference).add(p);
		}

		System.out.println(playerToDifferenceMap);

		Team team1 = new VBDRNTeam(198);
		Team team2 = new VBDRATeam(198);
		Team team3 = new DefaultTeam(198);
		Team team4 = new DefaultTeam(198);
		Team team5 = new DefaultTeam(198);
		Team team6 = new DefaultTeam(198);
		Team team7 = new DefaultTeam(198);
		Team team8 = new DefaultTeam(198);
		Team team9 = new DefaultTeam(198);
		Team team10 = new DefaultTeam(198);
		e.addTeam(team1, team2, team3, team4, team5, team6, team7, team8,
				team9, team10);
		double total1 = 0;
		double starterBenchRatio1 = 0;
		double moneySpent1 = 0;
		double total2 = 0;
		double moneySpent2 = 0;
		double starterBenchRatio2 = 0;
		double total3 = 0;
		double moneySpent3 = 0;
		double starterBenchRatio3 = 0;
		double total4 = 0;
		int count = 0;
		for (int i = 0; i < 1000; i++) {
			e.start();
			starterBenchRatio1 += team1.fractionSpentOnStarters();
			starterBenchRatio2 += team2.fractionSpentOnStarters();
			starterBenchRatio3 += team3.fractionSpentOnStarters();
			moneySpent1 += team1.moneyUsed;
			moneySpent2 += team2.moneyUsed;
			moneySpent3 += team3.moneyUsed;
			total1 += team1.myRoster.getTeamValue();
			total2 += team2.myRoster.getTeamValue();
			total3 += team3.myRoster.getTeamValue();
			total4 += team4.myRoster.getTeamValue();
		}
		System.out
				.println("TEAM 1 STARTER:BENCH  " + starterBenchRatio1 / 1000);
		System.out.println("TEAM 1 MONEY SPENT:  " + moneySpent1 / 1000);
		System.out.println("TEAM 1 AVG:  " + (total1 / 1000));
		System.out
				.println("TEAM 2 STARTER:BENCH  " + starterBenchRatio2 / 1000);
		System.out.println("TEAM 2 MONEY SPENT:  " + moneySpent2 / 1000);
		System.out.println("TEAM 2 AVG:  " + (total2 / 1000));
		System.out
				.println("TEAM 3 STARTER:BENCH  " + starterBenchRatio3 / 1000);
		System.out.println("TEAM 3 MONEY SPENT:  " + moneySpent3 / 1000);
		System.out.println("TEAM 3 AVG:  " + (total3 / 1000));
		System.out.println("TEAM 4 AVG:  " + (total4 / 1000));

		/*
		 * System.out.println(team1.toString() + "  " +
		 * team1.myRoster.getTeamValue()); System.out.println();
		 * System.out.println(team2.toString() + "  " +
		 * team2.myRoster.getTeamValue()); System.out.println();
		 * System.out.println(team3.toString() + "  " +
		 * team3.myRoster.getTeamValue());
		 */
		/*
		 * List<Player> allQBs = CalculateStats.parseQBProperties();
		 * List<Player> allRBs = CalculateStats.parseRBProperties();
		 * List<Player> allWRs = CalculateStats.parseWRProperties();
		 * List<Player> allTEs = CalculateStats.parseTEProperties();
		 * Collections.sort(allTEs); Collections.sort(allRBs);
		 * Collections.sort(allWRs); Collections.sort(allQBs); int number = 0;
		 * 
		 * VBDRNTeam t = new VBDRNTeam(200);
		 * 
		 * for (Player p : allRBs) { number++; System.out.println(number + "  "
		 * + p.toString() + " " + "Max Bid Risk Neutral:  " +
		 * t.bidRiskNeutral(p) + "  " + "Max Bid Risk Averse:  " +
		 * t.bidRiskAverse(p)); }
		 */
	}

}
