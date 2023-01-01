package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Field;
import model.FieldCreateLogic;

/**
 * Servlet implementation class FieldAdd
 */
@WebServlet("/FieldCreateServlet")
public class FieldCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FieldCreateServlet() {
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
		String ownerid = request.getParameter("ownerid");

		String action = request.getParameter("action"); //操作項目を決めるアクション属性

		Field field = new Field(fieldname, zip, tell, moyorieki, ekikara, kousokuic, rental, sougebuss, buss,
				bussteikara, yagai, sinrin, indoor, teireikai, teikyubi, ameyagai, bikou, comment, ownerid);

		//登録処理
		if(action.equals("add")) {
			FieldCreateLogic fieldaddlogic = new FieldCreateLogic();
			fieldaddlogic.fieldadd(field);
			response.sendRedirect("/SendhomeServlet"); //ホームに戻る時に登録情報更新されるためにサーブレットで処理を挟む

		}else if(action.equals("edit")){
			FieldCreateLogic fieldeditlogic = new FieldCreateLogic();
			fieldeditlogic.fieldedit(field);
			response.sendRedirect("/SendFieldStatusServlet"); //ページ更新でフィール詳細ページにリロードするためサーブレットで処理を挟む

		}else if(action.equals("delete")) {
			FieldCreateLogic fielddelete = new FieldCreateLogic();
			fielddelete.fielddelete(field);
			response.sendRedirect("/SendhomeServlet"); //ホームに戻る時に登録情報更新されるためにサーブレットで処理を挟む
		}
	}
}
