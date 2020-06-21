package in.thirumal.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.Embedded.Nullable;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;
/**
 * @author Thirumal
 */
@Configuration
public class TransactionRoutingDataSource extends AbstractRoutingDataSource {

	
	PersistenceConfig persistenceConfig;
	
	public TransactionRoutingDataSource() {
		persistenceConfig = new PersistenceConfig();
		setDataSource();
	}
	
	@Nullable
    @Override
    protected Object determineCurrentLookupKey() {
		System.out.println("de");
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? 
        		DataSourceType.READ_WRITE : DataSourceType.READ_ONLY;
    }
	
    public void setDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(
            DataSourceType.READ_WRITE,
            persistenceConfig.primaryDataSource()
        );
        dataSourceMap.put(
            DataSourceType.READ_ONLY,
            persistenceConfig.replicaDataSource()
        );
        System.out.println("Re" + dataSourceMap.get(DataSourceType.READ_WRITE));
        System.out.println("DSA" + dataSourceMap);
        System.out.println("das" + dataSourceMap.get(DataSourceType.READ_ONLY));
        
        this.setTargetDataSources(dataSourceMap);
    }
	
}
