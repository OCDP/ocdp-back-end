package br.ufg.api.ocd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OCDApplicationTests extends DefaultApplicationTest {

    @Test
    public void contextLoads() {
        ApiOcdApplication app = new ApiOcdApplication();
        assertNotNull(app);
    }

}
