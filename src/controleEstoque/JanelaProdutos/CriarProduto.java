package controleEstoque.JanelaProdutos;

import java.awt.EventQueue;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import controleEstoque.JanelaBase;
import controleEstoque.dao.CategoriaDAO;
import controleEstoque.dao.ProdutoDAO;
import controleEstoque.model.Categoria;
import controleEstoque.model.Produto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class CriarProduto extends JanelaBase {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarProduto frame = new CriarProduto();
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
	public CriarProduto() {
		super();

		CategoriaDAO categoriaDAO = new CategoriaDAO();
		List<Categoria> categorias = categoriaDAO.listarTodos();

		textField = new JTextField();
		textField.setBounds(10, 67, 314, 35);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Digite o nome do produto");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 22, 314, 28);
		contentPane.add(lblNewLabel);

		JComboBox<Categoria> comboBox = new JComboBox<Categoria>(categorias.toArray(new Categoria[0]));
		comboBox.setBounds(10, 158, 314, 22);
		getContentPane().add(comboBox);
		JLabel lblMudarCategoria = new JLabel("Seleciona a categoria");
		lblMudarCategoria.setForeground(Color.WHITE);
		lblMudarCategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMudarCategoria.setBounds(10, 107, 197, 40);
		getContentPane().add(lblMudarCategoria);

		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
		numberFormatter.setValueClass(Integer.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0);
		numberFormatter.setMaximum(Integer.MAX_VALUE);
		JFormattedTextField quantidadeField = new JFormattedTextField(numberFormatter);
		quantidadeField.setColumns(10);
		quantidadeField.setBounds(127, 242, 57, 40);
		getContentPane().add(quantidadeField);

		setBounds(100, 100, 365, 439);

		JButton btnCriar = new JButton("Criar");
		btnCriar.setBounds(233, 317, 89, 23);
		getContentPane().add(btnCriar);

		JLabel lblDigiteAQuantidade = new JLabel("Digite a quantidade");
		lblDigiteAQuantidade.setForeground(Color.WHITE);
		lblDigiteAQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDigiteAQuantidade.setBounds(127, 191, 197, 40);
		getContentPane().add(lblDigiteAQuantidade);

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
		precoField.setBounds(10, 242, 89, 40);
		getContentPane().add(precoField);

		JLabel lblPreo = new JLabel("Preço");
		lblPreo.setForeground(Color.WHITE);
		lblPreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPreo.setBounds(10, 191, 89, 40);
		getContentPane().add(lblPreo);
		btnCriar.addActionListener(e -> {
			String nomeProduto = textField.getText();
			Categoria categoriaSelecionada = (Categoria) comboBox.getSelectedItem();
			Integer quantidade = (Integer) quantidadeField.getValue();
			Double preco = (Double) precoField.getValue();
			
			if (nomeProduto.isBlank() || categoriaSelecionada == null || quantidade == null || preco == null || preco <= 0) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			int idCategoria = categoriaSelecionada.getId();

			Produto produto = new Produto(0, textField.getText(), preco, idCategoria, quantidade,
					false, false);
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.inserir(produto);

			JOptionPane.showMessageDialog(null, "Produto criado com sucesso!", "Informação",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		});

	}
}
