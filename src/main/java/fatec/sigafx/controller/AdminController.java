package fatec.sigafx.controller;

import fatec.sigafx.view.LoginView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

public class AdminController
{
    @FXML
    private VBox mainContent;

    @FXML
    private VBox gUsuarios;

    @FXML
    private VBox gDisciplinas;

    @FXML
    private VBox gTurmas;

    @FXML
    private ComboBox<String> cbTipoUsuario;

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
        mainContent.setVisible(false);
        gUsuarios.setVisible(false);
        gDisciplinas.setVisible(false);
        gTurmas.setVisible(false);
    }

    @FXML
    public void initialize() {
        carregarComboBox();
    }

    // Mostrar "Início"
    @FXML
    public void showInicio() {
        hideAllPanes();
        mainContent.setVisible(true);
    }

    // Mostrar "Gerenciar Usuários"
    @FXML
    public void showGerenciarUsuarios() {
        hideAllPanes();
        gUsuarios.setVisible(true);
    }

    // Mostrar "Gerenciar Disciplinas"
    @FXML
    public void showGerenciarDisciplinas() {
        hideAllPanes();
        gDisciplinas.setVisible(true);
    }

    // Mostrar "Gerenciar Turmas"
    @FXML
    public void showGerenciarTurmas() {
        hideAllPanes();
        gTurmas.setVisible(true);
    }

    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();
    }
}
