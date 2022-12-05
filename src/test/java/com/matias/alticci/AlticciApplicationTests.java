package com.matias.alticci;

import com.matias.alticci.util.AlticciSimpleCache;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AlticciApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	void testAddRemoveObjects() {
		AlticciSimpleCache<Integer, Integer> cache = new AlticciSimpleCache<>(200,500);
		cache.put(2,200);
		cache.put(3,300);
		cache.put(4,400);
		cache.put(1,100);
		cache.put(5,500);
		cache.put(7,700);

		cache.remove(2);
		cache.remove(5);

		assertEquals(4, cache.size());
		assertEquals(null, cache.get(5));
		assertEquals(700, cache.get(7));
	}

}
