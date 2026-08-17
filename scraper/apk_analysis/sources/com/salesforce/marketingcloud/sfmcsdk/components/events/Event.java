package com.salesforce.marketingcloud.sfmcsdk.components.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: Event.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u0002\u0014\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u000eH&J\b\u0010\u000f\u001a\u00020\bH&J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0013R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event;", "", "()V", "category", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Category;", "getCategory", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Category;", "id", "", "producer", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Producer;", "getProducer", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Producer;", "attributes", "", "name", "toJson", "Lorg/json/JSONObject;", "track", "", "Category", "Producer", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class Event {
    private final Category category;
    public final String id;
    private final Producer producer;

    public abstract Map<String, Object> attributes();

    /* renamed from: name */
    public abstract String getName();

    public Event() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        this.id = uuid;
        this.producer = Producer.SFMC_SDK;
        this.category = Category.ENGAGEMENT;
    }

    public Producer getProducer() {
        return this.producer;
    }

    public Category getCategory() {
        return this.category;
    }

    public final void track() {
        SFMCSdk.INSTANCE.track(this);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Event.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Producer;", "", "(Ljava/lang/String;I)V", "APP", "SFMC_SDK", "PUSH", "CDP", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Producer {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Producer[] $VALUES;
        public static final Producer APP = new Producer("APP", 0);
        public static final Producer SFMC_SDK = new Producer("SFMC_SDK", 1);
        public static final Producer PUSH = new Producer("PUSH", 2);
        public static final Producer CDP = new Producer("CDP", 3);

        private static final /* synthetic */ Producer[] $values() {
            return new Producer[]{APP, SFMC_SDK, PUSH, CDP};
        }

        public static EnumEntries<Producer> getEntries() {
            return $ENTRIES;
        }

        public static Producer valueOf(String str) {
            return (Producer) Enum.valueOf(Producer.class, str);
        }

        public static Producer[] values() {
            return (Producer[]) $VALUES.clone();
        }

        private Producer(String str, int i) {
        }

        static {
            Producer[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Event.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event$Category;", "", "(Ljava/lang/String;I)V", "APPLICATION", "ENGAGEMENT", "IDENTITY", "SYSTEM", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Category {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Category[] $VALUES;
        public static final Category APPLICATION = new Category("APPLICATION", 0);
        public static final Category ENGAGEMENT = new Category("ENGAGEMENT", 1);
        public static final Category IDENTITY = new Category("IDENTITY", 2);
        public static final Category SYSTEM = new Category("SYSTEM", 3);

        private static final /* synthetic */ Category[] $values() {
            return new Category[]{APPLICATION, ENGAGEMENT, IDENTITY, SYSTEM};
        }

        public static EnumEntries<Category> getEntries() {
            return $ENTRIES;
        }

        public static Category valueOf(String str) {
            return (Category) Enum.valueOf(Category.class, str);
        }

        public static Category[] values() {
            return (Category[]) $VALUES.clone();
        }

        private Category(String str, int i) {
        }

        static {
            Category[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", getName());
        jSONObject.put("id", this.id);
        jSONObject.put("producer", getProducer());
        jSONObject.put("category", getCategory());
        JSONObject jSONObject2 = new JSONObject();
        Iterator<T> it = attributes().entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry entry = (Map.Entry) it.next();
            try {
                Object value = entry.getValue();
                if (value instanceof SFMCSdkEvent) {
                    String str = (String) entry.getKey();
                    Object value2 = entry.getValue();
                    Intrinsics.checkNotNull(value2, "null cannot be cast to non-null type com.salesforce.marketingcloud.sfmcsdk.components.events.SFMCSdkEvent");
                    jSONObject2.put(str, ((SFMCSdkEvent) value2).toJson());
                } else {
                    boolean z = true;
                    if (!(value instanceof Number ? true : value instanceof String ? true : value instanceof Character)) {
                        z = value instanceof Boolean;
                    }
                    if (z) {
                        jSONObject2.put((String) entry.getKey(), entry.getValue());
                    } else {
                        jSONObject2.put((String) entry.getKey(), entry.getValue());
                    }
                }
            } catch (Exception unused) {
                SFMCSdkLogger sFMCSdkLogger = SFMCSdkLogger.INSTANCE;
                String name = jSONObject2.getClass().getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                sFMCSdkLogger.w(name, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.events.Event$toJson$1$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "Could not convert attribute (" + entry + ") to JSON.";
                    }
                });
            }
        }
        Unit unit = Unit.INSTANCE;
        jSONObject.put("attributes", jSONObject2);
        return jSONObject;
    }
}
