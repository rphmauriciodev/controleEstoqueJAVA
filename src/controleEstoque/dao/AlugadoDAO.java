package controleEstoque.dao;

import database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controleEstoque.dao.interfaces.IbaseDAO;
import controleEstoque.model.Alugado;
import controleEstoque.model.VW_Alugado;

public class AlugadoDAO implements IbaseDAO<Alugado> {

	public List<Alugado> listarTodos() {
		List<Alugado> alugados = new ArrayList<>();
		String sql = "SELECT id, produtoId, quantidade, dataAluguel, dataDevolucao, clienteId, funcionarioId, isDevolvido FROM Alugados";

		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Alugado alugado = new Alugado(rs.getInt("id"), rs.getInt("produtoId"), rs.getInt("quantidade"),
						rs.getDate("dataAluguel"), rs.getDate("dataDevolucao"), rs.getInt("clienteId"), rs.getInt("funcionarioId"),
						rs.getBoolean("isDevolvido"));
				alugados.add(alugado);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return alugados;
	}
	
	public List<VW_Alugado> listarRelatorio() {
		List<VW_Alugado> alugados = new ArrayList<>();
		String sql = "SELECT id, alugados, dataAluguel, dataDevolucao, produto, cliente, funcionario FROM VW_Alugados)";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					VW_Alugado alugado = new VW_Alugado(rs.getInt("id"), rs.getDate("dataAluguel"),
							rs.getDate("dataDevolucao"), rs.getString("cliente"),
							rs.getInt("alugados"), rs.getString("produto"), rs.getString("funcionario"));
					alugados.add(alugado);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return alugados;
	}

	public List<VW_Alugado> listarPor(int clienteId, boolean isDevolvido) {
		List<VW_Alugado> alugados = new ArrayList<>();
		String sql = "SELECT id, alugados, dataAluguel, dataDevolucao, produto, cliente, funcionario FROM FN_SelecionaAlugadosPor(?,?)";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, clienteId);
			stmt.setBoolean(2, isDevolvido);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					VW_Alugado alugado = new VW_Alugado(rs.getInt("id"), rs.getDate("dataAluguel"),
							rs.getDate("dataDevolucao"), rs.getString("cliente"),
							rs.getInt("alugados"), rs.getString("produto"), rs.getString("funcionario"));
					alugados.add(alugado);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return alugados;
	}

	public void inserir(Alugado alugado) {
		String sql = "INSERT INTO Alugados (produtoId, quantidade, dataAluguel, dataDevolucao, clienteId, isDevolvido) VALUES (?,?,?,?,?,?)";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, alugado.getProdutoId());
			stmt.setInt(2, alugado.getQuantidade());
			stmt.setDate(3, alugado.getDataAluguel());
			stmt.setDate(4, alugado.getDataDevolucao());
			stmt.setInt(5, alugado.getClienteId());
			stmt.setBoolean(6, alugado.isDevolvido());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void alugar(Alugado alugado) {
	    String sql = "CALL sp_inserir_aluguel (?,?,?,?)";

	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, alugado.getProdutoId());
	        stmt.setInt(2, alugado.getClienteId());
	        stmt.setInt(3, alugado.getFuncionarioId());
	        stmt.setInt(4, alugado.getQuantidade());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	}


	public void atualizar(Alugado alugado) {
		String sql = "UPDATE Alugados SET produtoId = ?, quantidade = ?, dataAluguel = ?, dataDevolucao = ?, clienteId = ? , isDevolvido = ? WHERE id = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, alugado.getProdutoId());
			stmt.setInt(2, alugado.getQuantidade());
			stmt.setDate(3, alugado.getDataAluguel());
			stmt.setDate(4, alugado.getDataDevolucao());
			stmt.setInt(5, alugado.getClienteId());
			stmt.setBoolean(6, alugado.isDevolvido());
			stmt.setInt(7, alugado.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void devolver(int id) {
		String sql = "CALL sp_devolver_produto(?)";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remover(int id) {
		String sql = "UPDATE Alugados SET isDesativado = true WHERE id = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
