package fatec.sigafx.controller;

import fatec.sigafx.model.usuario.dto.UsuarioLoginRequest;
import fatec.sigafx.service.UsuarioService;
import fatec.sigafx.view.HomeView;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UsuarioController {
    @FXML
    private TextField usuarioNome;

    @FXML
    private PasswordField usuarioSenha;

    @FXML
    private void confirmarLogin() {
        //Controller => UsuarioService => UsuarioDAO

        if (UsuarioService.confirmarLogin(new UsuarioLoginRequest(usuarioNome.getText(), usuarioSenha.getText()))) {
            HomeView.mostrarHome();
        } else {
            System.out.println("deu ruim");
        }
    }
}