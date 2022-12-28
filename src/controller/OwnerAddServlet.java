package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Owner;
import model.OwnerAddLogic;

/**
 * Servlet implementation class OwnerAddServlet
 */
@WebServlet("/OwnerAddServlet")
public class OwnerAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerAddServlet() {
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
			forwardpath = "/WEB-INF/accountadd/registerForm.jsp";
		}
		else if (action.equals("done")) {
			HttpSession session = request.getSession();
			Owner owneradd = (Owner) session.getAttribute("owneradd");

			OwnerAddLogic owneraddlogic = new OwnerAddLogic(); //登録フォームの内容をセッションスコープから取得
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
		Owner owneradd = new Owner(id, pass1, mailaddress, ownername, ""); //登録フォーム内容を設定 

		HttpSession session = request.getSession();
		session.setAttribute("oweneradd", owneradd); //登録フォーム内容をセッションスコープに保存

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accountadd/ownerAddComfirm.jsp");
		dispatcher.forward(request, response);

	}

}
