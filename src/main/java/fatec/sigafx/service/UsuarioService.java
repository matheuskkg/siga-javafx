package fatec.sigafx.service;

import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.usuario.UsuarioModel;
import fatec.sigafx.model.usuario.dto.UsuarioCriarRequest;
import fatec.sigafx.model.usuario.dto.UsuarioLoginRequest;

public class UsuarioService {
    private static final UsuarioDAO dao = new UsuarioDAO();

    public static boolean confirmarLogin(UsuarioLoginRequest request) {
        //dao.salvarUsuario(new UsuarioCriarRequest("admin", "email@email", "admin"));    //só p criar o admin, remover dps

        UsuarioModel usuario = dao.buscarPorNome(request.nome());

        return usuario != null && usuario.getSenha().equals(request.senha());
    }

}
