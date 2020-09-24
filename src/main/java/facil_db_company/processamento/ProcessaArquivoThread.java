package facil_db_company.processamento;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import facil_db_company.dominio.Cliente;
import facil_db_company.dominio.Venda;
import facil_db_company.dominio.Vendedor;

public class ProcessaArquivoThread extends Thread {
	
	private String arquivoOrigem;	
	private String arquivoDestino;
	
	
	public ProcessaArquivoThread(String arquivoOrigem, String arquivoDestino) {
		this.arquivoOrigem = arquivoOrigem;
		this.arquivoDestino = arquivoDestino;
	}	
	
	public void run() {
		
		//Impedir que outra thread comece a processar o mesmo arquivo simultaneamente
		geraArquivoSaidaVazio();
		
		//Gerenciamento automático dos recursos - JVM irá fechar automaticamente as streams
		try (FileInputStream fis = new FileInputStream(this.arquivoOrigem); 
				Scanner sc = new Scanner(fis)) {	
			
			int totalClientes = 0; //Quantidade de clientes no arquivo de entrada
			int totalVendedores = 0;//Quantidade de vendedor no arquivo de entrada
			String idMaiorVenda = "";
			double valorMaiorVenda = 0;				
			Map<String, Double> totalVendasPorVendedor = new HashMap<String, Double>();			
			
			while(sc.hasNextLine())	{				
				String linha = sc.nextLine().trim();
				if( ! linha.isEmpty() ) {					
					if(linha.substring(0, 3).equalsIgnoreCase(Vendedor.COD_ARQUIVO)) {
						totalVendedores++;												
						//Vendedor vendedor = new Vendedor(linha);						
					} else if(linha.substring(0, 3).equalsIgnoreCase(Cliente.COD_ARQUIVO)) {
						totalClientes++;						
						//Cliente cliente = new Cliente(linha);						
					} else if(linha.substring(0, 3).equalsIgnoreCase(Venda.COD_ARQUIVO)) {						
						
						Venda venda = new Venda(linha);						
						
						if(totalVendasPorVendedor.containsKey(venda.getNomeVendedor())) {
							totalVendasPorVendedor.put(venda.getNomeVendedor(),totalVendasPorVendedor.get(venda.getNomeVendedor()) + venda.getValorTotal());
						} else {
							totalVendasPorVendedor.put(venda.getNomeVendedor(),venda.getValorTotal());
						}
						
						if(idMaiorVenda.isEmpty()) {
							idMaiorVenda = venda.getIdVenda();
							valorMaiorVenda = venda.getValorTotal();
						} else if( valorMaiorVenda < venda.getValorTotal()) {
							idMaiorVenda = venda.getIdVenda();
							valorMaiorVenda = venda.getValorTotal();
						}						
						
					}	
				}
			}
			
			geraArquivoSaida(totalClientes, totalVendedores, idMaiorVenda, totalVendasPorVendedor);
			
		}  
		catch(IOException e) {
			e.printStackTrace();
		} 		
    }

	

	private void geraArquivoSaida(int totalClientes, int totalVendedores, String idMaiorVenda,
			Map<String, Double> totalVendasPorVendedor) throws FileNotFoundException {
		try (PrintStream out = new PrintStream(new FileOutputStream(this.arquivoDestino))) {
		    out.println("Quantidade de clientes no arquivo de entrada = " + totalClientes);
		    out.println("Quantidade de vendedor no arquivo de entrada = " + totalVendedores);
		    out.println("ID da venda mais cara = " + idMaiorVenda);
		    Stream<Map.Entry<String, Double>> sorted =	totalVendasPorVendedor.entrySet().stream().sorted(Map.Entry.comparingByValue());
		    out.print("O pior vendedor = " + sorted.iterator().next().getKey());
		}
	}	
	
	private void geraArquivoSaidaVazio()  {		
		try (PrintStream out = new PrintStream(new FileOutputStream(this.arquivoDestino))) {			
		    out.flush();		    
		} catch (FileNotFoundException  e) {
			throw new RuntimeException("Não foi possível iniciar o processamento, pois não foi possível gerar o arquivo de destino");
		}
	}
	

}
