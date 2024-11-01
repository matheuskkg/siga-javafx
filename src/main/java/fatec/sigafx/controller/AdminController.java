package fatec.sigafx.controller;

import fatec.sigafx.view.LoginView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

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
    private VBox gAlterarExcluirUsuario;
    @FXML
    private TextField nomeUsuario;
    @FXML
    private PasswordField senhaUsuario;
    @FXML
    private PasswordField confirmarSenhaUsuario;
    @FXML
    private TextField emailUsuario;
    @FXML
    private ComboBox<String> cbTipoUsuario;
    @FXML
    private Label mensagemErroUsuarioSenhasDiferentes;
    @FXML
    private Label mensagemErroUsuarioCampos;
    @FXML
    private Label mensagemErroUsuarioEmail;


    @FXML
    private VBox gDisciplinas;
    @FXML
    private VBox gBotaoDisciplinas;
    @FXML
    private VBox gAdicionarDisciplinas;
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

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    private List<String> usuarios = new ArrayList<>();

    public void carregarComboBox(){
        usuarios.add("Administrador");
        usuarios.add("Professor");
        usuarios.add("Aluno");

        ObservableList<String> obsUsuarios = FXCollections.observableArrayList(usuarios);

        cbTipoUsuario.setItems(obsUsuarios);
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

    // Mostrar "Início"
    @FXML
    public void mostraInicio() {
        hideAllPanes();
        gPrincipal.setVisible(true);
    }

    // Mostrar "Gerenciar Usuários"
    @FXML
    public void mostraGerenciarUsuarios() {
        hideAllPanes();
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
        if (!senhaUsuario.getText().equals(confirmarSenhaUsuario.getText())) {
            mensagemErroUsuarioSenhasDiferentes.setText("Senhas diferentes!");
            verificar = false;
        } else {
            mensagemErroUsuarioSenhasDiferentes.setText("");
        }
        if (!emailUsuario.getText().matches(EMAIL_REGEX) && !emailUsuario.getText().isEmpty()) {
            mensagemErroUsuarioEmail.setText("E-mail invalido!");
            verificar = false;
        } else {
            mensagemErroUsuarioEmail.setText("");
        }
        if (nomeUsuario.getText().isEmpty() ||
                senhaUsuario.getText().isEmpty() ||
                confirmarSenhaUsuario.getText().isEmpty() ||
                emailUsuario.getText().isEmpty() ||
                cbTipoUsuario.getSelectionModel().getSelectedItem() == null) {
            mensagemErroUsuarioCampos.setText("Todos campos devem ser preenchidos!");
            verificar = false;
        } else {
            mensagemErroUsuarioCampos.setText("");
        }
        if(verificar){
            System.out.println("Formulario enviado???");
        }
    }
    @FXML
    public void limparCampos(){
        nomeUsuario.clear();
        senhaUsuario.clear();
        confirmarSenhaUsuario.clear();
        emailUsuario.clear();
        cbTipoUsuario.getSelectionModel().clearSelection();
        mensagemErroUsuarioSenhasDiferentes.setText("");
        mensagemErroUsuarioCampos.setText("");
        mensagemErroUsuarioEmail.setText("");
    }
    @FXML
    public void mostraAlterarExcluirUsuario() {
        hideAllPanes();
        gUsuarios.setVisible(true);
        gAlterarExcluirUsuario.setVisible(true);
    }

    // Mostrar "Gerenciar Disciplinas"
    @FXML
    public void mostraGerenciarDisciplinas() {
        hideAllPanes();
        gDisciplinas.setVisible(true);
        gBotaoDisciplinas.setVisible(true);
    }
    public void mostraAdicionarDisciplinas() {
        hideAllPanes();
        gDisciplinas.setVisible(true);
        gAdicionarDisciplinas.setVisible(true);
    }
    public void mostraAlterarExcluirDisciplinas() {
        hideAllPanes();
        gDisciplinas.setVisible(true);
        gAlterarExcluirDisciplinas.setVisible(true);
    }

    // Mostrar "Gerenciar Turmas"
    @FXML
    public void mostraGerenciarTurmas() {
        hideAllPanes();
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
