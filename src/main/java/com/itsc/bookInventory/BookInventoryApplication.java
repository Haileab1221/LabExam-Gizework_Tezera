package com.itsc.bookInventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class BookInventoryApplication {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		BookRepo bookRepo = context.getBean("book", BookRepo.class);
		bookRepo.createDBAndTable();

		Book book = new Book();
		book.setTitle("Oromay");
		book.setAuthor("Baalu Girma");

		bookRepo.insertIntoTable(book);
	}
}
