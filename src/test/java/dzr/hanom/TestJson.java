package dzr.hanom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class TestJson {

    private ClassLoader cl = TestJson.class.getClassLoader();

    @Test
    void jsonTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = cl.getResourceAsStream("car.json");
             InputStreamReader isr = new InputStreamReader(is)) {
            Car car = objectMapper.readValue(isr, Car.class);
            Assertions.assertEquals("Skoda", car.brand);
            Assertions.assertEquals("Kodiaq", car.model);
            Assertions.assertEquals("Black", car.colour);
            Assertions.assertEquals(19, car.wheels);
            Assertions.assertEquals(3000000, car.price);
            Assertions.assertEquals(4653, car.dimensions.length);
            Assertions.assertEquals(1879, car.dimensions.width);
            Assertions.assertEquals(1621, car.dimensions.height);
            Assertions.assertEquals(List.of("mirror", "grid", "cruise control"), car.extras);

        }
    }

}
