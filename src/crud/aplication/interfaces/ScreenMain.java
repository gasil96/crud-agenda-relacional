package crud.aplication.interfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import crud.entities.Comunica;

public class ScreenMain extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenMain frame = new ScreenMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScreenMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 256);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("NOVO CONTATO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenAdd telaAdd = new ScreenAdd();
				telaAdd.show();
				dispose();
			}
		});
		btnNewButton.setBounds(24, 54, 201, 93);
		getContentPane().add(btnNewButton);

		JButton button = new JButton("CONTATOS");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenList telaLista = new ScreenList();
				telaLista.show();
				dispose();

			}
		});
		button.setBounds(223, 54, 201, 93);
		getContentPane().add(button);
		
		JButton btnTesteEnum = new JButton("teste enum");
		btnTesteEnum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// MODELO DE COMO O ENUM DEVE FUNCIONAR NA HORA DE SER CHAMADO NA LISTA
				Comunica comunica = new Comunica(1, Comunica.TIPO.CELULAR, "98198-7035", 2);
				System.out.println("tipo de comunicao do contato: "+ comunica.getTipo());
				
			}
		});
		btnTesteEnum.setBounds(23, 184, 130, 23);
		getContentPane().add(btnTesteEnum);
	}
}