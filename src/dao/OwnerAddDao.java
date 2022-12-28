package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Owner;

public class OwnerAddDao {

	private final String jdbc_url = "jdbc:h2:tcp://localhost/~/SurvGField";
	private final String db_user = "sa";
	private final String db_pass = "";

	public void owneradd(Owner owner) {

		//Owner owneradd = new Owner(); //登録情報インスタンスを受け取るためのクラス
		//owneradd = owner;

		//db接続
		try(Connection conn = DriverManager.getConnection(jdbc_url, db_user, db_pass)){

			//insert文の準備
			String sql = "INSERT INTO owner(userid, pass1, mail, name, fieldint)VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//insert文の?の値をを設定
			pstmt.setString(1, owner.getId());
			pstmt.setString(2, owner.getPass1());
			pstmt.setString(3, owner.getMailaddress());
			pstmt.setString(4, owner.getOwnername());
			pstmt.setString(5, "0"); //初期フェール度数
			//オーナーidはデーターベースにて自動で割り当て
			
			//insert文実を行
			int result = pstmt.executeUpdate();
			if (result != 1) {
				//ここにjspでエラーメッセージ表示処理をする（検討中）
			}
		}catch (SQLException e){
			e.printStackTrace();
			//ここにjspでエラーメッセージ表示処理をする
		}
		//ここにjspで成功時メッセージを出力する処理
	}
}
