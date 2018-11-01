package ca.bcit.new_westminster_project.data;


import com.google.gson.annotations.SerializedName;


public class JsonFile
{
    private String name;
    private String type;
    private Feature[] features;

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public Feature[] getFeatures()
    {
        return features;
    }

    public static class Feature
    {
        private String type;

        private Properties properties;

        public String getType()
        {
            return type;
        }



        public Properties getProperties()
        {
            return properties;
        }



        public static class Properties
        {
            @SerializedName("stop_name") private String stopName;           //bus stop name
            @SerializedName("NAME") private String name;                    //Skytrain name
            @SerializedName("BLDGNAM") private String buildingName;         //care home, school, hospital name
            @SerializedName("PARK") private String parkName;                //Playground name


            @SerializedName("Y") private String y;                          //all y coordinate
            @SerializedName("X") private String x;                          //all x coordinate
            @SerializedName("ID") private String id;
            @SerializedName("ADDR_NUM") private String addressNum;
            @SerializedName("ADDR_ROAD") private String addressRoad;
            @SerializedName("CITY") private String city;

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

            public String getStopName() { return stopName; }

            public void setStopName(String stopName) { this.stopName = stopName; }

            public String getBuildingName() {
                return buildingName;
            }

            public void setBuildingName(String buildingName) {
                this.buildingName = buildingName;
            }

            public String getParkName() {
                return parkName;
            }

            public void setParkName(String parkName) {
                this.parkName = parkName;
            }
        }
    }
}
