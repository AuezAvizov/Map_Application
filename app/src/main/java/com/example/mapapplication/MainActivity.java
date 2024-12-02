package com.example.mapapplication;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapview);
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        GeoPoint startPoint = new GeoPoint(51.186920, 71.409717);
        mapView.getController().setCenter(startPoint);
        mapView.getController().setZoom(15);

        addMarker("Военная кафедра", 51.187306, 71.410942);
        addMarker("Главный корпус", 51.186920, 71.409717);
        addMarker("Био корпус", 51.187944, 71.409579);
        addMarker("Старый тех корпус", 51.187625, 71.411373);
        addMarker("Новый тех корпус", 51.187086, 71.411717);
        addMarker("Управление Земельными Ресурсами Архитектура и Дизайн", 51.186499, 71.415311);
        addMarker("Общежитие 7", 51.186302, 71.412643);
        addMarker("Поликлинника/Общежитие 2А", 51.186364, 71.413280);
        addMarker("Агрономический корпус", 51.185989, 71.410354);

    }

    private void addMarker(String name, double latitude, double longitude) {
        GeoPoint point = new GeoPoint(latitude, longitude);
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setTitle(name);
        mapView.getOverlays().add(marker);

        marker.setOnMarkerClickListener((marker1, mapView1) -> {
            CampusBuilding building = CampusBuilding.getBuildingByName(name);
            if (building != null) {
                BuildingDetailFragment fragment = BuildingDetailFragment.newInstance(building);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
            return true;
        });
    }
}
