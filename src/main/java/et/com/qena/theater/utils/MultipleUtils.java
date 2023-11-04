package et.com.qena.theater.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.UUID;

@Component
public class MultipleUtils {
    /**
     * this service will generate ID but the ids are limited for now
     *
     * @return generated code
     */
    public static String randomGenerateId() {
        return  String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
    }
}
