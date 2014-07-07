package football;

public class RBPlayer extends Player {

	// projected baseline = 164
	public static final double BASELINE_RB_SCORE = 151;// 138.5;
	public static final double LAST_RB_DRAFTED_BASELINE = 65;
	public static final double BASELINE_RUSH_TDS = 6 * (1-.34);
	public static final double BASELINE_REC_TDS = 1 * (1-.34);
	public static final double POINTS_WITHOUT_TDS_BASELINE_RB = BASELINE_RB_SCORE
			- BASELINE_RUSH_TDS * 6 - BASELINE_REC_TDS * 6;
	public static final double LAST_BASELINE_RUSH_TDS = 2;
	public static final double LAST_BASELINE_REC_TDS = 0;
	public static final double POINTS_WITHOUT_TDS_LAST_RB = LAST_RB_DRAFTED_BASELINE
			- LAST_BASELINE_RUSH_TDS * 6 - LAST_BASELINE_REC_TDS * 6;

	public RBPlayer(String name, int dollarValue, int rushYards, int rushTDs,
			int recYards, int recTDs, int turnovers) {
		super(name, dollarValue, 0, 0, rushYards, rushTDs, recYards, recTDs,
				turnovers);
		pointsRelativeToBaseline = (this.totalPoints - BASELINE_RB_SCORE);
		ppgRelativeToBaseline = pointsRelativeToBaseline / 16;
		ppgRelativeToLastPlayerBaseline = (this.totalPoints - LAST_RB_DRAFTED_BASELINE) / 16;
	}

	public String toString() {
		return "RB  " + name;
	}

	@Override
	public double getPPGRelativeToNoTDsBaseline(boolean isLastPlayerBaseline) {
		if (!isLastPlayerBaseline)
			return (totalPoints - totalPassTDs * 4 - totalRushRecTDs * 6 * (1-.34) - POINTS_WITHOUT_TDS_BASELINE_RB) / 16;
		else
			return (totalPoints - totalPassTDs * 4 - totalRushRecTDs * 6 * (1-.34) - POINTS_WITHOUT_TDS_LAST_RB) / 16;
	}
}