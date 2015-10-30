import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Query6 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	Connection conn=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query6 frame = new Query6();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void refreshTable1(){
		try{
			String query ="select Customers.cust_fname,Addresses.street_name, Addresses.city, Addresses.country from Customers, Addresses where Customers.customer_id = Addresses.customer_id and Customers.customer_id in (select Orders.customer_id from Orders, Order_status where Orders.order_status_id = Order_status.order_status_id and Order_status.order_status_name like 'pending')";
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
	private void refreshTable2(){
		try{
			String query ="select COUNT(shipping_cost) as total_shipping_cost from Orders where address_id in (select Addresses.address_id from Addresses where Addresses.country = 'United Kingdom' or Addresses.country = 'United States')";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		}catch (Exception e2){
	JOptionPane.showMessageDialog(null, e2);
	e2.printStackTrace();
}
	}
	/**
	 * Create the frame.
	 */
	public Query6() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1201, 938);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustomersWithPending = new JLabel("Customers With Pending Status");
		lblCustomersWithPending.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCustomersWithPending.setBounds(25, 21, 323, 56);
		contentPane.add(lblCustomersWithPending);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 83, 554, 771);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblTotalShippingCost = new JLabel("Total Shipping Cost for orders shipped to US or UK");
		lblTotalShippingCost.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalShippingCost.setBounds(627, 24, 451, 50);
		contentPane.add(lblTotalShippingCost);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(628, 80, 531, 775);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnReturnToMenu = new JButton("Return to Menu");
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
		btnReturnToMenu.setBounds(10, 865, 120, 23);
		contentPane.add(btnReturnToMenu);
		refreshTable1();
		refreshTable2();
	}

}
