package fatec.sigafx;

import fatec.sigafx.model.usuarios.TipoUsuario;
import fatec.sigafx.model.usuarios.UsuarioModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import fatec.sigafx.view.LoginView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class SigaApplication extends Application
{

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        if (UsuarioModel.buscarPorEmail("aluno@aluno") == null) {
            UsuarioModel.criar(new UsuarioCriarRequest("aluno", "aluno@aluno", "aluno", TipoUsuario.ALUNO));
            UsuarioModel.criar(new UsuarioCriarRequest("admin", "admin@admin", "admin", TipoUsuario.ADMINISTRADOR));
            UsuarioModel.criar(new UsuarioCriarRequest("professor", "professor@professor", "professor", TipoUsuario.PROFESSOR));
        }

        SigaApplication.stage = stage;

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/icone.png"))));

        LoginView.mostrarLogin();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args)
    {
        launch();
        EMF.close();
    }
}