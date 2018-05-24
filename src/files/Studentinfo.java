package files;
import java.awt.*;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import net.proteanit.sql.DbUtils;

import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Studentinfo extends JFrame {
	private JTextField textFieldname;
	private JTextField textFieldid;
	private JTextField textFieldmail;
	private JTextField textFieldcontact;
	private JTextField textFieldcomps;
	private JTextField textFieldparents;
	private String gender,date1,month1,year1,all,data,gender1,str;
	private JTextArea textArea;
    @SuppressWarnings("rawtypes")
	private JComboBox date,month,year,search;
    private JRadioButton rdbtnOther,rdbtnMale,rdbtnFemale;
	
    Connection conn=null;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static Administrator a2;
	private JTextField Search;
	/**
	 * Create the frame.
	 */
	public void refreshTable() {
		try{
			String query="select * from StudentsInfo";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			refreshTextfields();
			pst.close();
			} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshTextfields() {
		try {
				textFieldname.setText(null);		/*code for setting all textfields blank*/
				textFieldid.setText(null);
				rdbtnMale.setSelected(true);
				rdbtnFemale.setSelected(false);
				rdbtnOther.setSelected(false);
				textFieldmail.setText(null);
				textArea.setText(null);
				date.setSelectedItem(null);
				month.setSelectedItem(null);
				year.setSelectedItem(null);
				textFieldcontact.setText(null);
				textFieldcomps.setText(null);
				textFieldparents.setText(null);
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Studentinfo() {
		setTitle("Students Details v1.0 Beta");
		conn=Sqlite.dbConnector();
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		panel.setAutoscrolls(true);
		panel.setBackground(new Color(102, 51, 153));
		panel.setBounds(0, 0, 540, 1013);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton addbutton = new JButton("");
		addbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				addbutton.setBorder(new SoftBevelBorder(1));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				addbutton.setBorder(new EmptyBorder(0,0,0,0));
			}
		});
		addbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addbutton.setOpaque(false);
		addbutton.setToolTipText("Add Student");
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query1="insert into StudentsInfo(Name,ID,Gender,Email,Address,Birth,Contact,Field,ParentsNo) values (?,?,?,?,?,?,?,?,?)";
					PreparedStatement pst1= conn.prepareStatement(query1);
					pst1.setString(1, textFieldname.getText());
					pst1.setString(2, textFieldid.getText());
					pst1.setString(3, gender);
					pst1.setString(4, textFieldmail.getText());
					data=textArea.getText();
					pst1.setString(5, data);
					date1=date.getSelectedItem().toString();
					month1=month.getSelectedItem().toString();
					year1=year.getSelectedItem().toString();
					all=date1+" "+month1+" "+year1;
					pst1.setString(6, all);
					pst1.setString(7, textFieldcontact.getText());
					pst1.setString(8, textFieldcomps.getText());
					pst1.setString(9, textFieldparents.getText());
					pst1.execute();
					pst1.close();
				} catch(Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,e);
				}
				refreshTable();
			}
		});
		addbutton.setBackground(new Color(102, 51, 153));
		addbutton.setForeground(Color.WHITE);
		addbutton.setFont(new Font("Calibri", Font.BOLD, 15));
		addbutton.setIcon(new ImageIcon(Studentinfo.class.getResource("/resources/icons8-Add Filled-50.png")));
		addbutton.setBounds(439, 13, 89, 77);
		panel.add(addbutton);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Calibri", Font.BOLD, 25));
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setBounds(42, 112, 72, 22);
		panel.add(lblName);
		
		textFieldname = new JTextField();
		textFieldname.setOpaque(false);
		textFieldname.setBackground(new Color(153, 102, 204));
		textFieldname.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldname.setForeground(Color.WHITE);
		textFieldname.setCaretColor(Color.WHITE);
		textFieldname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldname.setBounds(167, 109, 219, 22);
		panel.add(textFieldname);
		textFieldname.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(SystemColor.textHighlightText);
		lblId.setFont(new Font("Calibri", Font.BOLD, 25));
		lblId.setBounds(84, 190, 56, 25);
		panel.add(lblId);
		
		textFieldid = new JTextField();
		textFieldid.setOpaque(false);
		textFieldid.setBackground(new Color(153, 102, 204));
		textFieldid.setCaretColor(Color.WHITE);
		textFieldid.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldid.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldid.setForeground(SystemColor.textHighlightText);
		textFieldid.setBounds(167, 194, 219, 22);
		panel.add(textFieldid);
		textFieldid.setColumns(10);
		
		JLabel lblBirthDate = new JLabel("Gender:");
		lblBirthDate.setFont(new Font("Calibri", Font.BOLD, 25));
		lblBirthDate.setForeground(SystemColor.textHighlightText);
		lblBirthDate.setBounds(32, 267, 102, 22);
		panel.add(lblBirthDate);
		
		rdbtnMale = new JRadioButton("Male",false);
		rdbtnMale.setOpaque(false);
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gender="Male";
			}
		});
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setSize(new Dimension(1, 1));
		rdbtnMale.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		rdbtnMale.setBackground(new Color(102, 51, 153));
		rdbtnMale.setFont(new Font("Calibri", Font.PLAIN, 20));
		rdbtnMale.setForeground(SystemColor.textHighlightText);
		rdbtnMale.setBounds(167, 267, 77, 25);
		panel.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female",false);
		rdbtnFemale.setOpaque(false);
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender="Female";
			}
		});
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		rdbtnFemale.setFont(new Font("Calibri", Font.PLAIN, 20));
		rdbtnFemale.setBackground(new Color(102, 51, 153));
		rdbtnFemale.setForeground(SystemColor.textHighlightText);
		rdbtnFemale.setBounds(247, 267, 96, 25);
		panel.add(rdbtnFemale);
		
		rdbtnOther = new JRadioButton("Other",false);
		rdbtnOther.setOpaque(false);
		rdbtnOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender="Other";
			}
		});
		buttonGroup.add(rdbtnOther);
		rdbtnOther.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		rdbtnOther.setBackground(new Color(102, 51, 153));
		rdbtnOther.setFont(new Font("Calibri", Font.PLAIN, 20));
		rdbtnOther.setForeground(SystemColor.textHighlightText);
		rdbtnOther.setBounds(341, 267, 77, 25);
		panel.add(rdbtnOther);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Calibri", Font.BOLD, 25));
		lblEmail.setBounds(42, 340, 84, 22);
		panel.add(lblEmail);
		
		textFieldmail = new JTextField();
		textFieldmail.setOpaque(false);
		textFieldmail.setBackground(new Color(153, 102, 204));
		textFieldmail.setCaretColor(Color.WHITE);
		textFieldmail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldmail.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldmail.setForeground(Color.WHITE);
		textFieldmail.setBounds(167, 343, 219, 22);
		panel.add(textFieldmail);
		textFieldmail.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setBounds(32, 429, 106, 31);
		panel.add(lblAddress);
		
		textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setBackground(new Color(102, 153, 204));
		textArea.setLineWrap(true);
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		textArea.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textArea.setCaretColor(Color.WHITE);
		textArea.setFont(new Font("Calibri", Font.PLAIN, 18));
		textArea.setForeground(Color.WHITE);
		textArea.setBounds(167, 437, 220, 92);
		panel.add(textArea);
		
		JLabel lblBirth = new JLabel("Birth:");
		lblBirth.setForeground(Color.WHITE);
		lblBirth.setFont(new Font("Calibri", Font.BOLD, 25));
		lblBirth.setBounds(66, 597, 72, 25);
		panel.add(lblBirth);
		
		date = new JComboBox();
		date.setOpaque(false);
		date.setSelectedItem(null);
		date.setAutoscrolls(true);
		date.setBorder(new EmptyBorder(0, 0, 0, 0));
		date.setForeground(Color.WHITE);
		date.setFont(new Font("Calibri", Font.PLAIN, 18));
		date.setBackground(new Color(51, 51, 51));
		date.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		date.setBounds(166, 600, 46, 22);
		panel.add(date);
		
		month = new JComboBox();
		month.setSelectedItem(null);
		month.setOpaque(false);
		month.setAutoscrolls(true);
		month.setBorder(new EmptyBorder(0, 0, 0, 0));
		month.setBackground(new Color(51, 51, 51));
		month.setFont(new Font("Calibri", Font.PLAIN, 18));
		month.setForeground(Color.WHITE);
		month.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		month.setBounds(224, 600, 119, 22);
		panel.add(month);
	
		year = new JComboBox();
		year.setOpaque(false);
		year.setSelectedItem(null);
		year.setAutoscrolls(true);
		year.setBorder(new EmptyBorder(0, 0, 0, 0));
		year.setFont(new Font("Calibri", Font.PLAIN, 18));
		year.setModel(new DefaultComboBoxModel(new String[] {"1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017"}));
		year.setBackground(new Color(51, 51, 51));
		year.setForeground(Color.WHITE);
		year.setBounds(355, 600, 63, 22);
		panel.add(year);
		
		JLabel lblContactNo = new JLabel("Contact:");
		lblContactNo.setForeground(Color.WHITE);
		lblContactNo.setFont(new Font("Calibri", Font.BOLD, 25));
		lblContactNo.setBounds(42, 693, 96, 19);
		panel.add(lblContactNo);
		
		textFieldcontact = new JTextField();
		textFieldcontact.setOpaque(false);
		textFieldcontact.setBackground(new Color(153, 102, 204));
		textFieldcontact.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldcontact.setCaretColor(Color.WHITE);
		textFieldcontact.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldcontact.setForeground(Color.WHITE);
		textFieldcontact.setBounds(166, 694, 220, 22);
		panel.add(textFieldcontact);
		textFieldcontact.setColumns(10);
		
		JLabel lblField = new JLabel("Field:");
		lblField.setForeground(Color.WHITE);
		lblField.setFont(new Font("Calibri", Font.BOLD, 25));
		lblField.setBounds(66, 782, 72, 22);
		panel.add(lblField);
		
		textFieldcomps = new JTextField();
		textFieldcomps.setOpaque(false);
		textFieldcomps.setCaretColor(new Color(255, 255, 255));
		textFieldcomps.setForeground(new Color(255, 255, 255));
		textFieldcomps.setBackground(new Color(153, 102, 204));
		textFieldcomps.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldcomps.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldcomps.setBounds(166, 784, 220, 22);
		panel.add(textFieldcomps);
		textFieldcomps.setColumns(10);
		
		JLabel lblParents = new JLabel("Parents No.:");
		lblParents.setForeground(new Color(255, 255, 255));
		lblParents.setFont(new Font("Calibri", Font.BOLD, 24));
		lblParents.setBounds(12, 876, 142, 22);
		panel.add(lblParents);
		
		textFieldparents = new JTextField();
		textFieldparents.setOpaque(false);
		textFieldparents.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		textFieldparents.setCaretColor(new Color(255, 255, 255));
		textFieldparents.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldparents.setBackground(new Color(153, 102, 204));
		textFieldparents.setForeground(new Color(255, 255, 255));
		textFieldparents.setBounds(166, 876, 220, 22);
		panel.add(textFieldparents);
		textFieldparents.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Studentinfo.class.getResource("/resources/14 - 2.png")));
		lblNewLabel.setBounds(0, 0, 540, 1013);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 204, 204));
		panel_1.setBounds(539, 0, 1375, 1027);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setFont(new Font("Calibri Light", Font.BOLD, 20));
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(0, 153, 1375, 861);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setOpaque(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
			}
		));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
				int row=table.getSelectedRow();
				String ID_=(table.getModel().getValueAt(row, 1)).toString();
				String query="select * from StudentsInfo where ID='"+ID_+"' ";
				PreparedStatement pst=conn.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				
				while(rs.next())
				{
					textFieldname.setText(rs.getString("Name"));		/*code for showing details into respective textfields on selection of row*/
					textFieldid.setText(rs.getString("ID"));
					String a,b;
					a="Male";b="Female";
					gender1=rs.getString("Gender");
					if(a.equals(gender1)) { 
						rdbtnMale.setSelected(true);
					}
					else if (b.equals(gender1)) {
						rdbtnFemale.setSelected(true);
					}
					else {
						rdbtnOther.setSelected(true);
					}
					textFieldmail.setText(rs.getString("Email"));
					textArea.setText(rs.getString("Address"));
					str=rs.getString("Birth");
					String[] splitStr=str.split(" ");
					date.setSelectedItem(splitStr[0]);
					month.setSelectedItem(splitStr[1]);
					year.setSelectedItem(splitStr[2]);
					textFieldcontact.setText(rs.getString("Contact"));
					textFieldcomps.setText(rs.getString("Field"));
					textFieldparents.setText((rs.getString("ParentsNo")));
					
				}
				pst.close();
				}catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}
		});
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setAutoCreateRowSorter(true);
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.DARK_GRAY);
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setRowHeight(40);
		table.setSelectionBackground(new Color(51, 51, 51));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setGridColor(new Color(51, 51, 51));
		table.setFont(new Font("Calibri", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Calibri",Font.BOLD,22));
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setOpaque(false);
		UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0,0,0,0));
		scrollPane.setViewportView(table);
		
		JButton refreshbutton = new JButton("\r\n");
		refreshbutton.setOpaque(false);
		refreshbutton.setToolTipText("Refresh");
		refreshbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		
		refreshbutton.setBackground(new Color(102, 51, 153));
		refreshbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		refreshbutton.setForeground(new Color(102, 51, 153));
		refreshbutton.setIcon(new ImageIcon(Studentinfo.class.getResource("/resources/icons8-Refresh-48.png")));
		refreshbutton.setBounds(1255, 52, 72, 37);
		panel_1.add(refreshbutton);
		
		JButton updatebutton = new JButton("");
		updatebutton.setOpaque(false);
		updatebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					data=textArea.getText();
					date1=date.getSelectedItem().toString();
					month1=month.getSelectedItem().toString();
					year1=year.getSelectedItem().toString();
					all=date1+" "+month1+" "+year1;
					String query="Update StudentsInfo set ID='"+textFieldid.getText()+"' ,name='"+textFieldname.getText()+"' ,email='"+textFieldmail.getText()+"' ,address='"+data+"' ,birth='"+all+"' ,contact='"+textFieldcontact.getText()+"' ,field='"+textFieldcomps.getText()+"' ,parentsno='"+textFieldparents.getText()+"' where ID='"+textFieldid.getText()+"' ";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data Updated");
					pst.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
				refreshTable();
				
			}
		});
		updatebutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		updatebutton.setToolTipText("Update");
		updatebutton.setIcon(new ImageIcon(Studentinfo.class.getResource("/resources/icons8-Installing Updates-48.png")));
		updatebutton.setBackground(new Color(102, 51, 153));
		updatebutton.setBounds(1127, 45, 97, 57);
		panel_1.add(updatebutton);
		
		JButton deletebutton = new JButton("");
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query=" delete from StudentsInfo where ID='"+textFieldid.getText()+"' ";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.execute();
					pst.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
				refreshTable();
				refreshTextfields();
			}
		});
		deletebutton.setOpaque(false);
		deletebutton.setIcon(new ImageIcon(Studentinfo.class.getResource("/resources/icons8-Trash-48.png")));
		deletebutton.setToolTipText("Delete");
		deletebutton.setBackground(new Color(102, 51, 153));
		deletebutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		deletebutton.setBounds(1003, 45, 97, 50);
		panel_1.add(deletebutton);
		
		JButton adminjumper = new JButton("");
		adminjumper.setBorder(new EmptyBorder(0, 0, 0, 0));
		adminjumper.setBackground(new Color(51, 51, 51));
		adminjumper.setOpaque(false);
		adminjumper.setToolTipText("Administrator");
		adminjumper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage.s1.setVisible(false);
				a2=new Administrator();
				a2.setVisible(true);
			}
		});
		adminjumper.setIcon(new ImageIcon(Studentinfo.class.getResource("/resources/icons8-Door Filled-50.png")));
		adminjumper.setBounds(868, 35, 97, 67);
		panel_1.add(adminjumper);
		
		JButton browser = new JButton("");
		browser.setOpaque(false);
		browser.setToolTipText("Visit Shah and Anchor Official Website");
		browser.setBorderPainted(false);
		browser.setBorder(new EmptyBorder(0, 0, 0, 0));
		browser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String URL="www.shahandanchor.com";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Can't reach");
				}
			}
		});
		browser.setBackground(new Color(51, 51, 51));
		browser.setIcon(new ImageIcon(Studentinfo.class.getResource("/resources/icons8-Internet Browser Filled-50.png")));
		browser.setBounds(734, 45, 97, 55);
		panel_1.add(browser);
		
		Search = new JTextField();
		Search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
				String selection=(String)search.getSelectedItem();
				String query="select * from StudentsInfo where "+selection+" = ? ";
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setString(1, Search.getText());
				ResultSet rs=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				String ch=(String)Search.getText();
				if(ch.equals(""))
					refreshTable();
				
				pst.close();
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		Search.setCaretColor(Color.WHITE);
		Search.setFont(new Font("Calibri Light", Font.PLAIN, 19));
		Search.setForeground(Color.WHITE);
		Search.setOpaque(false);
		Search.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		Search.setBackground(Color.BLACK);
		Search.setBounds(160, 66, 186, 32);
		panel_1.add(Search);
		Search.setColumns(10);
		
		search = new JComboBox();
		search.setBackground(Color.DARK_GRAY);
		search.setForeground(Color.WHITE);
		search.setFont(new Font("Calibri", Font.PLAIN, 18));
		search.setModel(new DefaultComboBoxModel(new String[] {"ID", "Name"}));
		search.setOpaque(false);
		search.setBorder(null);
		search.setBounds(354, 67, 72, 22);
		panel_1.add(search);
		
		JLabel lblsearch = new JLabel("");
		lblsearch.setIcon(new ImageIcon(Studentinfo.class.getResource("/resources/icons8-Google Web Search-45.png")));
		lblsearch.setBounds(96, 52, 52, 50);
		panel_1.add(lblsearch);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Studentinfo.class.getResource("/resources/14 - 2.png")));
		label.setBounds(0, 0, 1375, 151);
		panel_1.add(label);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), panel, addbutton, lblName, textFieldname, lblId, textFieldid, lblBirthDate, panel_1}));
		refreshTable();
	}
}
