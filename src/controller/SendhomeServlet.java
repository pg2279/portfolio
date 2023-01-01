package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Owner;
import model.SendhomeLogic;

/**
 * Servlet implementation class SendhomeServlet
 */
@WebServlet("/SendhomeServlet")
public class SendhomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendhomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		String ownerid = (String)session.getAttribute("islogin");

		if(ownerid == null || ownerid == "") {response.sendRedirect("/survgfsearch/index.jsp");
		}else {
			Owner owner = new Owner();
			SendhomeLogic sendhomelogic = new SendhomeLogic();
			List<List<Object>> fieldint = sendhomelogic.sendhome(ownerid); //homeで出力される情報リスト

			owner = (Owner)fieldint.get(0);
			Object fieldnameObj = fieldint.get(1);
			Object zipObj = fieldint.get(2);
			String fieldname = fieldnameObj.toString();
			String zip = zipObj.toString();

			request.setAttribute("owner", owner); //homeで出力される情報リストをリクエストスコープに保存
			request.setAttribute("fieldname", fieldname);
			request.setAttribute("zip", zip);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/owner/home.jsp");
			dispatcher.forward(request, response);
		}
	}
}
