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
		System.out.println("Setting data source to " + datasource);
		CONTEXT.set(datasource);
	}

	public static DataSourceType getCurrentDataSource() {
		return CONTEXT.get() != null ? CONTEXT.get() : DataSourceType.PRIMARY;
	}

	public static void clear() {
		CONTEXT.remove();
	}
}
