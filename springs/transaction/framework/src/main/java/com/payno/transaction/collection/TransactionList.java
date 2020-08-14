package com.payno.transaction.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionList<E> extends ArrayList<E> implements TransationSupport {

    List<E> memento;

    public TransactionList() {
        super();
        memento = Collections.emptyList();
    }

    @Override
    public void begin() {
        memento = this;
    }

    @Override
    public void commit() {
        memento = this;
    }

    @Override
    public void rollback() {
        this.clear();
        this.addAll(memento);
    }
}
