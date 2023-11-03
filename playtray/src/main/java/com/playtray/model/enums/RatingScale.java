package com.playtray.model.enums;

public enum RatingScale {

    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    public final int value;

    RatingScale(int val) {
        this.value = val;
    }
}
