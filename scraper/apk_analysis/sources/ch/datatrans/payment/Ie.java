package ch.datatrans.payment;

/* loaded from: classes3.dex */
public final class Ie extends RuntimeException {
    private static final long serialVersionUID = 20110121;

    public Ie() {
        super("Empty non-optional XML node.");
    }

    public Ie(String str, Exception exc) {
        super(str, exc);
    }
}
