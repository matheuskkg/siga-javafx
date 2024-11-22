package fatec.sigafx.model.usuarios.dto;

import fatec.sigafx.model.usuarios.TipoUsuario;

public record UsuarioCriarRequest(
        String nome,
        String email,
        String senha,
        TipoUsuario tipo
) {}
