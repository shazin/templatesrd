package com.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class FreeMarkerMain {

	public static void main(String[] args) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/template"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<String, Object>();

		input.put("title", "title");		
		input.put("greeting", "greeting");

		List<Model> properties = new ArrayList<Model>();
		properties.add(new Model(1, "house", 10000.00d));
		properties.add(new Model(2, "bungalow", 100000.00d));
		properties.add(new Model(3, "condo", 100000.00d));
		input.put("properties", properties);
						
		input.put("resourceBundle", getFrenchResourceBundle());

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("email.ftl");

        long start = System.currentTimeMillis();
        System.out.println("FRENCH ====================================");
        
        /* Merge data-model with template */
        Writer out = new FileWriter("email_fr.html");
        temp.process(input, out);		
        long end = System.currentTimeMillis();
		System.out.printf("%d milliseconds\n", (end-start));
        
        input.put("title", "title");
		input.put("greeting", "greeting");
		
		input.put("resourceBundle", getEnglishResourceBundle());

		properties.clear();
		properties.add(new Model(1, "house", 10000.00d));
		properties.add(new Model(2, "bungalow", 100000.00d));
		properties.add(new Model(3, "condo", 100000.00d));
		input.put("properties", properties);
		
		start = System.currentTimeMillis();
		System.out.println("ENGLISH ====================================");
		
		out = new FileWriter("email_en.html");
		temp.process(input, out);
		
		end = System.currentTimeMillis();
		System.out.printf("%d milliseconds\n", (end-start));
	}

	private static ResourceBundle getEnglishResourceBundle() {
		return getResourceBundle(Locale.ENGLISH);
	}
	
	public static ResourceBundle getResourceBundle(Locale locale) {
		return ResourceBundle.getBundle("com.freemarker.test.messages", locale);
	}
	
	private static ResourceBundle getFrenchResourceBundle() {
		return getResourceBundle(Locale.FRENCH);
	}
}
