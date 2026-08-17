package uk.org.okapibarcode.gui;

/* loaded from: classes4.dex */
public class SaveImage {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0072, code lost:
    
        if (r9.equals("eps") == false) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void save(java.io.File r10, javax.swing.JPanel r11) throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r9 = r10.getName()
            r0 = 46
            int r9 = r9.lastIndexOf(r0)
            r0 = 1
            java.lang.String r1 = "png"
            if (r9 <= 0) goto L1d
            java.lang.String r2 = r10.getName()
            int r9 = r9 + r0
            java.lang.String r9 = r2.substring(r9)
            java.lang.String r9 = r9.toLowerCase()
            goto L1e
        L1d:
            r9 = r1
        L1e:
            uk.org.okapibarcode.graphics.Color r6 = new uk.org.okapibarcode.graphics.Color
            java.awt.Color r2 = uk.org.okapibarcode.gui.OkapiUI.paperColour
            int r2 = r2.getRGB()
            r6.<init>(r2)
            uk.org.okapibarcode.graphics.Color r7 = new uk.org.okapibarcode.graphics.Color
            java.awt.Color r2 = uk.org.okapibarcode.gui.OkapiUI.inkColour
            int r2 = r2.getRGB()
            r7.<init>(r2)
            r9.hashCode()
            int r2 = r9.hashCode()
            r3 = 2
            r4 = -1
            switch(r2) {
                case 97669: goto L75;
                case 100648: goto L6c;
                case 102340: goto L61;
                case 105441: goto L56;
                case 111145: goto L4d;
                case 114276: goto L42;
                default: goto L40;
            }
        L40:
            r0 = r4
            goto L7f
        L42:
            java.lang.String r0 = "svg"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L4b
            goto L40
        L4b:
            r0 = 5
            goto L7f
        L4d:
            boolean r0 = r9.equals(r1)
            if (r0 != 0) goto L54
            goto L40
        L54:
            r0 = 4
            goto L7f
        L56:
            java.lang.String r0 = "jpg"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L5f
            goto L40
        L5f:
            r0 = 3
            goto L7f
        L61:
            java.lang.String r0 = "gif"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L6a
            goto L40
        L6a:
            r0 = r3
            goto L7f
        L6c:
            java.lang.String r1 = "eps"
            boolean r1 = r9.equals(r1)
            if (r1 != 0) goto L7f
            goto L40
        L75:
            java.lang.String r0 = "bmp"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L7e
            goto L40
        L7e:
            r0 = 0
        L7f:
            switch(r0) {
                case 0: goto Lb3;
                case 1: goto L9f;
                case 2: goto Lb3;
                case 3: goto Lb3;
                case 4: goto Lb3;
                case 5: goto L8a;
                default: goto L82;
            }
        L82:
            java.io.PrintStream r9 = java.lang.System.out
            java.lang.String r10 = "Unsupported output format"
            r9.println(r10)
            goto Ld4
        L8a:
            uk.org.okapibarcode.output.SvgRenderer r9 = new uk.org.okapibarcode.output.SvgRenderer
            java.io.FileOutputStream r3 = new java.io.FileOutputStream
            r3.<init>(r10)
            int r10 = uk.org.okapibarcode.gui.OkapiUI.factor
            double r4 = (double) r10
            r8 = 1
            r2 = r9
            r2.<init>(r3, r4, r6, r7, r8)
            uk.org.okapibarcode.backend.Symbol r10 = uk.org.okapibarcode.gui.OkapiUI.symbol
            r9.render(r10)
            goto Ld4
        L9f:
            uk.org.okapibarcode.output.PostScriptRenderer r9 = new uk.org.okapibarcode.output.PostScriptRenderer
            java.io.FileOutputStream r3 = new java.io.FileOutputStream
            r3.<init>(r10)
            int r10 = uk.org.okapibarcode.gui.OkapiUI.factor
            double r4 = (double) r10
            r2 = r9
            r2.<init>(r3, r4, r6, r7)
            uk.org.okapibarcode.backend.Symbol r10 = uk.org.okapibarcode.gui.OkapiUI.symbol
            r9.render(r10)
            goto Ld4
        Lb3:
            uk.org.okapibarcode.backend.Symbol r0 = uk.org.okapibarcode.gui.OkapiUI.symbol
            int r0 = r0.getWidth()
            int r1 = uk.org.okapibarcode.gui.OkapiUI.factor
            int r0 = r0 * r1
            uk.org.okapibarcode.backend.Symbol r1 = uk.org.okapibarcode.gui.OkapiUI.symbol
            int r1 = r1.getHeight()
            int r2 = uk.org.okapibarcode.gui.OkapiUI.factor
            int r1 = r1 * r2
            java.awt.image.BufferedImage r2 = new java.awt.image.BufferedImage
            r2.<init>(r0, r1, r3)
            java.awt.Graphics r0 = r2.getGraphics()
            r11.paint(r0)
            javax.imageio.ImageIO.write(r2, r9, r10)
        Ld4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.gui.SaveImage.save(java.io.File, javax.swing.JPanel):void");
    }
}
