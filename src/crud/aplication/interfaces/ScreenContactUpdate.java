
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

		// GAMBI JFormattedTextField txtCpf = new JFormattedTextField(mascaraCPf);
		
		JFormattedTextField txtCpf = new JFormattedTextField(mascaraCPf);
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCpf.setBounds(82, 112, 119, 27);
		txtCpf.setText(contato.getCpf());
		getContentPane().add(txtCpf);

		Label label_Nome = new Label("Nome:");
		label_Nome.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_Nome.setAlignment(Label.RIGHT);
		label_Nome.setBounds(20, 46, 60, 27);
		getContentPane().add(label_Nome);

		Label label_Sexo = new Label("Sexo:");
		label_Sexo.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_Sexo.setAlignment(Label.RIGHT);
		label_Sexo.setBounds(26, 79, 54, 27);
		getContentPane().add(label_Sexo);

		Label label_CPF = new Label("CPF:");
		label_CPF.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_CPF.setAlignment(Label.RIGHT);
		label_CPF.setBounds(29, 112, 51, 27);
		getContentPane().add(label_CPF);

		Label label_Idade = new Label("Idade:");
		label_Idade.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_Idade.setAlignment(Label.RIGHT);
		label_Idade.setBounds(259, 112, 67, 27);
		getContentPane().add(label_Idade);

		// IDADE
		TextField txtIdade = new TextField(String.valueOf(contato.getIdade()));
		txtIdade.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtIdade.setBounds(332, 112, 40, 27);
		getContentPane().add(txtIdade);

		TextField txtNome = new TextField(contato.getNome());
		txtNome.setForeground(Color.BLACK);
		txtNome.setBounds(82, 46, 290, 27);
		getContentPane().add(txtNome);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 166, 408, 2);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 21, 408, 2);
		getContentPane().add(separator_1);

		// ADICIONAR ID CONTATO
		 
		txtId = new JTextField(contato.getId_contato());
		txtId.setBounds(191, 186, 22, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		setTitle("Alterar Contato");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 255);
		getContentPane().setLayout(null);

		JComboBox comboBoxSexo = new JComboBox();
		
		comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		List<String> lista = new ArrayList<String>();
		Arrays.stream(TipoSexoEnum.values()).forEach( i -> lista.add(i.name()));
		
		comboBoxSexo.setModel(new DefaultComboBoxModel(lista.toArray()));
		comboBoxSexo.setSelectedItem(contato.getSexo());
		comboBoxSexo.setBounds(82, 79, 125, 27);
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
		JButton bttAlterar = new JButton("Alterar");
		bttAlterar.addActionListener(new ActionListener() {
			
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
		bttAlterar.setBounds(329, 179, 89, 23);
		getContentPane().add(bttAlterar);

	}
}
