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
		setBounds(100, 100, 561, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final DefaultTableModel modelo_contato = new DefaultTableModel();
		final DefaultTableModel modelo_comunica = new DefaultTableModel();

		JButton btnNewButton = new JButton("Apagar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContatoDAO cttDAO = new ContatoDAO();

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
					ScreenList sl = new ScreenList();
					sl.show();
				} else {
					JOptionPane.showMessageDialog(null, "Operação Cancelada");
				}
			}
		});

		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenMain telaPrincipal = new ScreenMain();
				telaPrincipal.show();
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

		JLabel lblRegistroDosContatos = new JLabel("REGISTRO DOS CONTATOS");
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

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(button, GroupLayout.PREFERRED_SIZE, 89,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 89,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
												.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 89,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(Alterar, GroupLayout.PREFERRED_SIZE, 89,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 341, Short.MAX_VALUE)))
								.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblRegistroDosContatos, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
								.addGap(105)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(22)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
						.addComponent(Alterar))
				.addGap(19)
				.addComponent(lblRegistroDosContatos, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton_1)
						.addComponent(button).addComponent(button_1))
				.addContainerGap()));

		tabela_contato = new JTable(modelo_contato);
		tabela_contato.setSurrendersFocusOnKeystroke(true);
		tabela_contato.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(tabela_contato);
		contentPane.setLayout(gl_contentPane);

		modelo_contato.addColumn("ID");
		modelo_contato.addColumn("NOME");
		modelo_contato.addColumn("CPF");
		modelo_contato.addColumn("IDADE");
		modelo_contato.addColumn("SEXO");
		ContatoDAO cttDAO = new ContatoDAO();
		Contato contato = new Contato();

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