package fatec.sigafx.controller;

import fatec.sigafx.model.aulas.*;
import fatec.sigafx.model.aulas.dto.NotaCriarRequest;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.ProfessorModel;
import fatec.sigafx.view.LoginView;
import javafx.animation.PauseTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static fatec.sigafx.controller.LoginController.usuarioLogado;

public class ProfessorController {

    @FXML
    private VBox gPrincipal;
    @FXML
    private Label lBoasVindas;

    @FXML
    private VBox gMensagemSucesso;
    @FXML
    private Label mSucesso;

    @FXML
    private VBox gNotas;
    @FXML
    private VBox gAlunosTurma;
    @FXML
    private HBox hAtribuirNotasTurma;
    @FXML
    private ComboBox<TurmaModel> cbAtribuirNotasTurma;
    @FXML
    private Label mAtribuirNotas;

    @FXML
    private VBox gAtribuirNotas;
    @FXML
    private TextField nomeAlunoNota;
    @FXML
    private CheckBox checkP1;
    @FXML
    private HBox hAtribuirP1;
    @FXML
    private Spinner<Double> sP1;
    @FXML
    private CheckBox checkP2;
    @FXML
    private HBox hAtribuirP2;
    @FXML
    private Spinner<Double> sP2;
    @FXML
    private CheckBox checkP3;
    @FXML
    private HBox hAtribuirP3;
    @FXML
    private Spinner<Double> sP3;
    @FXML
    private Label mAtribuirNotasAluno;

    @FXML
    private VBox gFaltas;
    @FXML
    private VBox gRealizarAlterar;

    @FXML
    private VBox gRealizarChamada;
    @FXML
    private HBox hTurmaRealizarChamada;
    @FXML
    private ComboBox<TurmaModel> cbTurmaRealizarChamada;
    @FXML
    private HBox hDataRealizarChamada;
    @FXML
    private DatePicker dpRealizarChamada;
    @FXML
    private Label mRealizarChamada;

    @FXML
    private VBox gAlunosFaltas;
    @FXML
    private HBox hAtribuirFaltasTurma;
    @FXML
    private ComboBox<TurmaModel> cbAtribuirFaltasTurma;
    @FXML
    private Label mAlunosFaltas;

    @FXML
    private VBox gAlterarFaltas;
    @FXML
    private TextField tfTurmaAlterarFaltas;
    @FXML
    private TextField tfAlunoAlterarFaltas;
    @FXML
    private Spinner<Integer> sFaltas;
    @FXML
    private Label mAlterarFaltas;


    private TableView<AlunoModel> tAtribuirNotasAlunos;

    private TableView<AlunoModel> tRealizarChamada;

    private TableView<AlunoModel> tAtribuirFaltasAlunos;

    private TurmaModel turmaSelecionada;
    private AlunoModel alunoSelecionado;

    @FXML
    private void initialize() {
        lBoasVindas.setText("Bem vindo(a), " + usuarioLogado.getNome() + "!");
        carregarComboBoxTurmas();
        configurarDatePickerRestrito(dpRealizarChamada);
    }

    private void configurarDatePickerRestrito(DatePicker datePicker) {
        Callback<DatePicker, DateCell> dayCellFactory = picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
    }

    private SpinnerValueFactory<Double> montaSpinners(Double nota) {
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10) {
            @Override
            public void increment(int steps) {
                if (getValue() == null) {
                    setValue(0.0);
                } else {
                    super.increment(steps);
                }
            }

            @Override
            public void decrement(int steps) {
                if (getValue() == null || getValue() == 0.0) {
                    setValue(null);
                } else {
                    super.decrement(steps);
                }
            }
        };

