class CustomerBST {
    Customer root;

    void addCustomer(int id, String name, String cnic, int phone, String address) {
        root = insertRec(root, id, name, cnic, phone, address);
    }

    Customer insertRec(Customer root, int id, String name, String cnic, int phone, String address) {
        if (root == null)
            return new Customer(id, name, cnic, phone, address);
        if (id < root.customerId)
            root.left = insertRec(root.left, id, name, cnic, phone, address);
        else if (id > root.customerId)
            root.right = insertRec(root.right, id, name, cnic, phone, address);
        return root;
    }

    boolean searchCustomer(int id) {
        return searchRec(root, id);
    }

    boolean searchRec(Customer root, int id) {
        if (root == null)
            return false;
        if (root.customerId == id)
            return true;
        return id < root.customerId ? searchRec(root.left, id) : searchRec(root.right, id);
    }
}