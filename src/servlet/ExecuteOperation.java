package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.mail.MessagingException;
import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.TableModel;

import controller.JPAController;
import controller.MailSender;
import mail.Sender;
import mail.TestReceivMail2;
import mail.TestSendMail;
import tpp4.Client;
import tpp4.IModel;
import tpp4.Product;
import tpp4.Zakaz;

/**
 * Servlet implementation class ExecuteOperation
 */
@WebServlet("/ExecuteOperation")
public class ExecuteOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteOperation() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private IModel findById(int id, JPAController controller, String className) {
			try {
				IModel obj = null;
				Class clazz = Class.forName("tpp4."+className);
				for (Object x : controller.getObjectList(clazz)) {
					obj = (IModel) x;
					if (obj.getId() == id)
						return obj;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	String tableName = (String) session.getAttribute("tableName");
    	JPAController controller = (JPAController) session.getAttribute("controller");
    	String className = "tpp4." + tableName;
    	String operation = (String)session.getAttribute("operation");
    	if (operation.equals("delete")) {
    		int id = Integer.parseInt(request.getParameter("id"));
    		controller.delete(id, tableName);
    	} else {
    		IModel obj = null;
    		try {
    			obj = (IModel) Class.forName(className).newInstance();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		if (obj instanceof Client) {
    			String lastname = request.getParameter("lastname");
    			((Client) obj).setLastname(lastname);
    			String firstname = request.getParameter("firstname");
    			((Client) obj).setFirstname(firstname);
    			String sex = request.getParameter("sex");
    			((Client) obj).setSex(sex);
    			String telephone = request.getParameter("telephone");
    			((Client) obj).setTelephone(telephone);
    			String email = request.getParameter("email");
    			((Client) obj).setEmail(email);
    		} else if (obj instanceof Product) {
    			String name = request.getParameter("name");
    			((Product) obj).setName(name);
    			double price = Double.parseDouble(request.getParameter("price"));
    			((Product) obj).setPrice(price);
    			String brend = request.getParameter("brend");
    			((Product) obj).setBrend(brend);
    		} else if (obj instanceof Zakaz) {
    			Date date = Date.valueOf(request.getParameter("date"));
    			((Zakaz) obj).setDate(date);
    			if(operation.equals("add")){
    				int clientId = Integer.parseInt(request.getParameter("clientId"));
    				Client client = (Client)(findById(clientId,controller,"Client"));
    				((Zakaz) obj).setClient(client);;
    				int productId = Integer.parseInt(request.getParameter("productId"));
    				Product product = (Product)(findById(productId,controller,"Product"));
    				((Zakaz) obj).setProduct(product);
    			}
    		}
    		switch (operation) {
    		case"edit":
    			int id = Integer.parseInt(request.getParameter("id"));
    			controller.edit(id, obj);
    			break;
    		case"add":
    			controller.add(obj);
    		}
    	}
 	

    	TableModel tableModel = controller.getModel(tableName);
    	session.setAttribute("tableModel", tableModel);
    	request.getRequestDispatcher("showTable.jsp").forward(request, response);  
    	
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
 
	}

}