        valueFactory.setValue(nota);
        return valueFactory;
    }

    private SpinnerValueFactory<Integer> montaSpinnerFaltas(Integer faltas, int max) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, max) {
            @Override
            public void increment(int steps) {
                if (getValue() == null) {
                    setValue(0);
                } else {
                    super.increment(steps);
                }
            }

            @Override
            public void decrement(int steps) {
                if (getValue() == null || getValue() == 0) {
                    setValue(null);
                } else {
                    super.decrement(steps);
                }
            }
        };

        valueFactory.setValue(faltas);
        return valueFactory;
    }

    private void configuraSpinners() {
        List<NotaModel> notas = NotaModel.buscarPorAlunoTurma(alunoSelecionado.getId(), turmaSelecionada.getId());

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

        sP1.setValueFactory(montaSpinners(notaP1 != null ? notaP1.getNota() : null));
        sP2.setValueFactory(montaSpinners(notaP2 != null ? notaP2.getNota() : null));
        sP3.setValueFactory(montaSpinners(notaP3 != null ? notaP3.getNota() : null));
    }

    private <T> ComboBox<T> reconstruirComboBox(ComboBox<T> comboBox, HBox hboxPai) {
        ComboBox<T> novaComboBox = new ComboBox<>(comboBox.getItems());
        novaComboBox.setMaxSize(Double.MAX_VALUE, 30);
        HBox.setHgrow(novaComboBox, Priority.ALWAYS);
        hboxPai.setAlignment(Pos.CENTER);
        novaComboBox.setPromptText(comboBox.getPromptText());

        hboxPai.getChildren().remove(comboBox);
        hboxPai.getChildren().add(novaComboBox);
        return novaComboBox;
    }

    private void carregarComboBoxTurmas() {
        List<TurmaModel> turmas = TurmaModel.buscarPorProfessor((ProfessorModel) usuarioLogado);
        ObservableList<TurmaModel> obsTurmas = FXCollections.observableArrayList();
        obsTurmas.addAll(turmas);

        cbAtribuirFaltasTurma.setItems(obsTurmas);
        cbTurmaRealizarChamada.setItems(obsTurmas);
        cbAtribuirNotasTurma.setItems(obsTurmas);
    }

    private <T> TableColumn<AlunoModel, T> criarColunaTableView(String nome, int minWidth, int maxWidth) {
        TableColumn<AlunoModel, T> coluna = new TableColumn<>(nome);
        coluna.setCellValueFactory(new PropertyValueFactory<>(nome.toLowerCase()));
        coluna.setMinWidth(minWidth);
        coluna.setMaxWidth(maxWidth);
        return coluna;
    }

    private void configurarPropriedadesTableView(TableView<AlunoModel> tabela, TableColumn<AlunoModel, ?> colunaParaOrdenar) {
        tabela.setMaxSize(800, Double.MAX_VALUE);
        tabela.setPrefHeight(1000);
        VBox.setVgrow(tabela, Priority.ALWAYS);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        if (colunaParaOrdenar != null) {
            colunaParaOrdenar.setSortType(TableColumn.SortType.ASCENDING);
            tabela.getSortOrder().add(colunaParaOrdenar);
            tabela.sort();
        }
    }

    private void carregarDadosTableView(TableView<AlunoModel> tabela, TurmaModel turmaSelecionada) {
        ObservableList<AlunoModel> res = FXCollections.observableArrayList();

        if (!AlunoModel.buscarTodosAlunos().isEmpty()) {
            List<AlunoModel> alunos = new ArrayList<>(turmaSelecionada.getAlunosView());
            res.addAll(alunos);
        }

        tabela.setItems(res);
    }

    private List<TableColumn<AlunoModel, ?>> criarColunasPadrao() {
        TableColumn<AlunoModel, Integer> colunaId = criarColunaTableView("Id", 50, 50);
        TableColumn<AlunoModel, String> colunaNome = criarColunaTableView("Nome", 150, 300);
        TableColumn<AlunoModel, String> colunaEmail = criarColunaTableView("Email", 150, Integer.MAX_VALUE);

        return List.of(colunaId, colunaNome, colunaEmail);
    }

    private List<TableColumn<AlunoModel, ?>> criarColunasNotas() {
        List<TableColumn<AlunoModel, ?>> colunas = new ArrayList<>(criarColunasPadrao());

        TableColumn<AlunoModel, Double> colunaP1 = new TableColumn<>("P1");
        colunaP1.setCellValueFactory(cellData -> {
            AlunoModel aluno = cellData.getValue();
            List<NotaModel> notas = NotaModel.buscarPorAlunoTurma(aluno.getId(), turmaSelecionada.getId());
            NotaModel notaP1 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P1)
                    .findFirst()
                    .orElse(null);
            return new SimpleObjectProperty<>(notaP1 != null ? notaP1.getNota() : null);
        });
        colunaP1.setMinWidth(50);
        colunaP1.setMaxWidth(90);

        TableColumn<AlunoModel, Double> colunaP2 = new TableColumn<>("P2");
        colunaP2.setCellValueFactory(cellData -> {
            AlunoModel aluno = cellData.getValue();
            List<NotaModel> notas = NotaModel.buscarPorAlunoTurma(aluno.getId(), turmaSelecionada.getId());
            NotaModel notaP2 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P2)
                    .findFirst()
                    .orElse(null);
            return new SimpleObjectProperty<>(notaP2 != null ? notaP2.getNota() : null);
        });
        colunaP2.setMinWidth(50);
        colunaP2.setMaxWidth(90);

        TableColumn<AlunoModel, Double> colunaP3 = new TableColumn<>("P3");
        colunaP3.setCellValueFactory(cellData -> {
            AlunoModel aluno = cellData.getValue();
            List<NotaModel> notas = NotaModel.buscarPorAlunoTurma(aluno.getId(), turmaSelecionada.getId());
            NotaModel notaP3 = notas.stream()
                    .filter(nota -> nota.getTipo() == TipoNota.P3)
                    .findFirst()
                    .orElse(null);
            return new SimpleObjectProperty<>(notaP3 != null ? notaP3.getNota() : null);
        });
        colunaP3.setMinWidth(50);
        colunaP3.setMaxWidth(90);

        colunas.addAll(List.of(colunaP1, colunaP2, colunaP3));
        return colunas;
    }

    private List<TableColumn<AlunoModel, ?>> criarColunasChamada() {
        List<TableColumn<AlunoModel, ?>> colunas = new ArrayList<>(criarColunasPadrao());

        TableColumn<AlunoModel, Boolean> colunaPresente = new TableColumn<>("Presente");
        colunaPresente.setMinWidth(80);
        colunaPresente.setMaxWidth(120);

        colunaPresente.setCellValueFactory(cellData -> cellData.getValue().presenteProperty());
        colunaPresente.setCellFactory(CheckBoxTableCell.forTableColumn(colunaPresente::getCellObservableValue));

        colunas.add(colunaPresente);

        return colunas;
    }

    private List<TableColumn<AlunoModel, ?>> criarColunasFaltas() {
        List<TableColumn<AlunoModel, ?>> colunas = new ArrayList<>(criarColunasPadrao());

        TableColumn<AlunoModel, Integer> colunaFaltas = new TableColumn<>("Faltas");
        colunaFaltas.setCellValueFactory(cellData -> {
            AlunoModel aluno = cellData.getValue();
            Integer faltas = AlunoModel.contarFaltasAlunoTurma(aluno.getId(), turmaSelecionada.getId());
            return new SimpleObjectProperty<>(faltas);
        });
        colunaFaltas.setMinWidth(50);
        colunaFaltas.setMaxWidth(90);

        colunas.add(colunaFaltas);

        return colunas;
    }

    private void carregarTableView(TableView<AlunoModel> tabela, TurmaModel turmaSelecionada, List<TableColumn<AlunoModel, ?>> colunas) {
        tabela.getColumns().setAll(colunas);
        configurarPropriedadesTableView(tabela, colunas.getFirst());
        carregarDadosTableView(tabela, turmaSelecionada);
        definirAlunoSelecionado(tabela);
    }

    private TableView<AlunoModel> criarTableView(TableView<AlunoModel> tabela, VBox vbGerencia, HBox hbContainer, ComboBox<TurmaModel> cbTurma, List<TableColumn<AlunoModel, ?>> colunas) {
        if (tabela == null) {
            tabela = new TableView<>();
            carregarTableView(tabela, cbTurma.getSelectionModel().getSelectedItem(), colunas);

            int index = vbGerencia.getChildren().indexOf(hbContainer) + 1;
            vbGerencia.getChildren().add(index, tabela);
        }
        return tabela;
    }

    private TableView<AlunoModel> removeTableView(TableView<AlunoModel> tabela, VBox vbGerencia) {
        if (tabela != null) {
            vbGerencia.getChildren().remove(tabela);
        }
        return null;
    }

    private void exibirMensagemTemporaria(VBox vbox ,Label label, String mensagem) {
        vbox.setVisible(true);
        label.setText(mensagem);

        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(event -> {
            label.setText("");
            vbox.setVisible(false);
        });
        pause.play();
    }

    // Esconder todos os painéis
    private void esconderPaineis() {
        gPrincipal.setVisible(false);
        gNotas.setVisible(false);
        gFaltas.setVisible(false);
        gMensagemSucesso.setVisible(false);

        gAlunosTurma.setVisible(false);
        gAtribuirNotas.setVisible(false);

        gRealizarAlterar.setVisible(false);
        gRealizarChamada.setVisible(false);
        gAlterarFaltas.setVisible(false);
        gAlunosFaltas.setVisible(false);

        cbAtribuirNotasTurma = reconstruirComboBox(cbAtribuirNotasTurma, hAtribuirNotasTurma);
        cbTurmaRealizarChamada = reconstruirComboBox(cbTurmaRealizarChamada, hTurmaRealizarChamada);
        cbAtribuirFaltasTurma = reconstruirComboBox(cbAtribuirFaltasTurma, hAtribuirFaltasTurma);

        mAtribuirNotas.setText("");
        mAtribuirNotasAluno.setText("");
        mRealizarChamada.setText("");
        mAlunosFaltas.setText("");
        mAlterarFaltas.setText("");
        mSucesso.setText("");

        checkP1.setSelected(false);
        mostrarP1();
        checkP2.setSelected(false);
        mostrarP2();
        checkP3.setSelected(false);
        mostrarP3();

        dpRealizarChamada.setValue(null);

        if (tAtribuirNotasAlunos != null) {
            tAtribuirNotasAlunos.getSelectionModel().clearSelection();
            tAtribuirNotasAlunos = removeTableView(tAtribuirNotasAlunos, gAlunosTurma);
        }

        if (tRealizarChamada != null) {
            tRealizarChamada.getSelectionModel().clearSelection();
            tRealizarChamada = removeTableView(tRealizarChamada, gRealizarChamada);
        }

        if (tAtribuirFaltasAlunos != null) {
            tAtribuirFaltasAlunos.getSelectionModel().clearSelection();
            tAtribuirFaltasAlunos = removeTableView(tAtribuirFaltasAlunos, gAlunosFaltas);
        }

    }

    // Mostrar *Início*
    @FXML
    private void mostrarInicio() {
        esconderPaineis();
        gPrincipal.setVisible(true);
        turmaSelecionada = null;
        alunoSelecionado = null;
    }

    // Mostrar *Notas*
    @FXML
    private void mostrarNotas() {
        esconderPaineis();
        gNotas.setVisible(true);
        gAlunosTurma.setVisible(true);
        turmaSelecionada = null;
        alunoSelecionado = null;

        cbAtribuirNotasTurma.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                turmaSelecionada = newValue;
                tAtribuirNotasAlunos = removeTableView(tAtribuirNotasAlunos, gAlunosTurma);
                tAtribuirNotasAlunos = criarTableView(tAtribuirNotasAlunos, gAlunosTurma, hAtribuirNotasTurma, cbAtribuirNotasTurma, criarColunasNotas());
            }
        });
    }

    @FXML
    private void mostrarAtribuirNotas() {
        if (alunoSelecionado == null) {
            mAtribuirNotas.setText("Selecione uma turma e um aluno para prosseguir.");
        } else {
            gNotas.setVisible(true);
            gAlunosTurma.setVisible(false);
            gAtribuirNotas.setVisible(true);
            mAtribuirNotasAluno.setText("");

            nomeAlunoNota.setText(alunoSelecionado.getNome());
            configuraSpinners();
        }
    }

    private boolean verificarCamposVaziosAtribuirNotas() {
        return (!checkP1.isSelected() && !checkP2.isSelected() && !checkP3.isSelected())
                || (checkP1.isSelected() && sP1.getValue() == null)
                || (checkP2.isSelected() && sP2.getValue() == null)
                || (checkP3.isSelected() && sP3.getValue() == null);
    }

    @FXML
    private void atribuirNotas() {
        mAtribuirNotasAluno.setText("");

        if (verificarCamposVaziosAtribuirNotas()) {
            mAtribuirNotasAluno.setText("Selecione alguma nota e atribua um valor a ela para prosseguir.");
            return;
        }

        try {
            for (TipoNota tipoNota : TipoNota.values()) {
                Double valorNota = switch (tipoNota) {
                    case P1 -> sP1.getValue();
                    case P2 -> sP2.getValue();
                    case P3 -> sP3.getValue();
                };

                NotaModel nota = alunoSelecionado.getNotas().stream()
                        .filter(n -> n.getTipo() == tipoNota && n.getTurma().equals(turmaSelecionada))
                        .findFirst()
                        .orElse(null);

                if (nota == null) {
                    NotaCriarRequest request = new NotaCriarRequest(valorNota, alunoSelecionado, turmaSelecionada);
                    nota = new NotaModel(request);
                    nota.setTipo(tipoNota);
                    alunoSelecionado.getNotas().add(nota);
                } else {
                    nota.setNota(valorNota);
                }
                NotaModel.salvar(nota);
            }

            esconderPaineis();
            mostrarNotas();

            exibirMensagemTemporaria(gMensagemSucesso,mSucesso,"Notas atribuídas com sucesso!");

        } catch (Exception e) {
            mAtribuirNotasAluno.setText("Erro ao salvar as notas. Tente novamente.");
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarP1() {
        hAtribuirP1.setDisable(!checkP1.isSelected());
    }

    @FXML
    private void mostrarP2() {
        hAtribuirP2.setDisable(!checkP2.isSelected());
    }

    @FXML
    private void mostrarP3() {
        hAtribuirP3.setDisable(!checkP3.isSelected());
    }

    // Mostrar *Faltas*
    @FXML
    private void mostrarFaltas() {
        esconderPaineis();
        gFaltas.setVisible(true);
        gRealizarAlterar.setVisible(true);
        turmaSelecionada = null;
        alunoSelecionado = null;
    }

    @FXML
    private void mostrarRealizarChamada() {
        esconderPaineis();
        gFaltas.setVisible(true);
        gRealizarChamada.setVisible(true);

        cbTurmaRealizarChamada.getSelectionModel().selectedItemProperty().addListener((obsTurma, turmaAtual, turmaNova) -> {
            if (turmaNova != null) {

                turmaSelecionada = turmaNova;
                tRealizarChamada = removeTableView(tRealizarChamada, gRealizarChamada);
                tRealizarChamada = criarTableView(tRealizarChamada, gRealizarChamada, hDataRealizarChamada, cbTurmaRealizarChamada, criarColunasChamada());
                tRealizarChamada.setEditable(true);

                if (dpRealizarChamada.getValue() == null) {
                    tRealizarChamada.setDisable(true);
                }

                dpRealizarChamada.valueProperty().addListener((obsDatePicker, dpValorAntigo, dpValorNovo) -> {
                    if (dpValorNovo != null && tRealizarChamada != null) {
                        resetarValoresChamada();
                        tRealizarChamada.setDisable(false);
                    }
                });
            }
        });
    }

    @FXML
    private void resetarValoresChamada() {
        for (AlunoModel aluno : tRealizarChamada.getItems()) {
            BooleanProperty presenteProperty = aluno.presenteProperty();
            presenteProperty.set(false);
        }
        tRealizarChamada.refresh();
    }

    @FXML
    private void mostrarAlunosFaltas() {
        esconderPaineis();
        gFaltas.setVisible(true);
        gAlunosFaltas.setVisible(true);

        alunoSelecionado = null;
        turmaSelecionada = null;

        cbAtribuirFaltasTurma.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                turmaSelecionada = newValue;
                tAtribuirFaltasAlunos = removeTableView(tAtribuirFaltasAlunos, gAlunosFaltas);
                tAtribuirFaltasAlunos = criarTableView(tAtribuirFaltasAlunos, gAlunosFaltas, hAtribuirFaltasTurma, cbAtribuirFaltasTurma, criarColunasFaltas());
            }
        });
    }

    @FXML
    private void mostrarAlterarFaltas() {
        if (turmaSelecionada != null && alunoSelecionado != null) {
            esconderPaineis();
            gFaltas.setVisible(true);
            gAlterarFaltas.setVisible(true);

            tfTurmaAlterarFaltas.setText(turmaSelecionada.toString());
            tfAlunoAlterarFaltas.setText(alunoSelecionado.getNome());

            sFaltas.setValueFactory(montaSpinnerFaltas(AlunoModel.contarFaltasAlunoTurma(alunoSelecionado.getId(),
                    turmaSelecionada.getId()), turmaSelecionada.getDisciplina().getCargaHoraria()));

        } else {
            mAlunosFaltas.setText("Selecione uma turma e um aluno para prosseguir.");
        }

    }

    @FXML
    private void finalizarChamada() {
        mRealizarChamada.setText("");

        if (cbTurmaRealizarChamada.getSelectionModel().getSelectedItem() == null || dpRealizarChamada.getValue() == null) {
            mRealizarChamada.setText("Selecione uma turma e uma data para realizar a chamada.");
            return;
        }

        TurmaModel turmaSelecionada = cbTurmaRealizarChamada.getSelectionModel().getSelectedItem();
        LocalDate dataChamada = dpRealizarChamada.getValue();

        ChamadaModel chamada = new ChamadaModel();
        chamada.setTurma(turmaSelecionada);
        chamada.setData(dataChamada);

        List<FrequenciaModel> frequencias = new ArrayList<>();
        for (AlunoModel aluno : tRealizarChamada.getItems()) {
            FrequenciaModel frequencia = new FrequenciaModel();
            frequencia.setAluno(aluno);
            frequencia.setStatus(aluno.isPresente());
            frequencia.setChamada(chamada);
            frequencias.add(frequencia);
        }

        chamada.setFrequencias(frequencias);
        ChamadaModel.salvar(chamada);

        esconderPaineis();
        mostrarFaltas();

        exibirMensagemTemporaria(gMensagemSucesso, mSucesso, "Chamada finalizada com sucesso.");
    }

    @FXML
    private void atribuirFaltas() {
        if (sFaltas.getValue() == null) {
            mAlterarFaltas.setText("Atribua um valor a Faltas.");
            return;
        }
        Integer aulasQtd = FrequenciaModel.buscarQuantidade(alunoSelecionado.getId(), turmaSelecionada.getId());
        if(sFaltas.getValue() > aulasQtd){
            mAlterarFaltas.setText("Não pode atribuir mais faltas do que aulas dadas, no momento o máximo de aulas é: " + aulasQtd + ".");
            return;
        }
        List<FrequenciaModel> aulasRecentes = FrequenciaModel.listagemAulas(alunoSelecionado.getId(), turmaSelecionada.getId());

        List<FrequenciaModel> diasPresentes = aulasRecentes.stream().filter(FrequenciaModel::getStatus).toList();
        List<FrequenciaModel> diasAusentes = aulasRecentes.stream().filter(freq -> !freq.getStatus()).toList();

        System.out.println(diasAusentes.size());
        if(sFaltas.getValue() > diasAusentes.size()){
            int forQtd = Math.abs(sFaltas.getValue() - diasAusentes.size());
            for (int i=0; i<forQtd; i++) {
                FrequenciaModel aula = diasPresentes.get(i);
                aula.setStatus(false);
                FrequenciaModel.salvar(aula);
            }
        }else{
            for (int i=diasAusentes.size(); i> sFaltas.getValue(); i--) {
                FrequenciaModel aulaP = diasAusentes.get(i -1);
                aulaP.setStatus(true);
                FrequenciaModel.salvar(aulaP);
            }
        }

        esconderPaineis();
        mostrarFaltas();

        exibirMensagemTemporaria(gMensagemSucesso, mSucesso, "Faltas atualizadas com sucesso.");
    }

    private void definirAlunoSelecionado(TableView<AlunoModel> tabelaAlunos) {
        tabelaAlunos.setOnMouseClicked((MouseEvent) -> alunoSelecionado = tabelaAlunos.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onLogoutClicked(ActionEvent event) {
        LoginView.mostrarLogin();
    }
}
