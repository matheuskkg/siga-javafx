package fatec.sigafx.controller;

import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.util.AulasUtil;
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

        for (TurmaModel turma : turmas) {
            List<NotaModel> notas = NotaModel.buscarNotasPorAlunoETurma(usuarioLogado.getId(), turma.getId());
            turma.setNotas(notas);
        }

        ObservableList<TurmaModel> turmasObs = FXCollections.observableArrayList(turmas);

        turmaDisciplinaNotas.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDisciplina().getNome()));

        alunoP1.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getNotaAlunoPorIndice(0)));

        alunoP2.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getNotaAlunoPorIndice(1)));

        alunoP3.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getNotaAlunoPorIndice(2)));

        tabelaNotas.setItems(turmasObs);
    }

    private void carregarTabelaFaltas() {
        // Obter as turmas em que o aluno logado está registrado
        List<TurmaModel> turmas = TurmaModel.buscarPorAluno((AlunoModel) usuarioLogado);

        // Para cada turma, buscar o número de faltas e associar
        for (TurmaModel turma : turmas) {
            Integer faltas = AlunoModel.contarFaltasAlunoTurma(usuarioLogado.getId(), turma.getId());
            turma.setFaltas(faltas);
        }

        // Converter para ObservableList
        ObservableList<TurmaModel> turmasObs = FXCollections.observableArrayList(turmas);

        // Configurar a coluna de disciplina
        turmaDisciplinaFaltas.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getDisciplina().getNome()));

        // Configurar a coluna de faltas
        alunoFaltas.setCellValueFactory(param ->
                new SimpleObjectProperty<>(param.getValue().getFaltas()));

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
