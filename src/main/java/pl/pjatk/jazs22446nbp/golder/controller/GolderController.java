package pl.pjatk.jazs22446nbp.golder.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.jazs22446nbp.golder.model.GolderResponse;
import pl.pjatk.jazs22446nbp.golder.service.GolderService;

@RestController
@RequestMapping("/golder")
public class GolderController {
    private final GolderService golderService;

    public GolderController(GolderService golderService) {
        this.golderService = golderService;
    }

    @GetMapping("/average/{inDate}/{outDate}")
    @ApiOperation(value = "Calling NBP Api endpoint for average gold exchange rate value in given period of time",
            notes = "Provide in-date and out-date to get data from")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST - Invalid input data or request timeout"),
            @ApiResponse(code = 404, message = "NOT_FOUND - No data for proper input"),
            @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR")
    })
    public GolderResponse getAverageGoldPrice(
            @ApiParam(value = "in-date for NBP API, used as first timestamp date", example = "2022-01-01")
            @PathVariable(name = "inDate") String inDate,
            @ApiParam(value = "out-date for NBP API, used as last timestamp date", example = "2022-06-18")
            @PathVariable(name = "outDate") String outDate
    ) {
        return golderService.getAverageGoldValueBetweenDates(inDate, outDate);
    }
}
