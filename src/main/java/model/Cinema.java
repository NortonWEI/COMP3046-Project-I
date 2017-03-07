package model;

import controller.DatabaseConnector;

import java.util.ArrayList;

/**
 * Created by NortonWEI on 3/3/2017.
 */
public class Cinema {
    private int cinemaId = 0;
    private String name = "";
    private int houseQuantity = -1;
    private String location = "";
    private int tel = -1;

    public Cinema(String name, int houseQuantity, String location, int tel) {
        setId();
        this.name = name;
        this.houseQuantity = houseQuantity;
        this.location = location;
        this.tel = tel;
    }

    public Cinema(int cinemaId, String name, int houseQuantity, String location, int tel) {
        this.cinemaId = cinemaId;
        this.name = name;
        this.houseQuantity = houseQuantity;
        this.location = location;
        this.tel = tel;
    }

    private void setId() {
        boolean ifBreak = false;
        DatabaseConnector databaseConnector = new DatabaseConnector();
        ArrayList<Integer> cinemaIdList = databaseConnector.listId("CINEMA");
        for (int i=0; i<cinemaIdList.size(); i++) {
            if (cinemaIdList.get(i) != i) {
                cinemaId = i;
                ifBreak = true;
                break;
            }
        }
        if (!ifBreak) {
            if (!cinemaIdList.isEmpty()) {
                cinemaId = cinemaIdList.get(cinemaIdList.size()-1) + 1;
            }
        }
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public int getHouseQuantity() {
        return houseQuantity;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getTel() {
        return tel;
    }
}
