import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Query2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private void refreshTable(){
		try{
			String query ="select * from Products where prod_model is null";
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
					Query2 frame = new Query2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	/**
	 * Create the frame.
	 */
	public Query2() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1103, 687);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 85, 1002, 448);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblProductsWithPrice = new JLabel("Products with price between");
		lblProductsWithPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProductsWithPrice.setBounds(41, 23, 263, 36);
		contentPane.add(lblProductsWithPrice);
		
		textField = new JTextField();
		textField.setBounds(337, 35, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAnd = new JLabel("and");
		lblAnd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnd.setBounds(459, 31, 49, 21);
		contentPane.add(lblAnd);
		
		textField_1 = new JTextField();
		textField_1.setBounds(553, 35, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
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
		btnReturnToMenu.setBounds(41, 589, 126, 23);
		contentPane.add(btnReturnToMenu);
		
		JButton btnLoadTable = new JButton("Load Table");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				String query="select * from Products where Products.prod_price between '"+textField.getText()+"' and '"+textField_1.getText()+"' ";
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
		btnLoadTable.setBounds(955, 589, 89, 23);
		contentPane.add(btnLoadTable);
		
		
		
	}

}
