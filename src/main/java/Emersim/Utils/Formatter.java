package Emersim.Utils;

import java.text.NumberFormat;

public class Formatter {

    public static String formatNumber(double number, int decimal) {
        if (number >= 0) {
            NumberFormat n = NumberFormat.getNumberInstance();
            if (decimal > 0) {
                n.setMinimumFractionDigits(decimal);
                n.setMaximumFractionDigits(decimal);
            }
            return n.format(number);
        } else {
            return "";
        }
    }
}
