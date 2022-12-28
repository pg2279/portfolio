package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Field;

public class FieldCreateDao {

	private final String jdbc_url = "jdbc:h2:tcp://localhost/~/SurvGField";
	private final String db_user = "sa";
	private final String db_pass = "";

	public void fieldcreate(Field field) {

		//Owner owneradd = new Owner(); //登録情報インスタンスを受け取るためのクラス
		//owneradd = owner;

		//db接続
		try(Connection conn = DriverManager.getConnection(jdbc_url, db_user, db_pass)){

			//insert文の準備
			String sql = "INSERT INTO field(name, zip, tell, moyorieki, ekikara, kousokuic, rental, sougebuss, buss, "
					+ "bussteikara, yagai, sinrin, indoor, teireikai, teikyubi, ameyagai, bikou, comment, ownerid)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String sql2 = "UPDATE owner SET (filedint) = (?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt2 = conn.prepareStatement(sql2); //別々で実行すかはデーターベースの癖による、
			//insert文の?の値をを設定
			pstmt.setString(1, field.getFieldname());
			pstmt.setString(2, field.getZip());
			pstmt.setString(3, field.getTell());
			pstmt.setString(4, field.getMoyorieki());
			pstmt.setString(5, field.getEkikara());
			pstmt.setString(6, field.getKousokuic());
			pstmt.setString(7, field.getRental());
			pstmt.setString(8, field.getSougebuss());
			pstmt.setString(9, field.getBuss());
			pstmt.setString(10, field.getBussteikara());
			pstmt.setString(11, field.getYagai());
			pstmt.setString(12, field.getSinrin());
			pstmt.setString(13, field.getIndoor());
			pstmt.setString(14, field.getTeireikai());
			pstmt.setString(15, field.getTeikyubi());
			pstmt.setString(16, field.getAmeyagai());
			pstmt.setString(17, field.getBikou());
			pstmt.setString(18, field.getComment());
			pstmt.setString(19, field.getOwnerid());
			pstmt2.setString(1, "fieldint + 1");
			//insert文実を行
			int result = pstmt.executeUpdate();
			if (result != 1) {
				//ここにjspでエラーメッセージ表示処理をする（検討中）
			}
			int result2 = pstmt2.executeUpdate();
			if (result2 != 1) {
				//処理するかどうか考え中
			}
		}catch (SQLException e){
			e.printStackTrace();
			//ここにjspでエラーメッセージ表示処理をする
		}
		//ここにjspで成功時メッセージを出力する処理
	}
}
