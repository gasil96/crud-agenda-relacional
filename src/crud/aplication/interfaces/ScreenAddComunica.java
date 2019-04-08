package crud.aplication.interfaces;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import crud.dao.ComunicaDAO;
import crud.entities.Comunica;
import crud.entities.Contato;
import crud.entities.TipoComunicaEnum;
import javafx.scene.control.ComboBox;

public class ScreenAddComunica extends JFrame {
	private JTextField txtRegistro;

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

	public ScreenAddComunica() throws ParseException {
		setTitle("Novo Registro de Contato");

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

		JComboBox comboBoxEnum = new JComboBox();
		comboBoxEnum.setModel(new DefaultComboBoxModel(TipoComunicaEnum.values()));
		comboBoxEnum.setBounds(21, 33, 147, 27);
		getContentPane().add(comboBoxEnum);
		comboBoxEnum.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

			}
		});
		MaskFormatter maskID;
		maskID = new MaskFormatter("##");

		JFormattedTextField txtID = new JFormattedTextField(maskID);
		txtID.setBounds(171, 71, 28, 27);
		getContentPane().add(txtID);

		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ComunicaDAO cmcDAO = new ComunicaDAO();
				Comunica comunica = new Comunica();
				System.out.println(ItemEvent.SELECTED);

				String tipo = comboBoxEnum.getSelectedItem().toString();
				
				String registro = txtRegistro.getText();
				int idCMC = Integer.parseInt(txtID.getText());
				
				// comunica.setTipo(comunica.getTipo());
				comunica.setRegistro(registro);
				comunica.setId_contato(idCMC);
				cmcDAO.newReg(comunica);

				/*
				 * String nome = txtNome.getText(); String cpf = formatoCPF.getText(); int idade
				 * = Integer.parseInt(txtIdade.getText()); String sexo =
				 * comboBoxSexo.getSelectedItem().toString(); contato.setNome(nome);
				 * contato.setCpf(cpf); contato.setIdade(idade); contato.setSexo(sexo);
				 * cttDAO.create(contato); JOptionPane.showMessageDialog(null,
				 * "Novo Contato Cadastrado!"); dispose();
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

		JLabel lblIdprovisorio = new JLabel("ID (provisorio):");
		lblIdprovisorio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdprovisorio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdprovisorio.setBounds(75, 71, 93, 27);
		getContentPane().add(lblIdprovisorio);

		txtRegistro = new JTextField();
		txtRegistro.setBounds(171, 33, 258, 27);
		getContentPane().add(txtRegistro);
		txtRegistro.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = comboBoxEnum.getSelectedItem().toString();
				System.out.println(tipo);
			}
		});
		btnNewButton_2.setBounds(75, 111, 89, 23);
		getContentPane().add(btnNewButton_2);

	}
}
