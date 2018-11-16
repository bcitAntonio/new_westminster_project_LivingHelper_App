package ca.bcit.new_westminster_project;

public class CustomObject {

    private String address;
    private String info;

    public CustomObject(String address, String info) {
            this.address = address;
            this.info = info;
    }

    public String getAddress() {
        return this.address;
    }

    public String getInfo() {
        return this.info;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}


