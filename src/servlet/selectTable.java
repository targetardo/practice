package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.TableModel;

import controller.JPAController;

/**
 * Servlet implementation class selectTable
 */
@WebServlet("/selectTable")
public class selectTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JPAController controller ;
	public static String className;
        public static JPAController getController() {
		if(controller==null)
			controller = new JPAController();
		return controller;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String tableName = request.getParameter("tableName");
			HttpSession session = request.getSession();
			JPAController controller = (JPAController) session.getAttribute("controller");
			TableModel tableModel = controller.getModel(tableName);
			session.setAttribute("tableName", tableName);
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







