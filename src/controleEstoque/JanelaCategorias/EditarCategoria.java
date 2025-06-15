package controleEstoque.JanelaCategorias;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controleEstoque.JanelaBase;
import controleEstoque.dao.CategoriaDAO;
import controleEstoque.model.Categoria;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCategoria extends JanelaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelToggleButtons;
	private JTextField textField;

	private Categoria categoriaSelecionada;
	private Map<JToggleButton, Categoria> mapToggleCategoria = new HashMap<>();

	public EditarCategoria() {

		CategoriaDAO categoriaDAO = new CategoriaDAO();

		setTitle("Editar Categoria");
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
				btnSalvar.setEnabled(!texto.isEmpty() && categoriaSelecionada != null);
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
					Categoria categoria = new Categoria(categoriaSelecionada.getId(), textField.getText());

					categoriaDAO.atualizar(categoria);

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
					if (categoriaSelecionada != null) {
						categoriaDAO.remover(categoriaSelecionada.getId());
						JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Informação",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						throw new Exception("Seleciona uma opção!");
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

		List<Categoria> categorias = categoriaDAO.listarTodos();

		ButtonGroup grupo = new ButtonGroup();

		for (Categoria categoria : categorias) {
			JToggleButton toggle = new JToggleButton(categoria.getNome());
			toggle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
			toggle.setAlignmentX(Component.LEFT_ALIGNMENT);
			toggle.setBackground(Color.GRAY);
			toggle.setForeground(Color.WHITE);

			grupo.add(toggle);
			panelToggleButtons.add(toggle);
			panelToggleButtons.add(Box.createRigidArea(new Dimension(0, 5)));

			mapToggleCategoria.put(toggle, categoria);

			toggle.addActionListener(ev -> {
				if (toggle.isSelected()) {
					categoriaSelecionada = mapToggleCategoria.get(toggle);
					textField.setText(categoriaSelecionada.getNome());
				}
			});
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new EditarCategoria().setVisible(true);
		});
	}
}
