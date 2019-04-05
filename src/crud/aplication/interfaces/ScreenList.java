package crud.aplication.interfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import crud.dao.ContatoDAO;
import crud.entities.Contato;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

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

	/**
	 * Create the frame.
	 */
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
				int respostaExclusao = 	JOptionPane.showOptionDialog(null, "Clique Confirmar para Excluir ou Cancelar para Retornar",
						"Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
						options[0]);
				
				if (respostaExclusao == 0) {
					int idselecionada = (int) tabela_contato.getValueAt(tabela_contato.getSelectedRow(), tabela_contato.getSelectedColumn());
					cttDAO.remove(idselecionada);
					JOptionPane.showMessageDialog(null, "Contato Apagado");
					dispose(); ScreenList sl = new ScreenList(); sl.show();
				}else {
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Alterar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
						.addComponent(lblRegistroDosContatos, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
						.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(lblRegistroDosContatos, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Alterar)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		
		tabela_comunica = new JTable(modelo_comunica);
		scrollPane_1.setViewportView(tabela_comunica);
		modelo_comunica.addColumn("ID");
		modelo_comunica.addColumn("TIPO");
		modelo_comunica.addColumn("DESCRIÇÃO");
		modelo_comunica.addColumn("ID REFERENCIAL");
		
		
		
		
		tabela_contato = new JTable(modelo_contato);
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

				modelo_contato.addRow(new Object[] { c.getId_contato(), c.getNome(), c.getCpf(), c.getIdade(), c.getSexo() });

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}