package fatec.sigafx.model.usuarios;

import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    private String email;

    private String senha;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    @Transient
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public UsuarioModel() {}

    public UsuarioModel(UsuarioCriarRequest request) {
        this.nome = request.nome();
        this.email = request.email();
        this.senha = request.senha();
    }

    public static boolean verificarSenhasCoincidem(String senha, String confirmarSenha) {
        return senha.equals(confirmarSenha) && !confirmarSenha.isEmpty();
    }

    public static boolean verificarEmailValido(String email) {
        return email.matches(EMAIL_REGEX) ^ email.isEmpty();
    }

    public static boolean verificarEmailEmUso(String email) {
        return usuarioDAO.buscarPorEmail(email) != null;
    }

    public static void criarUsuario(UsuarioCriarRequest request, String tipo) {
        //TODO: Utilizar enum p/ definir a role
        UsuarioModel u = switch (tipo) {
            case "Administrador" -> new AdminModel(request);
            case "Professor" -> new ProfessorModel(request);
            case "Aluno" -> new AlunoModel(request);
            default -> null;
        };

        usuarioDAO.salvarUsuario(u);
    }

    public static void atualizarUsuario(UsuarioModel request) {
        usuarioDAO.salvarUsuario(request);
    }

    public static void excluirUsuario(UsuarioModel request) {
        usuarioDAO.excluirUsuario(request);
    }

    public static UsuarioModel buscarUsuarioPorEmail(String email) {
        return usuarioDAO.buscarPorEmail(email);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
