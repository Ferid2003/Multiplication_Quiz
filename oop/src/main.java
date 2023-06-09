import javax.swing.*;


public class main {
    public static void main(String[] args) {

        DataBase db = new DataBase();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginPage(db.getData());
            }
        });
    }
}
