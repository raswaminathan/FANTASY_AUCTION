package football;

public abstract class Player implements Comparable {

	private static final int RUSH_TDS_POINTS_DEFAULT = 6;
	private static final int PASS_TDS_POINTS_DEFAULT = 4;
	private static final double RUSH_REC_YARDS_POINTS_DEFAULT = .1;
	private static final double PASS_YARDS_POINTS_DEFAULT = .04;
	private static final int GAMES_PER_SEASON = 16;
	private static final int TURNOVERS_DEFAULT = 2;
	protected String name;
	protected int dollarValue;
	protected double ppgRelativeToBaseline;
	protected double ppgRelativeToLastPlayerBaseline;
	protected double pointsRelativeToBaseline;
	protected double pointsWithoutTDsBaseline = 0;
	private double passYards;
	private double passTDs;
	private double rushYards;
	private double rushTDs;
	private double recYards;
	private double recTDs;
	private double turnovers;
	protected double totalPassTDs;
	protected double totalRushRecTDs;
	protected double totalPoints;
	protected double pointsPerGame;
	protected double pointsPerDollar;
	protected double pointsPerGamePerDollar;

	public Player(String name, int dollarValue, double passYards, double passTDs,
			double rushYards, double rushTDs, double recYards, double recTDs, double turnovers) {
		this.name = name;
		this.dollarValue = dollarValue;
		this.passYards = passYards;
		this.passTDs = passTDs;
		this.rushYards = rushYards;
		this.rushTDs = rushTDs;
		this.recYards = recYards;
		this.recTDs = recTDs;
		this.turnovers = turnovers;
		this.totalPassTDs = passTDs;
		this.totalRushRecTDs = rushTDs + recTDs;
		// uses standard scoring as of now
		this.totalPoints = passYards * PASS_YARDS_POINTS_DEFAULT
				+ (rushYards + recYards) * RUSH_REC_YARDS_POINTS_DEFAULT
				+ passTDs * PASS_TDS_POINTS_DEFAULT + (rushTDs + recTDs)
				* RUSH_TDS_POINTS_DEFAULT - turnovers * TURNOVERS_DEFAULT;
		this.pointsPerGame = totalPoints / GAMES_PER_SEASON;
		this.pointsPerDollar = totalPoints / dollarValue;
		this.pointsPerGamePerDollar = pointsPerGame / dollarValue;
	}

	public double getPointsPerGamePerDollar() {
		return pointsPerGamePerDollar;
	}

	public double getTotalPoints() {
		return totalPoints;
	}

	public double getPointsPerGame() {
		return pointsPerGame;
	}

	public double getPointsPerDollar() {
		return pointsPerDollar;
	}

	public double getPPGRelativeToBaseline() {
		return ppgRelativeToBaseline;
	}

	public double getPPGRelativeToBothBaselines() {
		return .7 * this.ppgRelativeToBaseline + .3
				* this.ppgRelativeToLastPlayerBaseline;
	}

	public double getPPGRelativeToLastPlayerBaseline() {
		return ppgRelativeToLastPlayerBaseline;
	}

	public abstract double getPPGRelativeToNoTDsBaseline(boolean isLastPlayerBaseline); 
	
	public int compareTo(Object obj) {
		Player other = (Player) obj;
		if (this.pointsRelativeToBaseline > other.pointsRelativeToBaseline)
			return -1;
		if (this.pointsRelativeToBaseline == other.pointsRelativeToBaseline)
			return 0;
		else
			return 1;
	}

	public double getPointsPerGamePerDollarRelativeToBaseline() {
		return ppgRelativeToBaseline / this.dollarValue;
	}

	public abstract String toString();
}