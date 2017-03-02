package it.ariadne.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.ariadne.model.Message;

public class MessageCollectionRepository implements MessageRepository {

	@Override
	public List<Message> findMessages(long maxValue, int count) {
		List<Message> messages = new ArrayList<Message>();
		for (int i = 0; i < count; i++) {
			messages.add(new Message("Message " + i, new Date(), new Long(i), "RandomAuth"));
		}
		return messages;
	}

	@Override
	public List<String> getMessageText() {
		return null;
	}

	@Override
	public List<Message> queryForMessages() {
		return null;
	}

	@Override
	public List<Message> queryRowMapper() {
		return null;
	}

}