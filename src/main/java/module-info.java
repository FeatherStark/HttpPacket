module pro.opensourceproject.httppacket {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires commons.lang;

    opens pro.opensourceproject.httppacket to javafx.fxml;
    exports pro.opensourceproject.httppacket;
}