package fatec.sigafx.controller;

import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.view.LoginView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.util.List;

import static fatec.sigafx.controller.LoginController.usuarioLogado;

public class AlunoController {

    @FXML
    private VBox gPrincipal;
    public Label lBoasVindas;

    @FXML
    private VBox gNotas;
    public TableView<TurmaModel> tabelaNotas;
    public TableColumn<TurmaModel, String> turmaDisciplinaNotas;
    public TableColumn<TurmaModel, Double> alunoP1;
    public TableColumn<TurmaModel, Double> alunoP2;
    public TableColumn<TurmaModel, Double> alunoP3;
    public TableColumn<TurmaModel, String> alunoSituacaoNotas;

    @FXML
    private VBox gFaltas;
    public TableView<TurmaModel> tabelaFaltas;
    public TableColumn<TurmaModel, String> turmaDisciplinaFaltas;
    public TableColumn<TurmaModel, Integer> alunoFaltas;
    public TableColumn<TurmaModel, String> alunoSituacaoFaltas;

    @FXML
    public void initialize() {
        lBoasVindas.setText("Bem vindo(a), " + usuarioLogado.getNome() + "!");
        carregarTabelaNotas();
        carregarTabelaFaltas();
    }

    private void carregarTabelaNotas() {
        List<TurmaModel> turmas = TurmaModel.buscarPorAluno((AlunoModel) usuarioLogado);
        ObservableList<TurmaModel> turmasObs = FXCollections.observableArrayList(turmas);

        // Configurar a coluna de disciplina
        turmaDisciplinaNotas.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getDisciplina().getNome()));

        // Configurar as colunas de notas
        alunoP1.setCellValueFactory(param -> {
            AlunoModel aluno = (AlunoModel) usuarioLogado;
            Double notaP1 = aluno.getNotaP1();
            return new SimpleObjectProperty<>(notaP1);
        });

        alunoP2.setCellValueFactory(param -> {
            AlunoModel aluno = (AlunoModel) usuarioLogado;
            Double notaP2 = aluno.getNotaP2();
            return new SimpleObjectProperty<>(notaP2);
        });

        alunoP3.setCellValueFactory(param -> {
            AlunoModel aluno = (AlunoModel) usuarioLogado;
            Double notaP3 = aluno.getNotaP3();
            return new SimpleObjectProperty<>(notaP3);
        });

        tabelaNotas.setItems(turmasObs);
    }

    private void carregarTabelaFaltas() {
        List<TurmaModel> turmas = TurmaModel.buscarPorAluno((AlunoModel) usuarioLogado);
        ObservableList<TurmaModel> turmasObs = FXCollections.observableArrayList(turmas);

        // Configurar a coluna de disciplina
        turmaDisciplinaFaltas.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getDisciplina().getNome()));

        tabelaFaltas.setItems(turmasObs);
    }

    // Esconder todos os painéis
    private void esconderPaineis() {
        gPrincipal.setVisible(false);
        gNotas.setVisible(false);
        gFaltas.setVisible(false);
    }

    // Mostrar "Início"
    @FXML
    public void mostrarInicio() {
        esconderPaineis();
        gPrincipal.setVisible(true);
    }

    // Mostrar "Notas"
    @FXML
    public void mostarNotas() {
        esconderPaineis();
        gNotas.setVisible(true);
    }

    // Mostrar "Início"
    @FXML
    public void mostarFaltas() {
        esconderPaineis();
        gFaltas.setVisible(true);
    }

    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();

    }
}
