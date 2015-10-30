import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Addresses extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldacname;
	private JTextField textFieldaid;
	private JTextField textFieldatype;
	private JTextField textFieldacid;
	private JTextField textFieldasname;
	private JTextField textFieldacity;
	private JTextField textFieldapost;
	private JTextField textFieldastate;
	private JTextField textFieldacountry;
	private JTable table;
	private JTextField textFieldSearch;
	private JComboBox comboBoxSelector ;
	private JComboBox comboBox ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addresses frame = new Addresses();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private void refreshTable(){
		try{
			String query ="select * from Addresses";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		}catch (Exception e2){
	JOptionPane.showMessageDialog(null, e2);
	e2.printStackTrace();
}
	}
	private void fillComboBox(){
		try{
			String queries="Select * from Addresses";
			PreparedStatement pst=conn.prepareStatement(queries);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				//System.out.println(rs.getString("cust_fname"));
				comboBox.addItem(rs.getString("address_id"));
				}
		}catch (Exception e3){
			JOptionPane.showMessageDialog(null, e3);
			e3.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Addresses() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 934);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddresses = new JLabel("Addresses");
		lblAddresses.setBounds(35, 22, 66, 14);
		contentPane.add(lblAddresses);
		
		JLabel lblAddressId = new JLabel("Address Id");
		lblAddressId.setBounds(35, 148, 66, 14);
		contentPane.add(lblAddressId);
		
		JLabel lblAddressType = new JLabel("Address Type");
		lblAddressType.setBounds(35, 196, 79, 14);
		contentPane.add(lblAddressType);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(35, 251, 79, 14);
		contentPane.add(lblCustomerId);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(35, 295, 76, 14);
		contentPane.add(lblCustomerName);
		
		JLabel lblStreetName = new JLabel("Street Name");
		lblStreetName.setBounds(35, 349, 66, 14);
		contentPane.add(lblStreetName);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(35, 407, 46, 14);
		contentPane.add(lblCity);
		
		JLabel lblPostcode = new JLabel("Postcode");
		lblPostcode.setBounds(35, 451, 46, 14);
		contentPane.add(lblPostcode);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(35, 491, 46, 14);
		contentPane.add(lblState);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(35, 534, 46, 14);
		contentPane.add(lblCountry);
		
		textFieldacname = new JTextField();
		textFieldacname.setBounds(179, 292, 86, 20);
		contentPane.add(textFieldacname);
		textFieldacname.setColumns(10);
		
		textFieldaid = new JTextField();
		textFieldaid.setBounds(179, 145, 86, 20);
		contentPane.add(textFieldaid);
		textFieldaid.setColumns(10);
		
		textFieldatype = new JTextField();
		textFieldatype.setBounds(179, 193, 86, 20);
		contentPane.add(textFieldatype);
		textFieldatype.setColumns(10);
		
		textFieldacid = new JTextField();
		textFieldacid.setBounds(179, 248, 86, 20);
		contentPane.add(textFieldacid);
		textFieldacid.setColumns(10);
		
		textFieldasname = new JTextField();
		textFieldasname.setBounds(179, 346, 86, 20);
		contentPane.add(textFieldasname);
		textFieldasname.setColumns(10);
		
		textFieldacity = new JTextField();
		textFieldacity.setBounds(179, 404, 86, 20);
		contentPane.add(textFieldacity);
		textFieldacity.setColumns(10);
		
		textFieldapost = new JTextField();
		textFieldapost.setBounds(179, 448, 86, 20);
		contentPane.add(textFieldapost);
		textFieldapost.setColumns(10);
		
		textFieldastate = new JTextField();
		textFieldastate.setBounds(179, 488, 86, 20);
		contentPane.add(textFieldastate);
		textFieldastate.setColumns(10);
		
		textFieldacountry = new JTextField();
		textFieldacountry.setBounds(179, 531, 86, 20);
		contentPane.add(textFieldacountry);
		textFieldacountry.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="insert into Addresses (address_id, address_type, customer_id, cust_name, street_name, city, postcode, cust_state, country) values (?,?,?,?,?,?,?,?,?)";
					
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textFieldaid.getText());
					pst.setString(2, textFieldatype.getText());
					pst.setString(3, textFieldacid.getText());
					pst.setString(4, textFieldacname.getText());
					pst.setString(5, textFieldasname.getText());
					pst.setString(6, textFieldacity.getText());
					pst.setString(7, textFieldapost.getText());
					pst.setString(8, textFieldastate.getText());
					pst.setString(9, textFieldacountry.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "DataSaved");
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
				refreshTable();
			}
		});
		btnSave.setBounds(105, 607, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="update Addresses set address_id='"+textFieldaid.getText()+"',address_type='"+textFieldatype.getText()+"', customer_id = '"+textFieldacid.getText()+"', cust_name = '"+textFieldacname.getText()+"', street_name = '"+textFieldasname.getText()+"', city = '"+textFieldacity.getText()+"', postcode = '"+textFieldapost.getText()+"', cust_state = '"+textFieldastate.getText()+"', country='"+textFieldacountry.getText()+"'  where address_id='"+textFieldaid.getText()+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					
					pst.execute();
					JOptionPane.showMessageDialog(null, "DataUpdated");
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
				
		}
				refreshTable();
			}
		});
		btnUpdate.setBounds(105, 657, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action=JOptionPane.showConfirmDialog(null, "Do you really want to delete? :(","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				
				try{
					String query ="delete from Addresses where address_id ='"+textFieldaid.getText()+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted");
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
				refreshTable();
			
		
				}
			}
		});
		btnDelete.setBounds(105, 714, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnReturnToMenu = new JButton("Return to menu");
		btnReturnToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					dispose();
					Login menu=new Login();
					menu.main(null);
				}catch (Exception e3){
					JOptionPane.showMessageDialog(null,  e3);
					e3.printStackTrace();
				}
			}
		});
		btnReturnToMenu.setBounds(93, 820, 135, 23);
		contentPane.add(btnReturnToMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 148, 494, 671);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					String query ="Select * from Addresses where address_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,id);
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldaid.setText(rs.getString("address_id"));
						textFieldatype.setText(rs.getString("address_type"));
						textFieldacid.setText(rs.getString("customer_id"));
						textFieldacname.setText(rs.getString("cust_name"));
						textFieldasname.setText(rs.getString("street_name"));
						textFieldacity.setText(rs.getString("city"));
						textFieldapost.setText(rs.getString("postcode"));
						textFieldastate.setText(rs.getString("cust_state"));
						textFieldacountry.setText(rs.getString("country"));
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				String query ="Select * from Addresses where address_id=?";
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setString(1,(String)comboBox.getSelectedItem());
				ResultSet rs =pst.executeQuery();
				while(rs.next()){
					textFieldaid.setText(rs.getString("address_id"));
					textFieldatype.setText(rs.getString("address_type"));
					textFieldacid.setText(rs.getString("customer_id"));
					textFieldacname.setText(rs.getString("cust_name"));
					textFieldasname.setText(rs.getString("street_name"));
					textFieldacity.setText(rs.getString("city"));
					textFieldapost.setText(rs.getString("postcode"));
					textFieldastate.setText(rs.getString("cust_state"));
					textFieldacountry.setText(rs.getString("country"));
				}
			
				pst.close();
				
				
			}catch (Exception e2){
				JOptionPane.showMessageDialog(null, e2);
				e2.printStackTrace();
	}
			}
		});
		comboBox.setBounds(46, 66, 200, 50);
		contentPane.add(comboBox);
		
		comboBoxSelector = new JComboBox();
		comboBoxSelector.setModel(new DefaultComboBoxModel(new String[] {"address_id", "address_type", "customer_id", "cust_name", "street_name", "city", "postcode", "cust_state", "country"}));
		comboBoxSelector.setBounds(390, 66, 200, 50);
		contentPane.add(comboBoxSelector);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					String selection=(String)comboBoxSelector.getSelectedItem();
					String query ="Select * from Addresses where ("+selection+" like '%"+textFieldSearch.getText()+"%') OR "+selection+" = '"+textFieldSearch.getText()+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					//pst.setString(1,textFieldSearch.getText());
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		textFieldSearch.setBounds(687, 81, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search\r\n");
		lblSearch.setBounds(730, 56, 46, 14);
		contentPane.add(lblSearch);
		
		refreshTable();
		fillComboBox();
	}

}
