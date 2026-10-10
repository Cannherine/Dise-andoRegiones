package interfaz;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
public class Mensaje {
	  private static final Color FONDO = new Color(232, 241, 250);

	    private static final Color AZUL_OSCURO = new Color(25, 54, 91);

	    private static void mostrar(Component padre, String mensaje, String titulo,Color colorBoton) {

	        JLabel texto = new JLabel(mensaje);
	        texto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        texto.setForeground(AZUL_OSCURO);

	        JPanel panel = new JPanel();
	        panel.setBackground(FONDO);
	        panel.setBorder(new EmptyBorder(15, 20, 15, 20));
	        panel.add(texto);

	        JButton aceptar = new JButton("Aceptar");
	        aceptar.setBackground(colorBoton);
	        aceptar.setForeground(Color.WHITE);
	        aceptar.setFont(new Font("Segoe UI", Font.BOLD, 13));
	        aceptar.setFocusPainted(false);

	        JOptionPane opcion = new JOptionPane(  panel, JOptionPane.PLAIN_MESSAGE,JOptionPane.DEFAULT_OPTION, null,new Object[]{aceptar},aceptar );

	        opcion.setBackground(FONDO);

	        JDialog dialogo = opcion.createDialog(padre, titulo);
	        dialogo.setModal(true);

	        aceptar.addActionListener(e -> dialogo.dispose());

	        dialogo.setVisible(true);
	        dialogo.dispose();
	    }

	    public static void exito(Component padre, String mensaje) {
	        mostrar(padre, mensaje, "Operación exitosa",new Color(22, 163, 74));
	    }

	    public static void error(Component padre, String mensaje) {
	        mostrar(padre, mensaje, "Error", new Color(220, 38, 38));
	    }

	    public static void advertencia(Component padre, String mensaje) {
	        mostrar(padre, mensaje, "Advertencia", new Color(217, 119, 6));
	    }
}
