package et.com.qena.theater.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.UUID;

@Component
public class MultipleUtils {
    /**
     * this service will generate random code for resett
     *
     * @return generated code
     */
    public static String randomString() {
        return  String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
    }
}
