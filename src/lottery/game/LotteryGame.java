package lottery.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lottery.ball.LotteryBall;
import lottery.error.LotteryException;
import lottery.utility.RandomNumberGenerator;

public abstract class LotteryGame implements Runnable {
	
	protected String gameType;
	protected List<LotteryBall> balls = new ArrayList<LotteryBall>();
	protected boolean isBallDrawn = false;
	
	public LotteryGame() {
		gameType = "LotteryGame";
	}
	
	public String getGameType() {
		return gameType;
	}
	
	public List<LotteryBall> getBalls() {
		return balls;
	}
	
	public boolean isBallDrawn() {
		return isBallDrawn;
	}
	
	public List<LotteryBall> draw() throws LotteryException {
		for (LotteryBall ball: balls) {
			LotteryBall tempBall = new LotteryBall(ball.getType(), ball.getMaxNumber());
			tempBall.setNumber(RandomNumberGenerator.getRandomNumber(tempBall.getMaxNumber()));
			// if current ball has been drawn, keep drawning
			while(balls.contains(tempBall)) {
				tempBall.setNumber(RandomNumberGenerator.getRandomNumber(tempBall.getMaxNumber()));
			}
			ball.setNumber(tempBall.getNumber());
		}
		isBallDrawn = true;
		Collections.sort(balls);
		logGame();
		return balls;
	}
	
	protected void logGame() throws LotteryException {
		LotteryGameHistory.logGame(this);
	}
	
	public void printGameResult(List<LotteryBall> drawnBalls) {
		System.out.println("====Print " + gameType + " Game Result Start====");
		for (LotteryBall ball: drawnBalls) {
			System.out.println("Color: [" + ball.getType() + "]  Number: [" + ball.getNumber() + "]");
		}
		System.out.println("====Print " + gameType + " Game Result End  ====");
	}

	@Override
	public void run() {
		try {
			draw();
		} catch (LotteryException e) {
			System.err.println("Failed to fraw the game: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
