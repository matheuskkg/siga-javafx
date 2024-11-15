package fatec.sigafx.controller;

import fatec.sigafx.model.aulas.DisciplinaModel;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.aulas.dto.DisciplinaCriarRequest;
import fatec.sigafx.model.aulas.dto.TurmaCriarRequest;
import fatec.sigafx.model.usuarios.ProfessorModel;
import fatec.sigafx.model.usuarios.UsuarioModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import fatec.sigafx.model.util.UsuarioValidador;
import fatec.sigafx.view.LoginView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.List;

public class AdminController
{
    // Início
    @FXML
    private VBox gPrincipal;

    // Gerenciar Usuários
    @FXML
    private VBox gUsuarios;
    @FXML
    private VBox gBotaoUsuario;
    @FXML
    private VBox gAdicionarAlterarUsuario;
    @FXML
    private Label mAdicionarUsuario;
    @FXML
    private TextField nomeAdicionarUsuario;
    @FXML
    private PasswordField senhaAdicionarUsuario;
    @FXML
    private PasswordField confirmarSenhaAdicionarUsuario;
    @FXML
    private TextField emailAdicionarUsuario;
    @FXML
    private ComboBox<String> cbTipoAdicionarUsuario;
    @FXML
    private HBox hAdicionarTipoUsuario;
    @FXML
    private Label mAdicionarUsuarioErroSenhasDiferentes;
    @FXML
    private Label mAdicionarUsuarioErroCampos;
    @FXML
    private Label mAdicionarUsuarioErroEmail;
    @FXML
    private VBox gAlterarExcluirUsuario;
    @FXML
    private Button botaoAdicionarAlterarUsuario;
    @FXML
    private Button botaoLimparAdicionarAlterarUsuario;
    @FXML
    public VBox gConfirmaExclusao;
    @FXML
    public Label mAlterarExcluirUsuario;

    @FXML
    private TableView<UsuarioModel> tableViewAlterarExcluirUsuario;
    @FXML
    private TableColumn<UsuarioModel, Integer> usuarioId;
    @FXML
    private TableColumn<UsuarioModel, String> usuarioNome;
    @FXML
    private TableColumn<UsuarioModel, String> usuarioEmail;

    // Gerenciar Disciplinas
    @FXML
    private VBox gDisciplinas;
    @FXML
    private VBox gBotaoDisciplinas;
    @FXML
    private VBox gAdicionarDisciplinas;
    @FXML
    private Label mAdicionarDisciplinas;
    @FXML
    private TextField nomeAdicionarDisciplina;
    @FXML
    private ComboBox<Integer> cbCargaAdicionarDisciplina;
    @FXML
    private HBox hAdicionarDisciplinaCarga;
    @FXML
    private VBox gAlterarExcluirDisciplinas;
    @FXML
    public Label mAlterarExcluirDisciplina;
    @FXML
    public Label mAdicionarAlterarDisciplina;
    @FXML
    public Button bAdicionarAlterarDisiciplina;
    @FXML
    public VBox gConfirmaExclusaoDisciplina;
    @FXML
    public Button bLimparDisciplina;

    @FXML
    private TableView<DisciplinaModel> tableViewAlterarExcluirDisciplina;
    @FXML
    private TableColumn<DisciplinaModel, Integer> disciplinaId;
    @FXML
    private TableColumn<DisciplinaModel, String> disciplinaNome;
    @FXML
    private TableColumn<DisciplinaModel, Integer> disciplinaCargaHoraria;

    // Gerenciar Turmas
    @FXML
    private VBox gTurmas;
    @FXML
    private VBox gBotaoTurmas;
    @FXML
    private VBox gAdicionarTurmas;
    @FXML
    private ComboBox<String> cbCursoAdicionarTurma;
    @FXML
    private ComboBox<DisciplinaModel> cbDisciplinaAdicionarTurma;
    @FXML
    private ComboBox<ProfessorModel> cbProfResponAdicionarTurma;
    @FXML
    private Label mTurmas;
    @FXML
    private VBox gAlterarExcluirTurmas;
    @FXML
    private VBox gAdicionarRemoverAlunosTurmas;
    @FXML
    public HBox hCursoAdicionarTurma;
    @FXML
    public HBox hDisciplinaAdicionarTurma;
    @FXML
    public HBox hProfAdicionarTurma;
    @FXML
    public Button bLimparAdicionarTurma;
    @FXML
    public VBox gConfirmaExclusaoTurma;
    @FXML
    public VBox gConfirmaRemoverAluno;
    @FXML
    private ComboBox<TurmaModel> cbTurmaAdicionarRemoverAlunoTurma; //@Juninho arruma um nome melhor p issa combo box ae

