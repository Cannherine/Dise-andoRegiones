package interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class EstiloInterfaz {
	public static final Color FONDO = new Color(232, 241, 250);
	public static final Color AZUL_OSCURO = new Color(25, 54, 91);
	public static final Color AZUL = new Color(37, 99, 235);
	public static final Color VERDE = new Color(22, 163, 74);
	public static final Color GRIS = new Color(71, 85, 105);
	public static final Color ROJO = new Color(220, 38, 38);
	public static final Color NARANJA = new Color(217, 119, 6);
	public static final Color FONDO_LISTA = new Color(225, 237, 250);
	public static final Color TEXTO_LISTA = new Color(30, 58, 95);

	private EstiloInterfaz() {

	}

	public static void personalizarBoton(JButton boton, Color color) {
		boton.setBackground(color);
		boton.setForeground(Color.WHITE);
		boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		boton.setFocusPainted(false);
	}

	public static void personalizarSelector(JFileChooser selector) {
		selector.setDialogTitle("Seleccionar archivo del grafo");
		selector.setApproveButtonText("Cargar archivo");
		aplicarEstilo(selector);
	}

	private static void aplicarEstilo(Component componente) {

		componente.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		if (componente instanceof JPanel) {
			componente.setBackground(FONDO);
		}

		if (componente instanceof JLabel) {
			componente.setForeground(AZUL_OSCURO);
		}

		if (componente instanceof JButton) {
			personalizarBoton((JButton) componente, AZUL);
		}

		if (componente instanceof JTextField) {
			componente.setBackground(Color.WHITE);
			componente.setForeground(AZUL_OSCURO);
		}

		if (componente instanceof JList) {
			componente.setBackground(FONDO_LISTA);
			componente.setForeground(AZUL_OSCURO);
		}

		if (componente instanceof JComboBox) {
			componente.setBackground(Color.WHITE);
			componente.setForeground(AZUL_OSCURO);
		}

		if (componente instanceof JScrollPane) {
			componente.setBackground(FONDO);
			((JScrollPane) componente).getViewport() .setBackground(FONDO_LISTA);
		}

		if (componente instanceof Container) {
			for (Component hijo : ((Container) componente).getComponents()) {
				aplicarEstilo(hijo);
			}
		}
	}

}
