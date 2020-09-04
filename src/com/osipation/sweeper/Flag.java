package com.osipation.sweeper;

class Flag {
    private Martix flagMap;
    private int countOfClosedBoxes;

    void start(){
        flagMap = new Martix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;

    }

    Box get (Coord coord){
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord,Box.OPENED);
        countOfClosedBoxes--;

    }




     void toggleFlagToBox(Coord coord) {//поставить или снять флаг
        switch (flagMap.get(coord)){
            case FLAGED: setClosedToBox(coord); break;
            case CLOSED: setFlagToBox(coord); break;
        }
    }
    private void setFlagToBox(Coord coord) {
        flagMap.set(coord,Box.FLAGED);
    }
    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

     void setOpenedToClosedBomb(Coord coord) {//открытие закрытых бомб
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

     void setNoBombToFlagedSafeBox(Coord coord) {//если там где поставил флаг нет бомбы
        if (flagMap.get(coord) == Box.FLAGED)
            flagMap.set (coord, Box.NOBOMB);

     }


    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around: Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }


}
