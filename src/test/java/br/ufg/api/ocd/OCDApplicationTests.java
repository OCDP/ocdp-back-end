package br.ufg.api.ocd;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class OCDApplicationTests {

    @Test
    public void contextLoads() {
        ApiOcdApplication app = new ApiOcdApplication();
        assertNotNull(app);
    }

    @Test
    public void contextMongo() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

    }

}
