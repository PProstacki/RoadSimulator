package roadsimulator;

public class Pedestrian extends RoadUser{

    Pedestrian(int positionX, int positionY){
        speed = 1;
        marker = 'p';
        this.positionX = positionX;
        this.positionY = positionY;
    }
    
    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public char getMarker() {
        return 'p';
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public String toString() {
        return "" + getMarker();
    }

    @Override
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    @Override
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    
}
