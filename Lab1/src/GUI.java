import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI implements ActionListener {
    double number = 0;
    private JLabel lambdaLabel;
    private JLabel alphaLabel;
    private JLabel omegaLabel;
    private JLabel zLabel;
    private JLabel bLabel;
    private JLabel cLabel;
    private JFrame frame;
    private JButton button;
    private JPanel panel;
    private JTextField lambda;
    private JTextField alpha;
    private JTextField omega;
    private JTextField z0;
    private JTextField b;
    private JTextField c;

    public GUI() {

        JFrame frame = new JFrame();

        button = new JButton("Generate");
        button.addActionListener(this);
        button.setBounds(100, 20, 165, 25);

        lambda = new JTextField(6);
        lambda.setBounds(100, 20, 165, 25);
        alpha = new JTextField(6);
        alpha.setBounds(100, 20, 165, 25);
        omega = new JTextField(6);
        omega.setBounds(100, 20, 165, 25);
        z0 = new JTextField(6);
        z0.setBounds(100, 20, 165, 25);
        b = new JTextField(6);
        b.setBounds(100, 20, 165, 25);
        c = new JTextField(6);
        c.setBounds(100, 20, 165, 25);

        lambdaLabel = new JLabel("Enter lambda");
        alphaLabel = new JLabel("Enter alpha");
        omegaLabel = new JLabel("Enter omega");
        zLabel = new JLabel("Enter Z0");
        bLabel = new JLabel("Enter b");
        cLabel = new JLabel("Enter c");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(60, 60, 20, 60));
        panel.setLayout(new GridLayout(8, 2, 20, 5));
        panel.add(lambdaLabel);
        panel.add(lambda);
        panel.add(alphaLabel);
        panel.add(alpha);
        panel.add(omegaLabel);
        panel.add(omega);
        panel.add(zLabel);
        panel.add(z0);
        panel.add(bLabel);
        panel.add(b);
        panel.add(cLabel);
        panel.add(c);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Lab 1. Valentyn Shapoval IS-72");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] Args) {
        new GUI();
    }

    public void actionPerformed(ActionEvent event) {
        try {
            double lambdaValue = Double.parseDouble(lambda.getText());
            double alphaValue = Double.parseDouble(alpha.getText());
            double omegaValue = Double.parseDouble(omega.getText());
            double zValue = Double.parseDouble(z0.getText());
            double bValue = Double.parseDouble(b.getText());
            double cValue = Double.parseDouble(c.getText());

            File firstWay = new File("firstWay.txt");
            File secondWay = new File("secondWay.txt");
            File thirdWay = new File("thirdWay.txt");
            for (int i = 0; i < 3; i++) {
                File file;
                switch (i) {
                    case 1:
                        file = secondWay;
                        break;
                    case 2:
                        file = thirdWay;
                        break;
                    default:
                        file = firstWay;
                }
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                    Logic.main(i, lambdaValue, alphaValue, omegaValue, zValue, bValue, cValue);
                } else {
                    System.out.println("File already exists.");
                    Logic.main(i, lambdaValue, alphaValue, omegaValue, zValue, bValue, cValue);
                }
            }
        } catch (IOException exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
        }
        number = (double)Math.round(Math.random() * 100000d) / 100000d;
    }
}
