package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String loginid = (String)request.getAttribute("loginid");
		SendhomeLogic sendhomelogic = new SendhomeLogic();
		Map<Integer, List<String>> fieldint = sendhomelogic.sendhome(loginid); //homeで出力される情報リスト
		request.setAttribute("fieldint", fieldint); //homeで出力される情報リストをリクエストスコープに保存
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/owner/home.jsp");
		dispatcher.forward(request, response);
	}

}
