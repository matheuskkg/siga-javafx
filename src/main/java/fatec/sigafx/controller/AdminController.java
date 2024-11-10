package fatec.sigafx.controller;

import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.usuario.UsuarioModel;
import fatec.sigafx.model.usuario.dto.UsuarioCriarRequest;
import fatec.sigafx.view.LoginView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private HBox hTipoUsuario;

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
    private TableView<UsuarioModel> tableViewAlterarExcluirUsuario;
    @FXML
    private TableColumn<UsuarioModel, Integer> usuarioId;
    @FXML
    private TableColumn<UsuarioModel, String> usuarioNome;
    @FXML
    private TableColumn<UsuarioModel, String> usuarioEmail;

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

    @FXML
    public void initialize() {
        carregarTableViewUsuarios();
        carregarComboBoxTipoUsuario();
        definirUsuarioSelecionado();
    }

    private void atualizarTableViewUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        tableViewAlterarExcluirUsuario.setItems(usuarioDAO.buscarTodos());
    }

    private void carregarTableViewUsuarios() {
        //TableView alteração e exclusão de usuários
        usuarioId.setCellValueFactory(new PropertyValueFactory<>("id"));
        usuarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        usuarioEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        atualizarTableViewUsuarios();
    }

    private void carregarComboBoxTipoUsuario(){
        List<String> usuarios = new ArrayList<>();
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
    private void mudarTelaGeral(ActionEvent event) {
        esconderPaineis();
        limparCampos();

        String textoBotao = ((Button) event.getSource()).getText();
        switch (textoBotao) {
            case "Início":
                mostrarInicio();
                break;
            case "Gerenciar Usuários":
                mostrarGerenciarUsuarios();
                break;
            case "Gerenciar Disciplinas":
                mostrarGerenciarDisciplinas();
                break;
            case "Gerenciar Turmas":
                mostrarGerenciarTurmas();
                break;
            default:
                break;
        }
    }

    private void mostrarInicio() {
        gPrincipal.setVisible(true);
    }

    private void mostrarGerenciarUsuarios() {
        gUsuarios.setVisible(true);
        gBotaoUsuario.setVisible(true);
    }

    @FXML
    private void mudarTelaUsuarios(ActionEvent event) {
        esconderPaineis();
        limparCampos();

        String textoBotao = ((Button) event.getSource()).getText();
        switch (textoBotao) {
            case "Adicionar":
                mostrarAdicionarUsuario();
                break;
            case "Alterar/Excluir":
                mostrarAlterarExcluirUsuario();
                break;
            default:
                break;
        }
    }

    private void mostrarAdicionarUsuario() {
        gUsuarios.setVisible(true);
        gAdicionarUsuario.setVisible(true);
    }

    private boolean verificarCamposVazios() {
        return nomeAdicionarUsuario.getText().isEmpty()
                || senhaAdicionarUsuario.getText().isEmpty()
                || confirmarSenhaAdicionarUsuario.getText().isEmpty()
                || emailAdicionarUsuario.getText().isEmpty()
                || cbTipoAdicionarUsuario.getSelectionModel().getSelectedItem() == null;
    }

    //TODO: exibir mensagem de sucesso ao criar usuário
    @FXML
    private void adicionarUsuario() {
        boolean verificar = true;

        if (verificarCamposVazios()) {
            mensagemErroCampos.setText("Todos campos devem ser preenchidos!");
            verificar = false;
        } else {
            mensagemErroCampos.setText("");
        }

        if (!UsuarioModel.verificarSenhasCoincidem(senhaAdicionarUsuario.getText(), confirmarSenhaAdicionarUsuario.getText())) {
            mensagemErroSenhasDiferentes.setText("Senhas diferentes!");
            verificar = false;
        } else {
            mensagemErroSenhasDiferentes.setText("");
        }

        //TODO: criar mensagem de erro "email em uso"
        if (UsuarioModel.verificarEmailEmUso(emailAdicionarUsuario.getText())) {

            verificar = false;
        } else {

        }

        if (!UsuarioModel.verificarEmailValido(emailAdicionarUsuario.getText())) {
            mensagemErroEmail.setText("E-mail inválido!");
            verificar = false;
        } else {
            mensagemErroEmail.setText("");
        }

        if(verificar){
            UsuarioCriarRequest request = new UsuarioCriarRequest(
                    nomeAdicionarUsuario.getText(),
                    emailAdicionarUsuario.getText(),
                    senhaAdicionarUsuario.getText());

            UsuarioModel.criarUsuario(request, cbTipoAdicionarUsuario.getValue());
            atualizarTableViewUsuarios();
            mensagemErroCampos.setText("Usuário cadastrado com sucesso!");
            System.out.println("Formulario enviado???");

            // Cria uma pausa para o texto voltar a seu estado vazio após 2 segundos
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {mensagemErroCampos.setText("");});
            pause.play();
        }
    }

    private void mostrarAlterarExcluirUsuario() {
        gUsuarios.setVisible(true);
        gAlterarExcluirUsuario.setVisible(true);
    }

    private UsuarioModel usuarioSelecionado;

    private void definirUsuarioSelecionado() {
        tableViewAlterarExcluirUsuario.setOnMouseClicked((MouseEvent) -> {
            usuarioSelecionado = tableViewAlterarExcluirUsuario.getSelectionModel().getSelectedItem();
            System.out.println(usuarioSelecionado);
        });
    }

    private UsuarioModel getUsuarioSelecionado() {
        return usuarioSelecionado;
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
        UsuarioModel.excluirUsuario(getUsuarioSelecionado());
        atualizarTableViewUsuarios();
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
    private void mudarTelaDisciplinas(ActionEvent event) {
        esconderPaineis();
        limparCampos();

        String textoBotao = ((Button) event.getSource()).getText();
        switch (textoBotao) {
            case "Adicionar":
                mostrarAdicionarDisciplinas();
                break;
            case "Alterar/Excluir":
                mostrarAlterarExcluirDisciplinas();
                break;
            default:
                break;
        }
    }

    private void mostrarAdicionarDisciplinas() {
        gDisciplinas.setVisible(true);
        gAdicionarDisciplinas.setVisible(true);
    }

    private void mostrarAlterarExcluirDisciplinas() {
        gDisciplinas.setVisible(true);
        gAlterarExcluirDisciplinas.setVisible(true);
    }

    @FXML
    private void adicionarDisciplina(){
        System.out.println("diciplina adicionada?");
    }

    @FXML
    private void alterarDisciplina() {
        System.out.println("diciplina alterada?");
    }

    @FXML
    private void excluirDisciplina(){
        System.out.println("disciplina excluida?");
    }

    @FXML
    private void mostrarGerenciarTurmas() {
        gTurmas.setVisible(true);
        gBotaoTurmas.setVisible(true);
    }

    @FXML
    private void mudarTelaTurmas(ActionEvent event) {
        esconderPaineis();
        limparCampos();

        String textoBotao = ((Button) event.getSource()).getText();
        switch (textoBotao) {
            case "Adicionar":
                mostrarAdicionarTurmas();
                break;
            case "Alterar/Excluir":
                mostrarAlterarExcluirTurmas();
                break;
            case "Adicionar/Remover Aluno":
                mostraAdicionarRemoverAlunosTurmas();
                break;
            default:
                break;
        }
    }

    private void mostrarAdicionarTurmas() {
        gTurmas.setVisible(true);
        gAdicionarTurmas.setVisible(true);
    }

    private void mostrarAlterarExcluirTurmas() {
        gTurmas.setVisible(true);
        gAlterarExcluirTurmas.setVisible(true);
    }

    private void mostraAdicionarRemoverAlunosTurmas() {
        gTurmas.setVisible(true);
        gAdicionarRemoverAlunosTurmas.setVisible(true);
    }

    private void esconderPaineis() {
        gPrincipal.setVisible(false);

        gUsuarios.setVisible(false);
        gBotaoUsuario.setVisible(false);
        gAdicionarUsuario.setVisible(false);
        gAlterarExcluirUsuario.setVisible(false);

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
    private void limparCampos(){
        nomeAdicionarUsuario.clear();
        senhaAdicionarUsuario.clear();
        confirmarSenhaAdicionarUsuario.clear();
        emailAdicionarUsuario.clear();

        // Criar uma ComboBox com as mesmas propriedades e itens
        ComboBox<String> novaComboBox = new ComboBox<>(cbTipoAdicionarUsuario.getItems());
        novaComboBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(novaComboBox, Priority.ALWAYS);
        novaComboBox.setPromptText(cbTipoAdicionarUsuario.getPromptText());

        // Substituir a ComboBox original pela nova
        hTipoUsuario.getChildren().remove(cbTipoAdicionarUsuario);
        hTipoUsuario.getChildren().add(novaComboBox);
        cbTipoAdicionarUsuario = novaComboBox;

        mensagemErroSenhasDiferentes.setText("");
        mensagemErroCampos.setText("");
        mensagemErroEmail.setText("");

        nomeAdicionarDisciplina.clear();
    }

    @FXML
    private void onLogoutClicked(ActionEvent event) {
        LoginView.mostrarLogin();
    }
}
