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
        carregarComboBoxProfessorResponsavel();
    }

    private void atualizarTableViewUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        tableViewAlterarExcluirUsuario.setItems(usuarioDAO.buscarTodos());
    }

    private void carregarTableViewUsuarios() {
        //TableView alteração e exclusão de usuários
        usuarioId.setCellValueFactory(new PropertyValueFactory<>("id"));
        usuarioNome.setCellValueFactory(new PropertyValueFactory<>("email"));
        usuarioEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        atualizarTableViewUsuarios();
    }

    private void carregarComboBoxTipoUsuario(){
        List<String> usuarios = new ArrayList<>();
        usuarios.add("Administrador");
        usuarios.add("Professor");
        usuarios.add("Aluno");

        ObservableList<String> obsUsuarios = FXCollections.observableArrayList(usuarios);

        cbTipoAdicionarUsuario.setItems(obsUsuarios);
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

    private boolean verificarCamposVaziosAdicionarUsuario() {
        return nomeAdicionarUsuario.getText().isEmpty()
                || senhaAdicionarUsuario.getText().isEmpty()
                || confirmarSenhaAdicionarUsuario.getText().isEmpty()
                || emailAdicionarUsuario.getText().isEmpty()
                || cbTipoAdicionarUsuario.getSelectionModel().getSelectedItem() == null;
    }

    @FXML
    private void adicionarUsuario() {
        boolean verificar = true;

        if (verificarCamposVaziosAdicionarUsuario()) {
            meAdicionarUsuarioErroCampos.setText("Todos campos devem ser preenchidos!");
            verificar = false;
        } else {
            meAdicionarUsuarioErroCampos.setText("");
        }

        if (!UsuarioModel.verificarSenhasCoincidem(senhaAdicionarUsuario.getText(), confirmarSenhaAdicionarUsuario.getText())) {
            meAdicionarUsuarioErroSenhasDiferentes.setText("Senhas diferentes!");
            verificar = false;
        } else {
            meAdicionarUsuarioErroSenhasDiferentes.setText("");
        }

        //TODO: criar mensagem de erro "email em uso"
        if (UsuarioModel.verificarEmailEmUso(emailAdicionarUsuario.getText())) {

            verificar = false;
        } else {

        }

        if (!UsuarioModel.verificarEmailValido(emailAdicionarUsuario.getText())) {
            meAdicionarUsuarioErroEmail.setText("E-mail inválido!");
            verificar = false;
        } else {
            meAdicionarUsuarioErroEmail.setText("");
        }

        if(verificar){
            UsuarioCriarRequest request = new UsuarioCriarRequest(
                    nomeAdicionarUsuario.getText(),
                    emailAdicionarUsuario.getText(),
                    senhaAdicionarUsuario.getText());

            UsuarioModel.criarUsuario(request, cbTipoAdicionarUsuario.getValue());

            limparCampos();
            initialize();

            meAdicionarUsuarioErroCampos.setText("Usuário cadastrado com sucesso!");
            // Cria uma pausa para o texto voltar a seu estado vazio após 2 segundos
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                meAdicionarUsuarioErroCampos.setText("");});
            pause.play();
        }
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
                || cbProfRespon.getSelectionModel().getSelectedItem() == null;
    }

    @FXML
    private void adicionarDisciplina(){
        boolean verificar = true;

        /**
         * TODO:
         *  mensagem erro input invalido ????????
         *  mensagem sucesso ao cadastrar disciplina @Igor
         */

        if (verificarCamposVaziosAdicionarDisciplina()) {
            verificar = false;
            meAdicionarDisciplinas.setText("Todos os campos devem ser preenchidos");
        } else {
            meAdicionarDisciplinas.setText("");
        }

        if (verificar) {
            ProfessorModel professorResponsavel = ProfessorModel.buscarProfessorPorEmail(cbProfRespon.getSelectionModel().getSelectedItem());
            DisciplinaCriarRequest request = new DisciplinaCriarRequest(nomeAdicionarDisciplina.getText(), professorResponsavel);
            DisciplinaModel.criarDisciplina(request);

            limparCampos();

            meAdicionarDisciplinas.setText("Disciplina adicionada com sucesso!");
            // Cria uma pausa para o texto voltar a seu estado vazio após 2 segundos
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                meAdicionarDisciplinas.setText("");});
            pause.play();
        }
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
