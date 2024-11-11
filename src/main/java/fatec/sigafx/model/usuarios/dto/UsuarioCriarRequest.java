package fatec.sigafx.model.usuarios.dto;

public record UsuarioCriarRequest(
        String nome,
        String email,
        String senha
) {}
