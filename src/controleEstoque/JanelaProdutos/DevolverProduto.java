package controleEstoque.JanelaProdutos;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import controleEstoque.JanelaBase;
import controleEstoque.dao.AlugadoDAO;
import controleEstoque.dao.ClienteDAO;
import controleEstoque.model.Cliente;
import controleEstoque.model.VW_Alugado;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class DevolverProduto extends JanelaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelToggleButtons;

	public DevolverProduto() {
		ClienteDAO clienteDAO = new ClienteDAO();
		AlugadoDAO alugadoDAO = new AlugadoDAO();

		List<Cliente> clientes = clienteDAO.listarTodos();

		List<VW_Alugado> alugados = alugadoDAO.listarPor(clientes.get(0).getId(), false);

		setTitle("Devolver Produto");
		setBounds(100, 100, 360, 342);
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
		btnSair.setBounds(235, 259, 89, 23);
		getContentPane().add(btnSair);

		JLabel lblMudarCategoria = new JLabel("Selecione o produto");
		lblMudarCategoria.setForeground(Color.WHITE);
		lblMudarCategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMudarCategoria.setBounds(10, 95, 197, 40);
		getContentPane().add(lblMudarCategoria);

		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
		numberFormatter.setValueClass(Integer.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0);
		numberFormatter.setMaximum(Integer.MAX_VALUE);

		JComboBox<Cliente> comboBoxClientes = new JComboBox<Cliente>(clientes.toArray(new Cliente[0]));
		comboBoxClientes.setBounds(10, 62, 314, 22);
		getContentPane().add(comboBoxClientes);

		JComboBox<VW_Alugado> comboBoxAlugados = new JComboBox<VW_Alugado>(alugados.toArray(new VW_Alugado[0]));
		comboBoxAlugados.setBounds(10, 146, 314, 22);
		getContentPane().add(comboBoxAlugados);
		comboBoxClientes.addActionListener(e -> {
			Cliente clienteSelecionado = (Cliente) comboBoxClientes.getSelectedItem();
			if (clienteSelecionado != null) {
				List<VW_Alugado> alugadosFiltrados = alugadoDAO.listarPor(clienteSelecionado.getId(), false);

				comboBoxAlugados.removeAllItems();
				for (VW_Alugado alugado : alugadosFiltrados) {
					comboBoxAlugados.addItem(alugado);
				}
			}
		});

		JLabel lblSelecioneOCliente = new JLabel("Selecione o cliente");
		lblSelecioneOCliente.setForeground(Color.WHITE);
		lblSelecioneOCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelecioneOCliente.setBounds(10, 11, 197, 40);
		getContentPane().add(lblSelecioneOCliente);

		JButton btnSalvar = new JButton("Devolver");
		btnSalvar.setBounds(235, 194, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBoxAlugados.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Selecione um produto.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (comboBoxClientes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Selecione um cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				VW_Alugado alugado = (VW_Alugado)comboBoxAlugados.getSelectedItem();
				alugadoDAO.devolver(alugado.getId());
				JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Informação",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});

		getContentPane().add(btnSalvar);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new DevolverProduto().setVisible(true);
		});
	}
}