    @FXML
    private TableView<TurmaModel> tableViewAlterarExcluirTurma;
    @FXML
    private TableColumn<TurmaModel, Integer> turmaId;
    @FXML
    private TableColumn<TurmaModel, String> turmaCurso;
    @FXML
    private TableColumn<TurmaModel, DisciplinaModel> turmaDisciplina;
    @FXML
    private TableColumn<TurmaModel, ProfessorModel> turmaProfessor;

    @FXML
    public void initialize() {
        carregarTableViewUsuarios();
        carregarComboBoxTipoUsuario();
        definirUsuarioSelecionado();
        definirDisciplinaSelecionada();
        carregarComboBoxCargaHoraria();
        carregarComboBoxProfessorResponsavel();
        carregarTableViewDisciplinas();
        carregarComboBoxDisciplina();
        carregarComboBoxCursos();
        carregarTableViewTurmas();
        carregarComboBoxTurmas();
    }

    private void atualizarTableViewUsuarios() {
        ObservableList<UsuarioModel> usuarios = FXCollections.observableArrayList();
        usuarios.addAll(UsuarioModel.buscarTodosUsuarios());

        tableViewAlterarExcluirUsuario.setItems(usuarios);

        usuarioId.setSortType(TableColumn.SortType.ASCENDING);
        tableViewAlterarExcluirUsuario.getSortOrder().add(usuarioId);
        tableViewAlterarExcluirUsuario.sort();
    }

    private void carregarTableViewUsuarios() {
        usuarioId.setCellValueFactory(new PropertyValueFactory<>("id"));
        usuarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        usuarioEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        atualizarTableViewUsuarios();
    }

    private void carregarComboBoxTipoUsuario(){
        ObservableList<String> obsUsuarios = FXCollections.observableArrayList();
        obsUsuarios.addAll(List.of("Administrador", "Professor", "Aluno"));

        cbTipoAdicionarUsuario.setItems(obsUsuarios);
    }

    private void carregarComboBoxCargaHoraria() {
        ObservableList<Integer> obsCargaHoraria = FXCollections.observableArrayList();
        obsCargaHoraria.addAll(List.of(40, 80));

        cbCargaAdicionarDisciplina.setItems(obsCargaHoraria);
    }

    private void atualizarTableViewDisciplinas() {
        ObservableList<DisciplinaModel> disciplinas = FXCollections.observableArrayList();
        disciplinas.addAll(DisciplinaModel.buscarTodasDisciplinas());

        tableViewAlterarExcluirDisciplina.setItems(disciplinas);

        disciplinaId.setSortType(TableColumn.SortType.ASCENDING);
        tableViewAlterarExcluirDisciplina.getSortOrder().add(disciplinaId);
        tableViewAlterarExcluirDisciplina.sort();
    }

    private void carregarTableViewDisciplinas() {
        disciplinaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        disciplinaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        disciplinaCargaHoraria.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        atualizarTableViewDisciplinas();
    }

    private void carregarComboBoxCursos() {
        ObservableList<String> obsCursos = FXCollections.observableArrayList();
        obsCursos.addAll(List.of(
                "Análise e Desenvolvimento de Sistemas",
                "Agronegócio",
                "Gestão Empresarial",
                "Logística",
                "Recursos Humanos"));

        cbCursoAdicionarTurma.setItems(obsCursos);
    }

    private void carregarComboBoxDisciplina() {
        List<DisciplinaModel> disciplinas = DisciplinaModel.buscarTodasDisciplinas();

        ObservableList<DisciplinaModel> obsDisciplinas = FXCollections.observableArrayList();
        obsDisciplinas.addAll(disciplinas);

        cbDisciplinaAdicionarTurma.setItems(obsDisciplinas);
    }

    private void carregarComboBoxProfessorResponsavel() {
        List<ProfessorModel> professores = ProfessorModel.buscarTodosProfessores();

        ObservableList<ProfessorModel> obsProfessores = FXCollections.observableArrayList();
        obsProfessores.addAll(professores);

        cbProfResponAdicionarTurma.setItems(obsProfessores);
    }

