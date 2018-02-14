package com.existentialponytomas.getappprospero.contracts;

import com.existentialponytomas.getappprospero.model.Place;

import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public interface PlacesContract {
    interface View {
        void showDangerPlaces(List<Place> places);
        void showCoolPlaces(List<Place> places);
    }
    interface Presenter {
        void loadDangerPlaces();
        void loadCoolPlaces();
    }
}
