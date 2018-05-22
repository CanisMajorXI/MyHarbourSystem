package com.myharbour.pojo;

import java.util.ArrayList;
import java.util.List;

public class ContainersAndTotalPages {

    private List<Container> containers = new ArrayList<>();

    private int page;

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
