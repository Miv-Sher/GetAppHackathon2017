package com.existentialponytomas.getappprospero.presenters;

import com.existentialponytomas.getappprospero.contracts.PlacesContract;
import com.existentialponytomas.getappprospero.repos.PlaceRepository;

/**
 * Created by Anna on 4/1/2017.
 */

public class PlacesPresenter implements PlacesContract.Presenter {

    PlaceRepository placeRepository;
    PlacesContract.View view;

    public PlacesPresenter(PlacesContract.View view, PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.view = view;
    }

    @Override
    public void loadDangerPlaces() {
        view.showDangerPlaces(placeRepository.getDangerPlaces());
    }

    @Override
    public void loadCoolPlaces() {
        view.showCoolPlaces(placeRepository.getCoolPlaces());
    }
}
