package ca.bcit.new_westminster_project.data;


import com.google.gson.annotations.SerializedName;


public class BusStop
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
        private Geometry geometry;
        private Properties properties;

        public String getType()
        {
            return type;
        }

        public Geometry getGeometry()
        {
            return geometry;
        }

        public Properties getProperties()
        {
            return properties;
        }

        public static class Geometry
        {
            private String type;
            private double[] coordinates;

            public String getType()
            {
                return type;
            }

            public double[] getCoordinates()
            {
                return coordinates;
            }

        }

        public static class Properties
        {
            @SerializedName("stop_name") private String stopName;
            @SerializedName("stop_desc") private String stopDesc;
            @SerializedName("zone_id") private String zoneId;
            @SerializedName("wheelchair_boarding") private String wheel;
            @SerializedName("stop_code") private String stopCode;
            @SerializedName("X") private String x;
            @SerializedName("Y") private String y;

            public String getStopName() {
                return stopName;
            }

            public void setStopName(String stopName) {
                this.stopName = stopName;
            }

            public String getStopDesc() {
                return stopDesc;
            }

            public void setStopDesc(String stopDesc) {
                this.stopDesc = stopDesc;
            }

            public String getZoneId() {
                return zoneId;
            }

            public void setZoneId(String zoneId) {
                this.zoneId = zoneId;
            }

            public String getWheel() {
                return wheel;
            }

            public void setWheel(String wheel) {
                this.wheel = wheel;
            }

            public String getStopCode() {
                return stopCode;
            }

            public void setStopCode(String stopCode) {
                this.stopCode = stopCode;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }
        }
    }
}
