package crud.aplication.interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.text.MaskFormatter;

import crud.dao.ContatoDAO;
import crud.entities.Contato;
import crud.entities.TipoSexoEnum;

public class ScreenAddContact extends JFrame {

	public ScreenAddContact() throws Exception {
		setVisible(true);
		setTitle("Novo Contato");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 254);
		getContentPane().setLayout(null);

		Label labelNome = new Label("Nome:");
		labelNome.setAlignment(Label.RIGHT);
		labelNome.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelNome.setBounds(21, 49, 60, 27);
		getContentPane().add(labelNome);

		Label labelIdade = new Label("Idade:");
		labelIdade.setAlignment(Label.RIGHT);
		labelIdade.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelIdade.setBounds(264, 82, 67, 27);
		getContentPane().add(labelIdade);

		Label labelSexo = new Label("Sexo:");
		labelSexo.setAlignment(Label.RIGHT);
		labelSexo.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelSexo.setBounds(27, 82, 54, 27);
		getContentPane().add(labelSexo);

		Label labelCpf = new Label("CPF:");
		labelCpf.setAlignment(Label.RIGHT);
		labelCpf.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelCpf.setBounds(30, 115, 51, 27);
		getContentPane().add(labelCpf);

		TextField txtNome = new TextField();
		txtNome.setForeground(Color.BLACK);
		txtNome.setBounds(87, 49, 290, 27);
		getContentPane().add(txtNome);

		TextField txtIdade = new TextField("20");
		txtIdade.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtIdade.setBounds(337, 82, 40, 27);
		getContentPane().add(txtIdade);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScreenList listagem = new ScreenList();
				listagem.show();
				dispose();
			}
		});
		btnVoltar.setBounds(249, 180, 89, 23);
		getContentPane().add(btnVoltar);

		
		// cria um ArrayList baseado no enum e dentro de um foreach puxa o enum em string /java8
		List<String> lista = new ArrayList<String>();
		Arrays.stream(TipoSexoEnum.values()).forEach( i -> lista.add(i.name()));
		
		
		JComboBox comboBoxSexo = new JComboBox();
		comboBoxSexo.setModel(new DefaultComboBoxModel(lista.toArray()));
		comboBoxSexo.setToolTipText("Sexo");
		comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
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

		JFormattedTextField formatoCpf = new JFormattedTextField(mascaraCPf);
		formatoCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		formatoCpf.setBounds(87, 115, 119, 27);
		getContentPane().add(formatoCpf);

		/*
		 * METODO SALVAR
		 * */
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				ContatoDAO cttDAO = new ContatoDAO();

				Contato contato = new Contato();
				String nome = txtNome.getText();
				String cpf = formatoCpf.getText();
				int idade = Integer.parseInt(txtIdade.getText());
				String sexo = comboBoxSexo.getSelectedItem().toString();
				
				contato.setNome(nome);
				contato.setCpf(cpf);
				contato.setIdade(idade);
				contato.setSexo(sexo);
				
				cttDAO.create(contato);
				JOptionPane.showMessageDialog(null, "Novo Contato Cadastrado!");
				dispose();

				ScreenList listagemATT = new ScreenList();
				listagemATT.show();

			}

		});

		btnSalvar.setBounds(340, 180, 89, 23);
		getContentPane().add(btnSalvar);
		
		JSeparator separadorCima = new JSeparator();
		separadorCima.setBounds(21, 22, 408, 2);
		getContentPane().add(separadorCima);
		
		JSeparator separadorBaixo = new JSeparator();
		separadorBaixo.setBounds(21, 167, 408, 2);
		getContentPane().add(separadorBaixo);

	}
}
