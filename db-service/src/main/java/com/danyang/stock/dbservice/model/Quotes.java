package com.danyang.stock.dbservice.model;

import java.util.List;

public class Quotes {

    private String username;
    private List<String> Quotes;

    public Quotes() {
    }

    public Quotes(String username, List<String> quotes) {
        this.username = username;
        Quotes = quotes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getQuotes() {
        return Quotes;
    }

    public void setQuotes(List<String> quotes) {
        Quotes = quotes;
    }
}
