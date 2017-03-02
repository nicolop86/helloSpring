package it.ariadne.controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.ariadne.config.ApplicationContextConfig;
import it.ariadne.model.Language;

public class MainApp {

	public static void main(String[] args) {
		
		// Creating a Context Application object by reading
       // the configuration of the 'AppConfiguration' class.
       @SuppressWarnings("resource")
	ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
 
       System.out.println("----------");
       Language language = (Language) context.getBean("language");
      
       System.out.println("Bean Language: " + language);
       System.out.println("Call language.sayBye(): " + language.getBye());
      
       System.out.println("----------");
      
       GreetingService service = (GreetingService) context.getBean("greetingService");
  
 
       service.sayGreeting();
       service.getGreeting();

	}

}