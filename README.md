Este desafio é muito simples, então não foi necessário nenhuma biblioteca externa.
Para executar basta executar a classe facil_db_company.Principal.
Minha sugestão é fazer o clone do projeto, executar um 'mvn clean install' e executar o JAR que é gerado na pasta target.
Um exemplo de comando para executar o projeto:
java -Dfile.encoding=UTF-8  -jar facil_db_company-0.0.1-SNAPSHOT.jar
*****USE ARQUIVOS EM FORMATO UTF-8, caso contrário poderá ocorrer erros ArrayIndexOfBounds!*****
Estou levando em consideração que os arquivos de entrada estão em formato válido.
É muito trivial validar o formato dos arquivos e o formato de coisas como CPF, CNPJ, números. Mas seria perder meu
tempo implementando isto, pois não me sinto desafiado a nada.