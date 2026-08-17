package com.tealium.collect.visitor;

import com.tealium.collect.attribute.AttributeGroup;
import com.tealium.collect.attribute.AudienceAttribute;
import com.tealium.collect.attribute.BadgeAttribute;
import com.tealium.collect.attribute.DateAttribute;
import com.tealium.collect.attribute.FlagAttribute;
import com.tealium.collect.attribute.MetricAttribute;
import com.tealium.collect.attribute.PropertyAttribute;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class VisitorProfile extends com.tealium.collect.visitor.a {
    private volatile int g;
    private final AttributeGroup<AudienceAttribute> h;
    private final AttributeGroup<BadgeAttribute> i;
    private final CurrentVisit j;
    private final String k;
    private final boolean l;

    public static final class Builder {
        private long a;
        private Collection<AudienceAttribute> b;
        private Collection<BadgeAttribute> c;
        private Collection<DateAttribute> d;
        private Collection<FlagAttribute> e;
        private Collection<MetricAttribute> f;
        private Collection<PropertyAttribute> g;
        private CurrentVisit h;
        private String i;
        private boolean j;

        public VisitorProfile build() {
            return new VisitorProfile(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.j, this.i);
        }

        public Builder setAudiences(Collection<AudienceAttribute> collection) {
            this.b = collection;
            return this;
        }

        public Builder setBadges(Collection<BadgeAttribute> collection) {
            this.c = collection;
            return this;
        }

        public Builder setCreationDate(long j) {
            this.a = j;
            return this;
        }

        public Builder setCurrentVisit(CurrentVisit currentVisit) {
            this.h = currentVisit;
            return this;
        }

        public Builder setDates(Collection<DateAttribute> collection) {
            this.d = collection;
            return this;
        }

        public Builder setFlags(Collection<FlagAttribute> collection) {
            this.e = collection;
            return this;
        }

        public Builder setIsNewVisitor(boolean z) {
            this.j = z;
            return this;
        }

        public Builder setMetrics(Collection<MetricAttribute> collection) {
            this.f = collection;
            return this;
        }

        public Builder setProperties(Collection<PropertyAttribute> collection) {
            this.g = collection;
            return this;
        }

        public Builder setSource(String str) {
            this.i = str;
            return this;
        }
    }

    private static Set<AudienceAttribute> a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        HashSet hashSet = new HashSet(jSONObject.length());
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String obj = keys.next().toString();
            hashSet.add(new AudienceAttribute(obj, jSONObject.getString(obj)));
        }
        return hashSet;
    }

    private static Set<BadgeAttribute> b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashSet hashSet = new HashSet(jSONObject.length());
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            hashSet.add(new BadgeAttribute(keys.next().toString()));
        }
        return hashSet;
    }

    private static CurrentVisit c(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("dates");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        return new CurrentVisit(jSONObject.optLong("creation_ts", 0L), d(jSONObject.optJSONObject("dates")), e(jSONObject.optJSONObject("flags")), f(jSONObject.optJSONObject("metrics")), g(jSONObject.optJSONObject("properties")), optJSONObject.optLong("last_event_ts", 0L), jSONObject.optInt("total_event_count", 0));
    }

    private static Set<DateAttribute> d(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        HashSet hashSet = new HashSet(jSONObject.length());
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String obj = keys.next().toString();
            hashSet.add(new DateAttribute(obj, jSONObject.optLong(obj, 0L)));
        }
        return hashSet;
    }

    private static Set<FlagAttribute> e(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        HashSet hashSet = new HashSet(jSONObject.length());
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String obj = keys.next().toString();
            hashSet.add(new FlagAttribute(obj, jSONObject.getBoolean(obj)));
        }
        return hashSet;
    }

    private static Set<MetricAttribute> f(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        HashSet hashSet = new HashSet(jSONObject.length());
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String obj = keys.next().toString();
            hashSet.add(new MetricAttribute(obj, jSONObject.optDouble(obj, 0.0d)));
        }
        return hashSet;
    }

    public static VisitorProfile fromJSON(String str) throws JSONException {
        if (str == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        return new Builder().setCreationDate(jSONObject.optLong("creation_ts", 0L)).setAudiences(a(jSONObject.optJSONObject("audiences"))).setBadges(b(jSONObject.optJSONObject("badges"))).setDates(d(jSONObject.optJSONObject("dates"))).setFlags(e(jSONObject.optJSONObject("flags"))).setMetrics(f(jSONObject.optJSONObject("metrics"))).setProperties(g(jSONObject.optJSONObject("properties"))).setCurrentVisit(c(jSONObject.optJSONObject("current_visit"))).setIsNewVisitor(jSONObject.optBoolean("new_visitor", false)).setSource(str).build();
    }

    private static Set<PropertyAttribute> g(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        HashSet hashSet = new HashSet(jSONObject.length());
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String obj = keys.next().toString();
            hashSet.add(new PropertyAttribute(obj, jSONObject.optString(obj, "")));
        }
        return hashSet;
    }

    @Override // com.tealium.collect.visitor.a
    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof VisitorProfile)) {
            return false;
        }
        VisitorProfile visitorProfile = (VisitorProfile) obj;
        return this.h.equals(visitorProfile.h) && this.i.equals(visitorProfile.i) && this.j.equals(visitorProfile.getCurrentVisit()) && this.l == visitorProfile.l && super.equals(visitorProfile);
    }

    public AttributeGroup<AudienceAttribute> getAudiences() {
        return this.h;
    }

    public AttributeGroup<BadgeAttribute> getBadges() {
        return this.i;
    }

    public CurrentVisit getCurrentVisit() {
        return this.j;
    }

    public String getSource() {
        return this.k;
    }

    @Override // com.tealium.collect.visitor.a
    public int hashCode() {
        int i = this.g;
        if (i != 0) {
            return i;
        }
        int hashCode = ((((((((super.hashCode() + 527) * 31) + (this.l ? 1 : 0)) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + this.j.hashCode();
        this.g = hashCode;
        return hashCode;
    }

    public boolean isNewVisitor() {
        return this.l;
    }

    public String toString() {
        String property = System.getProperty("line.separator");
        return "Profile : {" + property + "    creation_ts : " + getCreationTimestamp() + property + "    is_new_visitor : " + this.l + property + "    audiences : " + this.h.toString("    ") + property + "    badges : " + this.i.toString("    ") + property + "    dates : " + getDates().toString("    ") + property + "    flags : " + getFlags().toString("    ") + property + "    metrics : " + getMetrics().toString("    ") + property + "    properties : " + getProperties().toString("    ") + property + "    current_visit : " + this.j.toString("    ") + property + "}";
    }

    private VisitorProfile(long j, Collection<AudienceAttribute> collection, Collection<BadgeAttribute> collection2, Collection<DateAttribute> collection3, Collection<FlagAttribute> collection4, Collection<MetricAttribute> collection5, Collection<PropertyAttribute> collection6, CurrentVisit currentVisit, boolean z, String str) {
        super(j, collection3, collection4, collection5, collection6);
        if (currentVisit == null) {
            this.j = new CurrentVisit();
        } else {
            this.j = currentVisit;
        }
        this.i = new AttributeGroup<>(collection2);
        this.h = new AttributeGroup<>(collection);
        this.l = z;
        this.k = str;
    }
}
