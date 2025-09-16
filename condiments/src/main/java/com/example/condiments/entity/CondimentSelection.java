package com.example.condiments.entity;

import java.util.List;

public class CondimentSelection {
    private List<String> selected;

    public CondimentSelection() {
    }

    public CondimentSelection(List<String> selected) {
        this.selected = selected;
    }

    public List<String> getSelected() {
        return selected;
    }

    public void setSelected(List<String> selected) {
        this.selected = selected;
    }
}