    private void atualizarTableViewTurmas() {
        ObservableList<TurmaModel> turmas = FXCollections.observableArrayList();
        turmas.addAll(TurmaModel.buscarTodasTurmas());

        tableViewAlterarExcluirTurma.setItems(turmas);

        turmaId.setSortType(TableColumn.SortType.ASCENDING);
        tableViewAlterarExcluirTurma.getSortOrder().add(turmaId);
        tableViewAlterarExcluirTurma.sort();
    }

    private void carregarTableViewTurmas() {
        turmaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        turmaCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        turmaDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        turmaProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
        atualizarTableViewTurmas();
    }

    private void carregarComboBoxTurmas() {
        List<TurmaModel> turmas = TurmaModel.buscarTodasTurmas();

        ObservableList<TurmaModel> obsTurmas = FXCollections.observableArrayList();
        obsTurmas.addAll(turmas);

        cbTurmaAdicionarRemoverAlunoTurma.setItems(obsTurmas);
    }

    private <T> ComboBox<T> reconstruirComboBox(ComboBox<T> comboBox, HBox hboxPai) {
        ComboBox<T> novaComboBox = new ComboBox<>(comboBox.getItems());
        novaComboBox.setMaxSize(Double.MAX_VALUE,30);
        HBox.setHgrow(novaComboBox, Priority.ALWAYS);
        HBox.setMargin(novaComboBox, new Insets(10,0,0,0));
        novaComboBox.setPromptText(comboBox.getPromptText());

        hboxPai.getChildren().remove(comboBox);
        hboxPai.getChildren().add(novaComboBox);
        return novaComboBox;
    }

    @FXML
    public void limparCampos(){
        nomeAdicionarUsuario.clear();
        senhaAdicionarUsuario.clear();
        confirmarSenhaAdicionarUsuario.clear();
        emailAdicionarUsuario.clear();

        cbTipoAdicionarUsuario = reconstruirComboBox(cbTipoAdicionarUsuario, hAdicionarTipoUsuario);
        cbCargaAdicionarDisciplina = reconstruirComboBox(cbCargaAdicionarDisciplina, hAdicionarDisciplinaCarga);
        cbCursoAdicionarTurma = reconstruirComboBox(cbCursoAdicionarTurma, hCursoAdicionarTurma);
        cbDisciplinaAdicionarTurma = reconstruirComboBox(cbDisciplinaAdicionarTurma, hDisciplinaAdicionarTurma);
        cbProfResponAdicionarTurma = reconstruirComboBox(cbProfResponAdicionarTurma, hProfAdicionarTurma);

        mAdicionarUsuarioErroSenhasDiferentes.setText("");
        mAdicionarUsuarioErroCampos.setText("");
        mAdicionarUsuarioErroEmail.setText("");

        mAlterarExcluirUsuario.setText("");

        nomeAdicionarDisciplina.clear();
        mAdicionarDisciplinas.setText("");

        mAlterarExcluirDisciplina.setText("");

        tableViewAlterarExcluirUsuario.getSelectionModel().clearSelection();
        usuarioSelecionado = null;

        tableViewAlterarExcluirDisciplina.getSelectionModel().clearSelection();
        disciplinaSelecionada = null;

        cbCursoAdicionarTurma = reconstruirComboBox(cbCursoAdicionarTurma, hCursoAdicionarTurma);
        cbDisciplinaAdicionarTurma = reconstruirComboBox(cbDisciplinaAdicionarTurma, hDisciplinaAdicionarTurma);
        cbProfResponAdicionarTurma = reconstruirComboBox(cbProfResponAdicionarTurma, hProfAdicionarTurma);

        mTurmas.setText("");
    }

    @FXML
    private void mudarTelaGeral(ActionEvent event) {
        esconderPaineis();
        limparCampos();

        String textoBotao = ((Button) event.getSource()).getText();
        switch (textoBotao) {
            case "Início":
                mostrarInicio();
                break;
            case "Gerenciar Usuários":
                mostrarGerenciarUsuarios();
                break;
            case "Gerenciar Disciplinas":
                mostrarGerenciarDisciplinas();
                break;
            case "Gerenciar Turmas":
                mostrarGerenciarTurmas();
                break;
            default:
                break;
        }
    }


    // Início
    private void mostrarInicio() {
        gPrincipal.setVisible(true);
    }


    // Usuários
    private void mostrarGerenciarUsuarios() {
        gUsuarios.setVisible(true);
        gBotaoUsuario.setVisible(true);
    }

