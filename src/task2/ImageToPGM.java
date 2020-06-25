package task2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImageToPGM extends Application {


    @Override
    public void start(Stage primaryStage) {
        VBox vb = initInterface();
        primaryStage.setScene(new Scene(vb, 640, 800));
        primaryStage.show();

        Button b = new Button("Choose File");
        vb.getChildren().addAll(b);
        FileChooser fc = new FileChooser();
        b.setOnAction(e -> {
            File chImg = fc.showOpenDialog(primaryStage);
            Label l = new Label(chImg.getName());
            vb.getChildren().add(l);
        });

    }

    private VBox initInterface() {
        VBox window = new VBox();
        HBox h1 = new HBox();

        ImageView iv1 = new ImageView();
        WritableImage im1 = new WritableImage(120, 100);
        for (int i = 0; i < 120; i++) {
            for (int j = 0; j < 100; j++) {
                im1.getPixelWriter().setColor(i,j, Color.GREEN);
            }
        }
        iv1.setImage(im1);

        h1.getChildren().addAll(iv1);
        window.getChildren().addAll(h1);
        return window;
    }

}

