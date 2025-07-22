import javax.swing.*;
import java.awt.*;

class CustomerPanelGUI extends JFrame {
    public CustomerPanelGUI() {
        setTitle("Customer Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel label = new JLabel("Customer Menu", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JButton newCustBtn = new JButton("New Customer Booking");
        JButton existingCustBtn = new JButton("Existing Customer Booking");
        JButton backBtn = new JButton("Back");

        newCustBtn.addActionListener(e -> {
            JTextField name = new JTextField();
            JTextField phone = new JTextField();
            JTextField address = new JTextField();
            JTextField cnic = new JTextField();
            Object[] message = {
                "Name:", name,
                "CNIC:", cnic,
                "Phone:", phone,
                "Address:", address
            };
            int option = JOptionPane.showConfirmDialog(null, message, "New Customer", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int id = MainMenuGUI.autoCustomerId++;
                try {
                    MainMenuGUI.customerTree.addCustomer(id, name.getText(), cnic.getText(),
                        Integer.parseInt(phone.getText()), address.getText());
                    JOptionPane.showMessageDialog(this, "Registered! Your ID: " + id);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input.");
                    return;
                }
                bookCar(id);
            }
        });

        existingCustBtn.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Enter Your Customer ID:");
            try {
                int id = Integer.parseInt(idStr);
                if (MainMenuGUI.customerTree.searchCustomer(id)) {
                    bookCar(id);
                } else {
                    JOptionPane.showMessageDialog(this, "Customer ID not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new MainMenuGUI();
        });

        add(label);
        add(newCustBtn);
        add(existingCustBtn);
        add(backBtn);

        setVisible(true);
    }

    private void bookCar(int customerId) {
        String carList = "Available Cars:\n";
        for (int i = 0; i < MainMenuGUI.carCount; i++) {
            Car c = MainMenuGUI.cars[i];
            if (c.isAvailable) {
                carList += "ID: " + c.carId + ", " + c.name + " " + c.model + ", Rent/hr: $" + c.rentPerHour + "\n";
            }
        }
        String carIdStr = JOptionPane.showInputDialog(carList + "\nEnter Car ID to book:");
        try {
            int carId = Integer.parseInt(carIdStr);
            for (int i = 0; i < MainMenuGUI.carCount; i++) {
                if (MainMenuGUI.cars[i].carId == carId) {
                    if (MainMenuGUI.cars[i].isAvailable) {
                        MainMenuGUI.cars[i].isAvailable = false;
                        JOptionPane.showMessageDialog(this, "Car booked! Rent/hr: $" + MainMenuGUI.cars[i].rentPerHour);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this, "Car already booked. Adding to waitlist.");
                        MainMenuGUI.bookingQueue.enqueue(customerId);
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Car ID not found.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }
}