    @FXML
    private void mudarTelaUsuarios(ActionEvent event) {
        esconderPaineis();
        limparCampos();

        String textoBotao = ((Button) event.getSource()).getText();
        switch (textoBotao) {
            case "Adicionar":
                mostrarAdicionarUsuario();
                break;
            case "Alterar/Excluir":
                mostrarAlterarExcluirUsuario();
                break;
            default:
                break;
        }
    }

    private void mostrarAdicionarUsuario() {
        gUsuarios.setVisible(true);
        gAdicionarAlterarUsuario.setVisible(true);
        mAdicionarUsuario.setText("Adicionar Usuario");
        botaoAdicionarAlterarUsuario.setText("Adicionar");
        botaoLimparAdicionarAlterarUsuario.setVisible(true);
        // Coloca o foco na label ao iniciar
        mAdicionarUsuario.requestFocus();
    }

    private void exibirMensagem(Label label, String mensagem) {
        label.setText(mensagem);
    }

    private void exibirMensagemTemporaria(Label label, String mensagem) {
        label.setText(mensagem);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            label.setText("");});
        pause.play();
    }

    @FXML
    private void adicionarUsuario() {
        exibirMensagem(mAdicionarUsuarioErroCampos, "");
        exibirMensagem(mAdicionarUsuarioErroEmail, "");
        exibirMensagem(mAdicionarUsuarioErroSenhasDiferentes, "");

        String nome = nomeAdicionarUsuario.getText();
        String email = emailAdicionarUsuario.getText();
        String senha = senhaAdicionarUsuario.getText();
        String confirmarSenha = confirmarSenhaAdicionarUsuario.getText();
        String tipo = cbTipoAdicionarUsuario.getValue();

        UsuarioValidador validador = new UsuarioValidador(nome, email, senha, confirmarSenha, tipo);

        boolean errosCampos = false;

        if (validador.verificarCamposVazios()) {
            exibirMensagem(mAdicionarUsuarioErroCampos, "Todos os campos devem ser preenchidos.");
            errosCampos = true;
        }

        if (!validador.verificarEmailValido()) {
            exibirMensagem(mAdicionarUsuarioErroEmail, "Email inválido.");
            errosCampos = true;
        }

        if (!validador.verificarSenhasCoincidem()) {
            exibirMensagem(mAdicionarUsuarioErroSenhasDiferentes, "Senhas diferentes.");
            errosCampos = true;
        }

        if (errosCampos) return;

        //Primeira condição: Está sendo criado um novo usuário, deve verificar se o e-mail não está em uso
        //Segunda condição: Um usuário está sendo alterado, deve verificar se o e-mail não está em uso apenas caso tenha sido alterado
        //Caso nenhuma das duas condições sejam verdadeiras, não há necessidade de verificar o e-mail
        if (usuarioSelecionado == null || !usuarioSelecionado.getEmail().equals(email)) {
            if (validador.verificarEmailEmUso()) {
                exibirMensagem(mAdicionarUsuarioErroEmail, "Email já cadastrado.");
                return;
            }
        }

        UsuarioCriarRequest request = new UsuarioCriarRequest(
                nomeAdicionarUsuario.getText(),
                emailAdicionarUsuario.getText(),
                senhaAdicionarUsuario.getText());

        if (usuarioSelecionado == null) {
            UsuarioModel.criarUsuario(request, tipo);
        } else {
            UsuarioModel.atualizarUsuario(request, tipo, usuarioSelecionado.getId());
        }

        limparCampos();
        initialize();

        exibirMensagemTemporaria(mAdicionarUsuarioErroCampos, "Usuário salvo com sucesso.");

        usuarioSelecionado = null;
    }

    private void mostrarAlterarExcluirUsuario() {
        gUsuarios.setVisible(true);
        gAlterarExcluirUsuario.setVisible(true);
    }

    private UsuarioModel usuarioSelecionado;

    private void definirUsuarioSelecionado() {
        tableViewAlterarExcluirUsuario.setOnMouseClicked((MouseEvent) -> {
            usuarioSelecionado = tableViewAlterarExcluirUsuario.getSelectionModel().getSelectedItem();
            System.out.println(usuarioSelecionado);
        });
    }

    @FXML
    public void mostrarAlterarUsuario(){
        if (usuarioSelecionado == null) {
            mAlterarExcluirUsuario.setText("Selecione um usuário a ser alterado!");
        } else {
            gUsuarios.setVisible(true);
            gAlterarExcluirUsuario.setVisible(false);
            gAdicionarAlterarUsuario.setVisible(true);
            mAdicionarUsuario.setText("Alterar Usuario");

            botaoAdicionarAlterarUsuario.setText("Alterar");
            botaoLimparAdicionarAlterarUsuario.setVisible(false);

            nomeAdicionarUsuario.setText(usuarioSelecionado.getNome());
            emailAdicionarUsuario.setText(usuarioSelecionado.getEmail());
            senhaAdicionarUsuario.setText(usuarioSelecionado.getSenha());
            confirmarSenhaAdicionarUsuario.setText(usuarioSelecionado.getSenha());
            cbTipoAdicionarUsuario.setValue(UsuarioModel.definirTipoUsuario(usuarioSelecionado));
        }
    }

    @FXML
    public void mostrarExcluirUsuario(){
        if (usuarioSelecionado == null) {
            mAlterarExcluirUsuario.setText("Selecione um usuário a ser excluído!");
        }
        else {
            mAlterarExcluirUsuario.setText("");
            gConfirmaExclusao.setVisible(true);
        }
    }

    @FXML
    public void confirmaExclusao(ActionEvent event){

        String textoBotao = ((Button) event.getSource()).getText();
        switch (textoBotao) {
            case "Sim":
                UsuarioModel.excluirUsuario(usuarioSelecionado);
                initialize();
                break;
            case "Não":
                break;
        }
        gConfirmaExclusao.setVisible(false);
    }


    // Disciplinas
    @FXML
    public void mostrarGerenciarDisciplinas() {
        gDisciplinas.setVisible(true);
        gBotaoDisciplinas.setVisible(true);
    }

    @FXML
    private void mudarTelaDisciplinas(ActionEvent event) {
        esconderPaineis();
        limparCampos();

        String textoBotao = ((Button) event.getSource()).getText();
        switch (textoBotao) {
            case "Adicionar":
                mostrarAdicionarDisciplinas();
                break;
            case "Alterar/Excluir":
                mostrarAlterarExcluirDisciplinas();
                break;
            default:
                break;
        }
    }

    private void mostrarAdicionarDisciplinas() {
        gDisciplinas.setVisible(true);
        gAdicionarDisciplinas.setVisible(true);
        mAdicionarAlterarDisciplina.setText("Adicionar nova Disciplina");
        bAdicionarAlterarDisiciplina.setText("Adicionar");
    }

    private boolean verificarCamposVaziosAdicionarDisciplina() {
        return nomeAdicionarDisciplina.getText().isEmpty()
                || cbCargaAdicionarDisciplina.getSelectionModel().getSelectedItem() == null;
    }

    @FXML
    private void adicionarDisciplina(){
        exibirMensagem(mAdicionarDisciplinas, "");

        boolean camposVazios = verificarCamposVaziosAdicionarDisciplina();

        if (camposVazios) {
            exibirMensagem(mAdicionarDisciplinas, "Todos os campos devem ser preenchidos.");

            return;
        }

        DisciplinaCriarRequest request = new DisciplinaCriarRequest(nomeAdicionarDisciplina.getText(), cbCargaAdicionarDisciplina.getSelectionModel().getSelectedItem());

        if (disciplinaSelecionada == null) {
            DisciplinaModel.criarDisciplina(request);
        } else {
            DisciplinaModel.atualizarDisciplina(request, disciplinaSelecionada.getId());
        }

        limparCampos();
        initialize();

        exibirMensagemTemporaria(mAdicionarDisciplinas, "Disciplina salva com sucesso.");
    }

    private void mostrarAlterarExcluirDisciplinas() {
        gDisciplinas.setVisible(true);
        gAlterarExcluirDisciplinas.setVisible(true);
    }

    private DisciplinaModel disciplinaSelecionada;

    private void definirDisciplinaSelecionada() {
        tableViewAlterarExcluirDisciplina.setOnMouseClicked((MouseEvent) -> {
            disciplinaSelecionada = tableViewAlterarExcluirDisciplina.getSelectionModel().getSelectedItem();
            System.out.println(disciplinaSelecionada);
        });
    }

    @FXML
    private void mostrarAlterarDisciplina() {
        if (disciplinaSelecionada == null) {
            mAlterarExcluirDisciplina.setText("Selecione uma disciplina a ser alterada!");
        } else {
            gDisciplinas.setVisible(true);
            gAlterarExcluirDisciplinas.setVisible(false);
            gAdicionarDisciplinas.setVisible(true);
            mAdicionarAlterarDisciplina.setText("Alterar Disciplina");

            bAdicionarAlterarDisiciplina.setText("Alterar");

            nomeAdicionarDisciplina.setText(disciplinaSelecionada.getNome());
            cbCargaAdicionarDisciplina.setValue(disciplinaSelecionada.getCargaHoraria());
        }
    }

    @FXML
    private void mostrarExcluirDisciplina(){
        if (disciplinaSelecionada == null) {
            mAlterarExcluirDisciplina.setText("Selecione uma disciplina a ser alterada!");
        } else {
            mAlterarExcluirDisciplina.setText("");
            gConfirmaExclusaoDisciplina.setVisible(true);
        }
    }

    @FXML
    private void confirmaExclusaoDisciplina(ActionEvent event) {

        String textoBotao = ((Button) event.getSource()).getText();
        switch (textoBotao) {
            case "Sim":
                DisciplinaModel.excluirDisciplina(disciplinaSelecionada);
                initialize();
                break;
            case "Não":
                break;
        }
        gConfirmaExclusaoDisciplina.setVisible(false);
    }


    // Turmas
    @FXML
    private void mostrarGerenciarTurmas() {
        gTurmas.setVisible(true);
        gBotaoTurmas.setVisible(true);
    }

    @FXML
    private void mudarTelaTurmas(ActionEvent event) {
        esconderPaineis();
        limparCampos();

        String textoBotao = ((Button) event.getSource()).getText();
        switch (textoBotao) {
            case "Adicionar":
                mostrarAdicionarTurmas();
                break;
            case "Alterar/Excluir":
                mostrarAlterarExcluirTurmas();
                break;
            case "Adicionar/Remover Aluno":
                mostraAdicionarRemoverAlunosTurmas();
                break;
            default:
                break;
        }
    }

    private void mostrarAdicionarTurmas() {
        gTurmas.setVisible(true);
        gAdicionarTurmas.setVisible(true);
    }

    private boolean verificarCamposVaziosAdicionarTurma() {
        return cbCursoAdicionarTurma.getSelectionModel().getSelectedItem() == null
                || cbDisciplinaAdicionarTurma.getSelectionModel().getSelectedItem() == null
                || cbProfResponAdicionarTurma.getSelectionModel().getSelectedItem() == null;
    }

    @FXML
    private void adicionarTurma() {
        exibirMensagem(mTurmas, "");

        boolean camposVazios = verificarCamposVaziosAdicionarTurma();

        if (camposVazios) {
            exibirMensagem(mTurmas, "Todos os campos devem ser preenchidos.");

            return;
        }

        TurmaModel.criarTurma(new TurmaCriarRequest(cbCursoAdicionarTurma.getSelectionModel().getSelectedItem(), cbDisciplinaAdicionarTurma.getSelectionModel().getSelectedItem(), cbProfResponAdicionarTurma.getSelectionModel().getSelectedItem()));

        limparCampos();
        initialize();

        exibirMensagemTemporaria(mTurmas, "Turma salva com sucesso.");
    }

    private void mostrarAlterarExcluirTurmas() {
        gTurmas.setVisible(true);
        gAlterarExcluirTurmas.setVisible(true);
    }

    private void mostraAdicionarRemoverAlunosTurmas() {
        gTurmas.setVisible(true);
        gAdicionarRemoverAlunosTurmas.setVisible(true);
    }

    private void esconderPaineis() {
        gPrincipal.setVisible(false);

        gUsuarios.setVisible(false);
        gBotaoUsuario.setVisible(false);
        gAdicionarAlterarUsuario.setVisible(false);
        gAlterarExcluirUsuario.setVisible(false);
        gConfirmaExclusao.setVisible(false);

        gDisciplinas.setVisible(false);
        gBotaoDisciplinas.setVisible(false);
        gAdicionarDisciplinas.setVisible(false);
        gAlterarExcluirDisciplinas.setVisible(false);

        gTurmas.setVisible(false);
        gBotaoTurmas.setVisible(false);
        gAdicionarTurmas.setVisible(false);
        gAdicionarRemoverAlunosTurmas.setVisible(false);
        gAlterarExcluirTurmas.setVisible(false);
    }

    @FXML
    private void onLogoutClicked(ActionEvent event) {
        LoginView.mostrarLogin();
    }
}
