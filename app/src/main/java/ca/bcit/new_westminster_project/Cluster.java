package ca.bcit.new_westminster_project;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Cluster implements ClusterItem {
    private final LatLng mPosition;
    private  String mTitle;
    private  String mSnippet;

    public Cluster(double lat, double lng,String nm) {
        mPosition = new LatLng(lat, lng);
        mTitle = nm;
    }

    public Cluster(double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
}
