package fatec.sigafx.controller;

import fatec.sigafx.view.LoginView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class AlunoController {
    @FXML
    private VBox gPrincipal;

    @FXML
    private VBox gNotas;

    @FXML
    private VBox gFaltas;

    // Esconder todos os painéis
    private void hideAllPanes() {
        gPrincipal.setVisible(false);
        gNotas.setVisible(false);
        gFaltas.setVisible(false);
    }

    // Mostrar "Início"
    @FXML
    public void mostrarInicio() {
        hideAllPanes();
        gPrincipal.setVisible(true);
    }

    // Mostrar "Notas"
    @FXML
    public void mostarNotas() {
        hideAllPanes();
        gNotas.setVisible(true);
    }

    // Mostrar "Início"
    @FXML
    public void mostarFaltas() {
        hideAllPanes();
        gFaltas.setVisible(true);
    }

    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();

    }
}
