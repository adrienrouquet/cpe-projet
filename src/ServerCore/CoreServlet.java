package ServerCore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TestEnvSetup.DBReset;

/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/CoreServlet")
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CoreServlet() {
        super();
        try {
			DBReset.resetDatabase();
		} catch (SQLException e) {
			System.out.println("Error in CoreServlet Constructor:" + e.getMessage());
		}
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doGet");
		RequestDispatcher rd = req.getRequestDispatcher("LoginServlet");
		rd.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doPost");
		RequestDispatcher rd = req.getRequestDispatcher("LoginServlet");
		rd.forward(req, res);
	}

}
