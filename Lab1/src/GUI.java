import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI implements ActionListener {
    double number = 0;
    private JLabel label;
    private JFrame frame;
    private JButton button;
    private JPanel panel;

    public GUI() {

        JFrame frame = new JFrame();

        button = new JButton("Generate numbers");
        button.addActionListener(this);

        label = new JLabel("Press the button");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 2));
        panel.add(label);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(100, 100);
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
            File firstWay = new File("firstWay.txt");
            File secondWay = new File("secondWay.txt");
            File thirdWay = new File("thirdWay.txt");
            for (int i = 0; i < 3; i++) {
                File file;
                switch (i) {
                    case 1: file = secondWay; break;
                    case 2: file = thirdWay; break;
                    default: file = firstWay;
                }
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            }
            String[] text = {"sad\n", "happy"};
            WriteToFile.main(text);
        } catch (IOException exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
        }
        number = (double)Math.round(Math.random() * 100000d) / 100000d;
        label.setText("Number: " + number);
    }
}
