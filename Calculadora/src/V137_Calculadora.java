// Referencias:
//
// Video 137
// Empaquetar la aplicación a un archivo .jar

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class V137_Calculadora extends JApplet {

	public void init() {
		// TODO Auto-generated method stub

		// Un applet no puede tener marco, tamaño ni hacerse visible (un applet
		// es siempre visible)

		// V137_Marco_Calculadora marco = new V137_Marco_Calculadora();
		// marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// marco.setVisible(true);

		// Hay que trasladar el código del constructor referente al marco al
		// init()
		// Un apple no tiene título
		// setTitle("Calculadora");
		// setBounds(500, 300, 450, 300);
		V137_Lamina_Calculadora milamina = new V137_Lamina_Calculadora();
		add(milamina);

	}
}

// Hay que trasladar el código del constructor referente al marco al
// init()
// Y la propia clase del marco sobra

// class V137_Marco_Calculadora extends JFrame {
//
// public V137_Marco_Calculadora() {
//
//
// setTitle("Calculadora");
// setBounds(500, 300, 450, 300);
// V137_Lamina_Calculadora milamina = new V137_Lamina_Calculadora();
// add(milamina);
// }
// }

class V137_Lamina_Calculadora extends JPanel {

	public V137_Lamina_Calculadora() {

		principio = true;

		setLayout(new BorderLayout());

		font = new Font(Font.MONOSPACED, Font.PLAIN, 24);

		pantalla = new JButton("0");

		pantalla.setEnabled(false);
		pantalla.setFont(font);
		pantalla.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pantalla, BorderLayout.NORTH);

		// Segunda lámina. Tipo Grid
		milamina2 = new JPanel();
		milamina2.setLayout(new GridLayout(4, 4));

		ActionListener insertar = new InsertaNumero();
		ActionListener orden = new AccionOrden();

		ponerBoton("7", insertar);
		ponerBoton("8", insertar);
		ponerBoton("9", insertar);
		ponerBoton("/", orden);

		ponerBoton("4", insertar);
		ponerBoton("5", insertar);
		ponerBoton("6", insertar);
		ponerBoton("*", orden);

		ponerBoton("1", insertar);
		ponerBoton("2", insertar);
		ponerBoton("3", insertar);
		ponerBoton("-", orden);

		ponerBoton("0", insertar);
		ponerBoton(".", insertar);
		ponerBoton("=", orden);
		ponerBoton("+", orden);

		add(milamina2, BorderLayout.CENTER);

		ultimaOperacion = "=";

	}

	private void ponerBoton(String rotulo, ActionListener oyente) {
		JButton boton = new JButton(rotulo);
		boton.setFont(font);
		boton.addActionListener(oyente);
		milamina2.add(boton);
	}

	private class InsertaNumero implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String entrada = e.getActionCommand();
			if (principio) {
				pantalla.setText("");
				principio = false;
			}
			pantalla.setText(pantalla.getText() + entrada);

		}

	}

	private class AccionOrden implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String operacion = e.getActionCommand(); // Texto del botón
			// System.out.println(operacion);
			calcular(Double.parseDouble(pantalla.getText()));
			ultimaOperacion = operacion;
			principio = true;

		}

		public void calcular(double x) {
			if (ultimaOperacion.equals("+")) {
				resultado += x;
				System.out.println(resultado);
			} else if (ultimaOperacion.equals("-")) {
				resultado -= x;
			} else if (ultimaOperacion.equals("*")) {
				resultado *= x;
			} else if (ultimaOperacion.equals("/")) {
				resultado /= x;
			} else if (ultimaOperacion.equals("=")) {
				resultado = x;
			}
			pantalla.setText("" + resultado); // Convierte de Double a String
		}

	}

	// Se saca la declaración de milamina2 fuera del constructor, para que esté
	// disponible en todos los métodos de la clase
	private JPanel milamina2;
	private JButton pantalla;
	private boolean principio;
	private double resultado;
	private String ultimaOperacion;
	private Font font;

}
