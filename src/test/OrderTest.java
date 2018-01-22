package test;

import model.CinemaScreen;
import model.MovieShowing;
import model.Order;
import model.tickets.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    private Order testOrder;
    private Order testDiscountOrder;

    @Before
    public void setUp(){
        testOrder = new Order();
        //Create a movie not eligible for a discount
        MovieShowing movie1 = new MovieShowing("Jurassic World: Fallen Kingdom",
                LocalDateTime.of(2018,6,22,19,0,0),
                CinemaScreen.D);
        testOrder.setMovie(movie1);

        //Create list of attendees with single tickets
        LinkedHashMap<Ticket, Integer> attendees1 = new LinkedHashMap<>();
        attendees1.put(new Standard(), 1);
        attendees1.put(new Student(), 1);
        attendees1.put(new Child(), 1);
        testOrder.setAttendees(attendees1);


        testDiscountOrder = new Order();
        //Create a movie eligible for a discount
        MovieShowing movie2 = new MovieShowing("Avengers: Infinity War",
                LocalDateTime.of(2018,5,9,20,30,0),
                CinemaScreen.B);
        testDiscountOrder.setMovie(movie2);

        //Create list of attendees with multiple tickets for each
        LinkedHashMap<Ticket, Integer> attendees2 = new LinkedHashMap<>();
        attendees2.put(new Standard(), 2);
        attendees2.put(new Student(), 3);
        attendees2.put(new Child(), 4);
        attendees2.put(new OAP(), 5);
        testDiscountOrder.setAttendees(attendees2);
    }

    /**
     * Test that order gives correct order value and formatting for non discounted order
     */
    @Test
    public void testGetSummary(){
        String expectedResult = "\n\tStandard  £ 8.00 x  1 = £ 8.00"
                               +"\n\tStudent   £ 6.00 x  1 = £ 6.00"
                               +"\n\tChild     £ 4.00 x  1 = £ 4.00"
                               +"\nTOTAL PRICE: £18.00" ;
        assertEquals(testOrder.getSummary(), expectedResult);
    }

    /**
     * Test that order gives correct order value and formatting for discounted order
     */
    @Test
    public void testGetSummaryWithDiscount(){
        String expectedResult = "Order qualifies for Wednesday Discount!"
                               +"\n\tStandard  £ 6.00 x  2 = £12.00"
                               +"\n\tStudent   £ 4.00 x  3 = £12.00"
                               +"\n\tChild     £ 2.00 x  4 = £ 8.00"
                               +"\n\tOAP       £ 4.00 x  5 = £20.00"
                               +"\nTOTAL PRICE: £52.00" ;
        assertEquals(testDiscountOrder.getSummary(), expectedResult);
    }


}
