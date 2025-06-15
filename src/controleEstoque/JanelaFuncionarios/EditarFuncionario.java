package controleEstoque.JanelaFuncionarios;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controleEstoque.JanelaBase;
import controleEstoque.dao.FuncionarioDAO;
import controleEstoque.model.Funcionario;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarFuncionario extends JanelaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelToggleButtons;
	private JTextField textField;

	private Funcionario funcionarioSelecionado;
	private Map<JToggleButton, Funcionario> mapToggleFuncionario = new HashMap<>();

	public EditarFuncionario() {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

		setTitle("Editar Funcionario");
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);

		panelToggleButtons = new JPanel();
		panelToggleButtons.setLayout(new BoxLayout(panelToggleButtons, BoxLayout.Y_AXIS));
		panelToggleButtons.setBackground(Color.DARK_GRAY);

		JScrollPane scrollPane = new JScrollPane(panelToggleButtons);
		scrollPane.setBounds(10, 10, 360, 440);
		getContentPane().add(scrollPane);

		JLabel lblNewLabel = new JLabel("Mudar nome");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(412, 10, 197, 40);
		getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(412, 61, 314, 35);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(637, 107, 89, 23);
		btnSalvar.setEnabled(false);

		textField.getDocument().addDocumentListener(new DocumentListener() {
			private void atualizarEstadoBotao() {
				String texto = textField.getText().trim();
				btnSalvar.setEnabled(!texto.isEmpty() && funcionarioSelecionado != null);
			}

			public void insertUpdate(DocumentEvent e) {
				atualizarEstadoBotao();
			}

			public void removeUpdate(DocumentEvent e) {
				atualizarEstadoBotao();
			}

			public void changedUpdate(DocumentEvent e) {
				atualizarEstadoBotao();
			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (funcionarioSelecionado != null) {
						Funcionario funcionario = new Funcionario(funcionarioSelecionado.getId(), textField.getText());

						funcionarioDAO.atualizar(funcionario);

						JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Informação",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						throw new Exception("Selecione uma opção!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		getContentPane().add(btnSalvar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(685, 427, 89, 23);
		getContentPane().add(btnSair);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (funcionarioSelecionado != null) {
						funcionarioDAO.remover(funcionarioSelecionado.getId());
						JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Informação",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						throw new Exception("Selecione uma opção!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setBackground(Color.RED);
		btnDeletar.setBounds(637, 168, 89, 23);
		getContentPane().add(btnDeletar);

		List<Funcionario> funcionarios = funcionarioDAO.listarTodos();

		ButtonGroup grupo = new ButtonGroup();

		for (Funcionario funcionario : funcionarios) {
			JToggleButton toggle = new JToggleButton(funcionario.getNome());
			toggle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
			toggle.setAlignmentX(Component.LEFT_ALIGNMENT);
			toggle.setBackground(Color.GRAY);
			toggle.setForeground(Color.WHITE);

			grupo.add(toggle);
			panelToggleButtons.add(toggle);
			panelToggleButtons.add(Box.createRigidArea(new Dimension(0, 5)));

			mapToggleFuncionario.put(toggle, funcionario);

			toggle.addActionListener(ev -> {
				if (toggle.isSelected()) {
					funcionarioSelecionado = mapToggleFuncionario.get(toggle);
					textField.setText(funcionarioSelecionado.getNome());
				}
			});
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new EditarFuncionario().setVisible(true);
		});
	}
}
