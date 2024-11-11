package fatec.sigafx.controller;

import fatec.sigafx.model.usuarios.AdminModel;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.ProfessorModel;
import fatec.sigafx.model.usuarios.UsuarioModel;
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
        //TODO: encontrar uma maneira de mover a validação para o model,
        //      é necessário saber a classe do objeto para redirecionar para a home correta
        UsuarioModel u = UsuarioModel.buscarUsuarioPorEmail(usuarioEmail.getText());
        if (u != null && u.getSenha().equals(usuarioSenha.getText())) {
            if (u instanceof AdminModel) {
                AdminView.mostrarHomeAdmin();
            }

            if (u instanceof AlunoModel) {
                AlunoView.mostrarHomeAluno();
            }

            if (u instanceof ProfessorModel) {
                ProfessorView.mostrarHomeProf();
            }
        } else {
            mensagemErroLogin.setVisible(true);
        }
    }
}