package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboTipo;
	private JTextField txtTerritorio;
	private JTextField txtLatitud;
	private JTextField txtLongitud;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaInicio() {
		
		configurarVentana();
		contentPane = new JPanel();
		contentPane.setBackground(EstiloInterfaz.FONDO);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(15, 15));

		crearPanelTitulo();

		JPanel panelCentral = crearPanelCentral();
		contentPane.add(panelCentral, BorderLayout.CENTER);

		panelCentral.add(crearDescripcion(), BorderLayout.NORTH);
		panelCentral.add(crearPanelDatos(), BorderLayout.CENTER);
		crearPanelBoton();
	}

	
	private void configurarVentana() {
		setTitle("Bienvenido - Diseñando Regiones");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 600, 420);
	    setLocationRelativeTo(null);
	    setResizable(false);		
	}

	private void crearPanelTitulo() {

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(EstiloInterfaz.AZUL_OSCURO);
		contentPane.add(panelTitulo, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("BIENVENIDO A DISEÑANDO REGIONES");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

		panelTitulo.add(lblTitulo);
	}

	private JPanel crearPanelCentral() {

		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(EstiloInterfaz.FONDO);
		panelCentral.setLayout(new BorderLayout(10, 15));

		return panelCentral;
	}

	private JLabel crearDescripcion() {
		JLabel lblDescripcion = new JLabel(
				"<html><center>"
						+ "Este programa permite construir un grafo de territorios, "
						+ "establecer conexiones con pesos de similitud "
						+ "y agruparlos en regiones utilizando el algoritmo de Kruskal."
						+ "</center></html>");

		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDescripcion.setForeground(EstiloInterfaz.AZUL_OSCURO);

		return lblDescripcion;
	}


private JPanel crearPanelDatos() {

    JPanel panelDatos = new JPanel();
    panelDatos.setBackground(EstiloInterfaz.FONDO);
    panelDatos.setLayout(new GridLayout(4, 2, 10, 15));

    Font fuenteEtiquetas = new Font("Segoe UI", Font.BOLD, 13);
    Color colorEtiquetas = EstiloInterfaz.AZUL_OSCURO;

    JLabel lblTipo = new JLabel("Tipo de territorio:");
    lblTipo.setFont(fuenteEtiquetas);
    lblTipo.setForeground(colorEtiquetas);
    panelDatos.add(lblTipo);

    comboTipo = new JComboBox<>();
    comboTipo.addItem("Continente");
    comboTipo.addItem("País");
    comboTipo.addItem("Provincia");
    comboTipo.setSelectedIndex(-1);
    panelDatos.add(comboTipo);

    JLabel lblTerritorio = new JLabel("Área de estudio:");
    lblTerritorio.setFont(fuenteEtiquetas);
    lblTerritorio.setForeground(colorEtiquetas);
    panelDatos.add(lblTerritorio);

    txtTerritorio = new JTextField();
    panelDatos.add(txtTerritorio);

    JLabel lblLatitud = new JLabel("Latitud del centro:");
    lblLatitud.setFont(fuenteEtiquetas);
    lblLatitud.setForeground(colorEtiquetas);
    panelDatos.add(lblLatitud);

    txtLatitud = new JTextField();
    panelDatos.add(txtLatitud);

    JLabel lblLongitud = new JLabel("Longitud del centro:");
    lblLongitud.setFont(fuenteEtiquetas);
    lblLongitud.setForeground(colorEtiquetas);
    panelDatos.add(lblLongitud);

    txtLongitud = new JTextField();
    panelDatos.add(txtLongitud);

    return panelDatos;
}


private void crearPanelBoton() {

	JPanel panelBoton = new JPanel();
	panelBoton.setBackground(EstiloInterfaz.FONDO);
	contentPane.add(panelBoton, BorderLayout.SOUTH);

	JButton botonContinuar = new JButton("Continuar");

	botonContinuar.setBackground(EstiloInterfaz.VERDE);
	botonContinuar.setForeground(Color.WHITE);
	botonContinuar.setFont(new Font("Segoe UI", Font.BOLD, 14));
	botonContinuar.setFocusPainted(false);

	panelBoton.add(botonContinuar);

	botonContinuar.addActionListener(e -> continuar());

	getRootPane().setDefaultButton(botonContinuar);
}

	private void continuar() {

		String tipo = (String) comboTipo.getSelectedItem();
		String territorio = txtTerritorio.getText().trim();
		String latitudTexto = txtLatitud.getText().trim();
		String longitudTexto = txtLongitud.getText().trim();

		if (tipo == null || territorio.isEmpty() || latitudTexto.isEmpty() || longitudTexto.isEmpty()) {

			Notificaciones.advertencia(this, "Debe completar todos los campos para continuar");
			return;
		}

		try {
			double latitud = Double.parseDouble(latitudTexto);
			double longitud = Double.parseDouble(longitudTexto);

			if (!Double.isFinite(latitud) || !Double.isFinite(longitud) || latitud < -90 || latitud > 90
					|| longitud < -180 || longitud > 180) {

				Notificaciones.error(this, "Coordenadas inválidas. Latitud: -90 a 90. Longitud: -180 a 180.");
				return;
			}

			VentanaPrincipal ventana = new VentanaPrincipal(tipo, latitud, longitud);
			ventana.setVisible(true);
			dispose();

		} catch (NumberFormatException ex) {

			Notificaciones.error(this, "Las coordenadas deben ser números decimales válidos");
		}
	}

}