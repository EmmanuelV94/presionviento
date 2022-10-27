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
		lblMap.setBounds(435, 50, 204, 218);
		contentPane.add(lblMap);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(491, 415, 176, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblVelocidad = new JLabel("Velocidad");
		lblVelocidad.setBounds(37, 84, 150, 14);
		contentPane.add(lblVelocidad);
		
		cbxSpeed = new JComboBox();
		cbxSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalcularQS();
			}
		});
		cbxSpeed.setModel(new DefaultComboBoxModel(new String[] {"120kph", "110kph", "100kph"}));
		cbxSpeed.setBounds(198, 80, 188, 22);
		contentPane.add(cbxSpeed);
		
		JComboBox cbxExpose = new JComboBox();
		cbxExpose.setModel(new DefaultComboBoxModel(Exhibitions.values()));
		cbxExpose.setBounds(198, 134, 188, 22);
		contentPane.add(cbxExpose);
		
		JComboBox cbxHigh = new JComboBox();
		cbxHigh.setModel(new DefaultComboBoxModel(new String[] {"4.5", "6.0", "7.5", "9.0", "12.0", "18.0", "24.0", "30.0", "36.0", "48.0", "60.0", "90.0", "120.0"}));
		cbxHigh.setBounds(198, 163, 188, 22);
		contentPane.add(cbxHigh);
		
		
		
		JLabel lblAlturaPromedio = new JLabel("Altura promedio:");
		lblAlturaPromedio.setBounds(37, 167, 150, 14);
		contentPane.add(lblAlturaPromedio);
		
		JLabel lblExposicin = new JLabel("Exposición");
		lblExposicin.setBounds(37, 138, 150, 14);
		contentPane.add(lblExposicin);
		
		JButton btnNewButton = new JButton("CALCULAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Exhibitions selectedExhibition = ((Exhibitions)cbxExpose.getSelectedItem());
				Double high = Double.parseDouble(cbxHigh.getSelectedItem().toString());
				Coefficients c = new Coefficients();
				Double valor = c.GetCoefficient(selectedExhibition, high);
				lblNewLabel_1.setText(valor.toString());
			}
		});
		btnNewButton.setBounds(356, 498, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New ");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Emmanuel\\eclipse-workspace\\PresionViento\\images\\VELOCIDADES.jpg"));
		lblNewLabel.setBounds(636, 48, 210, 158);
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
		
		CalcularQS();
		categoriesHasChanged();
	}
	
	
	public void CalcularQS()
	{
		int speed = Integer.parseInt(cbxSpeed.getSelectedItem().toString().replace("kph", ""));
		Double qs = (double) Math.round(0.0048 * (Math.pow(speed, 2)));
		txtQs.setText(qs.toString());	
	}
	
	public void Calculate() {
		
	}
	
	public void categoriesHasChanged() {
		controller.setIC(((Categories)cbxCategories.getSelectedItem()));
		System.out.println(controller.getICDisplay());
		txtWorkType.setText(controller.getICDisplay());
		txtIC.setText(controller.getIC().toString());
	}
}
