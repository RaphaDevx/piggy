package ch.coop.supercardapp;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

/* loaded from: classes3.dex */
public class MainActivity extends BridgeActivity {
    @Override // com.getcapacitor.BridgeActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        getTheme().applyStyle(R.style.OptOutEdgeToEdgeEnforcement, false);
        super.onCreate(bundle);
    }
}
