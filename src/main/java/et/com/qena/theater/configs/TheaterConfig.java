package et.com.qena.theater.configs;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("api-management")
@Data
public class TheaterConfig {
private  String omdb_api_url;
}
