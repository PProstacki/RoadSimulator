package roadsimulator;

public class Bike extends Vehicle{
    Pedestrian pedestrian;
    
    /*Bike(){
        speed = 3;
        marker = 'r';
        positionX = 0;
        positionY = 0;
    }*/
    
    Bike(Pedestrian pedestrian){
        this.pedestrian = pedestrian;
        speed = 3;
        marker = 'B';
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public char getMarker() {
        return marker;
    }

    @Override
    public int getPositionX() {
        return pedestrian.getPositionX();
    }

    @Override
    public int getPositionY() {
        return pedestrian.getPositionY();
    }

    @Override
    public String toString() {
        return "" + getMarker();
    }
    
    @Override
    public void setPositionX(int positionX) {
        pedestrian.setPositionX(positionX);
    }

    @Override
    public void setPositionY(int positionY) {
        pedestrian.setPositionY(positionY);
    }
                                                    //dopisanie nowej funkcjonalnosci
}
