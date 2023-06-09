import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class paramChange {


    JFrame frame = new JFrame("Param window");
    JLabel label = new JLabel("How many questions do you want?");
    JTextField textField = new JTextField();
    JButton button = new JButton("Submit");
    String fileName = "set.txt";

    public paramChange() {



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new GridLayout(3, 1));

        button.addActionListener(new paramChange.writeToTxt());

        frame.add(label);
        frame.add(textField);
        frame.add(button);

        frame.setVisible(true);
    }

    public class writeToTxt implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {

                bufferedWriter.write(textField.getText());
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException a) {
                a.printStackTrace();
            }


            frame.setVisible(false);
            frame.dispose();
            DataBase db = new DataBase();
            AdminMainMenu adminMainMenu = new AdminMainMenu(db.getData());
        }
    }
}
