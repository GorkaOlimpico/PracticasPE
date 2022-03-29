package gui;

import java.awt.BorderLayout;

import java.awt.Dimension;
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
import individuos.IndividuoPr2;


public class MainFrame extends JFrame {

	private AlgoritmoGenetico AG;
	private ConfigPanel<AlgoritmoGenetico> formulario;
	private static JSplitPane panelCentral;
	private Individuo ind;
	private static JTextField solucion;
	private JTextField num_var;
	private JTextField tEspera;
	private JTextField TEL;
	private JTextField vuelos;
	private JButton btnEjecutar;

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
		
		setTitle("Practica 1 PE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 1000);

		
		setLayout(new BorderLayout());
		
		AG = new AlgoritmoGenetico();
	
		// Panel central
		panelCentral = new JSplitPane();
		
		// Panel superior
		JPanel panelSuperior = new JPanel();
		
		JLabel tEsp = new JLabel("Tiempo de espera: ");
		tEspera = new JTextField();
		tEspera.setPreferredSize(new Dimension(100,25));
		
		JLabel tel = new JLabel("TEL: ");
		TEL = new JTextField();
		TEL.setPreferredSize(new Dimension(100,25));
		
		JLabel vue = new JLabel("Vuelos: ");
		vuelos = new JTextField();
		vuelos.setPreferredSize(new Dimension(100,25));
		
		
		panelSuperior.add(tEsp);
		panelSuperior.add(tEspera);
		panelSuperior.add(tel);
		panelSuperior.add(TEL);
		panelSuperior.add(vue);
		panelSuperior.add(vuelos);
		
		add(panelSuperior, BorderLayout.NORTH);
		
		
		
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
		
		// TODO mirar si esto está bien
		ind = new IndividuoPr2();
		
		
		// Formulario	
		panelCentral.setLeftComponent(creaFormulario());
			
		
		
		
		
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
		JLabel sol = new JLabel("Solución:");
		solucion = new JTextField();
		solucion.setPreferredSize(new Dimension(800, 25));
		solucion.setText("Sin solución");
		
		panelInferior.add(btnReset);
		panelInferior.add(sol);
		panelInferior.add(solucion);	
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
		AG = new AlgoritmoGenetico();
		formulario.setTarget(AG);
		formulario.initialize();
		btnEjecutar.setEnabled(true);
	}
	public void ejecutar() {
		 AG = new AlgoritmoGenetico();
		
		// Todos los datos del formulario se pondrán en AG
		
		
		formulario.setTarget(AG);
		formulario.update();	
		
		// falta comprobar que los datos introducidos son correctos
	
		// Inicializar individuo si hace falta
		ind = new IndividuoPr2();
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
		solucion.setText(sol);
	}
}
