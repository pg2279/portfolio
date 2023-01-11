package model;

import java.io.Serializable;

public class Owner implements Serializable {

	private String loginid;
	private String pass1;
	private String mailaddress;
	private String ownername;
	private String ownerid;

	public Owner() {}
	public Owner(String id, String pass1, String mailaddress, String ownername, String ownerid) {
		this.loginid = id;
		this.pass1 = pass1;
		this.mailaddress = mailaddress;
		this.ownername = ownername;
		this.ownerid = ownerid;
	}

	public String getId() {return loginid;}
	public void setId(String id) {this.loginid = id;}

	public String getPass1() {return pass1;}
	public void setPass1(String pass1) {this.pass1 = pass1;}

	public String getMailaddress() {return mailaddress;}
	public void setMailaddress(String mailaddress) {this.mailaddress = mailaddress;}

	public String getOwnername() {return ownername;}
	public void setOwnername(String ownername) {this.ownername = ownername;}

	public String getOwnerid() {return ownerid;}
	public void setOwnerid(String ownerid) {this.ownerid = ownerid;}
}
