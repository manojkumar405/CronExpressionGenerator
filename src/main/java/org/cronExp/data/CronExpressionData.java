/**
 * 
 */
package org.cronExp.data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * <code>CronExpressionData</code> is wrapper object for holding the Data
 * required for generating the CRON Expression
 * </p>
 * 
 * @author INST
 * 
 */
public class CronExpressionData {
	private CronInterval interval;
	private Integer seconds;
	private Integer minutes;
	private Integer hour;
	private Integer dayOfWeek;
	private Integer dayOfMonth;
	private Integer month;
	private Integer year;
	private Integer resultsPerPage;

	private List<Date> nextFiringTimes = Arrays.asList(new Date(), new Date(), new Date(), new Date(), new Date());

	/**
	 * Constructs Default {@link CronExpressionData} object
	 */
	public CronExpressionData() {
		super();
	}

	/**
	 * Initializes the {@link CronExpressionData} instance with the passed
	 * values
	 * 
	 * @param interval
	 * @param seconds
	 * @param minutes
	 * @param hour
	 * @param dayOfWeek
	 * @param dayOfMonth
	 * @param month
	 * @param year
	 */
	public CronExpressionData(CronInterval interval, Integer seconds, Integer minutes, Integer hour, Integer dayOfWeek,
			Integer dayOfMonth, Integer month, Integer year, Integer resultsPerPage) {
		this();
		this.interval = interval;
		this.seconds = seconds;
		this.minutes = minutes;
		this.hour = hour;
		this.dayOfWeek = dayOfWeek;
		this.dayOfMonth = dayOfMonth;
		this.month = month;
		this.year = year;
		this.resultsPerPage = resultsPerPage;
	}

	public CronInterval getInterval() {
		return interval;
	}

	public void setInterval(CronInterval interval) {
		this.interval = interval;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getResultsPerPage() {
		return resultsPerPage;
	}

	public void setResultsPerPage(Integer resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}

	public List<Date> getNextFiringTimes() {
		return nextFiringTimes;
	}

	public void setNextFiringTimes(List<Date> nextFiringTimes) {
		this.nextFiringTimes = nextFiringTimes;
	}

}