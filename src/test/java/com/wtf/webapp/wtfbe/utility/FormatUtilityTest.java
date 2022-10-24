package com.wtf.webapp.wtfbe.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatUtilityTest {

    @Test
    public void stringToJson_success1() {
        String target = "startIdx=0&order=%7B%22entityFieldName%22:%22matchDate%22,%22orderSortKeyword%22:%22DESC%22%7D";
        String expected = "{\"startIdx\":0,\"order\":{\"entityFieldName\":\"matchDate\",\"orderSortKeyword\":\"DESC\"}}";

        String res = FormatUtility.stringToJson(target);

        assertEquals(expected, res);
    }

    @Test
    public void stringToJson_success2() {
        String target = "startIdx=0";
        String expected = "{\"startIdx\":0}";

        String res = FormatUtility.stringToJson(target);

        assertEquals(expected, res);
    }

    @Test
    public void stringToJson_success3() {
        String target = "tartIdx=0&order=%7B%22entityFieldName%22:%22matchDate%22,%22orderSortKeyword%22:%22DESC%26order=%7B%22entityFieldName%22:%22matchDate%22,%22orderSortKeyword%22:%22DESC%22%7D%22%7D";
        String expected = "{\"tartIdx\":0,\"order\":{\"entityFieldName\":\"matchDate\",\"orderSortKeyword\":\"DESC\",\"order\":{\"entityFieldName\":\"matchDate\",\"orderSortKeyword\":\"DESC\"}}}";

        String res = FormatUtility.stringToJson(target);

        assertEquals(expected, res);
    }
}
