package football;

public class WRPlayer extends Player {

	//projected baseline = 141
	public static final double BASELINE_WR_SCORE = 138;//137.7;
	public static final double LAST_WR_DRAFTED_BASELINE = 89;
	public static final double BASELINE_TDS = 6.5;
	public static final double POINTS_WITHOUT_TDS_BASELINE_WR = BASELINE_WR_SCORE
			- BASELINE_TDS * 6;
	public static final double LAST_BASELINE_TDS = 2;
	public static final double POINTS_WITHOUT_TDS_LAST_WR = LAST_WR_DRAFTED_BASELINE
			- LAST_BASELINE_TDS * 6;

	public WRPlayer(String name, int dollarValue, double rushYards, double rushTDs,
			double recYards, double recTDs, double turnovers) {
		super(name, dollarValue, 0, 0, rushYards, rushTDs, recYards, recTDs,
				turnovers);
		pointsRelativeToBaseline = (this.totalPoints - BASELINE_WR_SCORE);
		ppgRelativeToBaseline = pointsRelativeToBaseline / 16;
		ppgRelativeToLastPlayerBaseline = (this.totalPoints - LAST_WR_DRAFTED_BASELINE) / 16;
	}

	public String toString() {
		return "WR  " + name;
	}

	@Override
	public double getPPGRelativeToNoTDsBaseline(boolean isLastPlayerBaseline) {
		if (!isLastPlayerBaseline)
			return (totalPoints - totalPassTDs * 4 - totalRushRecTDs * 6 - POINTS_WITHOUT_TDS_BASELINE_WR) / 16;
		else
			return (totalPoints - totalPassTDs * 4 - totalRushRecTDs * 6 - POINTS_WITHOUT_TDS_LAST_WR) / 16;

	}
}