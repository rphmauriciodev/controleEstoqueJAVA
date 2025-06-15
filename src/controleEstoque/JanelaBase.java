package controleEstoque;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JanelaBase extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;

	public JanelaBase() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	}
}
