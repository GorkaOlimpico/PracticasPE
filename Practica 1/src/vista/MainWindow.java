package src.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import src.vista.ConfigPanel;
import src.vista.ConfigPanel.ChoiceOption;
import src.vista.ConfigPanel.ConfigListener;
import src.vista.ConfigPanel.DoubleOption;
import src.vista.ConfigPanel.InnerOption;
import src.vista.ConfigPanel.IntegerOption;
import src.vista.ConfigPanel.StrategyOption;
import src.vista.ConfigPanel.ComplexOption;

import src.algoritmoGenetico.AlgoritmoGenetico;
import src.algoritmoGenetico.cruces.Cruce;
import src.algoritmoGenetico.cruces.CruceGenerator;
import src.algoritmoGenetico.individuos.Individuo;
import src.algoritmoGenetico.individuos.IndividuoGenerator;
import src.algoritmoGenetico.mutacion.Mutacion;
import src.algoritmoGenetico.mutacion.MutacionGenerator;
import src.algoritmoGenetico.seleccion.Seleccion;
import src.algoritmoGenetico.seleccion.SeleccionGenerator;

public class MainWindow extends JFrame {
	
	private AlgoritmoGenetico ag;
	
	private JSplitPane centerPanel;
	
	private ConfigPanel<AlgoritmoGenetico> formulario;
	private JTextField numVariables;
	private JComboBox<String> funciones;
	private JTextField solucion;
	private JButton ejecutar;
	private Individuo ind;
	
	public MainWindow() {
		super("PE - Practica 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		ag = new AlgoritmoGenetico(this);
		
		// Barra superior
		add(crearBarraSuperior(), BorderLayout.NORTH);
		
		// Panel Central
		centerPanel = new JSplitPane();
		add(centerPanel, BorderLayout.CENTER);
		
		// Grafica
		Plot2DPanel grafica = new Plot2DPanel();
		
		grafica.setPreferredSize(new Dimension(800,800));
		grafica.setBorder(BorderFactory.createTitledBorder("Grafica"));
		grafica.setAxisLabels("Numero de generaciones","Valor de la funcion");
		
		centerPanel.setRightComponent(grafica);

		// Formulario
		//centerPanel.setLeftComponent(crearFormulario()); 
		//El formulacio se añade cuando se selecciona el individuo
		
		// Barra inferior
		add(crearBarraInferior(), BorderLayout.SOUTH);
		
		// Listener de configuracion
		formulario.addConfigListener(new ConfigListener() {
			@Override
			public void configChanged(boolean isConfigValid) {
				ejecutar.setEnabled(isConfigValid);				
			}
		});
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private JPanel crearBarraSuperior() {
		JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		barraSuperior.add(new JLabel("Problema"));
		
		funciones = new JComboBox<>();
		String[] funcionOpts = IndividuoGenerator.getStrings();
		for (String i : funcionOpts) 
			funciones.addItem(i);
		
		barraSuperior.add(funciones);
		
		numVariables = new JTextField();
		numVariables.setPreferredSize(new Dimension(50,20));
		JPanel nvariables = new JPanel();
		nvariables.add(new JLabel("Variable n: "));
		nvariables.add(numVariables);
		
		barraSuperior.add(nvariables);
		
		funciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (IndividuoGenerator.getClasses()[funciones.getSelectedIndex()].sumatorio()) {
            		nvariables.setVisible(true);
            		String[] aux = new String[1];
            		aux[0] = funcionOpts[funciones.getSelectedIndex()];
            		ind = IndividuoGenerator.factoria(aux, 1, 0).get(0);
            		centerPanel.setLeftComponent(crearFormulario());
            	}
            	else nvariables.setVisible(false);
            }
        });
		
		funciones.setSelectedIndex(0);
		
		return barraSuperior;
	}
	
	private JPanel crearBarraInferior() {
		JPanel barraInferior = new JPanel();
		barraInferior.setLayout(new BoxLayout(barraInferior, BoxLayout.PAGE_AXIS));
		
		JButton button = new JButton("Resetear");
		button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { reset(); }
	    });	
		barraInferior.add(button);
		
		barraInferior.add(Box.createRigidArea(new Dimension(125, 0)));
		barraInferior.add(new JLabel("Solucion:  "));
		
		solucion = new JTextField();
		solucion.setEditable(false);
		solucion.setPreferredSize(new Dimension(1000, 20));
		barraInferior.add(solucion);
		
		barraInferior.add(Box.createRigidArea(new Dimension(125, 0)));
		ejecutar = new JButton("Ejecutar");
		ejecutar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { ejecutar(); }
	    });	
		barraInferior.add(ejecutar);
		
		return barraInferior;
	}
	
	private Plot2DPanel crearGrafica(double[] medias, double[] mejorGeneracion, double[] mejores) {
		Plot2DPanel grafica = new Plot2DPanel();
		double[] generaciones = new double[medias.length];
		for(int i = 0; i < generaciones.length; i++)
		{
			generaciones[i] = i;
		}
		
		grafica.setPreferredSize(new Dimension(800,800));
		grafica.setBorder(BorderFactory.createTitledBorder("Grafica"));
		
		grafica.setAxisLabels("Numero de generaciones","Valor de la funcion");
		grafica.addLegend("SOUTH");
		grafica.addLinePlot("Mejor absoluto", Color.blue, generaciones, mejores);
		grafica.addLinePlot("Mejor de la generacion", Color.red, generaciones, mejorGeneracion);
		grafica.addLinePlot("Media de la generacion", Color.green, generaciones, medias);
		
		return grafica;
	}
	
	public void dataGrafica(double[] mejores, double[] medias, double[] mejorGeneracion)
	{
		centerPanel.setRightComponent(crearGrafica(medias, mejorGeneracion, mejores));
	}
	
	private ConfigPanel<AlgoritmoGenetico> crearFormulario() {
	
		Seleccion[] selecciones = SeleccionGenerator.getClasses();
		Cruce[] cruces = CruceGenerator.getClasses(ind);
		Mutacion[] mutaciones = MutacionGenerator.getClasses(ind);
		
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

	public void solucion(String s) {
		solucion.setText(s);
	}
	
	private void ejecutar() {
		ag = new AlgoritmoGenetico(this);
		formulario.setTarget(ag);
		formulario.update();
		
		try {
			if (IndividuoGenerator.getClasses()[funciones.getSelectedIndex()].sumatorio()) {
				if (numVariables.getText().trim().equals(""))
					throw new IOException();
				
				int n = Integer.parseInt(numVariables.getText());
				if (n <= 0) {
					JOptionPane.showMessageDialog(null,"La funcion requiere un numero de variables >= 0", 
							  "ERROR",JOptionPane.ERROR_MESSAGE);
				}
				ag.setNumVariables(numVariables.getText());
			}
			
			ag.setFuncion(funciones.getSelectedItem().toString());
			
			ag.run();
		}
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"El numero de variables debe ser un numero", 
					  "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null,"Campos vacios", 
					  "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void reset() {
		ag = new AlgoritmoGenetico(this);
		formulario.setTarget(ag);
		formulario.initialize();
	}
} 
