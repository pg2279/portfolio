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
		String menu = request.getParameter("menu");
		String forwardpath = null;

		if(menu == "oweneradd") {
			String action = request.getParameter("action");
			if(action == null) {
				forwardpath = "/WEB-INF/accountcreate/ownerAddForm.jsp";

			}else if (action.equals("done")) {
				HttpSession session = request.getSession();
				Owner owneradd = (Owner) session.getAttribute("owneradd");

				OwnerCreateLogic owneraddlogic = new OwnerCreateLogic(); //登録フォームの内容をセッションスコープから取得
				owneraddlogic.owneradd(owneradd); //登録処理

				session.removeAttribute("owneradd"); //登録完了後不要となったインスタンス削除

				forwardpath = "/WEb-INF/accountcreate/OwnerAddDone.jsp";
			}

		}else if(menu == "delete") {
			List<Object> deletedata = new ArrayList<>();
			deletedata = (List<Object>)request.getAttribute("deletedata");

			int fieldint = (int)deletedata.get(0);
			String userid = (String)deletedata.get(1);
			String ownerid = (String)deletedata.get(2);

			Owner owner = new Owner(userid, "", "", "", ownerid);
			OwnerCreateLogic ownercreate = new OwnerCreateLogic();

			ownercreate.ownerdelete(owner, fieldint);

			forwardpath = "/WEB-INF/accountcreate/ownerDeleteSuccsess.jsp";
			//delete失敗時の処理を付け加える
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
		String forwardpath = null;

		RegexCheck regexcheck = new RegexCheck();
		boolean regextrue = regexcheck.ownercreate(id, pass1, mailaddress, ownername);

		HttpSession session = request.getSession();
		String ownerid = (String)session.getAttribute("islogin");

		//文を短くすることは可能だけど可読性を高める為にあえて各動作にif文書く
		if(action == "add") {                                         //アカウント登録時のowneridはデーターベースに自動振り分けしてもらう
			if(regextrue) {
				owner = new Owner(id, pass1, mailaddress, ownername, "");
				session.setAttribute("oweneradd", owner); //登録フォーム内容をセッションスコープに保存

				forwardpath = "/WEB-INF/accountadd/ownerAddComfirm.jsp";
			}
			forwardpath = "/WEB-INF/accountadd/ownerAddForm.jsp";

		}else if (action == "edit") {
			if(regextrue) {
				owner = new Owner(id, pass1, mailaddress, ownername, ownerid);
				OwnerCreateLogic ownercreate = new OwnerCreateLogic();
				ownercreate.owneredit(owner);

				request.setAttribute("menu", "sendownerstatus");
				forwardpath = "/SendPageServlet";
			}
			forwardpath = "/WEB-INF/owner/ownerEditForm.jsp";

		}else if(action == "delete") {
			List<Object> deletedata = new ArrayList<>();

			SendPageLogic sendpage = new SendPageLogic();
			deletedata = sendpage.senddelete(ownerid, pass1); //パスワード確認はsendpagelogicで行う
			//fieldintは使う機会が少ないからOwnerインスタンスで作らず必要になったら準備する
			if(deletedata != null) {
			request.setAttribute("deletedate", deletedata);

			forwardpath = "/WEB-INF/owner/ownerDeleteComfirm.jsp";
			}
			forwardpath = "/WEB-INF/owner/ownerDeleteForm.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardpath);
		dispatcher.forward(request, response);
	}
}
