package it.ariadne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.ariadne.dao.MessageRepository;

@Controller
public class MessageController {

	private MessageRepository messageRepository;

	@Autowired
	public MessageController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@RequestMapping(value={"/messages"}, method=RequestMethod.GET)
	public String messages(Model model) {
		model.addAttribute(messageRepository.findMessages(Long.MAX_VALUE, 20));
		return "messages";
	}
	
	@RequestMapping(value={"/template"}, method=RequestMethod.GET)
	public String messageTemplate(Model model) {
		model.addAttribute(messageRepository.getMessageText());
		return "messages";
	}
	
	@RequestMapping(value={"/queryOne"}, method=RequestMethod.GET)
	public String performQuery(Model model) {
		model.addAttribute(messageRepository.queryForMessages());
		return "messages";
	}
	
	@RequestMapping(value={"/queryRowMapper"}, method=RequestMethod.GET)
	public String queryWithRowMapper(Model model) {
		model.addAttribute(messageRepository.queryRowMapper());
		return "messages";
	}
	
}