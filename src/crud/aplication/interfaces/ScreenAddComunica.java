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
		JButton btnVoltar = new JButton("Voltar");
		
		/*
		 * COLOCANDO EVENTO CLICK NO BOTAO
		 * */
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScreenList listagem = new ScreenList();
				listagem.show();
				dispose();

			}
		});
		btnVoltar.setBounds(249, 125, 89, 23);
		getContentPane().add(btnVoltar);

		JComboBox comboBoxTipo = new JComboBox();
		comboBoxTipo.setModel(new DefaultComboBoxModel(TipoComunicaEnum.values()));
		comboBoxTipo.setBounds(21, 48, 147, 27);
		getContentPane().add(comboBoxTipo);
		comboBoxTipo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

			}
		});
		MaskFormatter maskID;
		maskID = new MaskFormatter("##");

		/*
		 * METODO SALVAR REGISTRO
		 * */
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ComunicaDAO cmcDAO = new ComunicaDAO();
				Comunica comunica = new Comunica();
				TipoComunicaEnum tipo = (TipoComunicaEnum) comboBoxTipo.getSelectedItem();

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

		btnSalvar.setBounds(340, 125, 89, 23);
		getContentPane().add(btnSalvar);

		JSeparator separadorCima = new JSeparator();
		separadorCima.setBounds(21, 22, 408, 2);
		getContentPane().add(separadorCima);
		
				JSeparator separadorBaixo = new JSeparator();
				separadorBaixo.setBounds(21, 106, 408, 2);
				getContentPane().add(separadorBaixo);

		txtRegistro = new JTextField();
		txtRegistro.setBounds(171, 48, 258, 27);
		getContentPane().add(txtRegistro);
		txtRegistro.setColumns(10);

	}
}
