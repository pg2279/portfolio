package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Owner;

public class OwnerStatusDao {


	public List<String> login(String id) {

		List<String> loginlist = new ArrayList<>();
		//データベース接続
		try(Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/example","sa","")){

			//select文準備
			String sql = "SELECT userid, pass1 FROM owner WHERE userid =" + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//select実行しデータを取得
			ResultSet rs = pstmt.executeQuery();

			//結果表に格納されたレコードを表示
			while(rs.next()) {
				String userid = rs.getString("userid");
				String pass1 = rs.getString("pass1");
				loginlist.add(userid);
				loginlist.add(pass1);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return loginlist;
	}

	public Map<Owner, List<String>> sendhome(String id) {


		Owner owner;
		Map<Owner, List<String>> status = new HashMap<>();

		List<String> field = new ArrayList<>();

		try(Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/example","sa","")){

			String sqlownerid = null;
			//select文準備
			String sql = "SELECT userid, pass1, mail, name, ownerid FROM owner WHERE userid =" + id;
			String sql2 = "SELECT name, zip From field WHERE ownerid =" + sqlownerid;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//select実行しデータを取得
			ResultSet rs = pstmt.executeQuery();

			//結果表に格納されたレコードを表示
			while(rs.next()) {
				String userid = rs.getString("userid");
				String pass1 = rs.getString("pass1");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				String ownerid = rs.getString("ownerid");

				sqlownerid = ownerid;
				owner = new Owner(userid, pass1, mail, name, ownerid);

				}

			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String fieldname = rs.getString("name");
				String zip = rs.getString("zip");

				field.add(fieldname);
				field.add(zip);

			}
			status.put(owner, field);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return status;


	}

}
