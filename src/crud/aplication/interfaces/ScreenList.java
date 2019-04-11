/*	Desenvolvido por:   Gabriel Silva
 * 	Ultima Atualização: 11/04/2019
 *	Versão:				1.0 
 */

package crud.aplication.interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import crud.dao.ComunicaDAO;
import crud.dao.ContatoDAO;
import crud.entities.Comunica;
import crud.entities.Contato;

public class ScreenList extends JFrame {

	private JPanel painelPrincipal;
	private JButton btnAlterarContato;
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
		setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\14876\\git\\crud_basa_project\\resource\\basa.png"));

		setTitle("AGENDA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 631);
		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelPrincipal);
		final DefaultTableModel modelo_contato = new DefaultTableModel();
		final DefaultTableModel modelo_comunica = new DefaultTableModel();

		/*
		 * METODO REMOVER CONTATO (POR ID)
		 */
		JButton btnApagarContato = new JButton("Apagar");
		btnApagarContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ContatoDAO cttDAO = new ContatoDAO();
				ComunicaDAO cmcDAO = new ComunicaDAO();
				Comunica valor = new Comunica();

				if (tabela_contato.getSelectedRow() >= 0) {

					Object[] options = { "Confirmar" };
					int respostaExclusao = JOptionPane.showOptionDialog(null,
							"Tem certeza que deseja EXCLUIR este contato!", "Informacao", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options, options[0]);

					if (respostaExclusao == 0) {

						int idselecionada = (int) tabela_contato.getValueAt(tabela_contato.getSelectedRow(), 0);

						valor.setId_contato(idselecionada);

						try {
							List<Comunica> listagemC = cmcDAO.listarTodasC(valor);

							if (listagemC.size() > 0) {
								JOptionPane.showMessageDialog(null,
										"IMPOSSIVEL APAGAR \n O contato selecionado possui Registros!");

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
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		/*
		 * METODO ALTERAR CONTATO
		 */
		btnAlterarContato = new JButton("Alterar");
		btnAlterarContato.addActionListener(new ActionListener() {

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

		JScrollPane scrollTabelaContato = new JScrollPane();
		scrollTabelaContato.setEnabled(false);

		JScrollPane scrollTabelaComunica = new JScrollPane();
		scrollTabelaComunica.setEnabled(false);

		JLabel labelContatos = new JLabel("CONTATOS ");
		labelContatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelContatos.setHorizontalAlignment(SwingConstants.LEFT);
		labelContatos.setBackground(Color.WHITE);

		JLabel labelRegContatos = new JLabel("REGISTRO DE CONTATOS");
		labelRegContatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelRegContatos.setHorizontalAlignment(SwingConstants.LEFT);

		JButton btnApagarRegistro = new JButton("Apagar");
		btnApagarRegistro.addActionListener(new ActionListener() {

			// METODO REMOVER COMUNICACAO
			public void actionPerformed(ActionEvent e) {
				ComunicaDAO cmcDAO = new ComunicaDAO();

				if (tabela_comunica.getSelectedRow() >= 0) {

					Object[] options = { "Confirmar" };
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
		JButton btnNovoContato = new JButton("+");
		btnNovoContato.addActionListener(new ActionListener() {
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

		/*
		 * ADICIONANDO COMUNICACAO DO USUARIO
		 */

		JButton btnNovoRegistro = new JButton("+");
		btnNovoRegistro.addActionListener(new ActionListener() {

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

		JSeparator separadorTabelas = new JSeparator();

		JLabel labelAgenda = new JLabel("AGENDA");
		labelAgenda.setFont(new Font("Tahoma", Font.BOLD, 53));
		labelAgenda.setForeground(UIManager.getColor("CheckBox.darkShadow"));
		labelAgenda.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout gl_painelPrincipal = new GroupLayout(painelPrincipal);
		gl_painelPrincipal.setHorizontalGroup(gl_painelPrincipal.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollTabelaComunica, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addComponent(scrollTabelaContato, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addGroup(gl_painelPrincipal.createSequentialGroup()
						.addGroup(gl_painelPrincipal.createParallelGroup(Alignment.LEADING).addComponent(labelContatos)
								.addComponent(labelRegContatos).addComponent(btnApagarRegistro,
										GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
						.addGroup(gl_painelPrincipal.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNovoRegistro).addComponent(btnNovoContato)
								.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
				.addComponent(separadorTabelas, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addGroup(gl_painelPrincipal.createSequentialGroup()
						.addComponent(btnApagarContato, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAlterarContato, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				.addComponent(labelAgenda, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE));
		gl_painelPrincipal
				.setVerticalGroup(gl_painelPrincipal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painelPrincipal.createSequentialGroup()
								.addComponent(labelAgenda, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_painelPrincipal.createParallelGroup(Alignment.BASELINE)
										.addComponent(labelContatos, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNovoContato))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollTabelaContato, GroupLayout.PREFERRED_SIZE, 175,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_painelPrincipal.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnApagarContato).addComponent(btnAlterarContato))
								.addGap(13)
								.addComponent(separadorTabelas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_painelPrincipal.createParallelGroup(Alignment.BASELINE)
										.addComponent(labelRegContatos, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNovoRegistro))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollTabelaComunica, GroupLayout.PREFERRED_SIZE, 142,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_painelPrincipal.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnApagarRegistro).addComponent(btnSair))
								.addContainerGap(19, Short.MAX_VALUE)));

		/*
		 * TABELA E LISTAGEM CONTATO
		 */

		tabela_contato = new JTable(modelo_contato);
		tabela_contato.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollTabelaContato.setViewportView(tabela_contato);
		painelPrincipal.setLayout(gl_painelPrincipal);

		modelo_contato.addColumn("ID");
		modelo_contato.addColumn("NOME");
		modelo_contato.addColumn("CPF");
		modelo_contato.addColumn("IDADE");
		modelo_contato.addColumn("SEXO");
		tabela_contato.setColumnSelectionInterval(1, 1);
		ContatoDAO cttDAO = new ContatoDAO();

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

		scrollTabelaComunica.setViewportView(tabela_comunica);
		modelo_comunica.addColumn("ID");
		modelo_comunica.addColumn("TIPO");
		modelo_comunica.addColumn("DESCRICAO");
		modelo_comunica.addColumn("ID REFERENCIAL");

	}
}