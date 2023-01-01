package model;

import dao.FieldCreateDao;

public class FieldCreateLogic {

	int menu;

	public void fieldadd(Field field) {
		FieldCreateDao fieldadd = new FieldCreateDao();
		fieldadd.fieldcreate(field, menu=0);
	}
	public void fieldedit(Field field) {
		FieldCreateDao fieldedit = new FieldCreateDao();
		fieldedit.fieldcreate(field, menu=1);
	}
	public void fielddelete(Field field) {
		FieldCreateDao fielddelete = new FieldCreateDao();
		fielddelete.fieldcreate(field, menu=2);
	}
}
