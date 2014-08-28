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

public class EmptyHtmlElement implements HtmlElement {
    private static String EMPTY = "";
    public static HtmlElement INSTANCE = new EmptyHtmlElement();

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public HtmlElement first(String cssSelector) {
        return INSTANCE;
    }

    @Override
    public HtmlElement nth(int index, String cssSelector) {
        return INSTANCE;
    }

    @Override
    public HtmlElement last(String cssSelector) {
        return INSTANCE;
    }

    @Override
    public HtmlFragment all(String cssSelector) {
        return EmptyHtmlFragment.INSTANCE;
    }

    @Override
    public String text() {
        return EMPTY;
    }

    @Override
    public String outerHtml() {
        return EMPTY;
    }
}