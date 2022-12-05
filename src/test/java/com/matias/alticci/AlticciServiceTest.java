package com.matias.alticci;

import com.matias.alticci.service.AlticciService;
import com.matias.alticci.util.AlticciSimpleCache;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlticciServiceTest {

    @Test
    public void testServiceGenerate() throws Exception {
        AlticciSimpleCache<Integer,Integer> cache = new AlticciSimpleCache<>(500,600);
        AlticciService service = new AlticciService(cache);
        assertEquals(100,service.validate("100"));
        assertEquals(0, service.generate(0));
        assertEquals(1, service.generate(1));
        assertEquals(1, service.generate(2));
        assertEquals(2, service.generate(4));
        assertEquals(2, service.generate(4));
        assertEquals(2, service.generate(4));
        assertEquals(2, service.generate(4));
        assertEquals(2, service.generate(4));

    }
}

