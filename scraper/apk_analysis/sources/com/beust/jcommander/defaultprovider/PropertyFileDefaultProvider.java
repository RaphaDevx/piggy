package com.beust.jcommander.defaultprovider;

import com.beust.jcommander.IDefaultProvider;
import com.beust.jcommander.ParameterException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/* loaded from: classes3.dex */
public class PropertyFileDefaultProvider implements IDefaultProvider {
    public static final String DEFAULT_FILE_NAME = "jcommander.properties";
    private Properties properties;

    public PropertyFileDefaultProvider() {
        init(DEFAULT_FILE_NAME);
    }

    public PropertyFileDefaultProvider(String str) {
        init(str);
    }

    private void init(String str) {
        try {
            this.properties = new Properties();
            URL systemResource = ClassLoader.getSystemResource(str);
            if (systemResource != null) {
                this.properties.load(systemResource.openStream());
                return;
            }
            throw new ParameterException("Could not find property file: " + str + " on the class path");
        } catch (IOException unused) {
            throw new ParameterException("Could not open property file: " + str);
        }
    }

    @Override // com.beust.jcommander.IDefaultProvider
    public String getDefaultValueFor(String str) {
        int i = 0;
        while (i < str.length() && !Character.isLetterOrDigit(str.charAt(i))) {
            i++;
        }
        return this.properties.getProperty(str.substring(i));
    }
}
