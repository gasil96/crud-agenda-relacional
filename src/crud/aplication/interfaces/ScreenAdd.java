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
import javax.swing.JList;

public class ScreenAdd extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenAdd frame = new ScreenAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public ScreenAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 524);
		getContentPane().setLayout(null);

		Label label = new Label("NOME:");
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		label.setBounds(63, 89, 55, 22);
		getContentPane().add(label);

		Label label_1 = new Label("IDADE:");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_1.setBounds(63, 168, 55, 22);
		getContentPane().add(label_1);

		Label label_2 = new Label("SEXO:");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_2.setBounds(69, 207, 49, 22);
		getContentPane().add(label_2);

		Label label_4 = new Label("CPF:");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_4.setBounds(80, 127, 44, 22);
		getContentPane().add(label_4);

		TextField txtNome = new TextField("");
		txtNome.setBounds(124, 89, 293, 22);
		getContentPane().add(txtNome);

		TextField txtCpf = new TextField();
		txtCpf.setBounds(124, 127, 184, 22);
		getContentPane().add(txtCpf);

		TextField txtIdade = new TextField();
		txtIdade.setBounds(124, 168, 33, 22);
		getContentPane().add(txtIdade);

		TextField txtSexo = new TextField();
		txtSexo.setBounds(124, 207, 127, 22);
		getContentPane().add(txtSexo);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScreenMain telaPrincipal = new ScreenMain();
				telaPrincipal.show();
				dispose();
			}
		});
		btnNewButton.setBounds(253, 452, 89, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nome = txtNome.getText();
				String cpf = txtCpf.getText();
				int idade = Integer.parseInt(txtIdade.getText());
				String sexo = txtSexo.getText();
				ContatoDAO cttDAO = new ContatoDAO();
				Contato contato = new Contato();
				contato.setNome(nome);
				contato.setCpf(cpf);
				contato.setIdade(idade);
				contato.setSexo(sexo);
				cttDAO.create(contato);
				JOptionPane.showMessageDialog(null, "Novo Contato Cadastrado!");
				ScreenMain telaPrincipal = new ScreenMain();
				telaPrincipal.show();
				dispose();

			}

		});
		btnNewButton_1.setBounds(344, 452, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		Label label_7 = new Label("EMAIL:");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_7.setBounds(68, 250, 55, 22);
		getContentPane().add(label_7);
		
		TextField txtEmail = new TextField();
		txtEmail.setBounds(124, 250, 184, 22);
		getContentPane().add(txtEmail);
		
		Label label_8 = new Label("CELULAR:");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_8.setBounds(39, 292, 84, 22);
		getContentPane().add(label_8);
		
		TextField textField = new TextField();
		textField.setBounds(124, 292, 184, 22);
		getContentPane().add(textField);
		
		Label label_9 = new Label("COMERCIAL: ");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_9.setBounds(21, 329, 102, 22);
		getContentPane().add(label_9);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(124, 329, 184, 22);
		getContentPane().add(textField_1);
		
		Label label_10 = new Label("RESIDENCIAL:");
		label_10.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_10.setBounds(10, 364, 113, 22);
		getContentPane().add(label_10);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(124, 364, 184, 22);
		getContentPane().add(textField_2);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {

	}
}
