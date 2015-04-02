package com.irwin13.winwork.velocity;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.DisplayTool;
import org.apache.velocity.tools.generic.EscapeTool;
import org.apache.velocity.tools.generic.NumberTool;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

/**
 * Created by irwin on 31/03/2015.
 */
public class VelocityWrapper {

    public static final String FILE = "file";
    public static final String CLASSPATH = "classpath";
    public static final String URL = "url";

    private static boolean initialized = false;

    public static Properties initVelocity(String type, String activateCache, String modificationCheckInterval, String
            rootResource) {

        if (CLASSPATH.equalsIgnoreCase(type)) {
            return VelocityWrapper.initClasspathLoader(activateCache, modificationCheckInterval);

        } else if (FILE.equalsIgnoreCase(type)) {
            return VelocityWrapper.initFileLoader(activateCache, modificationCheckInterval, rootResource);

        } else if (URL.equalsIgnoreCase(type)) {
            return VelocityWrapper.initUrlLoader(activateCache, modificationCheckInterval, rootResource);
        } else {
            throw new RuntimeException("Unexpected Velocity Loader Type. It should be one of the following : FILE or " +
                    "URL or CLASSPATH");
        }
    }

    private static Properties initClasspathLoader(String activateCache, String modificationCheckInterval) {
        if (initialized) {
            throw new RuntimeException("Velocity already initialized");
        }

        Properties velocityProperties = new Properties();

        velocityProperties.put("input.encoding", "UTF-8");
        velocityProperties.put("output.encoding", "UTF-8");
        velocityProperties.put("velocimacro.library.autoreload", "false");
        velocityProperties.put("resource.manager.logwhenfound", "false");

        velocityProperties.put("resource.loader", "class");

        velocityProperties.put("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        velocityProperties.put("class.resource.loader.cache", activateCache);
        velocityProperties.put("class.resource.loader.modificationCheckInterval", modificationCheckInterval);

        Velocity.init(velocityProperties);
        initialized = true;

        return velocityProperties;
    }

    private static Properties initUrlLoader(String activateCache, String modificationCheckInterval, String rootUrl) {
        if (initialized) {
            throw new RuntimeException("Velocity already initialized");
        }

        Properties velocityProperties = new Properties();

        velocityProperties.put("input.encoding", "UTF-8");
        velocityProperties.put("output.encoding", "UTF-8");
        velocityProperties.put("velocimacro.library.autoreload", "false");
        velocityProperties.put("url.resource.manager.logwhenfound", "false");

        velocityProperties.put("resource.loader", "url");

        velocityProperties.put("url.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.URLResourceLoader");
        velocityProperties.put("url.resource.loader.cache", activateCache);
        velocityProperties.put("url.resource.loader.modificationCheckInterval", modificationCheckInterval);
        velocityProperties.put("url.resource.loader.root", rootUrl);

        Velocity.init(velocityProperties);
        initialized = true;

        return velocityProperties;
    }

    private static Properties initFileLoader(String activateCache, String modificationCheckInterval, String rootPath) {
        if (initialized) {
            throw new RuntimeException("Velocity already initialized");
        }

        Properties velocityProperties = new Properties();

        velocityProperties.put("input.encoding", "UTF-8");
        velocityProperties.put("output.encoding", "UTF-8");
        velocityProperties.put("velocimacro.library.autoreload", "false");
        velocityProperties.put("url.resource.manager.logwhenfound", "false");

        velocityProperties.put("resource.loader", "file");

        velocityProperties.put("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        velocityProperties.put("file.resource.loader.cache", activateCache);
        velocityProperties.put("file.resource.loader.modificationCheckInterval", modificationCheckInterval);
        velocityProperties.put("file.resource.loader.path", rootPath);

        Velocity.init(velocityProperties);
        initialized = true;

        return velocityProperties;
    }

    public String generateTemplate(String vmName, Map<String, Object> velocityContext) {

        if (!initialized) {
            throw new RuntimeException("Velocity not initialized");
        }

        VelocityContext context = commonVelocityContext();
        Writer writer = new StringWriter();

        if (velocityContext != null && !velocityContext.isEmpty()) {
            for (Map.Entry<String, Object> entry : velocityContext.entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }
        }

        Velocity.getTemplate(vmName).merge(context, writer);
        return writer.toString();
    }

    public String generateWebPage(String vmName, VelocityConfiguration config, Map<String, Object> velocityContext) {

        if (!initialized) {
            throw new RuntimeException("Velocity not initialized");
        }

        VelocityContext context = commonVelocityContext();
        webPageVelocityContext(context, config);
        Writer writer = new StringWriter();

        if (velocityContext != null && !velocityContext.isEmpty()) {
            for (Map.Entry<String, Object> entry : velocityContext.entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }
        }

        Velocity.getTemplate(vmName).merge(context, writer);
        return writer.toString();
    }

    private VelocityContext commonVelocityContext() {
        VelocityContext context = new VelocityContext();
        context.put("dateTool", new DateTool());
        context.put("displayTool", new DisplayTool());
        context.put("escapeTool", new EscapeTool());
        context.put("numberTool", new NumberTool());

        return context;
    }

    private VelocityContext webPageVelocityContext(VelocityContext context, VelocityConfiguration config) {
        context.put("assetsRootUrl", config.getAssetsRootUrl());
        context.put("contextRootUrl", config.getContextRootUrl());
        return context;
    }

}
