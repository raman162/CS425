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
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Categories extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Categories frame = new Categories();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JTextField textFieldckey;
	private JTextField textFieldcdmod;
	private JTextField textFieldcdadd;
	private JTextField textFieldcname;
	private JTextField textFieldcsorder;
	private JTextField textFieldcpid;
	private JTextField textFieldcid;
	private JTable table;
	private JTextField textFieldSearch;
	private JComboBox comboBox;
	private JComboBox comboBoxSelector;
	private void refreshTable(){
		try{
			String query ="select * from Categories";
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
			String queries="Select * from Categories";
			PreparedStatement pst=conn.prepareStatement(queries);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				//System.out.println(rs.getString("cust_fname"));
				comboBox.addItem(rs.getString("cat_id"));
				}
		}catch (Exception e3){
			JOptionPane.showMessageDialog(null, e3);
			e3.printStackTrace();
		}
	}
	
	

	
	/**
	 * Create the frame.
	 */
	public Categories() {
		conn=sqlscon.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1217, 854);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategories = new JLabel("Categories");
		lblCategories.setBounds(44, 35, 84, 14);
		contentPane.add(lblCategories);
		
		JLabel lblCategoreyId = new JLabel("Categorey Id");
		lblCategoreyId.setBounds(26, 144, 84, 14);
		contentPane.add(lblCategoreyId);
		
		JLabel lblParentid = new JLabel("Parent Id");
		lblParentid.setBounds(26, 193, 46, 14);
		contentPane.add(lblParentid);
		
		JLabel lblSortorder = new JLabel("Sort Order\r\n");
		lblSortorder.setBounds(26, 243, 65, 14);
		contentPane.add(lblSortorder);
		
		JLabel lblCatname = new JLabel("Category Name\r\n");
		lblCatname.setBounds(26, 289, 102, 14);
		contentPane.add(lblCatname);
		
		JLabel lblDate = new JLabel("Date Added\r\n");
		lblDate.setBounds(26, 343, 65, 14);
		contentPane.add(lblDate);
		
		JLabel lblDateModified = new JLabel("Date Modified");
		lblDateModified.setBounds(26, 390, 84, 14);
		contentPane.add(lblDateModified);
		
		JLabel lblDateKeywords = new JLabel("Category Keywords");
		lblDateKeywords.setBounds(26, 436, 102, 14);
		contentPane.add(lblDateKeywords);
		
		textFieldckey = new JTextField();
		textFieldckey.setBounds(142, 433, 86, 20);
		contentPane.add(textFieldckey);
		textFieldckey.setColumns(10);
		
		textFieldcdmod = new JTextField();
		textFieldcdmod.setBounds(142, 387, 86, 20);
		contentPane.add(textFieldcdmod);
		textFieldcdmod.setColumns(10);
		
		textFieldcdadd = new JTextField();
		textFieldcdadd.setBounds(142, 340, 86, 20);
		contentPane.add(textFieldcdadd);
		textFieldcdadd.setColumns(10);
		
		textFieldcname = new JTextField();
		textFieldcname.setBounds(142, 286, 86, 20);
		contentPane.add(textFieldcname);
		textFieldcname.setColumns(10);
		
		textFieldcsorder = new JTextField();
		textFieldcsorder.setBounds(142, 240, 86, 20);
		contentPane.add(textFieldcsorder);
		textFieldcsorder.setColumns(10);
		
		textFieldcpid = new JTextField();
		textFieldcpid.setBounds(142, 190, 86, 20);
		contentPane.add(textFieldcpid);
		textFieldcpid.setColumns(10);
		
		textFieldcid = new JTextField();
		textFieldcid.setText("");
		textFieldcid.setBounds(142, 141, 86, 20);
		contentPane.add(textFieldcid);
		textFieldcid.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="insert into Categories (cat_id, parent_id, sort_order, cat_name, date_added, date_modified, categories_keywords) values (?,?,?,?,?,?,?)";
					
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textFieldcid.getText());
					pst.setString(2, textFieldcpid.getText());
					pst.setString(3, textFieldcsorder.getText());
					pst.setString(4, textFieldcname.getText());
					pst.setString(5, textFieldcdadd.getText());
					pst.setString(6, textFieldcdmod.getText());
					pst.setString(7, textFieldckey.getText());
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
		btnSave.setBounds(89, 499, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="update Categories set cat_id='"+textFieldcid.getText()+"',parent_id='"+textFieldcpid.getText()+"', sort_order = '"+textFieldcsorder.getText()+"', cat_name = '"+textFieldcname.getText()+"', date_added = '"+textFieldcdadd.getText()+"', date_modified = '"+textFieldcdmod.getText()+"', categories_keywords = '"+textFieldckey.getText()+"' where cat_id='"+textFieldcid.getText()+"'";
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
		btnUpdate.setBounds(89, 547, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action=JOptionPane.showConfirmDialog(null, "Do you really want to delete? :(","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				
				try{
					String query ="delete from Categories where cat_id ='"+textFieldcid.getText()+"'";
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
		}
				);
		btnDelete.setBounds(89, 594, 89, 23);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(310, 144, 814, 603);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					String query ="Select * from Categories where cat_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,id);
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldcid.setText(rs.getString("cat_id"));
						textFieldcpid.setText(rs.getString("parent_id"));
						textFieldcsorder.setText(rs.getString("sort_order"));
						textFieldcname.setText(rs.getString("cat_name"));
						textFieldcdadd.setText(rs.getString("date_added"));
						textFieldcdmod.setText(rs.getString("date_modified"));
						textFieldckey.setText(rs.getString("categories_keywords"));
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
		btnReturnToMenu.setBounds(80, 727, 134, 23);
		contentPane.add(btnReturnToMenu);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					String selection=(String)comboBoxSelector.getSelectedItem();
					String query ="Select * from Categories where ("+selection+" like '%"+textFieldSearch.getText()+"%') OR "+selection+" = '"+textFieldSearch.getText()+"'";
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
		textFieldSearch.setBounds(780, 79, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query ="Select * from Categories where cat_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,(String)comboBox.getSelectedItem());
					ResultSet rs =pst.executeQuery();
					while(rs.next()){
						textFieldcid.setText(rs.getString("cat_id"));
						textFieldcpid.setText(rs.getString("parent_id"));
						textFieldcsorder.setText(rs.getString("sort_order"));
						textFieldcname.setText(rs.getString("cat_name"));
						textFieldcdadd.setText(rs.getString("date_added"));
						textFieldcdmod.setText(rs.getString("date_modified"));
						textFieldckey.setText(rs.getString("categories_keywords"));
						
					}
				
					pst.close();
					
					
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2);
					e2.printStackTrace();
		}
			}
		});
		comboBox.setBounds(26, 63, 200, 50);
		contentPane.add(comboBox);
		
		comboBoxSelector = new JComboBox();
		comboBoxSelector.setModel(new DefaultComboBoxModel(new String[] {"cat_id", "parent_id", "sort_order", "cat_name", "date_added", "date_modified", "categories_keywords"}));
		comboBoxSelector.setBounds(398, 63, 200, 50);
		contentPane.add(comboBoxSelector);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(816, 54, 46, 14);
		contentPane.add(lblSearch);
		refreshTable();
		fillComboBox();
	}

}
