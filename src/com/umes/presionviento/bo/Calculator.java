package com.umes.presionviento.bo;

import com.umes.presionviento.constants.Categories;

public class Calculator {
	private Double ic;
	private String icDisplay;

	public Double getIC() {
		return ic;
	}

	public String getICDisplay() {
		return icDisplay;
	}

	public void setIC(Categories cat) {
		switch (cat) {
		case IV:
			this.icDisplay = "Obras esenciales";
			this.ic = 1.5;
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
