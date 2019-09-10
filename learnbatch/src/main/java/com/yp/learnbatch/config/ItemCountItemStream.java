package com.yp.learnbatch.config;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;

public class ItemCountItemStream implements ItemStream {
    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("ItemCount: " + executionContext.get("FlatFileItemReader.read.count"));
    }

    @Override
    public void close() throws ItemStreamException {

    }
}
