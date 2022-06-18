package pl.pjatk.jazs22446nbp.golder.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Golder Response", description = "Entity of provided dates and data fetched from NBP API")
public class GolderResponse {
    @ApiModelProperty(
            notes = "Value of average gold exchange rate between inDate and outDate period (for 1g in 1000 sample)",
            example = "255.7312"
    )
    private double averageExchangeRate;

    public GolderResponse(Currency currency, double averageExchangeRate) {
        this.averageExchangeRate = averageExchangeRate;
    }

    public double getAverageExchangeRate() {
        return averageExchangeRate;
    }

    public void setAverageExchangeRate(double averageExchangeRate) {
        this.averageExchangeRate = averageExchangeRate;
    }
}
