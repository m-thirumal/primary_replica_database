/**
 * 
 */
package in.thirumal.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import in.thirumal.model.Order;

/**
 * 
 */
@Repository
public class OrderRepository {
	
	JdbcTemplate jdbcTemplate;
	
	public OrderRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public int create(Order order) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(con -> setPreparedStatement(order, con.prepareStatement("INSERT INTO public.order(name) VALUES ( ?)",
					new String[] { "order_id" })), holder);
		return Optional.ofNullable(holder.getKey()).orElseThrow(() -> new InternalError("Insertion failed")).intValue();
	}

	public PreparedStatement setPreparedStatement(Order order, PreparedStatement ps) throws SQLException {
		ps.setString(1, order.getName());		
		return ps;
	}

	public List<Order> list() {
		return jdbcTemplate.query("SELECT * FROM public.order", orderRowMapper);
	}
	
	RowMapper<Order> orderRowMapper = (rs, rowNum) -> {
		Order order = new Order();
		order.setOrderId(rs.getInt(1));
		order.setName(rs.getObject("name") != null ? rs.getString("name") : null);
		return order;
		
	};

}
