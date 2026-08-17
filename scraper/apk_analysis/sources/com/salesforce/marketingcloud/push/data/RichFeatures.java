package com.salesforce.marketingcloud.push.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.o;
import com.salesforce.marketingcloud.push.data.Template;
import com.salesforce.marketingcloud.push.j;
import com.salesforce.marketingcloud.push.m;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class RichFeatures implements Parcelable {
    private final com.salesforce.marketingcloud.push.buttons.a buttons;
    private final String largeIcon;
    private final String smallIcon;
    private final Template viewTemplate;
    public static final a Companion = new a(null);
    public static final Parcelable.Creator<RichFeatures> CREATOR = new b();
    private static final String TAG = g.a("RichFeatures");

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final RichFeatures a(String json) {
            Template template;
            Object opt;
            j<?> a;
            Intrinsics.checkNotNullParameter(json, "json");
            try {
                JSONObject jSONObject = new JSONObject(json);
                String optString = jSONObject.optString(com.salesforce.marketingcloud.push.g.b);
                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                String b = o.b(optString);
                String optString2 = jSONObject.optString(com.salesforce.marketingcloud.push.g.c);
                Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                String b2 = o.b(optString2);
                Object opt2 = jSONObject.opt(com.salesforce.marketingcloud.push.g.d);
                Template template2 = null;
                if (opt2 != null) {
                    j<?> a2 = j.a.a.a(RichFeatures.Companion.a(opt2));
                    if (a2 != null) {
                        template = a2.parse(opt2.toString());
                        opt = jSONObject.opt(com.salesforce.marketingcloud.push.g.f);
                        if (opt != null && (a = j.a.a.a(Template.Type.RichButtons)) != null) {
                            template2 = a.parse(opt.toString());
                        }
                        return new RichFeatures(b, b2, template, (com.salesforce.marketingcloud.push.buttons.a) template2);
                    }
                }
                template = null;
                opt = jSONObject.opt(com.salesforce.marketingcloud.push.g.f);
                if (opt != null) {
                    template2 = a.parse(opt.toString());
                }
                return new RichFeatures(b, b2, template, (com.salesforce.marketingcloud.push.buttons.a) template2);
            } catch (JSONException e) {
                throw new com.salesforce.marketingcloud.push.d(e.toString());
            }
        }

        private a() {
        }

        public final Template.Type a(Object obj) {
            if (obj instanceof JSONObject) {
                String optString = ((JSONObject) obj).optString(com.salesforce.marketingcloud.push.g.g);
                Template.Type type = Template.Type.CarouselFull;
                if (Intrinsics.areEqual(optString, type.getValue())) {
                    return type;
                }
                throw new m(optString);
            }
            throw new m(String.valueOf(obj));
        }
    }

    public static final class b implements Parcelable.Creator<RichFeatures> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final RichFeatures createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new RichFeatures(parcel.readString(), parcel.readString(), (Template) parcel.readParcelable(RichFeatures.class.getClassLoader()), parcel.readInt() == 0 ? null : com.salesforce.marketingcloud.push.buttons.a.CREATOR.createFromParcel(parcel));
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final RichFeatures[] newArray(int i) {
            return new RichFeatures[i];
        }
    }

    static final class c extends Lambda implements Function0<String> {
        c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unable to serialize " + RichFeatures.this;
        }
    }

    public RichFeatures() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ RichFeatures copy$default(RichFeatures richFeatures, String str, String str2, Template template, com.salesforce.marketingcloud.push.buttons.a aVar, int i, Object obj) {
        if ((i & 1) != 0) {
            str = richFeatures.largeIcon;
        }
        if ((i & 2) != 0) {
            str2 = richFeatures.smallIcon;
        }
        if ((i & 4) != 0) {
            template = richFeatures.viewTemplate;
        }
        if ((i & 8) != 0) {
            aVar = richFeatures.buttons;
        }
        return richFeatures.copy(str, str2, template, aVar);
    }

    @JvmStatic
    public static final RichFeatures fromJson(String str) {
        return Companion.a(str);
    }

    public final String component1() {
        return this.largeIcon;
    }

    public final String component2() {
        return this.smallIcon;
    }

    public final Template component3() {
        return this.viewTemplate;
    }

    public final com.salesforce.marketingcloud.push.buttons.a component4() {
        return this.buttons;
    }

    public final RichFeatures copy(String str, String str2, Template template, com.salesforce.marketingcloud.push.buttons.a aVar) {
        return new RichFeatures(str, str2, template, aVar);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RichFeatures)) {
            return false;
        }
        RichFeatures richFeatures = (RichFeatures) obj;
        return Intrinsics.areEqual(this.largeIcon, richFeatures.largeIcon) && Intrinsics.areEqual(this.smallIcon, richFeatures.smallIcon) && Intrinsics.areEqual(this.viewTemplate, richFeatures.viewTemplate) && Intrinsics.areEqual(this.buttons, richFeatures.buttons);
    }

    public final com.salesforce.marketingcloud.push.buttons.a getButtons() {
        return this.buttons;
    }

    public final String getLargeIcon() {
        return this.largeIcon;
    }

    public final String getSmallIcon() {
        return this.smallIcon;
    }

    public final Template getViewTemplate() {
        return this.viewTemplate;
    }

    public int hashCode() {
        String str = this.largeIcon;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.smallIcon;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Template template = this.viewTemplate;
        int hashCode3 = (hashCode2 + (template == null ? 0 : template.hashCode())) * 31;
        com.salesforce.marketingcloud.push.buttons.a aVar = this.buttons;
        return hashCode3 + (aVar != null ? aVar.hashCode() : 0);
    }

    public final String toJson() {
        Template.Type f;
        j<?> a2;
        String hydrate;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.salesforce.marketingcloud.push.g.b, this.largeIcon);
            jSONObject.put(com.salesforce.marketingcloud.push.g.c, this.smallIcon);
            Template template = this.viewTemplate;
            if (template != null && (f = template.f()) != null && (a2 = j.a.a.a(f)) != null && (hydrate = a2.hydrate(this.viewTemplate)) != null) {
                jSONObject.put(com.salesforce.marketingcloud.push.g.d, new JSONObject(hydrate));
            }
            com.salesforce.marketingcloud.push.buttons.a aVar = this.buttons;
            if (aVar != null) {
                j<?> a3 = j.a.a.a(Template.Type.RichButtons);
                jSONObject.put(com.salesforce.marketingcloud.push.g.f, new JSONArray(a3 != null ? a3.hydrate(aVar) : null));
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            g.a.b(TAG, e, new c());
            return null;
        }
    }

    public String toString() {
        return "RichFeatures(largeIcon=" + this.largeIcon + ", smallIcon=" + this.smallIcon + ", viewTemplate=" + this.viewTemplate + ", buttons=" + this.buttons + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.largeIcon);
        out.writeString(this.smallIcon);
        out.writeParcelable(this.viewTemplate, i);
        com.salesforce.marketingcloud.push.buttons.a aVar = this.buttons;
        if (aVar == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            aVar.writeToParcel(out, i);
        }
    }

    public RichFeatures(String str, String str2, Template template, com.salesforce.marketingcloud.push.buttons.a aVar) {
        this.largeIcon = str;
        this.smallIcon = str2;
        this.viewTemplate = template;
        this.buttons = aVar;
    }

    public /* synthetic */ RichFeatures(String str, String str2, Template template, com.salesforce.marketingcloud.push.buttons.a aVar, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : template, (i & 8) != 0 ? null : aVar);
    }
}
