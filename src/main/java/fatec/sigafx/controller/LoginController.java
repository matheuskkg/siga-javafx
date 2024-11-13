package fatec.sigafx.controller;

import fatec.sigafx.model.usuarios.dto.UsuarioLoginRequest;
import fatec.sigafx.model.util.UsuarioValidador;
import fatec.sigafx.view.AdminView;
import fatec.sigafx.view.AlunoView;
import fatec.sigafx.view.ProfessorView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usuarioEmail;

    @FXML
    private PasswordField usuarioSenha;

    @FXML
    private Label mensagemErroLogin;

    @FXML
    private void confirmarLogin() {
        String login = UsuarioValidador.login(new UsuarioLoginRequest(usuarioEmail.getText(), usuarioSenha.getText()));

        if (login == null) {
            mensagemErroLogin.setVisible(true);
            return;
        }

        if (login.equals("ADMIN")) {
            AdminView.mostrarHomeAdmin();
        }

        if (login.equals("ALUNO")) {
            AlunoView.mostrarHomeAluno();
        }

        if (login.equals("PROFESSOR")) {
            ProfessorView.mostrarHomeProf();
        }
    }
}