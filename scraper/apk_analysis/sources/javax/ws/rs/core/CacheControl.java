package javax.ws.rs.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.ext.RuntimeDelegate;

/* loaded from: classes4.dex */
public class CacheControl {
    private static final RuntimeDelegate.HeaderDelegate<CacheControl> HEADER_DELEGATE = RuntimeDelegate.getInstance().createHeaderDelegate(CacheControl.class);
    private Map<String, String> cacheExtension;
    private List<String> noCacheFields;
    private List<String> privateFields;
    private int maxAge = -1;
    private int sMaxAge = -1;
    private boolean privateFlag = false;
    private boolean noCache = false;
    private boolean noStore = false;
    private boolean noTransform = true;
    private boolean mustRevalidate = false;
    private boolean proxyRevalidate = false;

    public static CacheControl valueOf(String str) {
        return HEADER_DELEGATE.fromString(str);
    }

    public boolean isMustRevalidate() {
        return this.mustRevalidate;
    }

    public void setMustRevalidate(boolean z) {
        this.mustRevalidate = z;
    }

    public boolean isProxyRevalidate() {
        return this.proxyRevalidate;
    }

    public void setProxyRevalidate(boolean z) {
        this.proxyRevalidate = z;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    public void setMaxAge(int i) {
        this.maxAge = i;
    }

    public int getSMaxAge() {
        return this.sMaxAge;
    }

    public void setSMaxAge(int i) {
        this.sMaxAge = i;
    }

    public List<String> getNoCacheFields() {
        if (this.noCacheFields == null) {
            this.noCacheFields = new ArrayList();
        }
        return this.noCacheFields;
    }

    public void setNoCache(boolean z) {
        this.noCache = z;
    }

    public boolean isNoCache() {
        return this.noCache;
    }

    public boolean isPrivate() {
        return this.privateFlag;
    }

    public List<String> getPrivateFields() {
        if (this.privateFields == null) {
            this.privateFields = new ArrayList();
        }
        return this.privateFields;
    }

    public void setPrivate(boolean z) {
        this.privateFlag = z;
    }

    public boolean isNoTransform() {
        return this.noTransform;
    }

    public void setNoTransform(boolean z) {
        this.noTransform = z;
    }

    public boolean isNoStore() {
        return this.noStore;
    }

    public void setNoStore(boolean z) {
        this.noStore = z;
    }

    public Map<String, String> getCacheExtension() {
        if (this.cacheExtension == null) {
            this.cacheExtension = new HashMap();
        }
        return this.cacheExtension;
    }

    public String toString() {
        return HEADER_DELEGATE.toString(this);
    }

    public int hashCode() {
        return ((((((((((((((((((((287 + (this.privateFlag ? 1 : 0)) * 41) + (this.noCache ? 1 : 0)) * 41) + (this.noStore ? 1 : 0)) * 41) + (this.noTransform ? 1 : 0)) * 41) + (this.mustRevalidate ? 1 : 0)) * 41) + (this.proxyRevalidate ? 1 : 0)) * 41) + this.maxAge) * 41) + this.sMaxAge) * 41) + hashCodeOf(this.privateFields)) * 41) + hashCodeOf(this.noCacheFields)) * 41) + hashCodeOf(this.cacheExtension);
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CacheControl cacheControl = (CacheControl) obj;
        return this.privateFlag == cacheControl.privateFlag && this.noCache == cacheControl.noCache && this.noStore == cacheControl.noStore && this.noTransform == cacheControl.noTransform && this.mustRevalidate == cacheControl.mustRevalidate && this.proxyRevalidate == cacheControl.proxyRevalidate && this.maxAge == cacheControl.maxAge && this.sMaxAge == cacheControl.sMaxAge && !notEqual(this.privateFields, cacheControl.privateFields) && !notEqual(this.noCacheFields, cacheControl.noCacheFields) && !notEqual(this.cacheExtension, cacheControl.cacheExtension);
    }

    private static boolean notEqual(Collection<?> collection, Collection<?> collection2) {
        if (collection == collection2) {
            return false;
        }
        if (collection == null) {
            return !collection2.isEmpty();
        }
        if (collection2 == null) {
            return !collection.isEmpty();
        }
        return !collection.equals(collection2);
    }

    private static boolean notEqual(Map<?, ?> map, Map<?, ?> map2) {
        if (map == map2) {
            return false;
        }
        if (map == null) {
            return !map2.isEmpty();
        }
        if (map2 == null) {
            return !map.isEmpty();
        }
        return !map.equals(map2);
    }

    private static int hashCodeOf(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return 0;
        }
        return collection.hashCode();
    }

    private static int hashCodeOf(Map<?, ?> map) {
        if (map == null || map.isEmpty()) {
            return 0;
        }
        return map.hashCode();
    }
}
