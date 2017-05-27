import java.awt.EventQueue;

import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JTable;
import java.util.GregorianCalendar;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.ibm.icu.util.Calendar;

public class PinCodeLogin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PinCodeLogin window = new PinCodeLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;

	private JPasswordField pc;

	/**
	 * Create the application.
	 */
	public PinCodeLogin() {
		connection = sqliteConnection.c();

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 803, 172);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblPinCodeLogin = new JLabel("Pin Code Login");
		lblPinCodeLogin.setFont(new Font("Castellar", Font.PLAIN, 34));
		lblPinCodeLogin.setBounds(232, 11, 323, 47);
		frame.getContentPane().add(lblPinCodeLogin);

		pc = new JPasswordField();
		pc.setColumns(10);
		pc.setBounds(338, 59, 188, 26);
		frame.getContentPane().add(pc);

		JLabel lblPincode = new JLabel("Pin Code");
		lblPincode.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPincode.setBounds(242, 60, 86, 20);
		frame.getContentPane().add(lblPincode);

		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from Pincode where code=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, pc.getText());
					ResultSet rs = pst.executeQuery();
					int count = 0;

					while (rs.next()) {
						count++;
					}
					if (count == 1) {

						JOptionPane.showMessageDialog(null, "Successfully loged in!");

						Main m = new Main();

						m.setSize(800, 594);

						m.setVisible(true);
						m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						m.newClass();

					} else {
						JOptionPane.showMessageDialog(null, "Pincode is incorrect. Try again");
					}
					rs.close();
					pst.close();

				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Exception caught");
				}
			}
		});
		login.setFont(new Font("Times New Roman", Font.BOLD, 12));
		login.setBounds(360, 96, 116, 27);
		frame.getContentPane().add(login);

	}

	public void newClass() {

		frame.setSize(803, 172);
		frame.setLocation(300, 100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
}