package com.salesforce.marketingcloud.messages.inbox;

import com.salesforce.marketingcloud.internal.o;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class InboxMessage {
    public final String alert;
    public final String custom;
    public final Map<String, String> customKeys;
    private boolean deleted;
    private boolean dirty;
    public final Date endDateUtc;
    public final String id;
    public final String inboxMessage;
    public final String inboxSubtitle;
    public final Media media;
    private final String messageHash;
    public final Integer messageType;
    public NotificationMessage notificationMessage;
    private boolean read;
    private final String requestId;
    public final Date sendDateUtc;
    public final String sound;
    public final Date startDateUtc;
    public final String subject;
    public final String subtitle;
    public final String title;
    public final String url;
    private final int viewCount;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class InboxMessageType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ InboxMessageType[] $VALUES;
        public static final a Companion;
        private final int index;
        public static final InboxMessageType LEGACY = new InboxMessageType("LEGACY", 0, 0);
        public static final InboxMessageType ADV = new InboxMessageType("ADV", 1, 1);
        public static final InboxMessageType PCTI = new InboxMessageType("PCTI", 2, 2);

        public static final class a {
            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final InboxMessageType a(int i) {
                Object obj;
                Iterator<E> it = InboxMessageType.getEntries().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((InboxMessageType) obj).getIndex() == i) {
                        break;
                    }
                }
                return (InboxMessageType) obj;
            }

            private a() {
            }
        }

        private static final /* synthetic */ InboxMessageType[] $values() {
            return new InboxMessageType[]{LEGACY, ADV, PCTI};
        }

        static {
            InboxMessageType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
            Companion = new a(null);
        }

        private InboxMessageType(String str, int i, int i2) {
            this.index = i2;
        }

        public static EnumEntries<InboxMessageType> getEntries() {
            return $ENTRIES;
        }

        public static InboxMessageType valueOf(String str) {
            return (InboxMessageType) Enum.valueOf(InboxMessageType.class, str);
        }

        public static InboxMessageType[] values() {
            return (InboxMessageType[]) $VALUES.clone();
        }

        public final int getIndex() {
            return this.index;
        }
    }

    public static final class Media {
        private final String altText;
        private final String url;

        public Media(String str, String str2) {
            this.url = str;
            this.altText = str2;
        }

        public static /* synthetic */ Media copy$default(Media media, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = media.url;
            }
            if ((i & 2) != 0) {
                str2 = media.altText;
            }
            return media.copy(str, str2);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "altText", imports = {}))
        public final String altText() {
            return this.altText;
        }

        public final String component1() {
            return this.url;
        }

        public final String component2() {
            return this.altText;
        }

        public final Media copy(String str, String str2) {
            return new Media(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Media)) {
                return false;
            }
            Media media = (Media) obj;
            return Intrinsics.areEqual(this.url, media.url) && Intrinsics.areEqual(this.altText, media.altText);
        }

        public final String getAltText() {
            return this.altText;
        }

        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            String str = this.url;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.altText;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Media(url=" + this.url + ", altText=" + this.altText + ")";
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "url", imports = {}))
        public final String url() {
            return this.url;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class a {
        public static final C0067a c;
        public static final a d = new a("INBOX_NO_URL", 0, 0);
        public static final a e = new a("ALERT_INBOX_NO_URL", 1, 1);
        public static final a f = new a("INBOX_CLOUDPAGE", 2, 2);
        public static final a g = new a("ALERT_INBOX_CLOUDPAGE", 3, 3);
        public static final a h = new a("INBOX_NON_CLOUDPAGE", 4, 4);
        public static final a i = new a("ALERT_INBOX_NON_CLOUDPAGE", 5, 5);
        private static final /* synthetic */ a[] j;
        private static final /* synthetic */ EnumEntries k;
        private final int b;

        /* renamed from: com.salesforce.marketingcloud.messages.inbox.InboxMessage$a$a, reason: collision with other inner class name */
        public static final class C0067a {
            public /* synthetic */ C0067a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final a a(int i) {
                Object obj;
                Iterator<E> it = a.b().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((a) obj).c() == i) {
                        break;
                    }
                }
                return (a) obj;
            }

            private C0067a() {
            }
        }

        static {
            a[] a = a();
            j = a;
            k = EnumEntriesKt.enumEntries(a);
            c = new C0067a(null);
        }

        private a(String str, int i2, int i3) {
            this.b = i3;
        }

        private static final /* synthetic */ a[] a() {
            return new a[]{d, e, f, g, h, i};
        }

        public static EnumEntries<a> b() {
            return k;
        }

        public static a valueOf(String str) {
            return (a) Enum.valueOf(a.class, str);
        }

        public static a[] values() {
            return (a[]) j.clone();
        }

        public final int c() {
            return this.b;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.valueOf(this.b);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class b {
        public static final a c;
        public static final b d = new b("PUSH", 0, 1);
        public static final b e = new b("ALERT_INBOX", 1, 3);
        private static final /* synthetic */ b[] f;
        private static final /* synthetic */ EnumEntries g;
        private final int b;

        public static final class a {
            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final b a(int i) {
                Object obj;
                Iterator<E> it = b.b().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((b) obj).c() == i) {
                        break;
                    }
                }
                return (b) obj;
            }

            private a() {
            }
        }

        static {
            b[] a2 = a();
            f = a2;
            g = EnumEntriesKt.enumEntries(a2);
            c = new a(null);
        }

        private b(String str, int i, int i2) {
            this.b = i2;
        }

        private static final /* synthetic */ b[] a() {
            return new b[]{d, e};
        }

        public static EnumEntries<b> b() {
            return g;
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) f.clone();
        }

        public final int c() {
            return this.b;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.valueOf(this.b);
        }
    }

    public InboxMessage(String id, String str, String str2, String str3, String str4, String str5, String str6, Media media, Date date, Date date2, Date date3, String str7, String str8, Map<String, String> map, String str9, String str10, String str11, NotificationMessage notificationMessage, int i, Integer num, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
        this.requestId = str;
        this.messageHash = str2;
        this.subject = str3;
        this.title = str4;
        this.alert = str5;
        this.sound = str6;
        this.media = media;
        this.startDateUtc = date;
        this.endDateUtc = date2;
        this.sendDateUtc = date3;
        this.url = str7;
        this.custom = str8;
        this.customKeys = map;
        this.subtitle = str9;
        this.inboxMessage = str10;
        this.inboxSubtitle = str11;
        this.notificationMessage = notificationMessage;
        this.viewCount = i;
        this.messageType = num;
        this.deleted = z;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "alert", imports = {}))
    public final String alert() {
        return this.alert;
    }

    public final String component1() {
        return this.id;
    }

    public final Date component10() {
        return this.endDateUtc;
    }

    public final Date component11() {
        return this.sendDateUtc;
    }

    public final String component12() {
        return this.url;
    }

    public final String component13() {
        return this.custom;
    }

    public final Map<String, String> component14() {
        return this.customKeys;
    }

    public final String component15() {
        return this.subtitle;
    }

    public final String component16() {
        return this.inboxMessage;
    }

    public final String component17() {
        return this.inboxSubtitle;
    }

    public final NotificationMessage component18() {
        return this.notificationMessage;
    }

    public final int component19$sdk_release() {
        return this.viewCount;
    }

    public final String component2$sdk_release() {
        return this.requestId;
    }

    public final Integer component20() {
        return this.messageType;
    }

    public final boolean component21() {
        return this.deleted;
    }

    public final String component3$sdk_release() {
        return this.messageHash;
    }

    public final String component4() {
        return this.subject;
    }

    public final String component5() {
        return this.title;
    }

    public final String component6() {
        return this.alert;
    }

    public final String component7() {
        return this.sound;
    }

    public final Media component8() {
        return this.media;
    }

    public final Date component9() {
        return this.startDateUtc;
    }

    public final InboxMessage copy(String id, String str, String str2, String str3, String str4, String str5, String str6, Media media, Date date, Date date2, Date date3, String str7, String str8, Map<String, String> map, String str9, String str10, String str11, NotificationMessage notificationMessage, int i, Integer num, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        return new InboxMessage(id, str, str2, str3, str4, str5, str6, media, date, date2, date3, str7, str8, map, str9, str10, str11, notificationMessage, i, num, z);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "custom", imports = {}))
    public final String custom() {
        return this.custom;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "customKeys", imports = {}))
    public final Map<String, String> customKeys() {
        return this.customKeys;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to getter", replaceWith = @ReplaceWith(expression = "getDeleted()", imports = {}))
    public final boolean deleted() {
        return this.deleted;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "endDateUtc", imports = {}))
    public final Date endDateUtc() {
        return this.endDateUtc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InboxMessage)) {
            return false;
        }
        InboxMessage inboxMessage = (InboxMessage) obj;
        return Intrinsics.areEqual(this.id, inboxMessage.id) && Intrinsics.areEqual(this.requestId, inboxMessage.requestId) && Intrinsics.areEqual(this.messageHash, inboxMessage.messageHash) && Intrinsics.areEqual(this.subject, inboxMessage.subject) && Intrinsics.areEqual(this.title, inboxMessage.title) && Intrinsics.areEqual(this.alert, inboxMessage.alert) && Intrinsics.areEqual(this.sound, inboxMessage.sound) && Intrinsics.areEqual(this.media, inboxMessage.media) && Intrinsics.areEqual(this.startDateUtc, inboxMessage.startDateUtc) && Intrinsics.areEqual(this.endDateUtc, inboxMessage.endDateUtc) && Intrinsics.areEqual(this.sendDateUtc, inboxMessage.sendDateUtc) && Intrinsics.areEqual(this.url, inboxMessage.url) && Intrinsics.areEqual(this.custom, inboxMessage.custom) && Intrinsics.areEqual(this.customKeys, inboxMessage.customKeys) && Intrinsics.areEqual(this.subtitle, inboxMessage.subtitle) && Intrinsics.areEqual(this.inboxMessage, inboxMessage.inboxMessage) && Intrinsics.areEqual(this.inboxSubtitle, inboxMessage.inboxSubtitle) && Intrinsics.areEqual(this.notificationMessage, inboxMessage.notificationMessage) && this.viewCount == inboxMessage.viewCount && Intrinsics.areEqual(this.messageType, inboxMessage.messageType) && this.deleted == inboxMessage.deleted;
    }

    public final boolean getDeleted() {
        return this.deleted;
    }

    public final boolean getDirty$sdk_release() {
        return this.dirty;
    }

    public final String getMessageHash$sdk_release() {
        return this.messageHash;
    }

    public final boolean getRead() {
        return this.read;
    }

    public final String getRequestId$sdk_release() {
        return this.requestId;
    }

    public final int getViewCount$sdk_release() {
        return this.viewCount;
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        String str = this.requestId;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.messageHash;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.subject;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.title;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.alert;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.sound;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Media media = this.media;
        int hashCode8 = (hashCode7 + (media == null ? 0 : media.hashCode())) * 31;
        Date date = this.startDateUtc;
        int hashCode9 = (hashCode8 + (date == null ? 0 : date.hashCode())) * 31;
        Date date2 = this.endDateUtc;
        int hashCode10 = (hashCode9 + (date2 == null ? 0 : date2.hashCode())) * 31;
        Date date3 = this.sendDateUtc;
        int hashCode11 = (hashCode10 + (date3 == null ? 0 : date3.hashCode())) * 31;
        String str7 = this.url;
        int hashCode12 = (hashCode11 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.custom;
        int hashCode13 = (hashCode12 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Map<String, String> map = this.customKeys;
        int hashCode14 = (hashCode13 + (map == null ? 0 : map.hashCode())) * 31;
        String str9 = this.subtitle;
        int hashCode15 = (hashCode14 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.inboxMessage;
        int hashCode16 = (hashCode15 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.inboxSubtitle;
        int hashCode17 = (hashCode16 + (str11 == null ? 0 : str11.hashCode())) * 31;
        NotificationMessage notificationMessage = this.notificationMessage;
        int hashCode18 = (((hashCode17 + (notificationMessage == null ? 0 : notificationMessage.hashCode())) * 31) + Integer.hashCode(this.viewCount)) * 31;
        Integer num = this.messageType;
        return ((hashCode18 + (num != null ? num.hashCode() : 0)) * 31) + Boolean.hashCode(this.deleted);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "id", imports = {}))
    public final String id() {
        return this.id;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = com.salesforce.marketingcloud.messages.inbox.b.e, imports = {}))
    public final Media media() {
        return this.media;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to getter", replaceWith = @ReplaceWith(expression = "getRead()", imports = {}))
    public final boolean read() {
        return this.read;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sendDateUtc", imports = {}))
    public final Date sendDateUtc() {
        return this.sendDateUtc;
    }

    public final void setDeleted(boolean z) {
        this.deleted = z;
    }

    public final void setDirty$sdk_release(boolean z) {
        this.dirty = z;
    }

    public final /* synthetic */ void setRead(boolean z) {
        this.read = z;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sound", imports = {}))
    public final String sound() {
        return this.sound;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "startDateUtc", imports = {}))
    public final Date startDateUtc() {
        return this.startDateUtc;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "subject", imports = {}))
    public final String subject() {
        return this.subject;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "title", imports = {}))
    public final String title() {
        return this.title;
    }

    public final JSONObject toJson$sdk_release() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        jSONObject.put("calculatedType", this.messageType);
        jSONObject.put("viewCount", this.viewCount);
        jSONObject.put("isDeleted", this.deleted);
        jSONObject.put("messageType", this.messageType);
        String str = this.url;
        if (str != null) {
            jSONObject.put("url", str);
        }
        String str2 = this.messageHash;
        if (str2 != null) {
            jSONObject.put("hash", str2);
        }
        String str3 = this.requestId;
        if (str3 != null) {
            jSONObject.put("requestId", str3);
        }
        String str4 = this.subject;
        if (str4 != null) {
            jSONObject.put("subject", str4);
        }
        String str5 = this.title;
        if (str5 != null) {
            jSONObject.put("title", str5);
        }
        String str6 = this.alert;
        if (str6 != null) {
            jSONObject.put("alert", str6);
        }
        String str7 = this.sound;
        if (str7 != null) {
            jSONObject.put("sound", str7);
        }
        Media media = this.media;
        if (media != null) {
            jSONObject.put(com.salesforce.marketingcloud.messages.inbox.b.e, com.salesforce.marketingcloud.messages.inbox.b.a(media));
        }
        Date date = this.startDateUtc;
        if (date != null) {
            jSONObject.put("startDateUtc", o.a(date));
        }
        Date date2 = this.endDateUtc;
        if (date2 != null) {
            jSONObject.put("endDateUtc", o.a(date2));
        }
        Date date3 = this.sendDateUtc;
        if (date3 != null) {
            jSONObject.put("sendDateUtc", o.a(date3));
        }
        String str8 = this.custom;
        if (str8 != null) {
            jSONObject.put("custom", str8);
        }
        Map<String, String> map = this.customKeys;
        if (map != null) {
            jSONObject.put("keys", o.a(map));
        }
        String str9 = this.subtitle;
        if (str9 != null) {
            jSONObject.put("subtitle", str9);
        }
        String str10 = this.inboxSubtitle;
        if (str10 != null) {
            jSONObject.put(NotificationMessage.NOTIF_KEY_INBOX_SUB_TITLE, str10);
        }
        String str11 = this.inboxMessage;
        if (str11 != null) {
            jSONObject.put(NotificationMessage.NOTIF_KEY_INBOX_MESSAGE, str11);
        }
        NotificationMessage notificationMessage = this.notificationMessage;
        if (notificationMessage != null) {
            jSONObject.put("notificationMessage", notificationMessage.toJson$sdk_release());
        }
        return jSONObject;
    }

    public final String toJsonString() {
        JSONObject json$sdk_release = toJson$sdk_release();
        if (this.messageType != null) {
            InboxMessageType a2 = InboxMessageType.Companion.a(this.messageType.intValue());
            json$sdk_release.put("messageType", a2 != null ? a2.name() : null);
        }
        String jSONObject = json$sdk_release.toString(2);
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
        return jSONObject;
    }

    public String toString() {
        return "InboxMessage(id=" + this.id + ", requestId=" + this.requestId + ", messageHash=" + this.messageHash + ", subject=" + this.subject + ", title=" + this.title + ", alert=" + this.alert + ", sound=" + this.sound + ", media=" + this.media + ", startDateUtc=" + this.startDateUtc + ", endDateUtc=" + this.endDateUtc + ", sendDateUtc=" + this.sendDateUtc + ", url=" + this.url + ", custom=" + this.custom + ", customKeys=" + this.customKeys + ", subtitle=" + this.subtitle + ", inboxMessage=" + this.inboxMessage + ", inboxSubtitle=" + this.inboxSubtitle + ", notificationMessage=" + this.notificationMessage + ", viewCount=" + this.viewCount + ", messageType=" + this.messageType + ", deleted=" + this.deleted + ")";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "url", imports = {}))
    public final String url() {
        return this.url;
    }

    public /* synthetic */ InboxMessage(String str, String str2, String str3, String str4, String str5, String str6, String str7, Media media, Date date, Date date2, Date date3, String str8, String str9, Map map, String str10, String str11, String str12, NotificationMessage notificationMessage, int i, Integer num, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7, (i2 & 128) != 0 ? null : media, (i2 & 256) != 0 ? null : date, (i2 & 512) != 0 ? null : date2, (i2 & 1024) != 0 ? null : date3, str8, (i2 & 4096) != 0 ? null : str9, (i2 & 8192) != 0 ? null : map, (i2 & 16384) != 0 ? null : str10, (32768 & i2) != 0 ? null : str11, (65536 & i2) != 0 ? null : str12, (131072 & i2) != 0 ? null : notificationMessage, (262144 & i2) != 0 ? 0 : i, num, (i2 & 1048576) != 0 ? false : z);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Removed duplicated region for block: B:40:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public InboxMessage(android.os.Bundle r27) {
        /*
            Method dump skipped, instructions count: 455
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.inbox.InboxMessage.<init>(android.os.Bundle):void");
    }

    public /* synthetic */ InboxMessage(JSONObject jSONObject, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) throws JSONException {
        this(jSONObject, (i & 2) != 0 ? false : z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x00eb, code lost:
    
        if (r12 == com.salesforce.marketingcloud.messages.inbox.InboxMessage.a.g.c()) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0121, code lost:
    
        if (r12 == com.salesforce.marketingcloud.messages.inbox.InboxMessage.b.e.c()) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x023c, code lost:
    
        if (r3 == com.salesforce.marketingcloud.messages.inbox.InboxMessage.b.e.c()) goto L111;
     */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public InboxMessage(org.json.JSONObject r30, boolean r31) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 616
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.inbox.InboxMessage.<init>(org.json.JSONObject, boolean):void");
    }
}
