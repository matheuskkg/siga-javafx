package fatec.sigafx.controller;

import fatec.sigafx.model.aluno.AlunoModel;
import fatec.sigafx.model.usuario.UsuarioModel;
import fatec.sigafx.model.usuario.dto.UsuarioCriarRequest;
import fatec.sigafx.view.LoginView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

public class AdminController
{
    @FXML
    private VBox gPrincipal;

    @FXML
    private Label mensagemErroSenhasDiferentes;
    @FXML
    private Label mensagemErroCampos;
    @FXML
    private Label mensagemErroEmail;

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
    private VBox gAlterarExcluirUsuario;
    @FXML
    private HBox hTipoUsuario;

    @FXML
    private VBox gDisciplinas;
    @FXML
    private VBox gBotaoDisciplinas;
    @FXML
    private VBox gAdicionarDisciplinas;
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
    private VBox gAlterarExcluirTurmas;
    @FXML
    private VBox gAdicionarRemoverAlunosTurmas;

    private List<String> usuarios = new ArrayList<>();

    public void carregarComboBox(){
        usuarios.add("Administrador");
        usuarios.add("Professor");
        usuarios.add("Aluno");

        ObservableList<String> obsUsuarios = FXCollections.observableArrayList(usuarios);

        cbTipoAdicionarUsuario.setItems(obsUsuarios);
        cbTipoAdicionarUsuario.getSelectionModel();
    }

    // Esconder todos os painéis
    private void hideAllPanes() {
        gPrincipal.setVisible(false);

        gUsuarios.setVisible(false);
        gBotaoUsuario.setVisible(false);
        gAdicionarUsuario.setVisible(false);
        gAlterarExcluirUsuario.setVisible(false);

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
    public void initialize() {
        carregarComboBox();
    }
    @FXML
    public void limparCampos(){
        nomeAdicionarUsuario.clear();
        senhaAdicionarUsuario.clear();
        confirmarSenhaAdicionarUsuario.clear();
        emailAdicionarUsuario.clear();

        // Criar uma ComboBox com as mesmas propriedades e itens
        ComboBox<String> novaComboBox = new ComboBox<>(cbTipoAdicionarUsuario.getItems());
        novaComboBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(novaComboBox, Priority.ALWAYS);
        novaComboBox.setPromptText(cbTipoAdicionarUsuario.getPromptText());

        // Substituir a ComboBox original pela nova
        hTipoUsuario.getChildren().remove(cbTipoAdicionarUsuario);
        hTipoUsuario.getChildren().add(novaComboBox);
        cbTipoAdicionarUsuario = novaComboBox;

        mensagemErroSenhasDiferentes.setText("");
        mensagemErroCampos.setText("");
        mensagemErroEmail.setText("");

        nomeAdicionarDisciplina.clear();
    }

    // Mostrar "Início"
    @FXML
    public void mostraInicio() {
        hideAllPanes();
        limparCampos();
        gPrincipal.setVisible(true);
    }

    // Mostrar "Gerenciar Usuários"
    @FXML
    public void mostraGerenciarUsuarios() {
        hideAllPanes();
        limparCampos();
        gUsuarios.setVisible(true);
        gBotaoUsuario.setVisible(true);
    }
    @FXML
    public void mostraAdicionarUsuario() {
        hideAllPanes();
        gUsuarios.setVisible(true);
        gAdicionarUsuario.setVisible(true);
    }
    @FXML
    public void adicionarUsuario() {
        boolean verificar = true;

        //Verificar campos vazios
        if (nomeAdicionarUsuario.getText().isEmpty() ||
                senhaAdicionarUsuario.getText().isEmpty() ||
                confirmarSenhaAdicionarUsuario.getText().isEmpty() ||
                emailAdicionarUsuario.getText().isEmpty() ||
                cbTipoAdicionarUsuario.getSelectionModel().getSelectedItem() == null) {
            mensagemErroCampos.setText("Todos campos devem ser preenchidos!");
            verificar = false;
        } else {
            mensagemErroCampos.setText("");
        }

        if (!UsuarioModel.verificarSenhasCoincidem(senhaAdicionarUsuario.getText(), confirmarSenhaAdicionarUsuario.getText())) {
            mensagemErroSenhasDiferentes.setText("Senhas diferentes!");
            verificar = false;
        } else {
            mensagemErroSenhasDiferentes.setText("");
        }

        //TODO: criar mensagem de erro "email em uso"
        if (UsuarioModel.verificarEmailEmUso(emailAdicionarUsuario.getText())) {

        } else {

        }

        if (!UsuarioModel.verificarEmailValido(emailAdicionarUsuario.getText())) {
            mensagemErroEmail.setText("E-mail inválido!");
            verificar = false;
        } else {
            mensagemErroEmail.setText("");
        }

        if(verificar){
            UsuarioCriarRequest request = new UsuarioCriarRequest(
                    nomeAdicionarUsuario.getText(),
                    emailAdicionarUsuario.getText(),
                    senhaAdicionarUsuario.getText());

            UsuarioModel.criarUsuario(request, cbTipoAdicionarUsuario.getValue());
        }
    }
    @FXML
    public void mostraAlterarExcluirUsuario() {
        hideAllPanes();
        gUsuarios.setVisible(true);
        gAlterarExcluirUsuario.setVisible(true);
    }
    @FXML
    public void alerarUsuario(){
        System.out.println("usuario alterado?");
    }
    @FXML
    public void excluirUsuario(){
        System.out.println("usuario excluido?");
    }

    // Mostrar "Gerenciar Disciplinas"
    @FXML
    public void mostraGerenciarDisciplinas() {
        hideAllPanes();
        limparCampos();
        gDisciplinas.setVisible(true);
        gBotaoDisciplinas.setVisible(true);
    }
    @FXML
    public void mostraAdicionarDisciplinas() {
        hideAllPanes();
        gDisciplinas.setVisible(true);
        gAdicionarDisciplinas.setVisible(true);
    }
    @FXML
    public void adicionarDiciplina(){
        System.out.println("diciplina adicionada?");
    }
    public void mostraAlterarExcluirDisciplinas() {
        hideAllPanes();
        gDisciplinas.setVisible(true);
        gAlterarExcluirDisciplinas.setVisible(true);
    }
    public void mostraAlterarDisciplina() {
        System.out.println("diciplina alterada?");
    }
    public void excluirDisciplina(){
        System.out.println("disciplina excluida?");
    }

    // Mostrar "Gerenciar Turmas"
    @FXML
    public void mostraGerenciarTurmas() {
        hideAllPanes();
        limparCampos();
        gTurmas.setVisible(true);
        gBotaoTurmas.setVisible(true);
    }
    public void mostraAdicionarTurmas() {
        hideAllPanes();
        gTurmas.setVisible(true);
        gAdicionarTurmas.setVisible(true);
    }
    public void mostraAlterarExcluirTurmas() {
        hideAllPanes();
        gTurmas.setVisible(true);
        gAlterarExcluirTurmas.setVisible(true);
    }
    public void mostraAdicionarRemoverAlunosTurmas() {
        hideAllPanes();
        gTurmas.setVisible(true);
        gAdicionarRemoverAlunosTurmas.setVisible(true);
    }

    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();
    }
}
