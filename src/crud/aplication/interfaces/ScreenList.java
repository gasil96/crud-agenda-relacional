package crud.aplication.interfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import crud.dao.ContatoDAO;
import crud.entities.Contato;
import crud.jdbc.connection.DB;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

public class ScreenList extends JFrame {

	private JPanel contentPane;
	private JButton button;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenList frame = new ScreenList();
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
	public ScreenList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final DefaultTableModel modelo = new DefaultTableModel();

		JButton btnNewButton = new JButton("Apagar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenMain telaPrincipal = new ScreenMain();
				telaPrincipal.show();
				dispose();

			}
		});

		button = new JButton("Listar*PROV");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ContatoDAO cttDAO = new ContatoDAO(); Contato contato = new Contato();
				 * 
				 * try { for (Contato c : cttDAO.listarTodos()) {
				 * 
				 * System.out.println("ID: " + c.getId_contato() + " NOME: " + c.getNome() +
				 * " CPF: " + c.getCpf() + " IDADE: " + c.getIdade() + " SEXO: " + c.getSexo());
				 * } } catch (Exception e1) { e1.printStackTrace(); }
				 */
			
			
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(157)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(2)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(2).addComponent(button, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(scrollPane,
								GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE).addGap(9)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton_1)
								.addComponent(btnNewButton).addComponent(button))));

		table = new JTable(modelo);

		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		modelo.addColumn("Id");
		modelo.addColumn("Nome");
		modelo.addColumn("CPF");
		modelo.addColumn("Idade");
		modelo.addColumn("Sexo");

		ContatoDAO cttDAO = new ContatoDAO();
		Contato contato = new Contato();

		try {
			for (Contato c : cttDAO.listarTodos()) {

				modelo.addRow(new Object[] { c.getId_contato(), c.getNome(), c.getCpf(), c.getIdade(), c.getSexo() });

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}