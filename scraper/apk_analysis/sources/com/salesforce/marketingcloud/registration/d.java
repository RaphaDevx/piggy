package com.salesforce.marketingcloud.registration;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.http.d;
import com.salesforce.marketingcloud.internal.m;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.registration.RegistrationManager;
import com.salesforce.marketingcloud.registration.e;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventSubscriber;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import com.salesforce.marketingcloud.storage.h;
import com.salesforce.marketingcloud.util.j;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class d implements com.salesforce.marketingcloud.e, RegistrationManager, com.salesforce.marketingcloud.behaviors.b, b.InterfaceC0044b, d.c, e.f, EventSubscriber {
    private static final EnumSet<com.salesforce.marketingcloud.behaviors.a> o = EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_TIME_ZONE_CHANGED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_TOKEN_REFRESHED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED);
    private final Context d;
    private final MarketingCloudConfig e;
    private final h f;
    private final com.salesforce.marketingcloud.behaviors.c g;
    private final com.salesforce.marketingcloud.alarms.b h;
    private final com.salesforce.marketingcloud.http.d i;
    private final PushMessageManager j;
    private final n k;
    private final SFMCSdkComponents l;
    private final f m;
    private e n;

    class a implements com.salesforce.marketingcloud.storage.b {
        a() {
        }

        @Override // com.salesforce.marketingcloud.storage.b
        public void a() {
        }

        @Override // com.salesforce.marketingcloud.storage.b
        public void a(String str) {
        }

        @Override // com.salesforce.marketingcloud.storage.b
        public void a(String str, String str2) {
        }

        @Override // com.salesforce.marketingcloud.storage.b
        public String b(String str, String str2) {
            return str2;
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[a.EnumC0042a.values().length];
            b = iArr;
            try {
                iArr[a.EnumC0042a.c.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            int[] iArr2 = new int[com.salesforce.marketingcloud.behaviors.a.values().length];
            a = iArr2;
            try {
                iArr2[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_TIME_ZONE_CHANGED.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED.ordinal()] = 5;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED.ordinal()] = 6;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED.ordinal()] = 7;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_TOKEN_REFRESHED.ordinal()] = 8;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    static class c implements RegistrationManager.Editor {
        c() {
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor addTag(String str) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor addTags(Iterable<String> iterable) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor addTags(String... strArr) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor clearAttribute(String str) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor clearAttributes(Iterable<String> iterable) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor clearAttributes(String... strArr) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor clearTags() {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public boolean commit() {
            return false;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor removeTag(String str) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor removeTags(Iterable<String> iterable) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor removeTags(String... strArr) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor setAttribute(String str, String str2) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor setContactKey(String str) {
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor setSignedString(String str) {
            return this;
        }
    }

    public d(Context context, MarketingCloudConfig marketingCloudConfig, h hVar, f fVar, com.salesforce.marketingcloud.behaviors.c cVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.d dVar, PushMessageManager pushMessageManager, n nVar) {
        this(context, marketingCloudConfig, hVar, fVar, cVar, bVar, dVar, pushMessageManager, nVar, (SFMCSdkComponents) null);
    }

    static String a(Registration registration, String str) {
        try {
            return m.c(registration).put("registrationDateUtc", j.a(new Date())).put("quietPushEnabled", false).putOpt("registrationId", str).toString();
        } catch (Exception e) {
            g.b(RegistrationManager.a, e, "Unable to create registration request payload", new Object[0]);
            return null;
        }
    }

    @Override // com.salesforce.marketingcloud.d
    public final String componentName() {
        return "RegistrationManager";
    }

    @Override // com.salesforce.marketingcloud.d
    public final JSONObject componentState() {
        e eVar = this.n;
        return eVar != null ? eVar.d() : new JSONObject();
    }

    @Override // com.salesforce.marketingcloud.e
    public void controlChannelInit(int i) {
        if (!com.salesforce.marketingcloud.b.a(i, 2)) {
            if (this.n == null) {
                a((InitializationStatus.a) null);
                this.n.g();
                return;
            }
            return;
        }
        this.n = null;
        e.a(this.f, this.h, com.salesforce.marketingcloud.b.c(i, 2));
        this.g.a(this);
        a();
        this.h.e(a.EnumC0042a.c);
        this.i.a(com.salesforce.marketingcloud.http.a.p);
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public RegistrationManager.Editor edit() {
        e eVar = this.n;
        return eVar != null ? eVar.a(this) : new c();
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public Map<String, String> getAttributes() {
        e eVar = this.n;
        return eVar != null ? eVar.getAttributes() : Collections.emptyMap();
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public String getContactKey() {
        e eVar = this.n;
        if (eVar != null) {
            return eVar.getContactKey();
        }
        return null;
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public String getDeviceId() {
        e eVar = this.n;
        return eVar != null ? eVar.getDeviceId() : "";
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public String getSignedString() {
        e eVar = this.n;
        if (eVar != null) {
            return eVar.getSignedString();
        }
        return null;
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public String getSystemToken() {
        e eVar = this.n;
        if (eVar != null) {
            return eVar.getSystemToken();
        }
        return null;
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public Set<String> getTags() {
        e eVar = this.n;
        return eVar != null ? eVar.getTags() : Collections.emptySet();
    }

    @Override // com.salesforce.marketingcloud.e
    public void init(InitializationStatus.a aVar, int i) {
        if (com.salesforce.marketingcloud.b.b(i, 2)) {
            a(aVar);
        }
    }

    @Override // com.salesforce.marketingcloud.behaviors.b
    public final void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        if (this.n != null) {
            switch (b.a[aVar.ordinal()]) {
                case 1:
                    this.n.c();
                    break;
                case 2:
                    this.n.h();
                    break;
                case 3:
                    this.n.b(bundle.getBoolean(PushMessageManager.g));
                    break;
                case 4:
                case 5:
                case 6:
                    this.n.b();
                    break;
                case 7:
                    this.n.a();
                    break;
                case 8:
                    this.n.a(bundle.getString(PushMessageManager.h, ""));
                    break;
                default:
                    g.a(RegistrationManager.a, "Unhandled behavior: %s", aVar);
                    break;
            }
        }
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.components.events.EventSubscriber
    public void onEventPublished(Event... eventArr) {
        for (com.salesforce.marketingcloud.events.Event event : com.salesforce.marketingcloud.events.d.a(eventArr, (EnumSet<Event.Producer>) EnumSet.of(Event.Producer.SFMC_SDK), (EnumSet<Event.Category>) EnumSet.of(Event.Category.IDENTITY))) {
            try {
                JSONObject jSONObject = (JSONObject) ((JSONObject) Objects.requireNonNull(event.attributes().get("moduleIdentities"))).get(ModuleIdentifier.PUSH.name().toLowerCase());
                JSONObject jSONObject2 = jSONObject.getJSONObject("customProperties").getJSONObject("attributes");
                Iterator<String> keys = jSONObject2.keys();
                HashMap hashMap = new HashMap();
                while (keys.hasNext()) {
                    String next = keys.next();
                    Object obj = jSONObject2.get(next);
                    hashMap.put(next, obj instanceof String ? (String) obj : "");
                }
                String optString = jSONObject.optString("profileId", null);
                if (optString != null) {
                    this.n.b(this).a(optString, (Map<String, String>) hashMap, false).commit();
                } else {
                    this.n.b(this).a((Map<String, String>) hashMap, false).commit();
                }
            } catch (Exception e) {
                g.e(RegistrationManager.a, e, "Failed to parse event for identity update.", new Object[0]);
            }
        }
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public void registerForRegistrationEvents(RegistrationManager.RegistrationEventListener registrationEventListener) {
        e eVar = this.n;
        if (eVar != null) {
            eVar.registerForRegistrationEvents(registrationEventListener);
        }
    }

    @Override // com.salesforce.marketingcloud.d
    public void tearDown(boolean z) {
        com.salesforce.marketingcloud.alarms.b bVar = this.h;
        a.EnumC0042a enumC0042a = a.EnumC0042a.c;
        bVar.d(enumC0042a);
        this.h.e(enumC0042a);
        this.g.a(this);
        a();
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public void unregisterForRegistrationEvents(RegistrationManager.RegistrationEventListener registrationEventListener) {
        e eVar = this.n;
        if (eVar != null) {
            eVar.unregisterForRegistrationEvents(registrationEventListener);
        }
    }

    public d(Context context, MarketingCloudConfig marketingCloudConfig, h hVar, f fVar, com.salesforce.marketingcloud.behaviors.c cVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.d dVar, PushMessageManager pushMessageManager, n nVar, SFMCSdkComponents sFMCSdkComponents) {
        this.d = context;
        this.e = marketingCloudConfig;
        this.f = hVar;
        this.m = fVar;
        this.g = cVar;
        this.h = bVar;
        this.i = dVar;
        this.j = pushMessageManager;
        this.k = nVar;
        this.l = sFMCSdkComponents;
    }

    public static com.salesforce.marketingcloud.http.e a(MarketingCloudConfig marketingCloudConfig, Context context, String str, String str2) {
        return com.salesforce.marketingcloud.http.a.p.a(marketingCloudConfig, new a(), a(new Registration(0, UUID.randomUUID().toString(), null, str, null, MarketingCloudSdk.getSdkVersionName(), com.salesforce.marketingcloud.util.f.a(context), TimeZone.getDefault().inDaylightTime(new Date()), false, false, Build.VERSION.RELEASE, false, j.b(), null, "Android", String.format(Locale.ENGLISH, "%s %s", Build.MANUFACTURER, Build.MODEL), marketingCloudConfig.applicationId(), Locale.getDefault().toString(), Collections.emptySet(), Collections.emptyMap()), str2)).j();
    }

    d(e eVar, Context context, MarketingCloudConfig marketingCloudConfig, h hVar, f fVar, com.salesforce.marketingcloud.behaviors.c cVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.d dVar, PushMessageManager pushMessageManager, n nVar) {
        this.n = eVar;
        this.d = context;
        this.e = marketingCloudConfig;
        this.f = hVar;
        this.m = fVar;
        this.g = cVar;
        this.h = bVar;
        this.i = dVar;
        this.j = pushMessageManager;
        this.k = nVar;
        this.l = null;
    }

    public static String a(h hVar) {
        return hVar.c().b(com.salesforce.marketingcloud.storage.b.d, null);
    }

    private void a(InitializationStatus.a aVar) {
        this.g.a(this, o);
        this.h.a(this, a.EnumC0042a.c);
        this.i.a(com.salesforce.marketingcloud.http.a.p, this);
        SFMCSdkComponents sFMCSdkComponents = this.l;
        if (sFMCSdkComponents != null) {
            sFMCSdkComponents.getEventManager().subscribe(this);
        }
        try {
            this.n = new e(this.d, this.e, this.f, this.m, this.h, this.i, this.j, this.k, this.l);
        } catch (Exception e) {
            if (aVar != null) {
                aVar.a(e);
            }
        }
    }

    private void a() {
        SFMCSdkComponents sFMCSdkComponents = this.l;
        if (sFMCSdkComponents != null) {
            sFMCSdkComponents.getEventManager().unsubscribe(this);
        }
    }

    @Override // com.salesforce.marketingcloud.registration.e.f
    public void a(String str, String str2, Map<String, String> map, Collection<String> collection, boolean z) {
        e eVar = this.n;
        if (eVar != null) {
            try {
                eVar.a(str, str2, map, collection, z);
            } catch (Exception e) {
                g.b(RegistrationManager.a, e, "Error encountered while saving registration", new Object[0]);
            }
        }
    }

    @Override // com.salesforce.marketingcloud.alarms.b.InterfaceC0044b
    public final void a(a.EnumC0042a enumC0042a) {
        e eVar;
        if (b.b[enumC0042a.ordinal()] == 1 && (eVar = this.n) != null) {
            eVar.e();
        }
    }

    @Override // com.salesforce.marketingcloud.http.d.c
    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.e eVar) {
        if (this.n != null) {
            if (eVar.o()) {
                try {
                    this.n.a(m.a(new JSONObject(bVar.o())), eVar.l());
                    return;
                } catch (Exception unused) {
                    this.n.a(-1, "Failed to convert our Response Body into a Registration.");
                    return;
                }
            }
            this.n.a(eVar.j(), eVar.m());
        }
    }
}
