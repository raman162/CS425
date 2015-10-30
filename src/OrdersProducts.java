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

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class OrdersProducts extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldopquan;
	private JTextField textFieldopid;
	private JTextField textFieldoid;
	private JTextField textFieldpid;
	private JTable table;
	private JTextField textFieldSearch;
	private JComboBox comboBoxSelector;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdersProducts frame = new OrdersProducts();
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
			String query ="select * from Orders_Products";
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
			String queries="Select * from Orders_Products";
			PreparedStatement pst=conn.prepareStatement(queries);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				//System.out.println(rs.getString("cust_fname"));
				comboBox.addItem(rs.getString("orders_prod_id"));
				}
		}catch (Exception e3){
			JOptionPane.showMessageDialog(null, e3);
			e3.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public OrdersProducts() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1384, 1154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOrdersToProducts = new JLabel("Orders to Products");
		lblOrdersToProducts.setBounds(26, 32, 125, 14);
		contentPane.add(lblOrdersToProducts);
		
		JLabel lblOrdersproductsId = new JLabel("Orders_Products ID");
		lblOrdersproductsId.setBounds(39, 197, 95, 14);
		contentPane.add(lblOrdersproductsId);
		
		JLabel lblOrderid = new JLabel("Order iD");
		lblOrderid.setBounds(39, 242, 46, 14);
		contentPane.add(lblOrderid);
		
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(39, 287, 83, 14);
		contentPane.add(lblProductId);
		
		JLabel lblProductQuantity = new JLabel("Product Quantity");
		lblProductQuantity.setBounds(39, 336, 83, 14);
		contentPane.add(lblProductQuantity);
		
		textFieldopquan = new JTextField();
		textFieldopquan.setBounds(234, 333, 86, 20);
		contentPane.add(textFieldopquan);
		textFieldopquan.setColumns(10);
		
		textFieldopid = new JTextField();
		textFieldopid.setBounds(234, 194, 86, 20);
		contentPane.add(textFieldopid);
		textFieldopid.setColumns(10);
		
		textFieldoid = new JTextField();
		textFieldoid.setBounds(234, 239, 86, 20);
		contentPane.add(textFieldoid);
		textFieldoid.setColumns(10);
		
		textFieldpid = new JTextField();
		textFieldpid.setBounds(234, 284, 86, 20);
		contentPane.add(textFieldpid);
		textFieldpid.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="insert into Orders_Products (orders_prod_id, order_id, prod_id, prod_quan) values (?,?,?,?)";
					
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textFieldopid.getText());
					pst.setString(2, textFieldoid.getText());
					pst.setString(3, textFieldpid.getText());
					pst.setString(4, textFieldopquan.getText());
					
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
		btnSave.setBounds(163, 401, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="update Orders_Products set orders_prod_id='"+textFieldopid.getText()+"',order_id='"+textFieldoid.getText()+"', prod_id = '"+textFieldpid.getText()+"', prod_quan = '"+textFieldopquan.getText()+"' where orders_prod_id='"+textFieldopid.getText()+"'";
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
		btnUpdate.setBounds(163, 462, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action=JOptionPane.showConfirmDialog(null, "Do you really want to delete? :(","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				
				try{
					String query ="delete from Orders_Products where orders_prod_id ='"+textFieldopid.getText()+"'";
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
		btnDelete.setBounds(163, 524, 89, 23);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 197, 726, 731);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					String query ="Select * from Orders_Products where orders_prod_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,id);
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldopid.setText(rs.getString("orders_prod_id"));
						textFieldoid.setText(rs.getString("order_id"));
						textFieldpid.setText(rs.getString("prod_id"));
						textFieldopquan.setText(rs.getString("prod_quan"));
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
		btnReturnToMenu.setBounds(149, 990, 138, 23);
		contentPane.add(btnReturnToMenu);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="Select * from Orders_Products where orders_prod_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,(String)comboBox.getSelectedItem());
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldopid.setText(rs.getString("orders_prod_id"));
						textFieldoid.setText(rs.getString("order_id"));
						textFieldpid.setText(rs.getString("prod_id"));
						textFieldopquan.setText(rs.getString("prod_quan"));
						
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		comboBox.setBounds(26, 111, 200, 50);
		contentPane.add(comboBox);
		
		comboBoxSelector = new JComboBox();
		comboBoxSelector.setModel(new DefaultComboBoxModel(new String[] {"orders_prod_id", "order_id", "prod_id", "prod_quan"}));
		comboBoxSelector.setBounds(505, 111, 200, 50);
		contentPane.add(comboBoxSelector);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					String selection=(String)comboBoxSelector.getSelectedItem();
					String query ="Select * from Orders_Products where "+selection+" like '%"+textFieldSearch.getText()+"%' OR "+selection+" = '"+textFieldSearch.getText()+"'";
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
		textFieldSearch.setBounds(789, 126, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(829, 97, 46, 14);
		contentPane.add(lblSearch);
		refreshTable();
		fillComboBox();
	}

}
