module fatec.sigafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens fatec.sigafx to javafx.fxml;
    exports fatec.sigafx;
    exports fatec.sigafx.controller;
    opens fatec.sigafx.controller to javafx.fxml;

    opens fatec.sigafx.model.usuario to org.hibernate.orm.core;
    opens fatec.sigafx.model.aluno to org.hibernate.orm.core;
    opens fatec.sigafx.model.admin to org.hibernate.orm.core;
    opens fatec.sigafx.model.disciplina to org.hibernate.orm.core;
}