package weather.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONObject;
import weather.weather.results.Root;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetMyCity {

    public Root getMyCity(String city) throws IOException, InterruptedException {

        String myApiKey = "436677a0f7d093b0cd7211ef50efee0c";
        String limit = "1";
        String URL = "http://api.openweathermap.org/geo/1.0/direct?q="+city+"&limit="+limit+"&appid="+myApiKey;

//        http://api.openweathermap.org/geo/1.0/direct?q=London&limit=2&appid=436677a0f7d093b0cd7211ef50efee0c

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        String str = response.body();
        JSONArray obj = new JSONArray(str);
        JSONObject info = (JSONObject) obj.get(0);

        return bringTheWeather(info.getDouble("lat"),info.getDouble("lon"));
    }

    public Root bringTheWeather(double lat, double lon) throws IOException, InterruptedException {
//        https://api.openweathermap.org/data/3.0/onecall?lat=33.44&lon=-94.04&exclude=hourly,daily&appid={API key}

        String myApiKey = "436677a0f7d093b0cd7211ef50efee0c";
        String URL="https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+myApiKey;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        ObjectMapper mapper = new ObjectMapper();
        Root rezultat = mapper.readValue(response.body(), Root.class);
        return rezultat;
    }
}
