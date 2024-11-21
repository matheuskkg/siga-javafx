package fatec.sigafx.model.usuarios;

import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import jakarta.persistence.*;

import java.util.List;

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

    private enum tipo{
        Administrador,
        Professor,
        Aluno
    }

    @Transient
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public UsuarioModel() {}

    public UsuarioModel(UsuarioCriarRequest request) {
        this.nome = request.nome();
        this.email = request.email();
        this.senha = request.senha();
    }

    public static String definirTipoUsuario(UsuarioModel u) {
        if (u instanceof AdminModel) return "Administrador";
        if (u instanceof AlunoModel) return "Aluno";
        if (u instanceof ProfessorModel) return "Professor";

        return null;
    }

    private static UsuarioModel definirTipoUsuario(UsuarioCriarRequest request, String tipo) {
        //TODO: Utilizar enum p/ definir a role
        return switch (tipo) {
            case "Administrador" -> new AdminModel(request);
            case "Professor" -> new ProfessorModel(request);
            case "Aluno" -> new AlunoModel(request);
            default -> null;
        };
    }

    public static void criarUsuario(UsuarioCriarRequest request, String tipo) {
        UsuarioModel u = definirTipoUsuario(request, tipo);

        usuarioDAO.salvar(u);
    }

    public static void atualizarUsuario(UsuarioModel request) {
        usuarioDAO.salvar(request);
    }

    public static void atualizarUsuario(UsuarioCriarRequest request, String tipo, Integer id) {
        UsuarioModel usuarioAntigo = usuarioDAO.buscarPorId(id);

        if (!definirTipoUsuario(usuarioAntigo).equals(tipo)) {
            excluirUsuario(usuarioAntigo);
            UsuarioModel usuarioNovo = definirTipoUsuario(request, tipo);
            usuarioNovo.setId(id);
            usuarioDAO.salvar(usuarioNovo);
        } else {
            usuarioAntigo.setNome(request.nome());
            usuarioAntigo.setEmail(request.email());
            usuarioAntigo.setSenha(request.senha());
            usuarioDAO.salvar(usuarioAntigo);
        }
    }

    public static void excluirUsuario(UsuarioModel request) {
        usuarioDAO.excluir(request);
    }

    public static List<UsuarioModel> buscarTodosUsuarios() {
        return usuarioDAO.buscarTodos();
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
