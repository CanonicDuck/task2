package task2;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class PGMImage {
    public int[][] image;
    public int width;
    public int height;

    public PGMImage(int w, int h) {
        this.width = w;
        this.height = h;
        this.image = new int[w][h];
    }

    public void setPixel(int x, int y, int color) {
        this.image[x][y] = color;
    }

    public void saveTo(String filename) throws Exception{
        PrintStream out = new PrintStream(filename, StandardCharsets.UTF_8);
        out.println("P2");
        out.println(this.width);
        out.println(this.height);
        out.println("255");

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                out.print(this.image[j][i] + " ");
            }
            out.println();
        }
        out.close();
    }
}
