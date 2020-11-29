package covid.controller;

import covid.json.JSONObject;
import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class HttpRequest {

    public StringBuffer HttpRequest() throws IOException {
        URL url = new URL("https://api.covidtracking.com/v1/states/ca/20200501.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setReadTimeout(20000);
        connection.setConnectTimeout(20000);


        int status = connection.getResponseCode();
        System.out.println(status);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer stringBuffer = new StringBuffer();

        while ((inputLine = in.readLine()) != null){
            stringBuffer.append(inputLine);
        }


       // System.out.println("Result :"+ jsonObject.getString("totalTestsViral"));
        in.close();
        connection.disconnect();
        return stringBuffer;
    }
}
