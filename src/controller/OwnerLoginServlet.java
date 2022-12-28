 package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.OwnerLoginLogic;

/**
 * Servlet implementation class OwnerLoginServlet
 */
@WebServlet("/OwnerLoginServlet")
public class OwnerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		OwnerLoginLogic ownerloginlogic = new OwnerLoginLogic();
		boolean islogin = ownerloginlogic.ownerlogin(id, pass); //ログイン処理

		if(islogin) { //ログイン成功時
			HttpSession session = request.getSession();
			session.setAttribute("islogin", islogin);
			request.setAttribute("loginid", id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/SendhomeServlet");
			dispatcher.forward(request, response);
		}
		//失敗時、isloginの真偽値でjspに再度ログインを促すように指示
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ownerLogin.jsp");
		dispatcher.forward(request, response);
	}

}