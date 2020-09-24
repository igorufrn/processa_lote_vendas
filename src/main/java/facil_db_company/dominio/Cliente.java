package facil_db_company.dominio;

public class Cliente {
	
	public static final String COD_ARQUIVO  = "002";
	
	private String cnpj;
	private String nome;
	private String businessArea;
	
	
	public Cliente() {}
	public Cliente(String linha) {
		//Aqui poderia ser criado um método para que o formato da linha fosse validado, bem como 
		//validação de cpf, entre outros. Só que eu não vou fazer isto, é muito trivial e pouco estimulante.
		String[] dados = linha.split("ç");
		String cnpj     = dados[1];
		String nome    = dados[2];
		String area = dados[3];		
		this.setNome(nome);
		this.setBusinessArea(area);
		this.setCnpj(cnpj);		
	}
	
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	
	
	
}
