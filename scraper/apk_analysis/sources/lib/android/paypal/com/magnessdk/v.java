package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
class v extends q {
    private static v I1 = null;
    private static final int J1 = -1000000;
    private static final String K1 = "";
    private Handler A1;
    private j y1;
    private MagnesSettings z1;
    private Context x1 = null;
    private List<String> B1 = new ArrayList();
    private long C1 = 0;
    private boolean D1 = false;
    private boolean E1 = false;
    private boolean F1 = false;
    private d G1 = d.a();
    private HashMap<String, EditText> H1 = new HashMap<>();

    class a implements TextWatcher {
        String a = "";
        final /* synthetic */ String b;

        a(String str) {
            this.b = str;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!v.this.b(this.b) || charSequence == null) {
                return;
            }
            this.a = charSequence.toString();
            if (i2 - i3 > 1) {
                v.this.B1.add(g.m.CUT_EVENT.toString());
                v.this.C1 = System.currentTimeMillis();
                v.this.G1.b = true;
                v.this.E1 = true;
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!v.this.b(this.b) || charSequence == null) {
                return;
            }
            String b = v.this.b(this.a, charSequence.toString());
            if (charSequence.length() == 0) {
                boolean z = v.this.E1;
                v vVar = v.this;
                if (z) {
                    vVar.E1 = false;
                    return;
                } else {
                    vVar.C1 = System.currentTimeMillis();
                    return;
                }
            }
            if (b.length() <= 1) {
                if (v.this.E1) {
                    return;
                }
                if (i2 > i3) {
                    v.this.G1.e = true;
                    v.this.D1 = true;
                }
                v.this.c();
                return;
            }
            if (v.this.F1) {
                v.this.B1.add(g.m.AUTO_COMPLETE_EVENT.toString());
                v.this.C1 = System.currentTimeMillis();
                v.this.G1.c = true;
                return;
            }
            if (charSequence != "") {
                v.this.B1.add(g.m.PRE_FILLED_EVENT.toString());
                v.this.G1.d = true;
            }
        }
    }

    class b implements View.OnFocusChangeListener {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        b(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (v.this.b(this.a)) {
                v.this.a(this.a, this.b, z);
            }
        }
    }

    class c {
        String a;
        String[] b;
        JSONObject c;
        String d;

        private c() {
        }

        c(String str, List<String> list, JSONObject jSONObject, String str2) {
            String[] a = a(list);
            this.a = str;
            this.b = a;
            this.c = jSONObject;
            this.d = str2;
        }

        private String[] a(List<String> list) {
            String[] strArr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                strArr[i] = list.get(i);
            }
            return strArr;
        }

        JSONArray a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(g.m.FLIGHT_TIME.toString(), new JSONArray(this.b));
            jSONObject.put(g.m.TELEMETRY_EVENTS.toString(), this.c);
            jSONObject.put(g.m.VIEW_ID.toString(), this.a);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            return jSONArray;
        }
    }

    static class d {
        private static d f;
        boolean a = false;
        boolean b = false;
        boolean c = false;
        boolean d = false;
        boolean e = false;

        private d() {
        }

        static synchronized d a() {
            d dVar;
            synchronized (d.class) {
                if (f == null) {
                    f = new d();
                }
                dVar = f;
            }
            return dVar;
        }
    }

    v() {
    }

    private int a(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == charSequence2) {
            return J1;
        }
        int i = 0;
        if (charSequence != null && charSequence2 != null) {
            while (i < charSequence.length() && i < charSequence2.length() && charSequence.charAt(i) == charSequence2.charAt(i)) {
                i++;
            }
            if (i >= charSequence2.length() && i >= charSequence.length()) {
                return J1;
            }
        }
        return i;
    }

    private void a(String str, EditText editText) {
        if (this.H1.get(str) == null) {
            this.H1.put(str, editText);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, boolean z) {
        if (z) {
            this.F1 = true;
        } else {
            this.F1 = false;
            c(str, str2);
        }
    }

    private void a(c cVar) {
        try {
            JSONArray a2 = cVar.a();
            boolean a3 = q.a(q.s1);
            JSONObject a4 = a3 ? q.a(cVar.d, a2, q.s1) : q.b(cVar.d, a2, q.s1);
            if (a4 != null) {
                new i(g.h.d.PRODUCTION_JSON_URL, a4, a3, this.z1, this.A1).c();
            }
        } catch (Exception e) {
            e.a(getClass(), 3, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        int a2 = a((CharSequence) str, (CharSequence) str2);
        return a2 == J1 ? "" : str2.substring(a2);
    }

    static synchronized v b() {
        v vVar;
        synchronized (v.class) {
            if (I1 == null) {
                I1 = new v();
            }
            vVar = I1;
        }
        return vVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        return this.H1.get(str) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.G1.a = true;
        if (this.C1 == 0) {
            this.C1 = System.currentTimeMillis();
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - this.C1;
        this.C1 = currentTimeMillis;
        if (this.D1) {
            j *= -1;
            this.D1 = false;
        }
        if (j != 0) {
            this.B1.add(String.valueOf(j));
        }
    }

    private void c(String str) {
        if (this.H1.get(str) != null) {
            this.H1.remove(str);
        }
    }

    private void c(String str, String str2) {
        d dVar = this.G1;
        boolean z = dVar.a || dVar.c || dVar.b || dVar.e;
        if (!this.B1.isEmpty() || z) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(g.m.REGULAR_TYPING_EVENT.toString(), this.G1.a);
                jSONObject.put(g.m.AUTO_COMPLETE_EVENT.toString(), this.G1.c);
                jSONObject.put(g.m.CUT_EVENT.toString(), this.G1.b);
                jSONObject.put(g.m.BACKSPACE_EVENT.toString(), this.G1.e);
            } catch (Exception e) {
                e.a(getClass(), 3, e);
            }
            a(new c(str, this.B1, jSONObject, str2));
        }
        c(str);
        d();
    }

    private void d() {
        d dVar = this.G1;
        dVar.a = false;
        dVar.c = false;
        dVar.b = false;
        dVar.e = false;
        this.B1.clear();
        this.C1 = 0L;
    }

    @Override // lib.android.paypal.com.magnessdk.q
    JSONObject a() {
        return null;
    }

    @Override // lib.android.paypal.com.magnessdk.q
    JSONObject a(MagnesSettings magnesSettings, j jVar, k kVar) {
        return null;
    }

    @Override // lib.android.paypal.com.magnessdk.q
    void a(int i, MagnesSettings magnesSettings) {
    }

    void a(EditText editText, String str, String str2, Context context, boolean z) {
        this.x1 = context;
        if (a(this.y1, this.z1.getMagnesSource(), q.k1, q.s1, this.z1.getContext())) {
            a(str, editText);
            editText.addTextChangedListener(new a(str));
            if (z) {
                return;
            }
            editText.setOnFocusChangeListener(new b(str, str2));
        }
    }

    void a(j jVar, MagnesSettings magnesSettings, Handler handler) {
        this.y1 = jVar;
        this.z1 = magnesSettings;
        this.A1 = handler;
    }

    void b(String str, String str2, boolean z) {
        if (b(str)) {
            a(str, str2, z);
        }
    }
}
