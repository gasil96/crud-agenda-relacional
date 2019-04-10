package crud.aplication.interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import crud.dao.ComunicaDAO;
import crud.dao.ContatoDAO;
import crud.entities.Comunica;
import crud.entities.Contato;
import crud.jdbc.connection.DB;

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

		setTitle("AGENDA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final DefaultTableModel modelo_contato = new DefaultTableModel();
		final DefaultTableModel modelo_comunica = new DefaultTableModel();

		/*
		 * METODO REMOVER CONTATO (POR ID)
		 */
		JButton btnNewButton = new JButton("Apagar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ContatoDAO cttDAO = new ContatoDAO();
				ComunicaDAO cmcDAO = new ComunicaDAO();
				Comunica valor = new Comunica();

				if (tabela_contato.getSelectedRow() >= 0) {

					Object[] options = { "Confirmar" };
					int respostaExclusao = JOptionPane.showOptionDialog(null,
							"Tem certeza que deseja EXCLUIR este contato!", "Informacao",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

					if (respostaExclusao == 0) {

						int idselecionada = (int) tabela_contato.getValueAt(tabela_contato.getSelectedRow(), 0);

						valor.setId_contato(idselecionada);

						try {
							List<Comunica> listagemC = cmcDAO.listarTodasC(valor);

							if (listagemC.size() > 0) {
								JOptionPane.showMessageDialog(null,
										"IMPOSSIVEL APAGAR \n O contato selecionado possui uma ou mais comunicacoes!");

							} else {

								cttDAO.remove(idselecionada);

								JOptionPane.showMessageDialog(null, "Contato Apagado");
								modelo_contato.setRowCount(0);

								// RECARREGA JTABLE APOS EXCLUSAO ******************************

								try {
									List<Contato> listarTodos = cttDAO.listarTodos();
									if (listarTodos.size() > 0) {
										for (Contato c : listarTodos) {
											modelo_contato.addRow(new Object[] { c.getId_contato(), c.getNome(),
													c.getCpf(), c.getIdade(), c.getSexo() });

										}
									}

								} catch (Exception e1) {
									e1.printStackTrace();
								}
								// DEVE TER UM JEITO MELHOR DE FAZER ISSO **************

								ScreenList listagemATT = new ScreenList();

							}

						} catch (Exception e2) {
							e2.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Operacao Cancelada");

					}

				} else {
					JOptionPane.showMessageDialog(null, "Selecione um contato para ser apagado!");
				}

			}
		});

		/*
		 * METODO SAIR DO SISTEMA
		 */
		JButton btnNewButton_1 = new JButton("Sair");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		/*
		 * METODO ALTERAR CONTATO
		 */
		Alterar = new JButton("Alterar");
		Alterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ContatoDAO contatoDAO = new ContatoDAO();

				if (tabela_contato.getSelectedRow() >= 0) {

					Object elemento = tabela_contato.getValueAt(tabela_contato.getSelectedRow(), 0);

					if (elemento != null) {
						try {

							Contato contato = contatoDAO.buscarPorId((int) elemento);
							if (contato != null) {
								setVisible(false);
								ScreenContactUpdate screenContactUpdate = new ScreenContactUpdate(contato);
								screenContactUpdate.setVisible(true);
							}

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "T");
							e1.printStackTrace();
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um contato para ser alterado!");
				}

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

			// METODO REMOVER COMUNICACAO
			public void actionPerformed(ActionEvent e) {
				ComunicaDAO cmcDAO = new ComunicaDAO();

				if (tabela_comunica.getSelectedRow() >= 0) {

					Object[] options = { "Confirmar", "Cancelar" };
					int respostaExclusao = JOptionPane.showOptionDialog(null,
							"Clique Confirmar para Excluir ou Cancelar para Retornar", "Informacao",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

					if (respostaExclusao == 0) {

						int idselecionada = (int) tabela_comunica.getValueAt(tabela_comunica.getSelectedRow(), 0);
						cmcDAO.remove(idselecionada);
						JOptionPane.showMessageDialog(null, "Contato Apagado");
						dispose();
						ScreenList listagemATT = new ScreenList();
						listagemATT.show();

					} else {
						JOptionPane.showMessageDialog(null, "Operacao Cancelada");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma comunicacao para ser apagada!");
				}

			}
		});

		/*
		 * ADICIONAR CONTATO
		 **/
		JButton addNewContact = new JButton("+");
		addNewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenAddContact adicionar;
				try {
					adicionar = new ScreenAddContact();
					dispose();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		JPanel panel = new JPanel();

		/*
		 * ADICIONANDO COMUNICACAO DO USUARIO
		 */

		JButton addNewRegistro = new JButton("+");
		addNewRegistro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (tabela_contato.getSelectedRow() >= 0) {
					try {
						new ScreenAddComunica((int) tabela_contato.getValueAt(tabela_contato.getSelectedRow(), 0));
						dispose();

					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um contato");
				}
			}
		});

		JSeparator separator = new JSeparator();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
								.addComponent(lblRegistroDosContatos)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(addNewRegistro, GroupLayout.PREFERRED_SIZE, 41,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(addNewContact).addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE,
										89, GroupLayout.PREFERRED_SIZE)))
				.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING,
						gl_contentPane.createSequentialGroup()
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(Alterar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
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
										.addComponent(btnNewButton_1))
								.addContainerGap(19, Short.MAX_VALUE)));

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("AGENDA");
		lblNewJgoodiesTitle.setBackground(Color.YELLOW);
		panel.add(lblNewJgoodiesTitle);

		/*
		 * TABELA E LISTAGEM CONTATO
		 */

		tabela_contato = new JTable(modelo_contato);
		tabela_contato.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(tabela_contato);
		contentPane.setLayout(gl_contentPane);

		modelo_contato.addColumn("ID");
		modelo_contato.addColumn("NOME");
		modelo_contato.addColumn("CPF");
		modelo_contato.addColumn("IDADE");
		modelo_contato.addColumn("SEXO");
		ContatoDAO cttDAO = new ContatoDAO();
		tabela_contato.setColumnSelectionInterval(1, 1);

		try {
			List<Contato> listarTodos = cttDAO.listarTodos();
			if (listarTodos.size() > 0) {
				for (Contato c : listarTodos) {
					modelo_contato.addRow(
							new Object[] { c.getId_contato(), c.getNome(), c.getCpf(), c.getIdade(), c.getSexo() });

				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		tabela_comunica = new JTable(modelo_comunica);

		tabela_contato.addMouseListener(new MouseListener() {

			/*
			 * ADICIONAR EVENTO DE CLICK NA TABELA DE LISTAGEM DO USUARIO
			 */
			public void mouseClicked(MouseEvent e) {
				int selecaoC = 0;
				Object selecao = tabela_contato.getValueAt(tabela_contato.getSelectedRow(), 0);
				selecaoC = Integer.parseInt(selecao.toString());
				ComunicaDAO cmcDAO = new ComunicaDAO();
				Comunica valor = new Comunica();
				valor.setId_contato(selecaoC);
				modelo_comunica.setRowCount(0);
				try {
					/*
					 * LISTAGEM TABELA COMUNICA
					 */
					// PASSADO 'VALOR' PARA DENTRO DO MÉTODO ( AJUDA DO DAVIDSON )
					List<Comunica> listarTodasC = cmcDAO.listarTodasC(valor);
					if (listarTodasC.size() > 0) {
						for (Comunica cmc : listarTodasC) {
							modelo_comunica.addRow(new Object[] { cmc.getId_comunica(), cmc.getTipo(),
									cmc.getRegistro(), cmc.getId_contato() });
						}

					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		/*
		 * TABELA COMUNICA
		 */

		scrollPane_1.setViewportView(tabela_comunica);
		modelo_comunica.addColumn("ID");
		modelo_comunica.addColumn("TIPO");
		modelo_comunica.addColumn("DESCRICAO");
		modelo_comunica.addColumn("ID REFERENCIAL");

	}
}