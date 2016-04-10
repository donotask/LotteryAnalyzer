package lottery.game.type;

import lottery.ball.LotteryBall;
import lottery.game.LotteryGame;

public class SuperLotto extends LotteryGame {
	
	public SuperLotto() {
		gameType = "SuperLotto";
		balls.add(new LotteryBall("White", 47));
		balls.add(new LotteryBall("White", 47));
		balls.add(new LotteryBall("White", 47));
		balls.add(new LotteryBall("White", 47));
		balls.add(new LotteryBall("White", 47));
		balls.add(new LotteryBall("Red", 27));
	}
	
}
