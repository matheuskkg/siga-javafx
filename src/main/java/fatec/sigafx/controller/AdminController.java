package fatec.sigafx.controller;

import fatec.sigafx.view.LoginView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class AdminController
{
    @FXML
    private VBox gPrincipal;

    @FXML
    private Label mensagemErroSenhasDiferentes;
    @FXML
    private Label mensagemErroCampos;
    @FXML
    private Label mensagemErroEmail;

    @FXML
    private VBox gUsuarios;
    @FXML
    private VBox gBotaoUsuario;
    @FXML
    private VBox gAdicionarUsuario;
    @FXML
    private TextField nomeAdicionarUsuario;
    @FXML
    private PasswordField senhaAdicionarUsuario;
    @FXML
    private PasswordField confirmarSenhaAdicionarUsuario;
    @FXML
    private TextField emailAdicionarUsuario;
    @FXML
    private ComboBox<String> cbTipoAdicionarUsuario;
    @FXML
    private VBox gAlterarExcluirUsuario;
    @FXML
    public VBox gAlterarUsuario; //Começa aqui
    @FXML
    public TextField nomeAlterarUsuario;
    @FXML
    public PasswordField senhaAlterarUsuario;
    @FXML
    public PasswordField confirmarSenhaAlterarUsuario;
    @FXML
    public Label meAlterarSenhasDiferentes;
    @FXML
    public TextField emailAlterarUsuario;
    @FXML
    public Label meAlterarEmail;
    @FXML
    public HBox hTipoUsuarioAlterar;
    @FXML
    public ComboBox<String> cbTipoAlterarUsuario;
    @FXML
    public Label mErroAlterarCampos;
    @FXML
    public VBox gConfirmaExclusao; //Termina aqui
    @FXML
    private HBox hTipoUsuario;

    @FXML
    private VBox gDisciplinas;
    @FXML
    private VBox gBotaoDisciplinas;
    @FXML
    private VBox gAdicionarDisciplinas;
    @FXML
    private TextField nomeAdicionarDisciplina;
    @FXML
    private VBox gAlterarExcluirDisciplinas;

    @FXML
    private VBox gTurmas;
    @FXML
    private VBox gBotaoTurmas;
    @FXML
    private VBox gAdicionarTurmas;
    @FXML
    private VBox gAlterarExcluirTurmas;
    @FXML
    private VBox gAdicionarRemoverAlunosTurmas;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    private List<String> usuarios = new ArrayList<>();

    public void carregarComboBox(){
        usuarios.add("Administrador");
        usuarios.add("Professor");
        usuarios.add("Aluno");

        ObservableList<String> obsUsuarios = FXCollections.observableArrayList(usuarios);

        cbTipoAdicionarUsuario.setItems(obsUsuarios);
        cbTipoAlterarUsuario.setItems(obsUsuarios);
    }

    // Esconder todos os painéis
    private void hideAllPanes() {
        gPrincipal.setVisible(false);

        gUsuarios.setVisible(false);
        gBotaoUsuario.setVisible(false);
        gAdicionarUsuario.setVisible(false);
        gAlterarExcluirUsuario.setVisible(false);
        gAlterarUsuario.setVisible(false);
        gConfirmaExclusao.setVisible(false);

        gDisciplinas.setVisible(false);
        gBotaoDisciplinas.setVisible(false);
        gAdicionarDisciplinas.setVisible(false);
        gAlterarExcluirDisciplinas.setVisible(false);

        gTurmas.setVisible(false);
        gBotaoTurmas.setVisible(false);
        gAdicionarTurmas.setVisible(false);
        gAdicionarRemoverAlunosTurmas.setVisible(false);
        gAlterarExcluirTurmas.setVisible(false);
    }

    @FXML
    public void initialize() {
        carregarComboBox();
    }
    @FXML
    public void limparCampos(){
        nomeAdicionarUsuario.clear();
        senhaAdicionarUsuario.clear();
        confirmarSenhaAdicionarUsuario.clear();
        emailAdicionarUsuario.clear();

        nomeAlterarUsuario.clear();
        senhaAlterarUsuario.clear();
        confirmarSenhaAlterarUsuario.clear();
        emailAlterarUsuario.clear();


        // Criar uma ComboBox com as mesmas propriedades e itens
        ComboBox<String> novaComboBox = new ComboBox<>(cbTipoAdicionarUsuario.getItems());
        novaComboBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(novaComboBox, Priority.ALWAYS);
        novaComboBox.setPromptText(cbTipoAdicionarUsuario.getPromptText());

        // Substituir a ComboBox original pela nova
        hTipoUsuario.getChildren().remove(cbTipoAdicionarUsuario);
        hTipoUsuario.getChildren().add(novaComboBox);
        cbTipoAdicionarUsuario = novaComboBox;

        // Substituir a ComboBox original pela nova
        hTipoUsuarioAlterar.getChildren().remove(cbTipoAlterarUsuario);
        hTipoUsuarioAlterar.getChildren().add(novaComboBox);
        cbTipoAlterarUsuario = novaComboBox;

        mensagemErroSenhasDiferentes.setText("");
        mensagemErroCampos.setText("");
        mensagemErroEmail.setText("");

        meAlterarSenhasDiferentes.setText("");
        mErroAlterarCampos.setText("");
        meAlterarEmail.setText("");

        nomeAdicionarDisciplina.clear();
    }

    // Mostrar "Início"
    @FXML
    public void mostraInicio() {
        hideAllPanes();
        limparCampos();
        gPrincipal.setVisible(true);
    }

    // Mostrar "Gerenciar Usuários"
    @FXML
    public void mostraGerenciarUsuarios() {
        hideAllPanes();
        limparCampos();
        gUsuarios.setVisible(true);
        gBotaoUsuario.setVisible(true);
    }
    @FXML
    public void mostraAdicionarUsuario() {
        hideAllPanes();
        gUsuarios.setVisible(true);
        gAdicionarUsuario.setVisible(true);
    }
    @FXML
    public void adicionarUsuario() {
        boolean verificar = true;
        if (!senhaAdicionarUsuario.getText().equals(confirmarSenhaAdicionarUsuario.getText()) && !confirmarSenhaAdicionarUsuario.getText().isEmpty()) {
            mensagemErroSenhasDiferentes.setText("Senhas diferentes!");
            verificar = false;
        } else {
            mensagemErroSenhasDiferentes.setText("");
        }
        if (!emailAdicionarUsuario.getText().matches(EMAIL_REGEX) && !emailAdicionarUsuario.getText().isEmpty()) {
            mensagemErroEmail.setText("E-mail inválido!");
            verificar = false;
        } else {
            mensagemErroEmail.setText("");
        }
        if (nomeAdicionarUsuario.getText().isEmpty() ||
                senhaAdicionarUsuario.getText().isEmpty() ||
                confirmarSenhaAdicionarUsuario.getText().isEmpty() ||
                emailAdicionarUsuario.getText().isEmpty() ||
                cbTipoAdicionarUsuario.getSelectionModel().getSelectedItem() == null) {
            mensagemErroCampos.setText("Todos campos devem ser preenchidos!");
            verificar = false;
        } else {
            mensagemErroCampos.setText("");
        }
        if(verificar){
            mensagemErroCampos.setText("Usuário cadastrado com sucesso!");
            System.out.println("Formulario enviado???");

            // Cria uma pausa para o texto voltar a seu estado vazio após 2 segundos
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {mensagemErroCampos.setText("");});
            pause.play();
        }
    }
    @FXML
    public void mostraAlterarExcluirUsuario() {
        hideAllPanes();
        gUsuarios.setVisible(true);
        gAlterarExcluirUsuario.setVisible(true);
    }
    @FXML
    public void mostraAlterarUsuario(){
        // Fazer uma verificação aqui checando se tem algum item da tabela selecionado antes de mostrar a tela de alterar
        hideAllPanes();
        gUsuarios.setVisible(true);
        gAlterarUsuario.setVisible(true);
    }
    @FXML
    public void alterarUsuario() {
        boolean verificar = true;
        if (!senhaAlterarUsuario.getText().equals(confirmarSenhaAlterarUsuario.getText()) && !confirmarSenhaAlterarUsuario.getText().isEmpty()) {
            meAlterarSenhasDiferentes.setText("Senhas diferentes!");
            verificar = false;
        } else {
            meAlterarSenhasDiferentes.setText("");
        }
        if (!emailAlterarUsuario.getText().matches(EMAIL_REGEX) && !emailAlterarUsuario.getText().isEmpty()) {
            meAlterarEmail.setText("E-mail inválido!");
            verificar = false;
        } else {
            meAlterarEmail.setText("");
        }
        if (nomeAlterarUsuario.getText().isEmpty() ||
                senhaAlterarUsuario.getText().isEmpty() ||
                confirmarSenhaAlterarUsuario.getText().isEmpty() ||
                emailAlterarUsuario.getText().isEmpty() ||
                cbTipoAlterarUsuario.getSelectionModel().getSelectedItem() == null) {
            mErroAlterarCampos.setText("Todos campos devem ser preenchidos!");
            verificar = false;
        } else {
            mErroAlterarCampos.setText("");
        }
        if (verificar) {
            mErroAlterarCampos.setText("Usuário alterado com sucesso!");
            System.out.println("Formulario enviado???");

            // Cria uma pausa para o texto voltar a seu estado vazio após 2 segundos
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                mErroAlterarCampos.setText("");
            });
            pause.play();
        }
    }
    @FXML
    public void excluirUsuario(){
        gConfirmaExclusao.setVisible(true);
    }
    @FXML
    public void confirmaExclusao(){
        System.out.println("usuario excluido?");
        gConfirmaExclusao.setVisible(false);
    }
    @FXML
    public void voltaConfirmaExclusao(){
        gConfirmaExclusao.setVisible(false);
    }

    // Mostrar "Gerenciar Disciplinas"
    @FXML
    public void mostraGerenciarDisciplinas() {
        hideAllPanes();
        limparCampos();
        gDisciplinas.setVisible(true);
        gBotaoDisciplinas.setVisible(true);
    }
    @FXML
    public void mostraAdicionarDisciplinas() {
        hideAllPanes();
        gDisciplinas.setVisible(true);
        gAdicionarDisciplinas.setVisible(true);
    }
    @FXML
    public void adicionarDiciplina(){
        System.out.println("diciplina adicionada?");
    }
    public void mostraAlterarExcluirDisciplinas() {
        hideAllPanes();
        gDisciplinas.setVisible(true);
        gAlterarExcluirDisciplinas.setVisible(true);
    }
    public void mostraAlterarDisciplina() {
        System.out.println("diciplina alterada?");
    }
    public void excluirDisciplina(){
        System.out.println("disciplina excluida?");
    }

    // Mostrar "Gerenciar Turmas"
    @FXML
    public void mostraGerenciarTurmas() {
        hideAllPanes();
        limparCampos();
        gTurmas.setVisible(true);
        gBotaoTurmas.setVisible(true);
    }
    public void mostraAdicionarTurmas() {
        hideAllPanes();
        gTurmas.setVisible(true);
        gAdicionarTurmas.setVisible(true);
    }
    public void mostraAlterarExcluirTurmas() {
        hideAllPanes();
        gTurmas.setVisible(true);
        gAlterarExcluirTurmas.setVisible(true);
    }
    public void mostraAdicionarRemoverAlunosTurmas() {
        hideAllPanes();
        gTurmas.setVisible(true);
        gAdicionarRemoverAlunosTurmas.setVisible(true);
    }

    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();
    }
}
