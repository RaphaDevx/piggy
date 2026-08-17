package ch.coop.supercardapp.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcard;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercardDatamatrixData;
import ch.coop.capacitor.extensionconnector.widget.WidgetType;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.R;
import ch.coop.supercardapp.core.helpers.BarcodeHelper;
import ch.coop.supercardapp.core.helpers.FormatHelper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.ITFWriter;
import com.salesforce.marketingcloud.push.g;
import io.sentry.Sentry;
import java.util.AbstractMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class WidgetHelper {
    private final Context context;

    public WidgetHelper(Context context) {
        this.context = context;
    }

    public void updateBalanceView(RemoteViews remoteViews, CardsGiftcard cardsGiftcard) {
        Map<String, Integer> m;
        if (AppContainer.getInstance(this.context).getDataUpdateState().getNeedsReauthorization().booleanValue()) {
            int i = R.id.widget_giftcard_balance_error_text;
            m = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_balance_error_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_balance_error_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_balance_error_it))});
            setTextInAppLanguage(remoteViews, i, m);
            remoteViews.setViewVisibility(R.id.widget_giftcard_balance_text, 8);
            remoteViews.setViewVisibility(R.id.widget_giftcard_balance_error_text, 0);
        } else {
            remoteViews.setTextViewText(R.id.widget_giftcard_balance_text, FormatHelper.formatCents(this.context, cardsGiftcard.getBalanceValue().intValue()));
            remoteViews.setViewVisibility(R.id.widget_giftcard_balance_text, 0);
            remoteViews.setViewVisibility(R.id.widget_giftcard_balance_error_text, 8);
        }
        remoteViews.setViewVisibility(R.id.widget_giftcard_employee_nr_wrapper, 8);
    }

    public void updateITFBarcode(RemoteViews remoteViews, String str) {
        updateBarcode(remoteViews, str, new ITFWriter(), BarcodeFormat.ITF);
    }

    public void updateCode128Barcode(RemoteViews remoteViews, String str) {
        updateBarcode(remoteViews, str, new Code128Writer(), BarcodeFormat.CODE_128);
    }

    private void updateBarcode(RemoteViews remoteViews, String str, Writer writer, BarcodeFormat barcodeFormat) {
        BitMatrix bitMatrix;
        Map<EncodeHintType, ?> m;
        try {
            m = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry(EncodeHintType.MARGIN, 0)});
            bitMatrix = writer.encode(str, barcodeFormat, 1200, 1, m);
        } catch (WriterException e) {
            Sentry.captureException(e);
            bitMatrix = null;
        }
        remoteViews.setImageViewBitmap(R.id.widget_giftcard_barcode, BarcodeHelper.createBitmap(bitMatrix, true));
    }

    /* renamed from: ch.coop.supercardapp.widget.WidgetHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType;

        static {
            int[] iArr = new int[WidgetType.values().length];
            $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType = iArr;
            try {
                iArr[WidgetType.SUPERCARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType[WidgetType.GIFTCARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType[WidgetType.PAYMENTCARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType[WidgetType.SUPERCARDPAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType[WidgetType.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public void updateLink(RemoteViews remoteViews, String str, WidgetType widgetType) {
        String str2;
        int i = AnonymousClass1.$SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType[widgetType.ordinal()];
        if (i == 1) {
            str2 = LoyaltySupercardDatamatrixData.SERIALIZED_NAME_SUPERCARD;
        } else if (i == 2) {
            str2 = "giftcard";
        } else if (i == 3) {
            str2 = "paymentcard";
        } else if (i == 4) {
            str2 = "supercardpay";
        } else {
            str2 = "";
        }
        remoteViews.setOnClickPendingIntent(R.id.widget_layout, PendingIntent.getActivity(this.context, 0, new Intent("android.intent.action.VIEW", Uri.parse(str).buildUpon().scheme("https").authority(this.context.getString(R.string.deeplink_host)).appendQueryParameter("source", "widget").appendQueryParameter("sourceName", str2).build()), 201326592));
    }

    public void setTextInAppLanguage(RemoteViews remoteViews, int i, Map<String, Integer> map) {
        Integer num = map.get(AppContainer.getInstance(this.context).getAppState().getAppLanguage());
        if (num != null) {
            remoteViews.setTextViewText(i, this.context.getText(num.intValue()));
        }
    }

    public void setImageInAppLanguage(RemoteViews remoteViews, int i, Map<String, Integer> map) {
        remoteViews.setImageViewResource(i, map.get(AppContainer.getInstance(this.context).getAppState().getAppLanguage()).intValue());
    }

    public RemoteViews dataMissingViews(WidgetType widgetType) {
        Map<String, Integer> m;
        RemoteViews remoteViews = new RemoteViews(this.context.getPackageName(), R.layout.widget_giftcard_data_missing);
        int i = R.id.widget_data_missing_text;
        m = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("de", Integer.valueOf(R.string.widget_data_missing_de)), new AbstractMap.SimpleEntry("fr", Integer.valueOf(R.string.widget_data_missing_fr)), new AbstractMap.SimpleEntry(g.h, Integer.valueOf(R.string.widget_data_missing_it))});
        setTextInAppLanguage(remoteViews, i, m);
        updateLink(remoteViews, "/app/cards", widgetType);
        return remoteViews;
    }
}
