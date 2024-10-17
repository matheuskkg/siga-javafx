package fatec.sigafx.controller;

import fatec.sigafx.view.LoginView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AlunoController {
    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();

    }
}
