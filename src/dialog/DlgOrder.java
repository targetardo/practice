package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import controller.*;
import tpp4.*;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class DlgOrder extends JDialog implements IDlg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldProduct;
	private JTextField textFieldClient;
	private JTextField textFieldDate;
	private Zakaz order;
	private Product product;
	private Client client;
	private IController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			DlgOrder dialog = new DlgOrder();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgOrder() {
		setTitle("Order");
		setModal(true);
		setBounds(100, 100, 537, 259);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		textFieldProduct = new JTextField();
		textFieldProduct.setBounds(10, 10, 239, 40);
		textFieldProduct.setBorder(new TitledBorder(null, "Product", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textFieldProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectProduct();
			}
		});
		textFieldProduct.setEditable(false);
		textFieldProduct.setColumns(10);
		
		textFieldClient = new JTextField();
		textFieldClient.setBounds(277, 10, 239, 40);
		textFieldClient.setBorder(new TitledBorder(null, "Client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textFieldClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectClient();
			}
		});
		textFieldClient.setEditable(false);
		textFieldClient.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(168, 107, 205, 40);
		textFieldDate.setBorder(new TitledBorder(null, "Date", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textFieldDate.setColumns(10);
		contentPanel.setLayout(null);
		contentPanel.add(textFieldProduct);
		contentPanel.add(textFieldClient);
		contentPanel.add(textFieldDate);
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
	public JTextField getTextFieldDate() {
		return textFieldDate;
	}
	public void setController(IController controller) {
		this.controller = controller;
	}
	protected void selectProduct() {
		if (textFieldProduct.isEnabled()) {
			TableModel model = controller.getModel("Product");
			DlgSelect dlg = new DlgSelect(model,"Product");
			dlg.setVisible(true);
			product = (Product) dlg.getObject();
			textFieldProduct.setText(product.toString());
		}
	}
	protected void selectClient() {
		if (textFieldClient.isEnabled()) {
			TableModel model = controller.getModel("Client");
			DlgSelect dlg = new DlgSelect(model, "Client");
			dlg.setVisible(true);
			client = (Client) dlg.getObject();
			textFieldClient.setText(client.toString());
		}
	}
	public void setRegistration(String oldDate) {
		textFieldDate.setText(oldDate);
}

public void setProductName(String oldMaster) {
		textFieldProduct.setText(oldMaster);
		textFieldProduct.setEnabled(false);
}

public void setClientName(String oldClient) {
		textFieldClient.setText(oldClient);
		textFieldClient.setEnabled(false);
}

protected void onCancel() {
	order = null;
	setVisible(false);
}
protected void onStore() {
	try {
		order = new Zakaz();
		Date date = Date.valueOf(textFieldDate.getText());
		order.setProduct(product);
		order.setClient(client);
		order.setDate(date);
	} catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null, e, this.getTitle(), JOptionPane.ERROR_MESSAGE);
		return;
	}
	setVisible(false);
}

@Override
public Object getObject() {
	// TODO Auto-generated method stub
	return order;
}

	}
