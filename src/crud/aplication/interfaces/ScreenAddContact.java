package crud.aplication.interfaces;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import crud.dao.ContatoDAO;
import crud.entities.Contato;
import crud.entities.TipoComunicaEnum;
import crud.jdbc.connection.DB;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class ScreenAddContact extends JFrame {

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ScreenAddContact frame = new
	 * ScreenAddContact(); frame.setVisible(true);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } });
	 * 
	 * }
	 */

	public ScreenAddContact() throws Exception {
		setVisible(true);
		setTitle("Novo Contato");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 254);
		getContentPane().setLayout(null);

		Label label = new Label("Nome:");
		label.setAlignment(Label.RIGHT);
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		label.setBounds(21, 49, 60, 27);
		getContentPane().add(label);

		Label label_1 = new Label("Idade:");
		label_1.setAlignment(Label.RIGHT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_1.setBounds(264, 82, 67, 27);
		getContentPane().add(label_1);

		Label label_2 = new Label("Sexo:");
		label_2.setAlignment(Label.RIGHT);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_2.setBounds(27, 82, 54, 27);
		getContentPane().add(label_2);

		Label label_4 = new Label("CPF:");
		label_4.setAlignment(Label.RIGHT);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_4.setBounds(30, 115, 51, 27);
		getContentPane().add(label_4);

		TextField txtNome = new TextField();
		txtNome.setForeground(Color.BLACK);
		txtNome.setBounds(87, 49, 290, 27);
		getContentPane().add(txtNome);

		TextField txtIdade = new TextField("20");
		txtIdade.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtIdade.setBounds(337, 82, 40, 27);
		getContentPane().add(txtIdade);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScreenList listagem = new ScreenList();
				listagem.show();
				dispose();
			}
		});
		btnNewButton.setBounds(249, 180, 89, 23);
		getContentPane().add(btnNewButton);

		JComboBox comboBoxSexo = new JComboBox();
		comboBoxSexo.setToolTipText("Sexo");
		comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] { "MASCULINO", "FEMININO", "NAO BINARIO" }));
		comboBoxSexo.setBounds(87, 82, 138, 27);
		getContentPane().add(comboBoxSexo);
		comboBoxSexo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int indiceDoCombo = comboBoxSexo.getSelectedIndex();
			}

		});

		MaskFormatter mascaraCPf;
		mascaraCPf = new MaskFormatter("###.###.###-##");

		JFormattedTextField formatoCPF = new JFormattedTextField(mascaraCPf);
		formatoCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		formatoCPF.setBounds(87, 115, 119, 27);
		getContentPane().add(formatoCPF);

		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ContatoDAO cttDAO = new ContatoDAO();
				Contato contato = new Contato();

				String nome = txtNome.getText();
				String cpf = formatoCPF.getText();
				int idade = Integer.parseInt(txtIdade.getText());
				String sexo = comboBoxSexo.getSelectedItem().toString();
				contato.setNome(nome);
				contato.setCpf(cpf);
				contato.setIdade(idade);
				contato.setSexo(sexo);
				cttDAO.create(contato, DB.getConnection());
				JOptionPane.showMessageDialog(null, "Novo Contato Cadastrado!");
				dispose();

				ScreenList listagemATT = new ScreenList();
				listagemATT.show();

			}

		});

		btnNewButton_1.setBounds(340, 180, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(21, 167, 408, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(21, 22, 408, 2);
		getContentPane().add(separator_1);

	}
}
