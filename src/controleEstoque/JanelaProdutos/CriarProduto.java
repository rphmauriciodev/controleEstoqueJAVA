package controleEstoque.JanelaProdutos;

import java.awt.EventQueue;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import controleEstoque.JanelaBase;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.NumberFormat;

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

		JComboBox comboBox = new JComboBox();
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
		JFormattedTextField formattedTextField = new JFormattedTextField(numberFormatter);
		formattedTextField.setColumns(10);
		formattedTextField.setBounds(10, 245, 57, 40);
		getContentPane().add(formattedTextField);
		
		setBounds(100, 100, 365, 439);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.setBounds(233, 317, 89, 23);
		getContentPane().add(btnCriar);
		
		JLabel lblDigiteAQuantidade = new JLabel("Digite a quantidade");
		lblDigiteAQuantidade.setForeground(Color.WHITE);
		lblDigiteAQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDigiteAQuantidade.setBounds(10, 194, 197, 40);
		getContentPane().add(lblDigiteAQuantidade);
		btnCriar.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		});

	}
}
