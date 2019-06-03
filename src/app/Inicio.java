/**
 * MODELADO Y SIMULACIÓN
 * 
 * @category Método de Euler
 * @teacher Fernando Acero
 * @author tomasmalio 
 * @email tomasmalio@gmail.com
 * @year 2019
 * 
 *******************************************************************
 * XChart: 
 * 		https://knowm.org/open-source/xchart/xchart-example-code/
 * 		https://github.com/knowm/XChart
 * 
 * MathParser:
 * 		http://mathparser.org/
 * 
 */
package app;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import controllers.Euler;
import controllers.EulerMejorado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JFrame
{
	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;
	private  JPanel contentPane;
	
	public Inicio () {
		super();
		components();
		mostrar();
	}
	
	private void mostrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationByPlatform(true);
		setVisible(true);
    }
	
	private  void components() {
		contentPane = new JPanel();
		setContentPane(contentPane);	
		setTitle("MODELADO Y SIMULACIÓN");
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(220, 400));
		
		
		getContentPane().add(panel, BorderLayout.WEST);
		
		JLabel lbl = new JLabel("Seleccionar el método");
		lbl.setAlignmentX(Container.LEFT_ALIGNMENT);
	    lbl.setVisible(true);
	    panel.add(lbl);
	    String[] choices = { "Método de Euler","Método de Euler Mejorado"};
	    final JComboBox<String> cb = new JComboBox<String>(choices);
	    cb.setVisible(true);
	    panel.add(cb);
	    
		JLabel labelFunction = new JLabel("f(x,t)=");
		labelFunction.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(labelFunction);
		
		JTextField textFieldFunction = new JTextField(12);
		textFieldFunction.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(textFieldFunction);
		
		JLabel labelA = new JLabel("a=");
		labelA.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(labelA);
		
		JTextField textFieldA = new JTextField(14);
		textFieldA.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(textFieldA);
		
		JLabel labelB = new JLabel("b=");
		labelB.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(labelB);
		
		JTextField textFieldB = new JTextField(14);
		textFieldB.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(textFieldB);
		
		JLabel labelXcero = new JLabel("Xo=");
		labelXcero.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(labelXcero);
		
		JTextField textFieldXcero = new JTextField(13);
		textFieldXcero.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(textFieldXcero);
		
		JLabel labelN = new JLabel("n=");
		labelN.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(labelN);
		
		JTextField textFieldN = new JTextField(14);
		textFieldN.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(textFieldN);
		
		JLabel labelT = new JLabel("t=");
		labelT.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(labelT);
		
		JTextField textFieldT = new JTextField(14);
		textFieldT.setAlignmentX(Container.LEFT_ALIGNMENT);
		panel.add(textFieldT);
		
		JButton button = new JButton("Generar Modelo");
		panel.add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				// Seleccion del tipo método a utilizar
				int model = cb.getSelectedIndex();
				model++;
				System.out.println("Seleccion"+model);
				String function 	= textFieldFunction.getText();
				double varA 		= Double.parseDouble(textFieldA.getText());
				double varB 		= Double.parseDouble(textFieldB.getText());
				double varXcero 	= Double.parseDouble(textFieldXcero.getText());
				int varN 			= Integer.parseInt(textFieldN.getText());
				int varT 			= Integer.parseInt(textFieldT.getText());
				
				/**
				 * Vamos a generar el gráfico correspondiente 
				 * para Euler y Euler Mejorado
				 * 
				 * 1) Euler
				 * 2) Euler Mejorado
				 */
				XYChart chart;
				if (model == 1) {
					Euler euler = new Euler(function, varA, varB, varXcero, varN, varT);
					chart = euler.initGui();
				} else {
					EulerMejorado eulerMejorado = new EulerMejorado(function, varA, varB, varXcero, varN, varT);
					chart = eulerMejorado.initGui();
				}
				/**
				 * Al finalizar obtenemos el chart y estamos listos 
				 * para ejecutar la construcción del gráfico
				 */
				Thread t = new Thread(new Runnable() {
				    @Override
				    public void run() {
				    	new SwingWrapper<XYChart>(chart).displayChart().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);;
				    }
				   });
				t.start();
            }
        } );
	} 
	
	
	public void cerrarVentana() {
		dispose();
		System.exit(0);
	}
	
	public static void main(String[] args) 
	{
    	Inicio app = new Inicio();
	}

}
