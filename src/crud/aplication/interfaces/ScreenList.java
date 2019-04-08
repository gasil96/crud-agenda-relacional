package crud.aplication.interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import crud.dao.ComunicaDAO;
import crud.dao.ContatoDAO;
import crud.entities.Comunica;
import crud.entities.Contato;
import javax.swing.JSeparator;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class ScreenList extends JFrame {

	private JPanel contentPane;
	private JButton Alterar;
	private JTable tabela_contato;
	private JTable tabela_comunica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenList frame = new ScreenList();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScreenList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final DefaultTableModel modelo_contato = new DefaultTableModel();
		final DefaultTableModel modelo_comunica = new DefaultTableModel();

		JButton btnNewButton = new JButton("Apagar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContatoDAO cttDAO = new ContatoDAO();
				ScreenList listagem = new ScreenList();

				Object[] options = { "Confirmar", "Cancelar" };
				int respostaExclusao = JOptionPane.showOptionDialog(null,
						"Clique Confirmar para Excluir ou Cancelar para Retornar", "Informação",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				if (respostaExclusao == 0) {

					int idselecionada = (int) tabela_contato.getValueAt(tabela_contato.getSelectedRow(),
							tabela_contato.getSelectedColumn());
					cttDAO.remove(idselecionada);
					JOptionPane.showMessageDialog(null, "Contato Apagado");
					dispose();
					ScreenList listagemATT = new ScreenList();
					listagemATT.show();

				} else {
					JOptionPane.showMessageDialog(null, "Operação Cancelada");
				}
			}
		});

		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});

		Alterar = new JButton("Alterar");
		Alterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ContatoDAO cttDAO = new ContatoDAO();

				ScreenUpdate telaAlterar = new ScreenUpdate();
				telaAlterar.show();

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);

		JLabel lblNewLabel = new JLabel("CONTATOS ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBackground(Color.WHITE);

		JLabel lblRegistroDosContatos = new JLabel("REGISTRO DE CONTATOS");
		lblRegistroDosContatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegistroDosContatos.setHorizontalAlignment(SwingConstants.LEFT);

		JButton button = new JButton("Apagar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton button_1 = new JButton("Alterar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton addNewContact = new JButton("+");
		addNewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenAddContact adicionar;
				try {
					adicionar = new ScreenAddContact();
					adicionar.show();
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		JPanel panel = new JPanel();

		JButton addNewRegistro = new JButton("+");
		addNewRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenAddComunica addComunica = new ScreenAddComunica();
				addComunica.show();
				dispose();

			}
		});

		JSeparator separator = new JSeparator();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRegistroDosContatos)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(Alterar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(addNewRegistro, GroupLayout.PREFERRED_SIZE, 41,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(addNewContact).addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE,
										89, GroupLayout.PREFERRED_SIZE)))
				.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addGap(11)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(addNewContact))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton).addComponent(Alterar))
								.addGap(13)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblRegistroDosContatos, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(addNewRegistro))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(button)
										.addComponent(button_1).addComponent(btnNewButton_1))
								.addContainerGap(19, Short.MAX_VALUE)));

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("New JGoodies title");
		panel.add(lblNewJgoodiesTitle);

		tabela_contato = new JTable(modelo_contato);
		tabela_contato.setSurrendersFocusOnKeystroke(true);
		tabela_contato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tabela_contato);
		contentPane.setLayout(gl_contentPane);

		modelo_contato.addColumn("ID");
		modelo_contato.addColumn("NOME");
		modelo_contato.addColumn("CPF");
		modelo_contato.addColumn("IDADE");
		modelo_contato.addColumn("SEXO");
		ContatoDAO cttDAO = new ContatoDAO();
		Contato contato = new Contato();
		tabela_contato.setColumnSelectionInterval(1, 1);
		tabela_contato.setRowSelectionAllowed(false);

		try {
			for (Contato c : cttDAO.listarTodos()) {

				modelo_contato
						.addRow(new Object[] { c.getId_contato(), c.getNome(), c.getCpf(), c.getIdade(), c.getSexo() });

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		tabela_comunica = new JTable(modelo_comunica);
		scrollPane_1.setViewportView(tabela_comunica);
		modelo_comunica.addColumn("ID");
		modelo_comunica.addColumn("TIPO");
		modelo_comunica.addColumn("DESCRIÇÃO");
		modelo_comunica.addColumn("ID REFERENCIAL");

		Comunica comunicao = new Comunica();
		ComunicaDAO cmcDAO = new ComunicaDAO();

		try {
			for (Comunica cmc : cmcDAO.listarTodasC()) {

				modelo_comunica.addRow(
						new Object[] { cmc.getId_comunica(), cmc.getTipo(), cmc.getRegistro(), cmc.getId_contato() });

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}