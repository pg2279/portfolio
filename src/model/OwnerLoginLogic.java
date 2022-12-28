package model;

import java.util.ArrayList;
import java.util.List;

import dao.OwnerStatusDao;

public class OwnerLoginLogic {

	public boolean ownerlogin(String id, String pass) {

		OwnerStatusDao login = new OwnerStatusDao();
		List<String> loginlist = new ArrayList<>();
		loginlist = login.login(id);
		if(id.equals(loginlist.get(0)) && pass.equals(loginlist.get(1))) {return true;}

		return false;
	}

}