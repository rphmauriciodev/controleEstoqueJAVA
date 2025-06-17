package controleEstoque;

import javax.swing.*;
import controleEstoque.dao.RelatorioDAO;
import controleEstoque.model.Relatorio;

import java.awt.*;
import java.util.List;

public class RelatorioFinal extends JanelaBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioFinal() {
		RelatorioDAO dao = new RelatorioDAO();
		List<Relatorio> relatorios = dao.listarTodos();

		setTitle("Editar Categoria");
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Devolvidos");
		lblNewLabel.setBounds(68, 11, 149, 28);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setForeground(Color.WHITE);
		getContentPane().add(lblNewLabel);

		JLabel lblPendentes = new JLabel("Pendentes");
		lblPendentes.setBounds(318, 11, 149, 22);
		lblPendentes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPendentes.setForeground(Color.WHITE);
		lblPendentes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		getContentPane().add(lblPendentes);

		JLabel lblEmAtraso = new JLabel("Em Atraso");
		lblEmAtraso.setBounds(568, 11, 149, 22);
		lblEmAtraso.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmAtraso.setForeground(Color.WHITE);
		lblEmAtraso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		getContentPane().add(lblEmAtraso);

		JPanel panel = new JPanel();
		panel.setBounds(34, 82, 216, 313);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(284, 82, 216, 313);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		getContentPane().add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(534, 82, 216, 313);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		getContentPane().add(panel_2);

		for (Relatorio r : relatorios) {
			String texto = "<html>" + "<b>Total Aluguéis:</b> " + r.getTotalAlugueis() + "<br>"
					+ "<b>Quantidade Total:</b> " + r.getQuantidadeTotal() + "<br>" + "<b>Valor Total:</b> R$ "
					+ String.format("%.2f", r.getValorTotal()) + "</html>";

			JLabel label = new JLabel(texto);
			label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			switch (r.getStatus()) {
			case "Devolvido":
			case "Devolvidos":
				panel.add(label);
				break;
			case "Pendente":
			case "Pendentes":
				panel_1.add(label);
				break;
			case "Em Atraso":
				panel_2.add(label);
				break;
			}
		}

		panel.revalidate();
		panel_1.revalidate();
		panel_2.revalidate();
	}

}
