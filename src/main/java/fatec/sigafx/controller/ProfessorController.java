package fatec.sigafx.controller;

import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.aulas.dto.NotaCriarRequest;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.view.LoginView;
import javafx.beans.property.SimpleBooleanProperty;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static fatec.sigafx.controller.LoginController.usuarioLogado;

public class ProfessorController
{
    @FXML
    private VBox gPrincipal;

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
    public HBox hDataAlunosFaltas;
    public DatePicker dpAlterarFaltas;
    public Label mAtribuirFaltas;
    public Button bConfirmarFaltas;

    public VBox gAlterarFaltas;
    public TextField tfTurmaAlterarFaltas;
    public TextField tfAlunoAlterarFaltas;
    public Spinner<Integer> sFaltas;

    public TableView<AlunoModel> tAtribuirNotasAlunos;
    public TableView<AlunoModel> tRealizarChamada;
    public TableView<AlunoModel> tAtribuirFaltasAlunos;

    @FXML
    public void initialize() {
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
                }else {
                    super.decrement(steps);
                }
            }
        };

        valueFactory.setValue(nota);
        return valueFactory;

    }

    private SpinnerValueFactory<Integer> montaSpinnerFaltas(int max) {
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
                }else {
                    super.decrement(steps);
                }
            }
        };

        valueFactory.setValue(null);
        return valueFactory;

    }

    public void configuraSpinners(){
        sP1.setValueFactory(montaSpinners(alunoSelecionado.getNotaP1()));
        sP2.setValueFactory(montaSpinners(alunoSelecionado.getNotaP2()));
        sP3.setValueFactory(montaSpinners(alunoSelecionado.getNotaP3()));
    }

    private <T> ComboBox<T> reconstruirComboBox(ComboBox<T> comboBox, HBox hboxPai) {
        ComboBox<T> novaComboBox = new ComboBox<>(comboBox.getItems());
        novaComboBox.setMaxSize(Double.MAX_VALUE,30);
        HBox.setHgrow(novaComboBox, Priority.ALWAYS);
        hboxPai.setAlignment(Pos.CENTER);
        novaComboBox.setPromptText(comboBox.getPromptText());

        hboxPai.getChildren().remove(comboBox);
        hboxPai.getChildren().add(novaComboBox);
        return novaComboBox;
    }

    private void carregarComboBoxTurmas(){
        List<TurmaModel> turmas = TurmaModel.buscarTodasTurmas();
        List<TurmaModel> turmasProfLogado = turmas.stream().filter(p -> Objects.equals(p.getProfessor().getEmail(), usuarioLogado.getEmail())).toList();
        ObservableList<TurmaModel> obsTurmas = FXCollections.observableArrayList();
        obsTurmas.addAll(turmasProfLogado);

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
        ObservableList<AlunoModel> alunos = FXCollections.observableArrayList();

        if (!AlunoModel.buscarTodosAlunos().isEmpty()) {
            alunos.addAll(AlunoModel.buscarAlunosNaTurma(turmaSelecionada.getId()));
        }

        tabela.setItems(alunos);
    }

    private List<TableColumn<AlunoModel, ?>> criarColunasPadrao() {
        TableColumn<AlunoModel, Integer> colunaId = criarColunaTableView("Id", 50, 50);
        TableColumn<AlunoModel, String> colunaNome = criarColunaTableView("Nome", 150, 300);
        TableColumn<AlunoModel, String> colunaEmail = criarColunaTableView("Email", 150, Integer.MAX_VALUE);

        return List.of(colunaId, colunaNome, colunaEmail);
    }

    private List<TableColumn<AlunoModel, ?>> criarColunasNotas() {
        List<TableColumn<AlunoModel, ?>> colunas = new ArrayList<>(criarColunasPadrao());

        // Configurar colunas de notas
        TableColumn<AlunoModel, Double> colunaP1 = new TableColumn<>("P1");
        colunaP1.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNotaP1()));
        colunaP1.setMinWidth(50);
        colunaP1.setMaxWidth(90);

        TableColumn<AlunoModel, Double> colunaP2 = new TableColumn<>("P2");
        colunaP2.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNotaP2()));
        colunaP2.setMinWidth(50);
        colunaP2.setMaxWidth(90);

        TableColumn<AlunoModel, Double> colunaP3 = new TableColumn<>("P3");
        colunaP3.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNotaP3()));
        colunaP3.setMinWidth(50);
        colunaP3.setMaxWidth(90);

        colunas.addAll(List.of(colunaP1, colunaP2, colunaP3));
        return colunas;
    }

    private List<TableColumn<AlunoModel, ?>> criarColunasChamada() {
        List<TableColumn<AlunoModel, ?>> colunas = new ArrayList<>(criarColunasPadrao());

        // Criar a coluna com checkboxes
        TableColumn<AlunoModel, Boolean> colunaPresente = new TableColumn<>("Presente");
        colunaPresente.setMinWidth(80);
        colunaPresente.setMaxWidth(120);

        // Configurar a célula com CheckBoxTableCell
        colunaPresente.setCellValueFactory(param -> new SimpleBooleanProperty(false));
        colunaPresente.setCellFactory(CheckBoxTableCell.forTableColumn(param -> {
            return null; // Nenhuma ação extra necessária
        }));

        colunas.add(colunaPresente);

        return colunas;
    }

    private List<TableColumn<AlunoModel, ?>> criarColunasFaltas() {
        List<TableColumn<AlunoModel, ?>> colunas = new ArrayList<>(criarColunasPadrao());

        TableColumn<AlunoModel, Integer> colunaFaltas = criarColunaTableView("Faltas", 80, 120);
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
        mAtribuirFaltas.setText("");

        checkP1.setSelected(false);
        mostrarP1();
        checkP2.setSelected(false);
        mostrarP2();
        checkP3.setSelected(false);
        mostrarP3();

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
        }else {
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

        boolean camposVazios = verificarCamposVaziosAtribuirNotas();
        if (camposVazios) {
            mAtribuirNotasAluno.setText("Selecione alguma nota e atribua um valor a ela para prosseguir.");
            return;
        }

        NotaCriarRequest requestP1;
        requestP1 = new NotaCriarRequest(sP1.getValue(), alunoSelecionado, turmaSelecionada);

        NotaCriarRequest requestP2;
        requestP2 = new NotaCriarRequest(sP2.getValue(), alunoSelecionado, turmaSelecionada);

        NotaCriarRequest requestP3;
        requestP3 = new NotaCriarRequest(sP3.getValue(), alunoSelecionado, turmaSelecionada);


        NotaModel.criarNota(requestP3);

        NotaModel.criarNota(requestP2);

        NotaModel.criarNota(requestP1);
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

        cbTurmaRealizarChamada.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                turmaSelecionada = newValue;
                tRealizarChamada = removeTableView(tRealizarChamada, gRealizarChamada);
                tRealizarChamada = criarTableView(tRealizarChamada, gRealizarChamada, hDataRealizarChamada, cbTurmaRealizarChamada, criarColunasChamada());
            }
        });
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
                tAtribuirFaltasAlunos = criarTableView(tAtribuirFaltasAlunos, gAlunosFaltas, hDataAlunosFaltas, cbAtribuirFaltasTurma, criarColunasFaltas());
            }
        });
    }

    @FXML
    public void mostrarAlterarFaltas() {
        if (alunoSelecionado != null) {
            esconderPaineis();
            gFaltas.setVisible(true);
            gAlterarFaltas.setVisible(true);

            String turma = turmaSelecionada.getCurso() + " || " + turmaSelecionada.getDisciplina().getNome() + " || " + turmaSelecionada.getProfessor().getNome();

            tfTurmaAlterarFaltas.setText(turma);
            tfAlunoAlterarFaltas.setText(alunoSelecionado.getNome());

            sFaltas.setValueFactory(montaSpinnerFaltas(turmaSelecionada.getDisciplina().getCargaHoraria()));

        }else {
            mAtribuirFaltas.setText("Selecione uma turma e um aluno para prosseguir.");
        }

    }

    @FXML
    public void finalizarChamada() {
        //Função a ser acionada ao finalizar chamada
    }

    @FXML
    public void atribuirFaltas() {
        //Função a ser acionada ao alterar Faltas
    }

    private AlunoModel alunoSelecionado;
    private TurmaModel turmaSelecionada;

    private void definirAlunoSelecionado(TableView<AlunoModel> tabelaAlunos) {
        tabelaAlunos.setOnMouseClicked((MouseEvent) -> alunoSelecionado = tabelaAlunos.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();

    }
}
