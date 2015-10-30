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
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Orders extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Orders frame = new Orders();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JTextField textFieldoid;
	private JTextField textFieldocid;
	private JTextField textFieldaid;
	private JTextField textFieldopay;
	private JTextField textFieldodmod;
	private JTextField textFieldodpur;
	private JTextField textFieldosid;
	private JTextField textFieldoscost;
	private JTable table;
	private JTextField textFieldSearch;
	private JComboBox comboBox;
	private JComboBox comboBoxSelector;
	private void refreshTable(){
		try{
			String query ="select * from Orders";
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
			String queries="Select * from Orders";
			PreparedStatement pst=conn.prepareStatement(queries);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				//System.out.println(rs.getString("cust_fname"));
				comboBox.addItem(rs.getString("order_id"));
				}
		}catch (Exception e3){
			JOptionPane.showMessageDialog(null, e3);
			e3.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Orders() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 972, 984);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOrders = new JLabel("Orders");
		lblOrders.setBounds(50, 41, 46, 14);
		contentPane.add(lblOrders);
		
		JLabel lblOrderId = new JLabel("Order Id");
		lblOrderId.setBounds(26, 160, 46, 14);
		contentPane.add(lblOrderId);
		
		JLabel lblCustomerId = new JLabel("Customer Id");
		lblCustomerId.setBounds(26, 202, 70, 14);
		contentPane.add(lblCustomerId);
		
		JLabel lblAddressId = new JLabel("Address id");
		lblAddressId.setBounds(26, 249, 70, 14);
		contentPane.add(lblAddressId);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method");
		lblPaymentMethod.setBounds(26, 303, 81, 14);
		contentPane.add(lblPaymentMethod);
		
		JLabel lblDateModified = new JLabel("Date modified");
		lblDateModified.setBounds(26, 349, 81, 14);
		contentPane.add(lblDateModified);
		
		JLabel lblDatePurchased = new JLabel("Date Purchased");
		lblDatePurchased.setBounds(26, 393, 81, 14);
		contentPane.add(lblDatePurchased);
		
		JLabel lblOrderStatusId = new JLabel("Order Status ID");
		lblOrderStatusId.setBounds(26, 432, 81, 14);
		contentPane.add(lblOrderStatusId);
		
		JLabel lblShippingCost = new JLabel("Shipping Cost");
		lblShippingCost.setBounds(26, 475, 125, 14);
		contentPane.add(lblShippingCost);
		
		textFieldoid = new JTextField();
		textFieldoid.setBounds(152, 157, 86, 20);
		contentPane.add(textFieldoid);
		textFieldoid.setColumns(10);
		
		textFieldocid = new JTextField();
		textFieldocid.setBounds(152, 199, 86, 20);
		contentPane.add(textFieldocid);
		textFieldocid.setColumns(10);
		
		textFieldaid = new JTextField();
		textFieldaid.setBounds(152, 246, 86, 20);
		contentPane.add(textFieldaid);
		textFieldaid.setColumns(10);
		
		textFieldopay = new JTextField();
		textFieldopay.setBounds(152, 300, 86, 20);
		contentPane.add(textFieldopay);
		textFieldopay.setColumns(10);
		
		textFieldodmod = new JTextField();
		textFieldodmod.setBounds(152, 346, 86, 20);
		contentPane.add(textFieldodmod);
		textFieldodmod.setColumns(10);
		
		textFieldodpur = new JTextField();
		textFieldodpur.setBounds(152, 390, 86, 20);
		contentPane.add(textFieldodpur);
		textFieldodpur.setColumns(10);
		
		textFieldosid = new JTextField();
		textFieldosid.setBounds(152, 429, 86, 20);
		contentPane.add(textFieldosid);
		textFieldosid.setColumns(10);
		
		textFieldoscost = new JTextField();
		textFieldoscost.setBounds(152, 472, 86, 20);
		contentPane.add(textFieldoscost);
		textFieldoscost.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="insert into orders (order_id, customer_id, address_id, payment_method, last_modified, date_purchased, order_status_id, shipping_cost) values (?,?,?,?,?,?,?,?)";
					
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textFieldoid.getText());
					pst.setString(2, textFieldocid.getText());
					pst.setString(3, textFieldaid.getText());
					pst.setString(4, textFieldopay.getText());
					pst.setString(5, textFieldodmod.getText());
					pst.setString(6, textFieldodpur.getText());
					pst.setString(7, textFieldosid.getText());
					pst.setString(8, textFieldoscost.getText());
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
		btnSave.setBounds(91, 550, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String query ="update Orders set order_id='"+textFieldoid.getText()+"',customer_id='"+textFieldocid.getText()+"', address_id = '"+textFieldaid.getText()+"', payment_method = '"+textFieldopay.getText()+"', last_modified = '"+textFieldodmod.getText()+"', date_purchased = '"+textFieldodpur.getText()+"', order_status_id = '"+textFieldosid.getText()+"', shipping_cost = '"+textFieldoscost.getText()+"'  where order_id='"+textFieldoid.getText()+"'";
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
		btnUpdate.setBounds(91, 610, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action=JOptionPane.showConfirmDialog(null, "Do you really want to delete? :(","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				
				try{
					String query ="delete from Orders where order_id ='"+textFieldoid.getText()+"'";
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
		btnDelete.setBounds(91, 680, 89, 23);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(332, 160, 593, 689);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					String query ="Select * from Orders where order_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,id);
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldoid.setText(rs.getString("order_id"));
						textFieldocid.setText(rs.getString("customer_id"));
						textFieldaid.setText(rs.getString("address_id"));
						textFieldopay.setText(rs.getString("payment_method"));
						textFieldodmod.setText(rs.getString("last_modified"));
						textFieldodpur.setText(rs.getString("date_purchased"));
						textFieldosid.setText(rs.getString("order_status_id"));
						textFieldoscost.setText(rs.getString("shipping_cost"));
						
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		scrollPane.setViewportView(table);
		
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
		btnReturnToMenu.setBounds(72, 796, 166, 23);
		contentPane.add(btnReturnToMenu);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="Select * from Orders where order_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,(String)comboBox.getSelectedItem());
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldoid.setText(rs.getString("order_id"));
						textFieldocid.setText(rs.getString("customer_id"));
						textFieldaid.setText(rs.getString("address_id"));
						textFieldopay.setText(rs.getString("payment_method"));
						textFieldodmod.setText(rs.getString("last_modified"));
						textFieldodpur.setText(rs.getString("date_purchased"));
						textFieldosid.setText(rs.getString("order_status_id"));
						textFieldoscost.setText(rs.getString("shipping_cost"));
						
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
			}
			}
		});
		comboBox.setBounds(38, 83, 200, 50);
		contentPane.add(comboBox);
		
		comboBoxSelector = new JComboBox();
		comboBoxSelector.setModel(new DefaultComboBoxModel(new String[] {"order_id", "customer_id", "address_id", "payment_method", "last_modified", "date_purchased", "order_status_id", "shipping_cost"}));
		comboBoxSelector.setBounds(376, 83, 200, 50);
		contentPane.add(comboBoxSelector);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					String selection=(String)comboBoxSelector.getSelectedItem();
					String query ="Select * from Orders where ("+selection+" like '%"+textFieldSearch.getText()+"%') OR "+selection+" = '"+textFieldSearch.getText()+"'";
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
		textFieldSearch.setBounds(642, 98, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(682, 66, 46, 14);
		contentPane.add(lblSearch);
		
		
		refreshTable();
		fillComboBox();
		
		
	}
}
