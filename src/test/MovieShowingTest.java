package test;

import model.CinemaScreen;
import model.MovieShowing;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class MovieShowingTest {

    private MovieShowing testMovie1;
    private MovieShowing testMovie2;

    @Before
    public void setUp(){
        //Create 2 movies for testing which can be accessed in test class in case more tests are added
        testMovie1 = new MovieShowing("Jurassic World: Fallen Kingdom",
                LocalDateTime.of(2018,6,22,19,0,0),
                CinemaScreen.D);

        testMovie2 = new MovieShowing("Avengers: Infinity War",
                LocalDateTime.of(2018,5,9,20,30,0),
                CinemaScreen.B);
    }

    /**
     * This test ensures that String representation of a movie displays correct information in the
     * correct format
     */
    @Test
    public void testToString(){
        String movie1 = "Jurassic World: Fallen Kingdom @ 22/06/2018 19:00 in Cinema D";
        assertEquals(testMovie1.toString(), movie1);

        String movie2 = "Avengers: Infinity War @ 09/05/2018 20:30 in Cinema B";
        assertEquals(testMovie2.toString(), movie2);
    }




}
