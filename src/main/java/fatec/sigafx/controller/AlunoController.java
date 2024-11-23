package fatec.sigafx.controller;

import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.aulas.TipoNota;
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
    @FXML
    private Label lBoasVindas;

    @FXML
    private VBox gNotas;
    @FXML
    private TableView<TurmaModel> tabelaNotas;
    @FXML
    private TableColumn<TurmaModel, String> turmaDisciplinaNotas;
    @FXML
    private TableColumn<TurmaModel, Double> alunoP1;
    @FXML
    private TableColumn<TurmaModel, Double> alunoP2;
    @FXML
    private TableColumn<TurmaModel, Double> alunoP3;
    @FXML
    private TableColumn<TurmaModel, String> alunoSituacaoNotas;

    @FXML
    private VBox gFaltas;
    @FXML
    private TableView<TurmaModel> tabelaFaltas;
    @FXML
    private TableColumn<TurmaModel, String> turmaDisciplinaFaltas;
    @FXML
    private TableColumn<TurmaModel, Integer> alunoFaltas;
    @FXML
    private TableColumn<TurmaModel, String> alunoSituacaoFaltas;

    @FXML
    private void initialize() {
        lBoasVindas.setText("Bem vindo(a), " + usuarioLogado.getNome() + "!");
        carregarTabelaNotas();
        carregarTabelaFaltas();
    }

    private void carregarTabelaNotas() {
        List<TurmaModel> turmas = TurmaModel.buscarPorAluno((AlunoModel) usuarioLogado);
        ObservableList<TurmaModel> turmasObs = FXCollections.observableArrayList(turmas);

        turmaDisciplinaNotas.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getDisciplina().getNome())
        );

        alunoP1.setCellValueFactory(param -> {
            TurmaModel turma = param.getValue();
            List<NotaModel> notas = NotaModel.buscarPorAlunoTurma(usuarioLogado.getId(), turma.getId());
            NotaModel notaP1 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P1)
                    .findFirst()
                    .orElse(null);
            return new SimpleObjectProperty<>(notaP1 != null ? notaP1.getNota() : null);
        });

        alunoP2.setCellValueFactory(param -> {
            TurmaModel turma = param.getValue();
            List<NotaModel> notas = NotaModel.buscarPorAlunoTurma(usuarioLogado.getId(), turma.getId());
            NotaModel notaP2 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P2)
                    .findFirst()
                    .orElse(null);
            return new SimpleObjectProperty<>(notaP2 != null ? notaP2.getNota() : null);
        });

        alunoP3.setCellValueFactory(param -> {
            TurmaModel turma = param.getValue();
            List<NotaModel> notas = NotaModel.buscarPorAlunoTurma(usuarioLogado.getId(), turma.getId());
            NotaModel notaP3 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P3)
                    .findFirst()
                    .orElse(null);
            return new SimpleObjectProperty<>(notaP3 != null ? notaP3.getNota() : null);
        });

        alunoSituacaoNotas.setCellValueFactory(param -> {
            TurmaModel turma = param.getValue();
            List<NotaModel> notas = NotaModel.buscarPorAlunoTurma(usuarioLogado.getId(), turma.getId());

            NotaModel notaP1 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P1)
                    .findFirst()
                    .orElse(null);

            NotaModel notaP2 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P2)
                    .findFirst()
                    .orElse(null);

            NotaModel notaP3 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P3)
                    .findFirst()
                    .orElse(null);

            double p1 = (notaP1 != null && notaP1.getNota() != null) ? notaP1.getNota() : 0.0;
            double p2 = (notaP2 != null && notaP2.getNota() != null) ? notaP2.getNota() : 0.0;
            double p3 = (notaP3 != null && notaP3.getNota() != null) ? notaP3.getNota() : 0.0;

            double media = (p1 + p2) / 2.0;

            String situacao;
            if (media >= 6.0) {
                situacao = "Aprovado";
            } else {
                media = (p1 + p2 + p3) / 3.0;
                situacao = (media >= 6.0) ? "Aprovado" : "Reprovado";
            }

            return new SimpleStringProperty(situacao);
        });

        tabelaNotas.setItems(turmasObs);
    }

    private void carregarTabelaFaltas() {
        List<TurmaModel> turmas = TurmaModel.buscarPorAluno((AlunoModel) usuarioLogado);

        for (TurmaModel turma : turmas) {
            Integer faltas = AlunoModel.contarFaltasAlunoTurma(usuarioLogado.getId(), turma.getId());
            turma.setFaltas(faltas);
        }

        ObservableList<TurmaModel> turmasObs = FXCollections.observableArrayList(turmas);

        turmaDisciplinaFaltas.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getDisciplina().getNome()));

        alunoFaltas.setCellValueFactory(param ->
                new SimpleObjectProperty<>(param.getValue().getFaltas()));

        alunoSituacaoFaltas.setCellValueFactory(param -> {
            TurmaModel turma = param.getValue();
            Integer faltas = turma.getFaltas();
            Integer cargaHoraria = turma.getDisciplina().getCargaHoraria();

            String situacao;
            if ((cargaHoraria == 40 && faltas > 5) || (cargaHoraria == 80 && faltas > 10)) {
                situacao = "Reprovado";
            } else {
                situacao = "Aprovado";
            }

            return new SimpleStringProperty(situacao);
        });


        tabelaFaltas.setItems(turmasObs);
    }

    // Esconder todos os painéis
    private void esconderPaineis() {
        gPrincipal.setVisible(false);
        gNotas.setVisible(false);
        gFaltas.setVisible(false);

        tabelaNotas.getSelectionModel().clearSelection();
        tabelaFaltas.getSelectionModel().clearSelection();
    }

    // Mostrar "Início"
    @FXML
    private void mostrarInicio() {
        esconderPaineis();
        gPrincipal.setVisible(true);
    }

    // Mostrar "Notas"
    @FXML
    private void mostarNotas() {
        esconderPaineis();
        gNotas.setVisible(true);
    }

    // Mostrar "Início"
    @FXML
    private void mostarFaltas() {
        esconderPaineis();
        gFaltas.setVisible(true);
    }

    @FXML
    private void onLogoutClicked() {
        LoginView.mostrarLogin();
    }
}
