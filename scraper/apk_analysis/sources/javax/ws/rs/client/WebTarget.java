package javax.ws.rs.client;

import java.net.URI;
import java.util.Map;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/* loaded from: classes3.dex */
public interface WebTarget extends Configurable<WebTarget> {
    URI getUri();

    UriBuilder getUriBuilder();

    WebTarget matrixParam(String str, Object... objArr);

    WebTarget path(String str);

    WebTarget queryParam(String str, Object... objArr);

    Invocation.Builder request();

    Invocation.Builder request(String... strArr);

    Invocation.Builder request(MediaType... mediaTypeArr);

    WebTarget resolveTemplate(String str, Object obj);

    WebTarget resolveTemplate(String str, Object obj, boolean z);

    WebTarget resolveTemplateFromEncoded(String str, Object obj);

    WebTarget resolveTemplates(Map<String, Object> map);

    WebTarget resolveTemplates(Map<String, Object> map, boolean z);

    WebTarget resolveTemplatesFromEncoded(Map<String, Object> map);
}
