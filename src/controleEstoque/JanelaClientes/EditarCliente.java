package controleEstoque.JanelaClientes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controleEstoque.JanelaBase;
import controleEstoque.dao.ClienteDAO;
import controleEstoque.model.Cliente;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCliente extends JanelaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelToggleButtons;
	private JTextField textField;

	private Cliente clienteSelecionado;
	private Map<JToggleButton, Cliente> mapToggleCliente = new HashMap<>();

	public EditarCliente() {

		ClienteDAO clienteDAO = new ClienteDAO();

		setTitle("Editar Cliente");
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
				btnSalvar.setEnabled(!texto.isEmpty() && clienteSelecionado != null);
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
					Cliente cliente = new Cliente(clienteSelecionado.getId(), textField.getText());

					clienteDAO.atualizar(cliente);

					JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Informação",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
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
					if (clienteSelecionado != null) {
						clienteDAO.remover(clienteSelecionado.getId());
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

		List<Cliente> clientes = clienteDAO.listarTodos();

		ButtonGroup grupo = new ButtonGroup();

		for (Cliente cliente : clientes) {
			JToggleButton toggle = new JToggleButton(cliente.getNome());
			toggle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
			toggle.setAlignmentX(Component.LEFT_ALIGNMENT);
			toggle.setBackground(Color.GRAY);
			toggle.setForeground(Color.WHITE);

			grupo.add(toggle);
			panelToggleButtons.add(toggle);
			panelToggleButtons.add(Box.createRigidArea(new Dimension(0, 5)));

			mapToggleCliente.put(toggle, cliente);

			toggle.addActionListener(ev -> {
				if (toggle.isSelected()) {
					clienteSelecionado = mapToggleCliente.get(toggle);
					textField.setText(clienteSelecionado.getNome());
				}
			});
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new EditarCliente().setVisible(true);
		});
	}
}
