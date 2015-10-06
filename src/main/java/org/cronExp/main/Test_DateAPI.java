/**
 * 
 */
package org.cronExp.main;

import java.util.Calendar;
import java.util.Date;

/**
 * @author INST
 *
 */
public class Test_DateAPI {

	/**
	 * 
	 */
	public Test_DateAPI() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.set(Calendar.DAY_OF_MONTH, 1);
		end.set(Calendar.DAY_OF_MONTH, 6);

		Date startDateTime = start.getTime();
		Date endDateTime = end.getTime();

		start = Calendar.getInstance();
		end = Calendar.getInstance();

		start.set(Calendar.DAY_OF_MONTH, 6);
		end.set(Calendar.DAY_OF_MONTH, 10);

		Date rangeStartDateTime = start.getTime();
		Date rangeEndDateTime = end.getTime();
		if (endDateTime.after(rangeStartDateTime)) {
			System.out.println("END IS AFTER RANGE START DATE TIME!!!");
		}
		if (endDateTime.compareTo(rangeStartDateTime) >= 0) {
			System.out.println("END DATE TIME IS GREATER THAN OR EQUAL TO RANGE START DATE TIME!!!");
		}
	}

}
