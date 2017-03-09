package model;

import controller.DatabaseConnector;

import java.util.ArrayList;

/**
 * Created by NortonWEI on 3/3/2017.
 */
public class Schedule {
    private int scheduleId = 0;
    private int movieId = 0;
    private int houseId = 0;
    private String startTime = "";
    private float price = 0;

    public Schedule(String movieName, boolean if3D, String houseName, String startTime, float price) {
        setId();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.movieId = databaseConnector.findMovie(movieName, if3D).getMovieId();
        this.houseId = databaseConnector.findHouse(houseName, movieId).getHouseId();
        this.startTime = startTime;
        this.price = price;
    }

    public Schedule(int scheduleId, int movieId, int houseId, String startTime, float price) {
        this.scheduleId = scheduleId;
        this.movieId = movieId;
        this.houseId = houseId;
        this.startTime = startTime;
        this.price = price;
    }

    private void setId() {
        boolean ifBreak = false;
        DatabaseConnector databaseConnector = new DatabaseConnector();
        ArrayList<Integer> scheduleIdList = databaseConnector.listId("SCHEDULE");
        for (int i=0; i<scheduleIdList.size(); i++) {
            if (scheduleIdList.get(i) != i) {
                scheduleId = i;
                ifBreak = true;
                break;
            }
        }
        if (!ifBreak) {
            if (!scheduleIdList.isEmpty()) {
                scheduleId = scheduleIdList.get(scheduleIdList.size()-1) + 1;
            }
        }
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getHouseId() {
        return houseId;
    }

    public String getStartTime() {
        return startTime;
    }

    public float getPrice() {
        return price;
    }
}
