package pl.pjatk.jazs22446nbp.golder.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.jazs22446nbp.golder.model.Currency;
import pl.pjatk.jazs22446nbp.golder.model.GoldRate;
import pl.pjatk.jazs22446nbp.golder.model.Golder;
import pl.pjatk.jazs22446nbp.golder.model.GolderResponse;
import pl.pjatk.jazs22446nbp.golder.repository.GolderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GolderService {
    private final GolderRepository golderRepository;
    private final RestTemplate restTemplate;

    public GolderService(GolderRepository golderRepository, RestTemplate restTemplate) {
        this.golderRepository = golderRepository;
        this.restTemplate = restTemplate;
    }

    public GolderResponse getAverageGoldValueBetweenDates(String inDate, String outDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inDateProjected = LocalDate.parse(inDate, dateTimeFormatter);
        LocalDate outDateProjected = LocalDate.parse(outDate, dateTimeFormatter);

        String url = "http://api.nbp.pl/api/cenyzlota/" + inDate + "/" + outDate;

        ResponseEntity<GoldRate[]> response = restTemplate.exchange(url, HttpMethod.GET, null, GoldRate[].class);

        GoldRate[] responseBody = response.getBody();

        double averageRate = this.calculateAverageGoldRate(responseBody);

        GolderResponse golderResponse = this.saveAverageGoldRateToDatabase(inDateProjected, outDateProjected, averageRate);

        return golderResponse;
    }

    private double calculateAverageGoldRate(GoldRate[] goldRates) {
        int ratesCount = goldRates.length;
        double ratesSum = 0d;
        for (GoldRate rate : goldRates) {
            ratesSum += rate.getCena();
        }

        double averageRate = ratesSum / ratesCount;

        return averageRate;
    }

    private GolderResponse saveAverageGoldRateToDatabase(LocalDate inDate, LocalDate outDate, double averageRate) {
        Golder golder = new Golder();

        Currency currency = Currency.GOLD;

        golder.setCurrency(currency);
        golder.setInDate(inDate);
        golder.setOutDate(outDate);
        golder.setValue(averageRate);
        golder.setRequestDate(LocalDateTime.now());

        golderRepository.save(golder);

        GolderResponse golderResponse = new GolderResponse(currency, averageRate);

        return golderResponse;
    }
}
