package model;

import dao.FieldCreateDao;

public class FieldCreateLogic {

	public void fieldadd(Field field) {

		FieldCreateDao fieldcreate = new FieldCreateDao();
		fieldcreate.fieldcreate(field);
	}

}
