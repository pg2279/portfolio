package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Field;

/**
 * Servlet implementation class ListenerServlet
 */
@WebServlet("/ListenerServlet")
public class ListenerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListenerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String state = request.getParameter("state"); //都道府県
		String rental = request.getParameter("rental");
		String sougebuss = request.getParameter("sougebuss");
		String yagai = request.getParameter("yagai");
		String sinrin = request.getParameter("sinrin");
		String indoor = request.getParameter("inddoor");
		String teireikai = request.getParameter("teireikai");
		String teikyubi = request.getParameter("teikyubi"); //営業曜日
		String ameyagai = request.getParameter("ameyagai");
		String time = request.getParameter("time");

		Field field = new Field("", state, "", "", "", "", rental, sougebuss, "", "", yagai, sinrin, indoor,
				teireikai, teikyubi, ameyagai, "", "", "", time);
		FieldStatusDao fiel

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
