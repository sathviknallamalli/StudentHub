import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingConstants;

public class Home extends JFrame {

	Connection connection = null;
	Connection connection1 = null;

	public Home() {
		super("Login");
		getContentPane().setBackground(new Color(240, 240, 240));
		connection = sqliteConnection.c();
		connection1 = sqliteConnection.c();
		setBounds(100, 100, 803, 313);
		getContentPane().setLayout(null);

		JPasswordField passwordField;
		JTextField name;
		JTextField lname;
		JTextField email;
		JTextField un;
		JPasswordField pd;
		JPasswordField cpd;
		JButton sendemail;

		JFrame frame;
		JLabel lblLoginToCalibur = new JLabel("Login to Calibur\r\n");
		lblLoginToCalibur.setFont(new Font("Castellar", Font.PLAIN, 34));
		lblLoginToCalibur.setBounds(209, 11, 365, 47);
		getContentPane().add(lblLoginToCalibur);
		JTextField untf = new JTextField();
		untf.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		untf.setBounds(343, 99, 188, 26);
		getContentPane().add(untf);
		untf.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(247, 100, 86, 20);
		getContentPane().add(lblNewLabel);
		untf.requestFocusInWindow();
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String query = "select * from EmployeeInfo where username=? and password=?";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, untf.getText());
						pst.setString(2, passwordField.getText());
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while (rs.next()) {
							count++;
						}
						if (count == 1) {

							Main m = new Main();
							m.newClass();

						} else {
							JOptionPane.showMessageDialog(null, "Username or password is not correct. Try again");
						}
						rs.close();
						pst.close();
					} catch (Exception a) {
						JOptionPane.showMessageDialog(null, "Exception caught");
					}
				}
			}
		});
		passwordField.setBounds(343, 143, 188, 26);
		getContentPane().add(passwordField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPassword.setBounds(247, 144, 86, 20);
		getContentPane().add(lblPassword);
		untf.requestFocusInWindow();
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String query = "select * from EmployeeInfo where username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, untf.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					int count = 0;
					// String user = untf.getText();

					while (rs.next()) {
						count++;
					}
					if (count == 1) {
						Main m = new Main();
						m.newClass();
						// close();

					} else {
						JOptionPane.showMessageDialog(null, "Username or password is not correct. Try again");
					}
					rs.close();
					pst.close();

				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Exception caught");
				}

			}
		});

		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBounds(224, 180, 124, 41);
		getContentPane().add(btnNewButton);

		JLabel lblIfYouDont = new JLabel(
				"If you dont have an account for Calibur, then please sign up and it is completely free of charge! A verification email will be sent once you sign up.");
		lblIfYouDont.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblIfYouDont.setBounds(43, 54, 697, 23);
		getContentPane().add(lblIfYouDont);

		JButton btnSignup = new JButton("Sign-up!");
		btnSignup.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		btnSignup.setBounds(420, 180, 124, 41);
		getContentPane().add(btnSignup);

		name = new JTextField();
		getContentPane().add(name);
		name.setColumns(10);

		JLabel n = new JLabel("Name\r\n");
		n.setFont(new Font("Times New Roman", Font.BOLD, 18));
		getContentPane().add(n);

		lname = new JTextField();
		lname.setColumns(10);
		getContentPane().add(lname);

		JLabel ln = new JLabel("Last name\r\n");
		ln.setFont(new Font("Times New Roman", Font.BOLD, 18));
		getContentPane().add(ln);

		email = new JTextField();
		email.setColumns(10);
		getContentPane().add(email);

		JLabel el = new JLabel("Email");
		el.setFont(new Font("Times New Roman", Font.BOLD, 18));
		getContentPane().add(el);

		un = new JTextField();
		un.setColumns(10);
		getContentPane().add(un);

		JLabel u = new JLabel("Username\r\n");
		u.setFont(new Font("Times New Roman", Font.BOLD, 18));
		getContentPane().add(u);

		JLabel p = new JLabel("Password");
		p.setFont(new Font("Times New Roman", Font.BOLD, 18));
		getContentPane().add(p);

		pd = new JPasswordField();
		getContentPane().add(pd);

		cpd = new JPasswordField();
		getContentPane().add(cpd);

		JLabel cp = new JLabel("<html>Confrim Pasword");
		cp.setFont(new Font("Times New Roman", Font.BOLD, 18));
		getContentPane().add(cp);

		sendemail = new JButton("Send Email!");
		sendemail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		getContentPane().add(sendemail);

		JLabel pic = new JLabel();
		pic.setIcon(new ImageIcon("C:\\Users\\sathv\\Desktop\\Pics\\profile.png"));
		pic.setBounds(541, 88, 94, 90);
		getContentPane().add(pic);

		JLabel lblTryAFaster = new JLabel("<html>Try a faster login method? Set up a pin code!");
		lblTryAFaster.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblTryAFaster.setBounds(279, 233, 250, 26);
		getContentPane().add(lblTryAFaster);

		JButton btnSetup = new JButton("Pin Code Setup");
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Setup s = new Setup();
				s.newClass();
			}
		});
		btnSetup.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		btnSetup.setBounds(143, 234, 124, 26);
		getContentPane().add(btnSetup);

		JLabel trivia = new JLabel("<html>");
		trivia.setVerticalAlignment(SwingConstants.TOP);
		trivia.setForeground(Color.RED);
		trivia.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		trivia.setBounds(24, 127, 179, 94);
		getContentPane().add(trivia);

		try {
			String query1 = "select * from Trivia";
			PreparedStatement pst1 = connection1.prepareStatement(query1);
			ResultSet rs1 = pst1.executeQuery();
			ArrayList al = new ArrayList();
			while (rs1.next()) {
				String fact = rs1.getString("Fact");
				al.add(fact);
			}

			int length = al.size();
			int rand = 0 + (int) (Math.random() * length);

			String setter = (String) al.get(rand);
			trivia.setText("<html>" + setter);

		} catch (Exception a) {
			a.printStackTrace();
		}

		JLabel lblDailyTrivia = new JLabel("Interesting Trivia!");
		lblDailyTrivia.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblDailyTrivia.setBounds(24, 103, 157, 20);
		getContentPane().add(lblDailyTrivia);

		JLabel forgot = new JLabel("<html>Forgot password?");

		forgot.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				ForgotPassword fp = new ForgotPassword();
				fp.newClass();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		forgot.setForeground(Color.BLUE);
		forgot.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		forgot.setBounds(644, 122, 106, 26);
		getContentPane().add(forgot);

		JButton btnPinCodeLogin = new JButton("Pin Code Login");
		btnPinCodeLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PinCodeLogin pc = new PinCodeLogin();
				pc.newClass();

			}
		});

		btnPinCodeLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		btnPinCodeLogin.setBounds(541, 232, 124, 26);
		getContentPane().add(btnPinCodeLogin);

		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SignUp s = new SignUp();
				s.newClass();
			}
		});

	}

	public void close() {
		WindowEvent w = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(w);
	}
}