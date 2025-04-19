package br.com.fiap.alarme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // ✅ ESSENCIAL PARA USAR O application-test.properties
class AlarmeApplicationTests {

	@Test
	void contextLoads() {
	}

}
