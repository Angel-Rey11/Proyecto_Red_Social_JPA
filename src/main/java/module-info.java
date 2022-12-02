module com.iesfranciscodelosrios.Proyecto_RedSocial {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
    requires javafx.base;
    requires java.xml.bind;
	requires javafx.graphics;
    requires org.apache.commons.codec;

    opens com.iesfranciscodelosrios.Proyecto_RedSocial to javafx.fxml;
    opens com.iesfranciscodelosrios.Proyecto_RedSocial.Connection to java.xml.bind;
    exports com.iesfranciscodelosrios.Proyecto_RedSocial;
}
