/**
 * 
 */
package in.thirumal.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 */
@Getter@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder@ToString
public class Order implements Serializable {

	private static final long serialVersionUID = -3496815994702273952L;
	
	private int orderId;
	private String name;

}
