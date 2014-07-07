package football;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class CalculateStats {

	private ResourceBundle myResources;
	private static final String PLAYER_RESOURCES_PACKAGE = "playerresources.";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		CalculateStats c = new CalculateStats();
		/*
		 * List<QBPlayer> qbs = c.parseQBProperties(); double maxQB = 0; String
		 * maxQBName = ""; for (QBPlayer qb : qbs) { if
		 * (qb.getPointsPerGamePerDollarRelativeToBaseline() > maxQB) { maxQB =
		 * qb.getPointsPerGamePerDollarRelativeToBaseline(); maxQBName =
		 * qb.toString(); } System.out.println(qb.toString() + "  " +
		 * qb.getPointsPerGamePerDollarRelativeToBaseline() + "  " +
		 * qb.getPPGRelativeToBaseline()); } System.out.println();
		 * System.out.println(maxQBName + " " + maxQB); System.out.println();
		 * 
		 * List<RBPlayer> rbs = c.parseRBProperties();
		 * 
		 * double maxRB = 0; String maxRBName = ""; for (RBPlayer rb : rbs) { if
		 * (rb.getPointsPerGamePerDollarRelativeToBaseline() > maxRB) { maxRB =
		 * rb.getPointsPerGamePerDollarRelativeToBaseline(); maxRBName =
		 * rb.toString(); } System.out.println(rb.toString() + "  " +
		 * rb.getPointsPerGamePerDollarRelativeToBaseline() + "  " +
		 * rb.getPPGRelativeToBaseline()); } System.out.println();
		 * System.out.println(maxRBName + " " + maxRB); System.out.println();
		 * 
		 * List<WRPlayer> wrs = c.parseWRProperties();
		 * 
		 * double maxWR = 0; String maxWRName = ""; for (WRPlayer wr : wrs) { if
		 * (wr.getPointsPerGamePerDollarRelativeToBaseline() > maxWR) { maxWR =
		 * wr.getPointsPerGamePerDollarRelativeToBaseline(); maxWRName =
		 * wr.toString(); } System.out.println(wr.toString() + "  " +
		 * wr.getPointsPerGamePerDollarRelativeToBaseline() + "  " +
		 * wr.getPPGRelativeToBaseline()); } System.out.println();
		 * System.out.println(maxWRName + " " + maxWR); System.out.println();
		 * 
		 * List<TEPlayer> tes = c.parseTEProperties();
		 * 
		 * double maxTE = 0; String maxTEName = ""; for (TEPlayer te : tes) { if
		 * (te.getPointsPerGamePerDollarRelativeToBaseline() > maxTE) { maxTE =
		 * te.getPointsPerGamePerDollarRelativeToBaseline(); maxTEName =
		 * te.toString(); } System.out.println(te.toString() + "  " +
		 * te.getPointsPerGamePerDollarRelativeToBaseline() + "  " +
		 * te.getPPGRelativeToBaseline()); } System.out.println();
		 * System.out.println(maxTEName + " " + maxTE); System.out.println();
		 */
		List<Player> allPlayers = new ArrayList<Player>();
		Set<Roster> allRosters = new HashSet<Roster>();
		List<Player> allQBs = new ArrayList<Player>();
		List<Player> allRBs = new ArrayList<Player>();
		List<Player> allWRs = new ArrayList<Player>();
		List<Player> allTEs = new ArrayList<Player>();
		allTEs.addAll(c.parseTEProperties());
		allQBs.addAll(c.parseQBProperties());
		allRBs.addAll(c.parseRBProperties());
		allWRs.addAll(c.parseWRProperties());

		int qbTotalValue = 0;
		for (Player q : allQBs) {
			qbTotalValue += q.dollarValue;
		}

		int rbTotalValue = 0;
		for (Player r : allRBs) {
			rbTotalValue += r.dollarValue;
		}

		int wrTotalValue = 0;
		for (Player w : allWRs) {
			wrTotalValue += w.dollarValue;
		}

		int teTotalValue = 0;
		for (Player t : allTEs) {
			teTotalValue += t.dollarValue;
		}

		System.out.println();
		System.out.println("QB TOTAL VALUE:  " + qbTotalValue);
		System.out.println("RB TOTAL VALUE:  " + rbTotalValue);
		System.out.println("WR TOTAL VALUE:  " + wrTotalValue);
		System.out.println("TE TOTAL VALUE:  " + teTotalValue);
		System.out.println("OVERALL TOTAL VALUE:  "
				+ (qbTotalValue + rbTotalValue + wrTotalValue + teTotalValue)
				+ "\n");
		Collections.sort(allTEs);
		Collections.sort(allRBs);
		Collections.sort(allWRs);
		Collections.sort(allQBs);
		/*
		 * Random r = new Random(); int qb1 = r.nextInt(allQBs.size()); int qb2
		 * = r.nextInt(allQBs.size()); while (qb1 == qb2) { qb2 =
		 * r.nextInt(allQBs.size()); }
		 * 
		 * int rb1 = r.nextInt(allRBs.size()); int rb2 =
		 * r.nextInt(allRBs.size()); while (rb1 == rb2) rb1 =
		 * r.nextInt(allRBs.size());
		 * 
		 * int rb3 = r.nextInt(allRBs.size()); while (rb2 == rb3 || rb1 == rb3)
		 * rb3 = r.nextInt(allRBs.size());
		 * 
		 * int rb4 = r.nextInt(allRBs.size()); while (rb1 == rb4 || rb2 == rb4
		 * || rb3 == rb4) rb4 = r.nextInt(allRBs.size());
		 * 
		 * int rb5 = r.nextInt(allRBs.size()); while (rb1 == rb5 || rb2 == rb5
		 * || rb3 == rb5 || rb4 == rb5) rb5 = r.nextInt(allRBs.size());
		 * 
		 * int rb6 = r.nextInt(allRBs.size()); while (rb1 == rb6 || rb2 == rb6
		 * || rb3 == rb6 || rb4 == rb6 || rb5 == rb6) rb6 =
		 * r.nextInt(allRBs.size());
		 * 
		 * int wr1 = r.nextInt(allWRs.size()); int wr2 =
		 * r.nextInt(allWRs.size()); while (wr1 == wr2) wr1 =
		 * r.nextInt(allWRs.size());
		 * 
		 * int wr3 = r.nextInt(allWRs.size()); while (wr2 == wr3 || wr1 == wr3)
		 * wr3 = r.nextInt(allWRs.size());
		 * 
		 * int wr4 = r.nextInt(allWRs.size()); while (wr1 == wr4 || wr2 == wr4
		 * || wr3 == wr4) wr4 = r.nextInt(allWRs.size());
		 * 
		 * int wr5 = r.nextInt(allWRs.size()); while (wr1 == wr5 || wr2 == wr5
		 * || wr3 == wr5 || wr4 == wr5) wr5 = r.nextInt(allRBs.size());
		 * 
		 * int te1 = r.nextInt(allTEs.size());
		 * 
		 * int te2 = r.nextInt(allTEs.size()); while (te1 == te2) { te2 =
		 * r.nextInt(allTEs.size()); }
		 * 
		 * 
		 * Roster ros = new Roster(); ros.addPlayer(allQBs.get(qb1));
		 * ros.addPlayer(allQBs.get(qb2)); ros.addPlayer(allRBs.get(rb1));
		 * ros.addPlayer(allRBs.get(rb2)); ros.addPlayer(allRBs.get(rb3));
		 * ros.addPlayer(allRBs.get(rb4)); ros.addPlayer(allRBs.get(rb5));
		 * ros.addPlayer(allWRs.get(wr1)); ros.addPlayer(allWRs.get(wr2));
		 * ros.addPlayer(allWRs.get(wr3)); ros.addPlayer(allWRs.get(wr4));
		 * ros.addPlayer(allWRs.get(wr5)); ros.addPlayer(allTEs.get(te1)); //
		 * ros.addPlayer(allTEs.get(te2));
		 * 
		 * System.out.println(ros.toString() + "\n" + ros.getTeamValue());
		 * 
		 * System.out.println();
		 */
		double baseLineWeekL = 2.5 * RBPlayer.LAST_RB_DRAFTED_BASELINE / 16
				+ 2.5 * WRPlayer.LAST_WR_DRAFTED_BASELINE / 16
				+ TEPlayer.LAST_TE_DRAFTED_BASELINE / 16
				+ QBPlayer.LAST_QB_DRAFTED_BASELINE / 16 + 141 / 16 + 110 / 16; // +
																				// 107/16;
		double baseLineWeekB = 2 * RBPlayer.BASELINE_RB_SCORE / 16 + 2
				* WRPlayer.BASELINE_WR_SCORE / 16 + TEPlayer.BASELINE_TE_SCORE
				/ 16 + QBPlayer.BASELINE_QB_SCORE / 16 + 141 / 16 + 110 / 16
				+ 125.6 / 16;

		double baseLineWeekNoTDs = 2 * RBPlayer.POINTS_WITHOUT_TDS_BASELINE_RB
				/ 16 + 2 * WRPlayer.POINTS_WITHOUT_TDS_BASELINE_WR / 16
				+ TEPlayer.POINTS_WITHOUT_TDS_BASELINE_TE / 16
				+ QBPlayer.POINTS_WITHOUT_TDS_BASELINE_QB / 16 + 141 / 16 + 110
				/ 16 + (125.6 - 6 * 6) / 16;

		double conversionFactor = 1;
		double VBDWeek = 100 - baseLineWeekB;
		System.out.println("BASELINE WEEK:  " + baseLineWeekB);
		System.out.println("VBD WEEK:  " + VBDWeek + "\n");
		for (int i = 1; i < 60; i++) {
			System.out
					.println("PPG NEEDED TO BE WORTH "
							+ i
							+ ":  "
							+ (i * VBDWeek * conversionFactor / 200 + RBPlayer.BASELINE_RB_SCORE/16));
		}
		double totalRB = 0;
		int aboveBaselineRB = 0;
		int numberRB = 0;
		for (Player rb : allRBs) {
			numberRB++;
			double value = rb.ppgRelativeToBaseline / VBDWeek * 200
					/ conversionFactor;
			// * (1 - Roster.PERCENTAGE_OF_GAME_MISSED_RB);
			if (value > 0) {
				totalRB += value;
				aboveBaselineRB++;
			} else {
				value = rb.ppgRelativeToLastPlayerBaseline / VBDWeek * 200
						* Roster.PERCENTAGE_OF_GAME_MISSED_RB
						/ conversionFactor;
			}
			System.out.println(numberRB + "  " + rb.name + "  " + value + "  "
					+ rb.pointsPerGame + "  "
					+ rb.ppgRelativeToBaseline + "  "
					+ rb.ppgRelativeToLastPlayerBaseline + "  "
					+ rb.totalPoints + "  " + "DIFFERENCE IN VALUE:  "
					+ (value - rb.dollarValue));
		}
		System.out.println();
		System.out.println("ABOVE BASELINE RB:  " + aboveBaselineRB);
		System.out.println("TOTAL RB:  " + totalRB);
		System.out.println();

		double totalTE = 0;
		int aboveBaselineTE = 0;
		int numberTE = 0;
		for (Player te : allTEs) {
			numberTE++;
			double value = te.ppgRelativeToBaseline / VBDWeek * 200
					/ conversionFactor;
			if (value > 0) {
				totalTE += value;
				aboveBaselineTE++;
			} else {
				value = te.ppgRelativeToLastPlayerBaseline / VBDWeek * 200
						* Roster.PERCENTAGE_OF_GAME_MISSED_TE
						/ conversionFactor;
			}
			System.out.println(numberTE + "  " + te.name + "  " + value + "  "
					+ te.ppgRelativeToBaseline + "  "
					+ "DIFFERENCE IN VALUE:  " + (value - te.dollarValue));
		}
		System.out.println();
		System.out.println("TE ABOVE BASELINE:  " + aboveBaselineTE);
		System.out.println("TOTAL TE:  " + totalTE);
		System.out.println();

		double totalWR = 0;
		int aboveBaselineWR = 0;
		int numberWR = 0;
		for (Player wr : allWRs) {
			numberWR++;
			double value = wr.ppgRelativeToBaseline / VBDWeek * 200
					/ conversionFactor;
			if (value > 0) {
				totalWR += value;
				aboveBaselineWR++;
			} else {
				value = wr.ppgRelativeToLastPlayerBaseline / VBDWeek * 200
						* Roster.PERCENTAGE_OF_GAME_MISSED_WR
						/ conversionFactor;
			}
			System.out.println(numberWR + "  " + wr.name + "  " + value + "  "
					+ wr.ppgRelativeToBaseline + "  "
					+ "DIFFERENCE IN VALUE:  " + (value - wr.dollarValue));
		}
		System.out.println();
		System.out.println("WR ABOVE BASELINE:  " + aboveBaselineWR);
		System.out.println("TOTAL WR:  " + totalWR);
		System.out.println();

		double totalQB = 0;
		int aboveBaselineQB = 0;
		int numberQB = 0;
		for (Player qb : allQBs) {
			numberQB++;
			double value = qb.ppgRelativeToBaseline / VBDWeek * 200
					/ conversionFactor;
			if (value > 0) {
				totalQB += value;
				aboveBaselineQB++;
			} else {
				value = qb.ppgRelativeToLastPlayerBaseline / VBDWeek * 200
						* Roster.PERCENTAGE_OF_GAME_MISSED_QB
						/ conversionFactor;
			}
			System.out.println(numberQB + "  " + qb.name + "  " + value + "  "
					+ qb.ppgRelativeToBaseline + "  "
					+ "DIFFERENCE IN VALUE:  " + (value - qb.dollarValue));
		}
		System.out.println();
		System.out.println("ABOVE BASELINE QB:  " + aboveBaselineQB);
		System.out.println("TOTAL QB:  " + totalQB);

		double totalValue = (totalQB + totalTE + totalRB + totalWR);
		System.out.println("TOTAL OVERALL VALUE:  " + totalValue);
		System.out.println(totalValue / 1980);
	}

	public CalculateStats() {
		// parseQBProperties();
	}

	public List<TEPlayer> parseTEProperties() {
		myResources = ResourceBundle.getBundle(PLAYER_RESOURCES_PACKAGE
				+ "TEplayers");
		List<TEPlayer> tePlayers = new ArrayList<TEPlayer>();
		for (Object obj : myResources.keySet()) {
			String[] playerParts = myResources.getString((String) obj).split(
					",");
			int dollarValue = Integer.parseInt(playerParts[0]);
			int recYards = Integer.parseInt(playerParts[1]);
			int recTDs = Integer.parseInt(playerParts[2]);
			int turnovers = Integer.parseInt(playerParts[3]);

			TEPlayer toAdd = new TEPlayer((String) obj, dollarValue, recYards,
					recTDs, turnovers);
			tePlayers.add(toAdd);
		}

		return tePlayers;
	}

	public List<WRPlayer> parseWRProperties() {
		myResources = ResourceBundle.getBundle(PLAYER_RESOURCES_PACKAGE
				+ "WRplayers");
		List<WRPlayer> wrPlayers = new ArrayList<WRPlayer>();
		for (Object obj : myResources.keySet()) {
			String[] playerParts = myResources.getString((String) obj).split(
					",");
			int dollarValue = Integer.parseInt(playerParts[0]);
			int rushingYards = Integer.parseInt(playerParts[1]);
			int rushingTDs = Integer.parseInt(playerParts[2]);
			int recYards = Integer.parseInt(playerParts[3]);
			int recTDs = Integer.parseInt(playerParts[4]);
			int turnovers = Integer.parseInt(playerParts[5]);

			WRPlayer toAdd = new WRPlayer((String) obj, dollarValue,
					rushingYards, rushingTDs, recYards, recTDs, turnovers);
			wrPlayers.add(toAdd);
		}

		return wrPlayers;
	}

	public List<RBPlayer> parseRBProperties() {
		myResources = ResourceBundle.getBundle(PLAYER_RESOURCES_PACKAGE
				+ "RBplayers");
		List<RBPlayer> rbPlayers = new ArrayList<RBPlayer>();
		for (Object obj : myResources.keySet()) {
			String[] playerParts = myResources.getString((String) obj).split(
					",");
			int dollarValue = Integer.parseInt(playerParts[0]);
			int rushingYards = Integer.parseInt(playerParts[1]);
			int rushingTDs = Integer.parseInt(playerParts[2]);
			int recYards = Integer.parseInt(playerParts[3]);
			int recTDs = Integer.parseInt(playerParts[4]);
			int turnovers = Integer.parseInt(playerParts[5]);

			RBPlayer toAdd = new RBPlayer((String) obj, dollarValue,
					rushingYards, rushingTDs, recYards, recTDs, turnovers);
			rbPlayers.add(toAdd);
		}

		return rbPlayers;
	}

	public List<QBPlayer> parseQBProperties() {
		myResources = ResourceBundle.getBundle(PLAYER_RESOURCES_PACKAGE
				+ "QBplayers");
		List<QBPlayer> qbPlayers = new ArrayList<QBPlayer>();
		for (Object obj : myResources.keySet()) {
			String[] playerParts = myResources.getString((String) obj).split(
					",");
			int dollarValue = Integer.parseInt(playerParts[0]);
			int passingYards = Integer.parseInt(playerParts[1]);
			int passingTDs = Integer.parseInt(playerParts[2]);
			int rushingYards = Integer.parseInt(playerParts[3]);
			int rushingTDs = Integer.parseInt(playerParts[4]);
			int turnovers = Integer.parseInt(playerParts[5]);

			QBPlayer toAdd = new QBPlayer((String) obj, dollarValue,
					passingYards, passingTDs, rushingYards, rushingTDs,
					turnovers);
			qbPlayers.add(toAdd);
		}

		return qbPlayers;
	}
}