package DSP2;

import java.util.ArrayList;
import java.util.Collections;

public class ReguladorPID {
	ArrayList<Double> lista_rpm;
	double rpm_objetivo;
	public static int MAX_SIZE = 120;
	
	ReguladorPID(double objetivo){
		lista_rpm = new ArrayList<Double>();
		rpm_objetivo = objetivo;
	}
	
	void setObjetivo(double objetivo) {
		rpm_objetivo = objetivo;
	}
	
	void actualiziarArrays(double rpm) {
		if(lista_rpm.size() < ReguladorPID.MAX_SIZE) {
			lista_rpm.add(rpm);
		}
		else {
			Collections.rotate(lista_rpm,-1);
			lista_rpm.add(ReguladorPID.MAX_SIZE-1, rpm);
		}
	}
	
	double update(double rpm) {	
		double UPS = Coche.UPDATES_PER_SECOND;
		
		double accel_maxima = 100/UPS;
		double accel = 0;
		
		if(lista_rpm.size() > 0) { //Si no hemos guardado nada por ahora, saltar

			//Elemento proporcional, cuanto mas la diferencia entre velocidad actual y deseada, mas se acelera (entre -1 y 1)
			double elemento_P = (rpm < rpm_objetivo) ? rpm/rpm_objetivo : -rpm_objetivo/rpm;

			//Elemento diferencial, cuanto mas la diferencia entre velocidad actual y anterior, menos se acelera (
			double elemento_D; 
			elemento_D = lista_rpm.get(lista_rpm.size()-1)-rpm;
			
			//Elemento integral, acelera o desacelera según el cambio a lo largo del tiempo
			double elemento_I = 0;
			for(double d : lista_rpm) {
				elemento_I += rpm_objetivo-d;
			}
			elemento_I /= lista_rpm.size();
			
			accel = accel_maxima * elemento_P;
		}
		
		return Math.max(-accel_maxima, Math.min(accel_maxima, accel));
	}

}