package net.kiranatos.ancillary;

public class PassException extends Exception {

    public PassException() {
    }

    public static void SingletonException (int total)
    {
        System.err.println("Something wrong with Singlton class!");
        System.err.println("Количество объектов класса = " + total);
    }
    
}
