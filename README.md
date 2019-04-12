# CRUD_PROJECT_SCHEDULE
Projeto CRUD agenda criado com o intuito de estudar e revisar conceitos básicos de Java, Java OO, Maven, Mysql, relacionamento e versionamento com Git/Github.

###FOI UTILIZADO:
Eclipse 2018 02 --> como IDE
Windowbuilder --> plugin para construção e manipulação de telas
Maven --> automação e gerenciamento de bibliotecas <dependecias> do projeto.
Java --> linguagem
Git/Github --> versionamento do projeto e hospedagem do código fonte
MySQL --> Banco de Dados
MySQLWorkbench --> modelagem do banco de dados e seu relacionamento.

## CRUD - Create, Readme, Update, Delete 
O crud é um modelo de criação de projeto que tem como objetivo criar, ler, alterar, e deletar um registro dentro de um banco de dados atraves de uma aplicação.


## MONTAGEM DO PROJETO
O *Designer Pattener* em que o projeto foi montado foi seguindo o modelo *SINGLETON* de java onde um objeto pode ser instanciado apenas uma vez. 
~~~~
ContatoDAO cttDAO = new ContatoDAO();
~~~~
A estrutura de divisão colocou uma *organização* dentro da aplicação separando as classes por pacotes
![padrao de projeto](https://user-images.githubusercontent.com/48265863/56034530-e762bd80-5cfd-11e9-9db3-d27331da032d.JPG)

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
no projeto **agenda** foi usado a dapendência do conector do mysql, sendo assim não precisamos baixar o connector e colocar ele manualmente na nossa aplicação
