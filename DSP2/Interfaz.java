package DSP2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import java.util.ArrayList;

public class Interfaz extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	SCAV scav;
	Mantenimiento mantenimiento;
	Monitor monitor;
	
	private ArrayList<AbstractButton> botones_motor_encendido;
	private ArrayList<AbstractButton> botones_motor_apagado;
	
	//Velocimetro
	private Label etiqueta;
	
	//Pedales
	private JToggleButton boton_accel = null;
	private JToggleButton boton_decel = null;
	private JToggleButton boton_motor = null;

	//SCAV
	private JToggleButton scav_acelerar = null;
	private JToggleButton scav_parar = null;
	private JToggleButton scav_reiniciar = null;
	private JToggleButton scav_mantener = null;
	
	private ButtonGroup grupo_scav;
	
	//Mantenimiento
	//double gasolina = mantenimiento.getGasolina();
	//TODO
	
	public Interfaz(SCAV s, Mantenimiento ma, Monitor mo) {
		setTitle("vroom vroom fast machine");
		setSize(600,400);
		
		scav = s;
		mantenimiento = ma;
		monitor = mo;
		
		//Panel Grande
		Panel panelGrande = new Panel();
		Panel panelIzda = new Panel();
		Panel panelDcha = new Panel();
		Panel panelVelocimetro = new Panel();
		Panel panelPedales = new Panel();
		Panel panelScav = new Panel();
		Panel panelMant = new Panel();
		
		panelGrande.setLayout(new FlowLayout());
		panelGrande.add(panelIzda);
		panelGrande.add(panelDcha);
		panelIzda.setLayout(new BorderLayout());
		panelIzda.add(panelVelocimetro, BorderLayout.NORTH);
		panelIzda.add(panelMant, BorderLayout.SOUTH);
		panelDcha.setLayout(new BorderLayout());
		panelDcha.add(panelPedales, BorderLayout.NORTH);
		panelDcha.add(panelScav, BorderLayout.SOUTH);
		getContentPane().add(panelGrande);
		
		//Velocimetro
		etiqueta = new Label("Actualmente se desconoce la velocidad del coche");
		panelVelocimetro.add(etiqueta, BorderLayout.NORTH);
				
		//Pedales
		boton_motor = new JToggleButton("");
		boton_accel = new JToggleButton("");
		boton_decel = new JToggleButton("");
		
	    boton_motor.addActionListener( this );
		boton_accel.addActionListener( this );
		boton_decel.addActionListener( this );
		
		panelPedales.add(boton_motor);
		panelPedales.add(boton_accel);
		panelPedales.add(boton_decel);
		
		//SCAV
		scav_parar 		= new JToggleButton("Apagar SCAV", true);
		scav_acelerar 	= new JToggleButton("Acelerar SCAV", false);
		scav_reiniciar 	= new JToggleButton("Volver a velocidad guardada", false);
		scav_mantener 	= new JToggleButton("Mantener Velocidad", false);
		
		grupo_scav = new ButtonGroup();
		
		grupo_scav.add(scav_acelerar);
		grupo_scav.add(scav_mantener);
		grupo_scav.add(scav_reiniciar);
		grupo_scav.add(scav_parar);
		
		panelScav.add(scav_acelerar);
		panelScav.add(scav_mantener);
		panelScav.add(scav_reiniciar);
		panelScav.add(scav_parar);
		
		//Mantenimiento
		//TODO
		
		//Estetica y funcionalidad
		botones_motor_encendido = new ArrayList<AbstractButton>();
		botones_motor_encendido.add(scav_acelerar);
		botones_motor_encendido.add(scav_mantener);
		botones_motor_encendido.add(scav_reiniciar);
		botones_motor_encendido.add(scav_parar);
		botones_motor_encendido.add(boton_accel);
		botones_motor_encendido.add(boton_decel);
		
		//TODO -- Botones de motor apagado
		
		establecerEtiquetas();
		this.setVisible(true);
		
		this.addWindowListener (new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
	}
	
	private void establecerEtiquetas() {
		if(boton_motor.isSelected()) {
			boton_motor.setForeground(Color.RED);
			boton_motor.setText("Parar motor");
			for(AbstractButton a : botones_motor_encendido) {
				a.setEnabled(true);
			}
			//TODO -- Botones de motor apagado
		}
		else {
			boton_motor.setForeground(new Color(0x009900));
			boton_motor.setText("Arrancar");
			boton_accel.setSelected(false);
			boton_decel.setSelected(false);
			for(AbstractButton a : botones_motor_encendido) {
				a.setEnabled(false);
			}
			//TODO -- Botones de motor apagado
		}
		if(boton_accel.isSelected()) {
			boton_accel.setText("Soltar Acelerador");
		}
		else {
			boton_accel.setText("Acelerar");
		}
		if(boton_decel.isSelected()) {
			boton_decel.setText("Soltar Freno");
		}
		else {
			boton_decel.setText("Frenar");
		}
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
}