package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.AppUser;
import model.Reservation;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.nio.charset.StandardCharsets.*;
import static utils.FormatDate.getCurrentDate;
import static utils.ParameterStringBuilder.getParamsString;

/**
 * @author jeremy on 2022/12/6
 */
public class ClientRequest {

    private final static String BASE_URL = "http://localhost:8082";
    private final static String USER_URL = "/api/user";
    private final static String RESERVATION_URL = "/api/reservation";
    public static final String BY_EMAIL_PATH = "/byEmail";
    public static final String BY_ID_PATH = "/byId";
    private final static Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();;

    public static AppUser getUserInfo(Integer userId) {
        AppUser user = null;
        if (userId == null) return user;

        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(userId));
            URL url = new URL(BASE_URL + USER_URL + BY_ID_PATH
                    + getParamsString(params));

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            System.out.println("URL: " + con.getURL());
            int statusCode = con.getResponseCode();
            System.out.println("GET Response Code: " + statusCode);

            if (statusCode == HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer json = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    json.append(inputLine);
                }
                in.close();
                user = gson.fromJson(json.toString(), AppUser.class);

            } else {
                System.out.println("GET request failed with code " + statusCode);
            }
            con.disconnect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public static AppUser getUserInfo(String email) {
        AppUser user = null;
        if (email == null) return user;

        try {
            Map<String, String> params = new HashMap<>();
            params.put("email", email);
            URL url = new URL(BASE_URL + USER_URL + BY_EMAIL_PATH
                    + getParamsString(params));

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            System.out.println("URL: " + con.getURL());
            int statusCode = con.getResponseCode();
            System.out.println("GET Response Code: " + statusCode);

            if (statusCode == HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer json = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    json.append(inputLine);
                }
                in.close();
                user = gson.fromJson(json.toString(), AppUser.class);
            } else {
                System.out.println("GET request failed with code " + statusCode);
            }
            con.disconnect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public static List<Reservation> getReservationByUserId(Integer userId) {
        List<Reservation> reservations = new ArrayList<>();
        if (userId == null) return reservations;

        try {
            Map<String, String> params = new HashMap<>();
            params.put("userId", String.valueOf(userId));
            URL url = new URL(BASE_URL + RESERVATION_URL
                    + getParamsString(params));

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            System.out.println("URL: " + con.getURL());
            int statusCode = con.getResponseCode();
            System.out.println("GET Response Code: " + statusCode);

            if (statusCode == HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer json = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    json.append(inputLine);
                }
                in.close();
                TypeToken<List<Reservation>> typeToken = new TypeToken<List<Reservation>>(){};
                reservations = gson.fromJson(json.toString(), typeToken);
            } else {
                System.out.println("GET request failed with code " + statusCode);
            }
            con.disconnect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return reservations;
    }

    public static void registerUser(String username,
                                    String lastName,
                                    String firstName,
                                    String email) {
        try {
            URL url = new URL(BASE_URL + USER_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // body:
            Map<String, String> body = new HashMap<>();
            body.put("username", username);
            body.put("lastName", lastName);
            body.put("firstName", firstName);
            body.put("email", email);

            String jsonInputString = gson.toJson(body);

            try (OutputStream output = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(UTF_8);
                output.write(input, 0, input.length);
            }

            /* read the response
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
             */
            System.out.println("URL: " + con.getURL());
            System.out.println("POST Response Code: " + con.getResponseCode());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void makeReservation(Date date,
                                       Integer roomId,
                                       Integer timeSlot,
                                       Integer userId,
                                       String description) {
        try {
            URL url = new URL(BASE_URL + RESERVATION_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // body:
            Map<String, Object> body = new HashMap<>();
            body.put("date", getCurrentDate(date));
            body.put("roomId", roomId);
            body.put("timeSlot", timeSlot);
            Map<String, Integer> user = new HashMap<>();
            user.put("id", userId);
            body.put("user", user);
            if (description != null) body.put("description", description);

            String jsonInputString = gson.toJson(body);

            try (OutputStream output = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(UTF_8);
                output.write(input, 0, input.length);
            }
            System.out.println("URL: " + con.getURL());
            System.out.println("POST Response Code: " + con.getResponseCode());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deleteUser(Integer id) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(id));
            URL url = new URL(BASE_URL + USER_URL + getParamsString(params));

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.connect();

            System.out.println("URL: " + con.getURL());
            System.out.println("DELETE Response Code: " + con.getResponseCode());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void updateUser(Integer id,
                                  String username,
                                  String lastName,
                                  String firstName,
                                  String email) {
        if (id == null) return;

        Map<String, String> params = new HashMap<>();
        if (username != null) params.put("username", username);
        if (lastName != null) params.put("lastName", lastName);
        if (firstName != null) params.put("firstName", firstName);
        if (email != null) params.put("email", email);
        if (params.size() == 0) return;

        try {
            URL url = new URL(BASE_URL + USER_URL
                    + "/" + id + getParamsString(params));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(
                    con.getOutputStream());
            out.close();
            System.out.println("URL: " + con.getURL());
            System.out.println("PUT Response Code: " + con.getResponseCode());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<Reservation> getAllReservation() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            URL url = new URL(BASE_URL + RESERVATION_URL + "/all");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            System.out.println("URL: " + con.getURL());
            int statusCode = con.getResponseCode();
            System.out.println("GET Response Code: " + statusCode);

            if (statusCode == HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer json = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    json.append(inputLine);
                }
                in.close();
                TypeToken<List<Reservation>> typeToken = new TypeToken<List<Reservation>>(){};
                reservations = gson.fromJson(json.toString(), typeToken);
            } else {
                System.out.println("GET request failed with code " + statusCode);
            }
            con.disconnect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return reservations;
    }

    public static void main(String[] args) {
//        getUserInfo(1);
//        getUserInfo("email1");
//        getUserInfo("email_not_exist");
//        getReservationByUserId(1);
//        registerUser(
//                "1207",
//                "lastname1207",
//                "firstname1207",
//                "email@gmail.com"
//        );
//        makeReservation(new Date(), 1, 1,1);
//        deleteUser(4);
//        updateUser(1, null, "update", null, "update@email.com");
//        getAllReservation();
    }
}
