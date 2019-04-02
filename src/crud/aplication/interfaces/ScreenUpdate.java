package crud.aplication.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import crud.dao.ContatoDAO;
import crud.entities.Contato;
import javax.swing.JPanel;

public class ScreenUpdate extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenUpdate frame = new ScreenUpdate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	public ScreenUpdate() {
		String idstring = JOptionPane.showInputDialog("Qual ID do contato a ser alterado ?");
		int id_correta = Integer.parseInt(idstring);

		Contato contato = new Contato();
		ContatoDAO cttDAO = new ContatoDAO();
		try {
			for (Contato c : cttDAO.listarTodos()) {
				if (id_correta == c.getId_contato()) {

					String nome = c.getNome();
					String cpf = c.getCpf();
					int idade = c.getIdade();
					String sexo = c.getSexo();
					int id_contato = c.getId_contato();
					contato.setNome(nome);
					contato.setCpf(cpf);
					contato.setIdade(idade);
					contato.setSexo(sexo);
					contato.setId_contato(id_contato);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 524);
		getContentPane().setLayout(null);

		Label label = new Label("NOME:");
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		label.setBounds(69, 119, 55, 22);
		getContentPane().add(label);

		Label label_1 = new Label("IDADE:");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_1.setBounds(68, 175, 55, 22);
		getContentPane().add(label_1);

		Label label_2 = new Label("SEXO:");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_2.setBounds(74, 203, 49, 22);
		getContentPane().add(label_2);

		Label label_4 = new Label("CPF:");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_4.setBounds(85, 147, 39, 22);
		getContentPane().add(label_4);

		// gambi
		String idSTG = Integer.toString(contato.getId_contato());

		TextField txtId = new TextField(idSTG);
		txtId.setBounds(130, 91, 33, 22);
		getContentPane().add(txtId);

		TextField txtNome = new TextField(contato.getNome());
		txtNome.setBounds(130, 119, 293, 22);
		getContentPane().add(txtNome);

		TextField txtCpf = new TextField(contato.getCpf());
		txtCpf.setBounds(130, 147, 184, 22);
		getContentPane().add(txtCpf);
		
		//
		
		String idadeSTG = Integer.toString(contato.getIdade());

		TextField txtIdade = new TextField(idadeSTG);
		txtIdade.setBounds(129, 175, 33, 22);
		getContentPane().add(txtIdade);

		TextField txtSexo = new TextField(contato.getSexo());
		txtSexo.setBounds(129, 203, 127, 22);
		getContentPane().add(txtSexo);

		Label label_7 = new Label("ID:");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_7.setBounds(101, 91, 23, 22);
		getContentPane().add(label_7);

		JButton btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {

					Contato contato2 = new Contato();
					ContatoDAO cttDAO = new ContatoDAO();

					String nome = txtNome.getText();
					String cpf = txtCpf.getText();
					int idade = Integer.parseInt(txtIdade.getText());
					String sexo = txtSexo.getText();
					int id_Contato = Integer.parseInt(txtId.getText());

					contato2.setNome(nome);
					contato2.setCpf(cpf);
					contato2.setIdade(idade);
					contato2.setSexo(sexo);
					contato2.setId_contato(id_Contato);

					cttDAO.update(contato2);

					JOptionPane.showMessageDialog(null, "Cadastro Alterado");

					dispose();

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("ERRO AQUI");
					dispose();
				}

			}

		});
		btnNewButton_1.setBounds(353, 452, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		TextField txtEmail = new TextField();
		txtEmail.setBounds(129, 231, 184, 22);
		getContentPane().add(txtEmail);
		
		Label label_8 = new Label("EMAIL:");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_8.setBounds(68, 231, 55, 22);
		getContentPane().add(label_8);
		
		Label label_9 = new Label("CELULAR:");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_9.setBounds(44, 259, 79, 22);
		getContentPane().add(label_9);
		
		TextField txtCelular = new TextField();
		txtCelular.setBounds(129, 259, 184, 22);
		getContentPane().add(txtCelular);
		
		Label label_10 = new Label("COMERCIAL: ");
		label_10.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_10.setBounds(21, 287, 102, 22);
		getContentPane().add(label_10);
		
		TextField txtComercial = new TextField();
		txtComercial.setBounds(129, 287, 184, 22);
		getContentPane().add(txtComercial);
		
		Label label_11 = new Label("RESIDENCIAL:");
		label_11.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_11.setBounds(10, 315, 113, 22);
		getContentPane().add(label_11);
		
		TextField txtResidencial = new TextField();
		txtResidencial.setBounds(129, 315, 184, 22);
		getContentPane().add(txtResidencial);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 452, 85);
		getContentPane().add(panel);

		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {

	}
}
