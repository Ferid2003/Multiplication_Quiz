import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;

public class history {

    JFrame frame;
    JLabel title;
    JLabel textField;
    String filename;
    JScrollPane scrollPane;

    public history(String username) {

        filename = username+".txt";

        frame = new JFrame("History");
        title = new JLabel();
        textField = new JLabel();




        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(textField, BorderLayout.CENTER);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);



        title.setHorizontalAlignment(JLabel.CENTER);

        StringBuilder labelText = new StringBuilder();

        labelText.append("History").append("<br>");

        if (Objects.equals(username, "admin")){
            try (BufferedReader reader = new BufferedReader(new FileReader("scoreboard.txt"))) {
                String line;
                int counter = 0;
                while ((line = reader.readLine()) != null) {
                    labelText.append(line).append("<br>");
                    counter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                int counter = 0;
                while ((line = reader.readLine()) != null) {
                    labelText.append(line).append("<br>");
                    counter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String htmlText = "<html>" + labelText.toString() + "</html>";

        textField.setText("<html><body style='width: 300px; font-size: 14px;'>" + labelText.toString() + "</body></html>");


        textField.setHorizontalAlignment(SwingConstants.CENTER);

        scrollPane = new JScrollPane(textField);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(title);
        frame.getContentPane().add(scrollPane);

        frame.setVisible(true);
    }
}
