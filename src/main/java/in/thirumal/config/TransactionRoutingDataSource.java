package in.thirumal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.Embedded.Nullable;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;
/**
 * @author Thirumal
 */
@Configuration
public class TransactionRoutingDataSource extends AbstractRoutingDataSource {

	@Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? DataSourceType.READ_ONLY : DataSourceType.READ_WRITE;
    }
	
}
