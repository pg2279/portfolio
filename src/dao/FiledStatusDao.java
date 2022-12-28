package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Field;

public class FiledStatusDao {

	private final String jdbc_url = "jdbc:h2:tcp://localhost/~/SurvGField";
	private final String db_user = "sa";
	private final String db_pass = "";

	public void findall(Field field) {

		//Owner owneradd = new Owner(); //登録情報インスタンスを受け取るためのクラス
		//owneradd = owner;

		//db接続
		try(Connection conn = DriverManager.getConnection(jdbc_url, db_user, db_pass)){

			//select文の準備
			String sql = "SELECT * FROM filed WHERE ownerid =" + field.getOwnerid();
			PreparedStatement pstmt = conn.prepareStatement(sql);



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
