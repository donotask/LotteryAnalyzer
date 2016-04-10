package lottery.game;

import lottery.game.type.*;

public class LotteryGameFactory {
	
	public static String FANTASY5 = (new Fantasy5()).getGameType();
	public static String MEGAMILLION = (new MegaMillion()).getGameType();
	public static String POWERBALL = (new PowerBall()).getGameType();
	public static String SUPERLOTTO = (new SuperLotto()).getGameType();
	
	public static LotteryGame getLotteryGame(String gameType) {
		LotteryGame game = null;
		if (gameType.equalsIgnoreCase(FANTASY5)) {
			game = new Fantasy5();
		} else if (gameType.equalsIgnoreCase(MEGAMILLION)) {
			game = new MegaMillion();
		} else if (gameType.equalsIgnoreCase(SUPERLOTTO)) {
			game = new SuperLotto();
		} else if (gameType.equalsIgnoreCase(POWERBALL)) {
			game = new PowerBall();
		}
		return game;
	}

}
