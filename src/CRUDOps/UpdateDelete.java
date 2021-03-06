package CRUDOps;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import AInterfaces.sqlConnection;

public class UpdateDelete {

	private JFrame frame;
	public static JTextField ssid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDelete window = new UpdateDelete();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection conn = null;
	private JTextField name;
	private JTextField date;


	DBOperations db = new DBOperations();
	
	public UpdateDelete() {
		initialize();
		conn = sqlConnection.sqlExpress();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Update Delete Operators");
		frame.setBounds(100, 100, 395, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblSsid = new JLabel("SSID");
		lblSsid.setFont(new Font("Arial", Font.PLAIN, 13));
		lblSsid.setBounds(10, 11, 107, 14);
		frame.getContentPane().add(lblSsid);

		ssid = new JTextField();
		ssid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE) {

					evt.consume();
				}
			}
		});
		ssid.setFont(new Font("Arial", Font.PLAIN, 13));
		ssid.setColumns(10);
		ssid.setBounds(10, 27, 119, 20);
		
		frame.getContentPane().add(ssid);

		String genders[] = { " ", "Male", "Female" };

		JComboBox gens = new JComboBox(genders);
		gens.setEditable(true);
		gens.setEnabled(false);

		gens.setFont(new Font("Arial", Font.BOLD, 13));
		frame.getContentPane().add(gens);

		String states[] = { " ", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
				"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
				"NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
				"WI", "WY" };

		JComboBox st = new JComboBox(states);
		st.setEditable(true);
		st.setEnabled(false);

		st.setFont(new Font("Arial", Font.BOLD, 13));
		frame.getContentPane().add(st);

		JLabel label = new JLabel("Name");
		label.setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().add(label);

		name = new JTextField();
		name.setEditable(false);
		name.setFont(new Font("Arial", Font.PLAIN, 13));
		name.setColumns(10);
		frame.getContentPane().add(name);

		JDateChooser jd = new JDateChooser();
		jd.getCalendarButton().setText("Select a date");
		jd.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 12));
		frame.getContentPane().add(jd);

		JButton delete = new JButton("Delete");
		delete.setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().add(delete);

		JLabel label_1 = new JLabel("Date of Birth");
		label_1.setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("Gender");
		label_2.setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().add(label_2);

		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().add(lblState);

		// UPDATE
		JButton editSave = new JButton("Edit");
		editSave.setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().add(editSave);
		st.setBounds(126, 187, 76, 20);
		gens.setBounds(10, 187, 76, 20);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editSave.setBounds(159, 222, 100, 28);
				delete.setBounds(269, 222, 100, 28);
				label.setBounds(10, 74, 46, 14);
				name.setBounds(10, 91, 119, 20);
				label_1.setBounds(10, 122, 107, 14);
				label_2.setBounds(10, 170, 107, 14);
				lblState.setBounds(126, 171, 107, 14);
				date.setBounds(10, 139, 119, 20);
				// check if recird exists
				boolean exists = false;
				try {
					int personSSID = Integer.parseInt( ssid.getText());
					
					PersonInfo person =db.getPersonDetails(personSSID);
					
					
					if (person!=null) {
						name.setText(person.getName());
						date.setText(person.getDob().toString());
						String gender = person.getGender();
						int index;
						if (gender.equals("M")) {
							index = 1;
						} else {
							index = 2;
						}
						gens.setSelectedIndex(index);
						st.setSelectedItem(person.getState());
						
					}else{
						JOptionPane.showMessageDialog(null, "ERROR: This record doesnt exist");
					}
					editSave.setBounds(162, 222, 100, 28);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}

			}
		});

		editSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editSave.setText("Save");
				ssid.setEditable(false);
				gens.setEnabled(true);
				st.setEnabled(true);
				gens.setEditable(true);
				st.setEditable(true);
				name.setEditable(true);
				date.setBounds(0, 0, 0, 0);
				jd.setBounds(10, 139, 181, 20);
				String cSSID = ssid.getText();
				String d = date.getText();
				java.util.Date date2;
				try {
					date2 = new SimpleDateFormat("yyyy-MM-dd").parse(d);
					jd.setDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				editSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {

						PersonInfo person = new PersonInfo();

						person.setName(name.getText());
						person.setDob(jd.getDate());
						person.setSsid(Integer.parseInt(ssid.getText()));
						
						String personGender = "";
						String raw = (String) gens.getSelectedItem();
						if (raw.equals("Male")) {
							personGender = "M";
						} else if (raw.equals("Female")) {
							personGender = "F";
						} 

						person.setGender(personGender);
						person.setState((String) st.getSelectedItem());
						
						db.updatePersonInfo(person);
						
											}
				});
			}
		});
		btnSearch.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSearch.setBounds(142, 25, 100, 23);
		frame.getContentPane().add(btnSearch);

		date = new JTextField();
		date.setEditable(false);
		date.setFont(new Font("Arial", Font.PLAIN, 13));
		date.setColumns(10);
		frame.getContentPane().add(date);

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your information", "",
						JOptionPane.YES_NO_OPTION);
				if (p == 0) {
					
					db.deletePerson(Integer.parseInt(ssid.getText()));
					
				}
			}
		});

	}

	public void newClass() {
		frame.setSize(396, 300);
		frame.setLocation(300, 100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
