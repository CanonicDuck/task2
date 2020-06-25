package task2;

public class FirstTask {
    public static void main(String[] args) throws Exception{
        PGMImage imRand = new PGMImage(80,60);
        PGMImage imGrad = new PGMImage(80,60);
        PGMImage imTest = new PGMImage(32,8);

        for (int i = 0; i < imRand.width; i++) {
            for (int j = 0; j < imRand.height; j++) {
                imRand.setPixel(i, j, (int) (Math.random()*256));
            }
        }

        for (int i = 0; i < imGrad.width; i++) {
            for (int j = 0; j < imGrad.height; j++) {
                imGrad.setPixel(i, j, (i+j) % 256);
            }
        }

        int k = 0;
        for (int i = 0; i < imTest.height; i++) {
            for (int j = 0; j < imTest.width; j++) {
                imTest.setPixel(j, i, k);
                //++k;
            }
        }
        imTest.setPixel(1, 3, 0);


        imRand.saveTo("imRand.pgm");
        imGrad.saveTo("imGrad.pgm");
        imTest.saveTo("imTest.pgm");

    }
}


