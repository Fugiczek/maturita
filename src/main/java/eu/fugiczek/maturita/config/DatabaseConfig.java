package eu.fugiczek.maturita.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class DatabaseConfig {
	
	@Autowired
    private DataSourceProperties properties;
	
	@Bean
	@ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
	public DataSource dataSource() throws URISyntaxException {
		String url;
        String username;
        String password;

        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl != null) {
            URI dbUri = new URI(databaseUrl);
            url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
        } else {
            url = properties.getUrl();
            username = properties.getUsername();
            password = properties.getPassword();
        }
        
		DataSourceBuilder factory = DataSourceBuilder
				.create(properties.getClassLoader())
				.url(url)
				.username(username)
				.password(password);
		return factory.build();
	}
	
}
