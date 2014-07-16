package football;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Team {

	protected Roster myRoster;
	protected int moneyLeft;
	protected int moneyUsed;
	protected int startingBudget;
	protected Map<Player, Integer> playerToCostMap = new HashMap<Player, Integer>();

	public Team(int budget) {
		myRoster = new Roster();
		startingBudget = budget;
		moneyLeft = budget;
		moneyUsed = 0;
	}
	
	public double fractionSpentOnStarters() {
		myRoster.findStartersAndBench();
		List<Player> starters = myRoster.starters;
		List<Player> bench = myRoster.bench;
		
		int starterTotal = 0;
		int benchTotal = 0;
		
		for (Player st : starters) {
			starterTotal += playerToCostMap.get(st);
		}
		
		for (Player bn : bench) {
			benchTotal += playerToCostMap.get(bn);
		}
				
		return starterTotal /  benchTotal;
	}

	public void reset() {
		myRoster = new Roster();
		moneyLeft = startingBudget;
		moneyUsed = 0;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		int total = 0;
		for (Player p : myRoster.allPlayers) {
			builder.append(p.toString());
			builder.append("  " + playerToCostMap.get(p));
			builder.append("\n");
			total += playerToCostMap.get(p);
		}
		return builder.toString() + total;
	}

	public void addPlayer(Player p, int cost) {
		myRoster.addPlayer(p);
		playerToCostMap.put(p, cost);
		moneyLeft -= cost;
		moneyUsed += cost;
	}

	public int countQBs() {
		return myRoster.qbPlayers.size();
	}

	public int countRBs() {
		return myRoster.rbPlayers.size();
	}

	public int countWRs() {
		return myRoster.wrPlayers.size();
	}

	public int countTEs() {
		return myRoster.tePlayers.size();
	}

	public int getNumPlayers() {
		return myRoster.allPlayers.size();
	}

	public int getMaxBid() {
		int slotsNeeded = 14 - myRoster.size();
		return moneyLeft - slotsNeeded - 1;
	}

	public int getNumPlayersAtSamePosition(Player p) {
		if (p instanceof QBPlayer)
			return countQBs();
		if (p instanceof RBPlayer)
			return countRBs();
		if (p instanceof WRPlayer)
			return countWRs();
		else
			return countTEs();
	}

	public int getNumStartersAtSamePosition(Player p) {
		if (p instanceof QBPlayer)
			return 1;
		if (p instanceof RBPlayer)
			return 2;
		if (p instanceof WRPlayer)
			return 2;
		else
			return 1;
	}

	public int getMaxPlayersAtSamePosition(Player p) {
		if (p instanceof QBPlayer)
			return Roster.NUM_QBS_LIMIT;
		if (p instanceof RBPlayer)
			return Roster.NUM_RBS_LIMIT;
		if (p instanceof WRPlayer)
			return Roster.NUM_WRS_LIMIT;
		else
			return Roster.NUM_TES_LIMIT;
	}

	public abstract int getMaxBidForPlayer(Player p);

}
