package com.salesforce.marketingcloud.registration;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationManagerCompat;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.i;
import com.salesforce.marketingcloud.internal.m;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.registration.RegistrationManager;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import com.salesforce.marketingcloud.sfmcsdk.util.SFMCExtension;
import com.salesforce.marketingcloud.storage.h;
import com.salesforce.marketingcloud.util.j;
import com.samsung.android.sdk.samsungpay.v2.InternalConst;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class e implements RegistrationManager {
    public static final String w = "Android";
    static final String x = "previousRegistrationHash";
    static final String y = "lastRegistrationSendTimestamp";
    final Set<String> d;
    final MarketingCloudConfig e;
    final h f;
    final com.salesforce.marketingcloud.alarms.b g;
    final com.salesforce.marketingcloud.http.d h;
    final n i;
    final SFMCSdkComponents j;
    private final Context k;
    private final Set<RegistrationManager.RegistrationEventListener> l;
    private final com.salesforce.marketingcloud.registration.f m;
    private ConcurrentHashMap<String, String> n;
    private ConcurrentSkipListSet<String> o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private String t;
    private String u;
    private String v;

    class a implements MarketingCloudSdk.WhenReadyListener {
        final /* synthetic */ boolean a;

        /* renamed from: com.salesforce.marketingcloud.registration.e$a$a, reason: collision with other inner class name */
        class CountDownTimerC0093a extends AbstractCountDownTimerC0095e {

            /* renamed from: com.salesforce.marketingcloud.registration.e$a$a$a, reason: collision with other inner class name */
            class C0094a extends i {
                C0094a(String str, Object... objArr) {
                    super(str, objArr);
                }

                @Override // com.salesforce.marketingcloud.internal.i
                protected void a() {
                    try {
                        SFMCSdkComponents sFMCSdkComponents = e.this.j;
                        String registrationId = sFMCSdkComponents != null ? sFMCSdkComponents.getRegistrationId() : null;
                        Registration l = e.this.f.p().l(e.this.f.b());
                        e eVar = e.this;
                        if (e.a(l, eVar.f, eVar.e.delayRegistrationUntilContactKeyIsSet())) {
                            e.this.g.d(a.EnumC0042a.c);
                            e eVar2 = e.this;
                            eVar2.h.a(com.salesforce.marketingcloud.http.a.p.a(eVar2.e, eVar2.f.c(), com.salesforce.marketingcloud.registration.d.a(l, registrationId)));
                        }
                    } catch (Exception e) {
                        g.b(RegistrationManager.a, e, "Failed to get our Registration from local storage.", new Object[0]);
                    }
                }
            }

            CountDownTimerC0093a(int i) {
                super(i);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                e.this.i.b().execute(new C0094a("registration_request", new Object[0]));
            }
        }

        a(boolean z) {
            this.a = z;
        }

        @Override // com.salesforce.marketingcloud.MarketingCloudSdk.WhenReadyListener
        public void ready(MarketingCloudSdk marketingCloudSdk) {
            new CountDownTimerC0093a(this.a ? 1000 : 0).start();
        }
    }

    class b extends i {
        b(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            e.this.f.p().c();
        }
    }

    class c extends i {
        c(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            e eVar = e.this;
            if (e.a(eVar.f, eVar.e.delayRegistrationUntilContactKeyIsSet())) {
                e.this.g.b(a.EnumC0042a.c);
            }
        }
    }

    /* renamed from: com.salesforce.marketingcloud.registration.e$e, reason: collision with other inner class name */
    static abstract class AbstractCountDownTimerC0095e extends CountDownTimer {
        public AbstractCountDownTimerC0095e(int i) {
            this(i, 1000L);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        private AbstractCountDownTimerC0095e(long j, long j2) {
            super(j, j2);
        }
    }

    interface f {
        default void a(String str, String str2, Map<String, String> map, Collection<String> collection) {
            a(str, str2, map, collection, false);
        }

        void a(String str, String str2, Map<String, String> map, Collection<String> collection, boolean z);
    }

    e(Context context, MarketingCloudConfig marketingCloudConfig, h hVar, com.salesforce.marketingcloud.registration.f fVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.d dVar, PushMessageManager pushMessageManager, n nVar) {
        this(context, marketingCloudConfig, hVar, fVar, bVar, dVar, pushMessageManager, nVar, null);
    }

    private static ConcurrentSkipListSet<String> a(ConcurrentSkipListSet<String> concurrentSkipListSet, Set<String> set) {
        if (!concurrentSkipListSet.containsAll(set)) {
            concurrentSkipListSet.addAll(set);
        }
        return concurrentSkipListSet;
    }

    com.salesforce.marketingcloud.registration.c b(f fVar) {
        return new d(fVar, this.v, this.t, this.n, this.o, this.d);
    }

    void c(boolean z) {
        try {
            Registration a2 = a(0);
            this.i.b().execute(new com.salesforce.marketingcloud.registration.a(this.f.p(), this.f.b(), a2, false));
            a(this.f, a2.contactKey());
            if (a(a2, this.f, this.e.delayRegistrationUntilContactKeyIsSet())) {
                SFMCSdkComponents sFMCSdkComponents = this.j;
                if (sFMCSdkComponents != null && z) {
                    if (this.t != null) {
                        sFMCSdkComponents.getIdentity().setProfile(this.t, this.n, ModuleIdentifier.PUSH, new ModuleIdentifier[0]);
                    } else {
                        sFMCSdkComponents.getIdentity().setProfileAttributes(this.n, ModuleIdentifier.PUSH);
                    }
                }
                e();
            }
        } catch (Exception e) {
            g.b(RegistrationManager.a, e, "An error occurred trying to save our Registration.", new Object[0]);
        }
    }

    JSONObject d() {
        String b2;
        Registration a2 = a(0);
        if (a2 == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("current_registration", m.c(a2));
            if (a(a2, this.f, this.e.delayRegistrationUntilContactKeyIsSet()) && (b2 = this.f.c().b(com.salesforce.marketingcloud.storage.b.h, null)) != null) {
                jSONObject.put("last_registration_sent", new JSONObject(b2));
            }
            long j = this.f.e().getLong(y, 0L);
            if (j > 0) {
                jSONObject.put("last_sent_timestamp", j.a(new Date(j)));
            }
        } catch (JSONException e) {
            g.b(RegistrationManager.a, e, "Failed to build our component state JSONObject.", new Object[0]);
        }
        return jSONObject;
    }

    void e() {
        a(true);
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public RegistrationManager.Editor edit() {
        g.a(RegistrationManager.a, "Changes with this editor will not be saved.", new Object[0]);
        return new d(null, this.v, this.t, this.n, this.o, this.d);
    }

    boolean f() {
        return this.s && NotificationManagerCompat.from(this.k).areNotificationsEnabled();
    }

    void g() {
        c(false);
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public Map<String, String> getAttributes() {
        return new HashMap(this.n);
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public String getContactKey() {
        return this.t;
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public String getDeviceId() {
        return this.m.f();
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public String getSignedString() {
        return this.v;
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public String getSystemToken() {
        return this.u;
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public Set<String> getTags() {
        return new TreeSet((SortedSet) this.o);
    }

    void h() {
        g();
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public void registerForRegistrationEvents(RegistrationManager.RegistrationEventListener registrationEventListener) {
        if (registrationEventListener == null) {
            return;
        }
        synchronized (this.l) {
            this.l.add(registrationEventListener);
        }
    }

    @Override // com.salesforce.marketingcloud.registration.RegistrationManager
    public void unregisterForRegistrationEvents(RegistrationManager.RegistrationEventListener registrationEventListener) {
        synchronized (this.l) {
            this.l.remove(registrationEventListener);
        }
    }

    static class d implements RegistrationManager.Editor, com.salesforce.marketingcloud.registration.c {
        private static final List<String> j;
        private final Object a = new Object();
        private final Map<String, String> b;
        private final Set<String> c;
        private final f d;
        private final Map<String, String> e;
        private String f;
        private String g;
        private boolean h;
        private boolean i;

        static {
            String[] strArr = {"addressId", SavedPaymentMethod.ALIAS_KEY, "apId", "backgroundRefreshEnabled", "badge", "channel", "contactId", "contactKey", "createdBy", "createdDate", "customObjectKey", "device", "deviceId", SpaySdk.EXTRA_DEVICE_TYPE, "gcmSenderId", "hardwareId", "isHonorDst", "lastAppOpen", "lastMessageOpen", "lastSend", "locationEnabled", "messageOpenCount", "modifiedBy", "modifiedDate", "optInDate", "optInMethodId", "optInStatusId", "optOutDate", "optOutMethodId", "optOutStatusId", "platform", "platformVersion", "providerToken", "proximityEnabled", "pushAddressExtensionId", "pushApplicationId", InternalConst.EXTRA_SDK_VERSION, "sendCount", "source", "sourceObjectId", "status", "systemToken", "timezone", "utcOffset", "signedString", "quietPushEnabled"};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 46; i++) {
                arrayList.add(strArr[i].toLowerCase(Locale.ENGLISH));
            }
            j = Collections.unmodifiableList(arrayList);
        }

        d(f fVar, String str, String str2, ConcurrentHashMap<String, String> concurrentHashMap, ConcurrentSkipListSet<String> concurrentSkipListSet, Set<String> set) {
            Comparator comparator = String.CASE_INSENSITIVE_ORDER;
            this.b = new TreeMap(comparator);
            this.c = new TreeSet(comparator);
            this.d = fVar;
            this.f = str;
            this.g = str2;
            this.e = new com.salesforce.marketingcloud.registration.b(concurrentHashMap);
            Iterator<String> it = concurrentSkipListSet.iterator();
            while (it.hasNext()) {
                String next = it.next();
                this.b.put(next, next);
            }
            this.c.addAll(set);
        }

        private boolean b(String str) {
            if (str != null) {
                return true;
            }
            g.b(RegistrationManager.a, "Attribute value was null and will not be saved.", new Object[0]);
            return false;
        }

        private boolean c(String str) {
            return str == null || TextUtils.getTrimmedLength(str) > 0;
        }

        private String d(String str) {
            String validContactKey = SFMCExtension.getValidContactKey(str);
            if (validContactKey == null) {
                g.e(RegistrationManager.a, "An invalid ContactKey will not be transmitted to the Marketing Cloud and was NOT updated with the provided value.", new Object[0]);
            }
            return validContactKey;
        }

        private String e(String str) {
            return str != null ? str.trim() : str;
        }

        @Override // com.salesforce.marketingcloud.registration.c
        public RegistrationManager.Editor a(String str, String str2, boolean z) {
            synchronized (this.a) {
                if (a(str) && b(str2)) {
                    this.e.put(str, str2);
                    this.h = true;
                    this.i = z;
                }
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor addTag(String str) {
            String e = e(str);
            synchronized (this.a) {
                if (!TextUtils.isEmpty(e) && !e.equals(this.b.put(e, e))) {
                    this.h = true;
                }
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor addTags(Iterable<String> iterable) {
            if (iterable == null) {
                return this;
            }
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                addTag(it.next());
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor clearAttribute(String str) {
            return !a(str) ? this : setAttribute(str, "");
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor clearAttributes(Iterable<String> iterable) {
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                clearAttribute(it.next());
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor clearTags() {
            synchronized (this.a) {
                if (this.b.keySet().retainAll(this.c)) {
                    this.h = true;
                }
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public boolean commit() {
            f fVar;
            synchronized (this.a) {
                if (!this.h || (fVar = this.d) == null) {
                    return false;
                }
                fVar.a(this.f, this.g, this.e, this.b.values(), this.i);
                return true;
            }
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor removeTag(String str) {
            if (str == null) {
                return this;
            }
            synchronized (this.a) {
                if (!this.c.contains(str) && this.b.remove(str) != null) {
                    this.h = true;
                }
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor removeTags(Iterable<String> iterable) {
            if (iterable == null) {
                return this;
            }
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                removeTag(it.next());
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        @Deprecated
        public RegistrationManager.Editor setAttribute(String str, String str2) {
            return a(str, str2, true);
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        @Deprecated
        public RegistrationManager.Editor setContactKey(String str) {
            return a(str, true);
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor setSignedString(String str) {
            synchronized (this.a) {
                if (c(str)) {
                    this.f = str;
                    this.h = true;
                }
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor addTags(String... strArr) {
            if (strArr != null && strArr.length != 0) {
                for (String str : strArr) {
                    addTag(str);
                }
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor clearAttributes(String... strArr) {
            if (strArr != null && strArr.length != 0) {
                for (String str : strArr) {
                    clearAttribute(str);
                }
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.RegistrationManager.Editor
        public RegistrationManager.Editor removeTags(String... strArr) {
            if (strArr != null && strArr.length != 0) {
                for (String str : strArr) {
                    removeTag(str);
                }
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.c
        public RegistrationManager.Editor a(String str, boolean z) {
            String d = d(str);
            if (d != null) {
                synchronized (this.a) {
                    this.h = true;
                    this.i = z;
                    this.g = d;
                }
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.c
        public RegistrationManager.Editor a(String str, Map<String, String> map, boolean z) {
            a(str, z);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                a(entry.getKey(), entry.getValue(), z);
            }
            return this;
        }

        @Override // com.salesforce.marketingcloud.registration.c
        public RegistrationManager.Editor a(Map<String, String> map, boolean z) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                a(entry.getKey(), entry.getValue(), z);
            }
            return this;
        }

        private boolean a(String str) {
            if (TextUtils.isEmpty(str)) {
                g.e(RegistrationManager.a, "The attribute you provided was null or empty.", new Object[0]);
                return false;
            }
            String trim = str.trim();
            if (TextUtils.isEmpty(trim)) {
                g.e(RegistrationManager.a, "The attribute you provided was blank.", new Object[0]);
                return false;
            }
            if (j.contains(trim.toLowerCase(Locale.ENGLISH))) {
                g.e(RegistrationManager.a, "Attribute key '%s' is invalid and can not be added.  Please see documentation regarding Attributes and Reserved Words.", trim);
                return false;
            }
            if (trim.length() <= 128) {
                return true;
            }
            g.e(RegistrationManager.a, "Your attribute key was %s characters long.  Attribute keys are restricted to %s characters.  Your attribute key will be truncated.", Integer.valueOf(trim.length()), 128);
            return false;
        }
    }

    e(Context context, MarketingCloudConfig marketingCloudConfig, h hVar, com.salesforce.marketingcloud.registration.f fVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.d dVar, PushMessageManager pushMessageManager, n nVar, SFMCSdkComponents sFMCSdkComponents) {
        Registration a2;
        this.l = new ArraySet();
        this.k = context;
        this.e = marketingCloudConfig;
        this.f = hVar;
        this.m = fVar;
        this.g = bVar;
        this.h = dVar;
        this.i = nVar;
        this.j = sFMCSdkComponents;
        TreeSet treeSet = new TreeSet();
        treeSet.add("ALL");
        treeSet.add("Android");
        if (j.a(context)) {
            treeSet.add("DEBUG");
        }
        Set<String> unmodifiableSet = Collections.unmodifiableSet(treeSet);
        this.d = unmodifiableSet;
        this.s = pushMessageManager.isPushEnabled();
        boolean b2 = com.salesforce.marketingcloud.util.f.b(context);
        this.p = b2;
        boolean z = true;
        boolean z2 = false;
        this.q = b2 && com.salesforce.marketingcloud.util.f.c(context);
        this.r = NotificationManagerCompat.from(context).areNotificationsEnabled();
        this.u = pushMessageManager.getPushToken();
        com.salesforce.marketingcloud.storage.b c2 = hVar.c();
        try {
            Registration l = hVar.p().l(hVar.b());
            if (l == null) {
                this.v = null;
                this.t = c2.b(com.salesforce.marketingcloud.storage.b.d, null);
                this.n = new ConcurrentHashMap<>(j.b(c2.b(com.salesforce.marketingcloud.storage.b.b, "")));
                ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet(j.c(c2.b(com.salesforce.marketingcloud.storage.b.c, "")));
                this.o = concurrentSkipListSet.isEmpty() ? new ConcurrentSkipListSet<>(unmodifiableSet) : a((ConcurrentSkipListSet<String>) concurrentSkipListSet, unmodifiableSet);
                a2 = a(0);
                z = false;
            } else {
                this.v = l.signedString();
                this.t = l.contactKey();
                this.n = new ConcurrentHashMap<>(l.attributes());
                this.o = a((ConcurrentSkipListSet<String>) new ConcurrentSkipListSet(l.tags()), unmodifiableSet);
                Registration a3 = a(m.b(l));
                a2 = j.a(l, a3) ? a3 : l;
            }
            a(hVar, this.t);
            z2 = z;
        } catch (Exception e) {
            g.b(RegistrationManager.a, e, "Error trying to get, update or add a registration to local storage.", new Object[0]);
            this.o = new ConcurrentSkipListSet<>(this.d);
            this.n = new ConcurrentHashMap<>();
            this.t = null;
            this.v = null;
            a2 = a(0);
        }
        nVar.b().execute(new com.salesforce.marketingcloud.registration.a(hVar.p(), hVar.b(), a2, z2));
        if (a(a2, hVar, marketingCloudConfig.delayRegistrationUntilContactKeyIsSet())) {
            e();
        }
    }

    void b() {
        boolean b2 = com.salesforce.marketingcloud.util.f.b(this.k);
        boolean z = b2 && com.salesforce.marketingcloud.util.f.c(this.k);
        boolean areNotificationsEnabled = NotificationManagerCompat.from(this.k).areNotificationsEnabled();
        if (b2 == this.p && z == this.q && areNotificationsEnabled == this.r) {
            return;
        }
        this.p = b2;
        this.q = z;
        this.r = areNotificationsEnabled;
        g();
    }

    static void a(h hVar, com.salesforce.marketingcloud.alarms.b bVar, boolean z) {
        if (z) {
            hVar.p().n();
            hVar.c().a(com.salesforce.marketingcloud.storage.b.d);
        }
        bVar.d(a.EnumC0042a.c);
    }

    static boolean a(h hVar, boolean z) {
        try {
            return a(hVar.p().l(hVar.b()), hVar, z);
        } catch (Exception e) {
            g.b(RegistrationManager.a, e, "Failed to get Registration from local storage or we can not determine if this Registration contains any changes.", new Object[0]);
            return false;
        }
    }

    static boolean a(Registration registration, h hVar, boolean z) {
        if (registration == null) {
            return false;
        }
        if (registration.contactKey() == null && z) {
            g.e(RegistrationManager.a, "You have delayRegistrationUntilContactKeyIsSet set to `true.`  The SDK will not send a registration to the Marketing Cloud until a contact key has been set.", new Object[0]);
            return false;
        }
        String string = hVar.e().getString(x, null);
        return string == null || !j.a(registration).equals(string);
    }

    void b(boolean z) {
        this.s = z;
        g();
    }

    private void a(h hVar, String str) {
        hVar.c().a(com.salesforce.marketingcloud.storage.b.d, str);
    }

    RegistrationManager.Editor a(f fVar) {
        return new d(fVar, this.v, this.t, this.n, this.o, this.d);
    }

    void c() {
        this.g.d(a.EnumC0042a.c);
        g();
    }

    private Registration a(int i) {
        return new Registration(i, UUID.randomUUID().toString(), this.v, this.m.f(), this.u, this.m.j(), this.m.e(), TimeZone.getDefault().inDaylightTime(new Date()), this.p, this.q, this.m.i(), f(), j.b(), this.t, this.m.h(), this.m.g(), this.e.applicationId(), Locale.getDefault().toString(), this.o, this.n);
    }

    void a(String str, String str2, Map<String, String> map, Collection<String> collection) throws Exception {
        a(str, str2, map, collection, false);
    }

    void a(String str, String str2, Map<String, String> map, Collection<String> collection, boolean z) throws Exception {
        this.v = str;
        this.t = str2;
        this.n.clear();
        this.n.putAll(map);
        this.o.clear();
        this.o.addAll(collection);
        this.g.c(a.EnumC0042a.c);
        c(z);
    }

    void a(boolean z) {
        MarketingCloudSdk.requestSdk(new a(z));
    }

    void a(Registration registration, Map<String, List<String>> map) {
        com.salesforce.marketingcloud.http.a.a(map, this.f.c());
        this.g.c(a.EnumC0042a.c);
        synchronized (this.l) {
            for (RegistrationManager.RegistrationEventListener registrationEventListener : this.l) {
                if (registrationEventListener != null) {
                    try {
                        registrationEventListener.onRegistrationReceived(registration);
                    } catch (Exception e) {
                        g.b(RegistrationManager.a, e, "%s threw an exception while processing the registration response", registrationEventListener.getClass().getName());
                    }
                }
            }
        }
        this.f.c().a(com.salesforce.marketingcloud.storage.b.h, m.c(registration).toString());
        this.f.e().edit().putLong(y, System.currentTimeMillis()).putString(x, j.a(registration)).apply();
        this.i.b().execute(new b("delete_old_registrations", new Object[0]));
    }

    void a(int i, String str) {
        g.a(RegistrationManager.a, "%s: %s", Integer.valueOf(i), str);
        this.i.b().execute(new c("schedule_registration_retry", new Object[0]));
    }

    void a() {
        this.f.e().edit().remove(com.salesforce.marketingcloud.http.a.p.d + "_device").apply();
        a(false);
    }

    void a(String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.u)) {
            return;
        }
        this.u = str;
        g();
    }
}
