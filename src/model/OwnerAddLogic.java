package model;

import dao.OwnerAddDao;

public class OwnerAddLogic {

	public void owneradd(Owner owner) {

		OwnerAddDao owneradd = new OwnerAddDao();
        owneradd.owneradd(owner);
		
	}

}
