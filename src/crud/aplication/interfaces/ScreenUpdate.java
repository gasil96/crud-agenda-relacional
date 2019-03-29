package crud.aplication.interfaces;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import crud.dao.ContatoDAO;
import crud.entities.Contato;
import crud.jdbc.connection.DB;

import javax.swing.JButton;

public class ScreenUpdate extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenUpdate frame = new ScreenUpdate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public ScreenUpdate() {
		String idstring = JOptionPane.showInputDialog("Qual ID do contato a ser alterado ?");
		int id_correta = Integer.parseInt(idstring);

		Contato contato = new Contato();
		ContatoDAO cttDAO = new ContatoDAO();
		try {
			for (Contato c : cttDAO.listarTodos()) {
				if (id_correta == c.getId_contato()) {

					String nome = c.getNome();
					String cpf = c.getCpf();
					int idade = c.getIdade();
					String sexo = c.getSexo();
					int id_contato = c.getId_contato();
					contato.setNome(nome);
					contato.setCpf(cpf);
					contato.setIdade(idade);
					contato.setSexo(sexo);
					contato.setId_contato(id_contato);
				}

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 524);
		getContentPane().setLayout(null);

		Label label = new Label("NOME:");
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		label.setBounds(10, 89, 49, 22);
		getContentPane().add(label);

		Label label_1 = new Label("IDADE:");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_1.setBounds(10, 168, 55, 22);
		getContentPane().add(label_1);

		Label label_2 = new Label("SEXO:");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_2.setBounds(10, 207, 49, 22);
		getContentPane().add(label_2);

		Label label_3 = new Label("Masculino/Feminino/N�o Declarado");
		label_3.setForeground(new Color(255, 0, 0));
		label_3.setBounds(204, 207, 193, 22);
		getContentPane().add(label_3);

		Label label_4 = new Label("CPF:");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_4.setBounds(10, 127, 49, 22);
		getContentPane().add(label_4);
		
		//gambi
		String idSTG = Integer.toString(contato.getId_contato());
		
		TextField txtId = new TextField(idSTG);
		txtId.setBounds(71, 61, 33, 22);
		getContentPane().add(txtId);

		TextField txtNome = new TextField(contato.getNome());
		txtNome.setBounds(71, 89, 293, 22);
		getContentPane().add(txtNome);

		TextField txtCpf = new TextField(contato.getCpf());
		txtCpf.setBounds(71, 127, 184, 22);
		getContentPane().add(txtCpf);
		// gambi
		String idadeSTG = Integer.toString(contato.getIdade());

		TextField txtIdade = new TextField(idadeSTG);
		txtIdade.setBounds(71, 168, 33, 22);
		getContentPane().add(txtIdade);

		TextField txtSexo = new TextField(contato.getSexo());
		txtSexo.setBounds(71, 207, 127, 22);
		getContentPane().add(txtSexo);

		Label label_5 = new Label("2 Digitos");
		label_5.setForeground(Color.RED);
		label_5.setBounds(110, 168, 171, 22);
		getContentPane().add(label_5);

		Label label_6 = new Label("Com pontos ' . ' e Tra�o ' - '");
		label_6.setForeground(Color.RED);
		label_6.setBounds(261, 127, 171, 22);
		getContentPane().add(label_6);

		Label label_7 = new Label("ID:");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_7.setBounds(10, 61, 55, 22);
		getContentPane().add(label_7);

		JButton btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Contato contato2 = new Contato();
					ContatoDAO cttDAO = new ContatoDAO();

					String nome = txtNome.getText();
					String cpf = txtCpf.getText();
					int idade = Integer.parseInt(txtIdade.getText());
					String sexo = txtSexo.getText();
					int id_Contato = Integer.parseInt(txtId.getText());

					contato2.setNome(nome);
					contato2.setCpf(cpf);
					contato2.setIdade(idade);
					contato2.setSexo(sexo);
					contato2.setId_contato(id_Contato);

					cttDAO.update(contato2);

					JOptionPane.showMessageDialog(null, "Cadastro Alterado");

					dispose();

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("ERRO AQUI");
					dispose();
				}

			}

		});
		btnNewButton_1.setBounds(344, 452, 89, 23);
		getContentPane().add(btnNewButton_1);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {

	}
}
