import javax.swing.JOptionPane;

class BookingQueue {
    int front, rear, size, capacity, queue[];

    BookingQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = capacity - 1;
        size = 0;
    }

    void enqueue(int customerId) {
        if (size == capacity) {
            JOptionPane.showMessageDialog(null, "Waitlist Full!");
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = customerId;
        size++;
        JOptionPane.showMessageDialog(null, "Customer " + customerId + " added to waitlist.");
    }

    void displayQueue() {
        if (size == 0) {
            JOptionPane.showMessageDialog(null, "Waitlist is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("Waitlist Customer IDs: ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            sb.append(queue[index]).append(" ");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}