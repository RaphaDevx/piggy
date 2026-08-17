package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Pa extends ViewModel {
    public final MutableLiveData a = new MutableLiveData();
    public final MutableLiveData b = new MutableLiveData();
    public final MutableLiveData c = new MutableLiveData();
    public final MutableLiveData d = new MutableLiveData();
    public final MutableLiveData e = new MutableLiveData();
    public final Xa f = new Xa();
    public final Xa g = new Xa();
    public Ae h;

    public static void a(Pa pa, CharSequence title, String str, int i) {
        boolean z = (i & 2) != 0;
        if ((i & 4) != 0) {
            str = null;
        }
        pa.getClass();
        Intrinsics.checkNotNullParameter(title, "title");
        pa.e.postValue(new Oa(title, z, str));
    }
}
