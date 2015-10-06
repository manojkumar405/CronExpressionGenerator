/**
 * 
 */
package org.cronExp.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * <p>
 * <code>CronExpressionWindow</code> displays the Application Window for the
 * CronExpression Generator
 * </p>
 * 
 * @author INST
 * 
 */
public class CronExpressionWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes this Window with Passed window title
	 * 
	 * @param title
	 *            - the title of this window
	 */
	public CronExpressionWindow(String title) {
		super();
		this.initializeDefaultProperties();
		this.addComponents(this);
		this.setVisible(true);
		this.setTitle(title);
		Toolkit.getDefaultToolkit().beep();
	}

	/**
	 * initialize this window with default properties
	 */
	private void initializeDefaultProperties() {
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension dimensions = toolkit.getScreenSize();
		final int width = (int) (dimensions.getWidth() * 0.5);
		final int height = (int) (dimensions.getHeight() * 0.5);
		this.setSize(width, height);
		this.setResizable(false);
		// this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setLocationByPlatform(true);

		// this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Add the {@link CronExpressionGeneratorPanel} instance to this window
	 * 
	 * @param parent
	 */
	private void addComponents(JFrame parent) {
		CronExpressionGeneratorPanel generator = new CronExpressionGeneratorPanel(parent);
		parent.add(BorderLayout.CENTER, generator);
		// parent.getContentPane().add(generator);
		// parent.add(generator);
	}
}
