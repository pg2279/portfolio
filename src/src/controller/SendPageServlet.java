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

		Owner owner = new Owner();

		if(menu == "sendhome") {
			HttpSession session = request.getSession();
			String ownerid = (String)session.getAttribute("islogin");

			if(ownerid == null || ownerid == "") {response.sendRedirect("/survgfsearch/index.jsp");
			}else {
				SendPageLogic sendhomelogic = new SendPageLogic();
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
		}else if(menu == "sendfieldstarus") {
			HttpSession session = request.getSession();
			String ownerid = (String) session.getAttribute("islogin"); //isloginとなっているが中身はownerid
			SendPageLogic sendpagelogic = new SendPageLogic();
			Field fieldstatus = sendpagelogic.sendfieldstatus(ownerid); //フィールド詳細で出力される情報
			request.setAttribute("fieldstatus", fieldstatus); //フィールド詳細で出力される情報リストをリクエストスコープに保存
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/owner/fieldstatus.jsp");
			dispatcher.forward(request, response);

		}else if(menu == "sendownerstatus") {
			HttpSession session = request.getSession();
			String ownerid = (String) session.getAttribute("islogin"); //isloginとなっているが中身はownerid
			SendPageLogic sendpagelogic = new SendPageLogic();
			Owner ownerstatus = sendpagelogic.sendownerstatus(ownerid); //フィールド詳細で出力される情報
			request.setAttribute("ownerdstatus", ownerstatus); //フィールド詳細で出力される情報リストをリクエストスコープに保存
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/owner/ownerstatus.jsp");
			dispatcher.forward(request, response);
		}
	}
}
