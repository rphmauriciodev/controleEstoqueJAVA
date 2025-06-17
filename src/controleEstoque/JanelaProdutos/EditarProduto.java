package controleEstoque.JanelaProdutos;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import controleEstoque.JanelaBase;
import controleEstoque.dao.CategoriaDAO;
import controleEstoque.dao.ProdutoDAO;
import controleEstoque.model.Categoria;
import controleEstoque.model.Produto;
import utils.DeletarTipo;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class EditarProduto extends JanelaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelToggleButtons;
	private JTextField textField;
	private Produto produtoSelecionado;
	private Map<JToggleButton, Produto> mapToggleProduto = new HashMap<>();

	private DeletarTipo tipoAcao = DeletarTipo.Deletar;

	public EditarProduto() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		List<Categoria> categorias = categoriaDAO.listarTodos();
		List<Produto> produtos = produtoDAO.listarTodos();

		setTitle("Editar Produtos");
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

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(685, 427, 89, 23);
		getContentPane().add(btnSair);

		JComboBox<Categoria> comboBox = new JComboBox<Categoria>(categorias.toArray(new Categoria[0]));
		comboBox.setBounds(412, 158, 314, 22);
		getContentPane().add(comboBox);

		JLabel lblMudarCategoria = new JLabel("Mudar categoria");
		lblMudarCategoria.setForeground(Color.WHITE);
		lblMudarCategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMudarCategoria.setBounds(412, 107, 197, 40);
		getContentPane().add(lblMudarCategoria);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (produtoSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um produto primeiro!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (tipoAcao == DeletarTipo.Deletar) {
					produtoDAO.remover(produtoSelecionado.getId());
				} else {
					produtoSelecionado.setDesativado(!produtoSelecionado.isDesativado());
					produtoDAO.atualizar(produtoSelecionado);
				}
				dispose();
			}
		});
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnDeletar.setBackground(Color.RED);
		btnDeletar.setBounds(637, 341, 89, 23);
		getContentPane().add(btnDeletar);

		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
		numberFormatter.setValueClass(Integer.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0);
		numberFormatter.setMaximum(Integer.MAX_VALUE);

		JFormattedTextField formattedTextField = new JFormattedTextField(numberFormatter);
		formattedTextField.setColumns(10);
		formattedTextField.setBounds(529, 242, 57, 40);
		getContentPane().add(formattedTextField);

		JLabel lblMudarQuantidade = new JLabel("Mudar quantidade");
		lblMudarQuantidade.setForeground(Color.WHITE);
		lblMudarQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMudarQuantidade.setBounds(529, 191, 197, 40);
		getContentPane().add(lblMudarQuantidade);

		JLabel lblPreo = new JLabel("Preço");
		lblPreo.setForeground(Color.WHITE);
		lblPreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPreo.setBounds(412, 191, 89, 40);
		getContentPane().add(lblPreo);

		NumberFormat doubleFormat = NumberFormat.getNumberInstance();
		doubleFormat.setMinimumFractionDigits(2);
		doubleFormat.setMaximumFractionDigits(2);
		NumberFormatter doubleFormatter = new NumberFormatter(doubleFormat);
		doubleFormatter.setValueClass(Double.class);
		doubleFormatter.setAllowsInvalid(false);
		doubleFormatter.setMinimum(0.0);
		doubleFormatter.setMaximum(Double.MAX_VALUE);
		JFormattedTextField precoField = new JFormattedTextField(doubleFormatter);
		precoField.setColumns(10);
		precoField.setBounds(412, 242, 89, 40);
		getContentPane().add(precoField);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(637, 285, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (produtoSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um produto primeiro!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String nome = textField.getText();
				Integer quantidade = (Integer) formattedTextField.getValue();
				Double preco = (Double) precoField.getValue();
				Categoria categoria = (Categoria) comboBox.getSelectedItem();

				if (nome.isBlank() || quantidade == null || preco == null || categoria == null) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Produto produto = new Produto(produtoSelecionado.getId(), nome, preco, categoria.getId(), quantidade,
						false, produtoSelecionado.isAlugado());

				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoDAO.atualizar(produto);

				JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!", "Informação",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		getContentPane().add(btnSalvar);

		ButtonGroup grupo = new ButtonGroup();

		for (Produto produto : produtos) {
			JToggleButton toggle = new JToggleButton(produto.getNome());
			toggle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
			toggle.setAlignmentX(Component.LEFT_ALIGNMENT);
			toggle.setBackground(Color.GRAY);
			toggle.setForeground(Color.WHITE);

			grupo.add(toggle);

			panelToggleButtons.add(toggle);
			panelToggleButtons.add(Box.createRigidArea(new Dimension(0, 5)));

			mapToggleProduto.put(toggle, produto);

			toggle.addActionListener(ev -> {
				if (toggle.isSelected()) {
					produtoSelecionado = mapToggleProduto.get(toggle);
					if (produtoSelecionado.isDesativado()) {
						btnDeletar.setText("Reativar");
						btnDeletar.setBackground(Color.BLUE);
						tipoAcao = DeletarTipo.Reativar;
					} else if (produtoSelecionado.isAlugado()) {
						btnDeletar.setText("Desativar");
						btnDeletar.setBackground(Color.ORANGE);
						tipoAcao = DeletarTipo.Desativar;
					} else {
						btnDeletar.setText("Deletar");
						btnDeletar.setBackground(Color.RED);
						tipoAcao = DeletarTipo.Deletar;
					}
					textField.setText(produtoSelecionado.getNome());
					precoField.setValue(produtoSelecionado.getPrecoUnit());
					formattedTextField.setValue(produtoSelecionado.getQuantidade());

					for (int i = 0; i < comboBox.getItemCount(); i++) {
						Categoria categoria = comboBox.getItemAt(i);
						if (categoria.getId() == produtoSelecionado.getCategoriaId()) {
							comboBox.setSelectedIndex(i);
							break;
						}
					}
				}
			});
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new EditarProduto().setVisible(true);
		});
	}
}
