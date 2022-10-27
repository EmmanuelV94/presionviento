package com.umes.presionviento.constants;

import java.util.*;

public class Coefficients {

	Hashtable<Double, Double> expoD;
	Hashtable<Double, Double> expoC;
	Hashtable<Double, Double> expoB;

	public Coefficients() {
		this.expoD = new Hashtable<Double, Double>();
		this.expoC = new Hashtable<Double, Double>();
		this.expoB = new Hashtable<Double, Double>();

		fillD();
		fillC();
		fillB();

	}

	private void fillD() {
		expoD.put(4.5, 1.39);
		expoD.put(6.0, 1.45);
		expoD.put(7.5, 1.50);
		expoD.put(9.0, 1.54);
		expoD.put(12.0, 1.62);
		expoD.put(18.0, 1.73);
		expoD.put(24.0, 1.81);
		expoD.put(30.0, 1.88);
		expoD.put(36.0, 1.93);
		expoD.put(48.0, 2.02);
		expoD.put(60.0, 2.10);
		expoD.put(90.0, 2.23);
		expoD.put(120.0, 2.34);
	}

	private void fillC() {
		expoC.put(4.5, 1.06);
		expoC.put(6.0, 1.13);
		expoC.put(7.5, 1.19);
		expoC.put(9.0, 1.23);
		expoC.put(12.0, 1.31);
		expoC.put(18.0, 1.43);
		expoC.put(24.0, 1.53);
		expoC.put(30.0, 1.61);
		expoC.put(36.0, 1.67);
		expoC.put(48.0, 1.79);
		expoC.put(60.0, 1.87);
		expoC.put(90.0, 2.05);
		expoC.put(120.0, 2.19);
	}

	private void fillB() {
		expoB.put(4.5, 0.62);
		expoB.put(6.0, 0.67);
		expoB.put(7.5, 0.72);
		expoB.put(9.0, 0.76);
		expoB.put(12.0, 0.84);
		expoB.put(18.0, 0.95);
		expoB.put(24.0, 1.04);
		expoB.put(30.0, 1.13);
		expoB.put(36.0, 1.20);
		expoB.put(48.0, 1.31);
		expoB.put(60.0, 1.42);
		expoB.put(90.0, 1.63);
		expoB.put(120.0, 1.80);
	}

	public double GetCoefficient(Exhibitions type, double high) {
		try {
			switch (type) {
				case D: {
					return expoD.get(high);
				}
				case B: {
					return expoB.get(high);
				}
				case C: {
					return expoC.get(high);
				}
				default:
					return 0.00;
			}
		} catch (Exception e) {
			return 0.00;
		}
		

	}

}
