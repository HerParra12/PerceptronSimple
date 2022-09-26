package co.edu.unbosque.model;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class Perceptron {

	private List <int [][]> listaTablas = Arrays.asList(new int [][]{{0, 0, 0}, {0, 1, 0}, {1, 0, 0}, {1, 1, 1}}, 
													    new int [][]{{0, 0, 0}, {0, 1, 1}, {1, 0, 1}, {1, 1, 1}});
	private int tabla [][];
	private int maxFila;

	public Perceptron() {
		throw new TablaException("Debe elegir una tabla para trabajar");
	}

	public Perceptron(int index) {
		tabla = listaTablas.get(index);
		maxFila = tabla.length -1;
	}

	public double [] functionActivacion(double [] entrada) {
		int cantidadErrores = 0;
		System.out.println("Valores de entrada = " + Arrays.toString(entrada));
		boolean pasadas [] = inicializarPasadas();
		int index = 0;
		while(true) {
			boolean success = false;
			boolean recalculo = false;
			double valorResultante = 0;
			while(!success) {
				double sum = (entrada[0] * tabla[index][0]) + (entrada[1] * tabla[index][1]) + entrada[2];
				valorResultante = sum >= 0? 1 : 0;
				double error = tabla[index][2] - valorResultante;
				if(error == 0) {
					success = true;
					pasadas[index] = !recalculo? true : false;
				}else {
					cantidadErrores ++;
					recalcularPesos(entrada, error, index, valorResultante);
					recalculo = true;
				}
				index = index < maxFila? index +1 : 0;
				formatEntrada(entrada);
			}
			if(isSuccess(pasadas)) break;
			pasadas = index == 0? pasadasFalse(pasadas) : pasadas;
		}
		System.out.println("Pesos finales = " + Arrays.toString(entrada) + ", la cantidad de recalculos fueron = " + cantidadErrores);
		return entrada;
	}

	public static void main(String[] args) {
		Perceptron perceptron = new Perceptron(0);
		perceptron.functionActivacion(new double [] {1.6, 2.5, 4.3});
		perceptron.functionActivacion(new double [] {0.8, 2.3, 4.5});
		perceptron.functionActivacion(new double [] {2.3, 1.4, 1.9});
		perceptron.functionActivacion(new double [] {1.6, 1.7, 1.9});
		perceptron.functionActivacion(new double [] {2.1, 1.1, 1.4});
	}

	public double [] entradasAleatorias() {
		double entradas [] = new double [3];
		for(int i = 0; i < entradas.length; i++)
			entradas[i] = random();
		return entradas;
	}

	private boolean [] inicializarPasadas() {
		boolean pasadas [] = new boolean[maxFila +1];
		for(int i = 0; i < pasadas.length; i++)
			pasadas[i] = false;
		return pasadas;
	}

	private double random() {
		return Math.random()*5;
	}

	private void recalcularPesos(double entrada [], double error, int index, double valorResultante) {
		entrada[0] = entrada[0] + (error * tabla[index][0]);
		entrada[1] = entrada[1] + (error * tabla[index][1]);
		entrada[2] = entrada[2] + (error * valorResultante);
	}

	private void formatEntrada(double entrada []) {
		for(int i = 0; i < entrada.length; i++)
			entrada[i] = formatDouble(entrada[i]);
	}

	private boolean [] pasadasFalse(boolean pasadas []) {
		for(int i = 0; i < pasadas.length; i++)
			pasadas[i] = false;
		return pasadas;
	}

	private boolean isSuccess(boolean pasadas []) {
		for(int i = 0; i < pasadas.length; i++)
			if(pasadas[i] == false)
				return false;
		return true;
	}

	private double formatDouble(double peso) {
		return new BigDecimal(peso).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}	
}