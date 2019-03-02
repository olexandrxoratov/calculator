package com.yashchuk;

import java.util.EnumSet;

public enum NumeralsHolder {

    ARABIAN {
        @Override
        public double getAbsolute(String representation) {
            return Math.abs(Double.parseDouble(representation));
        }
    },

    ROME {
        @Override
        public double getAbsolute(String representation) {
            int result = 0;
            String digit = representation;

            while (digit.length() > 0) {
                for (RomanNumeral romanNumeral : romanNumerals) {
                    if (digit.toUpperCase().startsWith(romanNumeral.name())) {
                        if (result > 0 && result < romanNumeral.getValue()) {
                            result = romanNumeral.getValue() - result;
                        } else {
                            result += romanNumeral.getValue();
                        }
                        digit = digit.substring(romanNumeral.name().length());
                    }
                }
            }
            return Math.abs(result);
        }
    };

    private static EnumSet<RomanNumeral> romanNumerals = EnumSet.allOf(RomanNumeral.class);

    public abstract double getAbsolute(String representation);

    public static NumeralsHolder determineDigitType(String digit) {
        if (isArabic(digit)) {
            return ARABIAN;
        }

        if (isRomanian(digit)) {
            return ROME;
        }

        throw new IllegalArgumentException("Not a number!");
    }

    private static boolean isRomanian(String digit) {
        romanNumerals = EnumSet.allOf(RomanNumeral.class);
        for (RomanNumeral romanNumeral : romanNumerals) {
            if (digit.toUpperCase().startsWith(romanNumeral.name())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isArabic(String digit) {
        try {
            Double.parseDouble(digit);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
