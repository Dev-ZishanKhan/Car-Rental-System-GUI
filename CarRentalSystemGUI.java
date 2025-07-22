import javax.swing.SwingUtilities;

public class CarRentalSystemGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuGUI());
    }
}