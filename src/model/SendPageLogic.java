package model;

import java.util.ArrayList;
import java.util.List;

import dao.FieldStatusDao;
import dao.OwnerStatusDao;

public class SendPageLogic {

	OwnerStatusDao ownerstatus = new OwnerStatusDao(); //ownerstatusとなってるがownerとfieldの必要情報が両方混ざってるから要注意

	public List<List<Object>> sendhome(String id) {
		List<List<Object>> field = ownerstatus.sendhome(id); //ホームに遷移すると同時にオーナーの情報を取得
		return field;
	}
	public Field sendfieldstatus(String id) {
		FieldStatusDao fieldstatus = new FieldStatusDao();
		Field field = new Field();
		field = fieldstatus.findall(id);
		return field;
	}
	public Owner sendownerstatus(String id) {
		Owner owner = new Owner();
		owner = ownerstatus.findall(id);
		return owner;
	}
	public List<Object> senddelete(String ownerid, String pass1) {
		List<Object> senddelete = new ArrayList<>();

		senddelete = ownerstatus.senddelete(ownerid);
		if(pass1.equals(senddelete.get(2))) {
		return senddelete;
		}
		return null;
	}
}
