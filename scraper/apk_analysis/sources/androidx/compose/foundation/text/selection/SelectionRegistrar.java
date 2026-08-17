package androidx.compose.foundation.text.selection;

import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Map;
import kotlin.Metadata;

/* compiled from: SelectionRegistrar.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u0000 !2\u00020\u0001:\u0001!J\b\u0010\b\u001a\u00020\u0004H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H&J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H&J-\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J5\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\b\u0010\u0019\u001a\u00020\nH&J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H&J-\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u0015J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH&J\u0010\u0010 \u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001eH&R\u001e\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "", "subselections", "", "", "Landroidx/compose/foundation/text/selection/Selection;", "getSubselections", "()Ljava/util/Map;", "nextSelectableId", "notifyPositionChange", "", "selectableId", "notifySelectableChange", "notifySelectionUpdate", "layoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "endPosition", "Landroidx/compose/ui/geometry/Offset;", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "notifySelectionUpdate-d-4ec7I", "(Landroidx/compose/ui/layout/LayoutCoordinates;JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)V", "startPosition", "notifySelectionUpdate-DUneCvk", "(Landroidx/compose/ui/layout/LayoutCoordinates;JJLandroidx/compose/foundation/text/selection/SelectionAdjustment;)V", "notifySelectionUpdateEnd", "notifySelectionUpdateSelectAll", "notifySelectionUpdateStart", "notifySelectionUpdateStart-d-4ec7I", "subscribe", "Landroidx/compose/foundation/text/selection/Selectable;", "selectable", "unsubscribe", "Companion", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface SelectionRegistrar {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final long InvalidSelectableId = 0;

    Map<Long, Selection> getSubselections();

    long nextSelectableId();

    void notifyPositionChange(long selectableId);

    void notifySelectableChange(long selectableId);

    /* renamed from: notifySelectionUpdate-DUneCvk, reason: not valid java name */
    void mo578notifySelectionUpdateDUneCvk(LayoutCoordinates layoutCoordinates, long startPosition, long endPosition, SelectionAdjustment adjustment);

    /* renamed from: notifySelectionUpdate-d-4ec7I, reason: not valid java name */
    void mo579notifySelectionUpdated4ec7I(LayoutCoordinates layoutCoordinates, long endPosition, SelectionAdjustment adjustment);

    void notifySelectionUpdateEnd();

    void notifySelectionUpdateSelectAll(long selectableId);

    /* renamed from: notifySelectionUpdateStart-d-4ec7I, reason: not valid java name */
    void mo580notifySelectionUpdateStartd4ec7I(LayoutCoordinates layoutCoordinates, long startPosition, SelectionAdjustment adjustment);

    Selectable subscribe(Selectable selectable);

    void unsubscribe(Selectable selectable);

    /* compiled from: SelectionRegistrar.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionRegistrar$Companion;", "", "()V", "InvalidSelectableId", "", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final long InvalidSelectableId = 0;

        private Companion() {
        }
    }
}
