package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;
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
    private void carregarCampos(){
        JogadorDTO jogadorDto = tblJogadores.getSelectionModel().getSelectedItem();

        if(jogadorDto != null){
            txtId.setText(Integer.toString(jogadorDto.getId()));
            txtNome.setText(jogadorDto.getNome());
            txtIdade.setText(Integer.toString(jogadorDto.getIdade()));
            txtDesempenho.setText(jogadorDto.getDesempenho());
            txtValor.setText( Double.toString(jogadorDto.getValor()));
        }
    }

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

        JogadorDAO jogadorDao = new JogadorDAO();
        jogadorDao.cadastrarJogador(jogadordto);

        carregarJogadores();
    }

    @FXML
    private void btnEditarAction(ActionEvent event){
        Integer id = Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        Integer idade = Integer.parseInt(txtIdade.getText());
        String desempenho = txtDesempenho.getText();
        Double valor = Double.parseDouble(txtValor.getText());

        JogadorDTO jogadorDto = new JogadorDTO();
        jogadorDto.setNome(nome);
        jogadorDto.setIdade(idade);
        jogadorDto.setDesempenho(desempenho);
        jogadorDto.setValor(valor);

        JogadorDAO jogadorDao = new JogadorDAO();
        jogadorDao.atualizarJogador(id,jogadorDto);

        carregarJogadores();
    }

    @FXML
    private void btnExcluirAction(ActionEvent event){
        Integer id = Integer.parseInt(txtId.getText());

        JogadorDAO jogadorDao = new JogadorDAO();
        jogadorDao.deletarJogador(id);

        carregarJogadores();
    }
    @FXML
    private void btnLimparAction(ActionEvent Event){
        txtId.clear();
        txtNome.clear();
        txtIdade.clear();
        txtDesempenho.clear();
        txtValor.clear();
    }

    @FXML
    private void carregarJogadores(){
        JogadorDAO jogadorDao = new JogadorDAO();
        ArrayList<JogadorDTO> listaJogadores = jogadorDao.mostrarJogadores();
        tblJogadores.setItems(FXCollections.observableArrayList(listaJogadores));
    }


    @FXML
    private void initialize()
    {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colDesempenho.setCellValueFactory(new PropertyValueFactory<>("desempenho"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        carregarJogadores();
        System.out.println("FXML loaded successfully!");
    }
}