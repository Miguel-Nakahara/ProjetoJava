package com.template;

import com.template.Conexao;
import com.template.JogadorDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* A classe futebolDAO é o CRUD dos jogadores.
   Nela toda a lógica é feita para cadastrar, ler, atualizar e deletar os jogadores.*/

public class JogadorDAO {

    private static final Logger logger = Logger.getLogger(JogadorDAO.class.getName());

    public void cadastrarJogador(JogadorDTO jogador){
        String sql = "INSERT INTO jogadores (nome, idade, valor, desempenho) VALUES (?,?,?,?)";

        try(Connection c = new Conexao().conectaBD(); PreparedStatement pstm = c.prepareStatement(sql)){

            pstm.setString(1, jogador.getNome());
            pstm.setInt(2, jogador.getIdade());
            pstm.setDouble(3,jogador.getValor());
            pstm.setString(4, jogador.getDesempenho());

            pstm.execute();

        }catch (SQLException e){
            logger.log(Level.SEVERE, "Erro ao executar operação", e);
        }

    };

    public void deletarJogador(int id)
    {
        String sql = "DELETE FROM jogadores WHERE id_jogador = ?";
        try(Connection c = new Conexao().conectaBD(); PreparedStatement pstm = c.prepareStatement(sql)){

            pstm.setInt(1, id);

        }catch(SQLException e){
            logger.log(Level.SEVERE, "Erro ao executar operação", e);
        }
    }

    public void atualizarJogador(int id, JogadorDTO jogador)
    {
        String sql = "UPDATE jogadores SET nome = ?, idade = ?, valor = ?, desempenho = ? WHERE id_jogador = ?";
        try(Connection c = new Conexao().conectaBD(); PreparedStatement pstm = c.prepareStatement(sql)){

            pstm.setString(1, jogador.getNome());
            pstm.setInt(2, jogador.getIdade());
            pstm.setDouble(3, jogador.getValor());
            pstm.setString(4, jogador.getDesempenho());
            pstm.setInt(5, id);

            pstm.execute();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Erro ao executar operação", e);
        }
    }

    public List<JogadorDTO> mostrarJogadores()
    {
        List<JogadorDTO> listaDeJogadores = new ArrayList<>();
        String sql = "SELECT * FROM jogadores";

        try(Connection c = new Conexao().conectaBD(); PreparedStatement pstm = c.prepareStatement(sql)){

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                JogadorDTO jogador = new JogadorDTO();
                jogador.setId(rs.getInt("id_jogador"));
                jogador.setNome(rs.getString("nome"));
                jogador.setIdade(rs.getInt("idade"));
                jogador.setValor(rs.getInt("valor"));
                jogador.setDesempenho(rs.getString("desempenho"));

                listaDeJogadores.add(jogador);
            }

        }catch(SQLException e){
            logger.log(Level.SEVERE, "Erro ao executar operação", e);
        }

        return listaDeJogadores;


    }
}
