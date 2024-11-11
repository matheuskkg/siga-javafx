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
    exports fatec.sigafx.model.usuarios;
    exports fatec.sigafx.model.aulas;
    opens fatec.sigafx.controller to javafx.fxml;

    opens fatec.sigafx.model.usuarios to javafx.base, org.hibernate.orm.core;
    opens fatec.sigafx.model.aulas to javafx.base, org.hibernate.orm.core;
}