package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;


import org.math.plot.Plot2DPanel;

import algoritmoGenetico.AlgoritmoGenetico;

import gui.ConfigPanel.*;
import gui.ConfigPanel.ConfigListener;


public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	private ConfigPanel<AlgoritmoGenetico> formulario;

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
		setBounds(100, 100, 865, 746);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Panel izq ------------------
		JPanel panel_izq = new JPanel();
		panel_izq.setBounds(0, 0, 246, 707);
		contentPane.add(panel_izq);
		panel_izq.setLayout(null);
		/*
		
		JLabel lblNewLabel = new JLabel("Problema:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 61, 22);
		panel_izq.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setBounds(126, 11, 103, 22);
		panel_izq.add(comboBox);
		
		JLabel lblPoblacin = new JLabel("Poblaci\u00F3n:");
		lblPoblacin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPoblacin.setBounds(10, 50, 61, 22);
		panel_izq.add(lblPoblacin);
		
		JLabel lblGeneraciones = new JLabel("Generaciones:");
		lblGeneraciones.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGeneraciones.setBounds(10, 90, 79, 22);
		panel_izq.add(lblGeneraciones);
		
		JLabel lblValorDeError = new JLabel("Valor de error:");
		lblValorDeError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblValorDeError.setBounds(10, 134, 79, 22);
		panel_izq.add(lblValorDeError);
		
		JLabel lblTipoDeSeleccion = new JLabel("Tipo de selecci\u00F3n:");
		lblTipoDeSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoDeSeleccion.setBounds(10, 179, 92, 22);
		panel_izq.add(lblTipoDeSeleccion);
		
		JLabel lblTipoDeCruce = new JLabel("Tipo de cruce:");
		lblTipoDeCruce.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoDeCruce.setBounds(10, 227, 79, 22);
		panel_izq.add(lblTipoDeCruce);
		
		JLabel lblProbCruce = new JLabel("Prob. cruce:");
		lblProbCruce.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProbCruce.setBounds(10, 277, 79, 22);
		panel_izq.add(lblProbCruce);
		
		JLabel lblTipoDeMutacin = new JLabel("Tipo de mutaci\u00F3n:");
		lblTipoDeMutacin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoDeMutacin.setBounds(10, 329, 92, 22);
		panel_izq.add(lblTipoDeMutacin);
		
		JLabel lblProbMutacin = new JLabel("Prob. mutaci\u00F3n:");
		lblProbMutacin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProbMutacin.setBounds(10, 379, 79, 22);
		panel_izq.add(lblProbMutacin);
		
		JLabel lblProbElitismo = new JLabel("Prob. elitismo:");
		lblProbElitismo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProbElitismo.setBounds(10, 425, 79, 22);
		panel_izq.add(lblProbElitismo);
		
		textField = new JTextField();
		textField.setBounds(143, 51, 86, 20);
		panel_izq.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 91, 86, 20);
		panel_izq.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 135, 86, 20);
		panel_izq.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(143, 278, 86, 20);
		panel_izq.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(143, 380, 86, 20);
		panel_izq.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(143, 426, 86, 20);
		panel_izq.add(textField_5);
		textField_5.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-12, 44, 265, 2);
		panel_izq.add(separator);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("");
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox_1.setBounds(126, 179, 103, 22);
		panel_izq.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setToolTipText("");
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox_2.setBounds(126, 227, 103, 22);
		panel_izq.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setToolTipText("");
		comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox_3.setBounds(126, 329, 103, 22);
		panel_izq.add(comboBox_3);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setBounds(13, 478, 89, 23);
		panel_izq.add(btnNewButton);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setBounds(143, 478, 89, 23);
		panel_izq.add(btnEjecutar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(249, 528, 0, -550);
		contentPane.add(separator_1);
		*/
		// ----------------------------------------
		
		// Panel solucion --------------------------------------
		JPanel panel_solucion = new JPanel();
		panel_solucion.setBorder(null);
		panel_solucion.setBounds(256, 600, 593, 107);
		contentPane.add(panel_solucion);
		
		JLabel lblNewLabel_1 = new JLabel("Soluci\u00F3n");
		panel_solucion.add(lblNewLabel_1);
		//------------------------------------------------------
		
		// Panel gráfica --------------------------------------------
		JPanel panel_grafica = new JPanel();
		panel_grafica.setBorder(null);
		panel_grafica.setBounds(249, 0, 600, 600);
		contentPane.add(panel_grafica);
		
		
		// Gráfica
		Plot2DPanel plot = new Plot2DPanel();
		
		plot.setBorder(BorderFactory.createTitledBorder("Grafica"));
		plot.setPreferredSize(new Dimension(600,600));
		
		plot.setAxisLabels("Número de generaciones","Valor de la función");
		plot.addLegend("SOUTH");
		
		//Saber de donde selecciono las generaciones etc
		//plot.addLinePlot("EVOLUCIÓN", generaciones, fitness);
		
		panel_grafica.add(plot);
		// -----------------------------------------------------------
		
		// Crear formulario
		formulario = creaFormulario();
		panel_izq.add(formulario);
		
		
		
		formulario.addConfigListener(new ConfigListener() {
			@Override
			public void configChanged(boolean isConfigValid) {
				btnEjecutar.setEnabled(isConfigValid);				
			}
		});

	}
	
	private ConfigPanel<AlgoritmoGenetico> creaFormulario() {
		
		// aquí necesito tener los arrays con los tipos de cruce, seleccion, etc...
		
		
		formulario = new ConfigPanel<AlgoritmoGenetico>();
		
		// se pueden añadir las opciones de forma independiente, o "de seguido"; el resultado es el mismo.
		formulario.addOption(new IntegerOption<AlgoritmoGenetico>(  
				"Tamaño población", 					    
				"Tamaño población",       
				"tam_pob",  						    
				1, Integer.MAX_VALUE))							     
			  .addOption(new IntegerOption<AlgoritmoGenetico>(  
				"Número de Generaciones", 					     
				"Número de Generaciones",      
				"num_max_gen",  						     
				1, Integer.MAX_VALUE))							                
			  .addOption(new DoubleOption<AlgoritmoGenetico>( 
						"Valor de error", 					     
						"Valor de error",       
						"error_val",  						     
						0, 0.1))							     
			  .addOption(new IntegerOption<AlgoritmoGenetico>(  // -- entero
						"Tamaño población", 					     // texto a usar como etiqueta del campo
						"Tamaño población",       // texto a usar como 'tooltip' cuando pasas el puntero
						"tam_pob",  						     // campo (espera que haya un getGrosor y un setGrosor)
						1, Integer.MAX_VALUE))							     // min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)  
			  .addOption(new IntegerOption<AlgoritmoGenetico>(  // -- entero
						"Tamaño población", 					     // texto a usar como etiqueta del campo
						"Tamaño población",       // texto a usar como 'tooltip' cuando pasas el puntero
						"tam_pob",  						     // campo (espera que haya un getGrosor y un setGrosor)
						1, Integer.MAX_VALUE))							     // min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)  
			  .addOption(new IntegerOption<AlgoritmoGenetico>(  // -- entero
						"Tamaño población", 					     // texto a usar como etiqueta del campo
						"Tamaño población",       // texto a usar como 'tooltip' cuando pasas el puntero
						"tam_pob",  						     // campo (espera que haya un getGrosor y un setGrosor)
						1, Integer.MAX_VALUE))							     // min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)  
			  .addOption(new DoubleOption<Figura>(   // -- doble, parecido a entero
			    "% transparencia", 					 // etiqueta
			    "transparencia del borde",           // tooltip
			    "transparencia",                     // campo
			    0, 100,							     // min y max, aplicando factor, si hay; vale usar Double.*_INFINITY) 
			    100))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
			  .addOption(new StrategyOption<Figura>( // -- eleccion de objeto configurable
				"forma",							 // etiqueta
				"forma de la figura",                // tooltip
				"forma",                             // campo
				formas))                             // elecciones (deben implementar Cloneable)
				
			  // para cada clase de objeto interno, hay que definir sus opciones entre un beginInner y un endInner 
			  .beginInner(new InnerOption<Figura,Forma>(  
			  	"circulo",							 // titulo del sub-panel
			  	"opciones del circulo",				 // tooltip asociado
			  	"forma",							 // campo
			  	Circulo.class))						 // tipo que debe de tener ese campo para que se active el sub-panel
		  		  .addInner(new DoubleOption<Forma>(
		  		     "radio", "radio del circulo", "radio", 0, Integer.MAX_VALUE))
		  		  .endInner()						 // cierra este sub-panel
		  	  // igual, pero opciones para el caso 'forma de tipo rectangulo'  
              .beginInner(new InnerOption<Figura,Forma>( 
			  	"rectangulo", "opciones del rectangulo", "forma", Rectangulo.class))
		  		  .addInner(new DoubleOption<Forma>(
		  		     "ancho", "ancho del rectangulo", "ancho", 0, Double.POSITIVE_INFINITY))
		  		  .addInner(new DoubleOption<Forma>(
		  		     "alto", "alto del rectangulo", "alto", 0, Double.POSITIVE_INFINITY))
		  		  .endInner()

			  // y por ultimo, el punto (siempre estara visible)
			  .beginInner(new InnerOption<Figura,Punto>(
			  	"coordenadas", "coordenadas de la figura", "coordenadas", Punto.class))
		  		  .addInner(new DoubleOption<Forma>(
		  		     "x", "coordenada x", "x", Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY))
		  		  .addInner(new DoubleOption<Forma>(
		  		     "y", "coordenada y", "y", Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY))
		  		  .endInner()
		  		  
			  // y ahora ya cerramos el formulario
		  	  .endOptions();
		
		return config;
		
		formulario = new ConfigPanel<>();
		
		formulario
			  //General
		      .addOption(new IntegerOption<AlgoritmoGenetico>(  
				"Poblacion", 					     
				"Numero de individuos en la poblacion",       
				"tamPoblacion",  						     
				3, Integer.MAX_VALUE))							     
			  .addOption(new IntegerOption<AlgoritmoGenetico>(	 
			    "Generaciones",							 
			    "Numero de generaciones", 					
			    "maxGeneraciones",   							
			    1, Integer.MAX_VALUE))                            
			  .addOption(new DoubleOption<AlgoritmoGenetico>(   
			    "Valor de error", 					
			    "Precision o valor de error para la discretización del intervalo",           
			    "valorError",                    
			    0, 0.1))	
			  
			  // Seleccion
			  .addOption(new StrategyOption<AlgoritmoGenetico>( 
		  		"Tipo de seleccion",							
				"Metodo de seleccion que utiliza el algoritmo",                
				"seleccion",                             
				selecciones))
			  
			  // Cruce
			  .addOption(new StrategyOption<AlgoritmoGenetico>( 
			  			"Tipo de cruce",							
						"Metodo de cruce que utiliza el algoritmo",                
						"cruce",                             
						cruces))  
			  .addOption(new DoubleOption<AlgoritmoGenetico>(   
				"% Cruce", 					
				"Porcentaje de cruce",           
				"probCruce",                    
				0, 100,							     
				100))
			  
			  //Mutacion
			  .addOption(new StrategyOption<AlgoritmoGenetico>( 
				"Tipo de mutacion",							
				"Metodo de mutacion que utiliza el algoritmo",                
				"mutacion",                             
				mutaciones))  
			  .addOption(new DoubleOption<AlgoritmoGenetico>(   
				"% Mutacion", 					
				"Porcentaje de mutacion",           
				"probMutacion",                    
				0, 100,							     
				100))	
			   
			  // Elitismo
			  .addOption(new DoubleOption<AlgoritmoGenetico>(   
				"% Elite", 					
				"Porcentaje de elitismo",           
				"elitismo",                    
				0, 100,
				100))
			  	
			  
		  	  .endOptions();
		
		formulario.setTarget(ag);
		formulario.initialize();
		
		return formulario;
	}
}
