package model;

import controller.DatabaseConnector;

import java.util.ArrayList;

/**
 * Created by NortonWEI on 3/3/2017.
 */
public class User {
    private int userId = 0;
    private String name = "";
    private String username = "";
    private String password = "";
    private String birthday = "";
    private int gender = -1;
    private int tel = -1;

    public User(String name, String username, String password, String birthday, int gender, int tel) {
        setId();
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.tel = tel;
    }

    public User(int userId, String name, String username, String password, String birthday, int gender, int tel) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.tel = tel;
    }

    private void setId() {
        boolean ifBreak = false;
        DatabaseConnector databaseConnector = new DatabaseConnector();
        ArrayList<Integer> userIdList = databaseConnector.listId("USER");
        for (int i=0; i<userIdList.size(); i++) {
            if (userIdList.get(i) != i) {
                userId = i;
                ifBreak = true;
                break;
            }
        }
        if (!ifBreak) {
            if (!userIdList.isEmpty()) {
                userId = userIdList.get(userIdList.size()-1) + 1;
            }
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getGender() {
        return gender;
    }

    public int getTel() {
        return tel;
    }

    public User getCurrentUser() {
        return this;
    }
}
