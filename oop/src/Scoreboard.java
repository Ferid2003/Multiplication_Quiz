import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class Scoreboard {
    private List<Double> scores;
    private List<String> usernames;
    private List<Double> Topscores;
    private List<String> Topusernames;
    private int maxScores;
    private HashMap<Double, String> hashMap;
    JFrame frame;
    JLabel title;
    JLabel textField;




    public Scoreboard(int maxScores) {

        this.scores = new ArrayList<>();
        this.usernames = new ArrayList<>();
        hashMap = new HashMap<>();


        try (BufferedReader reader = new BufferedReader(new FileReader("scoreboard2.txt"))) {
            String line;
            int linecount = 0;
            while ((line = reader.readLine()) != null) {

                double num = Double.parseDouble(line.split(":")[0]);
                String name = line.split(":")[1];

                // Populate the HashMap with key-value pairs
                try {
                    hashMap.put(num,name);
                }catch (Exception e){
                    System.out.println("sui");
                }
                // Convert the HashMap entries to a List

                linecount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map.Entry<Double, String>> entryList = new ArrayList<>(hashMap.entrySet());

        // Sort the list based on keys using a Comparator
        Collections.sort(entryList, Comparator.comparingDouble((Map.Entry<Double, String> entry) -> entry.getKey()).reversed());


        /*if (linecount%2==0) {
                    double number = Double.parseDouble(line);
                    scores.add(number);
                }else {
                    usernames.add(line);
                }*/

        Collections.sort(scores, Collections.reverseOrder());

        // Create a new list to store the top 10 values
        Topscores = new ArrayList<>();
        Topusernames = new ArrayList<>();


        // Retrieve the top 10 values and add them to the top10List
        for (int i = 0; i < Math.min(scores.size(), 10); i++) {
            Topscores.add(scores.get(i));
        }


        this.maxScores = maxScores;
        frame = new JFrame("Score Board");
        title = new JLabel();
        textField = new JLabel();


        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);


        title.setHorizontalAlignment(JLabel.CENTER);




        StringBuilder labelText = new StringBuilder();

        labelText.append("Score Board").append("<br>");
        /*for (int i = 0; i < Topscores.size(); i++) {
            Double s = Topscores.get(i);
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            String formattedValue = decimalFormat.format(s);
            String username = usernames.get(i);
            labelText.append(i+1+" ").append(username).append(" : ").append(formattedValue).append("<br>");

        }*/

        int counter = 0;
        for (Map.Entry<Double, String> entry : entryList) {
            Double s = entry.getKey();
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            String formattedValue = decimalFormat.format(s);
            String username = entry.getValue();
            labelText.append(counter+1+" ").append(username).append(" : ").append(formattedValue).append("<br>");


            counter++;
            if (counter == 10) {
                break;
            }
        }

        String htmlText = "<html>" + labelText.toString() + "</html>";
        textField.setText(htmlText);
        textField.setHorizontalAlignment(SwingConstants.CENTER);




        frame.add(title);
        frame.getContentPane().add(textField);



        frame.setVisible(true);

    }


}
