/**
 * 
 */
package in.thirumal.config;

/**
 * 
 */
public class DataSourceContextHolder {
	
	private static final ThreadLocal<DataSourceType> CONTEXT = new ThreadLocal<>();

	public static void setDataSource(DataSourceType datasource) {
		CONTEXT.set(datasource);
	}

	public static DataSourceType getCurrentDataSource() {
		return CONTEXT.get() != null ? CONTEXT.get() : DataSourceType.PRIMARY;
	}

	public static void clear() {
		CONTEXT.remove();
	}
}
