package football;

public class VBDRATeam extends Team{

	public VBDRATeam(int budget) {
		super(budget);
	}
	
	@Override
	public int getMaxBidForPlayer(Player p) {
		double VBD = p.pointsRelativeToBaseline;
		double baseLineWeekB = 2 * RBPlayer.BASELINE_RB_SCORE / 16 + 2
				* WRPlayer.BASELINE_WR_SCORE / 16 + TEPlayer.BASELINE_TE_SCORE
				/ 16 + QBPlayer.BASELINE_QB_SCORE / 16 + 141 / 16 + 110 / 16
				+ 125.6 / 16;
		// using value given in the paper
		double pointsNeededOverBaseline = 94*16 - baseLineWeekB * 16;
		double riskFactor = 0.4;
		double priceBonus = 1;
		double priceDiscount = 1;
		int playersAtPosition = getNumPlayersAtSamePosition(p);
		int startersAtPosition = getNumStartersAtSamePosition(p);
		int maxPlayersAtPosition = getMaxPlayersAtSamePosition(p);
		
		double firstTerm = (VBD*startingBudget*(1-riskFactor)) / pointsNeededOverBaseline;
		double value = 0;
		if (playersAtPosition < startersAtPosition)
			value = firstTerm * priceBonus;
		else if (playersAtPosition > startersAtPosition && playersAtPosition < maxPlayersAtPosition)
			value = firstTerm * priceDiscount;
		else
			value = 1;
		
		value = value / 0.7727272727272727;
		
		if (value > getMaxBid())
			value = getMaxBid();
		
		if (value < 1)
			value = 1;
		
		return (int) Math.floor(value);
	}

}
