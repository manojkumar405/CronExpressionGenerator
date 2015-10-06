/**
 * 
 */
package org.cronExp.data;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * <code>CronInterval</code> is an enum which Represents repeating interval for
 * which the CRON Expression is Generated
 * </p>
 * 
 * @author INST
 * 
 */
public enum CronInterval {
	MINUTE("Minute"), HOURLY("Hourly"), DAILY("Daily"), WEEKLY("Weekly"), MONTHLY("Montly"), YEARLY("Yearly");

	private static final List<CronInterval> collection = Arrays.asList(MINUTE, HOURLY, DAILY, WEEKLY, MONTHLY, YEARLY);
	private final String name;

	private CronInterval(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * <p>
	 * Returns the {@link CronInterval} value based on the ordinal value passed
	 * </p>
	 * 
	 * @param ordinal
	 * @return
	 * @throws Exception
	 *             when an invalid ordinal value is passed
	 */
	public CronInterval getCronIntervalFor(int ordinal) throws Exception {
		final CronInterval interval = collection.get(ordinal);
		if (interval == null) {
			throw new IllegalArgumentException("Invalid ordinal passed");
		}
		return interval;
	}

	/**
	 * <p>
	 * Utility method returns the Possible {@link CronInterval} values as
	 * Collection
	 * </p>
	 * 
	 * @return
	 */
	public static List<CronInterval> asCollection() {
		return collection;
	}
}
