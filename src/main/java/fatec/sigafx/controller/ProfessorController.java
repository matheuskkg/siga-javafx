package fatec.sigafx.controller;

import fatec.sigafx.model.aulas.ChamadaModel;
import fatec.sigafx.model.aulas.FrequenciaModel;
import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.aulas.dto.NotaCriarRequest;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.ProfessorModel;
import fatec.sigafx.view.LoginView;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static fatec.sigafx.controller.LoginController.usuarioLogado;

public class ProfessorController {
    @FXML
    private VBox gPrincipal;
    public Label lBoasVindas;

    @FXML
    private VBox gNotas;
    public VBox gAlunosTurma;
    public HBox hAtribuirNotasTurma;
    public ComboBox<TurmaModel> cbAtribuirNotasTurma;
    public Label mAtribuirNotas;
    public Button bConfirmarAluno;

    public VBox gAtribuirNotas;
    public TextField nomeAlunoNota;
    public CheckBox checkP1;
    public HBox hAtribuirP1;
    public Spinner<Double> sP1;
    public CheckBox checkP2;
    public HBox hAtribuirP2;
    public Spinner<Double> sP2;
    public CheckBox checkP3;
    public HBox hAtribuirP3;
    public Spinner<Double> sP3;
    public Label mAtribuirNotasAluno;
    public Button bAtribuirNotas;

    @FXML
    private VBox gFaltas;
    public VBox gRealizarAlterar;

    public VBox gRealizarChamada;
    public HBox hTurmaRealizarChamada;
    public ComboBox<TurmaModel> cbTurmaRealizarChamada;
    public HBox hDataRealizarChamada;
    public DatePicker dpRealizarChamada;
    public Label mRealizarChamada;
    public Button bFinalizarChamada;

    public VBox gAlunosFaltas;
    public HBox hAtribuirFaltasTurma;
    public ComboBox<TurmaModel> cbAtribuirFaltasTurma;
    public Label mAlunosFaltas;
    public Button bConfirmarFaltas;

    public VBox gAlterarFaltas;
    public TextField tfTurmaAlterarFaltas;
    public TextField tfAlunoAlterarFaltas;
    public Spinner<Integer> sFaltas;
    public Label mAlterarFaltas;

    public TableView<AlunoModel> tAtribuirNotasAlunos;
    public TableView<AlunoModel> tRealizarChamada;
    public TableView<AlunoModel> tAtribuirFaltasAlunos;

    private TurmaModel turmaSelecionada;
    private AlunoModel alunoSelecionado;

    @FXML
    public void initialize() {
        lBoasVindas.setText("Bem vindo(a), " + usuarioLogado.getNome() + "!");
        carregarComboBoxTurmas();
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

    public void configuraSpinners() {
        sP1.setValueFactory(montaSpinners(alunoSelecionado.getNotaP1()));
        sP2.setValueFactory(montaSpinners(alunoSelecionado.getNotaP2()));
        sP3.setValueFactory(montaSpinners(alunoSelecionado.getNotaP3()));
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
            List<NotaModel> notas = NotaModel.buscarNotasPorAlunoETurma(aluno.getId(), turmaSelecionada.getId());
            return new SimpleObjectProperty<>(notas != null && notas.size() > 0 ? notas.get(0).getNota() : null);
        });
        colunaP1.setMinWidth(50);
        colunaP1.setMaxWidth(90);

        TableColumn<AlunoModel, Double> colunaP2 = new TableColumn<>("P2");
        colunaP2.setCellValueFactory(cellData -> {
            AlunoModel aluno = cellData.getValue();
            List<NotaModel> notas = NotaModel.buscarNotasPorAlunoETurma(aluno.getId(), turmaSelecionada.getId());
            return new SimpleObjectProperty<>(notas != null && notas.size() > 1 ? notas.get(1).getNota() : null);
        });
        colunaP2.setMinWidth(50);
        colunaP2.setMaxWidth(90);

        TableColumn<AlunoModel, Double> colunaP3 = new TableColumn<>("P3");
        colunaP3.setCellValueFactory(cellData -> {
            AlunoModel aluno = cellData.getValue();
            List<NotaModel> notas = NotaModel.buscarNotasPorAlunoETurma(aluno.getId(), turmaSelecionada.getId());
            return new SimpleObjectProperty<>(notas != null && notas.size() > 2 ? notas.get(2).getNota() : null);
        });
        colunaP3.setMinWidth(50);
        colunaP3.setMaxWidth(90);

