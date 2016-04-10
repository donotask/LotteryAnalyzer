import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lottery.game.*;

public class Main {

	public static void main(String[] args) {
		boolean isMultiThread = true;
		int totalGames = 10000;
		
		ExecutorService threadManager = null;
		long startTime = new java.util.Date().getTime();
		System.out.println("==GAME START==  " + startTime);
		try {
			if (isMultiThread) {
				threadManager = Executors.newFixedThreadPool(1000);
				// Fire all the threads
				for (int i = 0; i < totalGames; i++) {
					LotteryGame game = LotteryGameFactory.getLotteryGame(LotteryGameFactory.POWERBALL);
					threadManager.execute(game);
				}
				
				// shutdown the portal to ensure not more tasks got submitted
				threadManager.shutdown();
				if (!threadManager.awaitTermination(10, TimeUnit.SECONDS)) {
					System.out.println("Not all tasks died yet, killing all");
					List<Runnable> unfinished = threadManager.shutdownNow();
					System.out.println("Number of tasks incomplete: " + unfinished.size());
				}
			} else {
				// draw games in the single thread and sequential fashion.
				for (int i = 0; i < totalGames; i++) {
					LotteryGame game = LotteryGameFactory.getLotteryGame(LotteryGameFactory.POWERBALL);
					game.draw();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = new java.util.Date().getTime();
		long totalTime = endTime - startTime;
		System.out.println("==GAME END====  " + endTime);
		System.out.println("Total Time:  " + totalTime);
		
		System.out.println(LotteryGameHistory.getTotalBalls());
//		LotteryGameHistory.showStatistic();
		LotteryGameHistory.showRecommendation();
	}

}
