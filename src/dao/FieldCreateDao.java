package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Field;
import model.Owner;

public class FieldCreateDao {

	private final String jdbc_url = "jdbc:h2:tcp://localhost/~/SurvGField";
	private final String db_user = "sa";
	private final String db_pass = "";

	PreparedStatement pstmt;
	PreparedStatement pstmt2;

	public void fieldcreate(Field field, int menu) {

		Owner owner = new Owner();
		//db接続
		try(Connection conn = DriverManager.getConnection(jdbc_url, db_user, db_pass)){

			//insert文の準備 フィールド作成時 fieldadd
			String sqladd = "INSERT INTO field(name, zip, tell, moyorieki, ekikara, kousokuic, rental, sougebuss, buss,"
					+ " bussteikara, yagai, sinrin, indoor, teireikai, teikyubi, ameyagai, bikou, comment, ownerid,"
					+ "time)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String sqladd2 = "UPDATE owner SET (filedint) = (?+1) WHERE owenerid=?";
			//unpdate文の準備 フィールド編集時 fieldedit
			String sqledit = "UPDATE field SET name=?, zip=?, tell=?, moyorieki=?, ekikara=?, kousokuic=?, rental=?, "
					+ "sougebuss=?, buss=?, bussteikara=?, yagai=?, sinrin=?, indoor=?, teireikai=?, teikyubi=? ,"
					+ "ameyagai=?, bikou=?, comment=?, time=? WHERE ownerid=?";
			String sqldelete = "DELETE FROM field WHERE name=? AND zip=? And ownerid=?"; //nameとzipは他のsql文と同じ順番だから?は今回の場合使いまわしでよい
			String sqldelete2 = "UPDATE owner SET (filedint) = (?-1) WHERE ownerid=?";
			if(menu == 0) {
				pstmt = conn.prepareStatement(sqladd);
				pstmt2 = conn.prepareStatement(sqladd2); //別々で実行すかはデーターベースの癖による
			}else if(menu == 1) {
				pstmt = conn.prepareStatement(sqledit);
			}else if(menu == 2) {
				pstmt = conn.prepareStatement(sqldelete); //nameとzipは他のsql文と同じ順番だから?は今回の場合使いまわしでよい
				pstmt2 = conn.prepareStatement(sqldelete2);
			}
			//insert文の?の値をを設定
			pstmt.setString(1, field.getFieldname()); //コード量を短くすることができるけど可読性を高めるために敢えて短くしない
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
			pstmt.setString(19, field.getTime());
			pstmt.setString(20, field.getOwnerid());
			pstmt2.setString(1, "fieldint");
			pstmt2.setString(2, owner.getOwnerid());
			if(menu == 2) {pstmt.setString(3, field.getOwnerid());}

			int result = pstmt.executeUpdate(); //追加された行数が代入される、もし追加された行数が違う場合は不具合
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
