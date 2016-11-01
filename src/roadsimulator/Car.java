package roadsimulator;

public class Car extends Vehicle{
    Pedestrian pedestrian;
    
    /*Car(){
        speed = 5;
        marker = 'c';
        positionX = 0;
        positionY = 0;
    }*/

    Car(Pedestrian pedestrian){
        this.pedestrian = pedestrian;
        speed = 5;
        marker = 'C';
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
