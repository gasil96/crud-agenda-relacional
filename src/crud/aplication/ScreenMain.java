package crud.aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

import crud.dao.ContatoDAO;
import crud.entities.Contato;

public class ScreenMain extends JFrame {

	private JLayeredPane contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public ScreenMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 411);
		contentPane = new JLayeredPane();
		contentPane.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}

			public void inputMethodTextChanged(InputMethodEvent event) {

			}
		});
		contentPane.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JButton btnContatoAdd = new JButton("Adicionar Contatos no SOUT**TESTE***");
		btnContatoAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ContatoDAO cttDAO = new ContatoDAO();
				
				Contato contato = new Contato();
				contato.setNome("Gabriel Silva");
				contato.setCpf("021.429.212.64");
				contato.setIdade(23);
				contato.setSexo("Masculino");
				cttDAO.create(contato);
			
			}
		});
		contentPane.add(btnContatoAdd, BorderLayout.SOUTH);
		
		JButton btnRemoverContatosteste = new JButton("Remover Contatos ***TESTE***");
		btnRemoverContatosteste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContatoDAO cttDAO = new ContatoDAO();
				cttDAO.remove(10);
				
			}
		});
		contentPane.add(btnRemoverContatosteste, BorderLayout.NORTH);
	}

}
