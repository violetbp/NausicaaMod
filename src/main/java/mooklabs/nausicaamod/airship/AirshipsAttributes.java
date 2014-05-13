package mooklabs.nausicaamod.airship;


public interface AirshipsAttributes {
	
	
	/**Max speed value<br>
	 * no limit -> Integer.MAX_VALUE<br>
	 * should be between 0-100
	 * @return the max speed
	 */
	public int maxSpeed();
	
	/**
	 * airship speed
	 * @return the speed
	 */
	public int getSpeed();
	
	/**Max fuel value<br>
	 * no limit -> Integer.MAX_VALUE<br>
	 * should be between 0-10
	 * @return the max speed
	 */
	public double maxFuel();
	
	/**
	 * airship fuel amount
	 * @return amout of fuel
	 */
	public double getFuel();
	
}
