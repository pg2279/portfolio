package model;

import java.util.List;

import dao.OwnerStatusDao;

public class SendhomeLogic {

	public List<List<Object>> sendhome(String id) {

		OwnerStatusDao sendhome = new OwnerStatusDao(); //ownerstatusとなってるがownerとfieldの必要情報が両方混ざってるから要注意
		List<List<Object>> field = sendhome.sendhome(id); //ホームに遷移すると同時にオーナーの情報を取得
		return field;
	}


}
