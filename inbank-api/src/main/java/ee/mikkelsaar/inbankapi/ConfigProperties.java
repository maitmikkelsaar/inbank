package ee.mikkelsaar.inbankapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "inbank-api")
public class ConfigProperties {
  private String inbankFrontendUrl;
}