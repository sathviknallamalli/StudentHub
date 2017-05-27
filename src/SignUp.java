import java.awt.Font;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

import java.awt.Toolkit;

import java.sql.*;
import java.util.Properties;

public class SignUp extends JFrame {
	private JTextField nametextField;
	private JTextField lastnameTextField;
	private JTextField email;
	private JTextField UsernameTextField;
	Connection connection = null;
	private JPasswordField pd;
	private JPasswordField confirmpd;

	public SignUp() {
		getContentPane().setLayout(null);

		connection = sqliteConnection.c();

		JLabel lblSignUp = new JLabel("Sign - Up for Calibur");
		lblSignUp.setFont(new Font("Castellar", Font.PLAIN, 34));
		lblSignUp.setBounds(167, 11, 450, 47);
		getContentPane().add(lblSignUp);

		JLabel lblName = new JLabel("Name*");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblName.setBounds(167, 69, 86, 20);
		getContentPane().add(lblName);

		JLabel lblLastName = new JLabel("Last Name*");
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblLastName.setBounds(167, 100, 116, 20);
		getContentPane().add(lblLastName);

		JLabel lblUsername = new JLabel("Email*");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUsername.setBounds(167, 131, 86, 20);
		getContentPane().add(lblUsername);

		JLabel lblUsername_1 = new JLabel("Username*");
		lblUsername_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUsername_1.setBounds(167, 162, 102, 20);
		getContentPane().add(lblUsername_1);

		JLabel lblPassword = new JLabel("Password*");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPassword.setBounds(167, 193, 102, 20);

		getContentPane().add(lblPassword);

		JLabel lblConfrimPassword = new JLabel("Confrim Password*");
		lblConfrimPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblConfrimPassword.setBounds(167, 224, 163, 20);
		getContentPane().add(lblConfrimPassword);

		nametextField = new JTextField();
		nametextField.setColumns(10);
		nametextField.setBounds(340, 63, 188, 26);
		getContentPane().add(nametextField);

		lastnameTextField = new JTextField();
		lastnameTextField.setColumns(10);
		lastnameTextField.setBounds(340, 94, 188, 26);
		getContentPane().add(lastnameTextField);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(340, 125, 188, 26);
		getContentPane().add(email);

		UsernameTextField = new JTextField();
		UsernameTextField.setColumns(10);
		UsernameTextField.setBounds(340, 156, 188, 26);
		getContentPane().add(UsernameTextField);

		JLabel lblPasswordsAreNot = new JLabel("<html> Passwords are not the same\r\n");
		lblPasswordsAreNot.setForeground(Color.RED);
		lblPasswordsAreNot.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		getContentPane().add(lblPasswordsAreNot);

		JButton btnNewButton = new JButton("Sign Up!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String p = pd.getText();
				String cp = confirmpd.getText();
				String em = email.getText();
				boolean same = p.equals(cp);
				String un = UsernameTextField.getText();
				boolean exists = false;

				if (nametextField.getText().isEmpty() || lastnameTextField.getText().isEmpty()
						|| email.getText().isEmpty() || UsernameTextField.getText().isEmpty() || pd.getText().isEmpty()
						|| confirmpd.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in the missing fields");
				} else {
					if (p.length() > 8 && p.contains("!") || p.contains("#") || p.contains("$") || p.contains("*")
							|| p.contains("/") || p.contains("&") || p.contains("^") || p.contains(")")
							|| p.contains("(") || p.contains("`") || p.contains("A") || p.contains("B")
							|| p.contains("C") || p.contains("D") || p.contains("E") || p.contains("F")
							|| p.contains("G") || p.contains("H") || p.contains("I") || p.contains("J")
							|| p.contains("K") || p.contains("L") || p.contains("M") || p.contains("N")
							|| p.contains("O") || p.contains("P") || p.contains("Q") || p.contains("R")
							|| p.contains("S") || p.contains("T") || p.contains("U") || p.contains("V")
							|| p.contains("W") || p.contains("X") || p.contains("Y") || p.contains("Z")) {
						if (same == true) {
							try {
								String query = "select * from EmployeeInfo";
								PreparedStatement pst = connection.prepareStatement(query);
								ResultSet rs = pst.executeQuery();
								while (rs.next()) {
									if (rs.getString("Username").equals(un)) {
										JOptionPane.showMessageDialog(null,
												"This Username already exists, please try another one");
										exists = true;
										break;
									}
								}
							} catch (Exception a) {

							}

							if (exists == false) {
								try {
									String query = "insert into EmployeeInfo (Name,LastName,Email,Username,Password,ConfirmPassword) values (?, ?, ?, ?, ?, ?)";
									PreparedStatement pst = connection.prepareStatement(query);
									pst.setString(1, nametextField.getText());
									pst.setString(2, lastnameTextField.getText());
									pst.setString(3, email.getText());
									pst.setString(4, UsernameTextField.getText());
									pst.setString(5, pd.getText());
									pst.setString(6, confirmpd.getText());

									pst.execute();

									JOptionPane.showMessageDialog(null,
											"You have been signed up!! Please check your email for verification.");

									pst.close();
									close();

								} catch (Exception a) {
									a.printStackTrace();
								}

								// send email

								final String username = "caliburnoreply@gmail.com";
								final String password = "Saibaba1214";
								final String to = email.getText();
								final String name = nametextField.getText();
								final String uname = UsernameTextField.getText();

								Properties props = new Properties();
								props.put("mail.smtp.host", "smtp.gmail.com");
								props.put("mail.smtp.socketFactory.port", "465");
								props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
								props.put("mail.smtp.auth", "true");
								props.put("mail.smtp.port", "465");
								props.put("mail.smtp.password", password);
								props.put("mail.smtp.username", "caliburnoreply@gmail.com");

								Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
									protected PasswordAuthentication getPasswordAuthentification() {
										return new PasswordAuthentication("caliburnoreply@gmail.com", password);
									}

								}

								);
								try {
									Message message = new MimeMessage(session);
									message.setFrom(new InternetAddress("caliburnoreply@gmail.com"));
									message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(em));
									message.setSubject("Signed Up for Calibur!");
									message.setText("Hello, " + name
											+ "\nThank you for signing up for Calibur! Calibur is the ultimate learning experience for students of all ages. You have been successfully signed up and your username is "
											+ uname
											+ ". You will be receiving emails regarding your progress and promotions to this address. Calibur has almost all the tools you use on a computer that have been condensed into this one application! We hope that Calibur can be a frequent tool you use and optimizes your learning through our numerous features. You can go back to the Login Page of Calibur and experience the Virtual Student Hub.");
									Transport transport = session.getTransport("smtp");

									transport.connect("smtp.gmail.com", "caliburnoreply@gmail.com", password);

									Transport.send(message, "caliburnoreply@gmail.com", "Saibaba1214");
									transport.close();

								} catch (Exception a) {
									JOptionPane.showMessageDialog(null, a);
								}
							}

							// end of send email
						}

						else if (same == false) {
							JOptionPane.showMessageDialog(null, "passwords arent the same, please check again");
						}

					} else {
						JOptionPane.showMessageDialog(null, "The password is not strong.");
					}
				}

			}

		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(609, 193, 149, 40);
		getContentPane().add(btnNewButton);

		pd = new JPasswordField();
		pd.setBounds(340, 187, 188, 26);
		getContentPane().add(pd);

		confirmpd = new JPasswordField();
		confirmpd.setBounds(340, 218, 188, 26);
		getContentPane().add(confirmpd);

	}

	public void close() {
		WindowEvent w = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(w);
	}

	public void newClass() {

		setSize(801, 301);
		setLocation(300, 100);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}