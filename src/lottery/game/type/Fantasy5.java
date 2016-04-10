package lottery.game.type;

import lottery.ball.LotteryBall;
import lottery.game.LotteryGame;

public class Fantasy5 extends LotteryGame {
	
	public Fantasy5() {
		gameType = "Fantasy5";
		balls.add(new LotteryBall("White", 39));
		balls.add(new LotteryBall("White", 39));
		balls.add(new LotteryBall("White", 39));
		balls.add(new LotteryBall("White", 39));
		balls.add(new LotteryBall("White", 39));
	}
	
}
