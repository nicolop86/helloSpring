package it.ariadne.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import it.ariadne.dao.MessageJDBCRepository;
import it.ariadne.dao.MessageRepository;
import it.ariadne.model.English;
import it.ariadne.model.Language;

@Configuration
@ComponentScan("it.ariadne.controller")
@EnableWebMvc
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = { "it.ariadne.dao" })/*Where are repositories?*/
@EnableTransactionManagement/*My REPOs will be transactional, every call on method managing with
my tables will be transactional*/

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

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, 
			Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();/*Instantiating
	        the entity manger which will manage persistence*/
		entityManagerFactoryBean.setDataSource(dataSource);/*Setting data source*/
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());/*Hibernate will be used*/
		entityManagerFactoryBean.setPackagesToScan("it.ariadne.model");/*Entity will be in it.ariadne.model*/

		Properties jpaProperties = new Properties();
		//Configures the used database dialect. This allows Hibernate to create SQL
		//that is optimized for the used database.
		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect")/*"org.hibernate.dialect.MySQL5Dialect"*/);

		//Specifies the action that is invoked to the database when the Hibernate
		//SessionFactory is created or closed.
		jpaProperties.put("hibernate.hbm2ddl.auto", 
				env.getRequiredProperty("hibernate.hbm2ddl.auto")/*"update"*/
				);

		//Configures the naming strategy that is used when Hibernate creates
		//new database objects and schema elements
		jpaProperties.put("hibernate.ejb.naming_strategy", 
				env.getRequiredProperty("hibernate.ejb.naming_strategy")/*"org.hibernate.cfg.ImprovedNamingStrategy"*/
				);

		//If the value of this property is true, Hibernate writes all SQL
		//statements to the console.
		jpaProperties.put("hibernate.show_sql", 
				env.getRequiredProperty("hibernate.show_sql")/*"true"*/
				);

		//If the value of this property is true, Hibernate will format the SQL
		//that is written to the console.
		jpaProperties.put("hibernate.format_sql", 
				env.getRequiredProperty("hibernate.format_sql")/*"true"*/
				);

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

}