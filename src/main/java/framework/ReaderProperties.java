package framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReaderProperties {
    private static final Logger LOG = LoggerFactory.getLogger(ReaderProperties.class);

    private static ReaderProperties instance = null;
    private final Properties properties;

    private ReaderProperties() {
        properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("run.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public static synchronized ReaderProperties getInstance() {
        if (instance == null) {
            instance = new ReaderProperties();
        }
        return instance;
    }

    public String getValue(String key) {
        return this.properties.getProperty(key, String.format("The key %s does not exists!", key));
    }
}
