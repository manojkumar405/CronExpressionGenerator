/**
 * 
 */
package org.cronExp.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.cronExp.data.CronExpressionData;
import org.cronExp.data.CronInterval;
import org.cronExp.factory.CronExpressionGeneratorServiceFactory;
import org.cronExp.service.CronExpressionGeneratorService;

/**
 * <p>
 * <code>CronExpressionGeneratorPanel</code> is Panel Component which displays
 * the CRONExpression Generator UI, by encapsulating all the DropDowns needed
 * for the Generation of CRONExpression
 * </p>
 * 
 * @author INST
 * 
 */
public class CronExpressionGeneratorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Map<Integer, String> formatterMap = new HashMap<Integer, String>();

	static {
		formatterMap.put(Calendar.HOUR_OF_DAY, "HH");
		formatterMap.put(Calendar.MINUTE, "mm");
		formatterMap.put(Calendar.MONTH, "MMMMMMMMM");
		formatterMap.put(Calendar.DAY_OF_MONTH, "dd");
		formatterMap.put(Calendar.DAY_OF_WEEK, "EEEEEEEEE");
	}

	private JComboBox<CronInterval> interval;
	private JComboBox<Integer> second;
	private JComboBox<Integer> minute;
	private JComboBox<Integer> hour;
	private JComboBox<Integer> dayOfWeek;
	private JComboBox<Integer> dayOfMonth;
	private JComboBox<Integer> month;
	private JComboBox<Integer> year;

	private JComboBox<Integer> resultsPerPage;

	private JLabel dayOfMonthLabel;
	private JLabel monthLabel;
	private JLabel hourLabel;
	private JLabel minuteLabel;
	private JLabel dayOfWeekLabel;
	private JLabel lastLabel;
	private JLabel resultPerPageLabel;

	private JButton submitButton;

	JToolBar statusBar;
	JLabel statusLabel;
	JTextArea resultTextArea;
	JTable table;
	private CronExpressionData model = new CronExpressionData();

	/**
	 * 
	 */
	public CronExpressionGeneratorPanel(JFrame parent) {
		super();
		instantiateLabels();
		this.minute = addDropDown(Calendar.MINUTE);
		this.hour = addDropDown(Calendar.HOUR_OF_DAY);
		this.dayOfWeek = addDropDown(Calendar.DAY_OF_WEEK);
		this.dayOfMonth = addDropDown(Calendar.DAY_OF_MONTH);
		this.month = addDropDown(Calendar.MONTH);

		this.resultsPerPage = addResultsPerPage();
		this.interval = addIntervalDropDown(this);
		this.submitButton = addSubmitButton();
		addValueBar(parent);

		this.interval.setSelectedIndex(0);
	}

	private JComboBox<Integer> addResultsPerPage() {
		JComboBox<Integer> result = new JComboBox<Integer>();
		ComboBoxModel<Integer> model = new CronExpressionComboBoxModel<Integer>(
				Arrays.asList(5, 10, 25, 100, 200, 500));
		result.setModel(model);
		return result;
	}

	private void addValueBar(JFrame parent) {

		resultTextArea = new JTextArea(1, 1);
		/*
		 * resultTextArea.addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(MouseEvent me) {
		 * 
		 * if (MouseEvent.BUTTON3 == me.getButton()) { JPopupMenu popup = new
		 * JPopupMenu(); JMenuItem copy = new JMenuItem("copy");
		 * copy.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent event) {
		 * resultTextArea.getSelectedText(); resultTextArea.copy(); } });
		 * popup.add(copy); resultTextArea.setComponentPopupMenu(popup); } } });
		 */
		table = new JTable(5, 1);

		table.setModel(new DefaultTableModel() {

			@Override
			public Vector getDataVector() {
				// TODO Auto-generated method stub
				return super.getDataVector();
			}

			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 5;
			}

			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 1;
			}

			@Override
			public Class<Date> getColumnClass(int arg0) {

				return Date.class;
			}

			@Override
			public Object getValueAt(int row, int column) {
				// List<Date> nextDates = model.getNextFiringTimes();
				// return nextDates.get(row);
				return new Date();
			}

		});
		parent.getRootPane().add(BorderLayout.SOUTH, table);
	}

	private void instantiateLabels() {
		this.dayOfMonthLabel = new JLabel("on the");
		this.monthLabel = new JLabel("of");
		this.hourLabel = new JLabel("at");
		this.minuteLabel = new JLabel(":");
		this.dayOfWeekLabel = new JLabel("on");
		this.lastLabel = new JLabel("minutes past the hour");
		this.resultPerPageLabel = new JLabel("Results Per Page: ");
	}

	private JButton addSubmitButton() {
		final JButton button = new JButton("Calculate Expression");
		button.addActionListener(new ActionListener() {
			CronExpressionGeneratorService service = CronExpressionGeneratorServiceFactory.instance()
					.getCronExpressionGeneratorService(false);

			@Override
			public void actionPerformed(ActionEvent e) {
				if (button == e.getSource()) {
					model.setInterval((CronInterval) interval.getSelectedItem());
					model.setMinutes(minute.getItemAt(minute.getSelectedIndex()));
					model.setHour(hour.getItemAt(hour.getSelectedIndex()));
					model.setDayOfWeek(dayOfWeek.getItemAt(dayOfWeek.getSelectedIndex()));
					model.setDayOfMonth(dayOfMonth.getItemAt(dayOfMonth.getSelectedIndex()));
					if (month.getItemAt(month.getSelectedIndex()) != null) {
						model.setMonth(month.getItemAt(month.getSelectedIndex()) + 1);
					}
					model.setResultsPerPage(resultsPerPage.getItemAt(resultsPerPage.getSelectedIndex()));
					button.setEnabled(false);
					try {
						String result = service.generateCronExpression(model);
						JOptionPane.showMessageDialog(button.getParent(), result);
						// resultTextArea.setText("Generated CRON Expression: "
						// + result);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(button.getParent(), e1.getMessage(), "CronExpression Generator",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} finally {
						button.setEnabled(true);
					}
				}

			}
		});
		return button;
	}

	private JComboBox<CronInterval> addIntervalDropDown(JComponent parent) {
		final JComboBox<CronInterval> result = new JComboBox<CronInterval>();
		result.setModel(new DefaultComboBoxModel<CronInterval>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			List<CronInterval> choices = CronInterval.asCollection();
			CronInterval selectedItem;

			@Override
			public int getSize() {
				return choices.size();
			}

			@Override
			public CronInterval getElementAt(int index) {
				return choices.get(index);
			}

			@Override
			public void setSelectedItem(Object anItem) {
				if (anItem instanceof CronInterval) {
					selectedItem = (CronInterval) anItem;
				}
			}

			@Override
			public Object getSelectedItem() {
				return this.selectedItem;
			}
		});
		result.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					CronInterval interval = (CronInterval) e.getItem();
					switch (interval) {
					case MINUTE:
						toggleVisibility(false, minute, hour, dayOfWeek, dayOfMonth, month, dayOfMonthLabel, monthLabel,
								hourLabel, minuteLabel, dayOfWeekLabel, lastLabel, resultPerPageLabel, resultsPerPage);
						break;
					case HOURLY:
						toggleVisibility(false, hour, dayOfWeek, dayOfMonth, month, dayOfMonthLabel, monthLabel,
								minuteLabel, dayOfWeekLabel, submitButton);
						toggleVisibility(true, hourLabel, minute, lastLabel, resultPerPageLabel, resultsPerPage,
								submitButton);
						resultsPerPage.setSelectedIndex(0);
						break;
					case DAILY:
						toggleVisibility(false, dayOfWeek, dayOfMonth, month, dayOfMonthLabel, monthLabel,
								dayOfWeekLabel, lastLabel, submitButton);
						toggleVisibility(true, hourLabel, hour, minuteLabel, minute, resultPerPageLabel, resultsPerPage,
								submitButton);
						resultsPerPage.setSelectedIndex(0);
						break;
					case WEEKLY:
						toggleVisibility(false, dayOfMonth, month, dayOfMonthLabel, monthLabel, lastLabel,
								submitButton);
						toggleVisibility(true, dayOfWeekLabel, dayOfWeek, hourLabel, hour, minuteLabel, minute,
								resultPerPageLabel, resultsPerPage, submitButton);
						resultsPerPage.setSelectedIndex(0);
						break;
					case MONTHLY:
						toggleVisibility(false, dayOfWeek, month, monthLabel, dayOfWeekLabel, lastLabel, submitButton);
						toggleVisibility(true, dayOfMonthLabel, dayOfMonth, hourLabel, hour, minuteLabel, minute,
								resultPerPageLabel, resultsPerPage, submitButton);
						resultsPerPage.setSelectedIndex(0);
						break;
					case YEARLY:
						toggleVisibility(false, dayOfWeek, dayOfWeekLabel, lastLabel, submitButton);
						toggleVisibility(true, dayOfMonthLabel, dayOfMonth, monthLabel, month, hourLabel, hour,
								minuteLabel, minute, resultPerPageLabel, resultsPerPage, submitButton);
						resultsPerPage.setSelectedIndex(0);
						break;
					}
				}

			}
		});
		parent.add(new JLabel("Every"));
		parent.add(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	private void toggleVisibility(boolean visibility, JComponent... components) {
		if (components != null && components.length > 0) {
			for (final JComponent component : components) {
				if (!visibility) {
					if (component.getParent() != null) {
						component.getParent().remove(component);
					}

				} else {
					if (component instanceof JComboBox) {
						((JComboBox<Integer>) component).setSelectedIndex(-1);
					}
					this.add(component);
				}
			}
			// re-validate the JPanel component due to changes made above
			this.revalidate();
		}
	}

	private JComboBox<Integer> addDropDown(int calendarField) {
		JComboBox<Integer> result = new JComboBox<Integer>();
		result.setModel(getAppropriateModelList(calendarField));
		result.setRenderer(getAppropriateCellRenderer(calendarField));
		this.add(result);
		return result;
	}

	private ComboBoxModel<Integer> getAppropriateModelList(int calendarField) {
		ComboBoxModel<Integer> model = null;
		List<Integer> choices = Collections.emptyList();
		switch (calendarField) {
		case Calendar.MINUTE:
			choices = getIntegersInRange(0, 59);
			break;
		case Calendar.HOUR_OF_DAY:
			choices = getIntegersInRange(0, 23);
			break;
		case Calendar.DAY_OF_WEEK:
			choices = getIntegersInRange(1, 7);
			break;
		case Calendar.DAY_OF_MONTH:
			choices = getIntegersInRange(1, 31);
			break;
		case Calendar.MONTH:
			choices = getIntegersInRange(0, 11);
			break;
		}
		model = new CronExpressionComboBoxModel<Integer>(choices);
		return model;
	}

	private class CronExpressionComboBoxModel<T> extends DefaultComboBoxModel<T> {
		final List<T> choicesModel;
		T selectedValue;
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CronExpressionComboBoxModel(List<T> choices) {
			super();
			choicesModel = choices;
		}

		@Override
		public int getSize() {
			return choicesModel.size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setSelectedItem(Object anItem) {
			selectedValue = (T) anItem;
		}

		@Override
		public T getElementAt(int index) {
			if (index >= 0) {
				return choicesModel.get(index);
			} else {
				return null;
			}
		}

		@Override
		public Object getSelectedItem() {
			return selectedValue;
		}
	}

	private List<Integer> getIntegersInRange(int min, int max) {
		List<Integer> integers = new ArrayList<Integer>(max - min);

		for (int i = min; i <= max; i++) {
			integers.add(new Integer(i));
		}
		return integers;
	}

	private ListCellRenderer<Integer> getAppropriateCellRenderer(final int calendarField) {
		ListCellRenderer<Integer> renderer = new ListCellRenderer<Integer>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Integer> list, Integer value, int index,
					boolean isSelected, boolean cellHasFocus) {

				String text = "";
				if (value != null) {
					switch (calendarField) {

					case Calendar.DAY_OF_WEEK:
						text = getFormattedText(Calendar.DAY_OF_WEEK, value);
						break;
					case Calendar.DAY_OF_MONTH:
						String suffix = "th";
						if (!(value >= 11 && value <= 13)) {

							int rem = value % 10;

							switch (rem) {
							case 1:
								suffix = "st";
								break;
							case 2:
								suffix = "nd";
								break;
							case 3:
								suffix = "rd";
								break;
							}
						}
						text = getFormattedText(Calendar.DAY_OF_MONTH, value);
						text += suffix;
						break;
					case Calendar.MONTH:
						text = getFormattedText(Calendar.MONTH, value);
						break;

					case Calendar.MINUTE:
						text = getFormattedText(Calendar.MINUTE, value);
						break;
					case Calendar.HOUR_OF_DAY:
						text = getFormattedText(Calendar.HOUR_OF_DAY, value);
						break;

					}
				}
				JLabel label = new JLabel(text);
				return label;
			}

			private String getFormattedText(int CalendarField, int value) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(CalendarField, value);
				Date date = calendar.getTime();
				SimpleDateFormat formatter = new SimpleDateFormat(formatterMap.get(calendarField));
				return formatter.format(date);

			}
		};

		return renderer;
	}
}
