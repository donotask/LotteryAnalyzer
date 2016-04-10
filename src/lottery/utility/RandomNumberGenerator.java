package lottery.utility;

public class RandomNumberGenerator {
	
	private static java.util.Random randomGenerator = new java.util.Random();
	
	/**
	 * Returns remainder of a random number by numerRange (randomNum%numberRange)
	 * @param numberRange
	 * @return
	 */
	public static int getRandomNumber(int numberRange) {
		return (Math.abs(randomGenerator.nextInt() % numberRange))+1;
	}

}
