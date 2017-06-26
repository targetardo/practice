package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import tpp4.Client;
import tpp4.Product;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dialog.IDlg;
import java.awt.ScrollPane;;
public class DlgSelect extends JDialog implements IDlg{

	private final JPanel contentPanel = new JPanel();
	private TableModel model;
	private String className;
	private Object obj;
	private JScrollPane scrollPane;
	private JTable table_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgSelect dialog = new DlgSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSelect() {
		setModal(true);
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onCancel();
				}
			});
			cancelButton.setBounds(329, 178, 76, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			JButton okButton = new JButton("Select");
			okButton.setBounds(228, 178, 91, 23);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onSelect();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 11, 385, 158);
			contentPanel.add(scrollPane);
			{
				table_2 = new JTable();
				scrollPane.setColumnHeaderView(table_2);
			}
		}	
	}	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	
	@Override
	public Object getObject() {
		return obj;
	}
	
	public DlgSelect(TableModel model, String className) {
		this();
		this.model = model;
		this.className = className;
		this.setTitle(className);
		table_2.setModel(model);
	}
	protected void onCancel() {
		obj = null;
		this.setVisible(false);	
}
	protected void onSelect() {
		//Class clz;
		try {
			Class clz = Class.forName("tpp4."+className);
			obj = clz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		int row = table_2.getSelectedRow();
		String str = model.getValueAt(row, 0).toString();
		int id = Integer.parseInt(str);
		/*String sex = (String)model.getValueAt(row, 1);
		String lastname = (String) model.getValueAt(row, 2);
		String firstname = (String)model.getValueAt(row, 3);
		String email = (String) model.getValueAt(row, 5);
		String telephone = (String) model.getValueAt(row, 4);*/
		//double  price = (Double)model.getValueAt(row, 3);
		if (obj instanceof Client){
			Client client = (Client)obj;
			String sex = (String)model.getValueAt(row, 1);
			String lastname = (String) model.getValueAt(row, 2);
			String firstname = (String)model.getValueAt(row, 3);
			String email = (String) model.getValueAt(row, 5);
			String telephone = (String) model.getValueAt(row, 4);
			client.setId(id);
			client.setSex(sex);
			client.setLastname(lastname);
			client.setFirstname(firstname);
			client.setTelephone(telephone);
			client.setEmail(email);
		}else if (obj instanceof Product) {
			Product product = (Product)obj;
			String name = (String)model.getValueAt(row, 1);
			String brend = (String) model.getValueAt(row, 2);
			product.setId(id);
			product.setName(name);
			product.setBrend(brend);
			String str1 = model.getValueAt(row, 3).toString();
			double price = Double.parseDouble(str1);
			product.setPrice(price);
		}else{ obj = null; }
		
		if(obj != null){
			this.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "ќб'Їкт не створено");
		}
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JTable getTable_2() {
		return table_2;
	}
	}
