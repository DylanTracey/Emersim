package Emersim.Gui.SizeConstants;

import java.awt.*;

public interface DrawConstrains {

    /**
     * Returns the gap between the edges of the drawing from drawing itself
     */
    double getStartingGap();

    /**
     * Returns the gap between two elements of the tail and between
     * the first element of the queue and the processor
     */
    double getElementsGap();

    /**
     * Returns the type of items
     */
    Stroke getDrawStroke();

    Stroke getBoldStroke();

    //font

    /**
     * returns the font for the area of panel
     */
    Font getFont();

    /**
     * returns the font for the GUI
     */
    Font getNormalGUIFont();

    /**
     * returns the small font for the GUI
     */
    Font getSmallGUIFont();

    //queue

    /**
     * Returns the width of an element of the queue
     */
    double getElementWidth();

    /**
     * Returns the height of an element of the queue
     *
     * @return
     */
    double getElementHeight();

    //processor

    /**
     * Returns the radius of the processor
     */
    double getProcessorRadius();

    //status

    /**
     * * Returns the radius of the status
     */
    double getStatusRadius();
}
