package controleEstoque.dao;

import database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controleEstoque.dao.interfaces.IbaseDAO;
import controleEstoque.model.Cliente;

public class ClienteDAO implements IbaseDAO<Cliente>{

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, nome FROM Clientes WHERE COALESCE(isDesativado, false) = false";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nome) VALUES (?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE Clientes SET nome = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void remover(int id) {
    	String sql = "UPDATE Clientes SET isDesativado = true WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
