package model;

public class RegexCheck {

	String loginidregex = "[a-zA-Z0-9//._-]{6,20}";
	String mailregex = ".*@.*"; //mailチェックで使いまわし
	String pass1regex = "^(?=.*[a-zA-Z0-9]).*${8,20}";
	String nameregex = ".*{1,50}";

	public boolean ownercreate(String id, String pass1, String mailaddress, String ownername){

		if ((id.matches(loginidregex) || id.matches(mailregex)) && pass1.matches(pass1regex) &&
				mailaddress.matches(mailregex) && ownername.matches(nameregex)) {return true;}
		return false;
	}
}