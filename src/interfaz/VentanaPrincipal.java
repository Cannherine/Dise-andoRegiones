package interfaz;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import negocio.Grafo;
import negocio.Vertice;
import java.util.Arrays;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import negocio.Arista;
import java.util.List;
import negocio.AlgoritmoKruskal;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtPeso;
	private JMapViewer mapa;

	private Grafo grafo;
	private DefaultListModel<String> modeloCiudades;
	private DefaultListModel<String> modeloConexiones;
	private JList<String> listaCiudades;
	private JComboBox<Vertice> comboCiudad1;
	private JComboBox<Vertice> comboCiudad2;
	private List<List<Vertice>> regionesGuardadas;

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

	public VentanaPrincipal(String tipo, String territorio, double latitud, double longitud) {
		grafo = new Grafo();
		final String singular;
		final String plural;
		final String terminacion;

		switch (tipo) {
		case "Continente":
			singular = "País";
			plural = "PAÍSES";
			terminacion = "CARGADOS";
			break;

		case "Pais":
			singular = "Provincia";
			plural = "PROVINCIAS";
			terminacion = "CARGADAS";
			break;

		case "Provincia":
			singular = "Localidad";
			plural = "LOCALIDADES";
			terminacion = "CARGADAS";
			break;

		default:
			singular = "Territorio";
			plural = "TERRITORIOS";
			terminacion = "CARGADOS";
			break;
		}

		setTitle("Diseñando Regiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 720);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		JPanel panelTitulo = new JPanel();
		contentPane.add(panelTitulo, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("DISEÑANDO REGIONES");
		panelTitulo.add(lblTitulo);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(1, 2, 10, 0));

		JPanel panelCarga = new JPanel();
		panelCentral.add(panelCarga);
		panelCarga.setLayout(new BorderLayout(10, 10));

		JLabel lblCarga = new JLabel("CARGA DEL GRAFO");
		panelCarga.add(lblCarga, BorderLayout.NORTH);

		JPanel panelContenido = new JPanel();
		panelCarga.add(panelContenido, BorderLayout.CENTER);
		panelContenido.setLayout(new BorderLayout(10, 10));

		JPanel panelCiudad = new JPanel();
		panelContenido.add(panelCiudad, BorderLayout.NORTH);
		panelCiudad.setLayout(new GridLayout(4, 2, 10, 10));

		JLabel lblNombre = new JLabel("Nombre de " + singular + ":");
		panelCiudad.add(lblNombre);

		txtNombre = new JTextField();
		panelCiudad.add(txtNombre);

		JLabel lblX = new JLabel("Coordenada X:");
		panelCiudad.add(lblX);

		txtX = new JTextField();
		panelCiudad.add(txtX);

		JLabel lblY = new JLabel("Coordenada Y:");
		panelCiudad.add(lblY);

		txtY = new JTextField();
		panelCiudad.add(txtY);

		panelCiudad.add(new JLabel(""));

		JButton botonAgregarCiudades = new JButton("Agregar " + singular);
		panelCiudad.add(botonAgregarCiudades);
		///////////////////////////////// cuadro vertical
		JPanel panelLista = new JPanel();
		panelContenido.add(panelLista, BorderLayout.CENTER);
		panelLista.setLayout(new GridLayout(1, 2, 10, 5));

		JPanel panelProvincias = new JPanel(new BorderLayout(5, 5));

		JLabel lblLista = new JLabel(plural + " " + terminacion);
		panelProvincias.add(lblLista, BorderLayout.NORTH);

		modeloCiudades = new DefaultListModel<>();
		listaCiudades = new JList<>(modeloCiudades);

		JScrollPane scrollCiudades = new JScrollPane(listaCiudades);
		panelProvincias.add(scrollCiudades, BorderLayout.CENTER);

		panelLista.add(panelProvincias);
		//// agregre esto para las listas

		JPanel panelConexionesLista = new JPanel();
		panelConexionesLista.setLayout(new BorderLayout(5, 5));

		JLabel lblConexiones = new JLabel("CONEXIONES Y PESOS");
		panelConexionesLista.add(lblConexiones, BorderLayout.NORTH);

		modeloConexiones = new DefaultListModel<>();

		JList<String> listaConexiones = new JList<>(modeloConexiones);

		JScrollPane scrollConexiones = new JScrollPane(listaConexiones);
		panelConexionesLista.add(scrollConexiones, BorderLayout.CENTER);

		// panelConexionesLista.setPreferredSize(
		// new java.awt.Dimension(0, 115)
		// );
		panelLista.add(panelConexionesLista);
		panelLista.add(panelConexionesLista, BorderLayout.SOUTH);

		JPanel panelConexion = new JPanel();
		panelContenido.add(panelConexion, BorderLayout.SOUTH);
		panelConexion.setLayout(new GridLayout(6, 2, 10, 5));
		JLabel lblCiudad1 = new JLabel(singular + " 1:");
		panelConexion.add(lblCiudad1);

		comboCiudad1 = new JComboBox<>();
		panelConexion.add(comboCiudad1);

		JLabel lblCiudad2 = new JLabel(singular + " 2:");
		panelConexion.add(lblCiudad2);

		comboCiudad2 = new JComboBox<>();
		panelConexion.add(comboCiudad2);

		JLabel lblPeso = new JLabel("Peso / similitud:");
		panelConexion.add(lblPeso);

		txtPeso = new JTextField();
		panelConexion.add(txtPeso);

		panelConexion.add(new JLabel(""));

		JButton botonAgregarConexion = new JButton("Agregar Conexión");
		panelConexion.add(botonAgregarConexion);
		JButton botonGenerarRegiones = new JButton("Generar Regiones");
		panelConexion.add(new JLabel(""));
		panelConexion.add(botonGenerarRegiones);
		//// para el born de la lista regiones
		JButton botonConsultarRegiones = new JButton("Consultar Regiones");
		panelConexion.add(new JLabel(""));
		panelConexion.add(botonConsultarRegiones);

		JPanel panelResultados = new JPanel();
		panelCentral.add(panelResultados);
		panelResultados.setLayout(new BorderLayout());
		mapa = new JMapViewer();
		panelResultados.add(mapa, BorderLayout.CENTER);
		mapa.setDisplayPosition(new Coordinate(latitud, longitud), 6);

		JLabel lblResultados = new JLabel("MAPA");
		panelResultados.add(lblResultados, BorderLayout.NORTH);

		botonAgregarCiudades.addActionListener(e -> {

			String nombre = txtNombre.getText().trim();

			if (nombre.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Ingresá el nombre de " + singular);
				return;
			}

			try {
				double x = Double.parseDouble(txtX.getText().trim());
				double y = Double.parseDouble(txtY.getText().trim());

				Vertice ciudad = new Vertice(nombre, x, y);
				boolean agregado = grafo.agregarVertice(ciudad);

				if (agregado) {
					modeloCiudades.addElement(nombre);
					comboCiudad1.addItem(ciudad);
					comboCiudad2.addItem(ciudad);

					MapMarkerDot marcador = new MapMarkerDot(nombre, new Coordinate(x, y));

					mapa.addMapMarker(marcador);
					mapa.setDisplayPosition(new Coordinate(x, y), 6);

					JOptionPane.showMessageDialog(this, singular + " agregado correctamente");
					txtNombre.setText("");
					txtX.setText("");
					txtY.setText("");
				} else {
					JOptionPane.showMessageDialog(this, singular + " ya existe");
				}

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Las coordenadas deben ser números decimales");
			}
		});
		botonAgregarConexion.addActionListener(e -> {

			Vertice v1 = (Vertice) comboCiudad1.getSelectedItem();
			Vertice v2 = (Vertice) comboCiudad2.getSelectedItem();

			if (v1 == null || v2 == null) {
				JOptionPane.showMessageDialog(this, "Primero tenés que agregar dos vértices");
				return;
			}

			if (v1.equals(v2)) {
				JOptionPane.showMessageDialog(this, "No podés conectar un vértice consigo mismo");
				return;
			}

			try {
				double peso = Double.parseDouble(txtPeso.getText().trim());
				Arista arista = new Arista(v1, v2, peso);

				boolean agregada = grafo.agregarArista(arista);

				if (agregada) {

					dibujarConexion(v1, v2);
					modeloConexiones.addElement(v1.getNombre() + " - " + v2.getNombre() + " | Peso: " + peso);
					JOptionPane.showMessageDialog(this, "Conexión agregada correctamente");

					txtPeso.setText("");

				} else {
					JOptionPane.showMessageDialog(this, "Esta conexión ya existe");
				}

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "El peso debe ser un numero valido ");
			}

		});
		botonGenerarRegiones.addActionListener(e -> {

			String entrada = JOptionPane.showInputDialog(this, "¿Cuántas regiones querés generar?");

			if (entrada == null) {
				return;
			}

			try {
				int k = Integer.parseInt(entrada.trim());

				List<List<Vertice>> regiones = AlgoritmoKruskal.generarRegiones(grafo, k);
				regionesGuardadas = regiones;
				colorearRegiones(regiones);
				actualizarAristasRegiones(regiones);
				StringBuilder resultado = new StringBuilder();

				for (int i = 0; i < regiones.size(); i++) {

					resultado.append("REGIÓN ").append(i + 1).append(":\n");

					for (Vertice v : regiones.get(i)) {
						resultado.append(" - ").append(v.getNombre()).append("\n");
					}

					resultado.append("\n");
				}

				JOptionPane.showMessageDialog(this, resultado.toString(), "Regiones generadas",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Ingresá un número entero para k");

			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});

		botonConsultarRegiones.addActionListener(e -> {

			if (regionesGuardadas == null) {
				JOptionPane.showMessageDialog(this, "Primero debés generar las regiones.");
				return;
			}

			StringBuilder resultado = new StringBuilder();

			for (int i = 0; i < regionesGuardadas.size(); i++) {

				resultado.append("REGIÓN ").append(i + 1).append(":\n");

				for (Vertice v : regionesGuardadas.get(i)) {
					resultado.append(" - ").append(v.getNombre()).append("\n");
				}

				resultado.append("\n");
			}

			JOptionPane.showMessageDialog(this, resultado.toString(), "Consultar Regiones",
					JOptionPane.INFORMATION_MESSAGE);
		});

	}

	private void dibujarConexion(Vertice v1, Vertice v2) {

		Coordinate punto1 = new Coordinate(v1.getX(), v1.getY());
		Coordinate punto2 = new Coordinate(v2.getX(), v2.getY());

		MapPolygonImpl linea = new MapPolygonImpl(Arrays.asList(punto1, punto2, punto2));

		mapa.addMapPolygon(linea);
		mapa.repaint();
	}

	private void actualizarAristasRegiones(List<List<Vertice>> regiones) {
		mapa.removeAllMapPolygons();
		Grafo agm = AlgoritmoKruskal.calcularAGM(grafo);
		for (Arista arista : agm.obtenerTodasAristas()) {

			Vertice v1 = arista.getVertice1();
			Vertice v2 = arista.getVertice2();
			for (List<Vertice> region : regiones) {

				if (region.contains(v1) && region.contains(v2)) {
					dibujarConexion(v1, v2);
					break;
				}
			}
		}

		mapa.repaint();
	}

	private void colorearRegiones(List<List<Vertice>> regiones) {

		Color[] colores = { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN };

		Map<Vertice, Color> colorPorVertice = new HashMap<>();

		for (int i = 0; i < regiones.size(); i++) {
			Color color = colores[i % colores.length];

			for (Vertice vertice : regiones.get(i)) {
				colorPorVertice.put(vertice, color);
			}
		}

		mapa.removeAllMapMarkers();

		for (Vertice vertice : grafo.obtenerVertices()) {
			MapMarkerDot marcador = new MapMarkerDot(vertice.getNombre(),
					new Coordinate(vertice.getX(), vertice.getY()));

			marcador.setBackColor(colorPorVertice.get(vertice));
			mapa.addMapMarker(marcador);
		}

		mapa.repaint();
	}
}