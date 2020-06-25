package task2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageToPGM extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setScene(new Scene(initInterface(primaryStage), 640, 800));
        primaryStage.show();
    }

    private VBox initInterface(Stage primaryStage) {
        VBox window = new VBox();

        ImageView iv = new ImageView();
        Label l = new Label();
        Button b = new Button("Choose File");
        FileChooser fc = new FileChooser();
        b.setOnAction(e -> {
            File chImg = fc.showOpenDialog(primaryStage);
            l.setText(chImg.getName());

            Image im;
            PixelReader pr;

            try {
                FileInputStream fis = new FileInputStream(chImg);
                im = new Image(fis);
                pr = im.getPixelReader();

                int w = (int) im.getWidth();
                int h = (int) im.getHeight();
                WritableImage im7 = new WritableImage(w, h);
                for (int i = 0; i < w; i++) {
                    for (int j = 0; j < h; j++) {
                        im7.getPixelWriter().setColor(i, j, pr.getColor(i, j));
                    }
                }
                iv.setImage(im7);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }


        });

        window.getChildren().addAll(b, l, iv);
        return window;
    }

}

