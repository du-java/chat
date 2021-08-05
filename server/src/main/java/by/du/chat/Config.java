package by.du.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class Config {

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

}