        colunas.addAll(List.of(colunaP1, colunaP2, colunaP3));
        return colunas;
    }

    private List<TableColumn<AlunoModel, ?>> criarColunasChamada() {
        List<TableColumn<AlunoModel, ?>> colunas = new ArrayList<>(criarColunasPadrao());

        // Criar coluna de presença com checkboxes
        TableColumn<AlunoModel, Boolean> colunaPresente = new TableColumn<>("Presente");
        colunaPresente.setMinWidth(80);
        colunaPresente.setMaxWidth(120);

        // Vincular a propriedade transiente
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

    // Esconder todos os painéis
    private void esconderPaineis() {
        gPrincipal.setVisible(false);
        gNotas.setVisible(false);
        gFaltas.setVisible(false);

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
    public void mostrarInicio() {
        esconderPaineis();
        gPrincipal.setVisible(true);
        turmaSelecionada = null;
        alunoSelecionado = null;
    }

    // Mostrar *Notas*
    @FXML
    public void mostrarNotas() {
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
    public void mostrarAtribuirNotas() {
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
    public void atribuirNotas() {
        mAtribuirNotasAluno.setText("");

        // Verifica se os campos estão vazios
        if (verificarCamposVaziosAtribuirNotas()) {
            mAtribuirNotasAluno.setText("Selecione alguma nota e atribua um valor a ela para prosseguir.");
            return;
        }

        // Atribuir ou criar e salvar as notas (P1, P2, P3)
        try {
            NotaModel notaP1;
            try {
                notaP1 = alunoSelecionado.getNotas().get(0);
                notaP1.setNota(sP1.getValue()); // Atualiza valor da nota
            } catch (IndexOutOfBoundsException e) {
                NotaCriarRequest requestP1 = new NotaCriarRequest(sP1.getValue(), alunoSelecionado, turmaSelecionada);
                notaP1 = new NotaModel(requestP1); // Cria nova nota
                alunoSelecionado.getNotas().add(notaP1);
            }
            NotaModel.salvarNota(notaP1); // Persiste a nota P1

            NotaModel notaP2;
            try {
                notaP2 = alunoSelecionado.getNotas().get(1);
                notaP2.setNota(sP2.getValue());
            } catch (IndexOutOfBoundsException e) {
                NotaCriarRequest requestP2 = new NotaCriarRequest(sP2.getValue(), alunoSelecionado, turmaSelecionada);
                notaP2 = new NotaModel(requestP2);
                alunoSelecionado.getNotas().add(notaP2);
            }
            NotaModel.salvarNota(notaP2); // Persiste a nota P2

            NotaModel notaP3;
            try {
                notaP3 = alunoSelecionado.getNotas().get(2);
                notaP3.setNota(sP3.getValue());
            } catch (IndexOutOfBoundsException e) {
                NotaCriarRequest requestP3 = new NotaCriarRequest(sP3.getValue(), alunoSelecionado, turmaSelecionada);
                notaP3 = new NotaModel(requestP3);
                alunoSelecionado.getNotas().add(notaP3);
            }
            NotaModel.salvarNota(notaP3); // Persiste a nota P3

            // Feedback ao usuário
            mAtribuirNotasAluno.setText("Notas atribuídas com sucesso!");

        } catch (Exception e) {
            mAtribuirNotasAluno.setText("Erro ao salvar as notas. Tente novamente.");
            e.printStackTrace();
        }
    }

    //Da pra deixar mais funcional e fazer só uma função, mas tô com preguiça
    @FXML
    public void mostrarP1() {
        hAtribuirP1.setDisable(!checkP1.isSelected());
    }

    @FXML
    public void mostrarP2() {
        hAtribuirP2.setDisable(!checkP2.isSelected());
    }

    @FXML
    public void mostrarP3() {
        hAtribuirP3.setDisable(!checkP3.isSelected());
    }

    // Mostrar *Faltas*
    @FXML
    public void mostrarFaltas() {
        esconderPaineis();
        gFaltas.setVisible(true);
        gRealizarAlterar.setVisible(true);
        turmaSelecionada = null;
        alunoSelecionado = null;
    }

    @FXML
    public void mostrarRealizarChamada() {
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

    //Acho que tem algo errado em usar isso aqui, mas vou deixar por enquanto
    @FXML
    public void resetarValoresChamada() {
        // Percorre todos os itens da tabela
        for (AlunoModel aluno : tRealizarChamada.getItems()) {
            // Obtém a propriedade associada à coluna "Presente"
            BooleanProperty presenteProperty = aluno.presenteProperty();

            // Reseta o valor para `false` (ou outro valor padrão)
            presenteProperty.set(false);
        }

        // Atualiza a tabela visualmente
        tRealizarChamada.refresh();
    }

    @FXML
    public void mostrarAlunosFaltas() {
        esconderPaineis();
        gFaltas.setVisible(true);
        gAlunosFaltas.setVisible(true);

        cbAtribuirFaltasTurma.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                turmaSelecionada = newValue;
                tAtribuirFaltasAlunos = removeTableView(tAtribuirFaltasAlunos, gAlunosFaltas);
                tAtribuirFaltasAlunos = criarTableView(tAtribuirFaltasAlunos, gAlunosFaltas, hAtribuirFaltasTurma, cbAtribuirFaltasTurma, criarColunasFaltas());
            }
        });
    }

    @FXML
    public void mostrarAlterarFaltas() {
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
    public void finalizarChamada() {
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
    }

    @FXML
    public void atribuirFaltas() {
        // Validar a seleção de um aluno e turma
        if (sFaltas.getValue() == null) {
            mAlterarFaltas.setText("Atribua um valor a Faltas.");
            return;
        }

        mAlterarFaltas.setText("Faltas atualizadas com sucesso.");
    }


    private void definirAlunoSelecionado(TableView<AlunoModel> tabelaAlunos) {
        tabelaAlunos.setOnMouseClicked((MouseEvent) -> alunoSelecionado = tabelaAlunos.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();

    }
}
