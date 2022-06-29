package pro.opensourceproject.httppacket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/*
POST / HTTP/1.1
Host: www.baidu.com

 */
public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("MainFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.setCursor(new ImageCursor(new Image("images/http.png")));
        stage.setTitle("HttpPacket v0.1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}