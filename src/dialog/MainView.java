package dialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import controller.IController;
import controller.JPAController;
import controller.JdbcTableModel;
import dialog.DlgOrder;
import dialog.DlgProduct;
import dialog.DlgClient;

import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.awt.MenuBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainView extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	IController controller;
	String className;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
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
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 516, 21);
		contentPane.add(menuBar);
		
		JMenu mnTable = new JMenu("Table");
		menuBar.add(mnTable);
		
		JMenuItem mntmCreateDB = new JMenuItem("Create DB");
		mntmCreateDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCreateDB();
			}
		});
		mnTable.add(mntmCreateDB);
		
		JMenuItem mntmProduct = new JMenuItem("Product");
		mntmProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProduct();
			}
		});
		mnTable.add(mntmProduct);
		
		JMenuItem mntmClient = new JMenuItem("Client");
		mntmClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showClient();
			}
		});
		mnTable.add(mntmClient);
		
		JMenuItem mntmOrder = new JMenuItem("Order");
		mntmOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showOrder();
			}
		});
		mnTable.add(mntmOrder);
		
		JMenu mnOperation = new JMenu("Operation");
		menuBar.add(mnOperation);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onDelete();
			}
		});
		mnOperation.add(mntmDelete);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAdd(className);
			}
		});
		mnOperation.add(mntmAdd);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onEdit();
			}
		});
		mnOperation.add(mntmEdit);
		
		JMenu mnQuery = new JMenu("Query");
		menuBar.add(mnQuery);
		
		JMenuItem mntmQry1 = new JMenuItem("Query1");
		mntmQry1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onQuery1();
			}
		});
		mnQuery.add(mntmQry1);
		
		JMenuItem mntmQry2 = new JMenuItem("Query2");
		mntmQry2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onQuery2();
			}
		});
		mnQuery.add(mntmQry2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 496, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}
	public JTable getTable() {
		return table;
	}

	private boolean checkClassName() {
		return className.equals("Client") 
				|| className.equals("Product")
				|| className.equals("Zakaz");
	}
	
	private int getSelectedRow() {
		int row = table.getSelectedRow();
		if (row == -1)
			JOptionPane.showMessageDialog(frame, "Row is not selected",
	"Table " + className, JOptionPane.WARNING_MESSAGE);
		return row;
	}
	protected void onCreateDB() {
		/*MyConnector connector = new MyConnector();
		Connection conn = connector.getConnection();
		try {
			Statement st = conn.createStatement();
			st.addBatch("create table MASTER (" 
					+ " ID integer generated always as identity," 
					+ " LASTNAME varchar(30) default '' not null," 
					+ " FIRSTNAME varchar(30) default '' not null,"
					+ " ACTIVITY varchar(30) default '' not null,"
					+ " EMAIL varchar(30) default '' not null,"
					+ " primary key (ID))");
			st.addBatch("create table ORDER (" 
					+ " ID integer generated always as identity," 
					+ " IDCLIENT integer, IDPRODUCT integer," 
					+ " DATE date ,"
					+ " primary key (ID))");
			st.addBatch("alter table ORDER " 
					+ " add constraint FK_ORDER_PRODUCT " 
					+ "	foreign key (IDPRODUCT)"
					+ " references PRODUCT (ID) on delete cascade");
			st.addBatch("alter table ORDER " 
					+ " add constraint FK_ORDER_CLIENT " 
					+ " foreign key (IDCLIENT) "
					+ " references CLIENT (ID)  on delete cascade");
			st.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connector.closeConnection();
		}*/
		}
	protected void showProduct() {
    	className = "Product";
    	table.setModel(controller.getModel(className));
	}
	protected void showClient() {
    	className = "Client";
    	table.setModel(controller.getModel(className));
	}
	protected void showOrder() {
   	className = "Zakaz";
    	table.setModel(controller.getModel(className));
	}

	public void setController(IController controller) {
		this.controller = controller;

	}

	protected void onAdd(String className) {
		if(!checkClassName()) return;
		
			IDlg dlg = null;
			if (className.equals("Client")) dlg = new DlgClient();
			if (className.equals("Product")) dlg = new DlgProduct();
			if (className.equals("Zakaz")) {
				dlg = new DlgOrder();
				((DlgOrder) dlg).setController(controller);
}
			if (dlg == null)
				return;
			// Активізуємо потрібний діалог
			((JDialog) dlg).setVisible(true);
			//Передаємо створений об'єкт контролеру
			try {
				controller.add(dlg.getObject());
				table.setModel(controller.getModel(className));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "Створення об'єкту "
		 								+ className, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	protected void onEdit() {
		if(!checkClassName()) return;
		TableModel model =  table.getModel();
		int row = getSelectedRow();
		if (row == -1) return;
		String str = model.getValueAt(row, 0).toString();
		int id = Integer.parseInt((str) );
		IDlg dlg = null;
		if (className.equals("Client")) {
			dlg = new DlgClient();
			String oldSex = (String)model.getValueAt(row, 1);
			String oldClientLast = (String) model.getValueAt(row, 2);
			String oldClientFirst = (String) model.getValueAt(row, 3);
			String oldClientTelephone = (String)model.getValueAt(row, 4);
			String oldClientEmail = (String) model.getValueAt(row, 5);
			((DlgClient) dlg).setSex(oldSex);
			((DlgClient) dlg).setLastname(oldClientLast);
			((DlgClient) dlg).setFirstname(oldClientFirst);
			((DlgClient) dlg).setDiscountCard(oldClientTelephone);
			((DlgClient) dlg).setEmail(oldClientEmail);
			
		} else if (className.equals("Product")) {
			dlg = new DlgProduct();
			String oldProductName = (String) model.getValueAt(row, 1);
			String oldProductBrend = (String) model.getValueAt(row, 2);
			String oldProductPrice = model.getValueAt(row, 3).toString();
			((DlgProduct) dlg).setName(oldProductName);
			((DlgProduct) dlg).setBrend(oldProductBrend);
			(((DlgProduct) dlg)).setPrice(oldProductPrice);
			
		} else if (className.equals("Zakaz")) {
			dlg = new DlgOrder();
			((DlgOrder) dlg).setController(controller);
			String oldClient = (String) model.getValueAt(row, 1);
			String oldProduct = (String) model.getValueAt(row, 2);
			String oldDate = model.getValueAt(row, 3).toString();
			((DlgOrder) dlg).setClientName(oldClient);
			((DlgOrder) dlg).setProductName(oldProduct);
			((DlgOrder) dlg).setRegistration(oldDate);
		}
		// Активізуємо потрібний діалог
		((JDialog) dlg).setVisible(true);
		//Передаємо скориговані дані контролеру
		try {
			controller.edit(id, dlg.getObject());
			table.setModel(controller.getModel(className));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Створення об'єкту " 
	+ className, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	protected void onDelete() {
		if (!checkClassName()) return;
		TableModel model =  table.getModel();
		int row = getSelectedRow();
				String str = model.getValueAt(row, 0).toString();
		int id = Integer.parseInt((str) );
		controller.delete(id, className);
		model = controller.getModel(className);
		table.setModel(model);
	}
	protected void onQuery1() {
		className = "";
		table.setModel(controller.doQuery1());	
	}
	protected void onQuery2() {
		className = "";
		table.setModel(controller.doQuery2());	
	}
}
