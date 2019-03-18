package DSP2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;

import javax.swing.*;

public class Cliente extends JFrame implements ActionListener {

	private JToggleButton boton_accel = null;
	private JToggleButton boton_decel = null;
	private JToggleButton boton_motor = null;
	private Label etiqueta;
	private double velocidad_kmh = 0;
	private double velocidad_rpm = 0;
	
	private GestorFiltros target;
	
	public Cliente() {
		setTitle("vroom vroom fast machine");
		setSize(500,150);
		
		Panel panelGrande = new Panel();
		panelGrande.setLayout(new BorderLayout());
		getContentPane().add(panelGrande);
		
		etiqueta = new Label("Actualmente se desconoce la velocidad del coche");
		panelGrande.add(etiqueta, BorderLayout.NORTH);
		
		Panel panelInf= new Panel();
		panelInf.setLayout(new FlowLayout());
		panelGrande.add(panelInf, BorderLayout.SOUTH);
		
		boton_motor = new JToggleButton("");
		boton_accel = new JToggleButton("");
		boton_decel = new JToggleButton("");
		
	    boton_motor.addActionListener( this );
		boton_accel.addActionListener( this );
		boton_decel.addActionListener( this );
		
		panelInf.add(boton_motor);
		panelInf.add(boton_accel);
		panelInf.add(boton_decel);
		
		establecerEtiquetas();
		
		this.addWindowListener (new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
	}

	public void update(){
		EstadoMotor vroom = EstadoMotor.NEUTRO;
		if(boton_motor.isSelected()) {
			if(boton_accel.isSelected()) { vroom = EstadoMotor.ACCEL; }
			if(boton_decel.isSelected()) { vroom = EstadoMotor.DECEL; }
		}
		target.update(velocidad_rpm, vroom);
	}
	
	public void setRPM(double rpm) {
		velocidad_rpm = rpm;
	}
	
	public void setKMH(double kmh){
		velocidad_kmh = kmh;
	}
	
	public void setFiltro(GestorFiltros g) {
		target = g;
	}
	
	//Parte cosmetica
	public void updateDisplay(double dist) {
		etiqueta.setText("El coche lleva " +
				Double.toString(Math.round(dist*100)/100.0) + " km recorridos y va a " +
				Double.toString(Math.round(velocidad_kmh*100)/100.0) + " kmh (" +
				Double.toString(Math.round(velocidad_rpm*100)/100.0) + " RPM)");
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == boton_accel) {
			if(!boton_motor.isSelected() || boton_decel.isSelected()) {
				boton_accel.setSelected(false);
			}
		}
		if (event.getSource() == boton_decel) {
			if(!boton_motor.isSelected() || boton_accel.isSelected()) {
				boton_decel.setSelected(false);
			}
		}
		
		establecerEtiquetas();
		
	}
	
	private void establecerEtiquetas() {
		if(boton_motor.isSelected()) {
			boton_motor.setForeground(Color.RED);
			boton_motor.setText("Parar motor");
		}
		else {
			boton_motor.setForeground(new Color(0x009900));
			boton_motor.setText("Arrancar");
			boton_accel.setSelected(false);
			boton_decel.setSelected(false);
		}
		if(boton_accel.isSelected()) {
			boton_accel.setText("Parar aceleracion");
		}
		else {
			boton_accel.setText("Acelerar");
		}
		if(boton_decel.isSelected()) {
			boton_decel.setText("Parar deceleracion");
		}
		else {
			boton_decel.setText("Decelerar");
		}

	}

}
