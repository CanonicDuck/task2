package task2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class ImageToPGM extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(initInterface(primaryStage), 400, 400));
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

            try {
                FileInputStream fis = new FileInputStream(chImg);
                Image im = new Image(fis);
                PixelReader pr = im.getPixelReader();

                int w = (int) im.getWidth();
                int h = (int) im.getHeight();
                PGMImage pgm = new PGMImage(w, h);

                for (int i = 0; i < w; i++) {
                    for (int j = 0; j < h; j++) {
                        double r = 0.2126 * pr.getColor(i, j).getRed();
                        double g = 0.7152 * pr.getColor(i, j).getGreen();
                        double bl = 0.0722 * pr.getColor(i, j).getBlue();
                        pgm.setPixel(i, j, (int) (255 * (r+g+bl)));
                    }
                }
                pgm.saveTo(chImg.getName() + ".pgm");
            } catch (Exception fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        window.getChildren().addAll(b, l, iv);
        return window;
    }

}

