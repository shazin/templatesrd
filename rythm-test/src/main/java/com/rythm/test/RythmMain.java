package com.rythm.test;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.rythmengine.Rythm;
import org.rythmengine.RythmEngine;
import org.rythmengine.utils.NamedParams;

public class RythmMain {

	public static void main(String[] args) throws Exception {
				
		List<Model> properties = new ArrayList<Model>();
		properties.add(new Model(1, "house", 10000.00d));
		properties.add(new Model(2, "bungalow", 100000.00d));
		properties.add(new Model(3, "condo", 100000.00d));
		
		long start = System.currentTimeMillis();
		NamedParams np = NamedParams.instance;				
		String englishMail = Rythm.render("src/main/resources/template/email.txt", np.from(np.p("properties", properties), np.p("title", "title"), np.p("greeting", "greeting")));
		
		System.out.println("ENGLISH ==============================================");
				
		Writer out = new FileWriter("email_en.html");
		try {
			out.write(englishMail);
		} finally {
			out.close();
		}
		long end = System.currentTimeMillis();
		System.out.printf("%d milliseconds\n", (end-start));
		

		properties.clear();
		properties.add(new Model(1, "house", 10000.00d));
		properties.add(new Model(2, "bungalow", 100000.00d));
		properties.add(new Model(3, "condo", 100000.00d));
		
		start = System.currentTimeMillis();
		RythmEngine rythmEngine = new RythmEngine();
		Rythm.shutdown();
		Rythm.init(rythmEngine.prepare(Locale.FRENCH));
		String frenchMail = Rythm.render("src/main/resources/template/email.txt", np.from(np.p("properties", properties), np.p("title", "title"), np.p("greeting", "greeting")));
		
		System.out.println("FRENCH ==============================================");
		
		
		out = new FileWriter("email_fr.html");
		try {
			out.write(frenchMail);
		} finally {
			out.close();
		}
		end = System.currentTimeMillis();
		System.out.printf("%d milliseconds\n", (end-start));
	}
	
	
}
