package files;
import net.proteanit.sql.DbUtils;

import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.MatteBorder;

import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
@SuppressWarnings("serial")
public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldusername;
	private JTextField textFieldsurname;
	private JTextField textFieldname;
	private JTextField textFieldId;
	private JTable table;
	Connection conn=null;
	private JTextField textFieldpass;
	/**
	 * Create the frame.
	 */
	public void refreshTable1() {
		try {
			String query="select * from TeachersInfo";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void refreshTextfields1() {
		try {
			
				textFieldname.setText(null);		/*code for setting all textfields blank*/
				textFieldId.setText(null);
				textFieldsurname.setText(null);
				textFieldusername.setText(null);
				textFieldpass.setText(null);
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	public Register() {
		setResizable(false);
		setTitle("Students Details v1.0 Beta");
		conn=Sqlite.dbConnector();
		setMaximumSize(new Dimension(1920, 1080));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1310, 785);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 470, 750);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Id = new JLabel("ID:");
		Id.setForeground(Color.WHITE);
		Id.setFont(new Font("Calibri", Font.BOLD, 25));
		Id.setBounds(58, 129, 72, 22);
		panel.add(Id);
		
		JLabel name = new JLabel("Name:");
		name.setForeground(Color.WHITE);
		name.setFont(new Font("Calibri", Font.BOLD, 25));
		name.setBounds(58, 242, 72, 22);
		panel.add(name);
		
		JLabel Surname = new JLabel("Surname:");
		Surname.setForeground(Color.WHITE);
		Surname.setFont(new Font("Calibri", Font.BOLD, 25));
		Surname.setBounds(59, 358, 99, 22);
		panel.add(Surname);
		
		JLabel Username = new JLabel("Username:");
		Username.setForeground(Color.WHITE);
		Username.setFont(new Font("Calibri", Font.BOLD, 25));
		Username.setBounds(58, 477, 113, 22);
		panel.add(Username);
		
		JLabel Password = new JLabel("Password:");
		Password.setForeground(Color.WHITE);
		Password.setFont(new Font("Calibri", Font.BOLD, 25));
		Password.setBounds(58, 593, 113, 22);
		panel.add(Password);
		
		textFieldusername = new JTextField();
		textFieldusername.setOpaque(false);
		textFieldusername.setForeground(Color.WHITE);
		textFieldusername.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		textFieldusername.setColumns(10);
		textFieldusername.setCaretColor(Color.WHITE);
		textFieldusername.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldusername.setBounds(183, 479, 236, 22);
		panel.add(textFieldusername);
		
		textFieldsurname = new JTextField();
		textFieldsurname.setOpaque(false);
		textFieldsurname.setForeground(Color.WHITE);
		textFieldsurname.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		textFieldsurname.setColumns(10);
		textFieldsurname.setCaretColor(Color.WHITE);
		textFieldsurname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldsurname.setBounds(183, 360, 236, 22);
		panel.add(textFieldsurname);
		
		textFieldname = new JTextField();
		textFieldname.setOpaque(false);
		textFieldname.setForeground(Color.WHITE);
		textFieldname.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		textFieldname.setColumns(10);
		textFieldname.setCaretColor(Color.WHITE);
		textFieldname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldname.setBounds(183, 244, 236, 22);
		panel.add(textFieldname);
		
		textFieldId = new JTextField();
		textFieldId.setOpaque(false);
		textFieldId.setForeground(Color.WHITE);
		textFieldId.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		textFieldId.setColumns(10);
		textFieldId.setCaretColor(Color.WHITE);
		textFieldId.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldId.setBounds(183, 131, 236, 22);
		panel.add(textFieldId);
		
		JButton addbutton = new JButton("");
		addbutton.setBorderPainted(false);
		addbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query1="insert into TeachersInfo(ID,Name,Surname,Username,Password) values (?,?,?,?,?)";
					PreparedStatement pst1= conn.prepareStatement(query1);
					pst1.setString(1, textFieldId.getText());
					pst1.setString(2, textFieldname.getText());
					pst1.setString(3, textFieldsurname.getText());
					pst1.setString(4, textFieldusername.getText());
					pst1.setString(5, textFieldpass.getText());
					
					pst1.execute();
					pst1.close();
					} catch(Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
					
				}
				refreshTable1();
			}
		});
		addbutton.setIcon(new ImageIcon(Register.class.getResource("/resources/icons8-Add Filled-50.png")));
		addbutton.setToolTipText("Add Teacher");
		addbutton.setOpaque(false);
		addbutton.setForeground(Color.DARK_GRAY);
		addbutton.setFont(new Font("Calibri", Font.BOLD, 15));
		addbutton.setBackground(new Color(102, 51, 153));
		addbutton.setBounds(381, 13, 77, 67);
		panel.add(addbutton);
		
		textFieldpass = new JTextField();
		textFieldpass.setOpaque(false);
		textFieldpass.setForeground(Color.WHITE);
		textFieldpass.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		textFieldpass.setColumns(10);
		textFieldpass.setCaretColor(Color.WHITE);
		textFieldpass.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldpass.setBounds(183, 595, 236, 22);
		panel.add(textFieldpass);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Register.class.getResource("/resources/16k_material_dark_green-wallpaper-7680x4320.jpg")));
		label.setBounds(0, 0, 470, 750);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(469, 0, 836, 750);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setFont(new Font("Calibri", Font.ITALIC, 30));
		scrollPane.setBounds(0, 146, 847, 617);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(0, 153, 51));
		table.setBackground(new Color(255, 255, 255));
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row=table.getSelectedRow();
					String ID_=(table.getModel().getValueAt(row, 0)).toString();
					String query="select * from TeachersInfo where ID='"+ID_+"' ";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						textFieldId.setText(rs.getString("ID"));
						textFieldname.setText(rs.getString("Name"));
						textFieldsurname.setText(rs.getString("Surname"));
						textFieldusername.setText(rs.getString("Username"));
						textFieldpass.setText((rs.getString("Password")));
					}
					pst.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		table.setRowHeight(30);
		table.setFont(new Font("Calibri", Font.BOLD, 21));
		table.setGridColor(new Color(255, 255, 255));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setFont(new Font("Calibri", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Calibri",Font.BOLD,22));
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setOpaque(false);
		table.setShowGrid(false);
		UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0,0,0,0));
		scrollPane.setViewportView(table);
		
		JButton deletebutton = new JButton("");
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query=" delete from TeachersInfo where ID='"+textFieldId.getText()+"' ";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.execute();
					pst.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				refreshTable1();
				refreshTextfields1();
			}
		});
		deletebutton.setIcon(new ImageIcon(Register.class.getResource("/resources/icons8-Trash-48.png")));
		deletebutton.setToolTipText("Delete");
		deletebutton.setOpaque(false);
		deletebutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		deletebutton.setBackground(new Color(102, 51, 153));
		deletebutton.setBounds(500, 24, 97, 50);
		panel_1.add(deletebutton);
		
		JButton updatebutton = new JButton("");
		updatebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="Update TeachersInfo set ID='"+textFieldId.getText()+"' ,name='"+textFieldname.getText()+"' ,surname='"+textFieldsurname.getText()+"' ,username='"+textFieldusername.getText()+"' ,password='"+textFieldpass.getText()+"' where ID='"+textFieldId.getText()+"' ";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data Updated");
					pst.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
				refreshTable1();
			}
		});
		updatebutton.setIcon(new ImageIcon(Register.class.getResource("/resources/icons8-Installing Updates-48.png")));
		updatebutton.setToolTipText("Update");
		updatebutton.setOpaque(false);
		updatebutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		updatebutton.setBackground(new Color(102, 51, 153));
		updatebutton.setBounds(624, 24, 97, 57);
		panel_1.add(updatebutton);
		
		JButton refreshbutton = new JButton("\r\n");
		refreshbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshTable1();
			}
		});
		refreshbutton.setIcon(new ImageIcon(Register.class.getResource("/resources/icons8-Refresh-48.png")));
		refreshbutton.setToolTipText("Refresh");
		refreshbutton.setOpaque(false);
		refreshbutton.setForeground(new Color(102, 51, 153));
		refreshbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		refreshbutton.setBackground(new Color(102, 51, 153));
		refreshbutton.setBounds(752, 31, 72, 37);
		panel_1.add(refreshbutton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Register.class.getResource("/resources/16k_material_dark_green-wallpaper-7680x4320.jpg")));
		lblNewLabel_1.setBounds(0, 0, 834, 147);
		panel_1.add(lblNewLabel_1);
		refreshTable1();
	}
}
		
	

