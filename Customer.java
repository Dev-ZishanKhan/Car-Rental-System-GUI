class Customer {
    int customerId;
    String name, address, cnic;
    int phone;
    Customer left, right;

    Customer(int id, String name, String cnic, int phone, String address) {
        this.customerId = id;
        this.name = name;
        this.cnic = cnic;
        this.phone = phone;
        this.address = address;
        left = right = null;
    }
}