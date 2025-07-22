import javax.swing.*;
import java.awt.*;

class MainMenuGUI extends JFrame {
    public static Car[] cars = new Car[10];
    public static int carCount = 5;
    public static CustomerBST customerTree = new CustomerBST();
    public static BookingQueue bookingQueue = new BookingQueue(5);
    public static int autoCustomerId = 1001;

    static {
        cars[0] = new Car(101, "Toyota", "Corolla", 5, 15.0, "Ali Khan");
        cars[1] = new Car(102, "Honda", "Civic", 5, 18.0, "Ahmed Raza");
        cars[2] = new Car(103, "Suzuki", "Swift", 4, 10.0, "Sara Ahmed");
        cars[3] = new Car(104, "Kia", "Sportage", 7, 25.0, "Zeeshan Khan");
        cars[4] = new Car(105, "Hyundai", "Elantra", 5, 17.5, "Usman Ali");
    }

    public MainMenuGUI() {
        setTitle("Car Rental Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel title = new JLabel("Select User Type", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JButton adminBtn = new JButton("Admin");
        JButton customerBtn = new JButton("Customer");
        JButton exitBtn = new JButton("Exit");

        adminBtn.addActionListener(e -> {
            dispose();
            new AdminPanelGUI();
        });

        customerBtn.addActionListener(e -> {
            dispose();
            new CustomerPanelGUI();
        });

        exitBtn.addActionListener(e -> System.exit(0));

        add(title);
        add(adminBtn);
        add(customerBtn);
        add(exitBtn);

        setVisible(true);
    }
}