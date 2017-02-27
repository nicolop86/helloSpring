package it.ariadne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import it.ariadne.dao.mapper.MessageRowMapper;
import it.ariadne.model.Message;

@Repository
public class MessageJDBCRepository extends JdbcDaoSupport implements MessageRepository {

	@Autowired
	private DataSource dataSource;

	/*Per questo repository faccio autowiring del dataSource e lo attacco a questo
	 * message repository; il metodo setDataSource è mutuato da JdbcDaoSupport*/
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}

	@Override
	public List <Message> findMessages(long maxValue, int count){
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Message> messageList = new ArrayList<>();

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM MESSAGES");
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				System.out.println(rs.getString("author"));
				System.out.println(rs.getString("text"));
				String text = rs.getString("text");
				String author = rs.getString("author");

				Message m = new Message(text, new Date(), 0L, author);
				messageList.add(m);

			}
			/*Se insert o update int res = stmt.executeUpdate() che indica il numero di 
			 * record soggetti a modifica*/
			/*stmt = conn.prepareStatement("SELECT * FROM MESSAGES WHERE AUTHOR = ?");
		stmt.setString(0, "autore");*/
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {			
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return messageList;
	}

	
	public List<String> getMessageText() {
		String sql = "Select m.text from messages m";
		List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
		return list;
	}
	
	
	public List<Message> queryForMessages(){
		
		String sql = "Select m.text, m.author, m.idmessages from messages m"
				+ " where m.author = ? and m.idmessages > ?";
		
		String auth = "NICK";
		int idmessage = 4;
		
		Object [] args = {auth, idmessage};
		int [] argTypes = new int [] {Types.VARCHAR, Types.INTEGER};
		
		List<Map<String, Object>> map = this.getJdbcTemplate().queryForList(sql, args, argTypes);

		
		List<Message> messages = new ArrayList<>();
		for (Map<String, Object> record : map) {
				Message m = new Message((String)record.get("text"), new Date(), ((Integer)record.get("idmessages")).longValue(), (String)record.get("author"));
				messages.add(m);
				System.out.println(record.get("text") + " " + record.get("idmessages") + " " + record.get("author"));
		}
		return messages;
	}
	
	
    private static final String BASE_SQL = //
            "Select m.author, m.text, m.idmessages from messages m";
    
	public List<Message> queryRowMapper() {
		String param = " where m.author = ?";
		
		String sql = BASE_SQL + param;
		
		MessageRowMapper rowMapper = new MessageRowMapper();
		Object[] args = {"CHRIS"};
		
        List<Message> list = this.getJdbcTemplate().query(sql, args, rowMapper);
        return list;
	}
}
