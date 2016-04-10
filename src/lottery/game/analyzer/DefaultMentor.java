package lottery.game.analyzer;

import java.util.List;
import java.util.Map;

import lottery.ball.LotteryBall;
import lottery.game.LotteryGame;

/**
 * This class define the default behavior of what a mentor should do
 * All mentors should only use their own specialize way to collect and prepare data but present in the same fashion
 * @author pohaosu
 *
 */
public abstract class DefaultMentor implements Mentor {

	@Override
	public void showRecommendation() {
		Map<String, List<LotteryBall>> games = getRecommendation();
		for(Map.Entry<String, List<LotteryBall>> entries: games.entrySet()) {
//			showRecommendation(entries);
		}
	}

	@Override
	public void showRecommendation(LotteryGame game) {
		
	}

	@Override
	public Map<String, List<LotteryBall>> getRecommendation() {
		return null;
	}

	@Override
	public List<LotteryBall> getRecommendation(LotteryGame game) {
		Map<String, List<LotteryBall>> games = getRecommendation();
		return games.get(game.getGameType());
	}

}
