package weather.weather;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weather.weather.results.Root;
import weather.weather.results.Weather;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class Controller {

//    GetRequest valiReq = new GetRequest();
    GetMyCity myCity = new GetMyCity();

    @RequestMapping("/hello/{city}")
    public Root sayHello(@PathVariable String city) throws IOException, InterruptedException {
        Root deModificat = myCity.getMyCity(city);
        deModificat.vali.setVali("Dani");
        return deModificat;
    }
}
