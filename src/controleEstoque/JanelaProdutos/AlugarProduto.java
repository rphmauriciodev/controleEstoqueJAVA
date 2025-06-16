package controleEstoque.JanelaProdutos;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import controleEstoque.JanelaBase;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
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
		setTitle("Editar Produtos");
		setBounds(100, 100, 360, 510);
		getContentPane().setLayout(null);

		panelToggleButtons = new JPanel();
		panelToggleButtons.setLayout(new BoxLayout(panelToggleButtons, BoxLayout.Y_AXIS));
		panelToggleButtons.setBackground(Color.DARK_GRAY);
		
		JButton btnSalvar = new JButton("Alugar");
		btnSalvar.setBounds(235, 368, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Informação",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		getContentPane().add(btnSalvar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(235, 433, 89, 23);
		getContentPane().add(btnSair);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 159, 314, 22);
		getContentPane().add(comboBox);
		
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
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 75, 314, 22);
		getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 243, 314, 22);
		getContentPane().add(comboBox_2);
		
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
		
		JComboBox comboBox_2_1 = new JComboBox();
		comboBox_2_1.setBounds(10, 327, 314, 22);
		getContentPane().add(comboBox_2_1);

		List<String> categorias = new ArrayList<>();
		categorias.add("Categoria A");
		categorias.add("Categoria B");
		categorias.add("Categoria C");
		categorias.add("Categoria D");

		ButtonGroup grupo = new ButtonGroup();

		for (String categoria : categorias) {
			JToggleButton toggle = new JToggleButton(categoria);
			toggle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
			toggle.setAlignmentX(Component.LEFT_ALIGNMENT);
			toggle.setBackground(Color.GRAY);
			toggle.setForeground(Color.WHITE);

			grupo.add(toggle);

			panelToggleButtons.add(toggle);
			panelToggleButtons.add(Box.createRigidArea(new Dimension(0, 5)));
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new AlugarProduto().setVisible(true);
		});
	}
}
