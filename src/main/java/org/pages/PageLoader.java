package org.pages;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageLoader {
    private static final String HTML_DIR = "/resources/";

    private static PageLoader pageLoader;
    private final Configuration cfg;

    public static PageLoader instance() {
        if (pageLoader == null)
            pageLoader = new PageLoader();
        return pageLoader;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer writer = new StringWriter();
        try {
            cfg.setDirectoryForTemplateLoading(new File("C:/Users/Andrei/IdeaProjects/JettyServer/src/main/resources"));
            Template template = cfg.getTemplate(filename);
            template.process(data, writer);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    private PageLoader() {
        cfg = new Configuration();
    }
}
