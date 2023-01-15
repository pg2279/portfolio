package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Field;
import model.Owner;
import model.SendPageLogic;

/**
 * Servlet implementation class SendhomeServlet
 */
@WebServlet("/SendPageServlet")
public class SendPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendPageServlet() {
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
		String menu = (String)request.getAttribute("menu");
		String ownerid = null;
		Owner owner = new Owner();
		SendPageLogic sendpagelogic = new SendPageLogic();
		String forwardpath = null;

		if(menu == "sendhome") {
			HttpSession session = request.getSession();
			ownerid = (String)session.getAttribute("islogin");

			if(ownerid == null || ownerid == "") {response.sendRedirect("/survgfsearch/index.jsp");
			}else {
				List<List<Object>> fieldint = sendpagelogic.sendhome(ownerid); //homeで出力される情報リスト

				owner = (Owner)fieldint.get(0);
				Object fieldnameObj = fieldint.get(1);
				Object zipObj = fieldint.get(2);
				String fieldname = fieldnameObj.toString();
				String zip = zipObj.toString();

				request.setAttribute("owner", owner); //homeで出力される情報リストをリクエストスコープに保存
				request.setAttribute("fieldname", fieldname);
				request.setAttribute("zip", zip);
				forwardpath = "/WEB-INF/owner/home.jsp";
			}
		}else if(menu == "sendfieldstarus") {
			HttpSession session = request.getSession();
			ownerid = (String) session.getAttribute("islogin"); //isloginとなっているが中身はownerid
			Field fieldstatus = sendpagelogic.sendfieldstatus(ownerid); //フィールド詳細で出力される情報
			request.setAttribute("fieldstatus", fieldstatus); //フィールド詳細で出力される情報リストをリクエストスコープに保存
			forwardpath = "/WEB-INF/owner/fieldstatus.jsp";
		}else if(menu == "sendownerstatus") {
			HttpSession session = request.getSession();
			ownerid = (String) session.getAttribute("islogin"); //isloginとなっているが中身はownerid
			owner = sendpagelogic.sendownerstatus(ownerid); //フィールド詳細で出力される情報(status)
			request.setAttribute("ownerdstatus", owner); //フィールド詳細で出力される情報リストをリクエストスコープに保存
			forwardpath = "/WEB-INF/owner/ownerstatus.jsp";
		}
		response.sendRedirect(forwardpath);
	}
}
