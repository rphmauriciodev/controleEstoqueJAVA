package controleEstoque.model;

import java.sql.Date;

public class VW_Alugado {
	private int id;
	private Date dataAluguel;
	private Date dataDevolucao;
	private String cliente;
	private int alugados;
	private String produto;
	private String funcionario;

	public VW_Alugado(int id, Date dataAluguel, Date dataDevolucao, String cliente, int alugados, String produto, String funcionario) {
		this.id = id;
		this.dataAluguel = dataAluguel;
		this.dataDevolucao = dataDevolucao;
		this.cliente = cliente;
		this.alugados = alugados;
		this.produto = produto;
		this.funcionario = funcionario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getAlugados() {
		return alugados;
	}

	public void setAlugados(int alugados) {
		this.alugados = alugados;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return cliente + " - " + produto;
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

}
