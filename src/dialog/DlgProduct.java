package dialog;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tpp4.Product;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.Color;

public class DlgProduct extends JDialog implements  IDlg {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private JTextField textFieldBrend;
	private Product product;
	private JTextField textFieldPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			DlgProduct dialog = new DlgProduct();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgProduct() {
		setModal(true);
		setTitle("Product");
		setBounds(100, 100, 188, 334);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(6, 1, 0, 0));
		
		textFieldName = new JTextField();
		textFieldName.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 70, 213)));
		contentPanel.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldBrend = new JTextField();
		textFieldBrend.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Brend", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 70, 213)));
		contentPanel.add(textFieldBrend);
		textFieldBrend.setColumns(10);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Price", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 70, 213)));
		contentPanel.add(textFieldPrice);
		textFieldPrice.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Store");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onStore();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onCancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public JTextField getTextFieldName() {
		return textFieldName;
	}
	public JTextField getTextFieldBrend() {
		return textFieldBrend;
	}
	public JTextField getTextFieldPrice() {
		return textFieldPrice;
	}
	public Object getObject() {
		return product;
	}
	public void setName(String name) {
		textFieldName.setText(name);
	}
	public void setBrend(String brend) {
		textFieldBrend.setText(brend);
	}
	public void setPrice(String price) {
		textFieldPrice.setText(price);
	}

	protected void onCancel() {
		product = null;
		setVisible(false);
	}
	protected void onStore() {
		product = new Product();
		String name = textFieldName.getText();
		String brend = textFieldBrend.getText();
		Double price = Double.valueOf(textFieldPrice.getText());
		product.setName(name);
		product.setBrend(brend);
		product.setPrice(price);
		setVisible(false);
	}
}