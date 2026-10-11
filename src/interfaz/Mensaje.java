package interfaz;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Font;
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

public static void personalizarSelector(JFileChooser selector) {

    selector.setDialogTitle("Seleccionar archivo del grafo");
    selector.setApproveButtonText("Cargar archivo");

    aplicarEstilo(selector);
}

private static void aplicarEstilo(Component componente) {

    Color fondo = new Color(232, 241, 250);
    Color azulOscuro = new Color(25, 54, 91);

    componente.setFont(new Font("Segoe UI", Font.PLAIN, 13));

    if (componente instanceof JPanel) {
        componente.setBackground(fondo);
    }

    if (componente instanceof JLabel) {
        componente.setForeground(azulOscuro);
    }

    if (componente instanceof JButton) {
        JButton boton = (JButton) componente;
        boton.setBackground(new Color(37, 99, 235));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
    }

    if (componente instanceof JTextField) {
        componente.setBackground(Color.WHITE);
        componente.setForeground(azulOscuro);
    }

    if (componente instanceof JList) {
        componente.setBackground(new Color(225, 237, 250));
        componente.setForeground(azulOscuro);
    }

    if (componente instanceof JComboBox) {
        componente.setBackground(Color.WHITE);
        componente.setForeground(azulOscuro);
    }

    if (componente instanceof JScrollPane) {
        componente.setBackground(fondo);
        ((JScrollPane) componente).getViewport()
            .setBackground(new Color(225, 237, 250));
    }

    if (componente instanceof Container) {
        for (Component hijo :
                ((Container) componente).getComponents()) {
            aplicarEstilo(hijo);
        }
    }
}

public static void mostrarRegiones( Component padre, String resultado, String titulo) {

    JTextArea area = new JTextArea(resultado);
    area.setEditable(false);
    area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    area.setBackground(new Color(232, 241, 250));
    area.setForeground(new Color(25, 54, 91));
    area.setCaretPosition(0);

    JScrollPane scroll = new JScrollPane(area);
    scroll.setPreferredSize(new Dimension(400, 350));

    JOptionPane.showMessageDialog(padre,scroll, titulo,JOptionPane.PLAIN_MESSAGE);
}
public static void personalizarBotonCargar(JButton boton) {

    boton.setBackground(new Color(25, 54, 91));
    boton.setForeground(Color.WHITE);
    boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
    boton.setFocusPainted(false);
}
public static void personalizarBoton(JButton boton, Color color) {

    boton.setBackground(color);
    boton.setForeground(Color.WHITE);
    boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
    boton.setFocusPainted(false);
}

}
