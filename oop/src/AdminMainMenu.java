import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class AdminMainMenu {

    JFrame frame = new JFrame("Admin Login Page");
    JButton quiz = new JButton("Take Quiz");
    JButton settings = new JButton("Change Parameters");
    JButton change_users = new JButton("Change Users");
    JButton quit = new JButton("View ScoreBoard");
    JButton history = new JButton("History");
    HashMap<String,String> data;

    public AdminMainMenu(HashMap<String, String> data) {
        this.data = data;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new GridLayout(3, 2));

        quiz.addActionListener(new AdminMainMenu.toQuiz());
        settings.addActionListener(new AdminMainMenu.changeSettings());
        change_users.addActionListener(new AdminMainMenu.toLoginPage());
        quit.addActionListener(new AdminMainMenu.toScoreBoard());
        history.addActionListener(new AdminMainMenu.toHistory());


        frame.add(quiz);
        frame.add(settings);
        frame.add(change_users);
        frame.add(quit);
        frame.add(history);

        frame.setVisible(true);
    }


    public class toQuiz implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            try {
                MultiplicationQuiz multQuiz = new MultiplicationQuiz("admin");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class toLoginPage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            frame.dispose();
            LoginPage loginPage = new LoginPage(data);
        }
    }

    public class changeSettings implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            frame.dispose();
            paramChange paramChange = new paramChange();
        }
    }


    public class toScoreBoard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Scoreboard scoreboard = new Scoreboard(10);
        }
    }

    public class toHistory implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            history history1 = new history("admin");
        }
    }
}

