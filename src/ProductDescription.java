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
import javax.swing.JComboBox;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ProductDescription extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldpid;
	private JTextField textFieldpname;
	private JTextField textFieldpviews;
	private JTable table;
	private JTextField textFieldSearch;
	Connection conn=null;
	private JComboBox comboBoxSelector;
	private JComboBox comboBox;
	private JScrollPane scrollPane;
	private JLabel lblSearch;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductDescription frame = new ProductDescription();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void refreshTable(){
		try{
			String query ="select * from Prod_Description";
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
			String queries="Select * from Prod_Description";
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
	public ProductDescription() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1647, 1265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Prodcuts Description");
		lblNewLabel.setBounds(41, 23, 137, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product ID");
		lblNewLabel_1.setBounds(41, 183, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(41, 236, 82, 14);
		contentPane.add(lblProductName);
		
		JLabel lblTotalViews = new JLabel("Total Views");
		lblTotalViews.setBounds(41, 293, 92, 14);
		contentPane.add(lblTotalViews);
		
		textFieldpid = new JTextField();
		textFieldpid.setBounds(183, 180, 86, 20);
		contentPane.add(textFieldpid);
		textFieldpid.setColumns(10);
		
		textFieldpname = new JTextField();
		textFieldpname.setBounds(183, 233, 86, 20);
		contentPane.add(textFieldpname);
		textFieldpname.setColumns(10);
		
		textFieldpviews = new JTextField();
		textFieldpviews.setBounds(183, 290, 86, 20);
		contentPane.add(textFieldpviews);
		textFieldpviews.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="insert into Prod_Description (prod_id, prod_name, prod_viewed) values (?,?,?)";
					
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textFieldpid.getText());
					pst.setString(2, textFieldpname.getText());
					pst.setString(3, textFieldpviews.getText());
					
					
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
		btnSave.setBounds(89, 361, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="update Prod_Description set prod_id='"+textFieldpid.getText()+"',prod_name='"+textFieldpname.getText()+"', prod_viewed = '"+textFieldpviews.getText()+"' where prod_id='"+textFieldpid.getText()+"'";
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
		btnUpdate.setBounds(89, 407, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action=JOptionPane.showConfirmDialog(null, "Do you really want to delete? :(","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				
				try{
					String query ="delete from Prod_Description where prod_id ='"+textFieldpid.getText()+"'";
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
		btnDelete.setBounds(89, 456, 89, 23);
		contentPane.add(btnDelete);
		
		scrollPane = new JScrollPane();
		
		scrollPane.setBounds(379, 183, 1089, 898);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					String query ="Select * from Prod_Description where prod_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,id);
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldpid.setText(rs.getString("prod_id"));
						textFieldpname.setText(rs.getString("prod_name"));
						textFieldpviews.setText(rs.getString("prod_viewed"));
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
		btnReturnToMenu.setBounds(89, 985, 152, 23);
		contentPane.add(btnReturnToMenu);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="Select * from Prod_Description where prod_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,(String)comboBox.getSelectedItem());
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldpid.setText(rs.getString("prod_id"));
						textFieldpname.setText(rs.getString("prod_name"));
						textFieldpviews.setText(rs.getString("prod_viewed"));
						
						
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		comboBox.setBounds(41, 95, 200, 50);
		contentPane.add(comboBox);
		
		comboBoxSelector = new JComboBox();
		comboBoxSelector.setModel(new DefaultComboBoxModel(new String[] {"prod_id", "prod_name", "prod_viewed"}));
		comboBoxSelector.setBounds(788, 95, 200, 50);
		contentPane.add(comboBoxSelector);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					String selection=(String)comboBoxSelector.getSelectedItem();
					String query ="Select * from Prod_Description where "+selection+" like '%"+textFieldSearch.getText()+"%' OR "+selection+" = '"+textFieldSearch.getText()+"'";
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
		textFieldSearch.setBounds(1116, 110, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(1156, 82, 46, 14);
		contentPane.add(lblSearch);
		refreshTable();
		fillComboBox();
	}

}
