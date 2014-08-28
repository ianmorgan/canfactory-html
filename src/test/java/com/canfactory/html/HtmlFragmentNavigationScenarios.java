//-----------------------------------------------------------------------
// Copyright Can Factory Limited, UK
// http://www.canfactory.com - mailto:info@canfactory.com
//
// The copyright to the computer program(s) (source files, compiled
// files and documentation) herein is the property of Can Factory
// Limited, UK.
//
// The program(s) may be used and/or copied only with the written
// permission of Can Factory Limited or in accordance with the terms
// and conditions stipulated in the agreement/contract under which
// the program(s) have been supplied.
//-----------------------------------------------------------------------

package com.canfactory.html;

import com.canfactory.html.HtmlFragment.Factory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Scenarios for basic navigation through an HtmlFragment using CSS style selectors
 */

@Test
public class HtmlFragmentNavigationScenarios {

    public void shouldReturnFragmentsOuterHtml() {
        HtmlFragment fragment = Factory.fromString("<p>This is some html</p>");
        assertEquals(fragment.outerHtml(), "<p>This is some html</p>");
    }

    public void shouldReturnFragmentsText() {
        HtmlFragment fragment = Factory.fromString("<p>This is some <span class=\"bold\">html</span></p>");
        assertEquals(fragment.text(), "This is some html");
    }

    public void shouldReturnFirstElementUsingSelector() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertEquals(fragment.first("ul").outerHtml(), "<ul id=\"colours\"> \n" +
                " <li>Red</li> \n" +
                " <li>Green</li> \n" +
                " <li>Blue</li> \n" +
                "</ul>");

        assertEquals(fragment.first("li").outerHtml(), "<li>Red</li>");

        assertEquals(fragment.first("ol").outerHtml(), "<ol id=\"numbers\"> \n" +
                " <li>One</li> \n" +
                " <li>Two</li> \n" +
                " <li>Three</li> \n" +
                "</ol>");

        assertEquals(fragment.first("ol li").outerHtml(), "<li>One</li>");

        assertEquals(fragment.first("#animals li ul").outerHtml(), "<ul id=\"cats\"> \n" +
                " <li>Persian</li> \n" +
                " <li>Manx</li> \n" +
                " <li>Siamese</li> \n" +
                "</ul>");
    }

    public void shouldReturnLastElementUsingSelector() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertEquals(fragment.last("#colours li").outerHtml(), "<li>Blue</li>");
    }

    public void shouldReturnEmptyHtmlElementIfNoMatch() {
        HtmlFragment fragment = loadExample("sample.html");
        assertTrue(fragment.last("table") instanceof EmptyHtmlElement);
        assertTrue(fragment.first("table") instanceof EmptyHtmlElement);
        assertTrue(fragment.nth(1,"table") instanceof EmptyHtmlElement);
        assertTrue(fragment.nth(100, "li") instanceof EmptyHtmlElement);
        assertTrue(fragment.all("table") instanceof EmptyHtmlFragment);
    }


    public void shouldReturnAllElement() {
        HtmlFragment fragment = loadExample("simple-lists.html");

        assertEquals(fragment.all("li").outerHtml(), "<li>Red</li>\n" +
                "<li>Green</li>\n" +
                "<li>Blue</li>\n" +
                "<li>One</li>\n" +
                "<li>Two</li>\n" +
                "<li>Three</li>\n" +
                "<li>Cats \n" +
                " <ul id=\"cats\"> \n" +
                "  <li>Persian</li> \n" +
                "  <li>Manx</li> \n" +
                "  <li>Siamese</li> \n" +
                " </ul> </li>\n" +
                "<li>Persian</li>\n" +
                "<li>Manx</li>\n" +
                "<li>Siamese</li>\n" +
                "<li>Dogs \n" +
                " <ul id=\"dogs\"> \n" +
                "  <li>Collie</li> \n" +
                "  <li>Jack Russell</li> \n" +
                "  <li>Corgi</li> \n" +
                " </ul> </li>\n" +
                "<li>Collie</li>\n" +
                "<li>Jack Russell</li>\n" +
                "<li>Corgi</li>");

        assertEquals(fragment.first("#animals li ul").outerHtml(), "<ul id=\"cats\"> \n" +
                " <li>Persian</li> \n" +
                " <li>Manx</li> \n" +
                " <li>Siamese</li> \n" +
                "</ul>");
    }

    public void shouldReturnEmptyHtmlFragmentIfNoMatch() {
        HtmlFragment fragment = loadExample("sample.html");
        assertTrue(fragment.all("table") instanceof EmptyHtmlFragment);
    }


    private HtmlFragment loadExample(String exampleFileName) {
        return Factory.fromStream(this.getClass().getResourceAsStream("/com/canfactory/html/" + exampleFileName));
    }
}