package model;

import dao.FieldStatusDao;

public class SendFieldStatusLogic {

	public Field sendfieldstatus(String id) {

		FieldStatusDao fieldstatus = new FieldStatusDao();
		Field field = new Field();
		field = fieldstatus.findall(id);

		return field;
	}

}
