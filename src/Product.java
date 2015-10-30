import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Product extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox;
	private JComboBox comboBoxSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JTextField textFieldcid;
	private JTextField textFieldcgen;
	private JTextField textFieldname;
	private JTextField textFielddob;
	private JTextField textFieldnews;
	private JTextField textFieldSearch;
	
	private void refreshTable(){
		try{
			String query ="select * from Customers";
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
			String queries="Select * from Customers";
			PreparedStatement pst=conn.prepareStatement(queries);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				//System.out.println(rs.getString("cust_fname"));
				comboBox.addItem(rs.getString("cust_fname"));
				}
		}catch (Exception e3){
			JOptionPane.showMessageDialog(null, e3);
			e3.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Product() {
		conn=sqlscon.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSecondForm = new JLabel("Customers");
		lblSecondForm.setBounds(61, 25, 113, 71);
		contentPane.add(lblSecondForm);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(305, 136, 452, 402);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		//puts data within the TextFields when selected from the table
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					String query ="Select * from Customers where customer_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,id);
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldcid.setText(rs.getString("customer_id"));
						textFieldcgen.setText(rs.getString("cust_gen"));
						textFieldname.setText(rs.getString("cust_fname"));
						textFielddob.setText(rs.getString("cust_dob"));
						textFieldnews.setText(rs.getString("cust_newsletter"));
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
				
			}
		});
		scrollPane.setViewportView(table);
		
		textFieldcid = new JTextField();
		textFieldcid.setBounds(94, 156, 86, 20);
		contentPane.add(textFieldcid);
		textFieldcid.setColumns(10);
		
		textFieldcgen = new JTextField();
		textFieldcgen.setText("");
		textFieldcgen.setBounds(94, 197, 86, 20);
		contentPane.add(textFieldcgen);
		textFieldcgen.setColumns(10);
		
		JLabel lblCustomerId = new JLabel("customer id");
		lblCustomerId.setBounds(23, 159, 61, 14);
		contentPane.add(lblCustomerId);
		
		JLabel lblGender = new JLabel("gender");
		lblGender.setBounds(23, 200, 46, 14);
		contentPane.add(lblGender);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(23, 243, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(23, 290, 46, 14);
		contentPane.add(lblDob);
		
		JLabel lblNewsletter = new JLabel("Newsletter");
		lblNewsletter.setBounds(23, 328, 46, 14);
		contentPane.add(lblNewsletter);
		
		textFieldname = new JTextField();
		textFieldname.setBounds(94, 240, 86, 20);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);
		
		textFielddob = new JTextField();
		textFielddob.setBounds(94, 287, 86, 20);
		contentPane.add(textFielddob);
		textFielddob.setColumns(10);
		
		textFieldnews = new JTextField();
		textFieldnews.setBounds(94, 325, 86, 20);
		contentPane.add(textFieldnews);
		textFieldnews.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query ="insert into Customers (customer_id, cust_gen, cust_fname, cust_dob, cust_newsletter) values (?,?,?,?,?)";
					
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textFieldcid.getText());
					pst.setString(2, textFieldcgen.getText());
					pst.setString(3, textFieldname.getText());
					pst.setString(4, textFielddob.getText());
					pst.setString(5, textFieldnews.getText());
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
		btnSave.setBounds(61, 399, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query ="update Customers set customer_id='"+textFieldcid.getText()+"',cust_gen='"+textFieldcgen.getText()+"', cust_fname = '"+textFieldname.getText()+"', cust_dob = '"+textFielddob.getText()+"', cust_newsletter = '"+textFieldnews.getText()+"' where customer_id='"+textFieldcid.getText()+"'";
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
		btnUpdate.setBounds(61, 432, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action=JOptionPane.showConfirmDialog(null, "Do you really want to delete? :(","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				
				try{
					String query ="delete from Customers where customer_id ='"+textFieldcid.getText()+"'";
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
		btnDelete.setBounds(61, 466, 89, 23);
		contentPane.add(btnDelete);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="Select * from Customers where cust_fname=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,(String)comboBox.getSelectedItem());
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldcid.setText(rs.getString("customer_id"));
						textFieldcgen.setText(rs.getString("cust_gen"));
						textFieldname.setText(rs.getString("cust_fname"));
						textFielddob.setText(rs.getString("cust_dob"));
						textFieldnews.setText(rs.getString("cust_newsletter"));
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		comboBox.setBounds(23, 85, 157, 23);
		contentPane.add(comboBox);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try{
					String selection=(String)comboBoxSelect.getSelectedItem();
					String query ="Select * from Customers where "+selection+" like '%"+textFieldSearch.getText()+"%' OR "+selection+" = '"+textFieldSearch.getText()+"'";
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
		}
			
			
		);
		textFieldSearch.setBounds(388, 49, 217, 47);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		comboBoxSelect = new JComboBox();
		comboBoxSelect.setModel(new DefaultComboBoxModel(new String[] {"customer_id", "cust_gen", "cust_fname", "cust_dob"}));
		comboBoxSelect.setBounds(230, 50, 136, 46);
		contentPane.add(comboBoxSelect);
		
		//button to return to menu
		JButton btnReturnToMenu = new JButton("Return to Menu");
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
		
		
		btnReturnToMenu.setBounds(61, 566, 157, 23);
		contentPane.add(btnReturnToMenu);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(559, 25, 46, 14);
		contentPane.add(lblSearch);
		refreshTable();
		fillComboBox();
	}
}
