package gui;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;


import org.math.plot.Plot2DPanel;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.seleccion.*;
import gui.ConfigPanel.*;
import gui.ConfigPanel.ConfigListener;
import individuos.Individuo;
import individuos.IndividuoGE;
import individuos.IndividuoPG;


public class MainFrame extends JFrame {

	private AlgoritmoGenetico AG;
	private ConfigPanel<AlgoritmoGenetico> formulario;
	private static JSplitPane panelCentral;
	private Individuo ind;
	private static JTextField fitness;
	private JTextField gramatica;
	private JTextField wraps;
	private JTextField longitud;
	private JTextField profundidad;
	private JButton btnEjecutar;
	private String tipo_inicializacion;
	private boolean m6;
	
	private JComboBox multiplexor;
	private JComboBox problema;
	private JComboBox inicializacion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		setTitle("Practica 3 PG/GE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 1000);

		
		setLayout(new BorderLayout());
		
		
	
		// Panel central
		panelCentral = new JSplitPane();
		
		
	
		// Panel superior
		JPanel panelSuperior = new JPanel();
		
		JLabel mult = new JLabel("Multiplexor: ");
		multiplexor = new JComboBox<>();
		String[] multis = new String[2];
		multis[0] = "Multiplexor 4 a 1";
		multis[1] = "Multiplexor 8 a 1";
		multiplexor.addItem(multis[0]);
		multiplexor.addItem(multis[1]);
		
		JLabel tip = new JLabel("Tipo de algoritmo evolutivo: ");
		problema = new JComboBox<>();
		String[] opciones = Individuo.getStrings();
		for (String op : opciones) {
			problema.addItem(op);
		}
		
		panelSuperior.add(mult);
		panelSuperior.add(multiplexor);
		panelSuperior.add(tip);
		panelSuperior.add(problema);
		
		//__________ Programación Genética _______________
		JLabel prof = new JLabel("Profundidad: ");
		profundidad = new JTextField();
		profundidad.setPreferredSize(new Dimension(100,25));
		profundidad.setText("2");
		
		JLabel mod = new JLabel("Tipo de inicialización: ");
		inicializacion = new JComboBox<>();
		String[] modos = {"Full", "Grow", "Ramped-Half"};
		for (String m : modos) {
			inicializacion.addItem(m);
		}
		tipo_inicializacion = "Full";
		
		panelSuperior.add(prof);
		panelSuperior.add(profundidad);
		panelSuperior.add(inicializacion);
		
		prof.setVisible(false);
		profundidad.setVisible(false);
		inicializacion.setVisible(false);
		//________________________________________________
		
		
		//---------- Gramatica Evolutiva -------------------
		
		JLabel gra = new JLabel("Archivo gramática: ");
		gramatica = new JTextField();
		gramatica.setPreferredSize(new Dimension(100,25));
		gramatica.setText("gramatica1.txt");

		
		JLabel wra = new JLabel("Número de wraps: ");
		wraps = new JTextField();
		wraps.setPreferredSize(new Dimension(100,25));
		wraps.setText("3");
		
		JLabel lon = new JLabel("Longitud cromosoma: ");
		longitud = new JTextField();
		longitud.setPreferredSize(new Dimension(100,25));
		longitud.setText("15");
		
		
		panelSuperior.add(gra);
		panelSuperior.add(gramatica);
		panelSuperior.add(wra);
		panelSuperior.add(wraps);
		panelSuperior.add(lon);
		panelSuperior.add(longitud);
		
		add(panelSuperior, BorderLayout.NORTH);
		ind = new IndividuoGE();
		AG = new AlgoritmoGenetico(new Object[] {ind.getId(), Integer.parseInt(wraps.getText()), Integer.parseInt(longitud.getText()), gramatica.getText(), m6});
		
		//-----------------------------------------------------
		
		
		

		// Panel central
		//panelCentral = new JSplitPane();
		add(panelCentral, BorderLayout.CENTER);
		
		// Gráfica
		Plot2DPanel plot = new Plot2DPanel();
		
		plot.setBorder(BorderFactory.createTitledBorder("Grafica"));
		plot.setPreferredSize(new Dimension(600,600));
		
		plot.setAxisLabels("Número de generaciones","Valor de la función");
		plot.addLegend("SOUTH");
		
		//Saber de donde selecciono las generaciones etc
		//plot.addLinePlot("EVOLUCIÓN", generaciones, fitness);
		
		panelCentral.setRightComponent(plot);
		
		//Parte Izquierda del panel central


		// Formulario	
		panelCentral.setLeftComponent(creaFormulario());

		
		// Selección de tipo
			problema.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (problema.getSelectedIndex() == 1) { // Selecciona PG
						gra.setVisible(false);
						gramatica.setVisible(false);
						wra.setVisible(false);
						wraps.setVisible(false);
						lon.setVisible(false);
						longitud.setVisible(false);
						
						prof.setVisible(true);
						profundidad.setVisible(true);
						mod.setVisible(true);
						inicializacion.setVisible(true);
					}
					else {
						prof.setVisible(false);
						profundidad.setVisible(false);
						mod.setVisible(false);
						inicializacion.setVisible(false);
						
						gra.setVisible(true);
						gramatica.setVisible(true);
						wra.setVisible(true);
						wraps.setVisible(true);
						lon.setVisible(true);
						longitud.setVisible(true);
					}
					ind = Individuo.seleccionarIndividuo(1, new Object[] {opciones[problema.getSelectedIndex()]})[0]; // solo le pasa el nombre del problema
					panelCentral.setLeftComponent(creaFormulario());
				}
			});
		
		// Selección de multiplexor
			m6 = true;
			multiplexor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (multiplexor.getSelectedIndex() == 1) { // Selecciona multiplexor8
						m6 = false;
					}
					else {
						m6 = true;
					}
					
				}
			});
		
			
		// Selección de modo de inicialización PG
			inicializacion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (inicializacion.getSelectedIndex() == 1) { // Selecciona Grow
						tipo_inicializacion = "Grow";
					}
					else if (inicializacion.getSelectedIndex() == 2){ // Selecciona Ramped-Half
						tipo_inicializacion = "Ramped-Half";
					}
					else {
						tipo_inicializacion = "Full";
					}
					
				}
			});
			
		
		// Panel inferior
		
		JPanel panelInferior = new JPanel();
		
		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutar();				
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();				
			}
		});
		JLabel sol_fitness = new JLabel("Solución:");
		fitness = new JTextField();
		fitness.setPreferredSize(new Dimension(800, 25));
		fitness.setText("Sin solución");
		
		panelInferior.add(btnReset);
		panelInferior.add(sol_fitness);
		panelInferior.add(fitness);	
		panelInferior.add(btnEjecutar);		
		add(panelInferior, BorderLayout.SOUTH);
		
		formulario.addConfigListener(new ConfigListener() {
			@Override
			public void configChanged(boolean isConfigValid) {
				btnEjecutar.setEnabled(isConfigValid);				
			}
		});
	}
	
	private ConfigPanel<AlgoritmoGenetico> creaFormulario() {
		
		// aquí necesito tener los arrays con los tipos de cruce, seleccion, etc...

		Seleccion[] tipos_seleccion = Seleccion.getSelecciones();
		Cruce[] tipos_cruce = ind.getCruces();
		Mutacion[] tipos_mutacion = ind.getMutaciones();
		
		
		formulario = new ConfigPanel<>();
		
		// se pueden añadir las opciones de forma independiente, o "de seguido"; el resultado es el mismo.
		formulario
			  .addOption(new IntegerOption<AlgoritmoGenetico>(  
				"Tamaño población", 					    
				"Tamaño población",       
				"tamPoblacion",  						    
				1, Integer.MAX_VALUE))							     
			  .addOption(new IntegerOption<AlgoritmoGenetico>(  
				"Número de Generaciones", 					     
				"Número de Generaciones",      
				"maxGen",  						     
				1, Integer.MAX_VALUE))							                							     
			  .addOption(new StrategyOption<AlgoritmoGenetico>(  
						"Tipo de selección", 					    
						"Tipo de selección",       
						"seleccion",  						     
						tipos_seleccion))						
			  .addOption(new StrategyOption<AlgoritmoGenetico>(  
						"Tipo de cruce", 					    
						"Tipo de cruce",       
						"cruce",  						     
						tipos_cruce))
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Probabilidad de cruce", 					     
						"Probabilidad de cruce",       
						"probCruce",  						     
						0, 100))
			  .addOption(new StrategyOption<AlgoritmoGenetico>(  
						"Tipo de mutación", 					    
						"Tipo de mutación",       
						"mutacion",  						     
						tipos_mutacion))
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Probabilidad de mutación", 					     
						"Probabilidad de mutación",       
						"probMutacion",  						     
						0, 100))
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Élite", 					     
						"Élite",       
						"elite", 						     
						0, 100))

		  	  .endOptions();

		
		formulario.setTarget(AG);
		formulario.initialize();
		return formulario;
	}
	
	public void reset() {
		if (problema.getSelectedIndex() == 1) {
			AG = new AlgoritmoGenetico(new Object[] {ind.getId(), profundidad.getText(), tipo_inicializacion, m6});
		}
		else {
			AG = new AlgoritmoGenetico(new Object[] {ind.getId(), Integer.parseInt(wraps.getText()), Integer.parseInt(longitud.getText()), gramatica.getText(), m6}); 
		}
		formulario.setTarget(AG);
		formulario.initialize();
		btnEjecutar.setEnabled(true);
	}
	public void ejecutar() {
		
		if (problema.getSelectedIndex() == 1) {
			AG = new AlgoritmoGenetico(new Object[] {ind.getId(), Integer.parseInt(profundidad.getText()), tipo_inicializacion, m6});
		}
		else {
			
			AG = new AlgoritmoGenetico(new Object[] {ind.getId(), Integer.parseInt(wraps.getText()), Integer.parseInt(longitud.getText()), gramatica.getText(), m6});
		}
		
		
		// Todos los datos del formulario se pondrán en AG
		
		
		formulario.setTarget(AG);
		formulario.update();	
		
		// falta comprobar que los datos introducidos son correctos
	
		// Inicializar individuo si hace falta
		//ind = new IndividuoGE();
		//AG.setProblema(IndividuoPr2);
		
		AG.run();
	}
	
	public static Plot2DPanel generaGrafica(double[] mejoresGlobales, double[] mejoresGeneracion, double[] mediaGeneracion) {
		Plot2DPanel plot = new Plot2DPanel();
		double num_generaciones[] = new double[mediaGeneracion.length];
		for(int i = 0; i < mejoresGeneracion.length; i++)
			num_generaciones[i] = i;
		
		plot.setBorder(BorderFactory.createTitledBorder("Grafica"));
		plot.setPreferredSize(new Dimension(600,600));
		
		plot.setAxisLabels("Número de generaciones","Valor de la función");
		plot.addLegend("SOUTH");
		plot.addLinePlot("Mejor absoluto", Color.BLUE, num_generaciones, mejoresGlobales);
		plot.addLinePlot("Mejor de la generación", Color.RED, num_generaciones, mejoresGeneracion);
		plot.addLinePlot("Media de la generación", Color.GREEN, num_generaciones, mediaGeneracion);
		
		return plot;
	}
	
	public static void imprimeGrafica(double[] mejoresGlobales, double[] mejoresGeneracion, double[] mediaGeneracion) {
		panelCentral.setRightComponent(generaGrafica(mejoresGlobales, mejoresGeneracion, mediaGeneracion));
	}
	
	
	
	public static void setSolucion(String sol){
		fitness.setText(sol);
	}
}
