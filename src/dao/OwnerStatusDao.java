package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Owner;

public class OwnerStatusDao {

	Owner owner;

	public List<String> login(String id) {

		List<String> loginlist = new ArrayList<>();
		//データベース接続
		try(Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/example","sa","")){

			//select文準備
			String sql = "SELECT loginid, pass1 FROM owner WHERE loginid =" + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//select実行しデータを取得
			ResultSet rs = pstmt.executeQuery();

			//結果表に格納されたレコードを表示
			while(rs.next()) {
				String loginid = rs.getString("loginid");
				String pass1 = rs.getString("pass1");
				loginlist.add(loginid);
				loginlist.add(pass1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return loginlist;
	}

	public List<List<Object>> sendhome(String id) {
                                                       //                 -------List field-------
		List<List<Object>> status = new ArrayList<>(); //List statusの中に<[owner] [fieldname] [zip]>
		List<Object> field = new ArrayList<>();

		try(Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/example","sa","")){

			owner = findall(id);
			String sqlownerid = owner.getOwnerid();
			field.add(owner); //Listのfieldの先頭のownerインスタンスを入れる

			//select文準備
			String sql = "SELECT name, zip From field WHERE ownerid =" + sqlownerid;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//select実行
			ResultSet rs = pstmt.executeQuery();

			//select文実行結果を受け取る
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String fieldname = rs.getString("name");
				String zip = rs.getString("zip");
				field.add(fieldname); //Listのfieldの次の要素にfieldnameその次の要素にzipを入れる
				field.add(zip);
			}
			status.add(field); //List<List>statusにListのfieldを入れる
			}catch (SQLException e) {
				e.printStackTrace();
			}
		return status;
	}

	public List<Object> senddelete(String id) {
		List<Object> deletestatus = new ArrayList<>(); //最初の要素にfieldint(int型)2番目の要素にdelete承認情報(String型)
		deletestatus.add(selectfieldint(id));
		try(Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/example","sa","")){
			String sql = "SELECT userid, pass1 FROM owner WHERE ownerid = " + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				deletestatus.add(rs.getString("userid"));
				deletestatus.add(rs.getString("pass1"));
				}
			}catch(SQLException e) {e.printStackTrace();}
		return deletestatus;
	}

	public String selectownerid(String id) {
		String ownerid = null;
		try(Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/example","sa","")){
			String sql = "SELECT ownerid FROM owner WHERE loginid =" + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {ownerid = rs.getString("ownerid");}
			}catch(SQLException e) {e.printStackTrace();}
		return ownerid;
	}

	public int selectfieldint(String id) {
		String fieldint = null;
		try(Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/example","sa","")){
			String sql = "SELECT field FROM owner WHERE loginid =" + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {fieldint = rs.getString("fieldint");}
			}catch(SQLException e) {e.printStackTrace();}
		int fieldintreturn = Integer.parseInt(fieldint);
		return fieldintreturn;
	}

	public Owner findall(String id) {

		try(Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/example","sa","")){

			//select文準備
			String sql = "SELECT loginid, pass1, mail, name, ownerid FROM owner WHERE ownerid =" + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//select実行しデータを取得
			ResultSet rs = pstmt.executeQuery();

			//結果表に格納されたレコードを表示
			while(rs.next()) {
				String loginid = rs.getString("loginid");
				String pass1 = rs.getString("pass1");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				String ownerid = rs.getString("ownerid");
				owner = new Owner(loginid, pass1, mail, name, ownerid);
			}
			}catch(SQLException e) {
				e.printStackTrace();
		}
		return owner;
	}
}