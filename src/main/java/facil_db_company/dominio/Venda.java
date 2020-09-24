package facil_db_company.dominio;

import java.util.ArrayList;
import java.util.List;

public class Venda {
	
	public static final String COD_ARQUIVO  = "003";
	
	
	public Venda() {}
	public Venda(String linha) {
		//Aqui poderia ser criado um método para que o formato da linha fosse validado, bem como 
		//validação de cpf, entre outros. Só que eu não vou fazer isto, é muito trivial e pouco estimulante.
		String[] dados = linha.split("ç");
		String idVenda     = dados[1];
		
		String itensLinha    = dados[2];
		String[] itens = itensLinha.replace("[", "").replace("]", "").split(",");
		
		List<ItemVenda> itensVenda = new ArrayList<ItemVenda>();
		
		for(String itemPedido : itens) {
			String[] dadosItem = itemPedido.split("-");
			String idItem     = dadosItem[0];
			String quantidade    = dadosItem[1];
			String preco = dadosItem[2];
			
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setIdItem(idItem);
			itemVenda.setQuantidade(Long.valueOf(quantidade));
			itemVenda.setPreco(Double.valueOf(preco));
			itensVenda.add(itemVenda);
		}						
		
		String nomeVendedor = dados[3];		
		this.setIdVenda(idVenda);
		this.setItensVenda(itensVenda);
		this.setNomeVendedor(nomeVendedor);		
		
	}
	
	
	private String idVenda;
	private String nomeVendedor;	
	private List<ItemVenda> itensVenda;
	
	
	public String getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	
	public double getValorTotal() {
		double total = 0;
		if( itensVenda != null && ! itensVenda.isEmpty() ) {
			for(ItemVenda i : itensVenda) {
				total+= i.getQuantidade() * i.getPreco();
			}
		}		
		return total;
	}
	
	
	
	
}
