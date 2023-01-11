package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Owner;

public class OwnerCreateDao {

	private final String jdbc_url = "jdbc:h2:tcp://localhost/~/SurvGField";
	private final String db_user = "sa";
	private final String db_pass = "";

	PreparedStatement pstmt;
	PreparedStatement pstmt2;

	public void ownercreate(Owner owner, int menu, int fieldint) {

		//db接続
		try(Connection conn = DriverManager.getConnection(jdbc_url, db_user, db_pass)){

			//insert文の準備
			String sqladd = "INSERT INTO owner(userid, pass1, mail, name, fieldint)VALUES (?, ?, ?, ?, ?)";
			String sqledit = "UPDATE owner SET userid=?, pass1=?, mail=?, name=? WHERE ownerid=?";
			String sqldelete = "DELETE FROM owner WHERE ownerid=?";
			String sqldelete2 = "DELETE FROM field * WHERE ownerid=?";
			if(menu == 0) {
				pstmt = conn.prepareStatement(sqladd);
			}else if(menu == 1) {
				pstmt = conn.prepareStatement(sqledit);
			}else if(menu == 2) {
				pstmt = conn.prepareStatement(sqldelete);
				pstmt2 = conn.prepareStatement(sqldelete2);
			}
			//insert文の?の値をを設定
			pstmt.setString(1, owner.getId());
			pstmt.setString(2, owner.getPass1());
			pstmt.setString(3, owner.getMailaddress());
			pstmt.setString(4, owner.getOwnername());
			//オーナーidはデーターベースにて自動で割り当て
			if(menu == 0) {
				pstmt.setInt(5, 0); //初期フィールド度数
			}else if(menu == 1) {
				pstmt.setString(5, owner.getOwnerid());
			}else if(menu == 2) {
				pstmt.setString(1, owner.getOwnerid());
				pstmt2.setString(1, owner.getOwnerid());
			}
			//insert文実を行
			int result = pstmt.executeUpdate();
			int result2 = 0;
			if(result != 1) {
				//ここにjspでエラーメッセージ表示処理をする（検討中）
			}
			if(menu == 2) {result2 = pstmt2.executeUpdate();}
			if (result != 1 || result2 != fieldint) {
				//ここにjspでエラーメッセージ表示処理をする（検討中）
			}
		}catch (SQLException e){
			e.printStackTrace();
			//ここにjspでエラーメッセージ表示処理をする
		}
		//ここにjspで成功時メッセージを出力する処理
	}
}
