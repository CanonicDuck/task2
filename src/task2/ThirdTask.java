package task2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import javafx.embed.swing.SwingFXUtils;

public class ThirdTask extends Application {
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

            int[][] pic;

            try {
                Path path = Path.of(chImg.getName());
                Scanner sc = new Scanner(path, StandardCharsets.UTF_8);
                sc.next();
                int w = Integer.parseInt(sc.next());
                int h = Integer.parseInt(sc.next());
                sc.next();

                WritableImage wi = new WritableImage(w, h);

                pic = new int[w][h];
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        pic[i][j] = Integer.parseInt(sc.next());
                        wi.getPixelWriter().setColor(j, i, Color.gray((double) pic[i][j]/255));
                        System.out.print(pic[i][j] + " ");
                    }
                    System.out.println();
                }
                iv.setImage(wi);

                File file = new File("test.png");
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(wi, null);
                ImageIO.write(renderedImage, "png", file);

            } catch (Exception fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });



        window.getChildren().addAll(b, l, iv);
        return window;
    }

}

