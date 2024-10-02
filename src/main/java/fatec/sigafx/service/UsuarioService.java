package fatec.sigafx.service;

import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.usuario.UsuarioModel;
import fatec.sigafx.model.usuario.dto.UsuarioLoginRequest;

public class UsuarioService {
    private static final UsuarioDAO dao = new UsuarioDAO();

    public static boolean confirmarLogin(UsuarioLoginRequest request) {
        UsuarioModel usuario = dao.buscarPorNome(request.nome());

        return usuario != null && usuario.getSenha().equals(request.senha());
    }

}
