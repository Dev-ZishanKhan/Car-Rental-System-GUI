import javax.swing.*;
import java.awt.*;

class AdminPanelGUI extends JFrame {
    public AdminPanelGUI() {
        setTitle("Admin Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        JLabel label = new JLabel("Admin Menu", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JButton addCarBtn = new JButton("Add Car");
        JButton showCarsBtn = new JButton("Show All Cars");
        JButton bookedCarsBtn = new JButton("View Booked Cars");
        JButton waitlistBtn = new JButton("View Waitlist");
        JButton backBtn = new JButton("Back to Main Menu");

        addCarBtn.addActionListener(e -> {
            JTextField id = new JTextField();
            JTextField name = new JTextField();
            JTextField model = new JTextField();
            JTextField seat = new JTextField();
            JTextField rent = new JTextField();
            JTextField owner = new JTextField();
            Object[] message = {
                "Car ID:", id,
                "Name:", name,
                "Model:", model,
                "Seating Capacity:", seat,
                "Rent per Hour:", rent,
                "Owner Name:", owner
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Add Car", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int carId = Integer.parseInt(id.getText());
                    double rentPerHour = Double.parseDouble(rent.getText());
                    int seating = Integer.parseInt(seat.getText());
                    for (int i = 0; i < MainMenuGUI.carCount; i++) {
                        if (MainMenuGUI.cars[i].carId == carId) {
                            JOptionPane.showMessageDialog(this, "Car ID already exists.");
                            return;
                        }
                    }
                    if (MainMenuGUI.carCount < MainMenuGUI.cars.length) {
                        MainMenuGUI.cars[MainMenuGUI.carCount++] =
                            new Car(carId, name.getText(), model.getText(), seating, rentPerHour, owner.getText());
                        JOptionPane.showMessageDialog(this, "Car Added Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Car Inventory Full.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input!");
                }
            }
        });

        showCarsBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("All Cars:\n");
            for (int i = 0; i < MainMenuGUI.carCount; i++) {
                Car c = MainMenuGUI.cars[i];
                sb.append("ID: ").append(c.carId).append(", ").append(c.name).append(" ").append(c.model)
                  .append(", Rent/hr: $").append(c.rentPerHour)
                  .append(", Available: ").append(c.isAvailable ? "Yes" : "No").append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        });

        bookedCarsBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Booked Cars:\n");
            boolean found = false;
            for (int i = 0; i < MainMenuGUI.carCount; i++) {
                if (!MainMenuGUI.cars[i].isAvailable) {
                    sb.append("ID: ").append(MainMenuGUI.cars[i].carId).append(", Name: ")
                      .append(MainMenuGUI.cars[i].name).append("\n");
                    found = true;
                }
            }
            if (!found) sb.append("None");
            JOptionPane.showMessageDialog(this, sb.toString());
        });

        waitlistBtn.addActionListener(e -> MainMenuGUI.bookingQueue.displayQueue());

        backBtn.addActionListener(e -> {
            dispose();
            new MainMenuGUI();
        });

        add(label);
        add(addCarBtn);
        add(showCarsBtn);
        add(bookedCarsBtn);
        add(waitlistBtn);
        add(backBtn);

        setVisible(true);
    }
}