package com.umes.presionviento.bo;

import com.umes.presionviento.constants.Categories;

public class Calculator {
	private Double ic;
	private String icDisplay;
	private Double Cez;
	private Double Qs;
	private Double Barlovento;
	private Double Sotavento;
	private int Speed;

	public Double getIC() {
		return ic;
	}
	
	public Double getPzBarlovento() {
		return this.Cez * this.Barlovento * this.Qs * this.ic;
	}
	
	public Double getPzSotavento() {
		return this.Cez * this.Sotavento * this.Qs * this.ic;
	}
	
	public void setBarlovento(Double barlovento) {
		this.Barlovento = barlovento;
	}
	public void setSotavento(Double sotavento) {
		this.Sotavento = sotavento;
	}
	
	public void setCez(Double cez) {
		this.Cez = cez;
	}

	public String getICDisplay() {
		return icDisplay;
	}
	
	public void setSpeed(Integer speed) {
		this.Speed = speed;
		this.Qs = (double) Math.round(0.0048 * (Math.pow(speed, 2)));
	}
	
	public Double getQs() {
		return this.Qs;
	}

	public void setIC(Categories cat) {
		switch (cat) {
		case IV:
			this.icDisplay = "Obras esenciales";
			this.ic = 1.15;
			break;
		case I:
			this.icDisplay = "Obras utilitarias";
			this.ic = 1.0;
			break;
		case II:
			this.icDisplay = "Obras ordinarias";
			this.ic = 1.0;
			break;
		case III:
			this.icDisplay = "Obras importantes";
			this.ic = 1.0;
			break;
		default:
			this.icDisplay = "Obras utilitarias";
			this.ic = 1.0;
		}
	}
}
