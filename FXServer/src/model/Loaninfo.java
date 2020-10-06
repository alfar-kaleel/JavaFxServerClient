package model;

import java.io.Serializable;

public class Loaninfo implements Serializable{
	
	
	private static final long serialVersionUID = 5950169519310163575L;
	private float air;
	private float noy;
	private float la;
	
	public Loaninfo() {}
	
	public Loaninfo(float air, float noy, float la) {
		
		this.air = air;
		this.noy = noy;
		this.la = la;
	}


	public float getAir() {
		return air;
	}


	public void setAir(float air) {
		this.air = air;
	}



	public float getNoy() {
		return noy;
	}


	public void setNoy(float noy) {
		this.noy = noy;
	}



	public float getLa() {
		return la;
	}


	public void setLa(float la) {
		this.la = la;
	}
	
	
	
	
	
	

}

