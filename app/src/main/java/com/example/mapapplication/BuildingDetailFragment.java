package com.example.mapapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BuildingDetailFragment extends Fragment {
    private CampusBuilding building;

    public static BuildingDetailFragment newInstance(CampusBuilding building) {
        BuildingDetailFragment fragment = new BuildingDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("building", building);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            building = (CampusBuilding) getArguments().getSerializable("building");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_building_detail, container, false);
        TextView textView = view.findViewById(R.id.buildingDescription);
        textView.setText(building.getDescription());
        return view;
    }
}
