package com.veyron.www.csdnclient;

import com.veyron.www.csdnclient.util.HtmlUtil;
import com.veyron.www.csdnclient.util.URLUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

       String html = HtmlUtil.doGet(URLUtil.TEST);
        System.out.print(html);
    }
}