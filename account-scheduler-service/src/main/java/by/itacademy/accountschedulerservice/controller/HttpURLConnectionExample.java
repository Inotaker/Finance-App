package by.itacademy.accountschedulerservice.controller;

import by.itacademy.accountschedulerservice.model.dto.OperationHttpPost;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class HttpURLConnectionExample {
    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        HttpURLConnectionExample http = new HttpURLConnectionExample();
//
//        System.out.println("Testing 1 - Send Http GET request");
//        http.sendGet();

        System.out.println("\nTesting 2 - Send Http POST request");

    }

    // HTTP-запрос GET
    private void sendGet() throws Exception {

        String url = "http://localhost:8080/account?page=1&size=5";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Значение по умолчанию - GET
        con.setRequestMethod("GET");

        // Добавляем заголовок запроса
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Распечатываем результат
        System.out.println(response.toString());

    }

    // HTTP-запрос POST
//    private void sendPost() throws Exception {
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        try {
//            HttpPost request = new HttpPost("http://localhost:8080/account");
//            StringEntity params = new StringEntity("{\"title\":\"1\"," +
//                    "\"description\":\"ЧЕРЕЗ HTTP\"," +
//                    "\"type\":\"CASH\"," +
//                    "\"currency\":\"d2b9063a-8a7d-4579-9889-8ab692f6a1f4\"} ");
//            request.addHeader("content-type", "application/json");
//            request.setEntity(params);
//            HttpResponse response = httpClient.execute(request);
//            System.out.println(response.getStatusLine().toString());
//        } catch (Exception ex) {
//        } finally {
//            // @Deprecated httpClient.getConnectionManager().shutdown();
//        }




//        String url = "http://localhost:8080/account";
//        URL obj = new URL(url);
//        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//        // Добавляем заголовок запроса
//        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//
//        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
//
//        // Отправить запрос на публикацию
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(urlParameters);
//        wr.flush();
//        wr.close();
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + urlParameters);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        // Распечатываем результат
//        System.out.println(response.toString());

    }


