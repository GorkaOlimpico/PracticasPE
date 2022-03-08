package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.*;

import javax.swing.*;


import org.math.plot.Plot2DPanel;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.seleccion.*;
import algoritmoGenetico.*;
import gui.ConfigPanel.*;
import gui.ConfigPanel.ConfigListener;


public class MainFrame extends JFrame {

	private AlgoritmoGenetico AG;
	private ConfigPanel<AlgoritmoGenetico> formulario;
	private JSplitPane panelCentral;

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
		//setBounds(100, 100, 1060, 994);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		//contentPane.setLayout(new BorderLayout());
		
		setLayout(new BorderLayout());
		
		AG = new AlgoritmoGenetico();
		
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
		
		
		JButton btnEjecutar = new JButton("Ejecutar");
		//btnEjecutar.setBounds(143, 478, 89, 23);
		add(btnEjecutar, BorderLayout.SOUTH);
		
		// Formulario
	
		panelCentral.setLeftComponent(creaFormulario());
			
		
		formulario.addConfigListener(new ConfigListener() {
			@Override
			public void configChanged(boolean isConfigValid) {
				btnEjecutar.setEnabled(isConfigValid);				
			}
		});

	}
	
	private ConfigPanel<AlgoritmoGenetico> creaFormulario() {
		
		// aqu� necesito tener los arrays con los tipos de cruce, seleccion, etc...

		Seleccion[] tipos_seleccion = new Seleccion[] {new SeleccionPrueba()}; 
		// tipos_cruce []
		// tipos_mutacion []
		
		
		formulario = new ConfigPanel<>();
		
		// se pueden a�adir las opciones de forma independiente, o "de seguido"; el resultado es el mismo.
		formulario
			  .addOption(new IntegerOption<AlgoritmoGenetico>(  
				"Tama�o poblaci�n", 					    
				"Tama�o poblaci�n",       
				"tam_pob",  						    
				1, Integer.MAX_VALUE))							     
			  .addOption(new IntegerOption<AlgoritmoGenetico>(  
				"N�mero de Generaciones", 					     
				"N�mero de Generaciones",      
				"num_max_gen",  						     
				1, Integer.MAX_VALUE))							                
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Valor de error", 					     
						"Valor de error",       
						"error_val",  						     
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
						"prob_cruce",  						     
						0, 100))
			  /*.addOption(new StrategyOption<AlgoritmoGenetico>(  
						"Tipo de mutaci�n", 					    
						"Tipo de mutaci�n",       
						"mutacion",  						     
						tipos_mutacion))*/
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Probabilidad de mutaci�n", 					     
						"Probabilidad de mutaci�n",       
						"prob_mutacion",  						     
						0, 100))
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Probabilidad de elitismo", 					     
						"Probabilidad de elitismo",       
						"prob_elitismo", 						     
						0, 100))

		  	  .endOptions();
		
		formulario.setTarget(AG);
		formulario.initialize();
		return formulario;
	}
}
