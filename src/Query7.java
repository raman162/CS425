import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Query7 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection conn=null;
	private void refreshTable(){
		try{
			String query ="select SUM(ordered_amount) as ordered_products_amount from (select Products.prod_id, (prod_ordered * prod_price) as prod_ordered_amount from Products, Orders_Products, Orders where Products.prod_id = Orders_Products.prod_id and Orders_Products.order_id = Orders.order_id and Orders.address_id not in (select Addresses.address_id from Addresses where Addresses.country = 'United States') ) as order_product_amount(prod_id, ordered_amount)";
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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query7 frame = new Query7();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Query7() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTotalForAll = new JLabel("Total for all orders shipped to International Destination");
		lblTotalForAll.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalForAll.setBounds(21, 11, 548, 52);
		contentPane.add(lblTotalForAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 62, 843, 492);
		contentPane.add(scrollPane);
		
		table = new JTable();
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
		btnReturnToMenu.setBounds(21, 577, 112, 23);
		contentPane.add(btnReturnToMenu);
		refreshTable();
	}

}
