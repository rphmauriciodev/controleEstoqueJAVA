package controleEstoque.dao;

import database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controleEstoque.dao.interfaces.IbaseDAO;
import controleEstoque.model.Funcionario;

public class FuncionarioDAO implements IbaseDAO<Funcionario>{

    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT id, nome FROM Funcionarios";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	Funcionario funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"));
                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionarios (nome) VALUES (?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE Funcionarios SET nome = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void remover(int id) {
        String sql = "DELETE FROM Funcionarios WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
