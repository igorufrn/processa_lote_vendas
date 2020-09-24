package facil_db_company;

import java.io.File;

import facil_db_company.processamento.ProcessaArquivoThread;

//Não me selecionem para algo que esta muito abaixo da minha capacidade técnica. 
//Espero que me seja enviado um DESAFIO de verdade!
//Isto foi muito trivial 
public class Principal {
	
	private static long INTERVALO_MILISEGUNDOS_VERIFICACAO_DIRETORIO_ENTRADA = 1;
	
	public static void main(String[] args ) throws InterruptedException {
		System.setProperty("file.encoding","UTF-8");
		String home = System.getenv("HOMEPATH").toString();
		      
		String inPath  =  home+"\\data\\in";
		String outPath =  home+"\\data\\out";
		File file = new File(inPath);		
		boolean inBool = file.mkdirs() || (file.exists() && file.isDirectory());
		file = new File(outPath);		
		boolean outBool = file.mkdirs() || (file.exists() && file.isDirectory());		
		if(!inBool || !outBool) {
			throw new RuntimeException("Não foi possível criar ou encontrar os diretórios para a aplicação funcionar:\n"+inPath+"\n"+outPath); 
		}
		
		
		System.out.println("Diretório de entrada de dados: " + inPath);
		System.out.println("Diretório de saída de dados: "   + outPath);
		
		while(true) {		
			
			File diretorio =  new File(inPath);			
			File[] listaArquivos = null;			
			File arquivoOrigem = null;
			
			if(diretorio.exists()) {
				if (diretorio.isDirectory()) {	            
					listaArquivos = diretorio.listFiles();	                
					if(listaArquivos != null) {	                
						for (int j = 0; j < listaArquivos.length; j++) {	                    
							arquivoOrigem = listaArquivos[j];	                        
							if (arquivoOrigem.getName().toString().toUpperCase().endsWith(".DAT")){	                             
								if(arquivoOrigem.length() != 0) {
									String arquivoDestino = file.getAbsolutePath() + "\\" + (arquivoOrigem.getName().substring(0, arquivoOrigem.getName().length()-4) + ".done.dat");
									File arquivoJaProcessado = new File(arquivoDestino);
									//Dá uma relaxada na mente, apenas para permitir que a Thread tenha tempo de criar o arquivo de saída
									//Impedir que outra thread comece a processar o mesmo arquivo simultaneamente
									Thread.sleep(50);  
									if( ! arquivoJaProcessado.exists()) {
										new ProcessaArquivoThread(arquivoOrigem.getAbsolutePath(),arquivoDestino).start();
									}
								}
							}
						}
					}
				}
			}
			
			Thread.sleep(INTERVALO_MILISEGUNDOS_VERIFICACAO_DIRETORIO_ENTRADA);
		}
	}
}
