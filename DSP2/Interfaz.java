package DSP2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Interfaz extends JFrame implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;
	
	SCAV scav;
	Mantenimiento mantenimiento;
	Monitor monitor;
	
	private ArrayList<AbstractButton> botones_motor_encendido;
	private ArrayList<AbstractButton> botones_motor_apagado;
	
	//Velocimetro
	private Label etiqueta_velocidad;
	private Label etiqueta_gasolina;
	private Label etiqueta_distancia;
	private Label etiqueta_crucero;
	
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
	private JButton boton_repostar = null;
	private JButton boton_aceite = null;
	private JButton boton_pastillas = null;
	private JButton boton_revision = null;
	
	private Label alerta_repostar;
	private Label alerta_aceite;
	private Label alerta_pastillas;
	private Label alerta_revision;
	
	public Interfaz(SCAV s, Mantenimiento ma, Monitor mo) {
		setTitle("vroom vroom fast machine");
		setSize(750,250);
		
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
		etiqueta_velocidad = new Label("Actualmente se desconoce la velocidad del coche");
		etiqueta_gasolina = new Label("Actualmente se desconoce el nivel de combustible");
		etiqueta_distancia = new Label("Actualmente se desconoce la distancia recorrida");
		panelVelocimetro.add(etiqueta_velocidad, BorderLayout.NORTH);
		panelVelocimetro.add(etiqueta_gasolina, BorderLayout.NORTH);
		panelVelocimetro.add(etiqueta_distancia, BorderLayout.NORTH);
				
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
		etiqueta_crucero = new Label("Velocidad Guardada: 0.00 km/h");
		
		grupo_scav = new ButtonGroup();
		
		grupo_scav.add(scav_acelerar);
		grupo_scav.add(scav_mantener);
		grupo_scav.add(scav_reiniciar);
		grupo_scav.add(scav_parar);
		
		scav_acelerar.addActionListener( this );
		scav_mantener.addActionListener( this );
		scav_reiniciar.addActionListener( this );
		scav_parar.addActionListener( this );
		
		panelDcha.add(etiqueta_crucero);
		
		panelScav.add(scav_acelerar);
		panelScav.add(scav_mantener);
		panelScav.add(scav_reiniciar);
		panelScav.add(scav_parar);
		
		//Mantenimiento
		boton_repostar = new  JButton("Repostar");
		boton_aceite = new  JButton("Cambiar aceite");
		boton_pastillas = new  JButton("Cambiar pastillas");
		boton_revision = new  JButton("Revisión general");
		
		boton_repostar.addActionListener( this );
		boton_aceite.addActionListener( this );
		boton_pastillas.addActionListener( this );
		boton_revision.addActionListener( this );
		
		alerta_repostar = new Label(""); alerta_repostar.setForeground(Color.RED);
		alerta_aceite = new Label(""); alerta_aceite.setForeground(Color.RED);
		alerta_pastillas = new Label(""); alerta_pastillas.setForeground(Color.RED);
		alerta_revision = new Label(""); alerta_revision.setForeground(Color.RED);

		Panel mini;
		mini = new Panel(new BorderLayout());
		mini.add(alerta_repostar, BorderLayout.NORTH);
		mini.add(boton_repostar, BorderLayout.SOUTH);
		panelMant.add(mini);
		mini = new Panel(new BorderLayout());
		mini.add(alerta_aceite, BorderLayout.NORTH);
		mini.add(boton_aceite, BorderLayout.SOUTH);
		panelMant.add(mini);
		mini = new Panel(new BorderLayout());
		mini.add(alerta_pastillas, BorderLayout.NORTH);
		mini.add(boton_pastillas, BorderLayout.SOUTH);
		panelMant.add(mini);
		mini = new Panel(new BorderLayout());
		mini.add(alerta_revision, BorderLayout.NORTH);
		mini.add(boton_revision, BorderLayout.SOUTH);
		panelMant.add(mini);
				
		//Estetica y funcionalidad
		botones_motor_encendido = new ArrayList<AbstractButton>();
		botones_motor_encendido.add(scav_acelerar);
		botones_motor_encendido.add(scav_mantener);
		botones_motor_encendido.add(scav_reiniciar);
		botones_motor_encendido.add(scav_parar);
		botones_motor_encendido.add(boton_accel);
		botones_motor_encendido.add(boton_decel);
		
		botones_motor_apagado = new ArrayList<AbstractButton>();
		botones_motor_apagado.add(boton_repostar);
		botones_motor_apagado.add(boton_aceite);
		botones_motor_apagado.add(boton_pastillas);
		botones_motor_apagado.add(boton_revision);
		
		etiquetasMotor();
		etiquetasPedalesSCAV();
		etiquetasMisc();
		this.setVisible(true);
		
		this.addWindowListener (new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
		
		new Thread(this).start();
	}
	
	private void etiquetasMotor() {
		if(boton_motor.isSelected()) {
			boton_motor.setForeground(Color.RED);
			boton_motor.setText("Parar motor");
			for(AbstractButton a : botones_motor_encendido) {
				a.setEnabled(true);
			}
			for(AbstractButton a : botones_motor_apagado) {
				a.setEnabled(false);
			}
		}
		else {
			boton_motor.setForeground(new Color(0x009900));
			boton_motor.setText("Arrancar");
			boton_accel.setSelected(false);
			boton_decel.setSelected(false);
			for(AbstractButton a : botones_motor_encendido) {
				a.setEnabled(false);
			}
			for(AbstractButton a : botones_motor_apagado) {
				a.setEnabled(true);
			}
		}
	}
	
	private void etiquetasMisc() {
		etiqueta_velocidad.setText("Velocidad: " + String.format("%.2f", monitor.getVelocidadKMH()) + " km/h ("
			+ String.format("%.2f", monitor.getVelocidadRPM()) + " rpm)");
		etiqueta_gasolina.setText("Combustible: " + String.format("%.2f", mantenimiento.getGasolina()) + " litros");
		etiqueta_distancia.setText("Distancia Recorrida:" + String.format("%.2f", mantenimiento.getDistancia()) + " km ("
				+ String.format("%.2f", mantenimiento.getRotaciones()) + " revoluciones)");
		etiqueta_crucero.setText("Velocidad guardada: " + String.format("%.2f", monitor.getVelocidadSCAV()) + " km/h");
		
		alerta_aceite.setText((mantenimiento.necesitoEngrase()) ? "Alerta":"");
		alerta_pastillas.setText((mantenimiento.necesitoCambioPastillas()) ? "Alerta":"");
		alerta_revision.setText((mantenimiento.necesitoRevision()) ? "Alerta":"");
		
	}
	
	private void etiquetasPedalesSCAV() {
		//Actualizar botones de pedales
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
		
		//Actualizar botones de SCAV
		switch(scav.getSCAV()) {
			case ACELERAR:
				scav_acelerar.setSelected(true);
				break;
			case MANTENER:
				scav_mantener.setSelected(true);
				break;
			case REINICIAR:
				scav_reiniciar.setSelected(true);
				break;
			case PARAR:
				scav_parar.setSelected(true);
				break;
		}
	}
	
	@Override
	public void run() {
		while(true) {
			etiquetasMisc();
			try {
				Thread.sleep(100);
			} catch (Exception e) { }
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
				
		if (event.getSource() == boton_motor) {
			if(!boton_motor.isSelected()) {
				scav.apagarMotor();
			}
			else {
				scav.setArranque(EstadoArranque.ENCENDIDO);
			}

			etiquetasMotor();
		}

		else if(boton_motor.isSelected()) {
			if (event.getSource() == boton_accel) {
				if(boton_accel.isSelected()) {
					scav.setPedales(EstadoPedales.ACELERANDO);
					boton_decel.setEnabled(false);
				}
				else {
					scav.setPedales(EstadoPedales.NINGUNO);
					boton_decel.setEnabled(true);
				}
			}
			else if (event.getSource() == boton_decel) {
				if(boton_decel.isSelected()) {
					scav.frenar();
					boton_accel.setEnabled(false);
				}
				else {
					scav.setPedales(EstadoPedales.NINGUNO);
					boton_accel.setEnabled(true);
				}
			}
			else if (event.getSource() == scav_acelerar) {
				scav.setSCAV(EstadoSCAV.ACELERAR);
			}
			else if (event.getSource() == scav_parar) {
				scav.setSCAV(EstadoSCAV.PARAR);
			}
			else if (event.getSource() == scav_reiniciar) {
				scav.setSCAV(EstadoSCAV.REINICIAR);
			}
			else if (event.getSource() == scav_mantener) {
				scav.setSCAV(EstadoSCAV.MANTENER);
			}
		}

		etiquetasPedalesSCAV();
	}
}