package controllers;

import java.awt.Color;
import java.util.Vector;

import org.knowm.xchart.*;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.mariuszgromada.math.mxparser.*;

public class EulerMejorado {

	private String fx;
	private double a;
	private double b;
	private double x0;
	private int n; 
	private int t;
	
	public EulerMejorado (String function, double varA, double varB, double varXo, int varN, int varT) {
		fx = "f(x,t)=" + function;
		a = varA;
		b = varB;
		x0 = varXo;
		n = varN; 
		t = varT;
	}
	public XYChart initGui () {
		
		try {
			/**
			 * Separación entre los puntos
			 */
			double h = (b - a) / n;
			
			System.out.print("Valor de h");
			System.out.println(h);
			System.out.println("==================================");
			
			/**
			 * Construimos dos array del formato double
			 * donde vamos almacenar los puntos x,y 
			 */
			double[] xData = new double[n+1];
			double[] yData = new double[n+1];
			
			/**
			 * Función definida
			 */
			Function function = new Function("f(x,t)=sin(x^2)+t");
			
			/**
			 * Primer punto definido por x(a) = xo
			 */
			xData[0] = a;
			yData[0] = x0;
			
			System.out.print("x "+ a);
			System.out.println(" | y "+ x0);
			
			/**
			 * Definimos el primer nuevoA que va a ser nuevo a sin h inicialmente
			 */
			double nuevoA = a;
			
			/**
			 * Vamos a recorrer n-1 para poder completar nuestro
			 * x,y para nuestro Euler
			 */
			for (int i = 1; i <= n; i++) {
				
				Argument newX = new Argument("x = " + nuevoA);
				Double xCorrector = nuevoA + h;
				Argument newXCorrector = new Argument("x = " + xCorrector);
				Argument newT = new Argument("t = "+ t);
				
				Expression resultFxPredictor = new Expression("f(x,t)",function, newX, newT);
				Expression resultFxCorrector = new Expression("f(x,t)",function, newXCorrector, newT);
				/**
				 * Xn = Xn-1 + (1/2) * (F(Xn-1, t) + F(Xn, t)) * h
				 */
				Double predictor = Double.sum(yData[i -1], ((resultFxPredictor.calculate() * h)));
				Double corrector = Double.sum(yData[i -1], ((0.5) * (Double.sum(resultFxPredictor.calculate(), resultFxCorrector.calculate()) * h)));
				
				nuevoA = nuevoA + h;
				System.out.print("x "+ nuevoA);
				System.out.println(" | y "+ corrector);
				
				xData[i] = nuevoA;
				yData[i] = corrector;
				
		    }
			
			System.out.println("==================================");
			
			/**
			 * FUNCION NORMAL
			 */
			int m = 100;
			
			/**
			 * Separación entre los puntos
			 */
			double k = (b - a) / m;
			
			/**
			 * Construimos dos array del formato double
			 * donde vamos almacenar los puntos x,y
			 * para la función real
			 */
			double[] xDataNormal = new double[m];
			double[] yDataNormal = new double[m];
			
			xDataNormal[0] = a;
			yDataNormal[0] = x0;
			
			/**
			 * Recorremos m = 10.000 para poder ilustrar la función
			 * que queremos mostrar realmente.
			 */
			
			Double xNormal = a;
			
			for (int i = 1; i < m; i++) {
				
				Argument newX = new Argument("x = " + xNormal);
				Argument newT = new Argument("t = "+ t);
				
				Expression fxResult = new Expression("f(x,t)",function, newX, newT);
				/**
				 * Xn = Xn-1 + YXn-1 * k
				 */
				Double result = Double.sum(yDataNormal[i - 1], (fxResult.calculate() * k));
				
				xNormal = xNormal + k;
				xDataNormal[i] = xNormal;
				yDataNormal[i] = result;
				
		    }
			
			XYChart chart = new XYChartBuilder().width(600).height(500).title("Modelado y Simulación").xAxisTitle("X").yAxisTitle("Y").build();
			
			/**
		     * Funcion Método de Euler
		     */
			XYSeries series = chart.addSeries("Método de Euler Mejorado", xData, yData);
			series.setLineColor(XChartSeriesColors.BLUE);
			series.setMarkerColor(Color.BLUE);
		    series.setMarker(SeriesMarkers.CIRCLE);
		    series.setLineStyle(SeriesLines.SOLID);
		  
		    /**
		     * Funcion Real
		     */
		    XYSeries seriesNormal = chart.addSeries("Función Normal", xDataNormal, yDataNormal);
		    seriesNormal.setLineColor(XChartSeriesColors.RED);
		    seriesNormal.setLineStyle(SeriesLines.SOLID);
			
			return chart;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
