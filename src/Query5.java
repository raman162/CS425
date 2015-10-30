import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Query5 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	Connection conn=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query5 frame = new Query5();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void refreshTable1(){
		try{
			String query ="select prod_id from Products where prod_id in ( select Products_to_Categories.prod_id from Products_to_Categories, Categories where Products_to_Categories.cat_id = Categories.cat_id and Categories.cat_name like '%Tote Bags%')";
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
			String query ="select * from Customers where customer_id not in (select customer_id from Orders)";
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
	private void refreshTable3(){
		try{
			String query ="select Customers.customer_id, Customers.cust_fname from Customers where Customers.customer_id not in ( select customer_id from Addresses where country = 'United States')";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(rs));
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
	public Query5() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1554, 885);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProductsThatAre = new JLabel("Products that are Tote Bags");
		lblProductsThatAre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProductsThatAre.setBounds(10, 11, 298, 47);
		contentPane.add(lblProductsThatAre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 467, 736);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblCustomersWithoutOrders = new JLabel("Customers Without Orders");
		lblCustomersWithoutOrders.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCustomersWithoutOrders.setBounds(516, 11, 317, 47);
		contentPane.add(lblCustomersWithoutOrders);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(501, 64, 442, 735);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblCustomersNotIn = new JLabel("Customers Not in the United States");
		lblCustomersNotIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCustomersNotIn.setBounds(977, 11, 351, 47);
		contentPane.add(lblCustomersNotIn);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(971, 64, 507, 730);
		contentPane.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
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
		btnReturnToMenu.setBounds(10, 812, 155, 23);
		contentPane.add(btnReturnToMenu);
		refreshTable1();
		refreshTable2();
		refreshTable3();
	}
}
