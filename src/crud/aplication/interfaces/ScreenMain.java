package crud.aplication.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

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
		btnNewButton.setBounds(10, 54, 201, 93);
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
	}
}
