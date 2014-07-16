package football;

public class DefaultTeam extends Team {

	public DefaultTeam(int budget) {
		super(budget);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMaxBidForPlayer(Player p) {
		int max = getMaxBid();
		if (p.dollarValue < max)
			return p.dollarValue;
		else
			return max;
	}

}
