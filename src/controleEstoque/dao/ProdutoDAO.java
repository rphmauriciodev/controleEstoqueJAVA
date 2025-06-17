package controleEstoque.dao;

import database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controleEstoque.dao.interfaces.IbaseDAO;
import controleEstoque.model.Produto;

public class ProdutoDAO implements IbaseDAO<Produto> {

	public List<Produto> listarTodos() {
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT id, nome, precoUnit, categoriaId, quantidade, isDesativado, isAlugado FROM public.\"VW_Produtos\"";

		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("precoUnit"),
						rs.getInt("categoriaId"), rs.getInt("quantidade"), rs.getBoolean("isDesativado"),
						rs.getBoolean("isAlugado"));
				produtos.add(produto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	public List<Produto> listarPorCategoria(int categoriaId) {
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT id, nome, precoUnit, categoriaId, quantidade, isDesativado, isAlugado FROM FN_SelecionaProdutosPor(?) WHERE COALESCE(isDesativado,false) = false";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, categoriaId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("precoUnit"),
							rs.getInt("categoriaId"), rs.getInt("quantidade"), rs.getBoolean("isDesativado"),
							rs.getBoolean("isAlugado"));
					produtos.add(produto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	public void inserir(Produto produto) {
		String sql = "INSERT INTO Produtos (nome, precoUnit, categoriaId, quantidade, isDesativado) VALUES (?,?,?,?,?)";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPrecoUnit());
			stmt.setInt(3, produto.getCategoriaId());
			stmt.setInt(4, produto.getQuantidade());
			stmt.setBoolean(5, produto.isDesativado());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(Produto produto) {
		String sql = "UPDATE Produtos SET nome = ?, precoUnit = ?, categoriaId = ?, quantidade = ?, isDesativado = ? WHERE id = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPrecoUnit());
			stmt.setInt(3, produto.getCategoriaId());
			stmt.setInt(4, produto.getQuantidade());
			stmt.setBoolean(5, produto.isDesativado());
			stmt.setInt(6, produto.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remover(int id) {
		String sql = "CALL sp_excluir_produto_com_alugados (?)";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
