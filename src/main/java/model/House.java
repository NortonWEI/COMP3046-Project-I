package model;

import controller.DatabaseConnector;

import java.util.ArrayList;

/**
 * Created by NortonWEI on 3/3/2017.
 */
public class House {
    private int houseId = 0;
    private String name = "";
    private int seatQuantity = -1;
    private int cinemaId = -1;

    public House(String name, int seatQuantity, int cinemaId) {
        setId();
        this.name = name;
        this.seatQuantity = seatQuantity;
        this.cinemaId = cinemaId;
    }

    public House(int houseId, String name, int seatQuantity, int cinemaId) {
        this.houseId = houseId;
        this.name = name;
        this.seatQuantity = seatQuantity;
        this.cinemaId = cinemaId;
    }

    private void setId() {
        boolean ifBreak = false;
        DatabaseConnector databaseConnector = new DatabaseConnector();
        ArrayList<Integer> houseIdList = databaseConnector.listId("HOUSE");
        for (int i=0; i<houseIdList.size(); i++) {
            if (houseIdList.get(i) != i) {
                houseId = i;
                ifBreak = true;
                break;
            }
        }
        if (!ifBreak) {
            if (!houseIdList.isEmpty()) {
                houseId = houseIdList.get(houseIdList.size()-1) + 1;
            }
        }
    }

    public int getHouseId() {
        return houseId;
    }

    public String getName() {
        return name;
    }

    public int getSeatQuantity() {
        return seatQuantity;
    }

    public int getCinemaId() {
        return cinemaId;
    }
}
