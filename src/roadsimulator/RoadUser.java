package roadsimulator;

abstract public class RoadUser {
    int speed;
    char marker;
    int positionX;
    int positionY;
    
    abstract public int getSpeed();
    
    abstract public char getMarker();
    
    abstract public int getPositionX();
    
    abstract public int getPositionY();
    
    abstract public void setPositionX(int PositionX);
    
    abstract public void setPositionY(int PositionY);
    
    @Override
    abstract public String toString();
}
