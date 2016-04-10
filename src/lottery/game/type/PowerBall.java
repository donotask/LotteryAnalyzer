package lottery.game.type;

import lottery.ball.LotteryBall;
import lottery.game.LotteryGame;

public class PowerBall extends LotteryGame {
	
	public PowerBall() {
		gameType = "PowerBall";
		balls.add(new LotteryBall("White", 69));
		balls.add(new LotteryBall("White", 69));
		balls.add(new LotteryBall("White", 69));
		balls.add(new LotteryBall("White", 69));
		balls.add(new LotteryBall("White", 69));
		balls.add(new LotteryBall("Red", 26));
	}
	
}
