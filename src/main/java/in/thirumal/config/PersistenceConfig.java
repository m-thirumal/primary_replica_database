/**
 * 
 */
package in.thirumal.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author திருமால்
 * @since 14/04/2017
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${jdbc.primary}")
    private String primaryUrl; 
    @Value("${jdbc.replica}")
    private String replicaUrl;    
    @Value("${spring.datasource.username}")
    private String username; 
    @Value("${spring.datasource..password}")
    private String password;
	@Bean
	//@ConfigurationProperties(prefix = "spring.datasource")
	@Primary
	public DataSource defaultDataSource() {
		logger.debug("{} {}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			return DataSourceBuilder.create().build();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Bean
	public DataSource primaryDataSource() {
		logger.debug("{} {}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			return DataSourceBuilder.create().url(primaryUrl).username(username).password(password).build();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Bean
	public DataSource replicaDataSource() {
		logger.debug("{} {}", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			return DataSourceBuilder.create().url(replicaUrl).username(username).password(password).build();
		} catch (Exception e) {
			throw e;
		}
	}
}
