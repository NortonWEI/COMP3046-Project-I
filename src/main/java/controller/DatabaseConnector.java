package controller;

import model.*;

import java.sql.*;
import java.util.*;

/**
 * Created by NortonWEI on 2/3/2017.
 */
public class DatabaseConnector {
    private String username = "";
    private String password = "";
    private Connection connection;

    public DatabaseConnector() {
        this.username = "root";
        this.password = "";
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            String dbUrl = "jdbc:mysql://101.78.175.101:3380/movie_ticketing_system_db";
            connection = DriverManager
                    .getConnection(dbUrl,username, password);
            System.out.println("Database connected to " + dbUrl);
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return;
        }
    }

    /** ID List */
    public ArrayList<Integer> listId(String databaseName) {
        ArrayList<Integer> userIdList = new ArrayList<Integer>();
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT ID FROM " + databaseName;
            ResultSet resultSet = statement.executeQuery(sqlStr);

            while (resultSet.next()) {
                userIdList.add(resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return userIdList;
    }

    /** User Operation */
    public void createUser(User user) {
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "INSERT INTO USER VALUES (" +
                    user.getUserId() + ", " +
                    "'" + user.getName() + "', " +
                    "'" + user.getUsername() + "', " +
                    "'" + user.getPassword() + "', " +
                    "str_to_date('" + user.getBirthday() + "', '%d-%m-%Y'), " +
                    user.getGender() + ", " +
                    user.getTel() +
                    ")";
            statement.executeUpdate(sqlStr);
            statement.close();

            System.out.println("Added user with id: " + user.getUserId());
        } catch (SQLException e) {
            System.out.println("Create user failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public User findUser(String username) {
        User foundUser = null;
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT * FROM USER " +
                    "WHERE USERNAME = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sqlStr);
            if (resultSet.next()) {
                System.out.println("Found user with id: " + resultSet.getInt(1));
                foundUser = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7));
            } else {
                System.out.println("User not found!");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Find user failed!");
            e.printStackTrace();
        }
        close();
        return foundUser;
    }

    public void updateUser(String name, String username, String password, String birthday, int gender, int tel) {
        User foundUser = findUser(username);
        connect();
        try {
            if (foundUser != null) {
                Statement statement = connection.createStatement();
                String sqlStr = "UPDATE USER " +
                        "SET NAME = '" +
                        name + "', PASSWORD = '" +
                        password + "', BIRTHDAY = str_to_date('" +
                        birthday + "', '%d-%m-%Y'), GENDER = " +
                        gender + ", TEL = " +
                        tel + " " +
                        "WHERE ID = " + foundUser.getUserId();
                statement.executeUpdate(sqlStr);
                statement.close();
                System.out.println("Updated user with id: " + foundUser.getUserId());
            }
        } catch (SQLException e) {
            System.out.println("Update user failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    /** Cinema Operation */
    public void createCinema(Cinema cinema) {
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "INSERT INTO CINEMA VALUES (" +
                    cinema.getCinemaId() + ", " +
                    "'" + cinema.getName() + "', " +
                    cinema.getHouseQuantity() + ", " +
                    "'" + cinema.getLocation() + "', " +
                    cinema.getTel() +
                    ")";
            statement.executeUpdate(sqlStr);
            statement.close();

            System.out.println("Added cinema with id: " + cinema.getCinemaId());
        } catch (SQLException e) {
            System.out.println("Create cinema failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public Cinema findCinema(String name) {
        Cinema foundCinema = null;
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT * FROM CINEMA " +
                    "WHERE NAME = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(sqlStr);
            if (resultSet.next()) {
                System.out.println("Found cinema with id: " + resultSet.getInt(1));
                foundCinema = new Cinema(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5));
            } else {
                System.out.println("Cinema not found!");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Find cinema failed!");
            e.printStackTrace();
        }
        close();
        return foundCinema;
    }

    public void updateCinema(String name, int houseQuantity, String location, int tel) {
        Cinema foundCinema = findCinema(name);
        connect();
        try {
            if (foundCinema != null) {
                Statement statement = connection.createStatement();
                String sqlStr = "UPDATE CINEMA " +
                        "SET NAME = '" +
                        name + "', HOUSE_QUANTITY = " +
                        houseQuantity + ", LOCATION = '" +
                        location + "', TEL = " +
                        tel + " " +
                        "WHERE ID = " + foundCinema.getCinemaId();
                statement.executeUpdate(sqlStr);
                statement.close();
                System.out.println("Updated cinema with id: " + foundCinema.getCinemaId());
            }
        } catch (SQLException e) {
            System.out.println("Update cinema failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public void deleteCinema(String name) {
        Cinema foundCinema = findCinema(name);
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "DELETE FROM CINEMA " +
                    "WHERE ID = " + foundCinema.getCinemaId();
            statement.executeUpdate(sqlStr);
            statement.close();
            System.out.println("Deleted cinema with id: " + foundCinema.getCinemaId());
        } catch (SQLException e) {
            System.out.println("Delete cinema failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    /** House Operation */
    public void createHouse(House house) {
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "INSERT INTO HOUSE VALUES (" +
                    house.getHouseId() + ", " +
                    "'" + house.getName() + "', " +
                    house.getSeatQuantity() + ", " +
                    house.getCinemaId() +
                    ")";
            statement.executeUpdate(sqlStr);
            statement.close();
            System.out.println("Added house with id: " + house.getHouseId());
        } catch (SQLException e) {
            System.out.println("Create hosue failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public House findHouse(String name, int cinemaId) {
        House foundHouse = null;
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT * FROM HOUSE " +
                    "WHERE NAME = '" + name + "' AND CINEMA_ID = " + cinemaId;
            ResultSet resultSet = statement.executeQuery(sqlStr);
            if (resultSet.next()) {
                System.out.println("Found house with id: " + resultSet.getInt(1));
                foundHouse = new House(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getInt(4));
            } else {
                System.out.println("House not found!");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Find house failed!");
            e.printStackTrace();
        }
        close();
        return foundHouse;
    }

    public void updateHouse(String name, int seatQuantity, int cinemaId) {
        House foundHouse = findHouse(name, cinemaId);
        connect();
        try {
            if (foundHouse != null) {
                Statement statement = connection.createStatement();
                String sqlStr = "UPDATE HOUSE " +
                        "SET NAME = '" +
                        name + "', SEAT_QUANTITY = " +
                        seatQuantity + ", CINEMA_ID = " +
                        cinemaId + " " +
                        "WHERE ID = " + foundHouse.getHouseId();
                statement.executeUpdate(sqlStr);
                statement.close();
                System.out.println("Updated house with id: " + foundHouse.getHouseId());
            }
        } catch (SQLException e) {
            System.out.println("Update house failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public void deleteHouse(String name, int cinemaId) {
        House foundHouse = findHouse(name, cinemaId);
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "DELETE FROM HOUSE " +
                    "WHERE ID = " + foundHouse.getHouseId();
            statement.executeUpdate(sqlStr);
            statement.close();
            System.out.println("Deleted house with id: " + foundHouse.getHouseId());
        } catch (SQLException e) {
            System.out.println("Delete house failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    /** Seat Operation
     * @param seats*/
    public void createSeat(int initSeatId, Seat[] seats) {
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "INSERT INTO SEAT VALUES ";
            for (int i = 0; i < seats.length; i++) {
                sqlStr += "(" +
                        initSeatId + ", " +
                        seats[i].getRowNo() + ", " +
                        seats[i].getColumnNo() + ", " +
                        seats[i].getHouseId() + ", " +
                        seats[i].isIfOccupied() + ", " +
                        "NULL" +
                        "), ";
                seats[i].setSeatId(initSeatId++);
            }
            sqlStr = sqlStr.substring(0, sqlStr.length()-2);
            statement.executeUpdate(sqlStr);
            System.out.println("Added seats");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Create seat failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public Seat findSeat(int rowNo, int columnNo, int houseId) {
        Seat foundSeat = null;
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT * FROM SEAT " +
                    "WHERE ROW_NO = " + rowNo + " AND COLUMN_ID = " + columnNo + " AND HOUSE_ID = " + houseId;
            ResultSet resultSet = statement.executeQuery(sqlStr);
            if (resultSet.next()) {
                System.out.println("Found seat with id: " + resultSet.getInt(1));
                foundSeat = new Seat(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getInt(4));
            } else {
                System.out.println("Seat not found!");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Find seat failed!");
            e.printStackTrace();
        }
        close();
        return foundSeat;
    }

    public void updateSeat(int rowNo, int columnNo, int houseId, boolean ifOccupied, int scheduleId) {
        Seat foundSeat = findSeat(rowNo,columnNo,houseId);
        connect();
        try {
            if (foundSeat != null) {
                Statement statement = connection.createStatement();
                String sqlStr = "UPDATE SEAT " +
                        "SET IF_OCCUPIED = " +
                        ifOccupied + ", SCHEDULE_ID = " +
                        scheduleId + " " +
                        "WHERE ID = " + foundSeat.getSeatId();
                statement.executeUpdate(sqlStr);
                statement.close();
                System.out.println("Updated seat with id: " + foundSeat.getSeatId());
            }
        } catch (SQLException e) {
            System.out.println("Update seat failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    /** Movie Operation */
    public void createMovie(Movie movie) {
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "INSERT INTO MOVIE VALUES (" +
                    movie.getMovieId() + ", " +
                    "'" + movie.getName() + "', " +
                    "'" + movie.getDescription() + "', " +
                    movie.isIf3D() + ", " +
                    movie.getLength() + ", " +
                    movie.getCategory() + ", " +
                    "'" + movie.getDirector() + "', " +
                    "'" + movie.getStarring() + "', " +
                    "str_to_date('" + movie.getReleaseDate() + "', '%d-%m-%Y'), " +
                    "str_to_date('" + movie.getOffDate() + "', '%d-%m-%Y'), " +
                    movie.getScore() +
                    ")";
            statement.executeUpdate(sqlStr);
            statement.close();
            System.out.println("Added movie with id: " + movie.getMovieId());
        } catch (SQLException e) {
            System.out.println("Create movie failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public Movie findMovie(String name, boolean if3D) {
        Movie foundMovie = null;
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT * FROM MOVIE " +
                    "WHERE NAME = '" + name + "' AND IF_3D = " + if3D;
            ResultSet resultSet = statement.executeQuery(sqlStr);
            if (resultSet.next()) {
                System.out.println("Found movie with id: " + resultSet.getInt(1));
                foundMovie = new Movie(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getBoolean(4), resultSet.getInt(5),
                        resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9), resultSet.getString(10), resultSet.getFloat(11));
            } else {
                System.out.println("Movie not found!");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Find movie failed!");
            e.printStackTrace();
        }
        close();
        return foundMovie;
    }

    public void updateMovie(String name, String description, boolean if3D, int length, int category, String director, String starring, String releaseDate, String offDate, float score) {
        Movie foundMovie = findMovie(name,if3D);
        connect();
        try {
            if (foundMovie != null) {
                Statement statement = connection.createStatement();
                String sqlStr = "UPDATE MOVIE " +
                        "SET NAME = '" +
                        name + "', DESCRIPTION = '" +
                        description + "', IF_3D = " +
                        if3D + ", LENGTH = " +
                        length + ", CATEGORY = " +
                        category + "', DIRECTOR = '" +
                        director + "', STARRING = '" +
                        starring + "', RELEASE_DATE = '" +
                        releaseDate + "', OFF_DATE = '" +
                        offDate + "', SCORE = " +
                        score + " " +
                        "WHERE ID = " + foundMovie.getMovieId();
                statement.executeUpdate(sqlStr);
                statement.close();
                System.out.println("Updated movie with id: " + foundMovie.getMovieId());
            }
        } catch (SQLException e) {
            System.out.println("Update movie failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public void deleteMovie(String name, boolean if3D) {
        Movie foundMovie = findMovie(name, if3D);
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "DELETE FROM MOVIE " +
                    "WHERE ID = " + foundMovie.getMovieId();
            statement.executeUpdate(sqlStr);
            statement.close();
            System.out.println("Deleted movie with id: " + foundMovie.getMovieId());
        } catch (SQLException e) {
            System.out.println("Delete movie failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    /** Schedule Operation */
    public void createSchedule(Schedule schedule) {
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "INSERT INTO SCHEDULE VALUES (" +
                    schedule.getScheduleId() + ", " +
                    schedule.getMovieId() + ", " +
                    schedule.getHouseId() + ", '" +
                    "str_to_date('" + schedule.getStartTime() + "', '%d-%m-%Y'), " +
                    schedule.getPrice() +
                    ")";
            statement.executeUpdate(sqlStr);
            statement.close();
            System.out.println("Added schedule with id: " + schedule.getScheduleId());
        } catch (SQLException e) {
            System.out.println("Create schedule failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public Schedule findSchedule(String movieName, boolean if3D, String startTime) {
        Schedule foundSchedule = null;
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT * FROM SCHDULE " +
                    "WHERE NAME = '" + movieName + "' AND IF_3D = " + if3D + " AND START_TIME = str_to_date('" + startTime + "', '%d-%m-%Y')";
            ResultSet resultSet = statement.executeQuery(sqlStr);
            if (resultSet.next()) {
                System.out.println("Found schedule with id: " + resultSet.getInt(1));
                foundSchedule = new Schedule(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getFloat(5));
            } else {
                System.out.println("Schedule not found!");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Find schedule failed!");
            e.printStackTrace();
        }
        close();
        return foundSchedule;
    }

    public ArrayList<Schedule> findSchedules(String movieName, boolean if3D) {
        ArrayList<Schedule> foundSchedules = new ArrayList<Schedule>();
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT * FROM SCHDULE " +
                    "WHERE NAME = '" + movieName + "' AND IF_3D = " + if3D;
            ResultSet resultSet = statement.executeQuery(sqlStr);
            if (resultSet == null) {
                System.out.println("Schedule not found!");
            }
            while (resultSet.next()) {
                System.out.println("Found schedules.");
                foundSchedules.add(new Schedule(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getFloat(5)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Find schedule failed!");
            e.printStackTrace();
        }
        close();
        return foundSchedules;
    }

    public void updateSchedule(String movieName, boolean if3D, String houseName, String startTime, float price) {
        Schedule foundSchedule = findSchedule(movieName, if3D, startTime);
        connect();
        try {
            if (foundSchedule != null) {
                Statement statement = connection.createStatement();
                String sqlStr = "UPDATE SCHEDULE " +
                        "SET MOVIE_ID = " +
                        foundSchedule.getMovieId() + ", HOUSE_ID = " +
                        foundSchedule.getHouseId() + ", START_TIME = str_to_date('" +
                        startTime + "', '%d-%m-%Y'), PRICE = " +
                        price + " " +
                        "WHERE ID = " + foundSchedule.getScheduleId();
                statement.executeUpdate(sqlStr);
                statement.close();
                System.out.println("Updated schedule with id: " + foundSchedule.getScheduleId());
            }
        } catch (SQLException e) {
            System.out.println("Update schedule failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public void deleteSchedule(String movieName, boolean if3D, String startTime) {
        Schedule foundSchedule = findSchedule(movieName, if3D, startTime);
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "DELETE FROM SCHEDULE " +
                    "WHERE ID = " + foundSchedule.getScheduleId();
            statement.executeUpdate(sqlStr);
            statement.close();
            System.out.println("Deleted schedule with id: " + foundSchedule.getScheduleId());
        } catch (SQLException e) {
            System.out.println("Delete schedule failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    /** Ticket Operation */
    public void createTicket(Ticket ticket) {
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "INSERT INTO TICKET VALUES (" +
                    ticket.getTicketId() + ", " +
                    ticket.getUserId() + ", " +
                    ticket.getScheduleId() + ", " +
                    ticket.getCategory() + ", " +
                    ticket.getSeatId() +
                    ")";
            statement.executeUpdate(sqlStr);
            statement.close();
            System.out.println("Added ticket with id: " + ticket.getTicketId());
        } catch (SQLException e) {
            System.out.println("Create ticket failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    public Ticket findTicket(String username, Schedule schedule, int seatRowNo, int seatColumnNo) {
        int userId = findUser(username).getUserId();
        int scheduleId = schedule.getScheduleId();
        int seatId = findSeat(seatRowNo, seatColumnNo, schedule.getHouseId()).getSeatId();
        Ticket foundTicket = null;
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT * FROM TICKET " +
                    "WHERE USER_ID = " + userId + " AND SCHEDULE_ID = " + scheduleId + " AND SEAT_ID = " + seatId;
            ResultSet resultSet = statement.executeQuery(sqlStr);
            if (resultSet.next()) {
                System.out.println("Found ticket with id: " + resultSet.getInt(1));
                foundTicket = new Ticket(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5));
            } else {
                System.out.println("Ticket not found!");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Find ticket failed!");
            e.printStackTrace();
        }
        close();
        return foundTicket;
    }

    public void deleteTicket(String username, Schedule schedule, int seatRowNo, int seatColumnNo) {
        Ticket foundTicket = findTicket(username, schedule, seatRowNo, seatColumnNo);
        connect();
        try {
            Statement statement = connection.createStatement();
            String sqlStr = "DELETE FROM TICKET " +
                    "WHERE ID = " + foundTicket.getTicketId();
            statement.executeUpdate(sqlStr);
            statement.close();
            System.out.println("Deleted ticket with id: " + foundTicket.getTicketId());
        } catch (SQLException e) {
            System.out.println("Delete ticket failed!");
            e.printStackTrace();
            return;
        }
        close();
    }

    private void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database disconnected!");
            }
        } catch (SQLException e) {
            System.out.println("Database disconnection failed!");
            e.printStackTrace();
            return;
        }
    }
}
