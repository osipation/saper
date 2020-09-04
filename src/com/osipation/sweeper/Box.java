package com.osipation.sweeper;

public enum Box {//класс перечисления для картинок
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object image;

    Box getNextNumberBox(){//возврвщвется след номер в боксе
        return Box.values()[this.ordinal() + 1];
    }

    int getNumber(){
        return this.ordinal();
    }
}
