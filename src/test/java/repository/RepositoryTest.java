package repository;

import org.junit.jupiter.api.Test;
import domain.NotFoundException;
import domain.Ticket;
import manager.Manager;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
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
    void shouldSearchById() {
        manager.add(firstFlight);
        manager.add(secondFlight);
        manager.add(thirdFlight);
        manager.add(fourFlight);
        manager.add(fifthFlight);
        manager.add(sixthFlight);
        manager.add(seventhFlight);
        manager.add(eighthFlight);

        Ticket[] actual = repository.findById(4);
        Ticket[] expected = {fourFlight};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        repository.save(firstFlight);
        repository.save(secondFlight);
        repository.save(thirdFlight);
        repository.save(fourFlight);
        repository.save(fifthFlight);
        repository.save(sixthFlight);
        repository.save(seventhFlight);
        repository.save(eighthFlight);
        repository.removeById(7);

        Ticket[] expected = {
                firstFlight,
                secondFlight,
                thirdFlight,
                fourFlight,
                fifthFlight,
                sixthFlight,
                eighthFlight};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByIdNoReal() {
        manager.add(firstFlight);
        manager.add(secondFlight);
        manager.add(thirdFlight);
        manager.add(fourFlight);
        manager.add(fifthFlight);
        manager.add(sixthFlight);
        manager.add(seventhFlight);
        manager.add(eighthFlight);

        Ticket[] actual = repository.findById(9);

        assertArrayEquals(null, actual);
    }

    @Test
    void shouldRemoveByIdIfNotExistAndCatchException() {
        repository.save(firstFlight);
        repository.save(secondFlight);
        repository.save(thirdFlight);
        repository.save(fourFlight);
        repository.save(fifthFlight);
        repository.save(sixthFlight);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(7);
        });
    }

    @Test
    void shouldSearchIfRepoEmpty() {
        repository.removeAll();

        Ticket[] actual = manager.findByCondition("TLV", "PVG");
        Ticket[] expected = {};

        assertArrayEquals(expected, actual);
    }
}