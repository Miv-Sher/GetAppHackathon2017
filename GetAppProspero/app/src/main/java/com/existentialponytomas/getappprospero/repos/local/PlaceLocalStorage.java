package com.existentialponytomas.getappprospero.repos.local;

import com.existentialponytomas.getappprospero.model.Area;
import com.existentialponytomas.getappprospero.model.GPSCoordinates;
import com.existentialponytomas.getappprospero.model.Place;
import com.existentialponytomas.getappprospero.repos.PlaceRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public class PlaceLocalStorage implements PlaceRepository{

    private static PlaceLocalStorage instance;

    List<Place> places;

    private PlaceLocalStorage(){
        places = new ArrayList<>();
        Area area1 = new Area(new GPSCoordinates(20, 30), 20);
        Area area2 = new Area(new GPSCoordinates(20, 2000), 20);
        Area area3 = new Area(new GPSCoordinates(20, 3000), 20);
        places.add(new Place(area1, "Шлюхи", "Найди девушку", false));
        places.add(new Place(area2, "Выпивка", "Пей молочко", false));
        places.add(new Place(area3, "Кофе", "Забери бесплатное латте!", true));
    }


    public static PlaceLocalStorage getInstance() {
        if (instance == null) {
            instance = new PlaceLocalStorage();
        }
        return  instance;
    }

    @Override
    public List<Place> getDangerPlaces() {
        List<Place> result = new ArrayList<>();
        for (int i =0; i < places.size(); i++) {
            if (places.get(i).isDanger()) {
                result.add(places.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Place> getCoolPlaces() {
        List<Place> result = new ArrayList<>();
        for (int i =0; i < places.size(); i++) {
            if (places.get(i).isCool()) {
                result.add(places.get(i));
            }
        }
        return result;
    }
}
