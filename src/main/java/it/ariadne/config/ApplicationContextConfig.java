package it.ariadne.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import it.ariadne.dao.MessageJDBCRepository;
import it.ariadne.dao.MessageRepository;
import it.ariadne.model.English;
import it.ariadne.model.Language;

@Configuration
@ComponentScan("it.ariadne.controller") 
public class ApplicationContextConfig {

	@Bean(name ="language")
	public Language getLanguage() { 
		return new English();
	}


	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/*Connecting to jdbc/ariadne which is the data source*/
	@Bean(name = "ariadneDataSource")
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		DataSource dataSource = dsLookup.getDataSource("jdbc/ariadne");
		return dataSource;
	}

	@Bean(name = "messageRepository")
	public MessageRepository getMessageRepository(){
		return new MessageJDBCRepository();
	}

}