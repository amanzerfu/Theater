package et.com.qena.theater.configs;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties("api-management")
public class TheaterConfig {
private  String omdb_api_url;
private  String omdb_api_key;
}
