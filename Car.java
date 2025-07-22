class Car {
    int carId;
    String name, model, ownerName;
    int seatingCapacity;
    double rentPerHour;
    boolean isAvailable;

    Car(int carId, String name, String model, int seatingCapacity, double rentPerHour, String ownerName) {
        this.carId = carId;
        this.name = name;
        this.model = model;
        this.seatingCapacity = seatingCapacity;
        this.rentPerHour = rentPerHour;
        this.ownerName = ownerName;
        this.isAvailable = true;
    }
}