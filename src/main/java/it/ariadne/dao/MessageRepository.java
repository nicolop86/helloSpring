package it.ariadne.dao;

import java.util.List;

import it.ariadne.model.Message;


public interface MessageRepository {

	public List<Message> findMessages(long maxValue, int count);
	public List<String> getMessageText();
	public List<Message> queryForMessages();
	public List<Message> queryRowMapper();

}