package ca.bcit.new_westminster_project.data;

import com.google.gson.annotations.SerializedName;


public class JsonfileTwo
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

        public static class Properties
        {
            @SerializedName("Name") private String name;
            @SerializedName("description") private String description;
            @SerializedName("tessellate") private String tessellate;
            @SerializedName("visibility") private String visibility;
            @SerializedName("Field_8") private String nName;
            @SerializedName("Field_6") private String num_res;
            @SerializedName("Field_7") private String neighbourHood;
            @SerializedName("Field_4") private String streetName;
            @SerializedName("Field_5") private String buildingName;
            @SerializedName("Field_2") private String mapRef;
            @SerializedName("Field_3") private String streetNum;
            @SerializedName("Field_1") private String buildingId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTessellate() {
                return tessellate;
            }

            public void setTessellate(String tessellate) {
                this.tessellate = tessellate;
            }

            public String getVisibility() {
                return visibility;
            }

            public void setVisibility(String visibility) {
                this.visibility = visibility;
            }

            public String getnName() {
                return nName;
            }

            public void setnName(String nName) {
                this.nName = nName;
            }

            public String getNum_res() {
                return num_res;
            }

            public void setNum_res(String num_res) {
                this.num_res = num_res;
            }

            public String getNeighbourHood() {
                return neighbourHood;
            }

            public void setNeighbourHood(String neighbourHood) {
                this.neighbourHood = neighbourHood;
            }

            public String getStreetName() {
                return streetName;
            }

            public void setStreetName(String streetName) {
                this.streetName = streetName;
            }

            public String getBuildingName() {
                return buildingName;
            }

            public void setBuildingName(String buildingName) {
                this.buildingName = buildingName;
            }

            public String getMapRef() {
                return mapRef;
            }

            public void setMapRef(String mapRef) {
                this.mapRef = mapRef;
            }

            public String getStreetNum() {
                return streetNum;
            }

            public void setStreetNum(String streetNum) {
                this.streetNum = streetNum;
            }

            public String getBuildingId() {
                return buildingId;
            }

            public void setBuildingId(String buildingId) {
                this.buildingId = buildingId;
            }
        }

        public static class Geometry
        {
            private String type;
            private double[][][] coordinates;

            public String getType()
            {
                return type;
            }


            public double[][]  getCoordinates()
            {
                return coordinates[0];
            }

            public double[]  getFirstCoordinates()
            {
                return coordinates[0][0];
            }

        }
    }
}
