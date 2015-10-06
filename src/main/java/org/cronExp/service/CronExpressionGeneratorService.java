/**
 * 
 */
package org.cronExp.service;

import org.cronExp.data.CronExpressionData;

/**
 * <p>
 * <code>CronExpressionGeneratorService</code> defines Interface for service
 * which Generates CronExpression based on the {@link CronExpressionData} object
 * passed
 * </p>
 * 
 * @author INST
 * 
 */
public interface CronExpressionGeneratorService {

	/**
	 * <p>
	 * Service for Generating the CRON Expression based on the
	 * {@link CronExpressionData} instance passed
	 * </p>
	 * 
	 * @param data
	 *            - the CronExpressionData object from which CRON Expression is
	 *            Generated
	 * @return - {@link String} object representing the CRON Expression
	 * @throws Exception
	 *             - if there any errors due to bad data
	 */
	public String generateCronExpression(CronExpressionData data) throws Exception;
}
