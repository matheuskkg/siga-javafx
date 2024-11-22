package fatec.sigafx.util;

import fatec.sigafx.model.usuarios.UsuarioModel;
import fatec.sigafx.model.usuarios.dto.UsuarioLoginRequest;

public class UsuariosUtil {

    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;
    private String tipo;

    public UsuariosUtil(String nome, String email, String senha, String confirmarSenha, String tipo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
        this.tipo = tipo;
    }

    public static UsuarioModel login(UsuarioLoginRequest request) {
        UsuarioModel u = UsuarioModel.buscarUsuarioPorEmail(request.email());

        if (u != null && u.getSenha().equals(request.senha())) {
            return u;
        } else {
            return null;
        }
    }

    public boolean verificarCamposVazios() {
        return nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty() || tipo == null;
    }

    public boolean verificarEmailValido() {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        return email.matches(regex) ^ email.isEmpty();
    }

    public boolean verificarEmailEmUso() {
        return UsuarioModel.buscarUsuarioPorEmail(email) != null;
    }

    public boolean verificarSenhasCoincidem() {
        return senha.equals(confirmarSenha);
    }
}
