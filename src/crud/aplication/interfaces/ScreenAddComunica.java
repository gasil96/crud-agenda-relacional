package crud.aplication.interfaces;

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
import crud.entities.TipoComunicaEnum;
import crud.jdbc.connection.DB;

public class ScreenAddComunica extends JFrame {
	private JTextField txtRegistro;
	private Long idSelecionado;
	
	public ScreenAddComunica(int idSelecionado) throws ParseException {
		this.setVisible(true);
		setTitle("Novo Registro de Contato");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 199);
		getContentPane().setLayout(null);

		/*
		 * ADICIONANDO BOTAO VOLTAR
		 * */
		JButton btnNewButton = new JButton("Voltar");
		
		/*
		 * COLOCANDO EVENTO CLICK NO BOTAO
		 * */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScreenList listagem = new ScreenList();
				listagem.show();
				dispose();

			}
		});
		btnNewButton.setBounds(249, 125, 89, 23);
		getContentPane().add(btnNewButton);

		JComboBox comboBoxEnum = new JComboBox();
		comboBoxEnum.setModel(new DefaultComboBoxModel(TipoComunicaEnum.values()));
		comboBoxEnum.setBounds(21, 48, 147, 27);
		getContentPane().add(comboBoxEnum);
		comboBoxEnum.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

			}
		});
		MaskFormatter maskID;
		maskID = new MaskFormatter("##");

		/*
		 * METODO SALVAR REGISTRO
		 * */
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ComunicaDAO cmcDAO = new ComunicaDAO();
				Comunica comunica = new Comunica();
				TipoComunicaEnum tipo = (TipoComunicaEnum) comboBoxEnum.getSelectedItem();

				String registro = txtRegistro.getText();
				
				comunica.setTipo(tipo);
				comunica.setRegistro(registro);
				comunica.setId_contato(idSelecionado);
				cmcDAO.newReg(comunica);
				
				setVisible(false);
				ScreenList listagemATT = new ScreenList();
				listagemATT.setVisible(true);

			}
		});

		btnNewButton_1.setBounds(340, 125, 89, 23);
		getContentPane().add(btnNewButton_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(21, 106, 408, 2);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(21, 22, 408, 2);
		getContentPane().add(separator_1);

		txtRegistro = new JTextField();
		txtRegistro.setBounds(171, 48, 258, 27);
		getContentPane().add(txtRegistro);
		txtRegistro.setColumns(10);

	}
}
