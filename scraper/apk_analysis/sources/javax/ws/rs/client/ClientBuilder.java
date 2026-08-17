package javax.ws.rs.client;

import com.fasterxml.jackson.core.JsonPointer;
import java.security.KeyStore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.Configuration;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes3.dex */
public abstract class ClientBuilder implements Configurable<ClientBuilder> {
    private static final String JAXRS_DEFAULT_CLIENT_BUILDER = "org.glassfish.jersey.client.JerseyClientBuilder";
    public static final String JAXRS_DEFAULT_CLIENT_BUILDER_PROPERTY = "javax.ws.rs.client.ClientBuilder";

    public abstract Client build();

    public abstract ClientBuilder connectTimeout(long j, TimeUnit timeUnit);

    public abstract ClientBuilder executorService(ExecutorService executorService);

    public abstract ClientBuilder hostnameVerifier(HostnameVerifier hostnameVerifier);

    public abstract ClientBuilder keyStore(KeyStore keyStore, char[] cArr);

    public abstract ClientBuilder readTimeout(long j, TimeUnit timeUnit);

    public abstract ClientBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService);

    public abstract ClientBuilder sslContext(SSLContext sSLContext);

    public abstract ClientBuilder trustStore(KeyStore keyStore);

    public abstract ClientBuilder withConfig(Configuration configuration);

    protected ClientBuilder() {
    }

    public static ClientBuilder newBuilder() {
        try {
            Object find = FactoryFinder.find(JAXRS_DEFAULT_CLIENT_BUILDER_PROPERTY, JAXRS_DEFAULT_CLIENT_BUILDER, ClientBuilder.class);
            if (!(find instanceof ClientBuilder)) {
                String str = ClientBuilder.class.getName().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, JsonPointer.SEPARATOR) + ".class";
                ClassLoader classLoader = ClientBuilder.class.getClassLoader();
                if (classLoader == null) {
                    classLoader = ClassLoader.getSystemClassLoader();
                }
                throw new LinkageError("ClassCastException: attempting to cast" + find.getClass().getClassLoader().getResource(str) + " to " + classLoader.getResource(str));
            }
            return (ClientBuilder) find;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Client newClient() {
        return newBuilder().build();
    }

    public static Client newClient(Configuration configuration) {
        return newBuilder().withConfig(configuration).build();
    }

    public ClientBuilder keyStore(KeyStore keyStore, String str) {
        return keyStore(keyStore, str.toCharArray());
    }
}
