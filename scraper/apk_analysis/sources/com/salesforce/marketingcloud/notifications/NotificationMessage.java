package com.salesforce.marketingcloud.notifications;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.internal.o;
import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.push.data.RichFeatures;
import com.salesforce.marketingcloud.storage.db.i;
import io.sentry.protocol.Geo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class NotificationMessage implements Parcelable {
    public static final String NOTIF_KEY_ALERT = "alert";
    public static final String NOTIF_KEY_MESSAGE_DATE_UTC = "messageDateUtc";
    public static final String NOTIF_KEY_SOUND = "sound";
    public static final String NOTIF_KEY_SUB_TITLE = "subtitle";
    public static final String NOTIF_KEY_TIMESTAMP = "timestamp";
    public static final String NOTIF_KEY_TITLE = "title";
    public final String alert;
    public final String custom;
    public final Map<String, String> customKeys;
    public final String id;
    public final String mediaAltText;
    public final String mediaUrl;
    private int notificationId;
    public final Map<String, String> payload;
    private final String propertyBag;
    public final Region region;
    public final String requestId;
    public final RichFeatures richFeatures;
    public final Sound sound;
    public final String soundName;
    public final String subtitle;
    public final String title;
    public final Trigger trigger;
    public final Type type;
    public final String url;
    public static final a Companion = new a(null);
    public static final Parcelable.Creator<NotificationMessage> CREATOR = new b();
    public static final String NOTIF_KEY_ID = "_m";
    public static final String NOTIF_KEY_SID = "_sid";
    public static final String NOTIF_KEY_MESSAGE_TYPE = "_mt";
    public static final String NOTIF_KEY_MESSAGE_HASH = "_h";
    public static final String NOTIF_KEY_REQUEST_ID = "_r";
    public static final String NOTIF_KEY_PB_ID = "_pb";
    public static final String NOTIF_KEY_MEDIA_URL = "_mediaUrl";
    public static final String NOTIF_KEY_MEDIA_ALT = "_mediaAlt";
    public static final String NOTIF_KEY_CLOUD_PAGE_URL = "_x";
    public static final String NOTIF_KEY_OPEN_DIRECT_URL = "_od";
    public static final String NOTIF_KEY_CONTENT_TYPE = "_ct";
    public static final String NOTIF_KEY_INBOX_SUB_TITLE = "inboxSubtitle";
    public static final String NOTIF_KEY_INBOX_MESSAGE = "inboxMessage";
    public static final String NOTIF_KEY_RICH_FEATURES = "_rf";
    public static final String NOTIF_KEY_END_DATE = "_endDt";
    private static final String[] KNOWN_KEYS = {NOTIF_KEY_ID, NOTIF_KEY_SID, "timestamp", NOTIF_KEY_MESSAGE_TYPE, NOTIF_KEY_MESSAGE_HASH, NOTIF_KEY_REQUEST_ID, NOTIF_KEY_PB_ID, "title", "subtitle", "alert", "sound", NOTIF_KEY_MEDIA_URL, NOTIF_KEY_MEDIA_ALT, NOTIF_KEY_CLOUD_PAGE_URL, NOTIF_KEY_OPEN_DIRECT_URL, NOTIF_KEY_CONTENT_TYPE, NOTIF_KEY_INBOX_SUB_TITLE, NOTIF_KEY_INBOX_MESSAGE, NOTIF_KEY_RICH_FEATURES, NOTIF_KEY_END_DATE, "messageDateUtc"};

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Sound {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Sound[] $VALUES;
        public static final Sound CUSTOM = new Sound("CUSTOM", 0);
        public static final Sound DEFAULT = new Sound("DEFAULT", 1);
        public static final Sound NONE = new Sound("NONE", 2);

        private static final /* synthetic */ Sound[] $values() {
            return new Sound[]{CUSTOM, DEFAULT, NONE};
        }

        static {
            Sound[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private Sound(String str, int i) {
        }

        public static EnumEntries<Sound> getEntries() {
            return $ENTRIES;
        }

        public static Sound valueOf(String str) {
            return (Sound) Enum.valueOf(Sound.class, str);
        }

        public static Sound[] values() {
            return (Sound[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Trigger {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Trigger[] $VALUES;
        public static final Trigger PUSH = new Trigger("PUSH", 0);
        public static final Trigger GEOFENCE = new Trigger("GEOFENCE", 1);
        public static final Trigger BEACON = new Trigger("BEACON", 2);
        public static final Trigger DOWNLOAD = new Trigger("DOWNLOAD", 3);

        private static final /* synthetic */ Trigger[] $values() {
            return new Trigger[]{PUSH, GEOFENCE, BEACON, DOWNLOAD};
        }

        static {
            Trigger[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private Trigger(String str, int i) {
        }

        public static EnumEntries<Trigger> getEntries() {
            return $ENTRIES;
        }

        public static Trigger valueOf(String str) {
            return (Trigger) Enum.valueOf(Trigger.class, str);
        }

        public static Trigger[] values() {
            return (Trigger[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Type {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Type[] $VALUES;
        public static final Type OPEN_DIRECT = new Type("OPEN_DIRECT", 0);
        public static final Type CLOUD_PAGE = new Type("CLOUD_PAGE", 1);
        public static final Type OTHER = new Type("OTHER", 2);

        private static final /* synthetic */ Type[] $values() {
            return new Type[]{OPEN_DIRECT, CLOUD_PAGE, OTHER};
        }

        static {
            Type[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        private Type(String str, int i) {
        }

        public static EnumEntries<Type> getEntries() {
            return $ENTRIES;
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String[] a() {
            return NotificationMessage.KNOWN_KEYS;
        }

        private a() {
        }

        public final NotificationMessage a(Map<String, String> data) {
            HashMap hashMap;
            Iterator<Map.Entry<String, String>> it;
            String str;
            RichFeatures richFeatures;
            Intrinsics.checkNotNullParameter(data, "data");
            RichFeatures richFeatures2 = null;
            Pair pair = data.containsKey(NotificationMessage.NOTIF_KEY_CLOUD_PAGE_URL) ? TuplesKt.to(Type.CLOUD_PAGE, data.get(NotificationMessage.NOTIF_KEY_CLOUD_PAGE_URL)) : data.containsKey(NotificationMessage.NOTIF_KEY_OPEN_DIRECT_URL) ? TuplesKt.to(Type.OPEN_DIRECT, data.get(NotificationMessage.NOTIF_KEY_OPEN_DIRECT_URL)) : TuplesKt.to(Type.OTHER, null);
            Type type = (Type) pair.component1();
            String str2 = (String) pair.component2();
            Pair<Sound, String> a = a(data.get("sound"));
            Sound component1 = a.component1();
            String component2 = a.component2();
            String str3 = data.get(NotificationMessage.NOTIF_KEY_ID);
            if (str3 == null) {
                throw new IllegalStateException("message id missing");
            }
            String str4 = str3;
            String str5 = data.get(NotificationMessage.NOTIF_KEY_REQUEST_ID);
            String str6 = data.get("title");
            String str7 = data.get("subtitle");
            String str8 = data.get("alert");
            if (str8 == null) {
                throw new IllegalStateException("alert missing");
            }
            String str9 = str8;
            String str10 = data.get(NotificationMessage.NOTIF_KEY_MEDIA_URL);
            String str11 = data.get(NotificationMessage.NOTIF_KEY_MEDIA_ALT);
            HashMap hashMap2 = new HashMap(data);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator<Map.Entry<String, String>> it2 = data.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<String, String> next = it2.next();
                String key = next.getKey();
                if (ArraysKt.contains(NotificationMessage.Companion.a(), key)) {
                    it = it2;
                    hashMap = hashMap2;
                    str = str2;
                    richFeatures = null;
                    if (!StringsKt.startsWith$default(key, ".google", false, 2, (Object) null)) {
                        it2 = it;
                        richFeatures2 = richFeatures;
                        str2 = str;
                        hashMap2 = hashMap;
                    }
                } else {
                    hashMap = hashMap2;
                    it = it2;
                    str = str2;
                    richFeatures = null;
                }
                linkedHashMap.put(next.getKey(), next.getValue());
                it2 = it;
                richFeatures2 = richFeatures;
                str2 = str;
                hashMap2 = hashMap;
            }
            HashMap hashMap3 = hashMap2;
            String str12 = str2;
            RichFeatures richFeatures3 = richFeatures2;
            Trigger trigger = Trigger.PUSH;
            String str13 = data.get(NotificationMessage.NOTIF_KEY_PB_ID);
            String str14 = data.get(NotificationMessage.NOTIF_KEY_RICH_FEATURES);
            return new NotificationMessage(str4, str5, null, str9, component1, component2, str6, str7, type, trigger, str12, str10, str11, linkedHashMap, null, hashMap3, str14 != null ? RichFeatures.Companion.a(str14) : richFeatures3, str13, 0, 278532, null);
        }

        public final NotificationMessage a(Message message, Region region) {
            Pair pair;
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(region, "region");
            String str = message.url;
            if (str != null) {
                pair = TuplesKt.to(Type.CLOUD_PAGE, str);
            } else {
                String str2 = message.openDirect;
                pair = str2 != null ? TuplesKt.to(Type.OPEN_DIRECT, str2) : TuplesKt.to(Type.OTHER, null);
            }
            Type type = (Type) pair.component1();
            String str3 = (String) pair.component2();
            Pair<Sound, String> a = a(message.sound);
            Sound component1 = a.component1();
            String component2 = a.component2();
            Trigger trigger = message.messageType == 5 ? Trigger.BEACON : Trigger.GEOFENCE;
            String str4 = message.id;
            String str5 = message.title;
            String str6 = message.alert;
            Map hashMap = message.customKeys != null ? new HashMap(message.customKeys) : MapsKt.emptyMap();
            String str7 = message.custom;
            Message.Media media = message.media;
            return new NotificationMessage(str4, null, region, str6, component1, component2, str5, null, type, trigger, str3, media != null ? media.url : null, media != null ? media.altText : null, hashMap, str7, null, null, null, 0, 491650, null);
        }

        public final NotificationMessage a(JSONObject json) {
            Map<String, String> emptyMap;
            String optString;
            String optString2;
            Intrinsics.checkNotNullParameter(json, "json");
            String optString3 = json.optString("sound");
            Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
            String b = o.b(optString3);
            if (b == null) {
                b = "";
            }
            Pair<Sound, String> a = a(b);
            Sound component1 = a.component1();
            String component2 = a.component2();
            String optString4 = json.optString("id");
            Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
            String b2 = o.b(optString4);
            if (b2 != null) {
                String optString5 = json.optString("requestId");
                Intrinsics.checkNotNullExpressionValue(optString5, "optString(...)");
                String b3 = o.b(optString5);
                String optString6 = json.optString("alert");
                Intrinsics.checkNotNullExpressionValue(optString6, "optString(...)");
                String b4 = o.b(optString6);
                if (b4 != null) {
                    String optString7 = json.optString("title");
                    Intrinsics.checkNotNullExpressionValue(optString7, "optString(...)");
                    String b5 = o.b(optString7);
                    String optString8 = json.optString("subtitle");
                    Intrinsics.checkNotNullExpressionValue(optString8, "optString(...)");
                    String b6 = o.b(optString8);
                    Type type = Type.OTHER;
                    Trigger trigger = Trigger.DOWNLOAD;
                    String optString9 = json.optString("url");
                    Intrinsics.checkNotNullExpressionValue(optString9, "optString(...)");
                    String b7 = o.b(optString9);
                    JSONObject optJSONObject = json.optJSONObject(com.salesforce.marketingcloud.messages.inbox.b.e);
                    String b8 = (optJSONObject == null || (optString2 = optJSONObject.optString(com.salesforce.marketingcloud.messages.inbox.b.k)) == null) ? null : o.b(optString2);
                    JSONObject optJSONObject2 = json.optJSONObject(com.salesforce.marketingcloud.messages.inbox.b.e);
                    String b9 = (optJSONObject2 == null || (optString = optJSONObject2.optString(com.salesforce.marketingcloud.messages.inbox.b.l)) == null) ? null : o.b(optString);
                    JSONArray optJSONArray = json.optJSONArray("keys");
                    if (optJSONArray == null || (emptyMap = o.b(optJSONArray)) == null) {
                        emptyMap = MapsKt.emptyMap();
                    }
                    Map<String, String> map = emptyMap;
                    String optString10 = json.optString("custom");
                    Intrinsics.checkNotNullExpressionValue(optString10, "optString(...)");
                    String b10 = o.b(optString10);
                    String optString11 = json.optString(com.salesforce.marketingcloud.messages.inbox.b.m);
                    Intrinsics.checkNotNullExpressionValue(optString11, "optString(...)");
                    String b11 = o.b(optString11);
                    return new NotificationMessage(b2, b3, null, b4, component1, component2, b5, b6, type, trigger, b7, b8, b9, map, b10, null, b11 != null ? RichFeatures.Companion.a(b11) : null, null, 0, 425988, null);
                }
                throw new IllegalArgumentException("alert is required and cannot be null or empty");
            }
            throw new IllegalArgumentException("id is required and cannot be null or empty");
        }

        public final Pair<Sound, String> a(String str) {
            if (str == null || StringsKt.equals(str, "none", true)) {
                return TuplesKt.to(Sound.NONE, null);
            }
            return StringsKt.equals(str, "default", true) ? TuplesKt.to(Sound.DEFAULT, null) : TuplesKt.to(Sound.CUSTOM, str);
        }
    }

    public static final class b implements Parcelable.Creator<NotificationMessage> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final NotificationMessage createFromParcel(Parcel parcel) {
            LinkedHashMap linkedHashMap;
            String str;
            LinkedHashMap linkedHashMap2;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            Region createFromParcel = parcel.readInt() == 0 ? null : Region.CREATOR.createFromParcel(parcel);
            String readString3 = parcel.readString();
            Sound valueOf = Sound.valueOf(parcel.readString());
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            Type valueOf2 = Type.valueOf(parcel.readString());
            Trigger valueOf3 = Trigger.valueOf(parcel.readString());
            String readString7 = parcel.readString();
            String readString8 = parcel.readString();
            String readString9 = parcel.readString();
            int readInt = parcel.readInt();
            LinkedHashMap linkedHashMap3 = new LinkedHashMap(readInt);
            int i = 0;
            while (i != readInt) {
                linkedHashMap3.put(parcel.readString(), parcel.readString());
                i++;
                readInt = readInt;
                readString8 = readString8;
            }
            String str2 = readString8;
            String readString10 = parcel.readString();
            if (parcel.readInt() == 0) {
                str = readString10;
                linkedHashMap = linkedHashMap3;
                linkedHashMap2 = null;
            } else {
                int readInt2 = parcel.readInt();
                LinkedHashMap linkedHashMap4 = new LinkedHashMap(readInt2);
                linkedHashMap = linkedHashMap3;
                int i2 = 0;
                while (i2 != readInt2) {
                    linkedHashMap4.put(parcel.readString(), parcel.readString());
                    i2++;
                    readInt2 = readInt2;
                    readString10 = readString10;
                }
                str = readString10;
                linkedHashMap2 = linkedHashMap4;
            }
            return new NotificationMessage(readString, readString2, createFromParcel, readString3, valueOf, readString4, readString5, readString6, valueOf2, valueOf3, readString7, str2, readString9, linkedHashMap, str, linkedHashMap2, parcel.readInt() == 0 ? null : RichFeatures.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final NotificationMessage[] newArray(int i) {
            return new NotificationMessage[i];
        }
    }

    public NotificationMessage(String id, String str, Region region, String alert, Sound sound, String str2, String str3, String str4, Type type, Trigger trigger, String str5, String str6, String str7, Map<String, String> customKeys, String str8, Map<String, String> map, RichFeatures richFeatures, String str9, int i) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(alert, "alert");
        Intrinsics.checkNotNullParameter(sound, "sound");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(trigger, "trigger");
        Intrinsics.checkNotNullParameter(customKeys, "customKeys");
        this.id = id;
        this.requestId = str;
        this.region = region;
        this.alert = alert;
        this.sound = sound;
        this.soundName = str2;
        this.title = str3;
        this.subtitle = str4;
        this.type = type;
        this.trigger = trigger;
        this.url = str5;
        this.mediaUrl = str6;
        this.mediaAltText = str7;
        this.customKeys = customKeys;
        this.custom = str8;
        this.payload = map;
        this.richFeatures = richFeatures;
        this.propertyBag = str9;
        this.notificationId = i;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "alert", imports = {}))
    public final String alert() {
        return this.alert;
    }

    public final String component1() {
        return this.id;
    }

    public final Trigger component10() {
        return this.trigger;
    }

    public final String component11() {
        return this.url;
    }

    public final String component12() {
        return this.mediaUrl;
    }

    public final String component13() {
        return this.mediaAltText;
    }

    public final Map<String, String> component14() {
        return this.customKeys;
    }

    public final String component15() {
        return this.custom;
    }

    public final Map<String, String> component16() {
        return this.payload;
    }

    public final RichFeatures component17() {
        return this.richFeatures;
    }

    public final String component18$sdk_release() {
        return this.propertyBag;
    }

    public final int component19$sdk_release() {
        return this.notificationId;
    }

    public final String component2() {
        return this.requestId;
    }

    public final Region component3() {
        return this.region;
    }

    public final String component4() {
        return this.alert;
    }

    public final Sound component5() {
        return this.sound;
    }

    public final String component6() {
        return this.soundName;
    }

    public final String component7() {
        return this.title;
    }

    public final String component8() {
        return this.subtitle;
    }

    public final Type component9() {
        return this.type;
    }

    public final NotificationMessage copy(String id, String str, Region region, String alert, Sound sound, String str2, String str3, String str4, Type type, Trigger trigger, String str5, String str6, String str7, Map<String, String> customKeys, String str8, Map<String, String> map, RichFeatures richFeatures, String str9, int i) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(alert, "alert");
        Intrinsics.checkNotNullParameter(sound, "sound");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(trigger, "trigger");
        Intrinsics.checkNotNullParameter(customKeys, "customKeys");
        return new NotificationMessage(id, str, region, alert, sound, str2, str3, str4, type, trigger, str5, str6, str7, customKeys, str8, map, richFeatures, str9, i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "custom", imports = {}))
    public final String custom() {
        return this.custom;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "customKeys", imports = {}))
    public final Map<String, String> customKeys() {
        return this.customKeys;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationMessage)) {
            return false;
        }
        NotificationMessage notificationMessage = (NotificationMessage) obj;
        return Intrinsics.areEqual(this.id, notificationMessage.id) && Intrinsics.areEqual(this.requestId, notificationMessage.requestId) && Intrinsics.areEqual(this.region, notificationMessage.region) && Intrinsics.areEqual(this.alert, notificationMessage.alert) && this.sound == notificationMessage.sound && Intrinsics.areEqual(this.soundName, notificationMessage.soundName) && Intrinsics.areEqual(this.title, notificationMessage.title) && Intrinsics.areEqual(this.subtitle, notificationMessage.subtitle) && this.type == notificationMessage.type && this.trigger == notificationMessage.trigger && Intrinsics.areEqual(this.url, notificationMessage.url) && Intrinsics.areEqual(this.mediaUrl, notificationMessage.mediaUrl) && Intrinsics.areEqual(this.mediaAltText, notificationMessage.mediaAltText) && Intrinsics.areEqual(this.customKeys, notificationMessage.customKeys) && Intrinsics.areEqual(this.custom, notificationMessage.custom) && Intrinsics.areEqual(this.payload, notificationMessage.payload) && Intrinsics.areEqual(this.richFeatures, notificationMessage.richFeatures) && Intrinsics.areEqual(this.propertyBag, notificationMessage.propertyBag) && this.notificationId == notificationMessage.notificationId;
    }

    public final int getNotificationId$sdk_release() {
        return this.notificationId;
    }

    public final String getPropertyBag$sdk_release() {
        return this.propertyBag;
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        String str = this.requestId;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Region region = this.region;
        int hashCode3 = (((((hashCode2 + (region == null ? 0 : region.hashCode())) * 31) + this.alert.hashCode()) * 31) + this.sound.hashCode()) * 31;
        String str2 = this.soundName;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.title;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.subtitle;
        int hashCode6 = (((((hashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.type.hashCode()) * 31) + this.trigger.hashCode()) * 31;
        String str5 = this.url;
        int hashCode7 = (hashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.mediaUrl;
        int hashCode8 = (hashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.mediaAltText;
        int hashCode9 = (((hashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31) + this.customKeys.hashCode()) * 31;
        String str8 = this.custom;
        int hashCode10 = (hashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Map<String, String> map = this.payload;
        int hashCode11 = (hashCode10 + (map == null ? 0 : map.hashCode())) * 31;
        RichFeatures richFeatures = this.richFeatures;
        int hashCode12 = (hashCode11 + (richFeatures == null ? 0 : richFeatures.hashCode())) * 31;
        String str9 = this.propertyBag;
        return ((hashCode12 + (str9 != null ? str9.hashCode() : 0)) * 31) + Integer.hashCode(this.notificationId);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "id", imports = {}))
    public final String id() {
        return this.id;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "mediaAltText", imports = {}))
    public final String mediaAltText() {
        return this.mediaAltText;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = i.a.e, imports = {}))
    public final String mediaUrl() {
        return this.mediaUrl;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "to be removed")
    public final int notificationId() {
        return this.notificationId;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "payload", imports = {}))
    public final Map<String, String> payload() {
        return this.payload;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "to be removed")
    public final String propertyBag() {
        return this.propertyBag;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = Geo.JsonKeys.REGION, imports = {}))
    public final Region region() {
        return this.region;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "requestId", imports = {}))
    public final String requestId() {
        return this.requestId;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = com.salesforce.marketingcloud.messages.inbox.b.m, imports = {}))
    public final RichFeatures richFeatures() {
        return this.richFeatures;
    }

    public final void setNotificationId$sdk_release(int i) {
        this.notificationId = i;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sound", imports = {}))
    public final Sound sound() {
        return this.sound;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "soundName", imports = {}))
    public final String soundName() {
        return this.soundName;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "subtitle", imports = {}))
    public final String subtitle() {
        return this.subtitle;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "title", imports = {}))
    public final String title() {
        return this.title;
    }

    public final JSONObject toJson$sdk_release() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        String str = this.requestId;
        if (str != null) {
            jSONObject.put("requestId", str);
        }
        jSONObject.put("alert", this.alert);
        jSONObject.put("sound", this.sound.name());
        String str2 = this.soundName;
        if (str2 != null) {
            jSONObject.put("sound", str2);
        }
        String str3 = this.title;
        if (str3 != null) {
            jSONObject.put("title", str3);
        }
        String str4 = this.subtitle;
        if (str4 != null) {
            jSONObject.put("subtitle", str4);
        }
        jSONObject.put("type", this.type.name());
        jSONObject.put(com.salesforce.marketingcloud.messages.inbox.b.n, this.trigger.name());
        String str5 = this.url;
        if (str5 != null) {
            jSONObject.put("url", str5);
        }
        String str6 = this.mediaUrl;
        if (str6 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(com.salesforce.marketingcloud.messages.inbox.b.k, str6);
            String str7 = this.mediaAltText;
            if (str7 != null) {
                jSONObject2.put(com.salesforce.marketingcloud.messages.inbox.b.l, str7);
            }
            Unit unit = Unit.INSTANCE;
            jSONObject.put(com.salesforce.marketingcloud.messages.inbox.b.e, jSONObject2);
        }
        if (!this.customKeys.isEmpty()) {
            jSONObject.put("keys", new JSONObject(this.customKeys));
        }
        String str8 = this.custom;
        if (str8 != null) {
            jSONObject.put("custom", str8);
        }
        RichFeatures richFeatures = this.richFeatures;
        if (richFeatures != null) {
            jSONObject.put(com.salesforce.marketingcloud.messages.inbox.b.m, richFeatures);
        }
        return jSONObject;
    }

    public String toString() {
        return "NotificationMessage(id=" + this.id + ", requestId=" + this.requestId + ", region=" + this.region + ", alert=" + this.alert + ", sound=" + this.sound + ", soundName=" + this.soundName + ", title=" + this.title + ", subtitle=" + this.subtitle + ", type=" + this.type + ", trigger=" + this.trigger + ", url=" + this.url + ", mediaUrl=" + this.mediaUrl + ", mediaAltText=" + this.mediaAltText + ", customKeys=" + this.customKeys + ", custom=" + this.custom + ", payload=" + this.payload + ", richFeatures=" + this.richFeatures + ", propertyBag=" + this.propertyBag + ", notificationId=" + this.notificationId + ")";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = com.salesforce.marketingcloud.messages.inbox.b.n, imports = {}))
    public final Trigger trigger() {
        return this.trigger;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "type", imports = {}))
    public final Type type() {
        return this.type;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "url", imports = {}))
    public final String url() {
        return this.url;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.id);
        out.writeString(this.requestId);
        Region region = this.region;
        if (region == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            region.writeToParcel(out, i);
        }
        out.writeString(this.alert);
        out.writeString(this.sound.name());
        out.writeString(this.soundName);
        out.writeString(this.title);
        out.writeString(this.subtitle);
        out.writeString(this.type.name());
        out.writeString(this.trigger.name());
        out.writeString(this.url);
        out.writeString(this.mediaUrl);
        out.writeString(this.mediaAltText);
        Map<String, String> map = this.customKeys;
        out.writeInt(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            out.writeString(entry.getKey());
            out.writeString(entry.getValue());
        }
        out.writeString(this.custom);
        Map<String, String> map2 = this.payload;
        if (map2 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            out.writeInt(map2.size());
            for (Map.Entry<String, String> entry2 : map2.entrySet()) {
                out.writeString(entry2.getKey());
                out.writeString(entry2.getValue());
            }
        }
        RichFeatures richFeatures = this.richFeatures;
        if (richFeatures == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            richFeatures.writeToParcel(out, i);
        }
        out.writeString(this.propertyBag);
        out.writeInt(this.notificationId);
    }

    public /* synthetic */ NotificationMessage(String str, String str2, Region region, String str3, Sound sound, String str4, String str5, String str6, Type type, Trigger trigger, String str7, String str8, String str9, Map map, String str10, Map map2, RichFeatures richFeatures, String str11, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : region, str3, sound, (i2 & 32) != 0 ? null : str4, (i2 & 64) != 0 ? null : str5, (i2 & 128) != 0 ? null : str6, type, trigger, (i2 & 1024) != 0 ? null : str7, (i2 & 2048) != 0 ? null : str8, (i2 & 4096) != 0 ? null : str9, (i2 & 8192) != 0 ? MapsKt.emptyMap() : map, (i2 & 16384) != 0 ? null : str10, (32768 & i2) != 0 ? null : map2, (65536 & i2) != 0 ? null : richFeatures, (131072 & i2) != 0 ? null : str11, (i2 & 262144) != 0 ? -1 : i);
    }
}
