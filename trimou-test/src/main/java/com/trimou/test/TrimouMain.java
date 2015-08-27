package com.trimou.test;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.trimou.Mustache;
import org.trimou.engine.MustacheEngine;
import org.trimou.engine.MustacheEngineBuilder;
import org.trimou.engine.config.Configuration;
import org.trimou.engine.config.ConfigurationKey;
import org.trimou.engine.locale.LocaleSupport;
import org.trimou.engine.locator.FileSystemTemplateLocator;
import org.trimou.handlebars.i18n.ResourceBundleHelper;

public class TrimouMain {

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		MustacheEngine engine = MustacheEngineBuilder
				.newBuilder()	
				.registerHelper("msg", new ResourceBundleHelper("com.trimou.test.messages"))
				.addTemplateLocator(
						new FileSystemTemplateLocator(1,
								"src/main/resources/template", "txt"))
				.build();
		Mustache mustache = engine.getMustache("email");
		Map<String, Object> inputs = new HashMap();
		List<Model> properties = new ArrayList<Model>();
		properties.add(new Model(1, 10000.00d, "house"));
		properties.add(new Model(2, 100000.00d, "bungalow"));
		properties.add(new Model(3, 100000.00d, "condo"));
		inputs.put("properties", properties);
		String englishEmail = mustache.render(inputs);
		
		System.out.println("ENGLISH ============================================");
				
		Writer out = new FileWriter("email_en.html");
		try {
			out.write(englishEmail);
		} finally {
			out.close();
		}
		long end = System.currentTimeMillis();
		System.out.printf("%d milliseconds\n", (end-start));
		
		start = System.currentTimeMillis();
		engine = MustacheEngineBuilder
				.newBuilder()	
				.registerHelper("msg", new ResourceBundleHelper("com.trimou.test.messages"))
				.setLocaleSupport(new LocaleSupport() {

					public void init(Configuration configuration) {
												
					}

					public Set<ConfigurationKey> getConfigurationKeys() {
						return new HashSet<ConfigurationKey>();
					}

					public Locale getCurrentLocale() {
						return Locale.FRENCH;
					}
					
				})
				.addTemplateLocator(
						new FileSystemTemplateLocator(1,
								"src/main/resources/template", "txt"))
				.build();
		
		mustache = engine.getMustache("email");
		String frenchEmail = mustache.render(inputs);
		
		System.out.println("FRENCH ============================================");
				
		out = new FileWriter("email_fr.html");
		try {
			out.write(frenchEmail);
		} finally {
			out.close();
		}
		end = System.currentTimeMillis();
		System.out.printf("%d milliseconds\n", (end-start));
		
		
		
	}
}
