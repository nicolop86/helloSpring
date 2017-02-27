package it.ariadne.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import it.ariadne.model.Message;

public class MessageRowMapper implements RowMapper<Message> {

	@Override
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer idMessage = rs.getInt("idmessages");
		Long idLongMessage = idMessage.longValue();
		String author = rs.getString("author");
		String text = rs.getString("text");
		return new Message(text, new Date(), idLongMessage, author);
	}

}
