import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
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


public class ActualProd extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldpid;
	private JTextField textFieldpquan;
	private JLabel lblProductId;
	private JScrollPane scrollPane;
	private JLabel lblProductQuantity;
	private JLabel lblProdmodel;
	private JLabel lblPrice;
	private JTextField textFieldpmodel;
	private JTextField textFieldpprice;
	private JTextField textFieldpdadd;
	private JTextField textFieldpdmod;
	private JTextField textFieldpweight;
	private JTextField textFieldpstatus;
	private JTextField textFieldptclass;
	private JTextField textFieldpmid;
	private JTextField textFieldporder;
	private JComboBox comboBoxSelector;
	private JComboBox comboBox;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActualProd frame = new ActualProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JButton btnReturnToMenu;
	private JLabel lblProducts;
	private JLabel lblSearch;
	
	private void refreshTable(){
		try{
			String query ="select * from Products";
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
			String queries="Select * from Products";
			PreparedStatement pst=conn.prepareStatement(queries);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				//System.out.println(rs.getString("cust_fname"));
				comboBox.addItem(rs.getString("prod_id"));
				}
		}catch (Exception e3){
			JOptionPane.showMessageDialog(null, e3);
			e3.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public ActualProd() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 886);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		
		scrollPane.setBounds(278, 113, 1029, 657);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					String query ="Select * from Products where prod_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,id);
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldpid.setText(rs.getString("prod_id"));
						textFieldpquan.setText(rs.getString("prod_quan"));
						textFieldpmodel.setText(rs.getString("prod_model"));
						textFieldpprice.setText(rs.getString("prod_price"));
						textFieldpdadd.setText(rs.getString("prod_date_added"));
						textFieldpdmod.setText(rs.getString("prod_date_mod"));
						textFieldpweight.setText(rs.getString("prod_weight"));
						textFieldpstatus.setText(rs.getString("prod_status"));
						textFieldptclass.setText(rs.getString("prod_tax_class_id"));
						textFieldpmid.setText(rs.getString("manu_id"));
						textFieldporder.setText(rs.getString("prod_ordered"));

					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		scrollPane.setViewportView(table);
		
		textFieldpid = new JTextField();
		textFieldpid.setBounds(131, 80, 86, 20);
		contentPane.add(textFieldpid);
		textFieldpid.setColumns(10);
		
		textFieldpquan = new JTextField();
		textFieldpquan.setText("");
		textFieldpquan.setBounds(131, 122, 86, 20);
		contentPane.add(textFieldpquan);
		textFieldpquan.setColumns(10);
		
		lblProductId = new JLabel("Product Id");
		lblProductId.setBounds(30, 80, 66, 20);
		contentPane.add(lblProductId);
		
		lblProductQuantity = new JLabel("Product Quantity");
		lblProductQuantity.setBounds(30, 125, 91, 14);
		contentPane.add(lblProductQuantity);
		
		lblProdmodel = new JLabel("Product Model");
		lblProdmodel.setBounds(30, 166, 91, 14);
		contentPane.add(lblProdmodel);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(30, 205, 46, 14);
		contentPane.add(lblPrice);
		
		JLabel lblNewLabel = new JLabel("Date Added");
		lblNewLabel.setBounds(30, 247, 91, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDateModified = new JLabel("Date Modified");
		lblDateModified.setBounds(30, 285, 91, 14);
		contentPane.add(lblDateModified);
		
		JLabel lblProductWeight = new JLabel("Product Weight");
		lblProductWeight.setBounds(30, 323, 91, 14);
		contentPane.add(lblProductWeight);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(30, 357, 46, 14);
		contentPane.add(lblStatus);
		
		JLabel lblTaxClass = new JLabel("Tax Class");
		lblTaxClass.setBounds(30, 397, 46, 14);
		contentPane.add(lblTaxClass);
		
		JLabel lblManufacturingId = new JLabel("Manufacturing Id");
		lblManufacturingId.setBounds(30, 442, 91, 14);
		contentPane.add(lblManufacturingId);
		
		JLabel lblAmountOrdered = new JLabel("Amount Ordered");
		lblAmountOrdered.setBounds(30, 478, 91, 14);
		contentPane.add(lblAmountOrdered);
		
		textFieldpmodel = new JTextField();
		textFieldpmodel.setBounds(131, 163, 86, 20);
		contentPane.add(textFieldpmodel);
		textFieldpmodel.setColumns(10);
		
		textFieldpprice = new JTextField();
		textFieldpprice.setBounds(131, 202, 86, 20);
		contentPane.add(textFieldpprice);
		textFieldpprice.setColumns(10);
		
		textFieldpdadd = new JTextField();
		textFieldpdadd.setBounds(131, 244, 86, 20);
		contentPane.add(textFieldpdadd);
		textFieldpdadd.setColumns(10);
		
		textFieldpdmod = new JTextField();
		textFieldpdmod.setBounds(131, 282, 86, 20);
		contentPane.add(textFieldpdmod);
		textFieldpdmod.setColumns(10);
		
		textFieldpweight = new JTextField();
		textFieldpweight.setBounds(131, 320, 86, 20);
		contentPane.add(textFieldpweight);
		textFieldpweight.setColumns(10);
		
		textFieldpstatus = new JTextField();
		textFieldpstatus.setBounds(131, 354, 86, 20);
		contentPane.add(textFieldpstatus);
		textFieldpstatus.setColumns(10);
		
		textFieldptclass = new JTextField();
		textFieldptclass.setBounds(131, 394, 86, 20);
		contentPane.add(textFieldptclass);
		textFieldptclass.setColumns(10);
		
		textFieldpmid = new JTextField();
		textFieldpmid.setBounds(131, 439, 86, 20);
		contentPane.add(textFieldpmid);
		textFieldpmid.setColumns(10);
		
		textFieldporder = new JTextField();
		textFieldporder.setBounds(131, 475, 86, 20);
		contentPane.add(textFieldporder);
		textFieldporder.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="insert into Products (prod_id, prod_quan, prod_model, prod_price, prod_date_added, prod_date_mod, prod_weight, prod_status, prod_tax_class_id, manu_id, prod_ordered) values (?,?,?,?,?,?,?,?,?,?,?)";
					
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textFieldpid.getText());
					pst.setString(2, textFieldpquan.getText());
					pst.setString(3, textFieldpmodel.getText());
					pst.setString(4, textFieldpprice.getText());
					pst.setString(5, textFieldpdadd.getText());
					pst.setString(6, textFieldpdmod.getText());
					pst.setString(7, textFieldpweight.getText());
					pst.setString(8, textFieldpstatus.getText());
					pst.setString(9, textFieldptclass.getText());
					pst.setString(10, textFieldpmid.getText());
					pst.setString(11, textFieldporder.getText());
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
		btnSave.setBounds(64, 530, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="update Products set prod_id='"+textFieldpid.getText()+"',prod_quan='"+textFieldpquan.getText()+"', prod_model = '"+textFieldpmodel.getText()+"', prod_price = '"+textFieldpprice.getText()+"', prod_date_added = '"+textFieldpdadd.getText()+"', prod_date_mod = '"+textFieldpdmod.getText()+"', prod_weight ='"+textFieldpweight.getText()+"', prod_status='"+textFieldpstatus.getText()+"', prod_tax_class_id = '"+textFieldptclass.getText()+"', manu_id ='"+textFieldpmid.getText()+"', prod_ordered='"+textFieldporder.getText()+"'  where prod_id='"+textFieldpid.getText()+"'";
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
		btnUpdate.setBounds(64, 566, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action=JOptionPane.showConfirmDialog(null, "Do you really want to delete? :(","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				
				try{
					String query ="delete from Products where prod_id ='"+textFieldpid.getText()+"'";
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
		btnDelete.setBounds(64, 600, 89, 23);
		contentPane.add(btnDelete);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					String query ="Select * from Products where prod_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,(String)comboBox.getSelectedItem());
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldpid.setText(rs.getString("prod_id"));
						textFieldpquan.setText(rs.getString("prod_quan"));
						textFieldpmodel.setText(rs.getString("prod_model"));
						textFieldpprice.setText(rs.getString("prod_price"));
						textFieldpdadd.setText(rs.getString("prod_date_added"));
						textFieldpdmod.setText(rs.getString("prod_date_mod"));
						textFieldpweight.setText(rs.getString("prod_weight"));
						textFieldpstatus.setText(rs.getString("prod_status"));
						textFieldptclass.setText(rs.getString("prod_tax_class_id"));
						textFieldpmid.setText(rs.getString("manu_id"));
						textFieldporder.setText(rs.getString("prod_ordered"));

					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		comboBox.setBounds(10, 34, 200, 23);
		contentPane.add(comboBox);
		
		comboBoxSelector = new JComboBox();
		comboBoxSelector.setModel(new DefaultComboBoxModel(new String[] {"prod_id", "prod_quan", "prod_model", "prod_price", "prod_date_added", "prod_date_mod", "prod_weight", "prod_status", "prod_ordered", "prod_tax_class_id", "manu_id", "prod_ordered"}));
		comboBoxSelector.setBounds(278, 34, 192, 23);
		contentPane.add(comboBoxSelector);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try{
					String selection=(String)comboBoxSelector.getSelectedItem();
					String query ="Select * from Products where "+selection+" like '%"+textField.getText()+"%' OR "+selection+" = '"+textField.getText()+"'";
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
		textField.setBounds(479, 35, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnReturnToMenu = new JButton("Return to menu");
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
		btnReturnToMenu.setBounds(30, 667, 158, 23);
		contentPane.add(btnReturnToMenu);
		
		lblProducts = new JLabel("Products");
		lblProducts.setBounds(30, 9, 46, 14);
		contentPane.add(lblProducts);
		
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(519, 10, 46, 14);
		contentPane.add(lblSearch);
		refreshTable();
		fillComboBox();
	}
}
