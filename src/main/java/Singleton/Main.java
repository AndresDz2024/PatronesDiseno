/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Singleton;

/**
 *
 * @author ARGOM
 */
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ejemplo Singleton
        Singleton singleton = Singleton.getInstance();
        singleton.showMessage();

        // Ejemplo Factory Method
        Creator creatorA = new ConcreteCreatorA();
        Creator creatorB = new ConcreteCreatorB();
        creatorA.anOperation();
        creatorB.anOperation();

        // Ejemplo Adapter
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.request();

        // Ejemplo Observer
        Subject subject = new Subject();
        Observer observer1 = new ConcreteObserver("Daza Observer 1");
        Observer observer2 = new ConcreteObserver("Daza Observer 2");
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.notifyObservers("Hello, Daza observers!");

        // Ejemplo Strategy
        Context context = new Context();
        context.setStrategy(new ConcreteStrategyA());
        context.executeStrategy();
        context.setStrategy(new ConcreteStrategyB());
        context.executeStrategy();
    }
}

// Singleton Pattern
class Singleton {
    private static Singleton instance;

    private Singleton() {
        // Constructor privado para prevenir la instanciación directa
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello, this is a Singleton instance!");
    }
}

// Factory Method Pattern
abstract class Product {
    abstract void use();
}

class ConcreteProductA extends Product {
    @Override
    void use() {
        System.out.println("Using Product A");
    }
}

class ConcreteProductB extends Product {
    @Override
    void use() {
        System.out.println("Using Product B");
    }
}

abstract class Creator {
    abstract Product factoryMethod();

    void anOperation() {
        Product product = factoryMethod();
        product.use();
    }
}

class ConcreteCreatorA extends Creator {
    @Override
    Product factoryMethod() {
        return new ConcreteProductA();
    }
}

class ConcreteCreatorB extends Creator {
    @Override
    Product factoryMethod() {
        return new ConcreteProductB();
    }
}

// Adapter Pattern
interface Target {
    void request();
}

class Adaptee {
    void specificRequest() {
        System.out.println("Specific request");
    }
}

class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

// Observer Pattern
interface Observer {
    void update(String message);
}

class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}

class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// Strategy Pattern
interface Strategy {
    void execute();
}

class ConcreteStrategyA implements Strategy {
    @Override
    public void execute() {
        System.out.println("Executing Strategy A");
    }
}

class ConcreteStrategyB implements Strategy {
    @Override
    public void execute() {
        System.out.println("Executing Strategy B");
    }
}

class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.execute();
    }
}