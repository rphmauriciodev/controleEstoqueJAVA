package controleEstoque.dao;

import database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controleEstoque.dao.interfaces.IbaseDAO;
import controleEstoque.model.Relatorio;

public class RelatorioDAO implements IbaseDAO<Relatorio> {

	public List<Relatorio> listarTodos() {
	    List<Relatorio> relatorios = new ArrayList<>();
	    String sql = "SELECT * FROM public.\"VW_RelatorioFinal\"";

	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Relatorio relatorio = new Relatorio();

	            relatorio.setStatus(rs.getString("status"));
	            relatorio.setTotalAlugueis(rs.getInt("total_alugueis"));
	            relatorio.setQuantidadeTotal(rs.getInt("quantidade_total"));
	            relatorio.setValorTotal(rs.getBigDecimal("valor_total")); 

	            relatorios.add(relatorio);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return relatorios;
	}

	
	@Override
	public void inserir(Relatorio item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Relatorio item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(int id) {
		// TODO Auto-generated method stub
		
	}
}