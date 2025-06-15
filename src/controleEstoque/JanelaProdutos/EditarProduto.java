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

public class EditarProduto extends JanelaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelToggleButtons;
	private JTextField textField;

	public EditarProduto() {
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

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(637, 285, 89, 23);
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
		btnSair.setBounds(685, 427, 89, 23);
		getContentPane().add(btnSair);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(412, 158, 314, 22);
		getContentPane().add(comboBox);
		
		JLabel lblMudarCategoria = new JLabel("Mudar categoria");
		lblMudarCategoria.setForeground(Color.WHITE);
		lblMudarCategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMudarCategoria.setBounds(412, 107, 197, 40);
		getContentPane().add(lblMudarCategoria);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
		formattedTextField.setBounds(412, 243, 57, 40);
		getContentPane().add(formattedTextField);
		
		JLabel lblMudarQuantidade = new JLabel("Mudar quantidade");
		lblMudarQuantidade.setForeground(Color.WHITE);
		lblMudarQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMudarQuantidade.setBounds(412, 192, 197, 40);
		getContentPane().add(lblMudarQuantidade);

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
			new EditarProduto().setVisible(true);
		});
	}
}
