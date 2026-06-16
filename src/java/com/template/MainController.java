package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    @FXML private TableView<JogadorDTO> tblJogadores;
    @FXML private TableColumn<JogadorDTO, Integer> colId;
    @FXML private TableColumn<JogadorDTO, String>    colNome;
    @FXML private TableColumn<JogadorDTO, Integer> colIdade;
    @FXML private TableColumn<JogadorDTO, String> colDesempenho;
    @FXML private TableColumn<JogadorDTO, Double> colValor;

    /*Mudanças feitas

    UI
        - Melhorar o visual geral da aplicação
        - Adicionar imagens, ícones ou logotipo
        - Melhorar a aparência da tabela
        - O campo ID não deve estar editável OU não deve existir

     UX
        - Remover comandos de saída para o console
        - Utilizar mensagens amigáveis e específicas para cada situação
        - Validar campos obrigatórios e formatos
        - Solicitar confirmação antes de excluir
        - Limpar os campos após as operações


      */

    private Integer idJogadorSelecionado = null;

    private void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    private void carregarCampos(){
        JogadorDTO jogadorDto = tblJogadores.getSelectionModel().getSelectedItem();

        if(jogadorDto != null){
            this.idJogadorSelecionado = jogadorDto.getId();

            txtNome.setText(jogadorDto.getNome());
            txtIdade.setText(Integer.toString(jogadorDto.getIdade()));
            txtDesempenho.setText(jogadorDto.getDesempenho());
            txtValor.setText( Double.toString(jogadorDto.getValor()));
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event){
        if(txtNome.getText().isEmpty() || txtIdade.getText().isEmpty() || txtValor.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.WARNING, "Campos Obrigatórios", "Por favor, preencha o Nome, Idade e Valor do jogador.");
            return;
        }

        try {
            String nome = txtNome.getText();
            Integer idade = Integer.parseInt(txtIdade.getText());
            String desempenho = txtDesempenho.getText();
            Double valor = Double.parseDouble(txtValor.getText());

            if (idade < 15 || idade>60){
                exibirAlerta(Alert.AlertType.WARNING, "Idade Inválida", "Idade inserida não é válida! O jogador deve ter a idade no intervalo de 15 a 60 anos");
                return;
            }

            JogadorDTO jogadordto = new JogadorDTO();
            jogadordto.setNome(nome);
            jogadordto.setIdade(idade);
            jogadordto.setDesempenho(desempenho);
            jogadordto.setValor(valor);

            JogadorDAO jogadorDao = new JogadorDAO();
            jogadorDao.cadastrarJogador(jogadordto);

            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Jogador contratado e adicionado a seus registros!");

            btnLimparAction(null);
            carregarJogadores();
        } catch (NumberFormatException e) {
            exibirAlerta(Alert.AlertType.ERROR, "Erro de Formato", "Idade e Valor precisam ser números válidos!");
        }
    }

    @FXML
    private void btnEditarAction(ActionEvent event){
        if (idJogadorSelecionado == null) {
            exibirAlerta(Alert.AlertType.WARNING, "Aviso", "Selecione um jogador na tabela antes de clicar em Editar.");
            return;
        }

        try {
            String nome = txtNome.getText();
            Integer idade = Integer.parseInt(txtIdade.getText());
            String desempenho = txtDesempenho.getText();
            Double valor = Double.parseDouble(txtValor.getText());

            if (idade < 15 || idade>60){
                exibirAlerta(Alert.AlertType.WARNING, "Idade Inválida", "Idade inserida não é válida! O jogador deve ter a idade no intervalo de 15 a 60 anos");
                return;
            }

            JogadorDTO jogadorDto = new JogadorDTO();
            jogadorDto.setNome(nome);
            jogadorDto.setIdade(idade);
            jogadorDto.setDesempenho(desempenho);
            jogadorDto.setValor(valor);

            JogadorDAO jogadorDao = new JogadorDAO();
            jogadorDao.atualizarJogador(idJogadorSelecionado, jogadorDto);

            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Dados do jogador atualizados com sucesso!");

            btnLimparAction(null);
            carregarJogadores();
        } catch (NumberFormatException e) {
            exibirAlerta(Alert.AlertType.ERROR, "Erro de Formato", "Verifique se a Idade e o Valor foram digitados corretamente.");
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event){

        if (idJogadorSelecionado == null) {
            exibirAlerta(Alert.AlertType.WARNING, "Aviso", "Selecione o jogador que deseja rescindir.");
            return;
        }

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmar Exclusão");
        confirmacao.setHeaderText("Excluir Registro de Jogador");
        confirmacao.setContentText("Tem certeza de que deseja mesmo rescindir o contrato com esse jogador?");

        if (confirmacao.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            JogadorDAO jogadorDao = new JogadorDAO();
            jogadorDao.deletarJogador(idJogadorSelecionado);

            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "O jogador foi removido do sistema.");

            btnLimparAction(null);
            carregarJogadores();
        }
    }
    @FXML
    private void btnLimparAction(ActionEvent Event){
        this.idJogadorSelecionado = null;

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