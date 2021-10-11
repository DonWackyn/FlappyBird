import javax.swing.*;

public class Screen extends JFrame {

    public Screen() {
        add(new Panel());

        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        new Screen();
    }
}
