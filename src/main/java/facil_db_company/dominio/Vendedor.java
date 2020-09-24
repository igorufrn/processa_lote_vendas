package facil_db_company.dominio;

public class Vendedor {
	
	public static final String COD_ARQUIVO  = "001";
	
	public Vendedor() {}
	public Vendedor(String linha) {
		//Aqui poderia ser criado um método para que o formato da linha fosse validado, bem como 
		//validação de cpf, entre outros. Só que eu não vou fazer isto, é muito trivial e pouco estimulante.
		String[] dados = linha.split("ç");
		String cpf     = dados[1];
		String nome    = dados[2];
		String salario = dados[3];		
		this.setNome(nome);
		this.setSalario(Double.valueOf(salario));
		this.setCpf(cpf);		
	}
	
	private String cpf;
	private String nome;
	private Double salario;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}	
	
}
