package com.osipation.sweeper;

class Martix {//для хранения боксов(два слоя)
    private Box [] [] matrix;

    Martix(Box defaultBox){
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords())
            matrix [coord.x] [coord.y] = defaultBox;
    }

    void set (Coord coord, Box box){
        if (Ranges.inRange(coord)) {
            matrix[coord.x] [coord.y] = box;
        }
    }

    Box get (Coord coord){
        if (Ranges.inRange(coord)) {
            return matrix[coord.x] [coord.y];
        }
        else return null;
    }


}
