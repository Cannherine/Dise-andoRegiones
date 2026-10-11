package interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Notificaciones {
	private static final Color FONDO = EstiloInterfaz.FONDO;
	private static final Color AZUL_OSCURO = EstiloInterfaz.AZUL_OSCURO;

	private static void mostrar(Component padre, String mensaje, String titulo, Color colorBoton) {

		JLabel texto = new JLabel(mensaje);
		texto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		texto.setForeground(AZUL_OSCURO);

		JPanel panel = new JPanel();
		panel.setBackground(FONDO);
		panel.setBorder(new EmptyBorder(15, 20, 15, 20));
		panel.add(texto);

		JButton aceptar = new JButton("Aceptar");
		EstiloInterfaz.personalizarBoton(aceptar, colorBoton);

		JOptionPane opcion = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
				new Object[] { aceptar }, aceptar);

		opcion.setBackground(FONDO);

		JDialog dialogo = opcion.createDialog(padre, titulo);
		dialogo.setModal(true);

		aceptar.addActionListener(e -> dialogo.dispose());

		dialogo.setVisible(true);
		dialogo.dispose();
	}

	public static void exito(Component padre, String mensaje) {
		mostrar(padre, mensaje, "Operación exitosa", EstiloInterfaz.VERDE);
	}

	public static void error(Component padre, String mensaje) {
		mostrar(padre, mensaje, "Error", EstiloInterfaz.ROJO);
	}

	public static void advertencia(Component padre, String mensaje) {
		mostrar(padre, mensaje, "Advertencia", EstiloInterfaz.NARANJA);
	}

	public static void mostrarRegiones(Component padre, String resultado, String titulo) {

		JTextArea area = new JTextArea(resultado);
		area.setEditable(false);
		area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		area.setBackground(EstiloInterfaz.FONDO);
		area.setForeground(EstiloInterfaz.AZUL_OSCURO);
		area.setCaretPosition(0);

		JScrollPane scroll = new JScrollPane(area);
		scroll.setPreferredSize(new Dimension(400, 350));

		JPanel panel = new JPanel();
		panel.setBackground(EstiloInterfaz.FONDO);
		panel.setBorder(new EmptyBorder(15, 20, 15, 20));
		panel.add(scroll);

		JButton aceptar = new JButton("Aceptar");
		EstiloInterfaz.personalizarBoton(aceptar, EstiloInterfaz.AZUL_OSCURO);

		JOptionPane opcion = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
				new Object[] { aceptar }, aceptar);

		opcion.setBackground(EstiloInterfaz.FONDO);

		JDialog dialogo = opcion.createDialog(padre, titulo);

		aceptar.addActionListener(e -> dialogo.dispose());

		dialogo.setVisible(true);
		dialogo.dispose();
	}

}
