package javax.ws.rs.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import javax.ws.rs.ext.RuntimeDelegate;
import net.openid.appauth.AuthorizationRequest;

/* loaded from: classes4.dex */
public class MediaType {
    public static final String APPLICATION_ATOM_XML = "application/atom+xml";
    public static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_JSON_PATCH_JSON = "application/json-patch+json";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String APPLICATION_SVG_XML = "application/svg+xml";
    public static final String APPLICATION_XHTML_XML = "application/xhtml+xml";
    public static final String APPLICATION_XML = "application/xml";
    public static final String CHARSET_PARAMETER = "charset";
    public static final String MEDIA_TYPE_WILDCARD = "*";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String SERVER_SENT_EVENTS = "text/event-stream";
    public static final String TEXT_HTML = "text/html";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String TEXT_XML = "text/xml";
    public static final String WILDCARD = "*/*";
    private Map<String, String> parameters;
    private String subtype;
    private String type;
    public static final MediaType WILDCARD_TYPE = new MediaType();
    public static final MediaType APPLICATION_XML_TYPE = new MediaType("application", "xml");
    public static final MediaType APPLICATION_ATOM_XML_TYPE = new MediaType("application", "atom+xml");
    public static final MediaType APPLICATION_XHTML_XML_TYPE = new MediaType("application", "xhtml+xml");
    public static final MediaType APPLICATION_SVG_XML_TYPE = new MediaType("application", "svg+xml");
    public static final MediaType APPLICATION_JSON_TYPE = new MediaType("application", "json");
    public static final MediaType APPLICATION_FORM_URLENCODED_TYPE = new MediaType("application", "x-www-form-urlencoded");
    public static final MediaType MULTIPART_FORM_DATA_TYPE = new MediaType("multipart", "form-data");
    public static final MediaType APPLICATION_OCTET_STREAM_TYPE = new MediaType("application", "octet-stream");
    public static final MediaType TEXT_PLAIN_TYPE = new MediaType("text", AuthorizationRequest.CODE_CHALLENGE_METHOD_PLAIN);
    public static final MediaType TEXT_XML_TYPE = new MediaType("text", "xml");
    public static final MediaType TEXT_HTML_TYPE = new MediaType("text", "html");
    public static final MediaType SERVER_SENT_EVENTS_TYPE = new MediaType("text", "event-stream");
    public static final MediaType APPLICATION_JSON_PATCH_JSON_TYPE = new MediaType("application", "json-patch+json");

    public static MediaType valueOf(String str) {
        return (MediaType) RuntimeDelegate.getInstance().createHeaderDelegate(MediaType.class).fromString(str);
    }

    private static TreeMap<String, String> createParametersMap(Map<String, String> map) {
        TreeMap<String, String> treeMap = new TreeMap<>(new Comparator<String>() { // from class: javax.ws.rs.core.MediaType.1
            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                return str.compareToIgnoreCase(str2);
            }
        });
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                treeMap.put(entry.getKey().toLowerCase(), entry.getValue());
            }
        }
        return treeMap;
    }

    public MediaType(String str, String str2, Map<String, String> map) {
        this(str, str2, null, createParametersMap(map));
    }

    public MediaType(String str, String str2) {
        this(str, str2, null, null);
    }

    public MediaType(String str, String str2, String str3) {
        this(str, str2, str3, null);
    }

    public MediaType() {
        this("*", "*", null, null);
    }

    private MediaType(String str, String str2, String str3, Map<String, String> map) {
        this.type = str == null ? "*" : str;
        this.subtype = str2 == null ? "*" : str2;
        map = map == null ? new TreeMap<>(new Comparator<String>() { // from class: javax.ws.rs.core.MediaType.2
            @Override // java.util.Comparator
            public int compare(String str4, String str5) {
                return str4.compareToIgnoreCase(str5);
            }
        }) : map;
        if (str3 != null && !str3.isEmpty()) {
            map.put(CHARSET_PARAMETER, str3);
        }
        this.parameters = Collections.unmodifiableMap(map);
    }

    public String getType() {
        return this.type;
    }

    public boolean isWildcardType() {
        return getType().equals("*");
    }

    public String getSubtype() {
        return this.subtype;
    }

    public boolean isWildcardSubtype() {
        return getSubtype().equals("*");
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public MediaType withCharset(String str) {
        return new MediaType(this.type, this.subtype, str, createParametersMap(this.parameters));
    }

    public boolean isCompatible(MediaType mediaType) {
        return mediaType != null && (this.type.equals("*") || mediaType.type.equals("*") || ((this.type.equalsIgnoreCase(mediaType.type) && (this.subtype.equals("*") || mediaType.subtype.equals("*"))) || (this.type.equalsIgnoreCase(mediaType.type) && this.subtype.equalsIgnoreCase(mediaType.subtype))));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaType)) {
            return false;
        }
        MediaType mediaType = (MediaType) obj;
        return this.type.equalsIgnoreCase(mediaType.type) && this.subtype.equalsIgnoreCase(mediaType.subtype) && this.parameters.equals(mediaType.parameters);
    }

    public int hashCode() {
        return (this.type.toLowerCase() + this.subtype.toLowerCase()).hashCode() + this.parameters.hashCode();
    }

    public String toString() {
        return RuntimeDelegate.getInstance().createHeaderDelegate(MediaType.class).toString(this);
    }
}
