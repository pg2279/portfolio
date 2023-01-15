package model;

import dao.OwnerCreateDao;

public class OwnerCreateLogic {

	OwnerCreateDao ownercreate = new OwnerCreateDao();

	//メソッドの3番目の引数はfiledintだからownerdelete以外は必要ないため適当に0にする
	public void owneradd(Owner owner) {
        ownercreate.ownercreate(owner, 0, 0);
	}
	public void owneredit(Owner owner) {
		ownercreate.ownercreate(owner, 1, 0);
	}
	public void ownerdelete(Owner owner, int fieldint) {
		ownercreate.ownercreate(owner, 2, fieldint);
	}
}
