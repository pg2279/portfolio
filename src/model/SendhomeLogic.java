package model;

import java.util.List;
import java.util.Map;

import dao.OwnerStatusDao;

public class SendhomeLogic {

	public Map<Integer, List<String>> sendhome(String id) {

		OwnerStatusDao sendhome = new OwnerStatusDao();
		sendhome.sendhome(id); //ホームに遷移すると同時にオーナーの情報を取得
		Map<Integer, List<String>> field = null;
		return field;
	}


}
