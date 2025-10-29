package com.waduclay.kata;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public enum Score {
    LOVE,
    POINT,
    TWO_POINTS,
    THREE_POINTS,
    ADVANTAGE,
    WIN;

    public int getPoint(){
        return this.ordinal();
    }
}
