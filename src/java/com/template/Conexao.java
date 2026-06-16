package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*  A classe Conexão é a classe responsável por conectar a aplicação com o banco de dados
    Atribuido a ela. Nela definimos qual o banco que vamos utilizar e colocamos as informações
    para acessar o banco.
*/

public class Conexao {

    static String conexao = "jdbc:postgresql://localhost:5432/futebol";
    static String usuario = "postgres";
    static String senha = "Postgres123@";

    public Connection conectaBD(){
        try{
            return DriverManager.getConnection(conexao,usuario,senha);
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
