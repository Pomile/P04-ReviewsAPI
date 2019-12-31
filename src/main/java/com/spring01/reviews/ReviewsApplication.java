package com.spring01.reviews;

import com.github.mongobee.Mongobee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;


@SpringBootApplication
public class ReviewsApplication {
	@Value("${mongodb.database}")
	private String databaseName;
	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);
	}

	@Bean
	public Mongobee mongobee() throws Exception {

		Mongobee runner = new Mongobee("mongodb://localhost:27017/");
		runner.setDbName(databaseName);// host must be set if not set in URI
		runner.setChangeLogsScanPackage("com.spring01.reviews.changelogs"); // the package to be scanned for changesets
		return runner;
	}
}