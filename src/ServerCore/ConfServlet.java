package ServerCore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConfServlet")
public class ConfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfServlet() {
        super();
       }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("ConfServlet: Entering doGet");

      try {
			Conf.DBSetup.resetDatabase(getServletContext());
		} catch (SQLException e) {
			System.out.println("Error in ConfServlet Constructor:" + e.getMessage());
		}

		
	
		res.sendRedirect("CoreServlet");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("ConfServlet: Entering doPost");
		res.sendRedirect("CoreServlet");
	}

}
