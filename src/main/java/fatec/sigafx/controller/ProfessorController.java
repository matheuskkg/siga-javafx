package fatec.sigafx.controller;

import fatec.sigafx.view.LoginView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ProfessorController {

    @FXML
    private VBox mainContent;

    @FXML
    private VBox turma;

    @FXML
    private VBox presenca;

    // Esconder todos os painéis
    private void esconderPaineis() {
        mainContent.setVisible(false);
        turma.setVisible(false);
        presenca.setVisible(false);
    }

    // Mostrar "Início"
    @FXML
    public void mostrarInicio() {
        esconderPaineis();
        mainContent.setVisible(true);
    }

    // Mostrar *Turma*
    @FXML
    public void mostrarTurma() {
        esconderPaineis();
        turma.setVisible(true);
    }

    // Mostrar *Precença*
    @FXML
    public void mostrarPrecenca() {
        esconderPaineis();
        presenca.setVisible(true);
    }

    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();

    }
}
