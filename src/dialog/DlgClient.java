package dialog;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tpp4.Client;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;

public class DlgClient extends JDialog implements  IDlg {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldLName;
	private JTextField textFieldFName;
	private Client client;
	private JTextField textFieldEmail;
	private JTextField textFieldTelephone;
	private JTextField textFieldSex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			DlgClient dialog = new DlgClient();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgClient() {
		setModal(true);
		setTitle("Client");
		setBounds(100, 100, 173, 339);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(6, 1, 0, 0));
		
		textFieldSex = new JTextField();
		textFieldSex.setBorder(new TitledBorder(null, "Sex", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(textFieldSex);
		textFieldSex.setColumns(10);
		
		textFieldLName = new JTextField();
		textFieldLName.setBorder(new TitledBorder(null, "Last Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(textFieldLName);
		textFieldLName.setColumns(10);
		
		textFieldFName = new JTextField();
		textFieldFName.setBorder(new TitledBorder(null, "First Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(textFieldFName);
		textFieldFName.setColumns(10);
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setBorder(new TitledBorder(null, "Telephone", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(textFieldTelephone);
		textFieldTelephone.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBorder(new TitledBorder(null, "Email", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
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
	public JTextField getTextFieldSex() {
		return textFieldSex;
	}
	public JTextField getTextFieldLName() {
		return textFieldLName;
	}
	public JTextField getTextFieldFName() {
		return textFieldFName;
	}
	public JTextField getTextFieldDiscount() {
		return textFieldTelephone;
	}
	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}
	public Object getObject() {
		return client;
	}
	public void setSex(String sex) {
		textFieldSex.setText(sex);
	}
	public void setLastname(String lastname) {
		textFieldLName.setText(lastname);
	}
	public void setFirstname(String firstname) {
		textFieldFName.setText(firstname);
	}
	public void setDiscountCard(String telephone) {
		textFieldTelephone.setText(telephone);
	}
	public void setEmail(String email) {
		textFieldEmail.setText(email);
	}
	protected void onCancel() {
		client = null;
		setVisible(false);
	}
	protected void onStore() {
		client = new Client();
		String sex = textFieldSex.getText();
		String lastname = textFieldLName.getText();
		String firstname = textFieldFName.getText();
		String telephone = textFieldTelephone.getText();
		String email = textFieldEmail.getText();
		client.setSex(sex);
		client.setLastname(lastname);
		client.setFirstname(firstname);
		client.setTelephone(telephone);
		client.setEmail(email);
		setVisible(false);
	}
}
