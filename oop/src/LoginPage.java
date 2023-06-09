import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.EventListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    HashMap<String,String> data;


    JFrame frame = new JFrame("Login Page");
    JButton login = new JButton("Login");
    static JTextField userName = new JTextField();
    JPasswordField password = new JPasswordField();
    JLabel userNameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");

    public LoginPage(HashMap<String, String> data) {
        this.data = data;


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new GridLayout(3, 1));

        userNameLabel.setHorizontalAlignment(JLabel.CENTER);


        userName.setHorizontalAlignment(JTextField.CENTER);
        userName.addActionListener(new LoginPage.AnswerListener());


        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        password.setHorizontalAlignment(JTextField.CENTER);
        password.addActionListener(new LoginPage.AnswerListener());


        login.addActionListener(new LoginPage.AnswerListener());



        frame.add(userNameLabel);
        frame.add(userName);
        frame.add(passwordLabel);
        frame.add(password);
        frame.add(login);



        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class AnswerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {




            if (e.getSource()==login){
                String username = userName.getText();
                String passWord = String.valueOf(password.getPassword());

                if (data.containsKey(username)){
                    if (data.get(username).equals(passWord)){
                        if (username.equals("admin")){
                            JOptionPane.showMessageDialog(frame, "Welcome!");
                            frame.setVisible(false);
                            frame.dispose();
                            AdminMainMenu adminMainMenu = new AdminMainMenu(data);
                        }else {
                            JOptionPane.showMessageDialog(frame, "Welcome!");
                            frame.setVisible(false);
                            frame.dispose();
                            StudentMainMenu studentMainMenu = new StudentMainMenu(username);
                        }
                    }else {
                        JOptionPane.showMessageDialog(frame, "Wrong Password!");
                    }
                }
            }
        }
    }



}
