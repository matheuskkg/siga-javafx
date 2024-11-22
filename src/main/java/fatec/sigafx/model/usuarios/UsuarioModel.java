package fatec.sigafx.model.usuarios;

import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;
import fatec.sigafx.util.UsuariosUtil;
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

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @Transient
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public UsuarioModel() {}

    public UsuarioModel(UsuarioCriarRequest request) {
        this.nome = request.nome();
        this.email = request.email();
        this.senha = request.senha();
        this.tipo = request.tipo();
    }

    public static void criar(UsuarioCriarRequest request) {
        UsuarioModel u = UsuariosUtil.definirTipoUsuario(request);

        usuarioDAO.salvar(u);
    }

    public static void atualizar(UsuarioCriarRequest request, Integer id) {
        UsuarioModel usuarioAntigo = usuarioDAO.buscarPorId(id);

        if (!usuarioAntigo.getTipo().equals(request.tipo())) {
            excluir(usuarioAntigo);
            UsuarioModel usuarioNovo = UsuariosUtil.definirTipoUsuario(request);
            usuarioNovo.setId(id);
            usuarioDAO.salvar(usuarioNovo);
        } else {
            usuarioAntigo.setNome(request.nome());
            usuarioAntigo.setEmail(request.email());
            usuarioAntigo.setSenha(request.senha());
            usuarioDAO.salvar(usuarioAntigo);
        }
    }

    public static void excluir(UsuarioModel request) {
        usuarioDAO.excluir(request);
    }

    public static List<UsuarioModel> buscarTodos() {
        return usuarioDAO.buscarTodos();
    }

    public static UsuarioModel buscarPorEmail(String email) {
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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
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
