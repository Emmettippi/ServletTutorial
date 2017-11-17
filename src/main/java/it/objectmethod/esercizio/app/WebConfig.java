package it.objectmethod.esercizio.app;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import it.objectmethod.esercizio.dao.ActorDao;
import it.objectmethod.esercizio.dao.FilmDao;
import it.objectmethod.esercizio.dao.jdbc.ActorJdbcTemplate;
import it.objectmethod.esercizio.dao.jdbc.FilmJdbcTemplate;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "it.objectmethod.esercizio.app")
public class WebConfig extends WebMvcConfigurerAdapter{

	@Bean
	@Primary
	public DriverManagerDataSource dataSource() {
		return (DriverManagerDataSource)DataSourceBuilder
	    		.create()
	            .username("root")
	            .password("password")
	            .url("jdbc:mysql://localhost:3306/sakila")
	            .driverClassName("com.mysql.jdbc.Driver")
	            .build();
	}
	
	@Bean
	public ActorDao actordao() {
		return new ActorJdbcTemplate();
	}
	
	@Bean
	public FilmDao filmdao() {
		return new FilmJdbcTemplate();
	}
	
	@Bean
	public InternalResourceViewResolver internalViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1);
		return viewResolver;
	}

	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
		return new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				tomcat.enableNaming();
				TomcatEmbeddedServletContainer container = super.getTomcatEmbeddedServletContainer(tomcat);
				return container;
			}
		};
	}

}
