package com.matias.alticci.service;

import com.matias.alticci.util.AlticciSimpleCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlticciService {

    private static final Logger log = LogManager.getLogger(AlticciService.class);
    private AlticciSimpleCache<Integer, Integer> cache;

    @Autowired
    public AlticciService(AlticciSimpleCache<Integer, Integer> cache) {
        this.cache = cache;
    }

    public int validate(String requestedIndex) throws Exception {
        if (requestedIndex.isEmpty()) {
            log.debug("Error: Invalid empty parameter");
            throw new Exception("Invalid empty index");
        }
        try {
            log.info("Parsing string parameter");
            int intValue = Integer.parseInt(requestedIndex);
            return intValue;
        } catch (NumberFormatException e) {
            log.debug("Error when parsing parameter: {}", e.getMessage());
            throw new Exception("Invalid number");
        }
    }

//    n=0 => a(0) = 0
//    n=1 => a(1) = 1
//    n=2 => a(2) = 1
//    n>2 => a(n) = a(n-3) + a(n-2)
    private int a(int num) {
        return switch (num) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 1;
            default -> a(num-3) + a(num-2);
        };
    }

    public int generate(int indexNumber) {
        Integer intResult = cache.get(indexNumber);
        if (intResult == null) {
            intResult = a(indexNumber);
            cache.put(indexNumber, intResult);
            log.info("New number created: {}, {}", indexNumber, intResult);
        } else {
            log.info("Using cache {},{}", indexNumber, intResult);
        }
        return intResult;
    }
}
