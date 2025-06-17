package controleEstoque.model;

import java.math.BigDecimal;

public class Relatorio {

    private String status;
    private int totalAlugueis;
    private int quantidadeTotal;
    private BigDecimal valorTotal;

    public Relatorio() {}

    public Relatorio(String status, int totalAlugueis, int quantidadeTotal, BigDecimal valorTotal) {
        this.status = status;
        this.totalAlugueis = totalAlugueis;
        this.quantidadeTotal = quantidadeTotal;
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalAlugueis() {
        return totalAlugueis;
    }

    public void setTotalAlugueis(int totalAlugueis) {
        this.totalAlugueis = totalAlugueis;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "status='" + status + '\'' +
                ", totalAlugueis=" + totalAlugueis +
                ", quantidadeTotal=" + quantidadeTotal +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
