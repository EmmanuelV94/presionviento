package com.umes.presionviento.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.umes.presionviento.bo.Calculator;
import com.umes.presionviento.constants.Coefficients;
import com.umes.presionviento.constants.Exhibitions;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import com.umes.presionviento.constants.Categories;

public class FormCalculation extends JFrame {

	private JPanel contentPane;
	private JTextField txtQs;
	private JComboBox cbxSpeed;
	private Calculator controller;
	private JTextField txtWorkType;
	private JComboBox cbxCategories;
	private JTextField txtIC;
	private JTextField txtBarlovento;
	private JTextField txtSotavento;
	private JComboBox cbxPresion;

	private JLabel lblBarlovento;
	private JLabel lblSotavento;
	private JComboBox cbxPendiente;
	private JTextField txtPzbarlovento;
	private JTextField txtPzSotavento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCalculation frame = new FormCalculation();
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
	public FormCalculation() {
		controller = new Calculator();
		setTitle("Calculo de Presión Viento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMap = new JLabel("");
		lblMap.setIcon(new ImageIcon("C:\\Users\\Emmanuel\\eclipse-workspace\\PresionViento\\images\\MAPA.jpg"));
		lblMap.setBounds(434, 36, 204, 218);
		contentPane.add(lblMap);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(491, 415, 176, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblVelocidad = new JLabel("Velocidad");
		lblVelocidad.setBounds(37, 84, 150, 14);
		contentPane.add(lblVelocidad);

		JLabel lblPresion = new JLabel("Presion");
		lblPresion.setBounds(37, 275, 150, 14);
		contentPane.add(lblPresion);

		cbxSpeed = new JComboBox();
		cbxSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalcularQS();
			}
		});
		cbxSpeed.setModel(new DefaultComboBoxModel(new String[] { "120kph", "110kph", "100kph" }));
		cbxSpeed.setBounds(198, 80, 188, 22);
		contentPane.add(cbxSpeed);

		JComboBox cbxExpose = new JComboBox();
		cbxExpose.setModel(new DefaultComboBoxModel(Exhibitions.values()));
		cbxExpose.setBounds(198, 134, 188, 22);
		contentPane.add(cbxExpose);

		JComboBox cbxHigh = new JComboBox();
		cbxHigh.setModel(new DefaultComboBoxModel(new String[] { "4.5", "6.0", "7.5", "9.0", "12.0", "18.0", "24.0",
				"30.0", "36.0", "48.0", "60.0", "90.0", "120.0" }));
		cbxHigh.setBounds(198, 163, 188, 22);
		contentPane.add(cbxHigh);

