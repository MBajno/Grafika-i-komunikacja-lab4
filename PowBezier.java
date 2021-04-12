import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.PrintWriter;

public class PowBezier {
    public static void main(String[] args) throws FileNotFoundException {
        PowBezier bezier = new PowBezier();
        bezier.oblicz();

    }

    private static double round(double value, int places) {
        //zaokrąglanie
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }


    public void oblicz() throws FileNotFoundException {
        String xyz;

        double[][] x = new double[4][4];
        double[][] y = new double[4][4];
        double[][] z = new double[4][4];

        double zmienna_x;
        double zmienna_y;
        double zmienna_z;

        //zczytywanie danych
        File teapot = new File("dane_czajnik.txt");
        File cup = new File("dane_filizanka.txt");
        File spoon = new File("dane_lyzka.txt");

        Scanner czajnik = new Scanner(teapot);
        Scanner filizanka = new Scanner(cup);
        Scanner lyzka = new Scanner(spoon);

        //do zapisywania punktów w pliku
        PrintWriter save_teapot = new PrintWriter("czajnik.txt");
        PrintWriter save_cup = new PrintWriter("filizanka.txt");
        PrintWriter save_spoon = new PrintWriter("lyzka.txt");

        //dawanie identyfikatorow
        save_teapot.println("x y z");
        save_cup.println("x y z");
        save_spoon.println("x y z");

        //Bezier dla czajnika
        while (czajnik.hasNextLine()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                    xyz = czajnik.next();
                    x[i][j] = Double.parseDouble(xyz);

                    xyz = czajnik.next();
                    y[i][j] = Double.parseDouble(xyz);

                    xyz = czajnik.next();
                    z[i][j] = Double.parseDouble(xyz);

                }
            }

