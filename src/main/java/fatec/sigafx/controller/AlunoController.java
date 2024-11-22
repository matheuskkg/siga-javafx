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
        // Busca as turmas associadas ao aluno logado
        List<TurmaModel> turmas = TurmaModel.buscarPorAluno((AlunoModel) usuarioLogado);

        // Transforma as turmas em uma ObservableList para a TableView
        ObservableList<TurmaModel> turmasObs = FXCollections.observableArrayList(turmas);

        // Configura a coluna da disciplina
        turmaDisciplinaNotas.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getDisciplina().getNome())
        );

        // Configura a coluna da P1
        alunoP1.setCellValueFactory(param -> {
            TurmaModel turma = param.getValue();
            List<NotaModel> notas = NotaModel.buscarPorAlunoTurma(usuarioLogado.getId(), turma.getId());
            NotaModel notaP1 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P1)
                    .findFirst()
                    .orElse(null);
            return new SimpleObjectProperty<>(notaP1 != null ? notaP1.getNota() : null);
        });

        // Configura a coluna da P2
        alunoP2.setCellValueFactory(param -> {
            TurmaModel turma = param.getValue();
            List<NotaModel> notas = NotaModel.buscarPorAlunoTurma(usuarioLogado.getId(), turma.getId());
            NotaModel notaP2 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P2)
                    .findFirst()
                    .orElse(null);
            return new SimpleObjectProperty<>(notaP2 != null ? notaP2.getNota() : null);
        });

        // Configura a coluna da P3
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

            // Busca as notas de P1, P2 e P3
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

            // Garante valores seguros para as notas
            double p1 = (notaP1 != null && notaP1.getNota() != null) ? notaP1.getNota() : 0.0;
            double p2 = (notaP2 != null && notaP2.getNota() != null) ? notaP2.getNota() : 0.0;
            double p3 = (notaP3 != null && notaP3.getNota() != null) ? notaP3.getNota() : 0.0;

            // Calcula a média inicial com P1 e P2
            double media = (p1 + p2) / 2.0;

            // Determina a situação do aluno
            String situacao;
            if (media >= 6.0) {
                situacao = "Aprovado";
            } else {
                // Se necessário, inclui P3 no cálculo
                media = (p1 + p2 + p3) / 3.0;
                situacao = (media >= 6.0) ? "Aprovado" : "Reprovado";
            }

            return new SimpleStringProperty(situacao);
        });



        // Associa as turmas ao TableView
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
