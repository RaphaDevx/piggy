package com.salesforce.marketingcloud.messages.iam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout;

/* loaded from: classes2.dex */
public class c extends Fragment {
    private k a;

    class a implements Animation.AnimationListener {
        final /* synthetic */ boolean a;

        a(boolean z) {
            this.a = z;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            FragmentActivity activity;
            View view = c.this.getView();
            if (view != null) {
                view.setLayerType(0, null);
            }
            if (this.a || (activity = c.this.getActivity()) == null) {
                return;
            }
            activity.finish();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public static c a(k kVar) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("messageHandler", kVar);
        c cVar = new c();
        cVar.setArguments(bundle);
        return cVar;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.a = (k) getArguments().getParcelable("messageHandler");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public Animation onCreateAnimation(int i, boolean z, int i2) {
        View view;
        Animation onCreateAnimation = super.onCreateAnimation(i, z, i2);
        if (onCreateAnimation == null && i2 != 0) {
            onCreateAnimation = AnimationUtils.loadAnimation(getActivity(), i2);
        }
        if (onCreateAnimation != null && (view = getView()) != null) {
            view.setLayerType(2, null);
            onCreateAnimation.setAnimationListener(new a(z));
        }
        return onCreateAnimation;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        k kVar = this.a;
        if (kVar == null) {
            return null;
        }
        View inflate = layoutInflater.inflate(a(kVar.l()), viewGroup, false);
        b bVar = new b(getActivity() instanceof View.OnClickListener ? (View.OnClickListener) getActivity() : null, this.a.s());
        bVar.a(inflate, this.a);
        SwipeDismissConstraintLayout swipeDismissConstraintLayout = (SwipeDismissConstraintLayout) inflate.findViewById(bVar.g());
        if (swipeDismissConstraintLayout != null && (getActivity() instanceof SwipeDismissConstraintLayout.SwipeDismissListener)) {
            swipeDismissConstraintLayout.setListener((SwipeDismissConstraintLayout.SwipeDismissListener) getActivity());
        }
        return inflate;
    }

    private int a(InAppMessage inAppMessage) {
        if (inAppMessage.type() == InAppMessage.Type.bannerTop) {
            return R.layout.mcsdk_iam_banner_top;
        }
        return R.layout.mcsdk_iam_banner_bottom;
    }
}