            for (double k = 0; k <= 1; k += 0.1) {
                for (double l = 0; l <= 1; l += 0.1) {

                    zmienna_x = ((1 - l) * (1 - l) * (1 - l)) * (x[0][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[0][1] * k * (1 - k) * (1 - k) + 3 * x[0][2] * k * k * (1 - k) + x[0][3] * k * k * k) +
                            (3 * (1 - l) * (1 - l) * l) * (x[1][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[1][1] * k * (1 - k) * (1 - k) + 3 * x[1][2] * k * k * (1 - k) + x[1][3] * k * k * k) +
                            (3 * (1 - l) * l * l) * (x[2][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[2][1] * k * (1 - k) * (1 - k) + 3 * x[2][2] * k * k * (1 - k) + x[2][3] * k * k * k) +
                            (l * l * l) * (x[3][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[3][1] * k * (1 - k) * (1 - k) + 3 * x[3][2] * k * k * (1 - k) + x[3][3] * k * k * k);

                    zmienna_y = ((1 - l) * (1 - l) * (1 - l)) * (y[0][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[0][1] * k * (1 - k) * (1 - k) + 3 * y[0][2] * k * k * (1 - k) + y[0][3] * k * k * k) +
                            (3 * (1 - l) * (1 - l) * l) * (y[1][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[1][1] * k * (1 - k) * (1 - k) + 3 * y[1][2] * k * k * (1 - k) + y[1][3] * k * k * k) +
                            (3 * (1 - l) * l * l) * (y[2][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[2][1] * k * (1 - k) * (1 - k) + 3 * y[2][2] * k * k * (1 - k) + y[2][3] * k * k * k) +
                            (l * l * l) * (y[3][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[3][1] * k * (1 - k) * (1 - k) + 3 * y[3][2] * k * k * (1 - k) + y[3][3] * k * k * k);

                    zmienna_z = ((1 - l) * (1 - l) * (1 - l)) * (z[0][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[0][1] * k * (1 - k) * (1 - k) + 3 * z[0][2] * k * k * (1 - k) + z[0][3] * k * k * k) +
                            (3 * (1 - l) * (1 - l) * l) * (z[1][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[1][1] * k * (1 - k) * (1 - k) + 3 * z[1][2] * k * k * (1 - k) + z[1][3] * k * k * k) +
                            (3 * (1 - l) * l * l) * (z[2][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[2][1] * k * (1 - k) * (1 - k) + 3 * z[2][2] * k * k * (1 - k) + z[2][3] * k * k * k) +
                            (l * l * l) * (z[3][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[3][1] * k * (1 - k) * (1 - k) + 3 * z[3][2] * k * k * (1 - k) + z[3][3] * k * k * k);
                    //zapisywanie wyniku
                    save_teapot.println(round(zmienna_x, 2) + " " + round(zmienna_y, 2) + " " + round(zmienna_z, 2) + " " + round(zmienna_x + zmienna_z + zmienna_y, 2));

                }
            }
        }

        //Bezier dla filizanki
        while (filizanka.hasNextLine()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                    xyz = filizanka.next();
                    x[i][j] = Double.parseDouble(xyz);

                    xyz = filizanka.next();
                    y[i][j] = Double.parseDouble(xyz);

                    xyz = filizanka.next();
                    z[i][j] = Double.parseDouble(xyz);

                }
            }

            for (double k = 0; k <= 1; k += 0.1) {
                for (double l = 0; l <= 1; l += 0.1) {

                    zmienna_x = ((1 - l) * (1 - l) * (1 - l)) * (x[0][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[0][1] * k * (1 - k) * (1 - k) + 3 * x[0][2] * k * k * (1 - k) + x[0][3] * k * k * k) +
                            (3 * (1 - l) * (1 - l) * l) * (x[1][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[1][1] * k * (1 - k) * (1 - k) + 3 * x[1][2] * k * k * (1 - k) + x[1][3] * k * k * k) +
                            (3 * (1 - l) * l * l) * (x[2][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[2][1] * k * (1 - k) * (1 - k) + 3 * x[2][2] * k * k * (1 - k) + x[2][3] * k * k * k) +
                            (l * l * l) * (x[3][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[3][1] * k * (1 - k) * (1 - k) + 3 * x[3][2] * k * k * (1 - k) + x[3][3] * k * k * k);

                    zmienna_y = ((1 - l) * (1 - l) * (1 - l)) * (y[0][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[0][1] * k * (1 - k) * (1 - k) + 3 * y[0][2] * k * k * (1 - k) + y[0][3] * k * k * k) +
                            (3 * (1 - l) * (1 - l) * l) * (y[1][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[1][1] * k * (1 - k) * (1 - k) + 3 * y[1][2] * k * k * (1 - k) + y[1][3] * k * k * k) +
                            (3 * (1 - l) * l * l) * (y[2][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[2][1] * k * (1 - k) * (1 - k) + 3 * y[2][2] * k * k * (1 - k) + y[2][3] * k * k * k) +
                            (l * l * l) * (y[3][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[3][1] * k * (1 - k) * (1 - k) + 3 * y[3][2] * k * k * (1 - k) + y[3][3] * k * k * k);

                    zmienna_z = ((1 - l) * (1 - l) * (1 - l)) * (z[0][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[0][1] * k * (1 - k) * (1 - k) + 3 * z[0][2] * k * k * (1 - k) + z[0][3] * k * k * k) +
                            (3 * (1 - l) * (1 - l) * l) * (z[1][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[1][1] * k * (1 - k) * (1 - k) + 3 * z[1][2] * k * k * (1 - k) + z[1][3] * k * k * k) +
                            (3 * (1 - l) * l * l) * (z[2][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[2][1] * k * (1 - k) * (1 - k) + 3 * z[2][2] * k * k * (1 - k) + z[2][3] * k * k * k) +
                            (l * l * l) * (z[3][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[3][1] * k * (1 - k) * (1 - k) + 3 * z[3][2] * k * k * (1 - k) + z[3][3] * k * k * k);
                    //zapisywanie wyniku
                    save_cup.println(round(zmienna_x, 2) + " " + round(zmienna_y, 2) + " " + round(zmienna_z, 2) + " " + round(zmienna_x + zmienna_z + zmienna_y, 2));

                }
            }
        }

        //Bezier dla lyzki
        while (lyzka.hasNextLine()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                    xyz = lyzka.next();
                    x[i][j] = Double.parseDouble(xyz);

                    xyz = lyzka.next();
                    y[i][j] = Double.parseDouble(xyz);

                    xyz = lyzka.next();
                    z[i][j] = Double.parseDouble(xyz);

                }
            }

            for (double k = 0; k <= 1; k += 0.1) {
                for (double l = 0; l <= 1; l += 0.1) {

                    zmienna_x = ((1 - l) * (1 - l) * (1 - l)) * (x[0][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[0][1] * k * (1 - k) * (1 - k) + 3 * x[0][2] * k * k * (1 - k) + x[0][3] * k * k * k) +
                            (3 * (1 - l) * (1 - l) * l) * (x[1][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[1][1] * k * (1 - k) * (1 - k) + 3 * x[1][2] * k * k * (1 - k) + x[1][3] * k * k * k) +
                            (3 * (1 - l) * l * l) * (x[2][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[2][1] * k * (1 - k) * (1 - k) + 3 * x[2][2] * k * k * (1 - k) + x[2][3] * k * k * k) +
                            (l * l * l) * (x[3][0] * (1 - k) * (1 - k) * (1 - k) + 3 * x[3][1] * k * (1 - k) * (1 - k) + 3 * x[3][2] * k * k * (1 - k) + x[3][3] * k * k * k);

                    zmienna_y = ((1 - l) * (1 - l) * (1 - l)) * (y[0][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[0][1] * k * (1 - k) * (1 - k) + 3 * y[0][2] * k * k * (1 - k) + y[0][3] * k * k * k) +
                            (3 * (1 - l) * (1 - l) * l) * (y[1][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[1][1] * k * (1 - k) * (1 - k) + 3 * y[1][2] * k * k * (1 - k) + y[1][3] * k * k * k) +
                            (3 * (1 - l) * l * l) * (y[2][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[2][1] * k * (1 - k) * (1 - k) + 3 * y[2][2] * k * k * (1 - k) + y[2][3] * k * k * k) +
                            (l * l * l) * (y[3][0] * (1 - k) * (1 - k) * (1 - k) + 3 * y[3][1] * k * (1 - k) * (1 - k) + 3 * y[3][2] * k * k * (1 - k) + y[3][3] * k * k * k);

                    zmienna_z = ((1 - l) * (1 - l) * (1 - l)) * (z[0][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[0][1] * k * (1 - k) * (1 - k) + 3 * z[0][2] * k * k * (1 - k) + z[0][3] * k * k * k) +
                            (3 * (1 - l) * (1 - l) * l) * (z[1][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[1][1] * k * (1 - k) * (1 - k) + 3 * z[1][2] * k * k * (1 - k) + z[1][3] * k * k * k) +
                            (3 * (1 - l) * l * l) * (z[2][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[2][1] * k * (1 - k) * (1 - k) + 3 * z[2][2] * k * k * (1 - k) + z[2][3] * k * k * k) +
                            (l * l * l) * (z[3][0] * (1 - k) * (1 - k) * (1 - k) + 3 * z[3][1] * k * (1 - k) * (1 - k) + 3 * z[3][2] * k * k * (1 - k) + z[3][3] * k * k * k);
                    //zapisywanie wyniku
                    save_spoon.println(round(zmienna_x, 2) + " " + round(zmienna_y, 2) + " " + round(zmienna_z, 2) + " " + round(zmienna_x + zmienna_z + zmienna_y, 2));

                }
            }
        }

        //zamkniecie plików
        save_teapot.close();
        save_cup.close();
        save_spoon.close();
    }
}