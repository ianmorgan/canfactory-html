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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ManyHtmlElements extends ToStringComparable implements HtmlElements {

    private List<HtmlElement> data;

    ManyHtmlElements(List<HtmlElement> data) {
        this.data = data;
    }

    public int size() {
        return data.size();
    }

    public <T> Iterable<T> map(Functor<T> functor) {
        return new TranformIterable<T>(this, functor);
    }

    @Override
    public Iterable<HtmlElements> grouped(int size) {
        final HtmlElements THIS = this;
        final int SIZE = size;
        return new Iterable<HtmlElements>() {
            @Override
            public Iterator<HtmlElements> iterator() {
                return new GroupedIterator(THIS, SIZE);
            }
        };
    }

    public HtmlElements append(HtmlElement newElement) {
        List<HtmlElement> elements = new ArrayList<HtmlElement>(data.size() + 1);
        elements.addAll(data);
        elements.add(newElement);
        return HtmlElements.Factory.fromList(elements);
    }

    public Iterator<HtmlElement> iterator() {
        return Collections.unmodifiableList(data).iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HtmlElement e : data) {
            sb.append(e.toString());
        }
        return sb.toString();
    }
}
