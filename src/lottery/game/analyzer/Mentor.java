package lottery.game.analyzer;

import java.util.List;
import java.util.Map;

import lottery.ball.LotteryBall;
import lottery.game.LotteryGame;

public interface Mentor {
	
	public void showRecommendation();
	
	public void showRecommendation(LotteryGame game);
	
	public Map<String, List<LotteryBall>> getRecommendation();

	public List<LotteryBall> getRecommendation(LotteryGame game);

}
