package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0002\b\u001d\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0003\u0010\b\u001a\u00020\u0006\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0012J\u0010\u0010\u0015\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u0012J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJr\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b!\u0010\u0012J\u0010\u0010\"\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\"\u0010\u0016J\u001a\u0010%\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b%\u0010&R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010\u0012R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b*\u0010(\u001a\u0004\b+\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b,\u0010(\u001a\u0004\b-\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u0010\u0016R\u001a\u0010\b\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b1\u0010/\u001a\u0004\b2\u0010\u0016R\u001c\u0010\t\u001a\u0004\u0018\u00010\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u0010\u0019R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u0010\u001bR\u0019\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b9\u0010(\u001a\u0004\b:\u0010\u0012R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010\u001eR\u0014\u0010?\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b>\u0010\u0016¨\u0006@"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/TextCardData;", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardData;", "", "itemId", "title", "contentDescription", "", "backgroundColor", "errorColor", "iconRes", "", "showError", FirebaseAnalytics.Param.CONTENT, "Landroid/graphics/Bitmap;", "image", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/Integer;ZLjava/lang/String;Landroid/graphics/Bitmap;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "()I", "component5", "component6", "()Ljava/lang/Integer;", "component7", "()Z", "component8", "component9", "()Landroid/graphics/Bitmap;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/Integer;ZLjava/lang/String;Landroid/graphics/Bitmap;)Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/TextCardData;", "toString", "hashCode", "", Request.JsonKeys.OTHER, "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getItemId", "b", "getTitle", "c", "getContentDescription", "d", "I", "getBackgroundColor", "e", "getErrorColor", "f", "Ljava/lang/Integer;", "getIconRes", "g", "Z", "getShowError", "h", "getContent", "i", "Landroid/graphics/Bitmap;", "getImage", "getCardHeight", "cardHeight", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final /* data */ class TextCardData implements CardData {

    /* renamed from: a, reason: from kotlin metadata */
    private final String itemId;

    /* renamed from: b, reason: from kotlin metadata */
    private final String title;

    /* renamed from: c, reason: from kotlin metadata */
    private final String contentDescription;

    /* renamed from: d, reason: from kotlin metadata */
    private final int backgroundColor;

    /* renamed from: e, reason: from kotlin metadata */
    private final int errorColor;

    /* renamed from: f, reason: from kotlin metadata */
    private final Integer iconRes;

    /* renamed from: g, reason: from kotlin metadata */
    private final boolean showError;

    /* renamed from: h, reason: from kotlin metadata */
    private final String content;

    /* renamed from: i, reason: from kotlin metadata */
    private final Bitmap image;

    public TextCardData(String itemId, String str, String contentDescription, int i, int i2, Integer num, boolean z, String str2, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Intrinsics.checkNotNullParameter(contentDescription, "contentDescription");
        this.itemId = itemId;
        this.title = str;
        this.contentDescription = contentDescription;
        this.backgroundColor = i;
        this.errorColor = i2;
        this.iconRes = num;
        this.showError = z;
        this.content = str2;
        this.image = bitmap;
    }

    /* renamed from: component1, reason: from getter */
    public final String getItemId() {
        return this.itemId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component3, reason: from getter */
    public final String getContentDescription() {
        return this.contentDescription;
    }

    /* renamed from: component4, reason: from getter */
    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    /* renamed from: component5, reason: from getter */
    public final int getErrorColor() {
        return this.errorColor;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getIconRes() {
        return this.iconRes;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getShowError() {
        return this.showError;
    }

    /* renamed from: component8, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component9, reason: from getter */
    public final Bitmap getImage() {
        return this.image;
    }

    public final TextCardData copy(String itemId, String title, String contentDescription, int backgroundColor, int errorColor, Integer iconRes, boolean showError, String content, Bitmap image) {
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Intrinsics.checkNotNullParameter(contentDescription, "contentDescription");
        return new TextCardData(itemId, title, contentDescription, backgroundColor, errorColor, iconRes, showError, content, image);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextCardData)) {
            return false;
        }
        TextCardData textCardData = (TextCardData) other;
        return Intrinsics.areEqual(this.itemId, textCardData.itemId) && Intrinsics.areEqual(this.title, textCardData.title) && Intrinsics.areEqual(this.contentDescription, textCardData.contentDescription) && this.backgroundColor == textCardData.backgroundColor && this.errorColor == textCardData.errorColor && Intrinsics.areEqual(this.iconRes, textCardData.iconRes) && this.showError == textCardData.showError && Intrinsics.areEqual(this.content, textCardData.content) && Intrinsics.areEqual(this.image, textCardData.image);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public int getCardHeight() {
        return PixelExtensionsKt.pxFromDp$default(88, (Context) null, 1, (Object) null);
    }

    public final String getContent() {
        return this.content;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public String getContentDescription() {
        return this.contentDescription;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public int getErrorColor() {
        return this.errorColor;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public Integer getIconRes() {
        return this.iconRes;
    }

    public final Bitmap getImage() {
        return this.image;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public String getItemId() {
        return this.itemId;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public boolean getShowError() {
        return this.showError;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int hashCode = this.itemId.hashCode() * 31;
        String str = this.title;
        int hashCode2 = (Integer.hashCode(this.errorColor) + ((Integer.hashCode(this.backgroundColor) + ((this.contentDescription.hashCode() + ((hashCode + (str == null ? 0 : str.hashCode())) * 31)) * 31)) * 31)) * 31;
        Integer num = this.iconRes;
        int hashCode3 = (Boolean.hashCode(this.showError) + ((hashCode2 + (num == null ? 0 : num.hashCode())) * 31)) * 31;
        String str2 = this.content;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Bitmap bitmap = this.image;
        return hashCode4 + (bitmap != null ? bitmap.hashCode() : 0);
    }

    public String toString() {
        return "TextCardData(itemId=" + this.itemId + ", title=" + this.title + ", contentDescription=" + this.contentDescription + ", backgroundColor=" + this.backgroundColor + ", errorColor=" + this.errorColor + ", iconRes=" + this.iconRes + ", showError=" + this.showError + ", content=" + this.content + ", image=" + this.image + ')';
    }

    public /* synthetic */ TextCardData(String str, String str2, String str3, int i, int i2, Integer num, boolean z, String str4, Bitmap bitmap, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? "" : str3, (i3 & 8) != 0 ? -1 : i, (i3 & 16) != 0 ? ViewCompat.MEASURED_STATE_MASK : i2, (i3 & 32) != 0 ? null : num, (i3 & 64) != 0 ? false : z, (i3 & 128) != 0 ? null : str4, (i3 & 256) == 0 ? bitmap : null);
    }
}
