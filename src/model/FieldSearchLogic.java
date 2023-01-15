package model;

import dao.FieldSearchDao;

public class FieldSearchLogic {

	Field field = new Field();

	public Field fieldsearch(Field field) {
		FieldSearchDao fieldsearch =  new FieldSearchDao();
		field = fieldsearch.fieldsearch(field);
	}

}
