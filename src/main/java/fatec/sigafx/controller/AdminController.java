package fatec.sigafx.controller;

import fatec.sigafx.dao.ProfessorDAO;
import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.aulas.DisciplinaModel;
import fatec.sigafx.model.aulas.dto.DisciplinaCriarRequest;
import fatec.sigafx.model.usuarios.ProfessorModel;
import fatec.sigafx.model.usuarios.UsuarioModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdminController
{
    @FXML
    private VBox gPrincipal;

    @FXML
    private VBox gUsuarios;
    @FXML
    private VBox gBotaoUsuario;
    @FXML
    private VBox gAdicionarUsuario;
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
    private HBox hTipoUsuario;
    @FXML
    private Label meAdicionarUsuarioErroSenhasDiferentes;
    @FXML
    private Label meAdicionarUsuarioErroCampos;
    @FXML
    private Label meAdicionarUsuarioErroEmail;

    @FXML
    private VBox gAlterarExcluirUsuario;
    @FXML
    public VBox gConfirmaExclusao;

    @FXML
    private TableView<UsuarioModel> tableViewAlterarExcluirUsuario;
    @FXML
    private TableColumn<UsuarioModel, Integer> usuarioId;
    @FXML
    private TableColumn<UsuarioModel, String> usuarioNome;
    @FXML
    private TableColumn<UsuarioModel, String> usuarioEmail;

    @FXML
    private VBox gDisciplinas;
    @FXML
    private VBox gBotaoDisciplinas;
    @FXML
    private VBox gAdicionarDisciplinas;
    @FXML
    private Label meAdicionarDisciplinas;
    @FXML
    private TextField nomeAdicionarDisciplina;
    @FXML
    private ComboBox<Integer> cbCargaAdicionarDisciplina;
    @FXML
    private HBox hDisciplinaCarga;
    @FXML
    private VBox gAlterarExcluirDisciplinas;

    @FXML
    private VBox gTurmas;
    @FXML
    private VBox gBotaoTurmas;
    @FXML
    private VBox gAdicionarTurmas;
    @FXML
    private ComboBox<String> cbProfRespon;
    @FXML
    private Label meTurmas;
    @FXML
    private VBox gAlterarExcluirTurmas;
    @FXML
    private VBox gAdicionarRemoverAlunosTurmas;
    @FXML
    private ComboBox<String> cbCursoAdicionarTurma;

    @FXML
    public void initialize() {
        carregarTableViewUsuarios();
        carregarComboBoxTipoUsuario();
        definirUsuarioSelecionado();
        carregarComboBoxCargaHoraria();
        carregarComboBoxProfessorResponsavel();
    }

    private void atualizarTableViewUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        tableViewAlterarExcluirUsuario.setItems(usuarioDAO.buscarTodos());
    }

    private void carregarTableViewUsuarios() {
        //TableView alteração e exclusão de usuários
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

    private void carregarComboBoxProfessorResponsavel() {
        ProfessorDAO professorDAO = new ProfessorDAO();

        List<ProfessorModel> professores = professorDAO.buscarTodosProfessores();

        ObservableList<String> obsProfessores = FXCollections.observableArrayList();

        for (ProfessorModel p : professores) {
            obsProfessores.add(p.getEmail());
        }

        cbProfRespon.setItems(obsProfessores);
    }

    @FXML
    public void limparCampos(){
        nomeAdicionarUsuario.clear();
        senhaAdicionarUsuario.clear();
        confirmarSenhaAdicionarUsuario.clear();
        emailAdicionarUsuario.clear();

        // Criar uma ComboBox com as mesmas propriedades e itens
        ComboBox<String> novaAdicionarCB = new ComboBox<>(cbTipoAdicionarUsuario.getItems());
        novaAdicionarCB.setMaxSize(Double.MAX_VALUE,30);
        HBox.setHgrow(novaAdicionarCB, Priority.ALWAYS);
        HBox.setMargin(novaAdicionarCB, new Insets(10,0,0,0));
        novaAdicionarCB.setPromptText(cbTipoAdicionarUsuario.getPromptText());
        // Substituir a ComboBox original pela nova
        hTipoUsuario.getChildren().remove(cbTipoAdicionarUsuario);
        hTipoUsuario.getChildren().add(novaAdicionarCB);
        cbTipoAdicionarUsuario = novaAdicionarCB;

        ComboBox<Integer> novaAdicionarCargaCB = new ComboBox<>(cbCargaAdicionarDisciplina.getItems());
        novaAdicionarCargaCB.setMaxSize(Double.MAX_VALUE,30);
        HBox.setHgrow(novaAdicionarCargaCB, Priority.ALWAYS);
        HBox.setMargin(novaAdicionarCargaCB, new Insets(10,0,0,0));
        novaAdicionarCargaCB.setPromptText(cbCargaAdicionarDisciplina.getPromptText());
        hDisciplinaCarga.getChildren().remove(cbCargaAdicionarDisciplina);
        hDisciplinaCarga.getChildren().add(novaAdicionarCargaCB);
        cbCargaAdicionarDisciplina = novaAdicionarCargaCB;

        meAdicionarUsuarioErroSenhasDiferentes.setText("");
        meAdicionarUsuarioErroCampos.setText("");
        meAdicionarUsuarioErroEmail.setText("");

        nomeAdicionarDisciplina.clear();
        meAdicionarDisciplinas.setText("");
    }

    // Mostrar "Início"
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

    private void mostrarInicio() {
        gPrincipal.setVisible(true);
    }

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
        gAdicionarUsuario.setVisible(true);
    }

    private void exibirMensagem(Label label, String mensagem) {
        label.setText(mensagem);
    }

    private void exibirMensagemTemporaria(Label label, String mensagem) {
        label.setText(mensagem);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            meAdicionarUsuarioErroCampos.setText("");});
        pause.play();
    }

    private boolean verificarCamposVaziosAdicionarUsuario() {
        return nomeAdicionarUsuario.getText().isEmpty()
                || senhaAdicionarUsuario.getText().isEmpty()
                || confirmarSenhaAdicionarUsuario.getText().isEmpty()
                || emailAdicionarUsuario.getText().isEmpty()
                || cbTipoAdicionarUsuario.getSelectionModel().getSelectedItem() == null;
    }

    private boolean verificarEmailValido() {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        return emailAdicionarUsuario.getText().matches(regex) ^ emailAdicionarUsuario.getText().isEmpty();
    }

    private boolean verificarSenhasCoincidem() {
        return senhaAdicionarUsuario.getText().equals(confirmarSenhaAdicionarUsuario.getText());
    }

    @FXML
    private void adicionarUsuario() {
        exibirMensagem(meAdicionarUsuarioErroCampos, "");
        exibirMensagem(meAdicionarUsuarioErroEmail, "");
        exibirMensagem(meAdicionarUsuarioErroSenhasDiferentes, "");

        boolean camposVazios = verificarCamposVaziosAdicionarUsuario();
        boolean emailValido = verificarEmailValido();
        boolean emailEmUso = UsuarioModel.verificarEmailEmUso(emailAdicionarUsuario.getText());
        boolean senhasCoincidem = verificarSenhasCoincidem();

        if (camposVazios || !emailValido || emailEmUso || !senhasCoincidem) {
            if (camposVazios) exibirMensagem(meAdicionarUsuarioErroCampos, "Todos os campos devem ser preenchidos.");
            if (!emailValido) exibirMensagem(meAdicionarUsuarioErroEmail, "Email inválido.");
            if (emailEmUso) exibirMensagem(meAdicionarUsuarioErroEmail, "Email já cadastrado.");
            if (!senhasCoincidem) exibirMensagem(meAdicionarUsuarioErroSenhasDiferentes, "Senhas diferentes.");

            return;
        }

        UsuarioCriarRequest request = new UsuarioCriarRequest(
                nomeAdicionarUsuario.getText(),
                emailAdicionarUsuario.getText(),
                senhaAdicionarUsuario.getText());

        UsuarioModel.criarUsuario(request, cbTipoAdicionarUsuario.getValue());

        limparCampos();
        initialize();

        exibirMensagemTemporaria(meAdicionarUsuarioErroCampos, "Usuário salvo com sucesso.");
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

    private UsuarioModel getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    @FXML
    public void mostrarAlterarUsuario(){
        esconderPaineis();
        gUsuarios.setVisible(true);
    }

    @FXML
    public void mostrarExcluirUsuario(){
        gConfirmaExclusao.setVisible(true);
    }
    @FXML
    public void confirmaExclusao(){
        UsuarioModel.excluirUsuario(getUsuarioSelecionado());
        atualizarTableViewUsuarios();
        System.out.println("usuario excluido?");
        gConfirmaExclusao.setVisible(false);
    }
    @FXML
    public void voltaConfirmaExclusao(){
        gConfirmaExclusao.setVisible(false);
    }

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
    }

    private boolean verificarCamposVaziosAdicionarDisciplina() {
        return nomeAdicionarDisciplina.getText().isEmpty()
                || cbCargaAdicionarDisciplina.getSelectionModel().getSelectedItem() == null;
    }

    @FXML
    private void adicionarDisciplina(){
        exibirMensagem(meAdicionarDisciplinas, "");

        boolean camposVazios = verificarCamposVaziosAdicionarDisciplina();

        if (camposVazios) {
            exibirMensagem(meAdicionarDisciplinas, "Todos os campos devem ser preenchidos.");
        }

        DisciplinaModel.criarDisciplina(new DisciplinaCriarRequest(nomeAdicionarDisciplina.getText(), cbCargaAdicionarDisciplina.getSelectionModel().getSelectedItem()));

        limparCampos();
        initialize();

        exibirMensagemTemporaria(meAdicionarDisciplinas, "Disciplina salva com sucesso.");
    }

    private void mostrarAlterarExcluirDisciplinas() {
        gDisciplinas.setVisible(true);
        gAlterarExcluirDisciplinas.setVisible(true);
    }

    @FXML
    private void alterarDisciplina() {
        System.out.println("diciplina alterada?");
    }

    @FXML
    private void excluirDisciplina(){
        System.out.println("disciplina excluida?");
    }

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

    @FXML
    private void adicionarTurma() {
        System.out.println("oi");
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
        gAdicionarUsuario.setVisible(false);
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
