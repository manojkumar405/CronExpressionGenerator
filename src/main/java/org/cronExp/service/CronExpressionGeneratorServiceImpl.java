/**
 * 
 */
package org.cronExp.service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import org.cronExp.data.CronExpressionData;
import org.cronExp.data.CronInterval;

/**
 * <p>
 * <code>CronExpressionGeneratorServiceImpl</code> is the Implementation for the
 * {@link CronExpressionGeneratorService} interface
 * </p>
 * 
 * @author INST
 * 
 */
public class CronExpressionGeneratorServiceImpl implements
		CronExpressionGeneratorService {

	private static final String FORMAT = "{0} {1} {2} {3} {4} {5} {6}";
	private static final String ANY_VALUE = "*";
	private static final String NO_SPECIFIC_VALUE = "?";

	/**
	 * Day of the Week Formatter with Format EEE
	 */
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"EEE");

	/**
	 * Constructs Default {@link CronExpressionGeneratorServiceImpl} instance
	 */
	public CronExpressionGeneratorServiceImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String generateCronExpression(CronExpressionData data)
			throws Exception {
		String generatedCronExp = "";
		CronInterval interval = data.getInterval();
		List<String> valuesCollection = getDefaultValuesCollection();

		final Calendar now = Calendar.getInstance();
		final Calendar nextFiringTime = Calendar.getInstance(TimeZone
				.getDefault());
		switch (interval) {
		case MINUTE:
			valuesCollection.add(0, data.getSeconds().toString());
			break;
		case HOURLY:
			valuesCollection.add(1, data.getMinutes().toString());
			nextFiringTime.set(Calendar.MINUTE, data.getMinutes());
			List<Date> nextFiringTimes = new ArrayList<Date>();
			for (int i = 0; i < data.getResultsPerPage(); i++) {
				int hour = nextFiringTime.get(Calendar.HOUR_OF_DAY);
				nextFiringTime.set(Calendar.HOUR, ++hour);
				nextFiringTimes.add(nextFiringTime.getTime());
			}
			data.setNextFiringTimes(nextFiringTimes);
			break;
		case DAILY:
			valuesCollection.set(1, data.getMinutes().toString());
			valuesCollection.set(2, data.getHour().toString());
			break;
		case WEEKLY:
			valuesCollection.set(1, data.getMinutes().toString());
			valuesCollection.set(2, data.getHour().toString());
			valuesCollection.set(3, NO_SPECIFIC_VALUE);
			valuesCollection.set(5, getDayOfWeekString(data.getDayOfWeek()));
			break;
		case MONTHLY:
			valuesCollection.set(1, data.getMinutes().toString());
			valuesCollection.set(2, data.getHour().toString());
			valuesCollection.set(3, data.getDayOfMonth().toString());
			break;
		case YEARLY:
			valuesCollection.add(1, data.getMinutes().toString());
			valuesCollection.add(2, data.getHour().toString());
			valuesCollection.add(3, data.getDayOfMonth().toString());
			valuesCollection.add(4, data.getMonth().toString());
			break;

		default:
		}
		generatedCronExp = MessageFormat.format(FORMAT,
				valuesCollection.toArray());

		return generatedCronExp;
	}

	/**
	 * Utility method to get the Day Of Week as String formatted as specified in
	 * {@link #dateFormatter}
	 * 
	 * @param value
	 * @return
	 */
	private String getDayOfWeekString(int value) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, value);
		return dateFormatter.format(calendar.getTime());
	}

	/**
	 * Creates and returns values Collection with Default {@link #ANY_VALUE}
	 * format
	 * 
	 * @return
	 */
	private List<String> getDefaultValuesCollection() {
		List<String> values = new LinkedList<String>();
		values.add("0");
		values.add(ANY_VALUE);
		values.add(ANY_VALUE);
		values.add(ANY_VALUE);
		values.add(ANY_VALUE);
		values.add(NO_SPECIFIC_VALUE);
		values.add(ANY_VALUE);
		return values;
	}
}
