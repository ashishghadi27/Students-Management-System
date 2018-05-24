package files;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Administrator extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel label;
	public static Register r1;
	Connection conn=null;
	/**
	 * Create the frame.
	 */
	public Administrator() {
		setTitle("Students Details v1.0 Beta");
		conn=Sqlite.dbConnector();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 994, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministratorLogin = new JLabel("Administrator Login");
		lblAdministratorLogin.setToolTipText("One who has Administrator Username and password can Login and can add teachers");
		lblAdministratorLogin.setForeground(new Color(255, 255, 255));
		lblAdministratorLogin.setFont(new Font("Calibri", Font.PLAIN, 35));
		lblAdministratorLogin.setBounds(343, 64, 296, 80);
		contentPane.add(lblAdministratorLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Calibri Light", Font.PLAIN, 26));
		lblUsername.setBounds(299, 218, 129, 38);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setCaretColor(new Color(255, 255, 255));
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		textField.setOpaque(false);
		textField.setBounds(440, 228, 236, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Calibri Light", Font.PLAIN, 26));
		lblPassword.setBounds(299, 327, 129, 38);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setOpaque(false);
		passwordField.setCaretColor(new Color(255, 255, 255));
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		passwordField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		passwordField.setBounds(440, 337, 236, 22);
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Log In");
		btnLogin.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from Administrator where username =? and password=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,textField.getText());
					pst.setString(2,passwordField.getText());
					
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()) {
						count++;
					}
					if(count==1)
					{
						try{
							LoginPage.a1.setVisible(false);
						}
						catch(Exception e1)
						{
							Studentinfo.a2.setVisible(false);
						}
						r1=new Register();
						r1.setVisible(true);
					}
					else if(count>1)
					{
						JOptionPane.showMessageDialog(null,"Duplicate username and password");
						
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Incorrect username and password");
					}
					rs.close();
					pst.close();
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		btnLogin.setBackground(new Color(204, 51, 102));
		btnLogin.setHideActionText(true);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 25));
		btnLogin.setBounds(440, 432, 97, 30);
		contentPane.add(btnLogin);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Administrator.class.getResource("/resources/md-3.png")));
		label.setBounds(0, 0, 988, 695);
		contentPane.add(label);
		try {
			this.getRootPane().setDefaultButton(btnLogin);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
