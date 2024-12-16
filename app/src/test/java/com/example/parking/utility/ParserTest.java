package com.example.parking.utility;

import com.example.parking.model.ParsedFreiePlaetze;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testStringToLongList() {
        List<Long> idsActual1 = Parser.stringToLongList("1,2,3");
        List<Long> idsExpected1 = List.of(1L, 2L, 3L);
        assertEquals(idsExpected1, idsActual1);

        List<Long> idsActual2 = Parser.stringToLongList("1,2,3");
        List<Long> idsExpected2 = List.of(1L, 2L, 3L);
        assertEquals(idsExpected2, idsActual2);

        List<Long> idsActual3 = Parser.stringToLongList("42");
        List<Long> idsExpected3 = Collections.singletonList(42L);
        assertEquals(idsExpected3, idsActual3);
    }

    @Test
    void testStringToLongListWithDelimiter() {
        List<Long> idsActual1 = Parser.stringToLongList("1,2,3", ",");
        List<Long> idsExpected1 = List.of(1L, 2L, 3L);
        assertEquals(idsExpected1, idsActual1);
        List<Long> idsActual2 = Parser.stringToLongList("1;-20;333333", ";");
        List<Long> idsExpected2 = List.of(1L, -20L, 333333L);
        assertEquals(idsExpected2, idsActual2);
        assertThrows(NumberFormatException.class, () -> Parser.stringToLongList("-1,22,0", ";"), "Expected throw due to wrong delimiter.");
    }

    @Test
    void testParseFreiePlaetze() {
        String test1 = "50 (49%)";
        ParsedFreiePlaetze parsed1 = Parser.parseFreiePlaetze(test1);
        assertEquals(50, parsed1.getAbsolut());
        assertEquals(49D, parsed1.getProzent());

        String test2 = "0 (0%)";
        ParsedFreiePlaetze parsed2 = Parser.parseFreiePlaetze(test2);
        assertEquals(0, parsed2.getAbsolut());
        assertEquals(0D, parsed2.getProzent());

        String test3 = "45 (1.7463%)";
        ParsedFreiePlaetze parsed3 = Parser.parseFreiePlaetze(test3);
        assertEquals(45, parsed3.getAbsolut());
        assertEquals(1.7463D, parsed3.getProzent());

        final String test4 = "elephant (11%)";
        assertThrows(NumberFormatException.class, () -> Parser.parseFreiePlaetze(test4));
    }
}