package controleEstoque.JanelaProdutos;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import controleEstoque.JanelaBase;
import controleEstoque.dao.AlugadoDAO;
import controleEstoque.dao.CategoriaDAO;
import controleEstoque.dao.ClienteDAO;
import controleEstoque.dao.FuncionarioDAO;
import controleEstoque.dao.ProdutoDAO;
import controleEstoque.model.Alugado;
import controleEstoque.model.Categoria;
import controleEstoque.model.Cliente;
import controleEstoque.model.Funcionario;
import controleEstoque.model.Produto;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class AlugarProduto extends JanelaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelToggleButtons;

	public AlugarProduto() {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		AlugadoDAO alugadoDAO = new AlugadoDAO();
		
		List<Categoria> categorias = categoriaDAO.listarTodos();
		List<Cliente> clientes = clienteDAO.listarTodos();
		List<Funcionario> funcionarios = funcionarioDAO.listarTodos();
		List<Produto> produtos = produtoDAO.listarPorCategoria(categorias.get(0).getId());
		
		setTitle("Alugar Produto");
		setBounds(100, 100, 360, 510);
		getContentPane().setLayout(null);

		panelToggleButtons = new JPanel();
		panelToggleButtons.setLayout(new BoxLayout(panelToggleButtons, BoxLayout.Y_AXIS));
		panelToggleButtons.setBackground(Color.DARK_GRAY);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(235, 433, 89, 23);
		getContentPane().add(btnSair);

		JComboBox<Categoria> comboBoxCategorias = new JComboBox<Categoria>(categorias.toArray(new Categoria[0]));

		comboBoxCategorias.setBounds(10, 75, 314, 22);
		getContentPane().add(comboBoxCategorias);

		JLabel lblMudarCategoria = new JLabel("Selecione o produto");
		lblMudarCategoria.setForeground(Color.WHITE);
		lblMudarCategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMudarCategoria.setBounds(10, 108, 197, 40);
		getContentPane().add(lblMudarCategoria);

		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
		numberFormatter.setValueClass(Integer.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0);
		numberFormatter.setMaximum(Integer.MAX_VALUE);

		JLabel lblSelecioneACategoria = new JLabel("Selecione a categoria");
		lblSelecioneACategoria.setForeground(Color.WHITE);
		lblSelecioneACategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelecioneACategoria.setBounds(10, 24, 197, 40);
		getContentPane().add(lblSelecioneACategoria);

		JComboBox<Produto> comboBoxProdutos = new JComboBox<Produto>(produtos.toArray(new Produto[0]));
		comboBoxProdutos.setBounds(10, 159, 314, 22);
		comboBoxCategorias.addItemListener(e -> {
			if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
				Categoria categoriaSelecionada = (Categoria) e.getItem();
				List<Produto> produtosFiltrados = produtoDAO.listarPorCategoria(categoriaSelecionada.getId());

				comboBoxProdutos.removeAllItems();
				for (Produto produto : produtosFiltrados) {
					comboBoxProdutos.addItem(produto);
				}
			}
		});
		getContentPane().add(comboBoxProdutos);

		JComboBox<Cliente> comboBoxClientes = new JComboBox<Cliente>(clientes.toArray(new Cliente[0]));
		comboBoxClientes.setBounds(10, 243, 314, 22);
		getContentPane().add(comboBoxClientes);

		JLabel lblSelecioneOCliente = new JLabel("Selecione o cliente");
		lblSelecioneOCliente.setForeground(Color.WHITE);
		lblSelecioneOCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelecioneOCliente.setBounds(10, 192, 197, 40);
		getContentPane().add(lblSelecioneOCliente);

		JLabel lblSelecioneOFuncionrio = new JLabel("Selecione o funcionário responsável");
		lblSelecioneOFuncionrio.setForeground(Color.WHITE);
		lblSelecioneOFuncionrio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelecioneOFuncionrio.setBounds(10, 276, 314, 40);
		getContentPane().add(lblSelecioneOFuncionrio);

		JComboBox<Funcionario> comboBoxFuncionarios = new JComboBox<Funcionario>(funcionarios.toArray(new Funcionario[0]));
		comboBoxFuncionarios.setBounds(10, 327, 314, 22);
		getContentPane().add(comboBoxFuncionarios);
		
		JButton btnSalvar = new JButton("Alugar");
		btnSalvar.setBounds(235, 368, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxCategorias.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Selecione uma categoria.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (comboBoxProdutos.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Selecione um produto.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (comboBoxClientes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Selecione um cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (comboBoxFuncionarios.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Selecione um funcionário responsável.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}


				Produto produtoSelecionado = (Produto)comboBoxProdutos.getSelectedItem();
				
				if (produtoSelecionado.getQuantidade() <= 0) {
					JOptionPane.showMessageDialog(null, "Produto fora de estoque", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Cliente clienteSelecionado = (Cliente)comboBoxClientes.getSelectedItem();
				Funcionario funcionarioSelecionado = (Funcionario)comboBoxFuncionarios.getSelectedItem();
				
				int quantidade = produtoSelecionado.getQuantidade() > 10 ? 10 : produtoSelecionado.getQuantidade();
				
				Date dataAtual = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(dataAtual);
				cal.add(Calendar.DAY_OF_MONTH, 30); 

				Date dataDevolucao = cal.getTime();
				
				Alugado alugado = new Alugado(0, produtoSelecionado.getId(), quantidade, dataAtual, dataDevolucao, clienteSelecionado.getId(), funcionarioSelecionado.getId(), false);
				
				alugadoDAO.alugar(alugado);
				JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Informação",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});

		getContentPane().add(btnSalvar);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new AlugarProduto().setVisible(true);
		});
	}
}
