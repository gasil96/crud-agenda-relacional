# CRUD_PROJECT_SCHEDULE
Projeto CRUD agenda criado com o intuito de estudar e revisar conceitos básicos de Java, Java OO, Maven, Mysql, relacionamento e versionamento com Git/Github.

### FOI UTILIZADO
Eclipse 2018 02 --> como IDE

Windowbuilder SWT --> plugin para construção e manipulação de telas

Maven 3.6.0 --> automação e gerenciamento de bibliotecas <dependecias> do projeto.
	
Java Vesao 8 --> linguagem

Git/Github --> versionamento do projeto e hospedagem do código fonte

MySQL 5.7 --> Banco de Dados

MySQLWorkbench 8.0 CE --> modelagem do banco de dados e seu relacionamento.


## CRUD - Create, Readme, Update, Delete 
O crud é um modelo de criação de projeto que tem como objetivo criar, ler, alterar, e deletar um registro dentro de um banco de dados atraves de uma aplicação.


## MONTAGEM DO PROJETO
O *Designer Pattener* em que o projeto foi montado foi seguindo o modelo *SINGLETON* de java onde um objeto pode ser instanciado apenas uma vez. 
~~~~
ContatoDAO cttDAO = new ContatoDAO();
~~~~
A estrutura de divisão colocou uma *organização* dentro da aplicação separando as classes por pacotes
![padrao de projeto](https://user-images.githubusercontent.com/48265863/56034530-e762bd80-5cfd-11e9-9db3-d27331da032d.JPG)

## BANCO DE DADOS
o banco de dados utilizado no projeto foi o MySQL criado atraves da command line o nome adotado ao banco foi **agenda**
e 2 tabelas *relacionadas* tabela **contato** par os contatos adicionados a agenda e a tabela **comunica** para os tipos de comunicações que estes contatos possuem ( emails, telefones residenciais, telefoens comerciais, celulares, etc...). O banco foi criado atraves da **command line** do **mysql** e gerenciado com o uso do **workbench**.
![MySQL BD CommandLine](https://user-images.githubusercontent.com/48265863/56034527-e598fa00-5cfd-11e9-8f4c-ec1ebe88e307.JPG)

### Modelagem no Workbench
antes da criação das tabelas é necessário *abstrair* suas idéias e ver como irar desenvoler seu banco, com isso em mente o **WORKBENCH** é uma ótima ferramente de modelagem podendo materializar sua idéia através de representação gráfica!
![ModelagemAgendaWorkbench](https://user-images.githubusercontent.com/48265863/56034525-e336a000-5cfd-11e9-8f44-6bc35fa0ca20.JPG)

### Tabelas
apos a modelagem esta na hora da criação das tabelas os códigos **sql's** utilizados para a criação das tabelas **contato** e **comunica** foram respectivamente.

````
CREATE TABLE `agenda`.`contato` (
  `id_contato` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(75) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `idade` INT NOT NULL,
  `sexo` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_contato`));
````
-----------------------------------------------------
````
CREATE TABLE `agenda`.`comunica` (
  `id_comunica` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  `registro` VARCHAR(75) NOT NULL,
  `id_contato` INT NOT NULL,
  PRIMARY KEY (`id_comunica`),
  CONSTRAINT fk_comunica_contato FOREIGN KEY(`id_contato`)
  REFERENCES contato (`id_contato`));
````
## JAVA
a aplicação utilizou java como linguagem , usando o padrao de classe **DAO** para a criação dos métodos (crud) dentro de um pacote designado. 

### DAO 
Exemplo de método utilizando o padrão DAO *(Data Access Object)*:
~~~~
package crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import crud.entities.Contato;
import crud.jdbc.connection.DB;

public class ContatoDAO {

	public ContatoDAO() {
	}

	public void create(Contato contato) {

		Connection conn = DB.getConnection();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(
					"INSERT INTO agenda.contato " + "(nome, cpf, idade, sexo) " + "VALUES " + "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, contato.getNome());
			st.setString(2, contato.getCpf());
			st.setInt(3, contato.getIdade());
			st.setString(4, contato.getSexo());
			st.execute();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		finally {

			try {

				st.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}
	}
~~~~
### ENUM
Tambem foi utilizado **ENUMERAÇÕES**, **(ENUM)** para diminuir o erro do usuário ao **inserir** ou **alterar**  registros nas tabelas do banco.  
~~~~~~
package crud.entities;

public enum TipoComunicaEnum {

	EMAIL, CELULAR, TELEFONE_RESIDENCIAL, TELEFONE_COMERCIAL;

	private String valor;

	public String getValor() {
		return valor;
	}
}
~~~~~~ 
### Windowbuilder
para melhor manusear a interface foi utilizado um *plugin* chamado **Windowbuilder** que habilita um construtor de telas na *IDE*  e facilita o uso atraves do "arrasta e solta" 
![Windowbuilder](https://user-images.githubusercontent.com/48265863/56034539-ec277180-5cfd-11e9-9af1-7a2a82e23aed.JPG)
**NOTA: mesmo com a práticidade do plugin windowbuilder ainda é necessário manipular atraves do código fonte, pois a ferramente é limitada e muitas vezes pré-definida nas funcionalidades**

### Maven Project
o *maven* é uma ferramenta de automação de código que gera um .**xml** no seu projeto que irá baixar todas as *<dependências>* do seu projeto direto do seu próprio repositório.
~~~~
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.gabriel.agenda</groupId>
	<artifactId>crud_agenda</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<dependencies>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.13</version>
		</dependency>
	</dependencies>
	<build>
		<sourceDirectory>src</sourceDirectory>
~~~~
no projeto **agenda** foi usado a dependência do conector do mysql, sendo assim não precisamos baixar o connector e colocar ele manualmente na nossa aplicação

## GIT/GITHUB
para o versionamento e o armazenamento do nosso código utilizamos o sistema git e o hospedeiro[github](https://github.com)

### Git
git é um sistema de controle de versionamento utilizado para guardar seu código e versionar ele pra um melhor controle, existem varias plataformas que podem hospedar seu código a utilizada neste projeto foi o github
![gits](https://user-images.githubusercontent.com/48265863/56038123-8b049b80-5d07-11e9-8e28-79ecf0341766.jpg)

### Github
github é uma opção dentre alguns hospedeiros de código com a opção de *multi-desenvolvimento* onde de forma gratuita você consegue adicionar até **3** colaboradores no seu projeto. 
![´github](https://user-images.githubusercontent.com/48265863/56038119-893ad800-5d07-11e9-8e78-3092444bc19a.JPG)











