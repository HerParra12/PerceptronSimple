package co.edu.unbosque.perceptron;

public class Main {

	private Perceptron perceptron;
	private Ventana view;
	
	public Main() {
		perceptron = new Perceptron();
		view = new Ventana();
		funcionar();
	}
	
	private void funcionar() {
		try {
			int menu = 0;
			do {
				menu = view.leerDato("PERCEPTRON" +
					   "\n Selecciona la opción a realizar." +
					   "\n 1. ");
				
				switch(menu) {
				case 1:
					
					break;
					
				case 0:
					view.mostrarInformacion("Hasta luego.");
					break;
					default:
						view.warningMessage("Selecciona una opción valida.");
						break;
				}
			}while(menu != 0);
		}catch(Exception error) {
			view.errorMessage("Hubo un error.");
			funcionar();
		}
		
	}
	
	
	public static void main(String[] args) {
		new Main();
	}
}
