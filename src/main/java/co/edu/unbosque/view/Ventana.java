package co.edu.unbosque.view;

import javax.swing.JOptionPane;

public class Ventana {

	public Ventana() {}
	
	public int leerDato(String mensaje) {
		String dato = JOptionPane.showInputDialog(mensaje);
		return Integer.parseInt(dato);
	}

	public String leerString(String mensaje) {
		return JOptionPane.showInputDialog(mensaje);
	}
	
	public void mostrarInformacion(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	public void warningMessage(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Ten mas cuidado", JOptionPane.WARNING_MESSAGE);
	}
	
	public void errorMessage(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "No debes cometer este error.", JOptionPane.ERROR_MESSAGE);
	}
	
}
