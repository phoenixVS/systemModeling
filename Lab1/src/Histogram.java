import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class Histogram {

    public static void main(double[] arr, String name, double lambda, double alpha, double omega, double z0, double b, double c) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Histogram(arr, name, lambda, alpha, omega, z0, b, c);
            }
        });
    }

    public static long factorial(int n) {
        long fact = 1;
        for (int i = 1; i <= n; i++) {
             fact *= i;
        }
        return fact;
    }

    private double min;
    private double max;
    private double middle = 0;

    private double calcXiSquareElem(double ni, double npT) {
        return Math.pow((ni - npT), 2) / npT;
    }

    public Histogram(double[] arr, String name, double lambda, double alpha, double omega, double z0, double b, double c) {
        final int MY_CONSTANT= 6;

        // Calc min
         for (int i = 0; i < arr.length; i++) {
             if (i == 0) {
                 this.min = arr[i];
             } else {
                 if (arr[i] < this.min) {
                     this.min = arr[i];
                 }
             }
         }
        // Calc max
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                this.max = arr[i];
            }
            if (arr[i] > this.max) {
                this.max = arr[i];
            }
        }
        // Calc middle
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        middle = sum / 10000;

        // calc frequencies
        double step = (max - min ) / MY_CONSTANT;
        System.out.println("step: " + step);
        int[] freq = new int[MY_CONSTANT - 1];
        Arrays.fill(freq, 0);
        for (int i = 0; i < arr.length; i++) {
            for(int j = 0; j < freq.length;j++) {
                if (arr[i] < this.min + (step * (j + 1))) {
                    freq[j]++;
                    break;
                }
            }
        }
        System.out.println(name);
        System.out.println("min: "+ min);
        System.out.println("max: "+ max);
        for (int i = 0; i < freq.length; i++) {
            System.out.println((i + 1) + " : " + freq[i]);
        }
        System.out.println("middle: "+ middle);

        // Calc dispersion
        double XN_sum = 0;
        double X2N_sum = 0;
        int[] n = new int[freq.length];
        for (int i = 0; i < freq.length; i++) {
            if (i == 0){
                n[i] = freq[i];
            }
            else {
                n[i] = n[i - 1] + freq[i];
            }
        }
        for (int i = 0; i < freq.length; i++) {
            double x = this.min + step * i + step / 2;
            System.out.println("x: "+ x);
            System.out.println("n: "+ n[i]);
            double XN = x * n[i];
            double X2N = Math.pow(x, 2) * n[i];
            XN_sum += XN;
            X2N_sum += X2N;
        }
        double dispersion = (X2N_sum / 10000)  - ((Math.pow(XN_sum, 2) / 10000));
        System.out.println("dispersion: "+ dispersion);

        // Calc Xi ^ 2
        double[] xiSqElem = new double[freq.length];
        double XiSquare = 0;
        switch(name) {
            case "Exponential distribution": {
                for (int i = 0; i < freq.length; i++) {
                    double XiPlus1 = this.min + (step * i + 1);
                    double Xi = this.min + (step * i);
                    double Fxi = (1 - Math.exp(-(lambda * XiPlus1)));
                    double FxiMin1 = (1 - Math.exp(-(lambda * Xi)));
                    double Pi = Fxi - FxiMin1;
                    double nPiT = 10000 * Pi;
                    xiSqElem[i] = calcXiSquareElem(freq[i], nPiT);
                    //Pi = (Math.pow(dispersion, i) * Math.exp(-dispersion)) / factorial(i);
                }
                for (int i = 0; i < freq.length; i++) {
                    XiSquare += xiSqElem[i];
                }
                break;
            }
            case "Normal distribution": {
                for (int i = 0; i < freq.length; i++) {
                    double XiPlus1 = this.min + (step * i + 1);
                    double Xi = this.min + (step * i);
                    double Fxi = (1 / (alpha * Math.sqrt(2 * Math.PI))) * Math.exp(-(Math.pow((XiPlus1 - omega), 2) / (2 * Math.pow(alpha, 2))));
                    double FxiMin1 = (1 / (alpha * Math.sqrt(2 * Math.PI))) * Math.exp(-(Math.pow((Xi - omega), 2) / (2 * Math.pow(alpha, 2))));
                    double Pi = Fxi - FxiMin1;
                    double nPiT = 10000 * Pi;
                    xiSqElem[i] = calcXiSquareElem(freq[i], nPiT);
                }
                for (int i = 0; i < freq.length; i++) {
                    XiSquare += xiSqElem[i];
                }
                break;
            }
            case "Even distribution": {
                for (int i = 0; i < freq.length; i++) {
                    double Pi = 0.05;
                    double nPiT = 10000 * Pi;
                    xiSqElem[i] = calcXiSquareElem(freq[i], nPiT);
                }
                for (int i = 0; i < freq.length; i++) {
                    XiSquare += xiSqElem[i];
                }
                break;
            }
        }

        System.out.println("Xi^2 = " + XiSquare);

        // Drawing histogram
        int width = freq.length;
        int height = freq.length;
        int[][] data = new int[width][height];
        for (int k = 0; k < height; k++) {
            for (int r = 0; r < width; r++) {
                if (freq[r] == 0) {
                    data[k][r] = (int) (255);
                }
                else {
                    data[k][r] = (int) ((freq[r] / 10000) * 100);
                }
            }
        }
        Map<Integer, Integer> mapHistory = new TreeMap<Integer, Integer>();
        for (int k = 0; k < data.length; k++) {
//            for (int r = 0; r < data[c].length; r++) {
//                int value = data[c][r];
//                int amount = 0;
//                if (mapHistory.containsKey(value)) {
//                    amount = mapHistory.get(value);
//                    amount++;
//                } else {
//                    amount = 1;
//                }
//                mapHistory.put(value, amount);
//            }
            mapHistory.put(k, freq[k]);
        }
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(new Graph(mapHistory)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected class Graph extends JPanel {

        protected static final int MIN_BAR_WIDTH = 4;
        private Map<Integer, Integer> mapHistory;

        public Graph(Map<Integer, Integer> mapHistory) {
            this.mapHistory = mapHistory;
            int width = (mapHistory.size() * MIN_BAR_WIDTH) + 11;
            Dimension minSize = new Dimension(width, 128);
            Dimension prefSize = new Dimension(width, 256);
            setMinimumSize(minSize);
            setPreferredSize(prefSize);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (mapHistory != null) {
                int xOffset = 5;
                int yOffset = 5;
                int width = getWidth() - 1 - (xOffset * 2);
                int height = getHeight() - 1 - (yOffset * 2);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(xOffset, yOffset, width, height);
                int barWidth = Math.max(MIN_BAR_WIDTH,
                        (int) Math.floor((float) width
                                / (float) mapHistory.size()));
                System.out.println("width = " + width + "; size = "
                        + mapHistory.size() + "; barWidth = " + barWidth);
                int maxValue = 0;
                for (Integer key : mapHistory.keySet()) {
                    int value = mapHistory.get(key);
                    maxValue = Math.max(maxValue, value);
                }
                int xPos = xOffset;
                for (Integer key : mapHistory.keySet()) {
                    int value = mapHistory.get(key);
                    int barHeight = Math.round(((float) value
                            / (float) maxValue) * height);
                    g2d.setColor(new Color(key, key, key));
                    int yPos = height + yOffset - barHeight;
                    // Rectangle bar = new Rectangle(xPos, yPos, barWidth, barHeight);
                    Rectangle2D bar = new Rectangle2D.Float(
                            xPos, yPos, barWidth, barHeight);
                    g2d.fill(bar);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.draw(bar);
                    xPos += barWidth;
                }
                g2d.dispose();
            }
        }
    }
}