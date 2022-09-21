package co.edu.unbosque.perceptron;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class PerceptronFamilia {

	private final int FAMILIA [][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {1, 1, 1}, {1, 1, 1}, {1, 0, 1}, {0, 1, 1}};
	private double umbral;
	private double factorAprendizaje;
	
	public PerceptronFamilia() {
		umbral = 1;
		factorAprendizaje = 0.5;
	}


	public double [] functionActivacion(double [] entrada) {
		System.out.println("Entradas = " + Arrays.toString(entrada));
		boolean pasadas [] = {false, false, false, false, false, false, false, false};
		int recalculos = 0;
		int index = 0;
		while(true) {
			boolean success = false;
			boolean recalculo = false;
			double valorResultante = 0;
			while(!success) {
				double sum = ((entrada[0] * FAMILIA[index][0]) + (entrada[1] * FAMILIA[index][1]) + entrada[2]) - umbral;
				valorResultante = sum > 0? 1 : 0;
				double error = FAMILIA[index][2] - valorResultante;
				if(error == 0) {
					success = true;
					pasadas[index] = !recalculo? true : false;
				}else {
					recalculos ++;
					recalcularPesos(entrada, error, index, valorResultante);
					recalculo = true;
				}
				index = index < 7? index +1 : 0;
				formatEntrada(entrada);
			}
			System.out.println("Recalculos = " + recalculos);
			if(isSuccess(pasadas)) break;
			pasadas = index == 0? pasadasFalse(pasadas) : pasadas;
			break;
		}
		System.out.println("Pesos finales = " + Arrays.toString(entrada) + ", cantidad de recalculo = " + recalculos);
		return entrada;
	}

	public static void main(String[] args) {
		PerceptronFamilia perceptron = new PerceptronFamilia();
		//perceptron.functionActivacion(new double [] {1.6, 2.5, 4.3});
		//perceptron.functionActivacion(new double [] {0.8, 2.3, 4.5});
		//perceptron.functionActivacion(new double [] {2.3, 1.4, 1.9});
		//perceptron.functionActivacion(new double [] {1.6, 1.7, 1.9});
		perceptron.functionActivacion(new double [] {2.1, 1.1, 1.4});
		
	}

	public void test() {
		
	}

	public double random() {
		return Math.random()*5;
	}

	public void recalcularPesos(double entrada [], double error, int index, double valorResultante) {
		entrada[0] = entrada[0] + (factorAprendizaje * error * FAMILIA[index][0]);
		entrada[1] = entrada[1] + (factorAprendizaje * error * FAMILIA[index][1]);
		entrada[2] = entrada[2] + (factorAprendizaje * error * valorResultante);
	}

	public void formatEntrada(double entrada []) {
		for(int i = 0; i < entrada.length; i++)
			entrada[i] = formatDouble(entrada[i]);
	}

	public boolean [] pasadasFalse(boolean pasadas []) {
		for(int i = 0; i < pasadas.length; i++)
			pasadas[i] = false;
		return pasadas;
	}

	public boolean isSuccess(boolean pasadas []) {
		for(int i = 0; i < pasadas.length; i++)
			if(pasadas[i] == false)
				return false;
		return true;
	}

	public double formatDouble(double peso) {
		return new BigDecimal(peso).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}	
}