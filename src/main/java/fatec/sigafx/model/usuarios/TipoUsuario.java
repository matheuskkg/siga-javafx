package fatec.sigafx.model.usuarios;

public enum TipoUsuario {
    ADMINISTRADOR("Administrador"),
    PROFESSOR("Professor"),
    ALUNO("Aluno");

    private String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    public static TipoUsuario fromTipo(String tipo) {
        for (TipoUsuario t : TipoUsuario.values()) {
            if (t.getTipoString().equalsIgnoreCase(tipo)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Tipo de usuário inválido: " + tipo);
    }

    public String getTipoString() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}