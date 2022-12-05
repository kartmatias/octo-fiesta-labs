package com.matias.alticci.controller;

import com.matias.alticci.service.AlticciService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Alticci - Handles Alticci Sequences")
@RestController
@RequestMapping(path = "/v1", produces = {MediaType.APPLICATION_JSON_VALUE })
public class AlticciController {
    private final AlticciService alticciService;
    private static final int RESULT_ERROR = -1;

    @Autowired
    public AlticciController(AlticciService alticciService) {
        this.alticciService = alticciService;
    }

    @ApiOperation("Generates alticci number")
    @GetMapping("/alticci/{number}")
    public @ResponseBody int generate(@PathVariable(value = "number") String indexNumber) {
        final int validIndex;
        try {
            validIndex = alticciService.validate(indexNumber);
        } catch (Exception e) {
            return RESULT_ERROR;
        }
        return alticciService.generate(validIndex);
    }

}


