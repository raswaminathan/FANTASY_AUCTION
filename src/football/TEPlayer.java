package football;

public class TEPlayer extends Player {

	//projected baseline = 103;
	public static final double BASELINE_TE_SCORE = 95.1;//100;//95.1;
	public static final double LAST_TE_DRAFTED_BASELINE = 86;
	public static final double BASELINE_TDS = 5.5;
	public static final double POINTS_WITHOUT_TDS_BASELINE_TE = BASELINE_TE_SCORE
			- BASELINE_TDS * 6;
	public static final double LAST_BASELINE_TDS = 4.5;
	public static final double POINTS_WITHOUT_TDS_LAST_TE = LAST_TE_DRAFTED_BASELINE
			- LAST_BASELINE_TDS * 6;

	public TEPlayer(String name, int dollarValue, double recYards, double recTDs,
			double turnovers) {
		super(name, dollarValue, 0, 0, 0, 0, recYards, recTDs, turnovers);
		pointsRelativeToBaseline = (this.totalPoints - BASELINE_TE_SCORE);
		ppgRelativeToBaseline = pointsRelativeToBaseline / 16;
		ppgRelativeToLastPlayerBaseline = (this.totalPoints - LAST_TE_DRAFTED_BASELINE) / 16;
	}

	public String toString() {
		return "TE  " + name;
	}

	@Override
	public double getPPGRelativeToNoTDsBaseline(boolean isLastPlayerBaseline) {
		if (!isLastPlayerBaseline)
			return (totalPoints - totalPassTDs * 4 - totalRushRecTDs * 6 - POINTS_WITHOUT_TDS_BASELINE_TE) / 16;
		else
			return (totalPoints - totalPassTDs * 4 - totalRushRecTDs * 6 - POINTS_WITHOUT_TDS_LAST_TE) / 16;	}
}