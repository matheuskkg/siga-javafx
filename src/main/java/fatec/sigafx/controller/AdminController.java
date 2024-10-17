package fatec.sigafx.controller;

import fatec.sigafx.view.LoginView;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

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

    // Esconder todos os painéis
    private void hideAllPanes() {
        mainContent.setVisible(false);
        gUsuarios.setVisible(false);
        gDisciplinas.setVisible(false);
        gTurmas.setVisible(false);
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
