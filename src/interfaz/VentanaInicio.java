package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

		setTitle("Bienvenido - Diseñando Regiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 420);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(15, 15));

		JPanel panelTitulo = new JPanel();
		contentPane.add(panelTitulo, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("BIENVENIDO A DISEÑANDO REGIONES");
		panelTitulo.add(lblTitulo);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(10, 15));

		JLabel lblDescripcion = new JLabel("<html><center>" + "Este programa permite construir un grafo de territorios, "
				+ "establecer conexiones con pesos de similitud "+ "y agruparlos en regiones utilizando el algoritmo de Kruskal."
				+ "</center></html>"
				);

		panelCentral.add(lblDescripcion, BorderLayout.NORTH);

		JPanel panelDatos = new JPanel();
		panelCentral.add(panelDatos, BorderLayout.CENTER);
		panelDatos.setLayout(new GridLayout(4, 2, 10, 15));

		JLabel lblTipo = new JLabel("Tipo de territorio:");
		panelDatos.add(lblTipo);

		comboTipo = new JComboBox<>();
		comboTipo.addItem("Continente");
		comboTipo.addItem("Pais");
		comboTipo.addItem("Provincia");
		comboTipo.setSelectedIndex(-1);
		panelDatos.add(comboTipo);

		JLabel lblTerritorio = new JLabel("Área de estudio:");
		panelDatos.add(lblTerritorio);

		txtTerritorio = new JTextField();
		panelDatos.add(txtTerritorio);

		JLabel lblLatitud = new JLabel("Latitud del centro:");
		panelDatos.add(lblLatitud);

		txtLatitud = new JTextField();
		panelDatos.add(txtLatitud);

		JLabel lblLongitud = new JLabel("Longitud del centro:");
		panelDatos.add(lblLongitud);

		txtLongitud = new JTextField();
		panelDatos.add(txtLongitud);

		JPanel panelBoton = new JPanel();
		contentPane.add(panelBoton, BorderLayout.SOUTH);

		JButton botonContinuar = new JButton("Continuar");
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

			JOptionPane.showMessageDialog(this, "Debe completar todos los campos para continuar");
			return;
		}

		try {
			double latitud = Double.parseDouble(latitudTexto);
			double longitud = Double.parseDouble(longitudTexto);

			if (!Double.isFinite(latitud) || !Double.isFinite(longitud) || latitud < -90 || latitud > 90|| longitud < -180 || longitud > 180) {

				JOptionPane.showMessageDialog(this, "Ingrese coordenadas válidas. " + "Latitud: -90 a 90. " + "Longitud: -180 a 180.");
				return;
			}

			VentanaPrincipal ventana = new VentanaPrincipal(tipo, territorio, latitud, longitud);            ventana.setVisible(true);
			dispose();

		} catch (NumberFormatException ex) {

			JOptionPane.showMessageDialog(this, "Las coordenadas deben ser números decimales válidos");
		}
	}
}