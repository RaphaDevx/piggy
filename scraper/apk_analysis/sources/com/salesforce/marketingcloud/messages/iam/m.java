package com.salesforce.marketingcloud.messages.iam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.salesforce.marketingcloud.UrlHandler;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.media.b;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.messages.iam.InAppMessageManager;
import com.salesforce.marketingcloud.util.Crypto;
import io.sentry.SentryLogEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class m implements InAppMessageManager, i, b.InterfaceC0044b, b.a, com.salesforce.marketingcloud.events.f {
    private static final String A = "minDurationBetweenMessages";
    static final String v = com.salesforce.marketingcloud.g.a("InAppMessageManager");
    private static final int w = 1;
    private static final int x = 111;
    private static final String y = "messagesAttemptedInSession";
    private static final String z = "maxMessagesPerSession";
    final Context d;
    final com.salesforce.marketingcloud.storage.h e;
    final com.salesforce.marketingcloud.analytics.f f;
    final Object g = new Object();
    final AtomicInteger h;
    final Handler i;
    private final com.salesforce.marketingcloud.alarms.b j;
    private final UrlHandler k;
    private final n l;
    private final com.salesforce.marketingcloud.config.a m;
    private final AtomicInteger n;
    private final Handler o;
    InAppMessageManager.EventListener p;
    o q;
    private Typeface r;
    private int s;
    private com.salesforce.marketingcloud.media.b t;
    private InAppMessage u;

    class a extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ String c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.c = str2;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            InAppMessage a = m.this.e.k().a(Collections.singletonList(this.c), m.this.e.b());
            if (a != null) {
                m.this.d(a);
            } else {
                com.salesforce.marketingcloud.g.a(m.v, "Unable to find InAppMessage for message id [%s]", this.c);
            }
        }
    }

    class b extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ InAppMessage c;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                m.this.p.didShowMessage(bVar.c);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, InAppMessage inAppMessage) {
            super(str, objArr);
            this.c = inAppMessage;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            m.this.e.k().a(this.c);
            m.this.f.a(this.c);
            m.this.d();
            synchronized (m.this.g) {
                if (m.this.p != null) {
                    try {
                        new Handler(Looper.getMainLooper()).post(new a());
                    } catch (Exception e) {
                        com.salesforce.marketingcloud.g.b(m.v, e, "InAppMessage EventListener threw an exception", new Object[0]);
                    }
                }
            }
        }
    }

    class c extends com.salesforce.marketingcloud.internal.i {
        c(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            m mVar = m.this;
            mVar.a(mVar.e.k().e(m.this.e.b()));
        }
    }

    class d implements Runnable {
        final /* synthetic */ InAppMessage b;

        d(InAppMessage inAppMessage) {
            this.b = inAppMessage;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (m.this.g) {
                InAppMessageManager.EventListener eventListener = m.this.p;
                if (eventListener != null) {
                    try {
                        if (!eventListener.shouldShowMessage(this.b)) {
                            com.salesforce.marketingcloud.g.a(m.v, "InAppMessage EventListener[%s] returned false for shouldShowMessage [%s]", m.this.p.getClass().getName(), this.b.id());
                            return;
                        }
                    } catch (Exception e) {
                        com.salesforce.marketingcloud.g.b(m.v, e, "InAppMessage EventListener threw exception during shouldShowMessage", new Object[0]);
                    }
                }
                try {
                    Class<? extends f> a = m.this.a(this.b);
                    if (a != null) {
                        m mVar = m.this;
                        if (mVar.a(a, this.b, mVar.d)) {
                            m.this.d.startActivity(new Intent(m.this.d, a).setFlags(276889600).putExtra("messageHandler", new k(this.b)));
                        }
                    } else {
                        com.salesforce.marketingcloud.g.a(m.v, "Not supported", new Object[0]);
                    }
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(m.v, e2, "Failed to display InAppMessage [%s]", this.b.id());
                }
            }
        }
    }

    static /* synthetic */ class e {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[InAppMessage.Type.values().length];
            a = iArr;
            try {
                iArr[InAppMessage.Type.bannerTop.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[InAppMessage.Type.bannerBottom.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[InAppMessage.Type.modal.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[InAppMessage.Type.fullImageFill.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[InAppMessage.Type.full.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    m(Context context, com.salesforce.marketingcloud.storage.h hVar, com.salesforce.marketingcloud.alarms.b bVar, o oVar, UrlHandler urlHandler, n nVar, com.salesforce.marketingcloud.analytics.f fVar, Handler handler, com.salesforce.marketingcloud.config.a aVar) {
        this.d = context;
        this.e = hVar;
        this.j = bVar;
        this.q = oVar;
        this.k = urlHandler;
        this.f = fVar;
        this.l = nVar;
        this.m = aVar;
        bVar.a(this, a.EnumC0042a.i);
        this.h = new AtomicInteger();
        this.n = new AtomicInteger();
        this.i = new Handler(Looper.getMainLooper());
        this.o = handler;
    }

    @Override // com.salesforce.marketingcloud.media.b.a
    public void a(boolean z2) {
        if (z2) {
            this.j.d(a.EnumC0042a.i);
        } else {
            this.j.b(a.EnumC0042a.i);
        }
    }

    void b(boolean z2) {
        this.j.e(a.EnumC0042a.i);
        this.i.removeCallbacksAndMessages(null);
        com.salesforce.marketingcloud.media.b bVar = this.t;
        if (bVar != null) {
            bVar.b();
        }
        if (z2) {
            com.salesforce.marketingcloud.storage.e k = this.e.k();
            this.q.a((Collection<String>) k.e(this.e.b()));
            k.a(Collections.emptyList());
        }
    }

    boolean c(InAppMessage inAppMessage) {
        JSONObject jSONObject;
        boolean z2 = false;
        if (inAppMessage.displayLimitOverride()) {
            com.salesforce.marketingcloud.g.a(v, "InAppMessage [%s] has displayLimit Override set. The message will not honour displayLimit settings", inAppMessage.id());
            return false;
        }
        int i = this.e.e().getInt(com.salesforce.marketingcloud.events.c.s, Integer.MAX_VALUE);
        try {
            jSONObject = new JSONObject();
            try {
            } catch (Exception e2) {
                e = e2;
                z2 = true;
                com.salesforce.marketingcloud.g.b(v, e, "Failed to log message Debug Analytics for IAM %s", inAppMessage.id());
                return z2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        if (this.h.get() < i) {
            if (this.i.hasMessages(111)) {
                jSONObject.put(A, this.e.e().getInt(com.salesforce.marketingcloud.events.c.t, 0));
                jSONObject.put(z, this.n.incrementAndGet());
            }
            if (z2 && this.m.j()) {
                this.f.a(inAppMessage, jSONObject);
            }
            return z2;
        }
        jSONObject.put(z, this.e.e().getInt(com.salesforce.marketingcloud.events.c.s, 0));
        jSONObject.put(y, this.n.incrementAndGet());
        z2 = true;
        if (z2) {
            this.f.a(inAppMessage, jSONObject);
        }
        return z2;
    }

    @Override // com.salesforce.marketingcloud.messages.iam.i
    public boolean canDisplay(InAppMessage inAppMessage) {
        InAppMessage inAppMessage2 = this.u;
        if (inAppMessage2 == null) {
            this.l.b().execute(new b("can_display", new Object[0], inAppMessage));
            this.u = inAppMessage;
        } else if (inAppMessage != inAppMessage2) {
            com.salesforce.marketingcloud.g.a(v, "In App Message [%s] not displayed because [%s] is currently being displayed", inAppMessage.id(), this.u.id());
            return false;
        }
        return true;
    }

    void d(InAppMessage inAppMessage) {
        if (inAppMessage == null || c(inAppMessage)) {
            return;
        }
        this.o.postDelayed(new d(inAppMessage), TimeUnit.SECONDS.toMillis(inAppMessage.messageDelaySec()));
    }

    @Override // com.salesforce.marketingcloud.messages.iam.i
    public int getStatusBarColor() {
        return this.s;
    }

    @Override // com.salesforce.marketingcloud.messages.iam.i
    public Typeface getTypeface() {
        return this.r;
    }

    @Override // com.salesforce.marketingcloud.messages.iam.i
    public void handleMessageFinished(InAppMessage inAppMessage, j jVar) {
        InAppMessage inAppMessage2 = this.u;
        if (inAppMessage2 != null && inAppMessage2.id().equals(inAppMessage.id())) {
            com.salesforce.marketingcloud.analytics.f fVar = this.f;
            if (fVar != null) {
                fVar.a(inAppMessage, jVar);
            }
            synchronized (this.g) {
                InAppMessageManager.EventListener eventListener = this.p;
                if (eventListener != null) {
                    try {
                        eventListener.didCloseMessage(inAppMessage);
                    } catch (Exception e2) {
                        com.salesforce.marketingcloud.g.b(v, e2, "InAppMessageEventListener threw an exception", new Object[0]);
                    }
                }
            }
        }
        this.u = null;
    }

    @Override // com.salesforce.marketingcloud.events.f
    public void handleOutcomes(Collection<String> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        String str = v;
        com.salesforce.marketingcloud.g.d(str, "Resolving IAM from outcomes %s", collection.toString());
        InAppMessage a2 = this.e.k().a(collection, this.e.b());
        if (a2 == null) {
            com.salesforce.marketingcloud.g.d(str, "No message resolved.", new Object[0]);
        } else {
            com.salesforce.marketingcloud.g.d(str, "Outcomes resolved to message[%s]", a2.id());
            d(a2);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.iam.i
    public o imageHandler() {
        return this.q;
    }

    @Override // com.salesforce.marketingcloud.messages.iam.InAppMessageManager
    public void setInAppMessageListener(InAppMessageManager.EventListener eventListener) {
        synchronized (this.g) {
            this.p = eventListener;
        }
    }

    @Override // com.salesforce.marketingcloud.messages.iam.InAppMessageManager
    public void setStatusBarColor(int i) {
        this.s = i;
    }

    @Override // com.salesforce.marketingcloud.messages.iam.InAppMessageManager
    public void setTypeface(Typeface typeface) {
        this.r = typeface;
    }

    @Override // com.salesforce.marketingcloud.messages.iam.InAppMessageManager
    public void showMessage(String str) {
        if (str == null) {
            return;
        }
        this.l.b().execute(new a("iam_showMessage", new Object[0], str));
    }

    @Override // com.salesforce.marketingcloud.messages.iam.i
    public UrlHandler urlHandler() {
        return this.k;
    }

    @Override // com.salesforce.marketingcloud.alarms.b.InterfaceC0044b
    public void a(a.EnumC0042a enumC0042a) {
        if (enumC0042a == a.EnumC0042a.i) {
            this.l.b().execute(new c("iam_image_cache", new Object[0]));
        }
    }

    void a(JSONObject jSONObject) {
        if (jSONObject.optInt("version") != 1) {
            com.salesforce.marketingcloud.g.b(v, "Unable to handle sync payload due to version mismatch", new Object[0]);
            return;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("items");
            int length = jSONArray.length();
            com.salesforce.marketingcloud.g.a(v, "%d in app message(s) received from sync.", Integer.valueOf(length));
            TreeSet treeSet = new TreeSet();
            com.salesforce.marketingcloud.storage.e k = this.e.k();
            Crypto b2 = this.e.b();
            List<String> e2 = k.e(b2);
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String b3 = b(jSONObject2);
                    if (b3 == null) {
                        InAppMessage inAppMessage = new InAppMessage(jSONObject2);
                        if (k.a(inAppMessage, b2) == 1) {
                            b(inAppMessage);
                        }
                        k.b(inAppMessage.id(), jSONObject2.optInt("displayCount", 0));
                        treeSet.add(inAppMessage.id());
                    } else if (!b3.isEmpty()) {
                        this.f.a(jSONObject2.optString("id"), jSONObject2.optString("activityInstanceId"), Collections.singletonList(b3));
                    }
                } catch (Exception e3) {
                    com.salesforce.marketingcloud.g.b(v, e3, "Unable to parse in app message payload", new Object[0]);
                }
            }
            k.a(treeSet);
            List<String> e4 = k.e(b2);
            a(e4);
            TreeSet treeSet2 = new TreeSet(e2);
            treeSet2.removeAll(e4);
            this.q.a((Collection<String>) treeSet2);
        } catch (JSONException e5) {
            com.salesforce.marketingcloud.g.b(v, e5, "Unable to get InAppMessages from sync payload", new Object[0]);
        }
    }

    void b() {
        this.h.set(0);
        this.n.set(0);
        this.i.removeCallbacksAndMessages(null);
    }

    String b(JSONObject jSONObject) {
        String optString = jSONObject.optString("id");
        String optString2 = jSONObject.optString("activityInstanceId");
        if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
            String optString3 = jSONObject.optString("endDateUtc", null);
            if (optString3 != null) {
                try {
                    if (com.salesforce.marketingcloud.util.j.d(optString3).getTime() < System.currentTimeMillis()) {
                        return "ExpiredMessage";
                    }
                } catch (Exception unused) {
                    return "InvalidDate";
                }
            }
            String optString4 = jSONObject.optString("startDateUtc", null);
            if (optString4 != null && a(optString4)) {
                return "InvalidDate";
            }
            String optString5 = jSONObject.optString("modifiedDateUtc", null);
            if (optString5 == null) {
                return "NoModifiedDate";
            }
            if (a(optString5)) {
                return "InvalidDate";
            }
            try {
                InAppMessage.Type.valueOf(jSONObject.getString("type"));
                JSONObject optJSONObject = jSONObject.optJSONObject(com.salesforce.marketingcloud.messages.inbox.b.e);
                JSONObject optJSONObject2 = jSONObject.optJSONObject("title");
                JSONObject optJSONObject3 = jSONObject.optJSONObject(SentryLogEvent.JsonKeys.BODY);
                JSONArray optJSONArray = jSONObject.optJSONArray("buttons");
                if (optJSONObject == null && optJSONObject2 == null && optJSONObject3 == null && (optJSONArray == null || optJSONArray.length() == 0)) {
                    return "NoContent";
                }
                if (optJSONObject != null) {
                    Object opt = optJSONObject.opt("url");
                    if (!(opt instanceof String) || TextUtils.isEmpty((String) opt)) {
                        return "InvalidMedia";
                    }
                }
                if (optJSONObject2 != null && TextUtils.isEmpty(optJSONObject2.optString("text", null))) {
                    return "InvalidTitle";
                }
                if (optJSONObject3 != null && TextUtils.isEmpty(optJSONObject3.optString("text", null))) {
                    return "InvalidBody";
                }
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject4 = optJSONArray.optJSONObject(i);
                        if (optJSONObject4 == null || TextUtils.isEmpty(optJSONObject4.optString("id")) || TextUtils.isEmpty(optJSONObject4.optString("text"))) {
                            return "InvalidButton";
                        }
                    }
                }
                return null;
            } catch (Exception unused2) {
                return "NoMessageType";
            }
        }
        return "";
    }

    public void c() {
        this.o.removeCallbacksAndMessages(null);
    }

    void d() {
        this.h.incrementAndGet();
        int i = this.e.e().getInt(com.salesforce.marketingcloud.events.c.t, 0);
        if (i > 0) {
            this.i.sendMessageDelayed(this.i.obtainMessage(111), TimeUnit.SECONDS.toMillis(i));
        }
    }

    boolean a(Class<? extends f> cls, InAppMessage inAppMessage, Context context) throws ClassNotFoundException {
        return (cls == Class.forName(IamFullscreenActivity.class.getName()) && inAppMessage.type() == InAppMessage.Type.fullImageFill && context.getResources().getConfiguration().orientation != 1) ? false : true;
    }

    Class<? extends f> a(InAppMessage inAppMessage) {
        int i = e.a[inAppMessage.type().ordinal()];
        if (i == 1 || i == 2) {
            return IamBannerActivity.class;
        }
        if (i == 3) {
            return IamModalActivity.class;
        }
        if (i == 4) {
            return IamFullImageFillActivity.class;
        }
        if (i != 5) {
            return null;
        }
        return IamFullscreenActivity.class;
    }

    private boolean a(String str) {
        try {
            com.salesforce.marketingcloud.util.j.d(str);
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    void a(List<String> list) {
        if (list.isEmpty()) {
            return;
        }
        com.salesforce.marketingcloud.media.b bVar = this.t;
        if (bVar != null) {
            bVar.b();
        }
        com.salesforce.marketingcloud.media.b a2 = this.q.a(list);
        this.t = a2;
        a2.a(this, true);
    }

    void b(InAppMessage inAppMessage) {
        try {
            this.f.b(inAppMessage);
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(v, e2, "Failed to log download analytics for IAM %s", inAppMessage.id());
        }
    }

    JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(com.salesforce.marketingcloud.storage.db.i.e, this.e.k().d(this.e.b()));
            InAppMessageManager.EventListener eventListener = this.p;
            if (eventListener != null) {
                jSONObject.put("eventListener", eventListener.getClass().getName());
            }
            jSONObject.put("subscriberToken", this.e.c().b(com.salesforce.marketingcloud.storage.b.j, "null"));
            jSONObject.put("custom_font_set", this.r != null);
            jSONObject.put("status_bar_color", this.s);
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(v, e2, "Unable to compile componentState for InAppMessageManager", new Object[0]);
        }
        return jSONObject;
    }
}
