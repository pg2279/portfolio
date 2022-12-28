package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SendFieldStatusLogic;
import model.Field;
/**
 * Servlet implementation class SendFieldStatusServlet
 */
@WebServlet("/SendFieldStatusServlet")
public class SendFieldStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendFieldStatusServlet() {
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
		SendFieldStatusLogic sendfieldstatuslogic = new SendFieldStatusLogic();
		Field fieldstatus = sendfieldstatuslogic.sendfieldstatus(); //フィールド詳細で出力される情報
		request.setAttribute("fieldstatus", fieldstatus); //フィールド詳細で出力される情報リストをリクエストスコープに保存
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/owner/fieldstatus.jsp");
		dispatcher.forward(request, response);
	}

}
