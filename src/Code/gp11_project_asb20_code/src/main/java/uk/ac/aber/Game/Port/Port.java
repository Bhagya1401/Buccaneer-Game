package uk.ac.aber.Game.Port;

public class Port {
    private String portName;
    private int[] coordinate;

    public Port(String name, int x, int y) {
        this.coordinate = new int[2];
        this.coordinate[0] = x;
        this.coordinate[1] = y;
        this.portName = name;
    }

    public String getPortName() {
        return this.portName;
    }

    public int[] getCoordinate() {
        return this.coordinate;
    }

    public int getColCoordinate(){
        return coordinate[0];
    }
    public int getRowCoordinate(){
        return coordinate[1];
    }

}
