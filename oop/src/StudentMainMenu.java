import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StudentMainMenu {

    JFrame frame = new JFrame("Student Login Page");
    JButton quiz = new JButton("Take Quiz");
    JButton history = new JButton("History");
    JButton change_users = new JButton("Change Users");
    JButton scoreboard = new JButton("ScoreBoard");
    String username;

    public StudentMainMenu() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new GridLayout(2, 2));

        quiz.addActionListener(new StudentMainMenu.toQuiz());
        //history.addActionListener();
        change_users.addActionListener(new StudentMainMenu.toLoginPage());
        scoreboard.addActionListener(new StudentMainMenu.toScoreBoard());

        frame.add(quiz);
        frame.add(history);
        frame.add(change_users);
        frame.add(scoreboard);
    }

    public StudentMainMenu(String username) {
        this.username = username;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new GridLayout(2, 2));

        quiz.addActionListener(new StudentMainMenu.toQuiz());
        history.addActionListener(new StudentMainMenu.toHistory());
        change_users.addActionListener(new StudentMainMenu.toLoginPage());
        scoreboard.addActionListener(new StudentMainMenu.toScoreBoard());

        frame.add(quiz);
        frame.add(history);
        frame.add(change_users);
        frame.add(scoreboard);

        frame.setVisible(true);
    }

    public class toLoginPage implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            frame.dispose();
            DataBase dataBase = new DataBase();
            LoginPage loginPage = new LoginPage(dataBase.getData());
        }
    }

    public class toScoreBoard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Scoreboard scoreboard = new Scoreboard(10);
        }
    }

    public class toQuiz implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            frame.dispose();
            try {
                MultiplicationQuiz multiplicationQuiz = new MultiplicationQuiz(username);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class toHistory implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            history history1 = new history(username);
        }
    }
}
