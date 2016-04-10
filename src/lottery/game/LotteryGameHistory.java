package lottery.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lottery.ball.LotteryBall;
import lottery.error.LotteryException;

public class LotteryGameHistory {
	
	protected static int totalBalls = 0;
	
	// Map<String(gameType), Map<String(ballType), Map<int(Number), int(Count)>
	protected static Map<String, Map<String, Map<Integer, LotteryBallCounter>>> records = new TreeMap<String, Map<String, Map<Integer, LotteryBallCounter>>>();
	
	private LotteryGameHistory() {
	}
	
	public static int getTotalBalls() {
		return totalBalls;
	}
	
	protected synchronized static void addTotalBalls() {
		totalBalls++;
	}
	
	public static void logGame(LotteryGame game) throws LotteryException {
		if (game == null || LotteryGameFactory.getLotteryGame(game.getGameType()) == null) {
			throw new LotteryException("Unknown Games");
		}
		if (!game.isBallDrawn()) {
			throw new LotteryException("Game is not ready yet");
		}
		if (records.get(game.getGameType()) == null) {
			addNewGameType(game);
		}
		List<LotteryBall> drawnBalls = game.getBalls();
		for (LotteryBall ball: drawnBalls) {
			LotteryBallCounter counter = records.get(game.getGameType()).get(ball.getType()).get(ball.getNumber());
			counter.increment();
			addTotalBalls();
		}
	}
	
	public Map<String, Map<String, Map<Integer, LotteryBallCounter>>> getRecords() {
		return records;
	}
	
	public Map<String, Map<Integer, LotteryBallCounter>> getRecords(String gameType) {
		return records.get(gameType);
	}
	
	protected synchronized static void addNewGameType(LotteryGame game) {
		if (records.get(game.getGameType()) == null) {
			Map<String, Map<Integer, LotteryBallCounter>> ballTypes = new TreeMap<String, Map<Integer, LotteryBallCounter>>();
			for (LotteryBall ball: game.getBalls()) {
				if (ballTypes.get(ball.getType()) == null) {
					Map<Integer, LotteryBallCounter> numberCounts = new TreeMap<Integer, LotteryBallCounter>();
					for (int i = 1; i <= ball.getMaxNumber(); i++) {
						LotteryBall tempBall = new LotteryBall(ball.getType(), ball.getMaxNumber());
						tempBall.setNumber(i);
						LotteryBallCounter counter = new LotteryBallCounter(tempBall);
						numberCounts.put(i, counter);
					}
					ballTypes.put(ball.getType(), numberCounts);
				}
			}
			records.put(game.getGameType(), ballTypes);
		}
	}
	
	public static void showStatistic() {
		for (Map.Entry<String, Map<String, Map<Integer, LotteryBallCounter>>> gameEntry: records.entrySet()) {
			System.out.println("========" + gameEntry.getKey());
			for (Map.Entry<String, Map<Integer, LotteryBallCounter>> ballTypeEntry: gameEntry.getValue().entrySet()) {
				System.out.println("====" + ballTypeEntry.getKey());
				for (Map.Entry<Integer, LotteryBallCounter> counterEntry: ballTypeEntry.getValue().entrySet()) {
					LotteryBallCounter counter = counterEntry.getValue();
					System.out.println(counter.getNumber() + ":  " + counter.getSize());
				}
			}
		}
	}
	
	public static void showRecommendation() {
		for (String gameType: records.keySet()) {
			System.out.println("====GAME: " + gameType + "====");
			showRecommendation(gameType);
		}
	}
	
	public static void showRecommendation(String gameType) {
		Map<String, Map<Integer, LotteryBallCounter>> gameData = records.get(gameType);
		if (gameData == null || gameData.size() == 0) {
			System.out.println("Not data for game: " + gameType);
			return;
		}
		
		// Map<ballType, List<LotteryBallCounter>>
		Map<String, LinkedList<LotteryBallCounter>> recommendMap = new HashMap<String, LinkedList<LotteryBallCounter>>();
		for (Map.Entry<String, Map<Integer, LotteryBallCounter>> ballEntry: gameData.entrySet()) {
			LinkedList<LotteryBallCounter> lbcList = new LinkedList<LotteryBallCounter>();
			// Collect all LotteryBallCounter and add them into list
			for (LotteryBallCounter ballCounter: ballEntry.getValue().values()) {
				lbcList.add(ballCounter);
			}
			Collections.sort(lbcList);
			recommendMap.put(ballEntry.getKey(), lbcList);
		}
		
		
		LotteryGame game = LotteryGameFactory.getLotteryGame(gameType);
		for (LotteryBall ball: game.getBalls()) {
			LinkedList<LotteryBallCounter> lbcList = recommendMap.get(ball.getType());
			System.out.println(ball.getType() + ": " + lbcList.pollFirst().getNumber());
		}
	}
	
}