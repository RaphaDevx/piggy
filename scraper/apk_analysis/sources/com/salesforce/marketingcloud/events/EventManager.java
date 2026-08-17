package com.salesforce.marketingcloud.events;

import com.salesforce.marketingcloud.sfmcsdk.BuildConfig;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public abstract class EventManager {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = com.salesforce.marketingcloud.g.a("EventManager");

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class AuthEventType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ AuthEventType[] $VALUES;
        public static final AuthEventType LOGIN = new AuthEventType("LOGIN", 0);

        private static final /* synthetic */ AuthEventType[] $values() {
            return new AuthEventType[]{LOGIN};
        }

        static {
            AuthEventType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private AuthEventType(String str, int i) {
        }

        public static EnumEntries<AuthEventType> getEntries() {
            return $ENTRIES;
        }

        public static AuthEventType valueOf(String str) {
            return (AuthEventType) Enum.valueOf(AuthEventType.class, str);
        }

        public static AuthEventType[] values() {
            return (AuthEventType[]) $VALUES.clone();
        }
    }

    public static final class Companion {

        static final class a extends Lambda implements Function0<String> {
            final /* synthetic */ String b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(String str) {
                super(0);
                this.b = str;
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return this.b + " contains a \".\" and will be dropped.";
            }
        }

        static final class b extends Lambda implements Function0<String> {
            final /* synthetic */ String b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(String str) {
                super(0);
                this.b = str;
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return this.b + " is null, blank, starts with a \"$\", or contains a line break and will be dropped.";
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Event a(Companion companion, String str, Map map, Event.Producer producer, int i, Object obj) {
            if ((i & 2) != 0) {
                map = MapsKt.emptyMap();
            }
            if ((i & 4) != 0) {
                producer = Event.Producer.PUSH;
            }
            return companion.customEvent(str, map, producer);
        }

        public final String b(String input) {
            Intrinsics.checkNotNullParameter(input, "input");
            String obj = StringsKt.trim((CharSequence) input).toString();
            if (!StringsKt.isBlank(obj) && !StringsKt.startsWith$default(obj, "$", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) obj, (CharSequence) StringUtils.LF, false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) obj, (CharSequence) StringUtils.CR, false, 2, (Object) null)) {
                return obj;
            }
            com.salesforce.marketingcloud.g.e(com.salesforce.marketingcloud.g.a, EventManager.TAG, null, new b(input), 2, null);
            return null;
        }

        @JvmStatic
        public final Event customEvent(String name, Map<String, ? extends Object> attributes, Event.Producer producer) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(producer, "producer");
            String b2 = b(name);
            if (b2 == null) {
                return null;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<String, ? extends Object> entry : attributes.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                String a2 = EventManager.Companion.a(key);
                if (a2 != null) {
                    linkedHashMap.put(a2, value);
                }
            }
            return new com.salesforce.marketingcloud.events.b(b2, linkedHashMap, producer);
        }

        private Companion() {
        }

        public final String a(String input) {
            Intrinsics.checkNotNullParameter(input, "input");
            if (StringsKt.contains$default((CharSequence) input, (CharSequence) ".", false, 2, (Object) null)) {
                com.salesforce.marketingcloud.g.e(com.salesforce.marketingcloud.g.a, EventManager.TAG, null, new a(input), 2, null);
                return null;
            }
            return b(input);
        }

        @JvmStatic
        public final Event customEvent(String name, Map<String, ? extends Object> attributes) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return customEvent(name, attributes, Event.Producer.PUSH);
        }

        @JvmStatic
        public final Event customEvent(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return customEvent(name, MapsKt.emptyMap(), Event.Producer.PUSH);
        }
    }

    @JvmStatic
    public static final Event customEvent(String str) {
        return Companion.customEvent(str);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "To leverage the benefits of the Unified SDK propagating your tracked events across all configured products, please use its event tracking API rather than the Push specific event tracking.", replaceWith = @ReplaceWith(expression = "SFMCSdk.track(event)", imports = {BuildConfig.LIBRARY_PACKAGE_NAME}))
    public abstract void track(Event... eventArr);

    @JvmStatic
    public static final Event customEvent(String str, Map<String, ? extends Object> map) {
        return Companion.customEvent(str, map);
    }

    @JvmStatic
    public static final Event customEvent(String str, Map<String, ? extends Object> map, Event.Producer producer) {
        return Companion.customEvent(str, map, producer);
    }
}
