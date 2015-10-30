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


public class OrderStatus extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldosname;
	private JTextField textFieldosid;
	private JTextField textFieldoslid;
	private JTextField textFieldospflag;
	private JTextField textFieldosdown;
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
					OrderStatus frame = new OrderStatus();
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
			String query ="select * from Order_status";
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
			String queries="Select * from Order_status";
			PreparedStatement pst=conn.prepareStatement(queries);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				//System.out.println(rs.getString("cust_fname"));
				comboBox.addItem(rs.getString("order_status_id"));
				}
		}catch (Exception e3){
			JOptionPane.showMessageDialog(null, e3);
			e3.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public OrderStatus() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1306, 932);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOrderstatus = new JLabel("OrderStatus");
		lblOrderstatus.setBounds(58, 40, 72, 14);
		contentPane.add(lblOrderstatus);
		
		JLabel lblOrderStatusId = new JLabel("Order Status ID");
		lblOrderStatusId.setBounds(28, 153, 102, 14);
		contentPane.add(lblOrderStatusId);
		
		JLabel lblLanguageId = new JLabel("Language ID");
		lblLanguageId.setBounds(28, 188, 102, 14);
		contentPane.add(lblLanguageId);
		
		JLabel lblOrderStatusName = new JLabel("Order Status Name");
		lblOrderStatusName.setBounds(28, 225, 102, 14);
		contentPane.add(lblOrderStatusName);
		
		JLabel lblPublicFlag = new JLabel("Public Flag");
		lblPublicFlag.setBounds(28, 267, 81, 14);
		contentPane.add(lblPublicFlag);
		
		JLabel lblDownloads = new JLabel("Downloads");
		lblDownloads.setBounds(28, 309, 81, 14);
		contentPane.add(lblDownloads);
		
		textFieldosname = new JTextField();
		textFieldosname.setBounds(179, 222, 86, 20);
		contentPane.add(textFieldosname);
		textFieldosname.setColumns(10);
		
		textFieldosid = new JTextField();
		textFieldosid.setBounds(179, 150, 86, 20);
		contentPane.add(textFieldosid);
		textFieldosid.setColumns(10);
		
		textFieldoslid = new JTextField();
		textFieldoslid.setBounds(179, 185, 86, 20);
		contentPane.add(textFieldoslid);
		textFieldoslid.setColumns(10);
		
		textFieldospflag = new JTextField();
		textFieldospflag.setBounds(179, 264, 86, 20);
		contentPane.add(textFieldospflag);
		textFieldospflag.setColumns(10);
		
		textFieldosdown = new JTextField();
		textFieldosdown.setBounds(179, 306, 86, 20);
		contentPane.add(textFieldosdown);
		textFieldosdown.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				String query ="insert into Order_status (order_status_id, language_id, order_status_name, public_flag, downloads_flag) values (?,?,?,?,?)";
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setString(1, textFieldosid.getText());
				pst.setString(2, textFieldoslid.getText());
				pst.setString(3, textFieldosname.getText());
				pst.setString(4, textFieldospflag.getText());
				pst.setString(5, textFieldosdown.getText());
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
		btnSave.setBounds(103, 365, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="update Order_status set order_status_id='"+textFieldosid.getText()+"',language_id='"+textFieldoslid.getText()+"', order_status_name = '"+textFieldosname.getText()+"', public_flag = '"+textFieldospflag.getText()+"', downloads_flag = '"+textFieldosdown.getText()+"' where order_status_id='"+textFieldosid.getText()+"'";
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
		btnUpdate.setBounds(103, 413, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action=JOptionPane.showConfirmDialog(null, "Do you really want to delete? :(","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				
				try{
					String query ="delete from Order_status where order_status_id ='"+textFieldosid.getText()+"'";
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
		btnDelete.setBounds(103, 468, 89, 23);
		contentPane.add(btnDelete);
		
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
		btnReturnToMenu.setBounds(103, 581, 162, 23);
		contentPane.add(btnReturnToMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 153, 820, 686);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					String query ="Select * from Order_status where order_status_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,id);
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldosid.setText(rs.getString("order_status_id"));
						textFieldoslid.setText(rs.getString("language_id"));
						textFieldosname.setText(rs.getString("order_status_name"));
						textFieldospflag.setText(rs.getString("public_flag"));
						textFieldosdown.setText(rs.getString("downloads_flag"));
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="Select * from Order_status where order_status_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,(String)comboBox.getSelectedItem());
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldosid.setText(rs.getString("order_status_id"));
						textFieldoslid.setText(rs.getString("language_id"));
						textFieldosname.setText(rs.getString("order_status_name"));
						textFieldospflag.setText(rs.getString("public_flag"));
						textFieldosdown.setText(rs.getString("downloads_flag"));
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		comboBox.setBounds(28, 71, 200, 50);
		contentPane.add(comboBox);
		
		comboBoxSelector = new JComboBox();
		comboBoxSelector.setModel(new DefaultComboBoxModel(new String[] {"order_status_id", "language_id", "order_status_name", "public_flag", "downloads_flag"}));
		comboBoxSelector.setBounds(434, 71, 200, 50);
		contentPane.add(comboBoxSelector);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					String selection=(String)comboBoxSelector.getSelectedItem();
					String query ="Select * from Order_status where ("+selection+" like '%"+textFieldSearch.getText()+"%') OR "+selection+" = '"+textFieldSearch.getText()+"'";
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
		textFieldSearch.setBounds(707, 86, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(747, 55, 46, 14);
		contentPane.add(lblSearch);
		refreshTable();
		fillComboBox();
	}

}
