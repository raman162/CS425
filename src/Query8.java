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


public class Query8 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection conn=null;
	/**
	 * Launch the application.
	 */
	private void refreshTable(){
		try{
			String query ="select prod_id, prod_model from Products where prod_id in (select prod_id from Orders_Products Where order_id in (select order_id from Orders where address_id in (select address_id from Addresses Where country<>'United States')))";
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query8 frame = new Query8();
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
	public Query8() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1221, 923);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProductsThatHave = new JLabel("Products that have been shipped outside the United States");
		lblProductsThatHave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProductsThatHave.setBounds(24, 11, 1004, 50);
		contentPane.add(lblProductsThatHave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 82, 1149, 720);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnReturnToMenu = new JButton("Return to menu");
		btnReturnToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		btnReturnToMenu.setBounds(24, 830, 117, 23);
		contentPane.add(btnReturnToMenu);
		refreshTable();
	}

}
