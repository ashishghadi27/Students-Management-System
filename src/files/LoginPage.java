package files;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class LoginPage extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	public static Administrator a1;
	public static Studentinfo s1;
	int X,Y;
	public int xx,xy;
	
	Connection conn=null;
	private JPasswordField passwordField;
	
	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/resources/download.png")));
		setTitle("Students Details v1.0 Beta");
		setResizable(false);
		conn=Sqlite.dbConnector();
		setForeground(new Color(245, 245, 220));
		setBackground(new Color(204, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1275, 774);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel signin = new JPanel();
		signin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				setOpacity((float) 0.5);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setOpacity((float)1.0);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setOpacity((float)0.8);
			}
		});
		signin.setBorder(new EmptyBorder(0, 0, 0, 0));
		signin.setBackground(new Color(51, 153, 102));
		signin.setBounds(443, 35, 832, 739);
		contentPane.add(signin);
		signin.setLayout(null);
		
		JLabel lblSignIn = new JLabel("Sign In");
		lblSignIn.setForeground(Color.WHITE);
		lblSignIn.setFont(new Font("Calibri", Font.PLAIN, 50));
		lblSignIn.setBounds(43, 41, 134, 89);
		signin.add(lblSignIn);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Calibri Light", Font.PLAIN, 25));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(43, 160, 134, 24);
		signin.add(lblUsername);
		
		textField = new JTextField();
		textField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		textField.setCaretColor(Color.WHITE);
		textField.setOpaque(false);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(187, 162, 217, 22);
		signin.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 25));
		lblNewLabel.setBounds(43, 223, 114, 24);
		signin.add(lblNewLabel);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="select * from TeachersInfo where username =? and password=?";
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
						Welcome.p1.setVisible(false);
						s1=new Studentinfo();
						s1.setVisible(true);
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
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnLogin.setBackground(new Color(75, 0, 130));
		btnLogin.setForeground(new Color(245, 245, 245));
		btnLogin.setFont(new Font("Calibri Light", Font.BOLD, 19));
		btnLogin.setBounds(187, 319, 94, 33);
		signin.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordField.setForeground(Color.WHITE);
		passwordField.setOpaque(false);
		passwordField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		passwordField.setCaretColor(Color.WHITE);
		passwordField.setBounds(187, 227, 217, 22);
		signin.add(passwordField);
		
		JButton btnAdministrator = new JButton("Admin");
		btnAdministrator.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Welcome.p1.setVisible(false);
				a1=new Administrator();
				a1.setVisible(true);
			}
		});
		btnAdministrator.setBackground(new Color(75, 0, 130));
		btnAdministrator.setForeground(Color.WHITE);
		btnAdministrator.setFont(new Font("Calibri Light", Font.BOLD, 19));
		btnAdministrator.setBounds(307, 319, 97, 33);
		signin.add(btnAdministrator);
		
		JLabel sidebar = new JLabel("New label");
		
		sidebar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xx=e.getX();
				xy=e.getY();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setOpacity((float)1.0);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setOpacity((float)0.9);
			}
			
		});
		sidebar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent a) {
				X=a.getXOnScreen();
				Y=a.getYOnScreen();
				
				Welcome.p1.setLocation(X-xx,Y-xy);
			}
		});
		sidebar.setOpaque(true);
		sidebar.setBackground(new Color(204, 255, 255));
		sidebar.setIcon(new ImageIcon(LoginPage.class.getResource("/resources/IMG-20170905-WA0006 (2).jpg")));
		sidebar.setBounds(0, 35, 444, 739);
		contentPane.add(sidebar);
		
		try {
			this.getRootPane().setDefaultButton(btnLogin);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(51, 153, 102));
			panel.setBounds(0, 0, 1275, 36);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JButton btnNewButton = new JButton("");
			btnNewButton.setBackground(new Color(51, 153, 102));
			btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Welcome.p1.dispose();
				}
			});
			btnNewButton.setOpaque(false);
			btnNewButton.setIcon(new ImageIcon(LoginPage.class.getResource("/resources/icons8-Cancel-35.png")));
			btnNewButton.setBounds(1215, 0, 60, 36);
			panel.add(btnNewButton);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
