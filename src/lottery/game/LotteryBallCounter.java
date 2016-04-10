package lottery.game;

import lottery.ball.LotteryBall;

public class LotteryBallCounter implements Comparable<LotteryBallCounter> {
	
	protected LotteryBall ball;
	protected int size;
	
	public LotteryBallCounter(LotteryBall ball) {
		this.ball = ball;
		size = 0;
	}
	
	public LotteryBall getBall() {
		return ball;
	}
	
	public int getNumber() {
		return ball.getNumber();
	}
	
	public int getSize() {
		return size;
	}
	
	public void increment() {
		synchronized (this) {
			size++;
		}
	}

	@Override
	public int compareTo(LotteryBallCounter counter) {
		return counter.getSize() - size;
	}

}
