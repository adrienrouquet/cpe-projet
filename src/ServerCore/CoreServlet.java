package ServerCore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/!")
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CoreServlet() {
        super();
       }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doGet");

      try {
			Conf.DBSetup.resetDatabase(getServletContext());
			
		} catch (SQLException e) {
			System.out.println("Error in CoreServlet Constructor:" + e.getMessage());
		}

		
	
		res.sendRedirect("AccountServlet");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doPost");
		res.sendRedirect("AccountServlet");
	}

}
