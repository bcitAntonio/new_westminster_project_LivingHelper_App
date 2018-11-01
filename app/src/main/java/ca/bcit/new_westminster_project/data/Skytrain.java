package ca.bcit.new_westminster_project.data;


import com.google.gson.annotations.SerializedName;


public class Skytrain {
    private String name;
    private String type;
    private Feature[] features;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Feature[] getFeatures() {
        return features;
    }

    public static class Feature {
        private String type;
        private Geometry geometry;
        private Properties properties;

        public String getType() {
            return type;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public Properties getProperties() {
            return properties;
        }

        public static class Geometry {
            private String type;
            private double[] coordinates;

            public String getType() {
                return type;
            }

            public double[] getCoordinates() {
                return coordinates;
            }

        }

        public static class Properties {
            @SerializedName("stop_name") private String stopName;
            @SerializedName("NAME") private String name;
            @SerializedName("Y") private String y;
            @SerializedName("X") private String x;
            @SerializedName("ID") private String id;
            @SerializedName("ADDR_NUM") private String addressNum;
            @SerializedName("ADDR_ROAD") private String addressRoad;
            @SerializedName("CITY") private String city;
            private float colour;

            public float getColour() {return colour;}

            public void setColour(float colour) {this.colour = colour;}

            public String getStopName() {
                return stopName;
            }

            public void setStopName(String stopName) {
                this.stopName = stopName;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAddressNum() {
                return addressNum;
            }

            public void setAddressNum(String addressNum) {
                this.addressNum = addressNum;
            }

            public String getAddressRoad() {
                return addressRoad;
            }

            public void setAddressRoad(String addressRoad) {
                this.addressRoad = addressRoad;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }
    }
}
