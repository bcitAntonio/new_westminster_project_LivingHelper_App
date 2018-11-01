package ca.bcit.new_westminster_project.data;

        import com.google.gson.annotations.SerializedName;

public class JsonFileTwo {

    @SerializedName("json_featuretype") private String type;
    @SerializedName("BLDG_ID") private int buildingId;
    @SerializedName("MAPREF") private int mapRefrence;
    @SerializedName("STRNUM") private String streetNumber;
    @SerializedName("STRNAM") private String streetName;
    @SerializedName("BLDGNAM") private String buildingName;
    @SerializedName("NUM_RES") private int numberOfResidents;
    @SerializedName("Ngbrhood") private String neighbourhood;
    @SerializedName("NgbrNam") private String neighbourhoodName;
    @SerializedName("json_geometry") private String geometry;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getMapRefrence() {
        return mapRefrence;
    }

    public void setMapRefrence(int mapRefrence) {
        this.mapRefrence = mapRefrence;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
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

    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public void setNumberOfResidents(int numberOfResidents) {
        this.numberOfResidents = numberOfResidents;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getNeighbourhoodName() {
        return neighbourhoodName;
    }

    public void setNeighbourhoodName(String neighbourhoodName) {
        this.neighbourhoodName = neighbourhoodName;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
}
