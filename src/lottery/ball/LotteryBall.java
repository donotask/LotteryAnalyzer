package lottery.ball;

public class LotteryBall implements Comparable<LotteryBall> {
	
	// Values vary per type of lotteryBall
	protected String type;
	protected int maxNumber;
	
	// Values vary per draw
	protected int number;
	
	public LotteryBall(String type, int maxNumber) {
		this.type = type;
		this.maxNumber = maxNumber;
	}
	
	public LotteryBall(LotteryBall ball) {
		this(ball.getType(), ball.getMaxNumber());
		number = ball.getNumber();
	}
	
	public int getMaxNumber() {
		return maxNumber;
	}
	
	public String getType() {
		return type;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public boolean equals(Object obj) {
		try {
			LotteryBall ball;
			// If the obj is not lottery ball, fail
			if (!(obj instanceof LotteryBall)) {
				return false;
			}
			ball = (LotteryBall) obj;
			
			if (this.getType().equals(ball.getType()) && this.getNumber() == ball.getNumber()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Override
	public int compareTo(LotteryBall targetBall) {
		// TODO Auto-generated method stub
		if (!this.getType().equals(targetBall.getType())) {
			return 0;
		}
		return this.getNumber() - targetBall.getNumber();
	}
	
}
