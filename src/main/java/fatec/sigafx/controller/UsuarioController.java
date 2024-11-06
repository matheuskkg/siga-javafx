package fatec.sigafx.controller;

import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.usuario.UsuarioModel;
import fatec.sigafx.view.HomeView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UsuarioController {
    private static final UsuarioDAO dao = new UsuarioDAO();

    @FXML
    private TextField usuarioNome;

    @FXML
    private PasswordField usuarioSenha;

    @FXML
    private Label mensagemErroLogin;

    @FXML
    private void confirmarLogin() {
        UsuarioModel u = dao.buscarPorNome(usuarioNome.getText());
        if (u != null && u.getSenha().equals(usuarioSenha.getText())) {
            HomeView.mostrarHome();
        }
        else {
            //Exibir erro caso o login falhe
            mensagemErroLogin.setVisible(true);
        }

    }
}