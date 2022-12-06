package manager;

import org.junit.jupiter.api.Test;
import domain.Ticket;
import repository.Repository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ManagerTest {
    private final Repository repository = new Repository();
    private final Manager manager = new Manager(repository);

    private final Ticket firstFlight = new Ticket(1, 1010, "JFK", "TLV", 100);
    private final Ticket secondFlight = new Ticket(2, 1020, "JFK", "PVG", 200);
    private final Ticket thirdFlight = new Ticket(3, 1030, "PVG", "JFK", 300);
    private final Ticket fourFlight = new Ticket(4, 1040, "TLV", "JFK", 400);
    private final Ticket fifthFlight = new Ticket(5, 1050, "TLV", "PVG", 500);
    private final Ticket sixthFlight = new Ticket(6, 1060, "TLV", "LAX", 600);
    private final Ticket seventhFlight = new Ticket(7, 1030, "PVG", "JFK", 300);
    private final Ticket eighthFlight = new Ticket(8, 1080, "JFK", "TLV", 800);

    @Test
    void shouldFindTicket() {
        manager.add(firstFlight);
        manager.add(secondFlight);
        manager.add(thirdFlight);
        manager.add(fourFlight);
        manager.add(fifthFlight);
        manager.add(sixthFlight);
        manager.add(seventhFlight);
        manager.add(eighthFlight);

        Ticket[] actual = manager.findByCondition("JFK", "TLV");
        Ticket[] expected = {firstFlight, eighthFlight};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchTicketsForArrival() {
        manager.add(firstFlight);
        manager.add(secondFlight);
        manager.add(thirdFlight);
        manager.add(fourFlight);
        manager.add(fifthFlight);
        manager.add(sixthFlight);
        manager.add(seventhFlight);
        manager.add(eighthFlight);

        Ticket[] actual = manager.findByCondition("", "PVG");
        Ticket[] expected = {secondFlight, fifthFlight};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchTicketsForDeparture() {
        manager.add(firstFlight);
        manager.add(secondFlight);
        manager.add(thirdFlight);
        manager.add(fourFlight);
        manager.add(fifthFlight);
        manager.add(sixthFlight);
        manager.add(seventhFlight);
        manager.add(eighthFlight);

        Ticket[] actual = manager.findByCondition("TLV", "");
        Ticket[] expected = {fourFlight, fifthFlight, sixthFlight};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSortAllTicketsByPrice() {
        manager.add(firstFlight);
        manager.add(secondFlight);
        manager.add(thirdFlight);
        manager.add(fourFlight);
        manager.add(fifthFlight);
        manager.add(sixthFlight);
        manager.add(seventhFlight);
        manager.add(eighthFlight);

        Ticket[] actual = manager.findByCondition("", "");
        Ticket[] expected = {
                firstFlight,
                secondFlight,
                thirdFlight,
                seventhFlight,
                fourFlight,
                fifthFlight,
                sixthFlight,
                eighthFlight};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchTicketsForEqualPrice() {
        manager.add(firstFlight);
        manager.add(secondFlight);
        manager.add(thirdFlight);
        manager.add(fourFlight);
        manager.add(fifthFlight);
        manager.add(sixthFlight);
        manager.add(seventhFlight);
        manager.add(eighthFlight);

        Ticket[] actual = manager.findByCondition("PVG", "JFK");
        Ticket[] expected = {thirdFlight, seventhFlight};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchTicketsIfNotExist() {
        manager.add(firstFlight);
        manager.add(secondFlight);
        manager.add(thirdFlight);
        manager.add(fourFlight);
        manager.add(fifthFlight);
        manager.add(sixthFlight);
        manager.add(seventhFlight);
        manager.add(eighthFlight);

        Ticket[] actual = manager.findByCondition("BUD", "KBP");
        Ticket[] expected = {};

        assertArrayEquals(expected, actual);
    }

}