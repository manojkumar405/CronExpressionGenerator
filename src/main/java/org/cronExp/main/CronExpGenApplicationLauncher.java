/**
 * 
 */
package org.cronExp.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.cronExp.ui.CronExpressionWindow;

/**
 * Launches the CRON Expression Application
 * 
 * @author INST
 * 
 */
public class CronExpGenApplicationLauncher {

	/**
	 * Constructs Default {@link CronExpGenApplicationLauncher} instance
	 */
	public CronExpGenApplicationLauncher() {
		super();
	}

	/**
	 * @param args
	 * @throws Exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				new CronExpressionWindow("CRON Expression Generator");
			}
		});
	}

}
