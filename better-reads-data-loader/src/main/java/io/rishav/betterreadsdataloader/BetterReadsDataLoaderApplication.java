package io.rishav.betterreadsdataloader;

import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import io.rishav.betterreadsdataloader.connection.DataStaxAstraProperties;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class BetterReadsDataLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetterReadsDataLoaderApplication.class, args);
	}

	/**
	 * This is necessary to have the Spring Boot app use the Astra secure bundle
	 * to connect to the database
	 */
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

}
