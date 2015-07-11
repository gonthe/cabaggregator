package com.kiwi.cabaggregator.uber.entities;

import java.util.List;

/**
 * Created by mohit on 9/7/15.
 */
public class UberServiceDataWrapper {

    private List<Price> prices;

    private List<Time> times;

    private List<Product> products;

    public List<Price> getPrices() {
        return prices;

    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Time> getTimes() {
        return times;
    }

}
