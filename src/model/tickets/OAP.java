package model.tickets;

public class OAP implements Ticket{

        private static final double PRICE = 6;

        @Override
        public double getPrice() {
            return PRICE;
        }
}
