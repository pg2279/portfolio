package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FieldEditServlet
 */
@WebServlet("/FieldEditServlet")
public class FieldEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FieldEditServlet() {
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
		String fieldname = request.getParameter("name");
		String zip = request.getParameter("zip");
		String tell = request.getParameter("tell");
		String moyorieki = request.getParameter("moyorieki");
		String ekikara = request.getParameter("ekikara");
		String kousokuic = request.getParameter("kousokuic");
		String rental = request.getParameter("rental");
		String sougebuss = request.getParameter("sougebuss");
		String buss = request.getParameter("buss");
		String bussteikara = request.getParameter("bussteikara");
		String yagai = request.getParameter("yagai");
		String sinrin = request.getParameter("sinrin");
		String indoor = request.getParameter("indoor");
		String teireikai = request.getParameter("teireikai");
		String teikyubi = request.getParameter("teikyubi");
		String ameyagai = request.getParameter("ameyagai");
		String bikou = request.getParameter("bikou");
		String comment = request.getParameter("comment");

		//使うかどうか考え中
	}

}
