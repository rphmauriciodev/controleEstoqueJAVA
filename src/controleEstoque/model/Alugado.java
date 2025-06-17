package controleEstoque.model;

import java.sql.Date;

public class Alugado {
	private int id;
	private int produtoId;
	private int funcionarioId;
	private int quantidade;
	private Date dataAluguel;
	private Date dataDevolucao;
	private int clienteId;
	private boolean isDevolvido;

	public Alugado(int id, int produtoId,int quantidade, java.util.Date dataAtual, java.util.Date dataDevolucao,
			int clienteId, int funcionarioId ,boolean isDevolvido) {
		this.id = id;
		this.produtoId = produtoId;
		this.quantidade = quantidade;
		this.dataAluguel = new java.sql.Date(dataAtual.getTime());
		this.dataDevolucao = new java.sql.Date(dataDevolucao.getTime());
		this.clienteId = clienteId;
		this.funcionarioId = funcionarioId;
		this.isDevolvido = isDevolvido;
	}

	public int getId() {
		return id;
	}

	public int getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataAluguel() {
		return dataAluguel;
	}

	public void setDataAluguel(Date dataAluguel) {
		this.dataAluguel = dataAluguel;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public boolean isDevolvido() {
		return isDevolvido;
	}

	public void setDevolvido(boolean isDevolvido) {
		this.isDevolvido = isDevolvido;
	}

	public int getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(int funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

}
