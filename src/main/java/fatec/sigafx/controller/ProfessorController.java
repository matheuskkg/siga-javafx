package fatec.sigafx.controller;

import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.view.LoginView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProfessorController {

    @FXML
    private VBox gPrincipal;

    @FXML
    private VBox gNotas;
    public VBox gAlunosTurma;
    public HBox hAtribuirNotasTurma;
    public ComboBox<TurmaModel> cbAtribuirNotasTurma;
    public TableView<AlunoModel> tAtribuirNotasAlunos;
    public TableColumn<AlunoModel, Integer> alunoId;
    public TableColumn<AlunoModel, String> alunoNome;
    public TableColumn<AlunoModel, String> alunoEmail;
    public TableColumn<AlunoModel, Double> alunoP1;
    public TableColumn<AlunoModel, Double> alunoP2;
    public TableColumn<AlunoModel, Double> alunoP3;
    public Label mAtribuirNotas;
    public Button bConfirmarAluno;

    public VBox gAtribuirNotas;
    public TextField nomeAlunoNota;
    public CheckBox checkP1;
    public HBox hAtribuirP1;
    public Spinner<Double> sP1;
    public CheckBox checkP2;
    public HBox hAtribuirP2;
    public Spinner<Double> sP2;
    public CheckBox checkP3;
    public HBox hAtribuirP3;
    public Spinner<Double> sP3;
    public Label mAtribuirNotasAluno;
    public Button bAtribuirNotas;

    @FXML
    private VBox gFaltas;

    @FXML
    public void initialize() {
        configuraSpinnersNotas();
    }

    private SpinnerValueFactory<Double> montaSpinnerNota() {
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10) {
            @Override
            public void increment(int steps) {
                if (getValue() == null) {
                    setValue(0.0);
                } else {
                    super.increment(steps);
                }
            }

            @Override
            public void decrement(int steps) {
                if (getValue() == null || getValue() == 0.0) {
                    setValue(null);
                }else {
                    super.decrement(steps);
                }
            }
        };

        valueFactory.setValue(null);
        return valueFactory;

    }

    public void configuraSpinnersNotas(){
        sP1.setValueFactory(montaSpinnerNota());
        sP2.setValueFactory(montaSpinnerNota());
        sP3.setValueFactory(montaSpinnerNota());
    }

    // Esconder todos os painéis
    private void esconderPaineis() {
        gPrincipal.setVisible(false);
        gNotas.setVisible(false);
        gFaltas.setVisible(false);

        gAlunosTurma.setVisible(false);
        gAtribuirNotas.setVisible(false);
    }

    // Mostrar *Início*
    @FXML
    public void mostrarInicio() {
        esconderPaineis();
        gPrincipal.setVisible(true);
    }

    // Mostrar *Notas*
    @FXML
    public void mostrarNotas() {
        esconderPaineis();
        gNotas.setVisible(true);
        gAlunosTurma.setVisible(true);
    }

    @FXML
    public void mostrarAtribuirNotas() {
        gNotas.setVisible(true);
        gAlunosTurma.setVisible(false);
        gAtribuirNotas.setVisible(true);
        mAtribuirNotasAluno.setText("");
    }

    private boolean verificarCamposVaziosAtribuirNotas() {
        return (!checkP1.isSelected() && !checkP2.isSelected() && !checkP3.isSelected())
                || (checkP1.isSelected() && sP1.getValue() == null)
                || (checkP2.isSelected() && sP2.getValue() == null)
                || (checkP3.isSelected() && sP3.getValue() == null);
    }

    @FXML
    public void atribuirNotas() {
        mAtribuirNotasAluno.setText("");

        boolean camposVazios = verificarCamposVaziosAtribuirNotas();
        if (camposVazios) {
            mAtribuirNotasAluno.setText("Selecione alguma nota e atribua um valor a ela para prosseguir.");
        }

    }

    //Da pra deixar mais funcional e fazer só uma função, mas tô com preguiça
    @FXML
    public void mostrarP1() {
        hAtribuirP1.setDisable(!checkP1.isSelected());
        sP1.setValueFactory(montaSpinnerNota());
    }

    @FXML
    public void mostrarP2() {
        hAtribuirP2.setDisable(!checkP2.isSelected());
        sP2.setValueFactory(montaSpinnerNota());
    }

    @FXML
    public void mostrarP3() {
        hAtribuirP3.setDisable(!checkP3.isSelected());
        sP3.setValueFactory(montaSpinnerNota());
    }

    // Mostrar *Faltas*
    @FXML
    public void mostrarFaltas() {
        esconderPaineis();
        gFaltas.setVisible(true);
    }

    @FXML
    public void onLogoutClicked(ActionEvent event) {
        // Redireciona para a página de login
        LoginView.mostrarLogin();

    }
}
