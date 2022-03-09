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
		setBounds(100, 100, 1400, 1000);

		
		setLayout(new BorderLayout());
		
		AG = new AlgoritmoGenetico();
	
		// Panel superior
		JPanel panelSuperior = new JPanel();
		JComboBox problema = new JComboBox<>();
		String[] opciones = new String[] {"1","2","3","4 Boolean", "4 Double"};
		for (String op : opciones) {
			problema.addItem(op);
		}
		
		JLabel prob = new JLabel("Problema");
		
		// Num variables
		JLabel num = new JLabel("Número de variables");
		JTextField num_var = new JTextField();
		num_var.setPreferredSize(new Dimension(42,25));
		num.setVisible(false);
		num_var.setVisible(false);
		
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
		
		// Gráfica
		Plot2DPanel plot = new Plot2DPanel();
		
		plot.setBorder(BorderFactory.createTitledBorder("Grafica"));
		plot.setPreferredSize(new Dimension(600,600));
		
		plot.setAxisLabels("Número de generaciones","Valor de la función");
		plot.addLegend("SOUTH");
		
		//Saber de donde selecciono las generaciones etc
		//plot.addLinePlot("EVOLUCIÓN", generaciones, fitness);
		
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
		JLabel sol = new JLabel("Solución:");
		JTextField solucion = new JTextField();
		// Solución muestra: variable X1, variable X2, valor de la función. Pero esto se recibe como una String
		solucion.setText(AG.getStrSol());
		
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

		Seleccion[] tipos_seleccion = new Seleccion[] {new SeleccionPrueba(), new SeleccionPrueba()}; 
		String[] funciones = new String[] {"1","2","3","4 Boolean", "4 Double"};
		
		
		// tipos_cruce []
		// tipos_mutacion []
		
		
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
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Valor de error", 					     
						"Valor de error",       
						"errorVal",  						     
						0, 0.1))							     
			  .addOption(new StrategyOption<AlgoritmoGenetico>(  
						"Tipo de selección", 					    
						"Tipo de selección",       
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
						"Tipo de mutación", 					    
						"Tipo de mutación",       
						"mutacion",  						     
						tipos_mutacion))*/
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Probabilidad de mutación", 					     
						"Probabilidad de mutación",       
						"probMutacion",  						     
						0, 100))
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Probabilidad de élite", 					     
						"Probabilidad de élite",       
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
		
		// AG.setNumvariables
		
		// AG.setFuncion
		
		// AG.run
	}
}
