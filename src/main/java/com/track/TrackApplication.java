package com.track;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/*@PropertySources({
		@PropertySource(value = "classpath:application.properties"),
		@PropertySource(value = "file:../conf/external.properties", ignoreResourceNotFound = true)
})*/
@PropertySource(value = "file:../conf/external.properties")
@SpringBootApplication
public class TrackApplication {

	private static final Logger logger = LoggerFactory.getLogger(TrackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TrackApplication.class, args);

	}

}
