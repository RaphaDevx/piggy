package io.sentry.android.replay.viewhierarchy;

import android.graphics.Rect;
import android.view.View;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.TextUnit;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.tealium.library.DataSources;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SentryReplayOptions;
import io.sentry.android.replay.SentryReplayModifiers;
import io.sentry.android.replay.util.ComposeTextLayout;
import io.sentry.android.replay.util.NodesKt;
import io.sentry.android.replay.util.TextAttributes;
import io.sentry.android.replay.util.ViewsKt;
import io.sentry.android.replay.viewhierarchy.ViewHierarchyNode;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ComposeViewHierarchyNode.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J \u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u0018J\u001a\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0002J\u0017\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\u0011\u001a\u00020\u0012H\u0001¢\u0006\u0002\b\"J\u001e\u0010#\u001a\u00020\u000e*\u0004\u0018\u00010 2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J$\u0010$\u001a\u00020%*\u00020\u00122\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u001c\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0002R\u001d\u0010\u0007\u001a\u0004\u0018\u00010\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lio/sentry/android/replay/viewhierarchy/ComposeViewHierarchyNode;", "", "()V", "_rootCoordinates", "Ljava/lang/ref/WeakReference;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "get_rootCoordinates$annotations", "getSemanticsConfigurationMethod", "Ljava/lang/reflect/Method;", "getGetSemanticsConfigurationMethod", "()Ljava/lang/reflect/Method;", "getSemanticsConfigurationMethod$delegate", "Lkotlin/Lazy;", "semanticsRetrievalErrorLogged", "", "fromComposeNode", "Lio/sentry/android/replay/viewhierarchy/ViewHierarchyNode;", "node", "Landroidx/compose/ui/node/LayoutNode;", "parent", "distance", "", "isComposeRoot", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "fromView", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Landroid/view/View;", "getProxyClassName", "", "isImage", FirebaseConstants.Commands.CONFIGURE, "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "retrieveSemanticsConfiguration", "retrieveSemanticsConfiguration$sentry_android_replay_release", "shouldMask", "traverse", "", "parentNode", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ComposeViewHierarchyNode {
    private static WeakReference<LayoutCoordinates> _rootCoordinates;
    private static boolean semanticsRetrievalErrorLogged;
    public static final ComposeViewHierarchyNode INSTANCE = new ComposeViewHierarchyNode();

    /* renamed from: getSemanticsConfigurationMethod$delegate, reason: from kotlin metadata */
    private static final Lazy getSemanticsConfigurationMethod = LazyKt.lazy(new Function0<Method>() { // from class: io.sentry.android.replay.viewhierarchy.ComposeViewHierarchyNode$getSemanticsConfigurationMethod$2
        @Override // kotlin.jvm.functions.Function0
        public final Method invoke() {
            try {
                Method declaredMethod = LayoutNode.class.getDeclaredMethod("getSemanticsConfiguration", null);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (Throwable unused) {
                return null;
            }
        }
    });
    public static final int $stable = 8;

    private static /* synthetic */ void get_rootCoordinates$annotations() {
    }

    private ComposeViewHierarchyNode() {
    }

    private final Method getGetSemanticsConfigurationMethod() {
        return (Method) getSemanticsConfigurationMethod.getValue();
    }

    @JvmStatic
    public static final SemanticsConfiguration retrieveSemanticsConfiguration$sentry_android_replay_release(LayoutNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        Method getSemanticsConfigurationMethod2 = INSTANCE.getGetSemanticsConfigurationMethod();
        if (getSemanticsConfigurationMethod2 != null) {
            return (SemanticsConfiguration) getSemanticsConfigurationMethod2.invoke(node, null);
        }
        return node.getCollapsedSemantics$ui_release();
    }

    private final String getProxyClassName(boolean isImage, SemanticsConfiguration config) {
        if (isImage) {
            return SentryReplayOptions.IMAGE_VIEW_CLASS_NAME;
        }
        if (config != null && (config.contains(SemanticsProperties.INSTANCE.getText()) || config.contains(SemanticsActions.INSTANCE.getSetText()) || config.contains(SemanticsProperties.INSTANCE.getEditableText()))) {
            return SentryReplayOptions.TEXT_VIEW_CLASS_NAME;
        }
        return AndroidComposeViewAccessibilityDelegateCompat.ClassName;
    }

    private final boolean shouldMask(SemanticsConfiguration semanticsConfiguration, boolean z, SentryOptions sentryOptions) {
        String str = semanticsConfiguration != null ? (String) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SentryReplayModifiers.INSTANCE.getSentryPrivacy()) : null;
        if (Intrinsics.areEqual(str, "unmask")) {
            return false;
        }
        if (Intrinsics.areEqual(str, "mask")) {
            return true;
        }
        String proxyClassName = getProxyClassName(z, semanticsConfiguration);
        if (sentryOptions.getSessionReplay().getUnmaskViewClasses().contains(proxyClassName)) {
            return false;
        }
        return sentryOptions.getSessionReplay().getMaskViewClasses().contains(proxyClassName);
    }

    private final ViewHierarchyNode fromComposeNode(LayoutNode node, ViewHierarchyNode parent, int distance, boolean isComposeRoot, SentryOptions options) {
        TextLayoutInput layoutInput;
        TextStyle style;
        TextLayoutInput layoutInput2;
        TextStyle style2;
        AccessibilityAction accessibilityAction;
        Function1 function1;
        if (!node.getIsPlaced() || !node.isAttached()) {
            return null;
        }
        if (isComposeRoot) {
            _rootCoordinates = new WeakReference<>(LayoutCoordinatesKt.findRootCoordinates(node.getCoordinates()));
        }
        LayoutCoordinates coordinates = node.getCoordinates();
        WeakReference<LayoutCoordinates> weakReference = _rootCoordinates;
        Rect boundsInWindow = NodesKt.boundsInWindow(coordinates, weakReference != null ? weakReference.get() : null);
        try {
            SemanticsConfiguration retrieveSemanticsConfiguration$sentry_android_replay_release = retrieveSemanticsConfiguration$sentry_android_replay_release(node);
            boolean z = !node.getOuterCoordinator$ui_release().isTransparent() && (retrieveSemanticsConfiguration$sentry_android_replay_release == null || !retrieveSemanticsConfiguration$sentry_android_replay_release.contains(SemanticsProperties.INSTANCE.getInvisibleToUser())) && boundsInWindow.height() > 0 && boundsInWindow.width() > 0;
            boolean z2 = (retrieveSemanticsConfiguration$sentry_android_replay_release != null && retrieveSemanticsConfiguration$sentry_android_replay_release.contains(SemanticsActions.INSTANCE.getSetText())) || (retrieveSemanticsConfiguration$sentry_android_replay_release != null && retrieveSemanticsConfiguration$sentry_android_replay_release.contains(SemanticsProperties.INSTANCE.getEditableText()));
            if ((retrieveSemanticsConfiguration$sentry_android_replay_release != null && retrieveSemanticsConfiguration$sentry_android_replay_release.contains(SemanticsProperties.INSTANCE.getText())) || z2) {
                boolean z3 = z && shouldMask(retrieveSemanticsConfiguration$sentry_android_replay_release, false, options);
                if (parent != null) {
                    parent.setImportantForCaptureToAncestors(true);
                }
                ArrayList arrayList = new ArrayList();
                if (retrieveSemanticsConfiguration$sentry_android_replay_release != null && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(retrieveSemanticsConfiguration$sentry_android_replay_release, SemanticsActions.INSTANCE.getGetTextLayoutResult())) != null && (function1 = (Function1) accessibilityAction.getAction()) != null) {
                }
                TextAttributes findTextAttributes = NodesKt.findTextAttributes(node);
                Color color = findTextAttributes.getColor();
                boolean hasFillModifier = findTextAttributes.getHasFillModifier();
                TextLayoutResult textLayoutResult = (TextLayoutResult) CollectionsKt.firstOrNull((List) arrayList);
                Color m1244boximpl = (textLayoutResult == null || (layoutInput2 = textLayoutResult.getLayoutInput()) == null || (style2 = layoutInput2.getStyle()) == null) ? null : Color.m1244boximpl(style2.getColor());
                if (m1244boximpl == null || m1244boximpl.getValue() != Color.INSTANCE.m1290getUnspecified0d7_KjU()) {
                    color = m1244boximpl;
                }
                TextUnit m3138boximpl = (textLayoutResult == null || (layoutInput = textLayoutResult.getLayoutInput()) == null || (style = layoutInput.getStyle()) == null) ? null : TextUnit.m3138boximpl(style.getFontSize());
                return new ViewHierarchyNode.TextViewHierarchyNode((textLayoutResult == null || z2 || (m3138boximpl != null ? TextUnit.m3145equalsimpl0(m3138boximpl.getPackedValue(), TextUnit.INSTANCE.m3159getUnspecifiedXSAIIZE()) : false)) ? null : new ComposeTextLayout(textLayoutResult, hasFillModifier), color != null ? Integer.valueOf(ViewsKt.toOpaque(ColorKt.m1309toArgb8_81llA(color.getValue()))) : null, 0, 0, boundsInWindow.left, boundsInWindow.top, node.getWidth(), node.getHeight(), parent != null ? parent.getElevation() : 0.0f, distance, parent, z3, true, z, boundsInWindow, 12, null);
            }
            Painter findPainter = NodesKt.findPainter(node);
            if (findPainter != null) {
                boolean z4 = z && shouldMask(retrieveSemanticsConfiguration$sentry_android_replay_release, true, options);
                if (parent != null) {
                    parent.setImportantForCaptureToAncestors(true);
                }
                return new ViewHierarchyNode.ImageViewHierarchyNode(boundsInWindow.left, boundsInWindow.top, node.getWidth(), node.getHeight(), parent != null ? parent.getElevation() : 0.0f, distance, parent, z4 && NodesKt.isMaskable(findPainter), true, z, boundsInWindow);
            }
            return new ViewHierarchyNode.GenericViewHierarchyNode(boundsInWindow.left, boundsInWindow.top, node.getWidth(), node.getHeight(), parent != null ? parent.getElevation() : 0.0f, distance, parent, z && shouldMask(retrieveSemanticsConfiguration$sentry_android_replay_release, false, options), false, z, boundsInWindow);
        } catch (Throwable th) {
            if (!semanticsRetrievalErrorLogged) {
                semanticsRetrievalErrorLogged = true;
                options.getLogger().log(SentryLevel.ERROR, th, "Error retrieving semantics information from Compose tree. Most likely you're using\nan unsupported version of androidx.compose.ui:ui. The supported\nversion range is 1.5.0 - 1.8.0.\nIf you're using a newer version, please open a github issue with the version\nyou're using, so we can add support for it.", new Object[0]);
            }
            return new ViewHierarchyNode.GenericViewHierarchyNode(boundsInWindow.left, boundsInWindow.top, node.getWidth(), node.getHeight(), parent != null ? parent.getElevation() : 0.0f, distance, parent, true, false, !node.getOuterCoordinator$ui_release().isTransparent() && boundsInWindow.height() > 0 && boundsInWindow.width() > 0, boundsInWindow);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean fromView(View view, ViewHierarchyNode parent, SentryOptions options) {
        LayoutNode root;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(options, "options");
        String name = view.getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        if (!StringsKt.contains$default((CharSequence) name, (CharSequence) "AndroidComposeView", false, 2, (Object) null) || parent == null) {
            return false;
        }
        try {
            Owner owner = view instanceof Owner ? (Owner) view : null;
            if (owner != null && (root = owner.getRoot()) != null) {
                traverse(root, parent, true, options);
                return true;
            }
            return false;
        } catch (Throwable th) {
            options.getLogger().log(SentryLevel.ERROR, th, "Error traversing Compose tree. Most likely you're using an unsupported version of\nandroidx.compose.ui:ui. The minimum supported version is 1.5.0. If it's a newer\nversion, please open a github issue with the version you're using, so we can add\nsupport for it.", new Object[0]);
            return false;
        }
    }

    private final void traverse(LayoutNode layoutNode, ViewHierarchyNode viewHierarchyNode, boolean z, SentryOptions sentryOptions) {
        List<LayoutNode> children$ui_release = layoutNode.getChildren$ui_release();
        if (children$ui_release.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(children$ui_release.size());
        int size = children$ui_release.size();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode2 = children$ui_release.get(i);
            ViewHierarchyNode fromComposeNode = fromComposeNode(layoutNode2, viewHierarchyNode, i, z, sentryOptions);
            if (fromComposeNode != null) {
                arrayList.add(fromComposeNode);
                traverse(layoutNode2, fromComposeNode, false, sentryOptions);
            }
        }
        viewHierarchyNode.setChildren(arrayList);
    }
}
