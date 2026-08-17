package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.foundation.text.selection.TextFieldPreparedSelection;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextPreparedSelectionState;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.apache.commons.lang3.StringUtils;

/* compiled from: TextFieldKeyInput.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J!\u0010#\u001a\u00020$2\u0017\u0010%\u001a\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020$0&¢\u0006\u0002\b(H\u0002J\u001b\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020+ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-J\u001f\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010*\u001a\u00020+H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101J\f\u00102\u001a\u00020$*\u000203H\u0002R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00064"}, d2 = {"Landroidx/compose/foundation/text/TextFieldKeyInput;", "", SentryThread.JsonKeys.STATE, "Landroidx/compose/foundation/text/TextFieldState;", "selectionManager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "editable", "", "singleLine", "preparedSelectionState", "Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "undoManager", "Landroidx/compose/foundation/text/UndoManager;", "keyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "(Landroidx/compose/foundation/text/TextFieldState;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/ui/text/input/TextFieldValue;ZZLandroidx/compose/foundation/text/selection/TextPreparedSelectionState;Landroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/UndoManager;Landroidx/compose/foundation/text/KeyMapping;)V", "getEditable", "()Z", "getOffsetMapping", "()Landroidx/compose/ui/text/input/OffsetMapping;", "getPreparedSelectionState", "()Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "getSelectionManager", "()Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "getSingleLine", "getState", "()Landroidx/compose/foundation/text/TextFieldState;", "getUndoManager", "()Landroidx/compose/foundation/text/UndoManager;", "getValue", "()Landroidx/compose/ui/text/input/TextFieldValue;", "commandExecutionContext", "", "block", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/selection/TextFieldPreparedSelection;", "Lkotlin/ExtensionFunctionType;", "process", "event", "Landroidx/compose/ui/input/key/KeyEvent;", "process-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "typedCommand", "Landroidx/compose/ui/text/input/CommitTextCommand;", "typedCommand-ZmokQxo", "(Landroid/view/KeyEvent;)Landroidx/compose/ui/text/input/CommitTextCommand;", "apply", "Landroidx/compose/ui/text/input/EditCommand;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextFieldKeyInput {
    private final boolean editable;
    private final KeyMapping keyMapping;
    private final OffsetMapping offsetMapping;
    private final TextPreparedSelectionState preparedSelectionState;
    private final TextFieldSelectionManager selectionManager;
    private final boolean singleLine;
    private final TextFieldState state;
    private final UndoManager undoManager;
    private final TextFieldValue value;

    public TextFieldKeyInput(TextFieldState state, TextFieldSelectionManager selectionManager, TextFieldValue value, boolean z, boolean z2, TextPreparedSelectionState preparedSelectionState, OffsetMapping offsetMapping, UndoManager undoManager, KeyMapping keyMapping) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(selectionManager, "selectionManager");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(preparedSelectionState, "preparedSelectionState");
        Intrinsics.checkNotNullParameter(offsetMapping, "offsetMapping");
        Intrinsics.checkNotNullParameter(keyMapping, "keyMapping");
        this.state = state;
        this.selectionManager = selectionManager;
        this.value = value;
        this.editable = z;
        this.singleLine = z2;
        this.preparedSelectionState = preparedSelectionState;
        this.offsetMapping = offsetMapping;
        this.undoManager = undoManager;
        this.keyMapping = keyMapping;
    }

    public final TextFieldState getState() {
        return this.state;
    }

    public final TextFieldSelectionManager getSelectionManager() {
        return this.selectionManager;
    }

    public /* synthetic */ TextFieldKeyInput(TextFieldState textFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, boolean z, boolean z2, TextPreparedSelectionState textPreparedSelectionState, OffsetMapping offsetMapping, UndoManager undoManager, KeyMapping keyMapping, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(textFieldState, textFieldSelectionManager, (i & 4) != 0 ? new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null) : textFieldValue, (i & 8) != 0 ? true : z, (i & 16) != 0 ? false : z2, textPreparedSelectionState, (i & 64) != 0 ? OffsetMapping.INSTANCE.getIdentity() : offsetMapping, (i & 128) != 0 ? null : undoManager, (i & 256) != 0 ? KeyMapping_androidKt.getPlatformDefaultKeyMapping() : keyMapping);
    }

    public final TextFieldValue getValue() {
        return this.value;
    }

    public final boolean getEditable() {
        return this.editable;
    }

    public final boolean getSingleLine() {
        return this.singleLine;
    }

    public final TextPreparedSelectionState getPreparedSelectionState() {
        return this.preparedSelectionState;
    }

    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public final UndoManager getUndoManager() {
        return this.undoManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void apply(EditCommand editCommand) {
        this.state.getOnValueChange().invoke(this.state.getProcessor().apply(CollectionsKt.listOf((Object[]) new EditCommand[]{new FinishComposingTextCommand(), editCommand})));
    }

    /* renamed from: typedCommand-ZmokQxo, reason: not valid java name */
    private final CommitTextCommand m511typedCommandZmokQxo(KeyEvent event) {
        if (TextFieldKeyInput_androidKt.m514isTypedEventZmokQxo(event)) {
            String sb = StringHelpers_jvmKt.appendCodePointX(new StringBuilder(), KeyEvent_androidKt.m2372getUtf16CodePointZmokQxo(event)).toString();
            Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().appendCodePointX(event.utf16CodePoint)\n                .toString()");
            return new CommitTextCommand(sb, 1);
        }
        return null;
    }

    /* renamed from: process-ZmokQxo, reason: not valid java name */
    public final boolean m512processZmokQxo(KeyEvent event) {
        final KeyCommand mo453mapZmokQxo;
        Intrinsics.checkNotNullParameter(event, "event");
        CommitTextCommand m511typedCommandZmokQxo = m511typedCommandZmokQxo(event);
        if (m511typedCommandZmokQxo != null) {
            if (!getEditable()) {
                return false;
            }
            apply(m511typedCommandZmokQxo);
            getPreparedSelectionState().resetCachedX();
            return true;
        }
        if (!KeyEventType.m2363equalsimpl0(KeyEvent_androidKt.m2371getTypeZmokQxo(event), KeyEventType.INSTANCE.m2367getKeyDownCS__XNY()) || (mo453mapZmokQxo = this.keyMapping.mo453mapZmokQxo(event)) == null || (mo453mapZmokQxo.getEditsText() && !this.editable)) {
            return false;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        commandExecutionContext(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2

            /* compiled from: TextFieldKeyInput.kt */
            @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[KeyCommand.valuesCustom().length];
                    iArr[KeyCommand.COPY.ordinal()] = 1;
                    iArr[KeyCommand.PASTE.ordinal()] = 2;
                    iArr[KeyCommand.CUT.ordinal()] = 3;
                    iArr[KeyCommand.LEFT_CHAR.ordinal()] = 4;
                    iArr[KeyCommand.RIGHT_CHAR.ordinal()] = 5;
                    iArr[KeyCommand.LEFT_WORD.ordinal()] = 6;
                    iArr[KeyCommand.RIGHT_WORD.ordinal()] = 7;
                    iArr[KeyCommand.PREV_PARAGRAPH.ordinal()] = 8;
                    iArr[KeyCommand.NEXT_PARAGRAPH.ordinal()] = 9;
                    iArr[KeyCommand.UP.ordinal()] = 10;
                    iArr[KeyCommand.DOWN.ordinal()] = 11;
                    iArr[KeyCommand.PAGE_UP.ordinal()] = 12;
                    iArr[KeyCommand.PAGE_DOWN.ordinal()] = 13;
                    iArr[KeyCommand.LINE_START.ordinal()] = 14;
                    iArr[KeyCommand.LINE_END.ordinal()] = 15;
                    iArr[KeyCommand.LINE_LEFT.ordinal()] = 16;
                    iArr[KeyCommand.LINE_RIGHT.ordinal()] = 17;
                    iArr[KeyCommand.HOME.ordinal()] = 18;
                    iArr[KeyCommand.END.ordinal()] = 19;
                    iArr[KeyCommand.DELETE_PREV_CHAR.ordinal()] = 20;
                    iArr[KeyCommand.DELETE_NEXT_CHAR.ordinal()] = 21;
                    iArr[KeyCommand.DELETE_PREV_WORD.ordinal()] = 22;
                    iArr[KeyCommand.DELETE_NEXT_WORD.ordinal()] = 23;
                    iArr[KeyCommand.DELETE_FROM_LINE_START.ordinal()] = 24;
                    iArr[KeyCommand.DELETE_TO_LINE_END.ordinal()] = 25;
                    iArr[KeyCommand.NEW_LINE.ordinal()] = 26;
                    iArr[KeyCommand.TAB.ordinal()] = 27;
                    iArr[KeyCommand.SELECT_ALL.ordinal()] = 28;
                    iArr[KeyCommand.SELECT_LEFT_CHAR.ordinal()] = 29;
                    iArr[KeyCommand.SELECT_RIGHT_CHAR.ordinal()] = 30;
                    iArr[KeyCommand.SELECT_LEFT_WORD.ordinal()] = 31;
                    iArr[KeyCommand.SELECT_RIGHT_WORD.ordinal()] = 32;
                    iArr[KeyCommand.SELECT_PREV_PARAGRAPH.ordinal()] = 33;
                    iArr[KeyCommand.SELECT_NEXT_PARAGRAPH.ordinal()] = 34;
                    iArr[KeyCommand.SELECT_LINE_START.ordinal()] = 35;
                    iArr[KeyCommand.SELECT_LINE_END.ordinal()] = 36;
                    iArr[KeyCommand.SELECT_LINE_LEFT.ordinal()] = 37;
                    iArr[KeyCommand.SELECT_LINE_RIGHT.ordinal()] = 38;
                    iArr[KeyCommand.SELECT_UP.ordinal()] = 39;
                    iArr[KeyCommand.SELECT_DOWN.ordinal()] = 40;
                    iArr[KeyCommand.SELECT_PAGE_UP.ordinal()] = 41;
                    iArr[KeyCommand.SELECT_PAGE_DOWN.ordinal()] = 42;
                    iArr[KeyCommand.SELECT_HOME.ordinal()] = 43;
                    iArr[KeyCommand.SELECT_END.ordinal()] = 44;
                    iArr[KeyCommand.DESELECT.ordinal()] = 45;
                    iArr[KeyCommand.UNDO.ordinal()] = 46;
                    iArr[KeyCommand.REDO.ordinal()] = 47;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                invoke2(textFieldPreparedSelection);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TextFieldPreparedSelection commandExecutionContext) {
                TextFieldValue undo;
                TextFieldValue redo;
                Intrinsics.checkNotNullParameter(commandExecutionContext, "$this$commandExecutionContext");
                switch (WhenMappings.$EnumSwitchMapping$0[KeyCommand.this.ordinal()]) {
                    case 1:
                        this.getSelectionManager().copy$foundation_release(false);
                        break;
                    case 2:
                        this.getSelectionManager().paste$foundation_release();
                        break;
                    case 3:
                        this.getSelectionManager().cut$foundation_release();
                        break;
                    case 4:
                        commandExecutionContext.collapseLeftOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                                invoke2(textFieldPreparedSelection);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldPreparedSelection collapseLeftOr) {
                                Intrinsics.checkNotNullParameter(collapseLeftOr, "$this$collapseLeftOr");
                                collapseLeftOr.moveCursorLeft();
                            }
                        });
                        break;
                    case 5:
                        commandExecutionContext.collapseRightOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.2
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                                invoke2(textFieldPreparedSelection);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldPreparedSelection collapseRightOr) {
                                Intrinsics.checkNotNullParameter(collapseRightOr, "$this$collapseRightOr");
                                collapseRightOr.moveCursorRight();
                            }
                        });
                        break;
                    case 6:
                        commandExecutionContext.moveCursorLeftByWord();
                        break;
                    case 7:
                        commandExecutionContext.moveCursorRightByWord();
                        break;
                    case 8:
                        commandExecutionContext.moveCursorPrevByParagraph();
                        break;
                    case 9:
                        commandExecutionContext.moveCursorNextByParagraph();
                        break;
                    case 10:
                        commandExecutionContext.moveCursorUpByLine();
                        break;
                    case 11:
                        commandExecutionContext.moveCursorDownByLine();
                        break;
                    case 12:
                        commandExecutionContext.moveCursorUpByPage();
                        break;
                    case 13:
                        commandExecutionContext.moveCursorDownByPage();
                        break;
                    case 14:
                        commandExecutionContext.moveCursorToLineStart();
                        break;
                    case 15:
                        commandExecutionContext.moveCursorToLineEnd();
                        break;
                    case 16:
                        commandExecutionContext.moveCursorToLineLeftSide();
                        break;
                    case 17:
                        commandExecutionContext.moveCursorToLineRightSide();
                        break;
                    case 18:
                        commandExecutionContext.moveCursorToHome();
                        break;
                    case 19:
                        commandExecutionContext.moveCursorToEnd();
                        break;
                    case 20:
                        commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.3
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                                invoke2(textFieldPreparedSelection);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldPreparedSelection deleteIfSelectedOr) {
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                deleteIfSelectedOr.moveCursorPrev().selectMovement().deleteSelected();
                            }
                        });
                        break;
                    case 21:
                        commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.4
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                                invoke2(textFieldPreparedSelection);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldPreparedSelection deleteIfSelectedOr) {
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                deleteIfSelectedOr.moveCursorNext().selectMovement().deleteSelected();
                            }
                        });
                        break;
                    case 22:
                        commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.5
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                                invoke2(textFieldPreparedSelection);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldPreparedSelection deleteIfSelectedOr) {
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                deleteIfSelectedOr.moveCursorPrevByWord().selectMovement().deleteSelected();
                            }
                        });
                        break;
                    case 23:
                        commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.6
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                                invoke2(textFieldPreparedSelection);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldPreparedSelection deleteIfSelectedOr) {
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                deleteIfSelectedOr.moveCursorNextByWord().selectMovement().deleteSelected();
                            }
                        });
                        break;
                    case 24:
                        commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.7
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                                invoke2(textFieldPreparedSelection);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldPreparedSelection deleteIfSelectedOr) {
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                deleteIfSelectedOr.moveCursorToLineStart().selectMovement().deleteSelected();
                            }
                        });
                        break;
                    case 25:
                        commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.8
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                                invoke2(textFieldPreparedSelection);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldPreparedSelection deleteIfSelectedOr) {
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                deleteIfSelectedOr.moveCursorToLineEnd().selectMovement().deleteSelected();
                            }
                        });
                        break;
                    case 26:
                        if (!this.getSingleLine()) {
                            this.apply(new CommitTextCommand(StringUtils.LF, 1));
                            break;
                        } else {
                            booleanRef.element = false;
                            break;
                        }
                    case 27:
                        if (!this.getSingleLine()) {
                            this.apply(new CommitTextCommand("\t", 1));
                            break;
                        } else {
                            booleanRef.element = false;
                            break;
                        }
                    case 28:
                        commandExecutionContext.selectAll();
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                        commandExecutionContext.moveCursorLeft().selectMovement();
                        break;
                    case 30:
                        commandExecutionContext.moveCursorRight().selectMovement();
                        break;
                    case 31:
                        commandExecutionContext.moveCursorLeftByWord().selectMovement();
                        break;
                    case 32:
                        commandExecutionContext.moveCursorRightByWord().selectMovement();
                        break;
                    case 33:
                        commandExecutionContext.moveCursorPrevByParagraph().selectMovement();
                        break;
                    case 34:
                        commandExecutionContext.moveCursorNextByParagraph().selectMovement();
                        break;
                    case 35:
                        commandExecutionContext.moveCursorToLineStart().selectMovement();
                        break;
                    case 36:
                        commandExecutionContext.moveCursorToLineEnd().selectMovement();
                        break;
                    case 37:
                        commandExecutionContext.moveCursorToLineLeftSide().selectMovement();
                        break;
                    case 38:
                        commandExecutionContext.moveCursorToLineRightSide().selectMovement();
                        break;
                    case 39:
                        commandExecutionContext.moveCursorUpByLine().selectMovement();
                        break;
                    case 40:
                        commandExecutionContext.moveCursorDownByLine().selectMovement();
                        break;
                    case 41:
                        commandExecutionContext.moveCursorUpByPage().selectMovement();
                        break;
                    case 42:
                        commandExecutionContext.moveCursorDownByPage().selectMovement();
                        break;
                    case 43:
                        commandExecutionContext.moveCursorToHome().selectMovement();
                        break;
                    case 44:
                        commandExecutionContext.moveCursorToEnd().selectMovement();
                        break;
                    case 45:
                        commandExecutionContext.deselect();
                        break;
                    case 46:
                        UndoManager undoManager = this.getUndoManager();
                        if (undoManager != null) {
                            undoManager.makeSnapshot(commandExecutionContext.getValue());
                        }
                        UndoManager undoManager2 = this.getUndoManager();
                        if (undoManager2 != null && (undo = undoManager2.undo()) != null) {
                            this.getState().getOnValueChange().invoke(undo);
                            break;
                        }
                        break;
                    case 47:
                        UndoManager undoManager3 = this.getUndoManager();
                        if (undoManager3 != null && (redo = undoManager3.redo()) != null) {
                            this.getState().getOnValueChange().invoke(redo);
                            break;
                        }
                        break;
                }
            }
        });
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
        return booleanRef.element;
    }

    private final void commandExecutionContext(Function1<? super TextFieldPreparedSelection, Unit> block) {
        TextFieldPreparedSelection textFieldPreparedSelection = new TextFieldPreparedSelection(this.value, this.offsetMapping, this.state.getLayoutResult(), this.preparedSelectionState);
        block.invoke(textFieldPreparedSelection);
        if (TextRange.m2759equalsimpl0(textFieldPreparedSelection.getSelection(), this.value.getSelection()) && Intrinsics.areEqual(textFieldPreparedSelection.getAnnotatedString(), this.value.getAnnotatedString())) {
            return;
        }
        this.state.getOnValueChange().invoke(textFieldPreparedSelection.getValue());
    }
}
