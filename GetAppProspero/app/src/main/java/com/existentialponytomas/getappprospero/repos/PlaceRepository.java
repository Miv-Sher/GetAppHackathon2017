package com.existentialponytomas.getappprospero.repos;

import com.existentialponytomas.getappprospero.model.Place;

import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public interface PlaceRepository {
    List<Place> getDangerPlaces();
    List<Place> getCoolPlaces();
}
