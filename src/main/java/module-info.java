module com.iesfranciscodelosrios.Proyecto_RedSocial {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
    requires javafx.base;
    requires java.xml.bind;
	requires javafx.graphics;
    requires org.apache.commons.codec;
	requires java.persistence;
	requires org.hibernate.orm.core;

    opens com.iesfranciscodelosrios.Proyecto_RedSocial to javafx.fxml;
    opens com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion to java.xml.bind;
    opens com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;
    exports com.iesfranciscodelosrios.Proyecto_RedSocial.Conexion;
    exports com.iesfranciscodelosrios.Proyecto_RedSocial;
}
