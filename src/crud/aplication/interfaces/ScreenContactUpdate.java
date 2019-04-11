
package crud.aplication.interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import crud.dao.ContatoDAO;
import crud.entities.Contato;
import crud.entities.TipoSexoEnum;

public class ScreenContactUpdate extends JFrame {

	private JTextField textId;
	private JTextField txtId;

	public ScreenContactUpdate(Contato contato) throws ParseException {

		// FORMA DE COMO PEGAR OS CAMPOS DO CONTATO A SER ALTERADO String idstring =
		
		MaskFormatter mascaraCPf;
		mascaraCPf = new MaskFormatter("###.###.###-##");
		getContentPane().setLayout(null);

		// GAMBI JFormattedTextField txtCpf = new JFormattedTextField(mascaraCPf);
		
		JFormattedTextField txtCpf = new JFormattedTextField(mascaraCPf);
		txtCpf.setBounds(82, 112, 119, 27);
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCpf.setText(contato.getCpf());
		getContentPane().add(txtCpf);

		Label labelNome = new Label("Nome:");
		labelNome.setBounds(20, 46, 60, 27);
		labelNome.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelNome.setAlignment(Label.RIGHT);
		getContentPane().add(labelNome);

		Label labelSexo = new Label("Sexo:");
		labelSexo.setBounds(26, 79, 54, 27);
		labelSexo.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelSexo.setAlignment(Label.RIGHT);
		getContentPane().add(labelSexo);

		Label labelCpf = new Label("CPF:");
		labelCpf.setBounds(29, 112, 51, 27);
		labelCpf.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelCpf.setAlignment(Label.RIGHT);
		getContentPane().add(labelCpf);

		Label labelIdade = new Label("Idade:");
		labelIdade.setBounds(259, 112, 67, 27);
		labelIdade.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelIdade.setAlignment(Label.RIGHT);
		getContentPane().add(labelIdade);

		// IDADE
		TextField txtIdade = new TextField(String.valueOf(contato.getIdade()));
		txtIdade.setBounds(332, 112, 40, 27);
		txtIdade.setFont(new Font("Dialog", Font.PLAIN, 14));
		getContentPane().add(txtIdade);

		TextField txtNome = new TextField(contato.getNome());
		txtNome.setBounds(82, 46, 290, 27);
		txtNome.setForeground(Color.BLACK);
		getContentPane().add(txtNome);

		JSeparator separadorBaixo = new JSeparator();
		separadorBaixo.setBounds(10, 166, 408, 2);
		getContentPane().add(separadorBaixo);

		JSeparator separadorCima = new JSeparator();
		separadorCima.setBounds(10, 21, 408, 2);
		getContentPane().add(separadorCima);

		// ADICIONAR ID CONTATO
		 
//		txtId = new JTextField(contato.getId_contato());
//		txtId.setBounds(10, 186, 22, 20);
//		getContentPane().add(txtId);
//		txtId.setColumns(10);
//		txtId.setVisible(false);
//		setTitle("Alterar Contato");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 255);

		JComboBox comboBoxSexo = new JComboBox();
		comboBoxSexo.setBounds(82, 79, 125, 27);
		
		comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		List<String> lista = new ArrayList<String>();
		Arrays.stream(TipoSexoEnum.values()).forEach( i -> lista.add(i.name()));
		
		comboBoxSexo.setModel(new DefaultComboBoxModel(lista.toArray()));
		comboBoxSexo.setSelectedItem(contato.getSexo());
		getContentPane().add(comboBoxSexo);
		
		comboBoxSexo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int indiceDoCombo = comboBoxSexo.getSelectedIndex();

			}

		});

		
		/*
		 * BOTAO ATUALIZAR CONTATO
		 * */
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(329, 179, 89, 23);
		btnAlterar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				try {

					ContatoDAO cttDAO = new ContatoDAO();

					String nome = txtNome.getText();
					String cpf = txtCpf.getText();
					int idade = Integer.parseInt(txtIdade.getText());
					String sexo = comboBoxSexo.getSelectedItem().toString();
					
					cttDAO.update(new Contato(contato.getId_contato(), nome, cpf, idade, sexo));
					JOptionPane.showMessageDialog(null, "Cadastro Alterado");
					dispose();
					new ScreenList().setVisible(true);

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		getContentPane().add(btnAlterar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(234, 179, 89, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenList listagem = new ScreenList();
				listagem.show();
				dispose();
			
			}
		});
		getContentPane().add(btnVoltar);

	}
}
