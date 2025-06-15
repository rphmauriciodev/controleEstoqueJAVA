package controleEstoque.JanelaClientes;

import java.awt.EventQueue;

import java.awt.Color;
import javax.swing.JTextField;
import controleEstoque.JanelaBase;
import controleEstoque.dao.ClienteDAO;
import controleEstoque.model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;

public class CriarCliente extends JanelaBase {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarCliente frame = new CriarCliente();
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
	public CriarCliente() {
		super();
		
		textField = new JTextField();
		textField.setBounds(10, 67, 314, 35);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Digite o nome do cliente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 22, 314, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.setBounds(235, 126, 89, 23);
		btnCriar.setEnabled(false);
		btnCriar.addActionListener(e -> {
			try {
				ClienteDAO clienteDAO = new ClienteDAO();
				
				Cliente cliente = new Cliente(0, textField.getText());
				
				clienteDAO.inserir(cliente);
				
				JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
				textField.setText("");
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
			}
		});
		contentPane.add(btnCriar);
		

		textField.getDocument().addDocumentListener(new DocumentListener() {
			private void atualizarEstadoBotao() {
				String texto = textField.getText().trim();
				btnCriar.setEnabled(!texto.isEmpty());
			}

			public void insertUpdate(DocumentEvent e) { atualizarEstadoBotao(); }
			public void removeUpdate(DocumentEvent e) { atualizarEstadoBotao(); }
			public void changedUpdate(DocumentEvent e) { atualizarEstadoBotao(); }
		});
			
		setBounds(100, 100, 364, 215);

	}
}
