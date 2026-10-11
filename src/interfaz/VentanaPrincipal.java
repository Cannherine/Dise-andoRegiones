package interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import archivo.LectorGrafo;
import negocio.Arista;
import java.util.List;
import negocio.AlgoritmoKruskal;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import java.io.IOException;
import java.io.File;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtPeso;
	private JMapViewer mapa;
	private Integer cantidadRegionesGuardadas;
	private Grafo grafo;
	private DefaultListModel<String> modeloCiudades;
	private DefaultListModel<String> modeloConexiones;
	private JList<String> listaCiudades;
	private JComboBox<Vertice> comboCiudad1;
	private JComboBox<Vertice> comboCiudad2;
	private List<List<Vertice>> regionesGuardadas;

	public VentanaPrincipal(String tipo, double latitud, double longitud) {
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
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(232, 241, 250));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(25, 54, 91));
		contentPane.add(panelTitulo, BorderLayout.NORTH);
		JLabel lblTitulo = new JLabel("DISEÑANDO REGIONES");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		panelTitulo.add(lblTitulo);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(1, 2, 10, 0));

		JPanel panelCarga = new JPanel();
		panelCentral.add(panelCarga);
		panelCarga.setLayout(new BorderLayout(10, 10));

		JLabel lblCarga = new JLabel("CARGA DEL GRAFO");
		lblCarga.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCarga.setForeground(new Color(25, 54, 91));
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

		JLabel lblX = new JLabel("Coordenada X (Latitud):");
		panelCiudad.add(lblX);

		txtX = new JTextField();
		panelCiudad.add(txtX);

		JLabel lblY = new JLabel("Coordenada Y (Longitud):");
		panelCiudad.add(lblY);

		txtY = new JTextField();
		panelCiudad.add(txtY);

		panelCiudad.add(new JLabel(""));

		JButton botonAgregarCiudades = new JButton("Agregar " + singular);
		panelCiudad.add(botonAgregarCiudades);
		JPanel panelLista = new JPanel();
		panelContenido.add(panelLista, BorderLayout.CENTER);
		panelLista.setLayout(new GridLayout(1, 2, 10, 5));

		JPanel panelProvincias = new JPanel(new BorderLayout(5, 5));

		JLabel lblLista = new JLabel(plural + " " + terminacion);
		lblLista.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblLista.setForeground(new Color(25, 54, 91));
		panelProvincias.add(lblLista, BorderLayout.NORTH);

		modeloCiudades = new DefaultListModel<>();
		listaCiudades = new JList<>(modeloCiudades);
		listaCiudades.setBackground(new Color(225, 237, 250));
		listaCiudades.setForeground(new Color(30, 58, 95));
		listaCiudades.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JScrollPane scrollCiudades = new JScrollPane(listaCiudades);
		panelProvincias.add(scrollCiudades, BorderLayout.CENTER);

		panelLista.add(panelProvincias);

		JPanel panelConexionesLista = new JPanel();
		panelConexionesLista.setLayout(new BorderLayout(5, 5));

		JLabel lblConexiones = new JLabel("CONEXIONES Y PESOS");
		lblConexiones.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblConexiones.setForeground(new Color(25, 54, 91));
		panelConexionesLista.add(lblConexiones, BorderLayout.NORTH);

		modeloConexiones = new DefaultListModel<>();

		JList<String> listaConexiones = new JList<>(modeloConexiones);
		listaConexiones.setBackground(new Color(225, 237, 250));
		listaConexiones.setForeground(new Color(30, 58, 95));
		listaConexiones.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JScrollPane scrollConexiones = new JScrollPane(listaConexiones);
		panelConexionesLista.add(scrollConexiones, BorderLayout.CENTER);

		panelLista.add(panelConexionesLista);

		JPanel panelConexion = new JPanel();
		panelContenido.add(panelConexion, BorderLayout.SOUTH);
		panelConexion.setLayout(new GridLayout(7, 2, 10, 5));
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
		JButton botonConsultarRegiones = new JButton("Consultar Regiones");
		panelConexion.add(new JLabel(""));
		panelConexion.add(botonConsultarRegiones);
		JButton botonCargarArchivo = new JButton("Cargar archivo");
		panelConexion.add(new JLabel(""));
		panelConexion.add(botonCargarArchivo);

		EstiloInterfaz.personalizarBoton(botonAgregarCiudades, EstiloInterfaz.AZUL);
		EstiloInterfaz.personalizarBoton(botonAgregarConexion, EstiloInterfaz.AZUL);
		EstiloInterfaz.personalizarBoton(botonGenerarRegiones, EstiloInterfaz.VERDE);
		EstiloInterfaz.personalizarBoton(botonConsultarRegiones, EstiloInterfaz.GRIS);
		EstiloInterfaz.personalizarBoton(botonCargarArchivo, EstiloInterfaz.AZUL_OSCURO);

		JPanel panelResultados = new JPanel();
		panelCentral.add(panelResultados);
		panelResultados.setLayout(new BorderLayout());
		mapa = new JMapViewer();
		panelResultados.add(mapa, BorderLayout.CENTER);
		mapa.setDisplayPosition(new Coordinate(latitud, longitud), 6);

		JLabel lblResultados = new JLabel("MAPA");
		lblResultados.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblResultados.setForeground(new Color(25, 54, 91));
		panelResultados.add(lblResultados, BorderLayout.NORTH);

		Color fondo = new Color(232, 241, 250);

		contentPane.setBackground(fondo);
		panelCentral.setBackground(fondo);
		panelCarga.setBackground(fondo);
		panelContenido.setBackground(fondo);

		panelCiudad.setBackground(fondo);
		panelConexion.setBackground(fondo);

		panelLista.setBackground(fondo);
		panelProvincias.setBackground(fondo);
		panelConexionesLista.setBackground(fondo);

		panelResultados.setBackground(fondo);

		botonAgregarCiudades.addActionListener(e -> agregarLugar(singular));

		botonAgregarConexion.addActionListener(e -> agregarConexion());
		botonGenerarRegiones.addActionListener(e -> generarRegiones());
		botonConsultarRegiones.addActionListener(e -> consultarRegiones());
		botonCargarArchivo.addActionListener(e -> cargarArchivo());
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

		Map<Vertice, Color> colorPorVertice = new HashMap<>();
		int cantidadRegiones = regiones.size();

		for (int i = 0; i < cantidadRegiones; i++) {

			Color color = Color.getHSBColor((float) i / cantidadRegiones, 0.8f, 0.9f);

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

	private String formatearRegiones(List<List<Vertice>> regiones) {

		StringBuilder resultado = new StringBuilder();

		for (int i = 0; i < regiones.size(); i++) {

			resultado.append("REGIÓN ").append(i + 1).append(":\n");

			for (Vertice vertice : regiones.get(i)) {
				resultado.append(" - ").append(vertice.getNombre()).append("\n");
			}

			resultado.append("\n");
		}

		return resultado.toString();
	}

	private void agregarConexion() {

		Vertice v1 = (Vertice) comboCiudad1.getSelectedItem();
		Vertice v2 = (Vertice) comboCiudad2.getSelectedItem();

		if (v1 == null || v2 == null) {
			Notificaciones.advertencia(this, "Primero tenés que agregar dos vértices");
			return;
		}

		if (v1.equals(v2)) {
			Notificaciones.advertencia(this, "No podés conectar un vértice consigo mismo");
			return;
		}

		try {
			double peso = Double.parseDouble(txtPeso.getText().trim());

			Arista arista = new Arista(v1, v2, peso);
			boolean agregada = grafo.agregarArista(arista);

			if (agregada) {
				regionesGuardadas = null;
				dibujarConexion(v1, v2);
				recalcularRegiones();
				modeloConexiones.addElement(v1.getNombre() + " - " + v2.getNombre() + " | Peso: " + peso);

				Notificaciones.exito(this, "Conexión agregada correctamente");

				txtPeso.setText("");

			} else {
				Notificaciones.advertencia(this, "Esta conexión ya existe");
			}

		} catch (NumberFormatException ex) {
			Notificaciones.error(this, "El peso debe ser un número válido");
		}
	}

	private void agregarLugar(String singular) {

		String nombre = txtNombre.getText().trim();

		if (nombre.isEmpty()) {
			Notificaciones.advertencia(this, "Ingresá el nombre de " + singular);
			return;
		}

		try {
			double x = Double.parseDouble(txtX.getText().trim());
			double y = Double.parseDouble(txtY.getText().trim());
			if (!Double.isFinite(x) || (x < -90 || x > 90)) {
				Notificaciones.error(this, "La latitud debe estar entre -90 y 90");
				return;
			}

			if (!Double.isFinite(y) || (y < -180 || y > 180)) {
				Notificaciones.error(this, "La longitud debe estar entre -180 y 180");
				return;
			}

			Vertice ciudad = new Vertice(nombre, x, y);
			boolean agregado = grafo.agregarVertice(ciudad);

			if (agregado) {
				regionesGuardadas = null;
				modeloCiudades.addElement(nombre);
				comboCiudad1.addItem(ciudad);
				comboCiudad2.addItem(ciudad);

				MapMarkerDot marcador = new MapMarkerDot(nombre, new Coordinate(x, y));

				mapa.addMapMarker(marcador);
				mapa.setDisplayPosition(new Coordinate(x, y), 6);
				recalcularRegiones();
				Notificaciones.exito(this, singular + " agregado correctamente");

				txtNombre.setText("");
				txtX.setText("");
				txtY.setText("");

			} else {
				Notificaciones.advertencia(this, singular + " ya existe");
			}

		} catch (NumberFormatException ex) {
			Notificaciones.error(this, "Las coordenadas deben ser números decimales");
		}
	}

	private void generarRegiones() {

		String entrada = JOptionPane.showInputDialog(this, "¿Cuántas regiones querés generar?");
		if (entrada == null)
			return;

		try {
			int k = Integer.parseInt(entrada.trim());

			List<List<Vertice>> regiones = AlgoritmoKruskal.generarRegiones(grafo, k);

			regionesGuardadas = regiones;
			cantidadRegionesGuardadas = k;

			colorearRegiones(regiones);
			actualizarAristasRegiones(regiones);

			String resultado = formatearRegiones(regiones);

			Notificaciones.mostrarRegiones(this, resultado, "Regiones generadas");

		} catch (NumberFormatException ex) {
			Notificaciones.error(this, "Ingresá un número entero para k");

		} catch (IllegalArgumentException ex) {
			Notificaciones.error(this, ex.getMessage());
		}
	}

	private void consultarRegiones() {

		if (regionesGuardadas == null) {
			Notificaciones.advertencia(this, "Primero debés generar las regiones.");
			return;
		}

		String resultado = formatearRegiones(regionesGuardadas);

		Notificaciones.mostrarRegiones(this, resultado, "Consultar Regiones");
	}

	private void recalcularRegiones() {

		if (cantidadRegionesGuardadas == null) {
			return;
		}

		try {
			List<List<Vertice>> regiones = AlgoritmoKruskal.generarRegiones(grafo, cantidadRegionesGuardadas);

			regionesGuardadas = regiones;

			colorearRegiones(regiones);
			actualizarAristasRegiones(regiones);

		} catch (IllegalArgumentException ex) {
			regionesGuardadas = null;
			Notificaciones.advertencia(this, "No se pudieron recalcular las regiones: " + ex.getMessage());
		}
	}

	private void cargarArchivo() {

		JFileChooser selector = new JFileChooser(new File(System.getProperty("user.dir")));

		selector.setDialogTitle("Seleccionar archivo del grafo");
		selector.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));

		EstiloInterfaz.personalizarSelector(selector);

		if (selector.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File archivo = selector.getSelectedFile();

		try {
			Grafo grafoCargado = LectorGrafo.cargarDesdeArchivo(archivo);

			grafo = grafoCargado;
			regionesGuardadas = null;
			cantidadRegionesGuardadas = null;
			actualizarVistaGrafo();

			Notificaciones.exito(this, "Archivo cargado correctamente.");

		} catch (IOException ex) {
			Notificaciones.error(this, "Error al cargar el archivo:\n" + ex.getMessage());
		}
	}

	private void actualizarVistaGrafo() {

		modeloCiudades.clear();
		modeloConexiones.clear();

		comboCiudad1.removeAllItems();
		comboCiudad2.removeAllItems();

		mapa.removeAllMapMarkers();
		mapa.removeAllMapPolygons();

		for (Vertice vertice : grafo.obtenerVertices()) {

			modeloCiudades.addElement(vertice.getNombre());

			comboCiudad1.addItem(vertice);
			comboCiudad2.addItem(vertice);

			MapMarkerDot marcador = new MapMarkerDot(vertice.getNombre(),
					new Coordinate(vertice.getX(), vertice.getY()));

			mapa.addMapMarker(marcador);
		}
		for (Arista arista : grafo.obtenerTodasAristas()) {

			Vertice v1 = arista.getVertice1();
			Vertice v2 = arista.getVertice2();

			modeloConexiones.addElement(v1.getNombre() + " - " + v2.getNombre() + " | Peso: " + arista.getPeso());

			dibujarConexion(v1, v2);
		}
		mapa.setDisplayToFitMapMarkers();
		mapa.repaint();
	}
}
