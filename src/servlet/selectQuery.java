package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.JPAController;

/**
 * Servlet implementation class selectQuery
 */
@WebServlet("/selectQuery")
public class selectQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	JPAController controller = (JPAController) session.getAttribute("controller");
    	String query = request.getParameter("query");
    	if (query.equals("neud")) {
    		session.setAttribute("tableModel", controller.doQuery1());
    		session.setAttribute("tableName", "Дата - Клієнт");
    	} else if (query.equals("sumthing")) {
    		session.setAttribute("tableModel", controller.doQuery2());
    		session.setAttribute("tableName", "Товар на суму більше 2000");
    	}
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
