package DSP2;

import java.util.ArrayList;
import java.util.Collections;

public class ReguladorPID {
	double rpm_objetivo;
	double integral;
	double error_anterior;
	public static int MAX_SIZE = (int) (4*Coche.UPDATES_PER_SECOND);
	private static double FACTOR_P = 2.0;
	private static double FACTOR_D = 0.5;
	private static double FACTOR_I = 2.0;
	
	ReguladorPID(double objetivo){
		rpm_objetivo = objetivo;
		integral = 0;
		error_anterior = 0;	
	}
	
	void setObjetivo(double objetivo) {
		rpm_objetivo = objetivo;
		integral = 0;
		error_anterior = 0;
	}
	
	double update(double rpm) {	
		double UPS = Coche.UPDATES_PER_SECOND;
		
		double accel_maxima = 100/UPS;
		double accel = 0;
		
		//Elemento proporcional, cuanto mas la diferencia entre velocidad actual y deseada, mas se acelera (entre -1 y 1)
		double elemento_P = rpm_objetivo-rpm;
		
		//Elemento diferencial, cuanto mas la diferencia entre velocidad actual y anterior, menos se acelera (
		double elemento_D = (elemento_P - error_anterior) * UPS;
		
		//Elemento integral, acelera o desacelera según el cambio a lo largo del tiempo
		integral += elemento_P / UPS;
		double elemento_I = integral;
			
		error_anterior = elemento_P;
		
		accel = (FACTOR_P * elemento_P + FACTOR_I * elemento_I + FACTOR_D * elemento_D)/150;
		accel = Math.max(-accel_maxima, Math.min(accel_maxima, accel));
		
		return accel;
	}

}