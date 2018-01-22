package model.tickets;

public class Student implements Ticket{
    private static final double PRICE = 6;

    @Override
    public double getPrice() {
        return PRICE;
    }
}
