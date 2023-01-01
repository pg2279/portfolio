package model;

import java.util.ArrayList;
import java.util.List;

import dao.OwnerStatusDao;

public class OwnerLoginLogic {

	public boolean ownerlogin(String id, String pass) {

		OwnerStatusDao login = new OwnerStatusDao();
		List<String> loginlist = new ArrayList<>();
		loginlist = login.login(id);
		if(id.equals(loginlist.get(0)) && pass.equals(loginlist.get(1))
				&& !id.equals(null) && !id.equals("") && !pass.equals(null) && !pass.equals("")) {return true;}

		return false;
	}
	public String selectownerid(String id) {

		OwnerStatusDao login = new OwnerStatusDao();
		String ownerid = login.selectownerid(id);

		return ownerid;
	}

}