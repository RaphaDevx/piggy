package io.card.payment;

/* loaded from: classes2.dex */
class DetectionInfo {
    public boolean bottomEdge;
    public boolean complete = false;
    public CreditCard detectedCard;
    public int expiry_month;
    public int expiry_year;
    public float focusScore;
    public boolean leftEdge;
    public int[] prediction;
    public boolean rightEdge;
    public boolean topEdge;

    public DetectionInfo() {
        int[] iArr = new int[16];
        this.prediction = iArr;
        iArr[0] = -1;
        iArr[15] = -1;
        this.detectedCard = new CreditCard();
    }

    boolean sameEdgesAs(DetectionInfo detectionInfo) {
        return detectionInfo.topEdge == this.topEdge && detectionInfo.bottomEdge == this.bottomEdge && detectionInfo.leftEdge == this.leftEdge && detectionInfo.rightEdge == this.rightEdge;
    }

    boolean detected() {
        return this.topEdge && this.bottomEdge && this.rightEdge && this.leftEdge;
    }

    boolean predicted() {
        return this.complete;
    }

    CreditCard creditCard() {
        int i;
        String str = new String();
        for (int i2 = 0; i2 < 16 && (i = this.prediction[i2]) >= 0 && i < 10; i2++) {
            str = str + String.valueOf(this.prediction[i2]);
        }
        this.detectedCard.cardNumber = str;
        this.detectedCard.expiryMonth = this.expiry_month;
        this.detectedCard.expiryYear = this.expiry_year;
        return this.detectedCard;
    }

    int numVisibleEdges() {
        return (this.topEdge ? 1 : 0) + (this.bottomEdge ? 1 : 0) + (this.leftEdge ? 1 : 0) + (this.rightEdge ? 1 : 0);
    }
}
