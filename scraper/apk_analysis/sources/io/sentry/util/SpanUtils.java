package io.sentry.util;

import io.sentry.FilterString;
import io.sentry.SentryOpenTelemetryMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public final class SpanUtils {
    private static final Map<String, Boolean> ignoredSpanDecisionsCache = new ConcurrentHashMap();

    public static List<String> ignoredSpanOriginsForOpenTelemetry(SentryOpenTelemetryMode sentryOpenTelemetryMode) {
        ArrayList arrayList = new ArrayList();
        if (SentryOpenTelemetryMode.AGENT == sentryOpenTelemetryMode || SentryOpenTelemetryMode.AGENTLESS_SPRING == sentryOpenTelemetryMode) {
            arrayList.add("auto.http.spring_jakarta.webmvc");
            arrayList.add("auto.http.spring.webmvc");
            arrayList.add("auto.http.spring7.webmvc");
            arrayList.add("auto.spring_jakarta.webflux");
            arrayList.add("auto.spring.webflux");
            arrayList.add("auto.spring7.webflux");
            arrayList.add("auto.db.jdbc");
            arrayList.add("auto.http.spring_jakarta.webclient");
            arrayList.add("auto.http.spring.webclient");
            arrayList.add("auto.http.spring7.webclient");
            arrayList.add("auto.http.spring_jakarta.restclient");
            arrayList.add("auto.http.spring.restclient");
            arrayList.add("auto.http.spring7.restclient");
            arrayList.add("auto.http.spring_jakarta.resttemplate");
            arrayList.add("auto.http.spring.resttemplate");
            arrayList.add("auto.http.spring7.resttemplate");
            arrayList.add("auto.http.openfeign");
            arrayList.add("auto.http.ktor-client");
        }
        if (SentryOpenTelemetryMode.AGENT == sentryOpenTelemetryMode) {
            arrayList.add("auto.graphql.graphql");
            arrayList.add("auto.graphql.graphql22");
        }
        return arrayList;
    }

    public static boolean isIgnored(List<FilterString> list, String str) {
        if (str != null && list != null && !list.isEmpty()) {
            Map<String, Boolean> map = ignoredSpanDecisionsCache;
            if (map.containsKey(str)) {
                return map.get(str).booleanValue();
            }
            Iterator<FilterString> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().getFilterString().equalsIgnoreCase(str)) {
                    ignoredSpanDecisionsCache.put(str, true);
                    return true;
                }
            }
            Iterator<FilterString> it2 = list.iterator();
            while (it2.hasNext()) {
                if (it2.next().matches(str)) {
                    ignoredSpanDecisionsCache.put(str, true);
                    return true;
                }
                continue;
            }
            ignoredSpanDecisionsCache.put(str, false);
        }
        return false;
    }
}
