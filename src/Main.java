import com.view.cuestionarios.logIn.SignIn;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignIn signIn = new SignIn();
            signIn.setSize(1200, 560);
            signIn.setVisible(true);
            signIn.setLocationRelativeTo(null);
            signIn.setResizable(false);
            signIn.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        });
    }
}
