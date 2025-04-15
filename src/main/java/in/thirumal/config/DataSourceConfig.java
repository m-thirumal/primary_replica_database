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

	@Bean
    @ConfigurationProperties("primary.datasource")
    DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("replica.datasource")
    DataSource replicaDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    @Primary
    DataSource dbRouter(@Qualifier("primaryDataSource") DataSource primary,
            @Qualifier("replicaDataSource") DataSource replica) {
        Map<Object, Object> dbTargets = new HashMap<>();
        dbTargets.put(DataSourceType.PRIMARY, primaryDataSource());
        dbTargets.put(DataSourceType.REPLICA, replicaDataSource());

        AbstractRoutingDataSource dbRouter = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
            	DataSourceType key = DataSourceContextHolder.getCurrentDataSource();
                return dbTargets.containsKey(key) ? key : DataSourceType.PRIMARY; // Fallback to primary
            }
        };
        dbRouter.setTargetDataSources(dbTargets);
        dbRouter.setDefaultTargetDataSource(primaryDataSource());
        dbRouter.afterPropertiesSet(); // Initialize properly
        return dbRouter;
    }

    @Bean
    PlatformTransactionManager transactionManager(DataSource dbRouter) {
        return new DataSourceTransactionManager(dbRouter);
    }
    
}
