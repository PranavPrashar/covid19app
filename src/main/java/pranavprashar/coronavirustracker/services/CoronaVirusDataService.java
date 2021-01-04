package pranavprashar.coronavirustracker.services;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pranavprashar.coronavirustracker.models.LocationStats;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
@Service // by doing this we are making this a spring service
public class CoronaVirusDataService {
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allStats = new ArrayList<>();
    
    @PostConstruct // when you construct then execute this method
    @Scheduled(cron = "* * 1 * * *") // second minute hour. We are going to run this fucntion every hour using a spring boot proxy
    public void fetchVirusData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient(); // create a new http client
        //creating a new request using the builder patern
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
        // this is creating a new http request and then builds using the .build

        //new we will try to get a response by sending the request and take the body and return it as a string.
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());
        StringReader csvBodyReader = new StringReader(httpResponse.body());// by calling .body we can return it as string


        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        // here the format is RCF4180 which is a format of csv which allows for empty lines
        for (CSVRecord record : records) {
            String state = record.get("Province/State");
            System.out.println(state);

           // String customerNo = record.get("CustomerNo");
           // String name = record.get("Name");
        }


    }
}


