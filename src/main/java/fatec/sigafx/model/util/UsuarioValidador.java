package fatec.sigafx.model.util;

import fatec.sigafx.model.usuarios.AdminModel;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.UsuarioModel;
import fatec.sigafx.model.usuarios.dto.UsuarioLoginRequest;

public class UsuarioValidador {

    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;
    private String tipo;

    public UsuarioValidador(String nome, String email, String senha, String confirmarSenha, String tipo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
        this.tipo = tipo;
    }

    public static String login(UsuarioLoginRequest request) {
        UsuarioModel u = UsuarioModel.buscarUsuarioPorEmail(request.email());

        if (u == null || !request.senha().equals(u.getSenha())) {
            return null;
        } else if (u instanceof AdminModel) {
            return "ADMIN";
        } else if (u instanceof AlunoModel) {
            return "ALUNO";
        } else {
            return "PROFESSOR";
        }
    }

    public boolean verificarCamposVazios() {
        System.out.println(tipo);
        return nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty() || tipo.isEmpty();
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
