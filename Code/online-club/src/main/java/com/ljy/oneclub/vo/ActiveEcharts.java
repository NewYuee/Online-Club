package com.ljy.oneclub.vo;

import java.util.List;

public class ActiveEcharts {
    private List<String> xAxisData;
    private List<Integer> activeCountData;
    private List<Integer> articleCountData;

    public List<String> getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(List<String> xAxisData) {
        this.xAxisData = xAxisData;
    }

    public List<Integer> getActiveCountData() {
        return activeCountData;
    }

    public void setActiveCountData(List<Integer> activeCountData) {
        this.activeCountData = activeCountData;
    }

    public List<Integer> getArticleCountData() {
        return articleCountData;
    }

    public void setArticleCountData(List<Integer> articleCountData) {
        this.articleCountData = articleCountData;
    }
}
