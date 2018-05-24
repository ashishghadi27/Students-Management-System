package files;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.SwingConstants;


public class Welcome {

	private JFrame welcomepage;
	public static LoginPage p1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.welcomepage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		welcomepage = new JFrame();
		welcomepage.setBackground(new Color(102, 51, 255));
		welcomepage.setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/resources/ShahandAnchor.png")));
		welcomepage.setTitle("Students Details v1.0 Beta");
		welcomepage.setResizable(false);
		welcomepage.setBounds(100, 100, 614, 538);
		welcomepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomepage.getContentPane().setLayout(null);
		
		JButton btnLaunch = new JButton("");
		btnLaunch.setContentAreaFilled(false);
		btnLaunch.setHorizontalTextPosition(SwingConstants.LEADING);
		btnLaunch.setToolTipText("Click Me!");
		btnLaunch.setIcon(new ImageIcon(Welcome.class.getResource("/resources/ShahandAnchor.png")));
		btnLaunch.setOpaque(false);
		btnLaunch.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLaunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				welcomepage.setVisible(false);
				p1=new LoginPage();
				p1.setVisible(true);
			}
		});
		btnLaunch.setFont(new Font("Calibri", Font.PLAIN, 19));
		btnLaunch.setBackground(new Color(102, 51, 153));
		btnLaunch.setForeground(Color.WHITE);
		btnLaunch.setBounds(193, 131, 220, 248);
		welcomepage.getContentPane().add(btnLaunch);
		
		JLabel welcomeback = new JLabel("");
		welcomeback.setToolTipText("Click Me!");
		welcomeback.setIcon(new ImageIcon(Welcome.class.getResource("/resources/os_x_mavericks_standard-wallpaper-1920x1080.jpg")));
		welcomeback.setBounds(0, 0, 608, 503);
		welcomepage.getContentPane().add(welcomeback);
		JRootPane rootPane = SwingUtilities.getRootPane(btnLaunch); 
		rootPane.setDefaultButton(btnLaunch);
	}
}
