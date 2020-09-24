package facil_db_company.dominio;

public class ItemVenda {
	
	private String idItem;
	private long quantidade;
	private double preco;	
	
	public String getIdItem() {
		return idItem;
	}
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	public long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

}
