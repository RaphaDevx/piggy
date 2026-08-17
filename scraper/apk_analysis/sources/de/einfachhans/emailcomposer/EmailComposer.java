package de.einfachhans.emailcomposer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.text.Html;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.getcapacitor.JSArray;
import com.getcapacitor.PluginCall;
import com.salesforce.marketingcloud.config.a;
import io.sentry.SentryLogEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class EmailComposer {
    private static final String MAILTO_SCHEME = "mailto:";
    private final Context ctx;

    public EmailComposer(Context context) {
        this.ctx = context;
    }

    public Intent getIntent(PluginCall pluginCall) throws JSONException {
        String string = pluginCall.getString("subject", "");
        String string2 = pluginCall.getString(SentryLogEvent.JsonKeys.BODY, "");
        CharSequence charSequence = string2;
        if (pluginCall.getBoolean("isHtml", false).booleanValue()) {
            charSequence = Html.fromHtml(string2);
        }
        List list = pluginCall.getArray(TypedValues.TransitionType.S_TO, new JSArray()).toList();
        String[] strArr = (String[]) list.toArray(new String[list.size()]);
        List list2 = pluginCall.getArray("cc", new JSArray()).toList();
        String[] strArr2 = (String[]) list2.toArray(new String[list2.size()]);
        List list3 = pluginCall.getArray("bcc", new JSArray()).toList();
        String[] strArr3 = (String[]) list3.toArray(new String[list3.size()]);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("mailto:"));
        intent.putExtra("android.intent.extra.SUBJECT", string);
        intent.putExtra("android.intent.extra.TEXT", charSequence);
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        intent.putExtra("android.intent.extra.CC", strArr2);
        intent.putExtra("android.intent.extra.BCC", strArr3);
        setAttachments(pluginCall, intent);
        return intent;
    }

    private void setAttachments(PluginCall pluginCall, Intent intent) throws JSONException {
        JSArray array = pluginCall.getArray("attachments", new JSArray());
        ArrayList arrayList = new ArrayList();
        AssetUtil assetUtil = new AssetUtil(this.ctx);
        for (int i = 0; i < array.length(); i++) {
            JSONObject jSONObject = array.getJSONObject(i);
            Uri parse = assetUtil.parse(jSONObject.getString(a.j), jSONObject.getString("type"), jSONObject.optString("name"));
            if (parse != null && parse != Uri.EMPTY) {
                arrayList.add(parse);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        intent.setAction("android.intent.action.SEND_MULTIPLE").setType(MediaType.WILDCARD).addFlags(1).putExtra("android.intent.extra.STREAM", arrayList);
        if (arrayList.size() > 1) {
            return;
        }
        intent.setAction("android.intent.action.SEND").putExtra("android.intent.extra.STREAM", (Parcelable) arrayList.get(0));
    }
}
