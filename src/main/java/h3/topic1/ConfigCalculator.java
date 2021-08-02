package h3.topic1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCalculator {
    @Bean
    public ICalculator getCalculator(){
        return new AdvancedCalculator();
    }
}
