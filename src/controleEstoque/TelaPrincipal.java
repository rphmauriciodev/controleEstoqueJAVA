package controleEstoque;

import java.awt.*;
import javax.swing.*;
import controleEstoque.JanelaCategorias.CriarCategoria;
import controleEstoque.JanelaCategorias.EditarCategoria;
import controleEstoque.JanelaClientes.CriarCliente;
import controleEstoque.JanelaClientes.EditarCliente;
import controleEstoque.JanelaFuncionarios.CriarFuncionario;
import controleEstoque.JanelaFuncionarios.EditarFuncionario;
import controleEstoque.JanelaProdutos.AlugarProduto;
import controleEstoque.JanelaProdutos.CriarProduto;
import controleEstoque.JanelaProdutos.EditarProduto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JanelaBase {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaPrincipal frame = new TelaPrincipal();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaPrincipal() {
		super();
		setBounds(100, 100, 800, 900);
		JLabel lblCategorias = new JLabel("CATEGORIAS");
		lblCategorias.setForeground(Color.WHITE);
		lblCategorias.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategorias.setBounds(28, 23, 132, 31);
		contentPane.add(lblCategorias);

		JButton btnCriarCategoria = new JButton("Criar categoria");
		btnCriarCategoria.setBounds(28, 78, 220, 75);
		btnCriarCategoria.addActionListener(e -> {
			CriarCategoria cCategories = new CriarCategoria();
			cCategories.setVisible(true);
		});
		contentPane.add(btnCriarCategoria);

		JButton btnEditarCategorias = new JButton("Editar Categoria");
		btnEditarCategorias.setBounds(285, 78, 220, 75);
		btnEditarCategorias.addActionListener(e -> {
			EditarCategoria lCategories = new EditarCategoria();
			lCategories.setVisible(true);
		});
		contentPane.add(btnEditarCategorias);

		JLabel lblRelatorio = new JLabel("RELATÓRIO FINAL");
		lblRelatorio.setBounds(30, 684, 242, 31);
		getContentPane().add(lblRelatorio);
		lblRelatorio.setForeground(Color.WHITE);
		lblRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton btnAbrirRelatrio = new JButton("Abrir relatório");
		btnAbrirRelatrio.setBounds(30, 740, 220, 75);
		getContentPane().add(btnAbrirRelatrio);

		JLabel lblProdutos = new JLabel("PRODUTOS");
		lblProdutos.setBounds(30, 446, 132, 31);
		getContentPane().add(lblProdutos);
		lblProdutos.setForeground(Color.WHITE);
		lblProdutos.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton btnCriarProduto = new JButton("Criar produto");
		btnCriarProduto.setBounds(30, 488, 220, 75);
		getContentPane().add(btnCriarProduto);

		JButton btnListarProdutos = new JButton("Editar Produto");
		btnListarProdutos.setBounds(287, 488, 220, 75);
		getContentPane().add(btnListarProdutos);

		JButton btnAlugarProduto = new JButton("Alugar Produto");
		btnAlugarProduto.setBounds(30, 598, 220, 75);
		getContentPane().add(btnAlugarProduto);

		JLabel lblClientes = new JLabel("CLIENTES");
		lblClientes.setForeground(Color.WHITE);
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblClientes.setBounds(30, 164, 132, 31);
		getContentPane().add(lblClientes);

		JButton btnCriarCliente = new JButton("Criar cliente");
		btnCriarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarCliente cCliente = new CriarCliente();
				cCliente.setVisible(true);
			}
		});
		btnCriarCliente.setBounds(30, 219, 220, 75);
		getContentPane().add(btnCriarCliente);

		JButton btnEditarCliente = new JButton("Editar Cliente");
		btnEditarCliente.setBounds(287, 219, 220, 75);
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarCliente lCliente = new EditarCliente();
				lCliente.setVisible(true);
			}
		});
		getContentPane().add(btnEditarCliente);
		
		JLabel lblFuncionarios = new JLabel("FUNCIONARIOS");
		lblFuncionarios.setForeground(Color.WHITE);
		lblFuncionarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFuncionarios.setBounds(30, 305, 149, 31);
		getContentPane().add(lblFuncionarios);
		
		JButton btnCriarFuncionario = new JButton("Criar funcionario");
		btnCriarFuncionario.setBounds(30, 360, 220, 75);
		btnCriarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarFuncionario cFuncionario = new CriarFuncionario();
				cFuncionario.setVisible(true);
			}
		});
		getContentPane().add(btnCriarFuncionario);
		
		JButton btnEditarFuncionario = new JButton("Editar Funcionario");
		btnEditarFuncionario.setBounds(287, 360, 220, 75);
		getContentPane().add(btnEditarFuncionario);
		
		JButton btnDevolverProduto = new JButton("Devolver Produto");
		btnDevolverProduto.setBounds(287, 600, 220, 75);
		getContentPane().add(btnDevolverProduto);

		btnEditarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarFuncionario lFuncionario = new EditarFuncionario();
				lFuncionario.setVisible(true);
			}
		});
		
		btnListarProdutos.addActionListener(e -> {
			EditarProduto lProdutos = new EditarProduto();
			lProdutos.setVisible(true);
		});
		btnCriarProduto.addActionListener(e -> {
			CriarProduto cProduto = new CriarProduto();
			cProduto.setVisible(true);
		});
		btnAlugarProduto.addActionListener(e -> {
			AlugarProduto aProduto = new AlugarProduto();
			aProduto.setVisible(true);
		});
	}
}
