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
    private JLabel aLabel;
    private JLabel cLabel;
    private JFrame frame;
    private JButton button;
    private JPanel panel;
    private JTextField lambda;
    private JTextField alpha;
    private JTextField omega;
    private JTextField a;
    private JTextField c;

    public GUI() {

        JFrame frame = new JFrame();

        button = new JButton("Just do it!");
        button.addActionListener(this);
        button.setBounds(100, 20, 165, 25);

        lambda = new JTextField(6);
        lambda.setBounds(100, 20, 165, 25);
        alpha = new JTextField(6);
        alpha.setBounds(100, 20, 165, 25);
        omega = new JTextField(6);
        omega.setBounds(100, 20, 165, 25);
        a = new JTextField(6);
        a.setBounds(100, 20, 165, 25);
        c = new JTextField(6);
        c.setBounds(100, 20, 165, 25);

        lambdaLabel = new JLabel("Enter lambda");
        alphaLabel = new JLabel("Enter alpha");
        omegaLabel = new JLabel("Enter omega");
        aLabel = new JLabel("Enter aLabel");
        cLabel = new JLabel("Enter cLabel");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(6, 2 ));
        panel.add(lambdaLabel);
        panel.add(lambda);
        panel.add(alphaLabel);
        panel.add(alpha);
        panel.add(omegaLabel);
        panel.add(omega);
        panel.add(aLabel);
        panel.add(a);
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
            double aValue = Double.parseDouble(a.getText());
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
                    Logic.main(i, lambdaValue, alphaValue, omegaValue, aValue, cValue);
                } else {
                    System.out.println("File already exists.");
                    Logic.main(i, lambdaValue, alphaValue, omegaValue, aValue, cValue);
                }
            }
        } catch (IOException exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
        }
        number = (double)Math.round(Math.random() * 100000d) / 100000d;
    }
}
