import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Font;
public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JButton btnProducts;
	private JButton btnOrders;
	private JButton btnAddresses;
	private JButton btnOrderStatus;
	private JButton btnOrdersToProducts;
	private JButton btnProductsToCategories;
	private JButton btnProductDescription;
	private JButton btnNewButton;
	private JButton btnExit;
	private JButton btnQuery;
	private JButton btnQuery_1;
	private JButton btnQuery_2;
	private JButton btnQuery_3;
	private JButton btnQuery_4;
	private JButton btnQuery_5;
	private JButton btnQuery_6;
	private JButton btnQuery_7;
	private JButton btnHelp;
	private JButton btnMakeASpecefic;
	private JLabel label;
	//private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		conn=sqlscon.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1718, 1044);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Customers");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Image img2 = new ImageIcon(this.getClass().getResource("/Customer-icon.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img2));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//	JOptionPane.showMessageDialog(null, "The query was successful");
						frame.dispose();
						Product cust=new Product();
						cust.setVisible(true);
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnLogin.setBounds(47, 266, 245, 114);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblSelectTheTable = new JLabel("Select the table you would like to edit");
		lblSelectTheTable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelectTheTable.setBounds(563, 206, 465, 60);
		frame.getContentPane().add(lblSelectTheTable);
		
		btnProducts = new JButton("Products");
		btnProducts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Image img3 = new ImageIcon(this.getClass().getResource("/bag-icon.png")).getImage();
		btnProducts.setIcon(new ImageIcon(img3));
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					ActualProd product=new ActualProd();
					product.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnProducts.setBounds(321, 266, 220, 114);
		frame.getContentPane().add(btnProducts);
		
		btnOrders = new JButton("Orders");
		btnOrders.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Image img4 = new ImageIcon(this.getClass().getResource("/Order-icon.png")).getImage();
		btnOrders.setIcon(new ImageIcon(img4));
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				//	JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Orders order=new Orders();
					order.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnOrders.setBounds(570, 266, 220, 114);
		frame.getContentPane().add(btnOrders);
		
		btnAddresses = new JButton("Addresses");
		btnAddresses.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Image img5 = new ImageIcon(this.getClass().getResource("/Addressesicon.png")).getImage();
		btnAddresses.setIcon(new ImageIcon(img5));
		btnAddresses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Addresses addr=new Addresses();
					addr.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnAddresses.setBounds(824, 266, 236, 114);
		frame.getContentPane().add(btnAddresses);
		
		btnOrderStatus = new JButton("Order Status");
		btnOrderStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOrderStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					OrderStatus os=new OrderStatus();
					os.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnOrderStatus.setBounds(570, 402, 220, 69);
		frame.getContentPane().add(btnOrderStatus);
		
		btnOrdersToProducts = new JButton("Orders to Products");
		btnOrdersToProducts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOrdersToProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					OrdersProducts orderprod=new OrdersProducts();
					orderprod.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnOrdersToProducts.setBounds(570, 482, 220, 69);
		frame.getContentPane().add(btnOrdersToProducts);
		
		btnProductsToCategories = new JButton("Products to Categories");
		btnProductsToCategories.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnProductsToCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					ProductsCategories prodcad= new ProductsCategories();
					prodcad.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnProductsToCategories.setBounds(321, 402, 220, 69);
		frame.getContentPane().add(btnProductsToCategories);
		
		btnProductDescription = new JButton("Product Description");
		btnProductDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProductDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					ProductDescription proddes=new ProductDescription();
					proddes.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnProductDescription.setBounds(321, 482, 220, 69);
		frame.getContentPane().add(btnProductDescription);
		
		btnNewButton = new JButton("Categories");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Image img8 = new ImageIcon(this.getClass().getResource("/Category-icon.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img8));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Categories cat=new Categories();
					cat.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnNewButton.setBounds(1095, 266, 276, 114);
		frame.getContentPane().add(btnNewButton);
		
		btnExit = new JButton("EXIT");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Image img7 = new ImageIcon(this.getClass().getResource("/Button-Close-icon.png")).getImage();
		btnExit.setIcon(new ImageIcon(img7));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action=JOptionPane.showConfirmDialog(null, "Are you sure you want to exit? :(","Delete",JOptionPane.YES_NO_OPTION);
				if (action==0){
					frame.dispose();
				}
			}
		});
		btnExit.setBounds(747, 877, 124, 52);
		frame.getContentPane().add(btnExit);
		
		btnQuery = new JButton("Query 1");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Query1 q1=new Query1();
					q1.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnQuery.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuery.setBounds(65, 634, 227, 80);
		frame.getContentPane().add(btnQuery);
		
		btnQuery_1 = new JButton("Query 2");
		btnQuery_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Query2 q2=new Query2();
					q2.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnQuery_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuery_1.setBounds(321, 634, 220, 80);
		frame.getContentPane().add(btnQuery_1);
		
		btnQuery_2 = new JButton("Query 3");
		btnQuery_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Query3 q3=new Query3();
					q3.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnQuery_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuery_2.setBounds(570, 634, 220, 80);
		frame.getContentPane().add(btnQuery_2);
		
		btnQuery_3 = new JButton("Query 4");
		btnQuery_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Query4 q4=new Query4();
					q4.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnQuery_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuery_3.setBounds(824, 633, 236, 82);
		frame.getContentPane().add(btnQuery_3);
		
		btnQuery_4 = new JButton("Query 5");
		btnQuery_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Query5 q5=new Query5();
					q5.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnQuery_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuery_4.setBounds(65, 725, 227, 80);
		frame.getContentPane().add(btnQuery_4);
		
		btnQuery_5 = new JButton("Query 6");
		btnQuery_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Query6 q6=new Query6();
					q6.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnQuery_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuery_5.setBounds(321, 725, 220, 80);
		frame.getContentPane().add(btnQuery_5);
		
		btnQuery_6 = new JButton("Query 7");
		btnQuery_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Query7 q7=new Query7();
					q7.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnQuery_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuery_6.setBounds(570, 725, 220, 80);
		frame.getContentPane().add(btnQuery_6);
		
		btnQuery_7 = new JButton("Query 8");
		btnQuery_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Query8 q8=new Query8();
					q8.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
				
			}
		});
		btnQuery_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQuery_7.setBounds(824, 726, 236, 80);
		frame.getContentPane().add(btnQuery_7);
		
		btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					Help help=new Help();
					help.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Image img6 = new ImageIcon(this.getClass().getResource("/help-icon.png")).getImage();
		btnHelp.setIcon(new ImageIcon(img6));
		btnHelp.setBounds(1483, 266, 187, 114);
		frame.getContentPane().add(btnHelp);
		
		btnMakeASpecefic = new JButton("Make a specefic query with SQL command\r\n");
		btnMakeASpecefic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//JOptionPane.showMessageDialog(null, "The query was successful");
					frame.dispose();
					QueryUnique qu=new QueryUnique();
					qu.setVisible(true);
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		btnMakeASpecefic.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnMakeASpecefic.setBounds(1107, 690, 485, 52);
		frame.getContentPane().add(btnMakeASpecefic);
		
		label = new JLabel("");
		label.setBounds(10, 0, 904, 202);
		Image img9 = new ImageIcon(this.getClass().getResource("/leather-bags-top-banner.jpg")).getImage();
		label.setIcon(new ImageIcon(img9));
		frame.getContentPane().add(label);
		Image img = new ImageIcon(this.getClass().getResource("/bag-icon.png")).getImage();
		
		
	}
}
