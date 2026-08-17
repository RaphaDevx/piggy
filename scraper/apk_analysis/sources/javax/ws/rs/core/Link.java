package javax.ws.rs.core;

import io.sentry.rrweb.RRWebMetaEvent;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.ext.RuntimeDelegate;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

/* loaded from: classes4.dex */
public abstract class Link {
    public static final String REL = "rel";
    public static final String TITLE = "title";
    public static final String TYPE = "type";

    public interface Builder {
        Builder baseUri(String str);

        Builder baseUri(URI uri);

        Link build(Object... objArr);

        Link buildRelativized(URI uri, Object... objArr);

        Builder link(String str);

        Builder link(Link link);

        Builder param(String str, String str2);

        Builder rel(String str);

        Builder title(String str);

        Builder type(String str);

        Builder uri(String str);

        Builder uri(URI uri);

        Builder uriBuilder(UriBuilder uriBuilder);
    }

    public abstract Map<String, String> getParams();

    public abstract String getRel();

    public abstract List<String> getRels();

    public abstract String getTitle();

    public abstract String getType();

    public abstract URI getUri();

    public abstract UriBuilder getUriBuilder();

    public abstract String toString();

    public static Link valueOf(String str) {
        Builder createLinkBuilder = RuntimeDelegate.getInstance().createLinkBuilder();
        createLinkBuilder.link(str);
        return createLinkBuilder.build(new Object[0]);
    }

    public static Builder fromUri(URI uri) {
        Builder createLinkBuilder = RuntimeDelegate.getInstance().createLinkBuilder();
        createLinkBuilder.uri(uri);
        return createLinkBuilder;
    }

    public static Builder fromUri(String str) {
        Builder createLinkBuilder = RuntimeDelegate.getInstance().createLinkBuilder();
        createLinkBuilder.uri(str);
        return createLinkBuilder;
    }

    public static Builder fromUriBuilder(UriBuilder uriBuilder) {
        Builder createLinkBuilder = RuntimeDelegate.getInstance().createLinkBuilder();
        createLinkBuilder.uriBuilder(uriBuilder);
        return createLinkBuilder;
    }

    public static Builder fromLink(Link link) {
        Builder createLinkBuilder = RuntimeDelegate.getInstance().createLinkBuilder();
        createLinkBuilder.link(link);
        return createLinkBuilder;
    }

    public static Builder fromPath(String str) {
        return fromUriBuilder(UriBuilder.fromPath(str));
    }

    public static Builder fromResource(Class<?> cls) {
        return fromUriBuilder(UriBuilder.fromResource(cls));
    }

    public static Builder fromMethod(Class<?> cls, String str) {
        return fromUriBuilder(UriBuilder.fromMethod(cls, str));
    }

    public static class JaxbLink {
        private Map<QName, Object> params;
        private URI uri;

        public JaxbLink() {
        }

        public JaxbLink(URI uri) {
            this.uri = uri;
        }

        public JaxbLink(URI uri, Map<QName, Object> map) {
            this.uri = uri;
            this.params = map;
        }

        @XmlAttribute(name = RRWebMetaEvent.JsonKeys.HREF)
        public URI getUri() {
            return this.uri;
        }

        @XmlAnyAttribute
        public Map<QName, Object> getParams() {
            if (this.params == null) {
                this.params = new HashMap();
            }
            return this.params;
        }

        void setUri(URI uri) {
            this.uri = uri;
        }

        void setParams(Map<QName, Object> map) {
            this.params = map;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof JaxbLink)) {
                return false;
            }
            JaxbLink jaxbLink = (JaxbLink) obj;
            URI uri = this.uri;
            if (uri == null ? jaxbLink.uri != null : !uri.equals(jaxbLink.uri)) {
                return false;
            }
            Map<QName, Object> map = this.params;
            Map<QName, Object> map2 = jaxbLink.params;
            if (map == map2) {
                return true;
            }
            if (map == null) {
                return map2.isEmpty();
            }
            if (map2 == null) {
                return map.isEmpty();
            }
            return map.equals(map2);
        }

        public int hashCode() {
            URI uri = this.uri;
            int i = 0;
            int hashCode = (uri != null ? uri.hashCode() : 0) * 31;
            Map<QName, Object> map = this.params;
            if (map != null && !map.isEmpty()) {
                i = this.params.hashCode();
            }
            return hashCode + i;
        }
    }

    public static class JaxbAdapter extends XmlAdapter<JaxbLink, Link> {
        public Link unmarshal(JaxbLink jaxbLink) {
            Builder fromUri = Link.fromUri(jaxbLink.getUri());
            for (Map.Entry<QName, Object> entry : jaxbLink.getParams().entrySet()) {
                fromUri.param(entry.getKey().getLocalPart(), entry.getValue().toString());
            }
            return fromUri.build(new Object[0]);
        }

        public JaxbLink marshal(Link link) {
            JaxbLink jaxbLink = new JaxbLink(link.getUri());
            for (Map.Entry<String, String> entry : link.getParams().entrySet()) {
                jaxbLink.getParams().put(new QName("", entry.getKey()), entry.getValue());
            }
            return jaxbLink;
        }
    }
}
