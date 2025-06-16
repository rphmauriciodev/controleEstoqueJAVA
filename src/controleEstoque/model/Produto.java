package controleEstoque.model;

public class Produto {
	private int id;
	private double precoUnit;
	private int categoriaId;
	private int quantidade;
	private String nome;
	private boolean isDesativado;
	private boolean isAlugado;
	
	public Produto(int id, String nome, double precoUnit, int categoriaId, int quantidade, boolean isDesativado, boolean isAlugado) {
		this.id = id;
		this.precoUnit = precoUnit;
		this.categoriaId = categoriaId;
		this.quantidade = quantidade;
		this.nome = nome;
		this.isDesativado = isDesativado;
		this.isAlugado = isAlugado;
	}
	
	public int getId() {
		return id;
	}
	public double getPrecoUnit() {
		return precoUnit;
	}
	public void setprecoUnit(double precoUnit) {
		this.precoUnit = precoUnit;
	}
	public int getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isDesativado() {
		return isDesativado;
	}
	public void setDesativado(boolean isDesativado) {
		this.isDesativado = isDesativado;
	}

	public boolean isAlugado() {
		return isAlugado;
	}

	public void setAlugado(boolean isAlugado) {
		this.isAlugado = isAlugado;
	}

}