		cbxPresion = new JComboBox();
		cbxPresion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressureHasChanged();
			}
		});
		cbxPresion.setModel(new DefaultComboBoxModel(new String[] { "PRESION SOBRE SUPERFICIES VERTICALES",
				"PRESION SOBRE TECHO PLANO", "PRESION SOBRE TECHO INCLINADO" }));
		cbxPresion.setBounds(198, 271, 188, 22);
		contentPane.add(cbxPresion);

		JLabel lblAlturaPromedio = new JLabel("Altura promedio:");
		lblAlturaPromedio.setBounds(37, 167, 150, 14);
		contentPane.add(lblAlturaPromedio);

		JLabel lblExposicin = new JLabel("Exposición");
		lblExposicin.setBounds(37, 138, 150, 14);
		contentPane.add(lblExposicin);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnSalir.setBounds(600, 500, 90, 20);
		contentPane.add(btnSalir);

		JButton btnNewButton = new JButton("CALCULAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Exhibitions selectedExhibition = ((Exhibitions) cbxExpose.getSelectedItem());
				Double high = Double.parseDouble(cbxHigh.getSelectedItem().toString());
				Coefficients c = new Coefficients();
				Double valor = c.GetCoefficient(selectedExhibition, high);
				controller.setCez(valor);
				lblNewLabel_1.setText(valor.toString());
				
				txtPzbarlovento.setText(controller.getPzBarlovento().toString() + " kg/m^2");
				txtPzSotavento.setText(controller.getPzSotavento().toString() + " kg/m^2");
			}
		});
		btnNewButton.setBounds(356, 498, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("New ");
		lblNewLabel.setIcon(
				new ImageIcon("C:\\Users\\Emmanuel\\eclipse-workspace\\PresionViento\\images\\VELOCIDADES.jpg"));
		lblNewLabel.setBounds(637, 36, 210, 158);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("q_s");
		lblNewLabel_2.setBounds(37, 109, 150, 14);
		contentPane.add(lblNewLabel_2);

		txtQs = new JTextField();
		txtQs.setEnabled(false);
		txtQs.setBounds(198, 106, 188, 20);
		contentPane.add(txtQs);
		txtQs.setColumns(10);

		cbxCategories = new JComboBox();
		cbxCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoriesHasChanged();
			}
		});
		cbxCategories.setModel(new DefaultComboBoxModel(Categories.values()));
		cbxCategories.setBounds(198, 196, 188, 22);
		contentPane.add(cbxCategories);

		JLabel lblCategories = new JLabel("Categoria");
		lblCategories.setBounds(37, 200, 150, 14);
		contentPane.add(lblCategories);

		JLabel lblTypeWork = new JLabel("Tipo de Obra:");
		lblTypeWork.setBounds(37, 225, 150, 14);
		contentPane.add(lblTypeWork);

		txtWorkType = new JTextField();
		txtWorkType.setEnabled(false);
		txtWorkType.setColumns(10);
		txtWorkType.setBounds(198, 222, 188, 20);
		contentPane.add(txtWorkType);

		JLabel lblIC = new JLabel("Factor I_C:");
		lblIC.setBounds(37, 250, 150, 14);
		contentPane.add(lblIC);

		txtIC = new JTextField();
		txtIC.setText("69");
		txtIC.setEnabled(false);
		txtIC.setColumns(10);
		txtIC.setBounds(198, 247, 188, 20);
		contentPane.add(txtIC);

		lblBarlovento = new JLabel("Barlovento");
		lblBarlovento.setBounds(37, 311, 150, 14);
		lblBarlovento.setVisible(false);
		;
		contentPane.add(lblBarlovento);

		lblSotavento = new JLabel("Sotavento");
		lblSotavento.setBounds(37, 336, 150, 14);
		lblSotavento.setVisible(false);
		;
		contentPane.add(lblSotavento);

		txtBarlovento = new JTextField();
		txtBarlovento.setBounds(198, 308, 188, 20);
		txtBarlovento.setVisible(false);
		txtBarlovento.setEnabled(false);
		contentPane.add(txtBarlovento);
		txtBarlovento.setColumns(10);

		txtSotavento = new JTextField();
		txtSotavento.setColumns(10);
		txtSotavento.setVisible(false);
		txtSotavento.setEnabled(false);
		txtSotavento.setBounds(198, 333, 188, 20);
		contentPane.add(txtSotavento);

		cbxPendiente = new JComboBox();
		cbxPendiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pendienteHasChanged();
			}
		});
		cbxPendiente.setVisible(false);
		cbxPendiente.setModel(new DefaultComboBoxModel(
				new String[] { "Pendiente <= 1:6 (16.67%)", "1:6 (16.67%) < Pendiente <= 3:4 (75%)",
						"3:4 (75%)  < Pendiente <= 1:1 (100%)", "Pendiente > 1:1 (100%)", "" }));
		cbxPendiente.setBounds(491, 271, 226, 22);
		contentPane.add(cbxPendiente);

		JLabel lblPendiente = new JLabel("Pendiente");
		lblPendiente.setBounds(415, 275, 75, 14);
		contentPane.add(lblPendiente);
		
		JLabel lblNewLabel_3 = new JLabel("Pz barlovento");
		lblNewLabel_3.setBounds(37, 377, 150, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Pz sotavento");
		lblNewLabel_4.setBounds(37, 402, 150, 14);
		contentPane.add(lblNewLabel_4);
		
		txtPzbarlovento = new JTextField();
		txtPzbarlovento.setBounds(198, 374, 188, 20);
		contentPane.add(txtPzbarlovento);
		txtPzbarlovento.setColumns(10);
		
		txtPzSotavento = new JTextField();
		txtPzSotavento.setColumns(10);
		txtPzSotavento.setBounds(198, 399, 188, 20);
		contentPane.add(txtPzSotavento);

		CalcularQS();
		categoriesHasChanged();
		pressureHasChanged();

	}

	public void CalcularQS() {
		controller.setSpeed(Integer.parseInt(cbxSpeed.getSelectedItem().toString().replace("kph", "")));
		txtQs.setText(controller.getQs().toString());
	}


	public void pressureHasChanged() {
		switch (cbxPresion.getSelectedIndex()) {
		case 0:
			lblBarlovento.setVisible(true);
			lblSotavento.setVisible(true);
			txtBarlovento.setVisible(true);
			txtSotavento.setVisible(true);
			cbxPendiente.setVisible(false);
			controller.setBarlovento(0.8);
			txtBarlovento.setText("0.8 empuje");
			controller.setSotavento(0.5);
			txtSotavento.setText("0.5 succión");
			break;

		case 1:
			lblBarlovento.setVisible(false);
			lblSotavento.setVisible(false);
			txtBarlovento.setVisible(true);
			txtSotavento.setVisible(false);
			cbxPendiente.setVisible(false);
			txtBarlovento.setText("0.7 succión");
			txtSotavento.setText("");
			break;
		case 2:
			lblBarlovento.setVisible(true);
			lblSotavento.setVisible(true);
			txtBarlovento.setVisible(true);
			txtSotavento.setVisible(true);
			cbxPendiente.setVisible(true);
			txtSotavento.setText("0.7 succión");
			controller.setSotavento(0.7);
			pendienteHasChanged();
			break;

		}
	}

	public void pendienteHasChanged() {
		switch (cbxPendiente.getSelectedIndex()) {
		case 0:
			txtBarlovento.setText("0.7 succión");
			controller.setSotavento(0.7);

			break;

		case 1:
			txtBarlovento.setText("0.9 succión y: 0.3 empuje");
			break;
		case 2:
			txtBarlovento.setText("0.4 empuje");
			break;
		case 3:
			txtBarlovento.setText("0.7 empuje");
			break;

		}
	}

	public void categoriesHasChanged() {
		controller.setIC(((Categories) cbxCategories.getSelectedItem()));
		System.out.println(controller.getICDisplay());
		txtWorkType.setText(controller.getICDisplay());
		txtIC.setText(controller.getIC().toString());
	}
}
