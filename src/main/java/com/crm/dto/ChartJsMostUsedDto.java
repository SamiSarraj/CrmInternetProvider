package com.crm.dto;

import java.util.List;

public class ChartJsMostUsedDto {

    public List<Integer> getAmount() {
        return amount;
    }

    public void setAmount(List<Integer> amount) {
        this.amount = amount;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<Integer> amount;
    public List<String> title;


}
