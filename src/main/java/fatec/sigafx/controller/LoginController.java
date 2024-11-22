package fatec.sigafx.controller;

import fatec.sigafx.model.usuarios.dto.UsuarioLoginRequest;
import fatec.sigafx.model.usuarios.UsuarioModel;
import fatec.sigafx.util.UsuariosUtil;
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

    public static UsuarioModel usuarioLogado = new UsuarioModel();

    @FXML
    private void confirmarLogin() {
        boolean login = UsuariosUtil.login(new UsuarioLoginRequest(usuarioEmail.getText(), usuarioSenha.getText()));

        if (!login) {
            mensagemErroLogin.setVisible(true);
            return;
        }

        usuarioLogado = UsuarioModel.buscarUsuarioPorEmail(usuarioEmail.getText());

        switch (usuarioLogado.getTipo()) {
            case ADMINISTRADOR -> AdminView.mostrarHomeAdmin();
            case ALUNO -> AlunoView.mostrarHomeAluno();
            case PROFESSOR -> ProfessorView.mostrarHomeProf();
        }
    }
}