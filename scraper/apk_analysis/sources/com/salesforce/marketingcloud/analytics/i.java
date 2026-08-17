package com.salesforce.marketingcloud.analytics;

import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class i implements j, k, AnalyticsManager, g, f, m, n, l {
    public void a(long j) {
    }

    @Override // com.salesforce.marketingcloud.analytics.n
    public void a(e eVar, Event... eventArr) {
    }

    @Override // com.salesforce.marketingcloud.analytics.l
    public void a(l.a aVar, JSONObject jSONObject) {
    }

    public void a(Region region) {
    }

    @Override // com.salesforce.marketingcloud.analytics.f
    public void a(InAppMessage inAppMessage) {
    }

    @Override // com.salesforce.marketingcloud.analytics.f
    public void a(InAppMessage inAppMessage, com.salesforce.marketingcloud.messages.iam.j jVar) {
    }

    @Override // com.salesforce.marketingcloud.analytics.f
    public void a(InAppMessage inAppMessage, JSONObject jSONObject) {
    }

    public void a(InboxMessage inboxMessage) {
    }

    @Override // com.salesforce.marketingcloud.analytics.j
    public void a(NotificationMessage notificationMessage) {
    }

    @Override // com.salesforce.marketingcloud.analytics.j
    public void a(NotificationMessage notificationMessage, int i, String str, String str2) {
    }

    @Override // com.salesforce.marketingcloud.analytics.j
    public void a(NotificationMessage notificationMessage, boolean z) {
    }

    @Override // com.salesforce.marketingcloud.analytics.j
    public void a(com.salesforce.marketingcloud.push.f fVar, String str) {
    }

    @Override // com.salesforce.marketingcloud.analytics.m
    public void a(String str, String str2, String str3, String str4) {
    }

    @Override // com.salesforce.marketingcloud.analytics.f
    public void a(String str, String str2, List<String> list) {
    }

    @Override // com.salesforce.marketingcloud.analytics.j
    public void a(Map<String, String> map) {
    }

    @Override // com.salesforce.marketingcloud.analytics.m
    public void a(JSONObject jSONObject) {
    }

    public abstract void a(boolean z);

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public boolean areAnalyticsEnabled() {
        return true;
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public boolean arePiAnalyticsEnabled() {
        return true;
    }

    public void b(long j) {
    }

    public void b(Region region) {
    }

    @Override // com.salesforce.marketingcloud.analytics.f
    public void b(InAppMessage inAppMessage) {
    }

    @Override // com.salesforce.marketingcloud.analytics.j
    public void b(NotificationMessage notificationMessage) {
    }

    public void b(Map<String, String> map) {
    }

    @Override // com.salesforce.marketingcloud.analytics.m
    public void b(JSONObject jSONObject) {
    }

    public void c(long j) {
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public void disableAnalytics() {
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public void disablePiAnalytics() {
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public void enableAnalytics() {
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public void enablePiAnalytics() {
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public String getPiIdentifier() {
        return null;
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public void setPiIdentifier(String str) {
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public void trackCartContents(PiCart piCart) {
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public void trackCartConversion(PiOrder piOrder) {
    }

    public void trackInboxOpenEvent(InboxMessage inboxMessage) {
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public void trackPageView(String str, String str2, String str3, String str4) {
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public final void trackPageView(String str) {
        trackPageView(str, null, null, null);
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public final void trackPageView(String str, String str2) {
        trackPageView(str, str2, null, null);
    }

    @Override // com.salesforce.marketingcloud.analytics.AnalyticsManager
    public final void trackPageView(String str, String str2, String str3) {
        trackPageView(str, str2, str3, null);
    }
}
