package com.template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btExcluir;
    @FXML private TextField txtNome;
    @FXML private TextField txtIdade;
    @FXML private TextField txtDesempenho;
    @FXML private TextField txtValor;
    @FXML private TextField txtId;
    @FXML private TableView<JogadorDTO> tblJogadores;
    @FXML private TableColumn<JogadorDTO, Integer> colId;
    @FXML private TableColumn<JogadorDTO, String>    colNome;
    @FXML private TableColumn<JogadorDTO, Integer> colIdade;
    @FXML private TableColumn<JogadorDTO, String> colDesempenho;
    @FXML private TableColumn<JogadorDTO, Double> colValor;

    @FXML
    private void btnSalvarAction(ActionEvent event){
        String nome = txtNome.getText();
        Integer idade = Integer.parseInt(txtIdade.getText());
        String desempenho = txtDesempenho.getText();
        Double valor = Double.parseDouble(txtValor.getText());

        JogadorDTO jogadordto = new JogadorDTO();
        jogadordto.setNome(nome);
        jogadordto.setIdade(idade);
        jogadordto.setDesempenho(desempenho);
        jogadordto.setValor(valor);

        JogadorDAO jogadordao = new JogadorDAO();
        jogadordao.cadastrarJogador(jogadordto);
    }

    @FXML
    private void btnEditarAction(ActionEvent event){
        Integer id = Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        Integer idade = Integer.parseInt(txtIdade.getText());
        String desempenho = txtDesempenho.getText();
        Double valor = Double.parseDouble(txtValor.getText());

        JogadorDTO jogadordto = new JogadorDTO();
        jogadordto.setNome(nome);
        jogadordto.setIdade(idade);
        jogadordto.setDesempenho(desempenho);
        jogadordto.setValor(valor);

        JogadorDAO jogadordao = new JogadorDAO();
        jogadordao.atualizarJogador(id,jogadordto);
    }

    @FXML
    private void btnExcluirAction(ActionEvent event){
        Integer id = Integer.parseInt(txtId.getText());

        JogadorDAO jogadordao = new JogadorDAO();
        jogadordao.deletarJogador(id);
    }

    @FXML
    private void carregarJogadores(){
        JogadorDAO jogadordao = new JogadorDAO();
        ArrayList<JogadorDTO> listaJogadores = jogadordao.mostrarJogadores();
    }

    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}