/**
 * 
 */
package in.thirumal.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 
 */
@Configuration
public class DataSourceConfig {

	@Bean(name = "primaryDataSource")
    @ConfigurationProperties("primary.datasource")
    DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "replicaDataSource")
    @ConfigurationProperties("replica.datasource")
    DataSource replicaDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    @Primary
    DataSource dbRouter(@Qualifier("primaryDataSource") DataSource primary,
            @Qualifier("replicaDataSource") DataSource replica) {
        Map<Object, Object> dbTargets = new HashMap<>();
        dbTargets.put(DataSourceType.PRIMARY, primary);
        dbTargets.put(DataSourceType.REPLICA, replica);

        AbstractRoutingDataSource dbRouter = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
            	DataSourceType key = DataSourceContextHolder.getCurrentDataSource();
            	System.out.println("🔁 Routing to: " + key);
                return dbTargets.containsKey(key) ? key : DataSourceType.PRIMARY; // Fallback to primary
            }
        };
        dbRouter.setTargetDataSources(dbTargets);
        dbRouter.setDefaultTargetDataSource(primary);
        dbRouter.afterPropertiesSet(); // Initialize properly
        return dbRouter;
    }

    @Bean
    PlatformTransactionManager transactionManager(DataSource dbRouter) {
        return new DataSourceTransactionManager(dbRouter);
    }
    
}
