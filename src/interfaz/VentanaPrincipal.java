package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import java.util.Map;

import negocio.Grafo;
import negocio.Vertice;

public class VentanaPrincipal extends JFrame {

    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtPeso;
    private JMapViewer mapa;

    private Grafo grafo;
    private DefaultListModel<String> modeloCiudades;
    private JList<String> listaCiudades;
    private JComboBox<Vertice> comboCiudad1;
    private JComboBox<Vertice> comboCiudad2;
	private String tipo;



    public VentanaPrincipal(String tipo, String territorio, double latitud, double longitud) {
        this.tipo = tipo;
    	
        grafo = new Grafo();

        setTitle("Diseñando Regiones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 650);
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
        
        JLabel lblNombre = new JLabel("Nombre de " + tipo + ":");
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

        JButton botonAgregarCiudades = new JButton("Agregar Ciudad");
        panelCiudad.add(botonAgregarCiudades);

        JPanel panelLista = new JPanel();
        panelContenido.add(panelLista, BorderLayout.CENTER);
        panelLista.setLayout(new BorderLayout(5, 5));

        JLabel lblLista = new JLabel("CIUDADES CARGADAS");
        panelLista.add(lblLista, BorderLayout.NORTH);

        modeloCiudades = new DefaultListModel<>();
        listaCiudades = new JList<>(modeloCiudades);

        JScrollPane scrollCiudades = new JScrollPane(listaCiudades);
        panelLista.add(scrollCiudades, BorderLayout.CENTER);

        JPanel panelConexion = new JPanel();
        panelContenido.add(panelConexion, BorderLayout.SOUTH);
        panelConexion.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblCiudad1 = new JLabel(tipo + " 1:");
        panelConexion.add(lblCiudad1);

        comboCiudad1 = new JComboBox<>();
        panelConexion.add(comboCiudad1);

        JLabel lblCiudad2 = new JLabel(tipo + " 2:");
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

        JPanel panelResultados = new JPanel();
        panelCentral.add(panelResultados);
        panelResultados.setLayout(new BorderLayout());
        mapa = new JMapViewer();
        panelResultados.add(mapa, BorderLayout.CENTER);

        JLabel lblResultados = new JLabel("MAPA");
        panelResultados.add(lblResultados, BorderLayout.NORTH);

        botonAgregarCiudades.addActionListener(e -> {

            String nombre = txtNombre.getText().trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresá el nombre de la ciudad");
                return;
            }

            try {
                double x = Double.parseDouble(txtX.getText().trim());
                double y = Double.parseDouble(txtX.getText().trim());

                Vertice ciudad = new Vertice(nombre, x, y);
                boolean agregado = grafo.agregarVertice(ciudad);

                if (agregado) {
                    modeloCiudades.addElement(nombre);
                    comboCiudad1.addItem(ciudad);
                    comboCiudad2.addItem(ciudad);

                    JOptionPane.showMessageDialog(this, tipo + " agregada correctamente");

                    txtNombre.setText("");
                    txtX.setText("");
                    txtY.setText("");

                } else {
                    JOptionPane.showMessageDialog(this, "El/La " + tipo +  " ya existe");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Las coordenadas deben ser números decimales");
            }
        });
    }
}