package roadsimulator;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class RoadMovement {

    private LinkedList<RoadUser> usersList = new LinkedList();
    private char[][] road = new char[25][25];
    boolean crash = false;

    public void start() {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        clearRoad();
        usersList.add(new Pedestrian(r.nextInt(road.length), r.nextInt(road[0].length)));
        usersList.add(new Pedestrian(r.nextInt(road.length), r.nextInt(road[0].length)));
        createVehiclesMark();

        do {
            clearRoad();
            System.out.println("=================================================================");

            if (r.nextInt(4) == 0) {
                createPedestrian();
            }

            addToRoad();
            showRoad();
            
            gettingOff();
            move();
            if (crash == false) {
                crash = crash();
            }

            sc.nextLine();
        } while (!crash);
        System.out.println("=================================================================");
        showRoad();
    }

    private void clearRoad() {
        for (int i = 0; i < road.length; i++) {
            for (int j = 0; j < road[0].length; j++) {
                if (road[i][j] != 'c' && road[i][j] != 'b') {
                    road[i][j] = ' ';
                }
            }
        }
    }

    private void showRoad() {
        for (int i = 0; i < road.length; i++) {
            for (int j = 0; j < road[0].length; j++) {
                System.out.print(road[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void addToRoad() {
        for (RoadUser user : usersList) {
            road[user.getPositionX()][user.getPositionY()] = user.getMarker();
        }
    }

    private boolean crash() {
        for (RoadUser user1 : usersList) {
            for (RoadUser user2 : usersList) {
                if (user1.getPositionX() == user2.getPositionX() && user1.getPositionY() == user2.getPositionY() && user1 != user2) {
                    return true;
                }
            }
        }
        return false;
    }

    private void createVehiclesMark() {
        int x = 0;
        int y = 0;
        Random r = new Random();

        for (int i = 0; i < 6; i++) {
            do {
                x = r.nextInt(road.length);
                y = r.nextInt(road[0].length);
            } while (road[x][y] != ' ');
            road[x][y] = 'b';
        }

        for (int i = 0; i < 3; i++) {
            do {
                x = r.nextInt(road.length);
                y = r.nextInt(road[0].length);
            } while (road[x][y] != ' ');
            road[x][y] = 'c';
        }
    }

    private void createPedestrian() {
        Random r = new Random();
        int x = 0;

        if (r.nextInt(2) == 0) {      //lewo prawo
            if (r.nextInt(2) == 0) {  //lewo
                do {
                    x = r.nextInt(road[0].length);
                } while (road[0][x] != ' ');
                usersList.add(new Pedestrian(0, x));
            } else {                  //prawo
                do {
                    x = r.nextInt(road[0].length);
                } while (road[road.length - 1][x] != ' ');
                usersList.add(new Pedestrian(road.length - 1, x));
            }
        } else //gora dol
        {
            if (r.nextInt(2) == 0) {  //gora
                do {
                    x = r.nextInt(road.length);
                } while (road[x][0] != ' ');
                usersList.add(new Pedestrian(x, 0));
            } else {                  //dol
                do {
                    x = r.nextInt(road.length);
                } while (road[x][road[0].length - 1] != ' ');
                usersList.add(new Pedestrian(x, road[0].length - 1));
            }
        }
    }

    private void move() {
        Random r = new Random();
        boolean isMoved = false;

        for (RoadUser user : usersList) {
            do {
                isMoved = false;
                if (r.nextInt(2) == 0) {              //lewo prawo
                    if (r.nextInt(2) == 0) {          //lewo
                        if (user.getPositionX() - user.getSpeed() > 0) {
                            user.setPositionX(user.getPositionX() - user.getSpeed());
                            isMoved = true;
                            if (user instanceof Pedestrian) {
                                if (road[user.getPositionX()][user.getPositionY()] == 'c') {
                                    usersList.set(usersList.indexOf(user), new Car((Pedestrian) user));
                                } else if (road[user.getPositionX()][user.getPositionY()] == 'b') {
                                    usersList.set(usersList.indexOf(user), new Bike((Pedestrian) user));
                                }
                            } else if (user instanceof Bike || user instanceof Car) {
                                crash = true;
                            }

                        }
                    } else //prawo
                     if (user.getPositionX() + user.getSpeed() < road.length - 1) {
                            user.setPositionX(user.getPositionX() + user.getSpeed());
                            isMoved = true;
                            if (user instanceof Pedestrian) {
                                if (road[user.getPositionX()][user.getPositionY()] == 'c') {
                                    usersList.set(usersList.indexOf(user), new Car((Pedestrian) user));
                                } else if (road[user.getPositionX()][user.getPositionY()] == 'b') {
                                    usersList.set(usersList.indexOf(user), new Bike((Pedestrian) user));
                                }
                            } else if (user instanceof Bike || user instanceof Car) {
                                crash = true;
                            }

                        }
                } else //gora dol
                 if (r.nextInt(2) == 0) {          //gora
                        if (user.getPositionY() - user.getSpeed() > 0) {
                            user.setPositionY(user.getPositionY() - user.getSpeed());
                            isMoved = true;
                            if (user instanceof Pedestrian) {
                                if (road[user.getPositionX()][user.getPositionY()] == 'c') {
                                    usersList.set(usersList.indexOf(user), new Car((Pedestrian) user));
                                } else if (road[user.getPositionX()][user.getPositionY()] == 'b') {
                                    usersList.set(usersList.indexOf(user), new Bike((Pedestrian) user));
                                }
                            } else if (user instanceof Bike || user instanceof Car) {
                                crash = true;
                            }
                        }
                    } else //dol
                    {
                        if (user.getPositionY() + user.getSpeed() < road[0].length - 1) {
                            user.setPositionY(user.getPositionY() + user.getSpeed());
                            isMoved = true;
                            if (user instanceof Pedestrian) {
                                if (road[user.getPositionX()][user.getPositionY()] == 'c') {
                                    usersList.set(usersList.indexOf(user), new Car((Pedestrian) user));
                                } else if (road[user.getPositionX()][user.getPositionY()] == 'b') {
                                    usersList.set(usersList.indexOf(user), new Bike((Pedestrian) user));
                                }
                            } else if (user instanceof Bike || user instanceof Car) {
                                crash = true;
                            }
                        }
                    }
            } while (!isMoved);
        }
    }

    private void gettingOff() {
        Random r = new Random();
        for (RoadUser user : usersList) {
            if (r.nextInt(4) == 0) {
                if (user instanceof Bike) {
                    usersList.set(usersList.indexOf(user), ((Bike) user).dismount());
                    road[user.getPositionX()][user.getPositionY()] = 'b';
                } else if (user instanceof Car) {
                    usersList.set(usersList.indexOf(user), ((Car) user).dismount());
                    road[user.getPositionX()][user.getPositionY()] = 'c';
                }
                if (user.getPositionX() > 0 && road[user.getPositionX() - 1][user.getPositionY()] == ' ') {
                    user.setPositionX(user.getPositionX() - 1);
                    user.setPositionY(user.getPositionY());
                } else if (user.getPositionX() < road.length - 1 && road[user.getPositionX() + 1][user.getPositionY()] == ' ') {
                    user.setPositionX(user.getPositionX() + 1);
                    user.setPositionY(user.getPositionY());
                } else if (user.getPositionY() > 0 && road[user.getPositionX()][user.getPositionY() - 1] == ' ') {
                    user.setPositionX(user.getPositionX());
                    user.setPositionY(user.getPositionY() - 1);
                } else if (user.getPositionY() < road[0].length - 1 && road[user.getPositionX()][user.getPositionY() + 1] == ' ') {
                    user.setPositionX(user.getPositionX());
                    user.setPositionY(user.getPositionY() + 1);
                } else {
                    crash = true;
                    road[user.getPositionX()][user.getPositionY()] = 'X';
                }
 
            }
        }
    }

}
