package ca.bcit.new_westminster_project;

public class CustomObject {

    private String address;
    private String distance;

    public CustomObject(String address, String distance) {
        this.address = address;
        this.distance = distance;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}


