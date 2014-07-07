package football;

public class QBPlayer extends Player {

	//projected baseline = 265;
	public static final double BASELINE_QB_SCORE = 251;//268///260.1;
	public static final double LAST_QB_DRAFTED_BASELINE = 222;
	public static final double BASELINE_PASS_TDS = 25;
	public static final double BASELINE_RUSH_TDS = 2;
	public static final double POINTS_WITHOUT_TDS_BASELINE_QB = BASELINE_QB_SCORE
			- BASELINE_RUSH_TDS * 6 - BASELINE_PASS_TDS * 6;
	public static final double LAST_BASELINE_PASS_TDS = 22;
	public static final double LAST_BASELINE_RUSH_TDS = 1;
	public static final double POINTS_WITHOUT_TDS_LAST_QB = LAST_QB_DRAFTED_BASELINE
			- LAST_BASELINE_PASS_TDS * 4 - LAST_BASELINE_RUSH_TDS * 6;

	public QBPlayer(String name, int dollarValue, int passYards, int passTDs,
			int rushYards, int rushTDs, int turnovers) {
		super(name, dollarValue, passYards, passTDs, rushYards, rushTDs, 0, 0,
				turnovers);
		pointsRelativeToBaseline = (this.totalPoints - BASELINE_QB_SCORE);
		ppgRelativeToBaseline = pointsRelativeToBaseline / 16;
		ppgRelativeToLastPlayerBaseline = (this.totalPoints - LAST_QB_DRAFTED_BASELINE) / 16;
	}

	public String toString() {
		return "QB  " + name;
	}

	@Override
	public double getPPGRelativeToNoTDsBaseline(boolean isLastPlayerBaseline) {
		if (!isLastPlayerBaseline)
			return (totalPoints - totalPassTDs * 4 - totalRushRecTDs * 6 - POINTS_WITHOUT_TDS_BASELINE_QB) / 16;
		else
			return (totalPoints - totalPassTDs * 4 - totalRushRecTDs * 6 - POINTS_WITHOUT_TDS_LAST_QB) / 16;
	}
}