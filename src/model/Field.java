package model;

import java.io.Serializable;

public class Field implements Serializable {

	private String fieldname;
	private String zip;
	private String tell;
	private String moyorieki;
	private String ekikara;
	private String kousokuic;
	private String rental;
	private String sougebuss;
	private String buss;
	private String bussteikara;
	private String yagai;
	private String sinrin;
	private String indoor;
	private String teireikai;
	private String teikyubi;
	private String ameyagai;
	private String bikou;
	private String comment;
	private String ownerid;
	private String time;

	public Field() {}
	public Field(String fieldname, String zipString, String tell, String moyori, String ekikara, String kousokuic,
			String rental, String sougebuss, String buss, String bussteikara, String yagai, String sinrin,
			String indoor, String teireikai, String teikyubi, String ameyagai, String bikou, String comment,
			String ownerid, String time) {}

	public String getFieldname() {return fieldname;}
	public void setFieldname(String fieldname) {this.fieldname = fieldname;}

	public String getZip() {return zip;}
	public void setZip(String zip) {this.zip = zip;}

	public String getTell() {return tell;}
	public void setTell(String tell) {this.tell = tell;}

	public String getMoyorieki() {return moyorieki;}
	public void setMoyorieki(String moyorieki) {this.moyorieki = moyorieki;}

	public String getEkikara() {return ekikara;}
	public void setEkikara(String ekikara) {this.ekikara = ekikara;}

	public String getKousokuic() {return kousokuic;}
	public void setKousokuic(String kousokuic) {this.kousokuic = kousokuic;}

	public String getRental() {return rental;}
	public void setRental(String rental) {this.rental = rental;}

	public String getSougebuss() {return sougebuss;}
	public void setSougebuss(String sougebuss) {this.sougebuss = sougebuss;}

	public String getBuss() {return buss;}
	public void setBuss(String buss) {this.buss = buss;}

	public String getBussteikara() {return bussteikara;}
	public void setBussteikara(String bussteikara) {this.bussteikara = bussteikara;}

	public String getYagai() {return yagai;}
	public void setYagai(String yagai) {this.yagai = yagai;}

	public String getSinrin() {return sinrin;}
	public void setSinrin(String sinrin) {this.sinrin = sinrin;}

	public String getIndoor() {return indoor;}
	public void setIndoor(String indoor) {this.indoor = indoor;}

	public String getTeireikai() {return teireikai;}
	public void setTeireikai(String teireikai) {this.teireikai = teireikai;}

	public String getTeikyubi() {return teikyubi;}
	public void setTeikyubi(String teikyubi) {this.teikyubi = teikyubi;}

	public String getAmeyagai() {return ameyagai;}
	public void setAmeyagai(String ameyagai) {this.ameyagai = ameyagai;}

	public String getBikou() {return bikou;}
	public void setBiou(String bikou) {this.bikou = bikou;}

	public String getComment() {return comment;}
	public void setComment(String comment) {this.comment = comment;}

	public String getOwnerid() {return ownerid;}
	public void setOwnerid(String ownerid) {this.ownerid = ownerid;}

	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}
}

