import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Help extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help frame = new Help();
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
	public Help() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 977, 777);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHelp = new JLabel("HELP");
		lblHelp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHelp.setBounds(438, 11, 125, 38);
		contentPane.add(lblHelp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 86, 876, 614);
		contentPane.add(scrollPane);
		
		JTextPane txtpnWhenInThe = new JTextPane();
		scrollPane.setColumnHeaderView(txtpnWhenInThe);
		txtpnWhenInThe.setText("When in the main menu, there will be a lgroup of buttons on the top. Each of these buttons are linked to a table where it's data can be viewed, added, updated and deleted.\r\n\r\nAddind data to a table:\r\n\r\nTo add data to a table, you will need to enter all the blank spaces with relative information. After all of the blank spaces have been filled, you will then click \"Save\". If the data was added sucessfully you will be told or else you will receive an error explaining the problem in  a popup message.\r\n\r\nUpdating data to a table:\r\n\r\nTo update data within it's respective table, you must update data based on the table_id so that field can't be changed. All of the other fields are free to change though. Note when updating ensure all the original values are there then change the ones you want to update in their respectibve fields. When you have finished putting the desired updated data into their fields, click \"Update\". If the data was updated sucessfully, you will receive a popup message informing you so. If not the pop up message dialog will share the error encountered.\r\n\r\nDeleting data from a table:\r\n\r\nTo delete data from a table all that is needed is the id# for the tuple in that table. Once the id# field is filled with the desired data to be tabled, click \"Delete\". You will first receive a pop up message asking if you are sure if you want to delete the data. If yes is clicked, a dialog box will pop up telling you whether the data was deleted succesfully or an error that occured. If no is clicked, nothing will be deleted.\r\n\r\nSearching a Table:\r\n\r\nIn each respective table, the data can be searched based on it's attributes. To search data, you will select the desired attribute to use as your criteria for the search. Once selected, type what you are searching for in the text field to the right underneath the search label. The table will automatically populate after each key is pressed with the results that match your search.\r\n\r\nSelecting data from the table:\r\n\r\nData can be selected from any row on the table by clicking it. Once a row is clicked, this will automatically fill your text fields to perform add, update, delete commands with it's respective data.\r\n\r\nSelecting data from the IdDropdown menu:\r\n\r\nData can also be slected from the dropdown menu over the textfields for the add, update, delete commands. Once selected, it will also populate theise fields automatically. \r\n\r\nReturning to menu:\r\n\r\nThe \"return to menu\" button will return you to the main menu and is always located within the lower left corner.\r\n\r\nLoading Tables:\r\n\r\nTables that require input, have a special Load Table button that will generate results once appropiate data has been entered within the required fields.\r\n\r\n");
		
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
		btnReturnToMenu.setBounds(47, 704, 107, 23);
		contentPane.add(btnReturnToMenu);
	}

}
