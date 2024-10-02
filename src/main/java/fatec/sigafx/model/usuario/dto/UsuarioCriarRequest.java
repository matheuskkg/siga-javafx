package fatec.sigafx.model.usuario.dto;

public record UsuarioCriarRequest(
        String nome,
        String email,
        String senha
) {}
