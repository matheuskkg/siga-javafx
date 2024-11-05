package fatec.sigafx.controller;

import fatec.sigafx.view.LoginView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ProfessorController {

    @FXML
    private VBox gPrincipal;

    @FXML
    private VBox gNotas;

    @FXML
    private VBox gFaltas;

    // Esconder todos os painéis
    private void esconderPaineis() {
        gPrincipal.setVisible(false);
        gNotas.setVisible(false);
        gFaltas.setVisible(false);
    }

    // Mostrar "Início"
    @FXML
    public void mostrarInicio() {
        esconderPaineis();
        gPrincipal.setVisible(true);
    }

    // Mostrar *Notas*
    @FXML
    public void mostrarNotas() {
        esconderPaineis();
        gNotas.setVisible(true);
    }

    // Mostrar *Faltas*
    @FXML
    public void mostrarFaltas() {
        esconderPaineis();
        gFaltas.setVisible(true);
    }

    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();

    }
}
