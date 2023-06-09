import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MultiplicationQuiz  {
    private JFrame frame;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel timerLabel;
    private JLabel scoreLabel;

    private int currentQuestion;
    private int numCorrectAnswers;
    private int numTotalQuestions;
    private TimerListener timerListener;
    private int operand1;
    private int operand2;

    private Scoreboard scoreboard;

    int questionNumber;
    String fileName = "set.txt";
    String scoreboardtxt = "scoreboard.txt";
    String scoreboard2txt = "scoreboard2.txt";
    String qaqa = "qaqa.txt";
    String rafiq = "rafiq.txt";
    String bob = "bob.txt";
    String uni;
    String username;






    public MultiplicationQuiz() throws IOException {







        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String lastLine = null;

            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }

            if (lastLine != null) {
                questionNumber = Integer.parseInt(lastLine);
            } else {
                questionNumber = 10;
            }

        } catch (FileNotFoundException e) {
            questionNumber = 10;
        }









        frame = new JFrame("Multiplication Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(5, 1));

        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.CENTER);

        answerField = new JTextField();
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.addActionListener(new AnswerListener());

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new AnswerListener());

        timerLabel = new JLabel();
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        scoreLabel = new JLabel();
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        frame.add(questionLabel);
        frame.add(answerField);
        frame.add(submitButton);
        frame.add(timerLabel);
        frame.add(scoreLabel);

        currentQuestion = 1;
        numCorrectAnswers = 0;
        numTotalQuestions = 0;



        timerListener = new TimerListener();

        frame.setVisible(true);

        showNextQuestion();
    }

    public MultiplicationQuiz(String username) throws IOException {






        this.username = username;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String lastLine = null;

            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }

            if (lastLine != null) {
                questionNumber = Integer.parseInt(lastLine);
            } else {
                questionNumber = 10;
            }

        } catch (FileNotFoundException e) {
            questionNumber = 10;
        }








        frame = new JFrame("Multiplication Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(5, 1));

        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.CENTER);

        answerField = new JTextField();
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.addActionListener(new AnswerListener());

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new AnswerListener());

        timerLabel = new JLabel();
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        scoreLabel = new JLabel();
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        frame.add(questionLabel);
        frame.add(answerField);
        frame.add(submitButton);
        frame.add(timerLabel);
        frame.add(scoreLabel);

        currentQuestion = 1;
        numCorrectAnswers = 0;
        numTotalQuestions = 0;



        timerListener = new TimerListener();

        frame.setVisible(true);

        showNextQuestion();
    }

    private void showNextQuestion() throws IOException {
        if (currentQuestion <= questionNumber) {
            operand1 = (int) (Math.random() * 10) + 1;
            operand2 = (int) (Math.random() * 10) + 1;
            int correctAnswer = operand1 * operand2;


            questionLabel.setText("Question " + currentQuestion + ": What is " + operand1 + " * " + operand2 + "?");
            answerField.setText("");
            answerField.requestFocus();

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scoreboardtxt, true))) {

                bufferedWriter.write(String.valueOf(timerLabel.getText()));
                bufferedWriter.newLine();
            } catch (IOException a) {
                a.printStackTrace();
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scoreboardtxt, true))) {

                bufferedWriter.write("Question " + currentQuestion + ": What is " + operand1 + " * " + operand2 + "?");
                bufferedWriter.newLine();
            } catch (IOException a) {
                a.printStackTrace();
            }
            uni = username+".txt";
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(uni, true))) {

                bufferedWriter.write(String.valueOf(timerLabel.getText()));
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            } catch (IOException a) {
                a.printStackTrace();
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(uni, true))) {

                bufferedWriter.write("Question " + currentQuestion + ": What is " + operand1 + " * " + operand2 + "?");
                bufferedWriter.newLine();
            } catch (IOException a) {
                a.printStackTrace();
            }


            timerListener.startTimer(); // Start the timer once when the question is displayed

            currentQuestion++;
            numTotalQuestions++;
        } else {

            //buffer.close();
            double score = (double) numCorrectAnswers / numTotalQuestions * 100;
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scoreboardtxt, true))) {

                bufferedWriter.write(String.valueOf(timerLabel.getText()));
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(score));
                bufferedWriter.newLine();
                bufferedWriter.write(username);
                bufferedWriter.newLine();
            } catch (IOException a) {
                a.printStackTrace();
            }
            uni = username+".txt";
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(uni, true))) {

                bufferedWriter.write(String.valueOf(timerLabel.getText()));
                bufferedWriter.newLine();
            } catch (IOException a) {
                a.printStackTrace();
            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scoreboard2txt, true))) {

                bufferedWriter.write(String.valueOf(score));
                bufferedWriter.write(":");
                bufferedWriter.write(username);
                bufferedWriter.newLine();
            } catch (IOException a) {
                a.printStackTrace();
            }

            String message = "Quiz complete!\nYou answered " + numCorrectAnswers + " out of " + numTotalQuestions +
                    " questions correctly.\nScore: " + String.format("%.2f", score) + "%";
            JOptionPane.showMessageDialog(frame, message);


            int option = JOptionPane.showConfirmDialog(frame, "Do you want to repeat the program?", "Repeat Program",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                resetQuiz();
            } else {
                DataBase db = new DataBase();
                if (Objects.equals(username, "admin")){
                    AdminMainMenu adminMainMenu = new AdminMainMenu(db.getData());
                }else {
                    StudentMainMenu studentMainMenu = new StudentMainMenu(username);
                }
                frame.dispose();
            }
        }
    }



    private class AnswerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            timerListener.stopTimer();
            int userAnswer = Integer.parseInt(answerField.getText());
            int correctAnswer = operand1 * operand2;

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scoreboardtxt, true))) {

                bufferedWriter.write("Your answer " + userAnswer);
                bufferedWriter.newLine();
            } catch (IOException a) {
                a.printStackTrace();
            }
            uni = username+".txt";
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(uni, true))) {

                bufferedWriter.write("Your answer " + userAnswer);
                bufferedWriter.newLine();
            } catch (IOException a) {
                a.printStackTrace();
            }

            if (userAnswer == correctAnswer) {
                JOptionPane.showMessageDialog(frame, "Correct!");

                numCorrectAnswers++;
            } else {

                JOptionPane.showMessageDialog(frame, "Incorrect. The correct answer is " + correctAnswer + ".");

            }

            try {
                showNextQuestion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class TimerListener implements ActionListener {
        private long startTime;

        public void startTimer() {
            startTime = System.currentTimeMillis();
            Timer timer = new Timer(1000, this);
            timer.start();
        }

        public void stopTimer() {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = (currentTime - startTime) / 1000;

            timerLabel.setText("Time elapsed: " + elapsedTime + " seconds");

        }

    }



    private void resetQuiz() throws IOException {
        currentQuestion = 1;
        numCorrectAnswers = 0;
        numTotalQuestions = 0;
        scoreLabel.setText("");
        showNextQuestion();
    }


}
