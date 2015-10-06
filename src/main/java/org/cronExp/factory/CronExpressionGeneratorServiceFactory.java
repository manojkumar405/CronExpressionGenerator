/**
 * 
 */
package org.cronExp.factory;

import org.cronExp.service.CronExpressionGeneratorService;
import org.cronExp.service.CronExpressionGeneratorServiceImpl;

/**
 * <p>
 * Factory Class for {@link CronExpressionGeneratorService} implementations
 * </p>
 * 
 * @author INST
 * 
 */
public class CronExpressionGeneratorServiceFactory {

	private static final CronExpressionGeneratorService singletonTarget;
	private static final CronExpressionGeneratorServiceFactory singletonInstance;
	static {
		singletonTarget = new CronExpressionGeneratorServiceImpl();
		singletonInstance = new CronExpressionGeneratorServiceFactory();
	}

	private CronExpressionGeneratorServiceFactory() {
		super();
	}

	/**
	 * Factory method which returns an instance of
	 * {@link CronExpressionGeneratorService}. if the requiresNew param is true
	 * then returns new instance else returns the SingleTonInstance
	 * 
	 * @param requiresNew
	 *            -flag to indicate if new instance of
	 *            {@link CronExpressionGeneratorService} is to be returned or
	 *            not
	 * @return {@link CronExpressionGeneratorService} instance
	 */
	public CronExpressionGeneratorService getCronExpressionGeneratorService(boolean requiresNew) {
		CronExpressionGeneratorService target = singletonTarget;
		if (requiresNew)
			target = new CronExpressionGeneratorServiceImpl();
		return target;
	}

	/**
	 * Returns Instance of this Factory class
	 * 
	 * @return
	 */
	public static CronExpressionGeneratorServiceFactory instance() {
		return singletonInstance;
	}
}
