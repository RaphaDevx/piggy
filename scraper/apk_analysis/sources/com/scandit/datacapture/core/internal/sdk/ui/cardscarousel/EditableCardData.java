package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.content.Context;
import androidx.core.view.ViewCompat;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\u0000\n\u0002\b\"\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\b\u001a\u00020\u0006\u0012\b\b\u0003\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0010\u001a\u00020\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0014J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u0014J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u0014J\u0010\u0010 \u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b \u0010\u001aJ\u0012\u0010!\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\u0014J\u0010\u0010\"\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\"\u0010\u001dJ\u0092\u0001\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\b\u001a\u00020\u00062\b\b\u0003\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0010\u001a\u00020\nHÆ\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b%\u0010\u0014J\u0010\u0010&\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b&\u0010\u001aJ\u001a\u0010)\u001a\u00020\n2\b\u0010(\u001a\u0004\u0018\u00010'HÖ\u0003¢\u0006\u0004\b)\u0010*R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010\u0014R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b.\u0010,\u001a\u0004\b/\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b0\u0010,\u001a\u0004\b1\u0010\u0014R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u0010\u0018R\u001a\u0010\b\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u0010\u001aR\u001a\u0010\t\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b8\u00106\u001a\u0004\b9\u0010\u001aR\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010\u001dR\u0019\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b=\u0010,\u001a\u0004\b>\u0010\u0014R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b?\u0010,\u001a\u0004\b@\u0010\u0014R\u0017\u0010\u000e\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bA\u00106\u001a\u0004\bB\u0010\u001aR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\bC\u0010,\u001a\u0004\bD\u0010\u0014R\u0017\u0010\u0010\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\bE\u0010;\u001a\u0004\bF\u0010\u001dR\u0014\u0010H\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010\u001a¨\u0006I"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/EditableCardData;", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/CardData;", "", "itemId", "title", "contentDescription", "", "iconRes", "backgroundColor", "errorColor", "", "showError", "hint", "text", "inputType", "errorText", "showLoading", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;IIZLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Z)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "()Ljava/lang/Integer;", "component5", "()I", "component6", "component7", "()Z", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;IIZLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Z)Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/EditableCardData;", "toString", "hashCode", "", Request.JsonKeys.OTHER, "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getItemId", "b", "getTitle", "c", "getContentDescription", "d", "Ljava/lang/Integer;", "getIconRes", "e", "I", "getBackgroundColor", "f", "getErrorColor", "g", "Z", "getShowError", "h", "getHint", "i", "getText", "j", "getInputType", "k", "getErrorText", "l", "getShowLoading", "getCardHeight", "cardHeight", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final /* data */ class EditableCardData implements CardData {

    /* renamed from: a, reason: from kotlin metadata */
    private final String itemId;

    /* renamed from: b, reason: from kotlin metadata */
    private final String title;

    /* renamed from: c, reason: from kotlin metadata */
    private final String contentDescription;

    /* renamed from: d, reason: from kotlin metadata */
    private final Integer iconRes;

    /* renamed from: e, reason: from kotlin metadata */
    private final int backgroundColor;

    /* renamed from: f, reason: from kotlin metadata */
    private final int errorColor;

    /* renamed from: g, reason: from kotlin metadata */
    private final boolean showError;

    /* renamed from: h, reason: from kotlin metadata */
    private final String hint;

    /* renamed from: i, reason: from kotlin metadata */
    private final String text;

    /* renamed from: j, reason: from kotlin metadata */
    private final int inputType;

    /* renamed from: k, reason: from kotlin metadata */
    private final String errorText;

    /* renamed from: l, reason: from kotlin metadata */
    private final boolean showLoading;

    public EditableCardData(String itemId, String str, String contentDescription, Integer num, int i, int i2, boolean z, String str2, String str3, int i3, String str4, boolean z2) {
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Intrinsics.checkNotNullParameter(contentDescription, "contentDescription");
        this.itemId = itemId;
        this.title = str;
        this.contentDescription = contentDescription;
        this.iconRes = num;
        this.backgroundColor = i;
        this.errorColor = i2;
        this.showError = z;
        this.hint = str2;
        this.text = str3;
        this.inputType = i3;
        this.errorText = str4;
        this.showLoading = z2;
    }

    /* renamed from: component1, reason: from getter */
    public final String getItemId() {
        return this.itemId;
    }

    /* renamed from: component10, reason: from getter */
    public final int getInputType() {
        return this.inputType;
    }

    /* renamed from: component11, reason: from getter */
    public final String getErrorText() {
        return this.errorText;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getShowLoading() {
        return this.showLoading;
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
    public final Integer getIconRes() {
        return this.iconRes;
    }

    /* renamed from: component5, reason: from getter */
    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    /* renamed from: component6, reason: from getter */
    public final int getErrorColor() {
        return this.errorColor;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getShowError() {
        return this.showError;
    }

    /* renamed from: component8, reason: from getter */
    public final String getHint() {
        return this.hint;
    }

    /* renamed from: component9, reason: from getter */
    public final String getText() {
        return this.text;
    }

    public final EditableCardData copy(String itemId, String title, String contentDescription, Integer iconRes, int backgroundColor, int errorColor, boolean showError, String hint, String text, int inputType, String errorText, boolean showLoading) {
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Intrinsics.checkNotNullParameter(contentDescription, "contentDescription");
        return new EditableCardData(itemId, title, contentDescription, iconRes, backgroundColor, errorColor, showError, hint, text, inputType, errorText, showLoading);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EditableCardData)) {
            return false;
        }
        EditableCardData editableCardData = (EditableCardData) other;
        return Intrinsics.areEqual(this.itemId, editableCardData.itemId) && Intrinsics.areEqual(this.title, editableCardData.title) && Intrinsics.areEqual(this.contentDescription, editableCardData.contentDescription) && Intrinsics.areEqual(this.iconRes, editableCardData.iconRes) && this.backgroundColor == editableCardData.backgroundColor && this.errorColor == editableCardData.errorColor && this.showError == editableCardData.showError && Intrinsics.areEqual(this.hint, editableCardData.hint) && Intrinsics.areEqual(this.text, editableCardData.text) && this.inputType == editableCardData.inputType && Intrinsics.areEqual(this.errorText, editableCardData.errorText) && this.showLoading == editableCardData.showLoading;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public int getCardHeight() {
        return PixelExtensionsKt.pxFromDp$default(104, (Context) null, 1, (Object) null);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public String getContentDescription() {
        return this.contentDescription;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public int getErrorColor() {
        return this.errorColor;
    }

    public final String getErrorText() {
        return this.errorText;
    }

    public final String getHint() {
        return this.hint;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public Integer getIconRes() {
        return this.iconRes;
    }

    public final int getInputType() {
        return this.inputType;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public String getItemId() {
        return this.itemId;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public boolean getShowError() {
        return this.showError;
    }

    public final boolean getShowLoading() {
        return this.showLoading;
    }

    public final String getText() {
        return this.text;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData
    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int hashCode = this.itemId.hashCode() * 31;
        String str = this.title;
        int hashCode2 = (this.contentDescription.hashCode() + ((hashCode + (str == null ? 0 : str.hashCode())) * 31)) * 31;
        Integer num = this.iconRes;
        int hashCode3 = (Boolean.hashCode(this.showError) + ((Integer.hashCode(this.errorColor) + ((Integer.hashCode(this.backgroundColor) + ((hashCode2 + (num == null ? 0 : num.hashCode())) * 31)) * 31)) * 31)) * 31;
        String str2 = this.hint;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.text;
        int hashCode5 = (Integer.hashCode(this.inputType) + ((hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31)) * 31;
        String str4 = this.errorText;
        return Boolean.hashCode(this.showLoading) + ((hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31);
    }

    public String toString() {
        return "EditableCardData(itemId=" + this.itemId + ", title=" + this.title + ", contentDescription=" + this.contentDescription + ", iconRes=" + this.iconRes + ", backgroundColor=" + this.backgroundColor + ", errorColor=" + this.errorColor + ", showError=" + this.showError + ", hint=" + this.hint + ", text=" + this.text + ", inputType=" + this.inputType + ", errorText=" + this.errorText + ", showLoading=" + this.showLoading + ')';
    }

    public /* synthetic */ EditableCardData(String str, String str2, String str3, Integer num, int i, int i2, boolean z, String str4, String str5, int i3, String str6, boolean z2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i4 & 2) != 0 ? null : str2, (i4 & 4) != 0 ? "" : str3, (i4 & 8) != 0 ? null : num, (i4 & 16) != 0 ? -1 : i, (i4 & 32) != 0 ? ViewCompat.MEASURED_STATE_MASK : i2, (i4 & 64) != 0 ? false : z, (i4 & 128) != 0 ? null : str4, (i4 & 256) != 0 ? null : str5, (i4 & 512) != 0 ? 1 : i3, (i4 & 1024) == 0 ? str6 : null, (i4 & 2048) == 0 ? z2 : false);
    }
}
