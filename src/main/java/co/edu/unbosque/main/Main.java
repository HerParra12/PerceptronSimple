package co.edu.unbosque.main;
import java.util.Arrays;

import co.edu.unbosque.model.Perceptron;
import co.edu.unbosque.view.Ventana;

public class Main {

	private Ventana view;
	private Perceptron perceptron;
	
	public Main() {
		view = new Ventana();
		funcionar();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	private void funcionar() {
		try {
			int menu = 0;
			do {
				menu = view.leerDato("PERCEPTRON SIMPLE." + 
					   "\n Selecciona una opcion a realizar." +
					   "\n 1. Entrenar con la tabla AND con valores aleatorios." +
					   "\n 2. Entrenar con la tabla AND con valores a ingresar." +
					   "\n 3. Entrenar con la tabla OR con valores aleatorios." +
					   "\n 4. Entranar con la tabla OR con valores a ingresar." + 
					   "\n 0. Salir.");
				
				switch(menu){
					case 1:
						entrenar(false, 0);
						break;
						
					case 2:
						entrenar(false, 1);
						break;
						
					case 3:
						entrenar(true, 0);
						break;
						
					case 4:
						entrenar(true, 1);
						break;
						
					case 0:
						view.mostrarInformacion("Hasta luego.");
						break;
						default:
							view.warningMessage("Ingresa una opción valida.");
							break;
				}
			}while(menu != 0);
		} catch(Exception error) {
			view.warningMessage("Hubo un error.");
			funcionar();
		}
	}
	
	
	private void entrenar(boolean autoEntrenar, int tabla) {
		if(autoEntrenar) {
			String primerDato = ingresarDato("Ingresa el valor del primer peso.");
			String segundoDato = ingresarDato("Ingresa el valor del segundo peso.");
			String valorTendencia = ingresarDato("Ingresa el valor de tendencia.");
			perceptron = new Perceptron(tabla);
			view.mostrarInformacion("Los pesos finales son: " +
			Arrays.toString(perceptron.functionActivacion(new double [] {Double.parseDouble(primerDato), Double.parseDouble(segundoDato), Double.parseDouble(valorTendencia)})));
		}else {
			perceptron = new Perceptron(tabla);
			view.mostrarInformacion("Los pesos finales son: " + perceptron.functionActivacion(perceptron.entradasAleatorias()));
		}
	}
	

	private String ingresarDato(String mensaje) {
		String variable;
		do {
			variable = view.leerString(mensaje);
			if(!numeroValido(variable))
				view.warningMessage("El numero ingresado no es valido, intenta de nuevo.");
		}while(!numeroValido(variable));
		return variable;
	}
	
	private boolean numeroValido(String numero) {
		return numero.matches("[0-9]+[.]?[0-9]*");
	}
}