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

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class ScreenAddComunica extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenAddComunica frame = new ScreenAddComunica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public ScreenAddComunica()  {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 254);
		getContentPane().setLayout(null);

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


		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ContatoDAO cttDAO = new ContatoDAO();
				Contato contato = new Contato();
/*
				String nome = txtNome.getText();
				String cpf = formatoCPF.getText();
				int idade = Integer.parseInt(txtIdade.getText());
				String sexo = comboBoxSexo.getSelectedItem().toString();
				contato.setNome(nome);
				contato.setCpf(cpf);
				contato.setIdade(idade);
				contato.setSexo(sexo);
				cttDAO.create(contato);
				JOptionPane.showMessageDialog(null, "Novo Contato Cadastrado!");
				dispose();
*/
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
