package Emersim.Gui.SizeConstants;

import java.awt.*;

public class DrawBig implements DrawConstrains {
    private static final double START_GAP = 10.0;
    private static final double ELEM_HEIGHT = 50.0;
    private static final double ELEM_WIDTH = 30.0;
    private static final double ELEMS_GAP = 7.0;
    private static final double PROC_RAD = 40.0;
    private static final double STAT_RAD = 25.0;
    private static final float STROKE_SIZE = 4.0f;

    private static final Font F = new Font("Monospaced", Font.BOLD, 18);
    private static final Font FN = new Font("Verdana", Font.BOLD, 18);
    private static final Font FS = new Font("Verdana", Font.BOLD,
            16);

    private static final Stroke S = new BasicStroke(STROKE_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private static final Stroke BS = new BasicStroke(STROKE_SIZE + STROKE_SIZE / 2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getElementHeight()
     */
    public double getElementHeight() {
        return ELEM_HEIGHT;
    }

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getElementsGap()
     */
    public double getElementsGap() {
        return ELEMS_GAP;
    }

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getElementWidth()
     */
    public double getElementWidth() {
        return ELEM_WIDTH;
    }

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getFont()
     */
    public Font getFont() {
        return F;
    }

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getProcessorRadius()
     */
    public double getProcessorRadius() {
        return PROC_RAD;
    }

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getStartingGap()
     */
    public double getStartingGap() {
        return START_GAP;
    }

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getStatusRadius()
     */
    public double getStatusRadius() {
        return STAT_RAD;
    }

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getDrawStroke()
     */
    public Stroke getDrawStroke() {
        return S;
    }

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getBoldStroke()
     */
    public Stroke getBoldStroke() {
        return BS;
    }

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getNormalGUIFont()
     */
    public Font getNormalGUIFont() {
        return FN;
    }

    /* (non-Javadoc)
     * @see Graphics.SizeConstants.DrawConstrains#getSmallGUIFont()
     */
    public Font getSmallGUIFont() {
        return FS;
    }

}
