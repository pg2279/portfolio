package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Owner;
import model.OwnerCreateLogic;
import model.RegexCheck;
import model.SendPageLogic;

/**
 * Servlet implementation class OwnerAddServlet
 */
@WebServlet("/OwnerCreateServlet")
public class OwnerCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forwardpath = null;
		String action = request.getParameter("action");
		if(action == null) {
			forwardpath = "/WEB-INF/accountadd/ownerAddForm.jsp";
		}
		else if (action.equals("done")) {
			HttpSession session = request.getSession();
			Owner owneradd = (Owner) session.getAttribute("owneradd");

			OwnerCreateLogic owneraddlogic = new OwnerCreateLogic(); //登録フォームの内容をセッションスコープから取得
			owneraddlogic.owneradd(owneradd); //登録処理

			session.removeAttribute("owneradd"); //登録完了後不要となったインスタンス削除

			forwardpath = "/WEb-INF/accountadd/OwnerAddDone.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardpath);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass1 = request.getParameter("pass1");
		String mailaddress = request.getParameter("mail");
		String ownername = request.getParameter("name");
		//アカウント登録時のowneridはデーターベースに自動振り分けしてもらう
		String action = request.getParameter("action"); //操作項目を決めるアクション属性

		Owner owner;
		RequestDispatcher dispatcher;

		RegexCheck regexcheck = new RegexCheck();
		boolean regextrue = regexcheck.ownercreate(id, pass1, mailaddress, ownername);

		HttpSession session = request.getSession();
		String ownerid = (String)session.getAttribute("islogin");

		//文を短くすることは可能だけど可読性を高める為にあえて各動作にif文書く
		if(action == "add") {                                         //アカウント登録時のowneridはデーターベースに自動振り分けしてもらう
			if(regextrue) {
				owner = new Owner(id, pass1, mailaddress, ownername, "");
				session.setAttribute("oweneradd", owner); //登録フォーム内容をセッションスコープに保存

				dispatcher = request.getRequestDispatcher("/WEB-INF/accountadd/ownerAddComfirm.jsp");
				dispatcher.forward(request, response);
			}
			dispatcher = request.getRequestDispatcher("/WEB-INF/accountadd/ownerAddForm.jsp");
			dispatcher.forward(request, response);

		}else if (action == "edit") {
			if(regextrue) {
				owner = new Owner(id, pass1, mailaddress, ownername, ownerid);
				OwnerCreateLogic ownercreate = new OwnerCreateLogic();
				ownercreate.owneredit(owner);

				request.setAttribute("menu", "sendownerstatus");
				dispatcher = request.getRequestDispatcher("/SendPageServlet");
				dispatcher.forward(request, response);
			}
			dispatcher = request.getRequestDispatcher("/WEB-INF/owner/ownerEditForm.jsp");
			dispatcher.forward(request, response);

		}else if(action == "delete") {
			List<Object> senddelete = new ArrayList<>();
			owner = new Owner(id, pass1, "", "", ownerid);

			SendPageLogic sendpage = new SendPageLogic();
			senddelete = sendpage.senddelete(id); //fieldintは使う機会が少ないからOwnerインスタンスで作らず必要になったら準備する
			int fieldint = (int)senddelete.get(0);
			String pass1todelete = (String)senddelete.get(1);

			if(pass1.equals(pass1todelete)) {
				OwnerCreateLogic ownercreate = new OwnerCreateLogic();
				ownercreate.ownerdelete(owner, fieldint);

				dispatcher = request.getRequestDispatcher("/WEB-INF/owner/ownerDeleteComfirm.jsp");
				dispatcher.forward(request, response);
			}
			dispatcher = request.getRequestDispatcher("/WEB-INF/owner/ownerDeleteForm.jsp");
			dispatcher.forward(request, response);
		}
	}
}
