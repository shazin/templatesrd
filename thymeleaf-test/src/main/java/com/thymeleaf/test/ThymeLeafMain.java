package com.thymeleaf.test;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.resourceresolver.FileResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Hello world!
 *
 */
public class ThymeLeafMain {

	public static void main(String[] args) throws Exception {		
		TemplateResolver templateResolver = new TemplateResolver();
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setResourceResolver(new FileResourceResolver());
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setMessageResolver(new StandardMessageResolver());
		templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setCacheTTLMs(3600000L);

		Map<String, Object> input = new HashMap<String, Object>();

		List<Model> properties = new ArrayList<Model>();
		properties.add(new Model(1, 10000.00d, "house"));
		properties.add(new Model(2, 100000.00d, "bungalow"));
		properties.add(new Model(3, 100000.00d, "condo"));
		input.put("properties", properties);

		long start = System.currentTimeMillis();
		System.out.println("FRENCH ====================================");
		Context ctx = new Context(Locale.FRENCH, input);
		Writer out = null;
		try {
			out = new FileWriter("email_fr.html");
			templateEngine.process("src/main/resources/template/email.html",
					ctx, out);
		} finally {
			out.close();
		}
		long end = System.currentTimeMillis();
		System.out.printf("%d milliseconds\n", (end-start));

		start = System.currentTimeMillis();
		System.out.println("ENGLISH ====================================");
		ctx = new Context(Locale.ENGLISH, input);
		try {
			out = new FileWriter("email_en.html");
			templateEngine.process("src/main/resources/template/email.html",
					ctx, out);
		} finally {
			out.close();
		}
		end = System.currentTimeMillis();
		System.out.printf("%d milliseconds\n", (end-start));

	}
}
