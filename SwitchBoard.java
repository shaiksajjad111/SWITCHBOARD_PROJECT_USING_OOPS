
 import java.util.*;

class Fan {
    public void initialize(int numberOfFans, int sequence, String fanStatus[]) {
        for (int fanIndex = 0; fanIndex < numberOfFans; fanIndex++) {
            System.out.println(sequence + "." + "Fan " + (fanIndex + 1) + " is " + fanStatus[fanIndex]);
            sequence++;
        }
    }

    public void option(int numberOfFans, int select, String fanStatus[]) {
        if (fanStatus[select - 1].equals("on")) {
            System.out.println("1.Switch off Fan " + select);
            System.out.println("2.back");
        } else {
            System.out.println("1.Switch on Fan " + select);
            System.out.println("2.back");
        }
    }

    public String[] change(int numberOfFans, int select, String fanStatus[]) {
        if (fanStatus[select - 1].equals("on")) {
            fanStatus[select - 1] = "off";
        } else {
            fanStatus[select - 1] = "on";
        }
        return fanStatus;
    }
}

class Ac {
    public void initialize(int numberOfACs, int sequence, String acStatus[]) {
        for (int acIndex = 0; acIndex < numberOfACs; acIndex++) {
            System.out.println(sequence + "." + "AC " + (acIndex + 1) + " is " + acStatus[acIndex]);
            sequence++;
        }
    }

    public void option(int numberOfACs, int select, String acStatus[]) {
        if (acStatus[select - 1].equals("on")) {
            System.out.println("1.Switch off AC " + select);
            System.out.println("2.back");
        } else {
            System.out.println("1.Switch on AC " + select);
            System.out.println("2.back");
        }
    }

    public String[] change(int numberOfACs, int select, String acStatus[]) {
        if (acStatus[select - 1].equals("on")) {
            acStatus[select - 1] = "off";
        } else {
            acStatus[select - 1] = "on";
        }
        return acStatus;
    }
}

class Bulb {
    public void initialize(int numberOfBulbs, int sequence, String bulbStatus[]) {
        for (int bulbIndex = 0; bulbIndex < numberOfBulbs; bulbIndex++) {
            System.out.println(sequence + "." + "Bulb " + (bulbIndex + 1) + " is " + bulbStatus[bulbIndex]);
            sequence++;
        }
        System.out.println(+sequence + ".exit");
    }

    public void option(int numberOfBulbs, int select, String bulbStatus[]) {
        if (bulbStatus[select - 1].equals("on")) {
            System.out.println("1.Switch off Bulb " + select);
            System.out.println("2.back");
        } else {
            System.out.println("1.Switch on Bulb " + select);
            System.out.println("2.back");
        }
    }

    public String[] change(int numberOfBulbs, int select, String bulbStatus[]) {
        if (bulbStatus[select - 1].equals("on")) {
            bulbStatus[select - 1] = "off";
        } else {
            bulbStatus[select - 1] = "on";
        }
        return bulbStatus;
    }
}

class SwitchChecker {
    public int check(int numberOfFans, int numberOfACs, int numberOfBulbs, String fanStatus[], String acStatus[], String bulbStatus[]) {
        Fan fan = new Fan();
        Ac ac = new Ac();
        Bulb bulb = new Bulb();
        System.out.println("Enter checking value:");
        Scanner scanner = new Scanner(System.in);
        int check = scanner.nextInt();
        if (check <= numberOfFans) {
            fan.option(numberOfFans, check, fanStatus);
        } else if (numberOfFans < check && check <= (numberOfFans + numberOfACs)) {
            ac.option(numberOfACs, (check - numberOfFans), acStatus);
        } else if ((numberOfFans + numberOfACs) < check && check <= (numberOfFans + numberOfACs + numberOfBulbs)) {
            bulb.option(numberOfBulbs, (check - (numberOfFans + numberOfACs)), bulbStatus);
        } else if (check == (numberOfFans + numberOfACs + numberOfBulbs + 1)) {
            System.exit(0);
        }
        int choice = scanner.nextInt();
        if (choice == 1) {
            if (check <= numberOfFans) {
                fanStatus = fan.change(numberOfFans, check, fanStatus);
            } else if (numberOfFans < check && check <= (numberOfFans + numberOfACs)) {
                acStatus = ac.change(numberOfACs, (check - numberOfFans), acStatus);
            } else if ((numberOfFans + numberOfACs) < check && check <= (numberOfFans + numberOfACs + numberOfBulbs)) {
                bulbStatus = bulb.change(numberOfBulbs, (check - (numberOfFans + numberOfACs)), bulbStatus);
            }
            fan.initialize(numberOfFans, (0 + 1), fanStatus);
            ac.initialize(numberOfACs, (numberOfFans + 1), acStatus);
            bulb.initialize(numberOfBulbs, (numberOfFans + numberOfACs + 1), bulbStatus);
        } else {
            fan.initialize(numberOfFans, (0 + 1), fanStatus);
            ac.initialize(numberOfACs, (numberOfFans + 1), acStatus);
            bulb.initialize(numberOfBulbs, (numberOfFans + numberOfACs + 1), bulbStatus);
        }
        return choice;
    }
}

public class SwitchBoard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of fans:");
        int numberOfFans = scanner.nextInt();
        String fanStatus[] = new String[numberOfFans];
        for (int i = 0; i < numberOfFans; i++) {
            fanStatus[i] = "off";
        }
        System.out.println("Enter number of ACs:");
        int numberOfACs = scanner.nextInt();
        String acStatus[] = new String[numberOfACs];
        for (int i = 0; i < numberOfACs; i++) {
            acStatus[i] = "off";
        }
        System.out.println("Enter number of Bulbs:");
        int numberOfBulbs = scanner.nextInt();
        String bulbStatus[] = new String[numberOfBulbs];
        for (int i = 0; i < numberOfBulbs; i++) {
            bulbStatus[i] = "off";
        }
        Fan fan = new Fan();
        fan.initialize(numberOfFans, (0 + 1), fanStatus);
        Ac ac = new Ac();
        ac.initialize(numberOfACs, (numberOfFans + 1), acStatus);
        Bulb bulb = new Bulb();
        bulb.initialize(numberOfBulbs, (numberOfFans + numberOfACs + 1), bulbStatus);
        SwitchChecker switchChecker = new SwitchChecker();
        int choice = switchChecker.check(numberOfFans, numberOfACs, numberOfBulbs, fanStatus, acStatus, bulbStatus);
        while (choice == 1) {
            choice = switchChecker.check(numberOfFans, numberOfACs, numberOfBulbs, fanStatus, acStatus, bulbStatus);
        }
    }
}