package androidx.compose.ui.input.pointer.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VelocityTracker.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u001a,\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\f\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"AssumePointerMoveStoppedMilliseconds", "", "DefaultWeight", "", "HistorySize", "HorizonMilliseconds", "MinSampleSize", "polyFitLeastSquares", "Landroidx/compose/ui/input/pointer/util/PolynomialFit;", "x", "", "y", "degree", "ui_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class VelocityTrackerKt {
    private static final int AssumePointerMoveStoppedMilliseconds = 40;
    private static final float DefaultWeight = 1.0f;
    private static final int HistorySize = 20;
    private static final int HorizonMilliseconds = 100;
    private static final int MinSampleSize = 3;

    public static final PolynomialFit polyFitLeastSquares(List<Float> x, List<Float> y, int i) {
        ArrayList arrayList;
        float f;
        Intrinsics.checkNotNullParameter(x, "x");
        Intrinsics.checkNotNullParameter(y, "y");
        if (i < 1) {
            throw new IllegalArgumentException("The degree must be at positive integer");
        }
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("x and y must be the same length");
        }
        if (x.isEmpty()) {
            throw new IllegalArgumentException("At least one point must be provided");
        }
        int size = i >= x.size() ? x.size() - 1 : i;
        int i2 = i + 1;
        ArrayList arrayList2 = new ArrayList(i2);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            arrayList2.add(Float.valueOf(0.0f));
        }
        ArrayList arrayList3 = arrayList2;
        int size2 = x.size();
        int i5 = size + 1;
        Matrix matrix = new Matrix(i5, size2);
        if (size2 > 0) {
            int i6 = 0;
            while (true) {
                int i7 = i6 + 1;
                matrix.set(0, i6, 1.0f);
                if (1 < i5) {
                    int i8 = 1;
                    while (true) {
                        int i9 = i8 + 1;
                        matrix.set(i8, i6, matrix.get(i8 - 1, i6) * x.get(i6).floatValue());
                        if (i9 >= i5) {
                            break;
                        }
                        i8 = i9;
                    }
                }
                if (i7 >= size2) {
                    break;
                }
                i6 = i7;
            }
        }
        Matrix matrix2 = new Matrix(i5, size2);
        Matrix matrix3 = new Matrix(i5, i5);
        if (i5 > 0) {
            int i10 = 0;
            while (true) {
                int i11 = i10 + 1;
                if (size2 > 0) {
                    int i12 = i3;
                    while (true) {
                        int i13 = i12 + 1;
                        matrix2.set(i10, i12, matrix.get(i10, i12));
                        if (i13 >= size2) {
                            break;
                        }
                        i12 = i13;
                    }
                }
                if (i10 > 0) {
                    int i14 = i3;
                    while (true) {
                        int i15 = i14 + 1;
                        float times = matrix2.getRow(i10).times(matrix2.getRow(i14));
                        if (size2 > 0) {
                            int i16 = 0;
                            while (true) {
                                int i17 = i16 + 1;
                                int i18 = i14;
                                matrix2.set(i10, i16, matrix2.get(i10, i16) - (matrix2.get(i14, i16) * times));
                                if (i17 >= size2) {
                                    break;
                                }
                                i16 = i17;
                                i14 = i18;
                            }
                        }
                        if (i15 >= i10) {
                            break;
                        }
                        i14 = i15;
                    }
                }
                float norm = matrix2.getRow(i10).norm();
                arrayList = arrayList3;
                if (norm < 1.0E-6d) {
                    throw new IllegalArgumentException("Vectors are linearly dependent or zero so no solution. TODO(shepshapard), actually determine what this means");
                }
                float f2 = 1.0f / norm;
                if (size2 > 0) {
                    int i19 = 0;
                    while (true) {
                        int i20 = i19 + 1;
                        matrix2.set(i10, i19, matrix2.get(i10, i19) * f2);
                        if (i20 >= size2) {
                            break;
                        }
                        i19 = i20;
                    }
                }
                if (i5 > 0) {
                    int i21 = 0;
                    while (true) {
                        int i22 = i21 + 1;
                        matrix3.set(i10, i21, i21 < i10 ? 0.0f : matrix2.getRow(i10).times(matrix.getRow(i21)));
                        if (i22 >= i5) {
                            break;
                        }
                        i21 = i22;
                    }
                }
                if (i11 >= i5) {
                    break;
                }
                arrayList3 = arrayList;
                i10 = i11;
                i3 = 0;
            }
        } else {
            arrayList = arrayList3;
        }
        Vector vector = new Vector(size2);
        if (size2 > 0) {
            int i23 = 0;
            while (true) {
                int i24 = i23 + 1;
                vector.set(i23, y.get(i23).floatValue() * 1.0f);
                if (i24 >= size2) {
                    break;
                }
                i23 = i24;
            }
        }
        if (size >= 0) {
            int i25 = size;
            while (true) {
                int i26 = i25 - 1;
                arrayList.set(i25, Float.valueOf(matrix2.getRow(i25).times(vector)));
                int i27 = i25 + 1;
                if (i27 <= size) {
                    int i28 = size;
                    while (true) {
                        int i29 = i28 - 1;
                        arrayList.set(i25, Float.valueOf(((Number) arrayList.get(i25)).floatValue() - (matrix3.get(i25, i28) * ((Number) arrayList.get(i28)).floatValue())));
                        if (i28 == i27) {
                            break;
                        }
                        i28 = i29;
                    }
                }
                arrayList.set(i25, Float.valueOf(((Number) arrayList.get(i25)).floatValue() / matrix3.get(i25, i25)));
                if (i26 < 0) {
                    break;
                }
                i25 = i26;
            }
        }
        if (size2 > 0) {
            int i30 = 0;
            f = 0.0f;
            while (true) {
                int i31 = i30 + 1;
                f += y.get(i30).floatValue();
                if (i31 >= size2) {
                    break;
                }
                i30 = i31;
            }
        } else {
            f = 0.0f;
        }
        float f3 = f / size2;
        float f4 = 0.0f;
        float f5 = 0.0f;
        if (size2 > 0) {
            int i32 = 0;
            while (true) {
                int i33 = i32 + 1;
                float floatValue = y.get(i32).floatValue() - ((Number) arrayList.get(0)).floatValue();
                if (1 < i5) {
                    int i34 = 1;
                    float f6 = 1.0f;
                    while (true) {
                        int i35 = i34 + 1;
                        f6 *= x.get(i32).floatValue();
                        floatValue -= ((Number) arrayList.get(i34)).floatValue() * f6;
                        if (i35 >= i5) {
                            break;
                        }
                        i34 = i35;
                    }
                }
                f4 += floatValue * 1.0f * floatValue;
                float floatValue2 = y.get(i32).floatValue() - f3;
                f5 += floatValue2 * 1.0f * floatValue2;
                if (i33 >= size2) {
                    break;
                }
                i32 = i33;
            }
        }
        return new PolynomialFit(arrayList, f5 <= 1.0E-6f ? 1.0f : 1.0f - (f4 / f5));
    }
}
