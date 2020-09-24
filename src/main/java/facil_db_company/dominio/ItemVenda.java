package facil_db_company.dominio;

public class ItemVenda {
	
	
	public ItemVenda() {}
	public ItemVenda(String itemPedido) {
		String[] dadosItem = itemPedido.split("-");
		String idItem     = dadosItem[0];
		String quantidade    = dadosItem[1];
		String preco = dadosItem[2];		
		
		this.setIdItem(idItem);
		this.setQuantidade(Long.valueOf(quantidade));
		this.setPreco(Double.valueOf(preco));
		
	}
	
	
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
