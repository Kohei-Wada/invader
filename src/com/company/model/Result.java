package com.company.model;

public class Result {
    int score;

    public Result() {
        score = 0;
    }

    public void addScore(int n) {
        score += n;
    }

    public int getScore() {
        return score;
    }
}
