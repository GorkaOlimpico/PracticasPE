package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;


import org.math.plot.Plot2DPanel;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.seleccion.*;
import gui.ConfigPanel.*;
import gui.ConfigPanel.ConfigListener;
import individuos.Individuo;


public class MainFrame extends JFrame {

	private AlgoritmoGenetico AG;
	private ConfigPanel<AlgoritmoGenetico> formulario;
	private JSplitPane panelCentral;
	private Individuo ind;
	private static JTextField solucion;
	private JTextField num_var;

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
	
		// Panel superior
		JPanel panelSuperior = new JPanel();
		JComboBox problema = new JComboBox<>();
		String[] opciones = Individuo.getStrings();
		for (String op : opciones) {
			problema.addItem(op);
		}
		
		JLabel prob = new JLabel("Problema");
		
		// Num variables
		JLabel num = new JLabel("N�mero de variables");
		num_var = new JTextField();
		num_var.setPreferredSize(new Dimension(42,25));
		num.setVisible(false);
		num_var.setVisible(false);
		
		ind = Individuo.seleccionarIndividuo(1, new String[] {opciones[problema.getSelectedIndex()]})[0];
		
		problema.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (problema.getSelectedIndex() == 3 || problema.getSelectedIndex() == 4) {
					num.setVisible(true);
					num_var.setVisible(true);
				}
				else {
					num.setVisible(false);
					num_var.setVisible(false);
				}
				ind = Individuo.seleccionarIndividuo(1, new String[] {opciones[problema.getSelectedIndex()]})[0];
				//TODO crear de nuevo seleccionador mutaciones y cruces
			}
		});
		
		panelSuperior.add(num);
		panelSuperior.add(num_var);
		panelSuperior.add(prob);
		panelSuperior.add(problema);
		add(panelSuperior, BorderLayout.NORTH);
		
		
		
		// Panel central
		panelCentral = new JSplitPane();
		add(panelCentral, BorderLayout.CENTER);
		
		// Gr�fica
		Plot2DPanel plot = new Plot2DPanel();
		
		plot.setBorder(BorderFactory.createTitledBorder("Grafica"));
		plot.setPreferredSize(new Dimension(600,600));
		
		plot.setAxisLabels("N�mero de generaciones","Valor de la funci�n");
		plot.addLegend("SOUTH");
		
		//Saber de donde selecciono las generaciones etc
		//plot.addLinePlot("EVOLUCI�N", generaciones, fitness);
		
		panelCentral.setRightComponent(plot);
		
		
		
		// Formulario	
		panelCentral.setLeftComponent(creaFormulario());
			
		
		
		
		
		// Panel inferior
		
		JPanel panelInferior = new JPanel();
		
		JButton btnEjecutar = new JButton("Ejecutar");
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
		JLabel sol = new JLabel("Soluci�n:");
		solucion = new JTextField();
		
		solucion.setText("Sin soluci�n");
		
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
		
		// aqu� necesito tener los arrays con los tipos de cruce, seleccion, etc...

		Seleccion[] tipos_seleccion = new Seleccion[] {new SeleccionPrueba(), new SeleccionPrueba()};
		
		
		// tipos_cruce []
		// tipos_mutacion []
		
		
		formulario = new ConfigPanel<>();
		
		// se pueden a�adir las opciones de forma independiente, o "de seguido"; el resultado es el mismo.
		formulario
			  .addOption(new IntegerOption<AlgoritmoGenetico>(  
				"Tama�o poblaci�n", 					    
				"Tama�o poblaci�n",       
				"tamPoblacion",  						    
				1, Integer.MAX_VALUE))							     
			  .addOption(new IntegerOption<AlgoritmoGenetico>(  
				"N�mero de Generaciones", 					     
				"N�mero de Generaciones",      
				"maxGen",  						     
				1, Integer.MAX_VALUE))							                
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Valor de error", 					     
						"Valor de error",       
						"errorVal",  						     
						0, 0.1))							     
			  .addOption(new StrategyOption<AlgoritmoGenetico>(  
						"Tipo de selecci�n", 					    
						"Tipo de selecci�n",       
						"seleccion",  						     
						tipos_seleccion))						
			 /* .addOption(new StrategyOption<AlgoritmoGenetico>(  
						"Tipo de cruce", 					    
						"Tipo de cruce",       
						"cruce",  						     
						tipos_cruce))*/
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Probabilidad de cruce", 					     
						"Probabilidad de cruce",       
						"probCruce",  						     
						0, 100))
			  /*.addOption(new StrategyOption<AlgoritmoGenetico>(  
						"Tipo de mutaci�n", 					    
						"Tipo de mutaci�n",       
						"mutacion",  						     
						tipos_mutacion))*/
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Probabilidad de mutaci�n", 					     
						"Probabilidad de mutaci�n",       
						"probMutacion",  						     
						0, 100))
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Probabilidad de �lite", 					     
						"Probabilidad de �lite",       
						"probElite", 						     
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
	}
	public void ejecutar() {
		AG = new AlgoritmoGenetico();
		formulario.setTarget(AG);
		formulario.initialize();
		
		int num = 
		AG.setVariables();
		
		// AG.setFuncion
		
		// AG.run
	}
	
	public static void generaGrafica(double[] mejoresGlobales, double[] mejoresGeneracion, double[] mediaGeneracion) {
		Plot2DPanel plot = new Plot2DPanel();
		double num_generaciones[] = new double[mejoresGeneracion.length];
		int i = 1;
		for(double mejor:mejoresGeneracion) {
			num_generaciones[i] = i;
			i++;
		}
		
		plot.setBorder(BorderFactory.createTitledBorder("Grafica"));
		plot.setPreferredSize(new Dimension(600,600));
		
		plot.setAxisLabels("N�mero de generaciones","Valor de la funci�n");
		plot.addLinePlot("Mejor absoluto", Color.BLUE, num_generaciones, mejoresGlobales);
		plot.addLinePlot("Mejor de la generaci�n", Color.RED, num_generaciones, mejoresGeneracion);
		plot.addLinePlot("Media de la generaci�n", Color.GREEN, num_generaciones, mediaGeneracion);
		plot.addLegend("SOUTH");
	}
	
	
	public static void setSolucion(String sol){
		solucion.setText(sol);
	}
}
