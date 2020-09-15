public class   Logic {
    public static void main(int type, double lambda, double alpha, double omega, double a, double c) {
        switch(type) {
            case 1: firstWay(lambda); break;
            case 2: secondWay(alpha, omega); break;
            case 3: thirdWay(a, c); break;
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
        ReadFromFile.main(1);
        Histogram.main();
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
        ReadFromFile.main(2);
        Histogram.main();
    }
    private static void thirdWay(double a, double c) {

    }

}
