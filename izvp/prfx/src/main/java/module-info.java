module com.karpenko {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml;
    requires java.xml.bind;
    requires java.activation;
    opens com.karpenko to javafx.fxml;
    exports com.karpenko;
}
