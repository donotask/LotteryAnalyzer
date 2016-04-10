package lottery.game.type;

import lottery.ball.LotteryBall;
import lottery.game.LotteryGame;

public class MegaMillion extends LotteryGame {
	
	public MegaMillion() {
		gameType = "MegaMillion";
		balls.add(new LotteryBall("White", 75));
		balls.add(new LotteryBall("White", 75));
		balls.add(new LotteryBall("White", 75));
		balls.add(new LotteryBall("White", 75));
		balls.add(new LotteryBall("White", 75));
		balls.add(new LotteryBall("Red", 15));
	}

}
