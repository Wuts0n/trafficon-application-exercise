package com.example.parking.util;

import com.example.parking.model.ParsedFreiePlaetze;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Long> stringToLongList(@NotNull String idsString) throws NumberFormatException {
        return stringToLongList(idsString, ",");
    }

    /**
     * Parses a String, possibly separated by 'delimiter', to a List of Longs.
     *
     * @param idsString A String of numbers separated by 'delimiter'. E.g. "23,656,234,87897" or "42"
     * @param delimiter A Regex (or a simple String) that separates numbers in idsString.
     * @return A List of Longs
     */
    public static List<Long> stringToLongList(@NotNull String idsString, @NotNull String delimiter) throws NumberFormatException {
        String[] idsStringList = idsString.split(delimiter);
        List<Long> ids = new ArrayList<>();
        for (String idString : idsStringList) {
            ids.add(Long.parseLong(idString));
        }
        return ids;
    }

    /**
     * Converts a string in the form of "XX (YY%)" to two independent numbers XX and YY
     *
     * @param freiePlaetze string in the form of "XX (YY%)"
     * @return an object with the first absolute value and the second percentage value
     * @throws NumberFormatException if any of XX or YY are not numbers
     */
    public static ParsedFreiePlaetze parseFreiePlaetze(@NotNull String freiePlaetze) throws NumberFormatException {
        ParsedFreiePlaetze parsedFreiePlaetze = new ParsedFreiePlaetze();
        String[] split = freiePlaetze.split("\\(");
        String absolutString = split[0].replaceAll("[^\\d.]", "");
        String prozentString = split[1].replaceAll("[^\\d.]", "");
        if (absolutString.isEmpty()) {
            throw new NumberFormatException("Absolute Freie Plaetze Value is not a number.");
        }
        if (prozentString.isEmpty()) {
            throw new NumberFormatException("Percentage Freie Plaetze Value is not a number.");
        }
        Integer absolut = Integer.parseInt(absolutString);
        parsedFreiePlaetze.setAbsolut(absolut);
        Double prozent = Double.parseDouble(prozentString);
        parsedFreiePlaetze.setProzent(prozent);
        return parsedFreiePlaetze;
    }
}
