package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Field;

public class FieldStatusDao {

	private final String jdbc_url = "jdbc:h2:tcp://localhost/~/SurvGField";
	private final String db_user = "sa";
	private final String db_pass = "";

	Field field;

	public Field findall(String id) {

		//db接続
		try(Connection conn = DriverManager.getConnection(jdbc_url, db_user, db_pass)){

			//select文の準備
			String sql = "SELECT * FROM filed WHERE ownerid =" + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			//結果表に格納されたレコードを表示
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String fieldname = rs.getString("name");
				String zip = rs.getString("zip");
				String tell = rs.getString("tell");
				String moyorieki = rs.getString("moyorieki");
				String ekikara = rs.getString("ekikara");
				String kousokuic = rs.getString("kousokuic");
				String rental = rs.getString("rental");
				String sougebuss = rs.getString("sougebuss");
				String buss = rs.getString("buss");
				String bussteikara = rs.getString("bussteikara");
				String yagai = rs.getString("yagai");
				String sinrin = rs.getString("sinrin");
				String indoor = rs.getString("inddoor");
				String teireikai = rs.getString("teireikai");
				String teikyubi = rs.getString("teikyubi");
				String ameyagai = rs.getString("ameyagai");
				String bikou = rs.getString("bikou");
				String comment = rs.getString("comment");
				String ownerid = rs.getString("ownerid");

				field = new Field(fieldname, zip, tell, moyorieki, ekikara, kousokuic, rental, sougebuss, buss,
						bussteikara, yagai, sinrin, indoor, teireikai, teikyubi, ameyagai, bikou, comment, ownerid);
			}
		}catch (SQLException e){
			e.printStackTrace();
					//ここにjspでエラーメッセージ表示処理をする
		}
		return field;
	}
}