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

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import crud.dao.ContatoDAO;
import crud.entities.Contato;
import javax.swing.JButton;

public class ScreenAdd extends JFrame {

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public ScreenAdd() {
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

		Label label_3 = new Label("Masculino/Feminino/Não Declarado");
		label_3.setForeground(new Color(255, 0, 0));
		label_3.setBounds(204, 207, 171, 22);
		getContentPane().add(label_3);

		Label label_4 = new Label("CPF:");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_4.setBounds(10, 127, 49, 22);
		getContentPane().add(label_4);

		TextField txtNome = new TextField();
		txtNome.setBounds(71, 89, 293, 22);
		getContentPane().add(txtNome);

		TextField txtCpf = new TextField();
		txtCpf.setBounds(71, 127, 184, 22);
		getContentPane().add(txtCpf);

		TextField txtIdade = new TextField();
		txtIdade.setBounds(71, 168, 33, 22);
		getContentPane().add(txtIdade);

		TextField txtSexo = new TextField();
		txtSexo.setBounds(71, 207, 127, 22);
		getContentPane().add(txtSexo);

		Label label_5 = new Label("2 Digitos");
		label_5.setForeground(Color.RED);
		label_5.setBounds(110, 168, 171, 22);
		getContentPane().add(label_5);

		Label label_6 = new Label("Com pontos ' . ' e Traço ' - '");
		label_6.setForeground(Color.RED);
		label_6.setBounds(261, 127, 171, 22);
		getContentPane().add(label_6);

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
				try {
					cttDAO.create(contato);
					JOptionPane.showMessageDialog(null, "Novo Contato Cadastrado!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Problemas ao Cadastrar Contato!");
				}
			}
		});
		btnNewButton_1.setBounds(344, 452, 89, 23);
		getContentPane().add(btnNewButton_1);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {

	}
}
