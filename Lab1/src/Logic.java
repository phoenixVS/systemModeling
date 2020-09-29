public class   Logic {
    public static void main(int type, double lambda, double alpha, double omega, double z0, double b, double c) {
        switch(type) {
            case 0: firstWay(lambda); break;
            case 1: secondWay(alpha, omega); break;
            case 2: thirdWay(z0, b, c); break;
        }
    }
    private static double rand() {
        return (double)Math.round(Math.random() * 100000d) / 100000d;
    }
    private static void firstWay(double lambda) {
        double numbers[] = new double[10000];
        for (int i = 0; i < 10000; i++) {
            numbers[i] = (-1 / lambda) * Math.log(rand());
        }
        String[] s = new String[numbers.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = String.valueOf(numbers[i]) + '\n';
        }
        WriteToFile.main(s, 1);
//        ReadFromFile.main(1);
        Histogram.main(numbers, "Exponential distribution", lambda, 0, 0, 0,0, 0);
    }
    private static void secondWay(double alpha, double omega) {
        double numbers[] = new double[10000];
        for (int i = 0; i < 10000; i++) {
            double mu = 0;
            for (int j = 1; j <= 12; j++) {
                mu += rand();
            }
            mu -= 6;
            numbers[i] = omega * mu + alpha;
        }
        String[] s = new String[numbers.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = String.valueOf(numbers[i]) + '\n';
        }
        WriteToFile.main(s, 2);
//        ReadFromFile.main(2);
        Histogram.main(numbers, "Normal distribution", 0, alpha, omega, 0 ,0, 0);
    }
    private static void thirdWay(double z0, double bPow, double cPow) {
        double numbers[] = new double[10000];
        double z = z0;
        for (int i = 0; i < 10000; i++) {
            if (i == 0) {
                z = z0;
            } else {
                z = numbers[i - 1];
            }
            double b = (int) Math.pow(5,bPow);
            double c = (int) Math.pow(2,cPow);
//            System.out.println("b: " + b);
//            System.out.println("c: " + c);
            z = b * z % c;
            if (Double.toString(z / c).contains("E")) {
                numbers[i] = numbers[i - 1] + Math.random() * 0.001;
            }
            else {
                numbers[i] = z / c;
            }
        }
        String[] s = new String[numbers.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = String.valueOf(numbers[i]) + '\n';
        }
        WriteToFile.main(s, 3);
//        ReadFromFile.main(3);
        Histogram.main(numbers, "Even distribution", 0, 0, 0, 0, 0,0);
    }

}
