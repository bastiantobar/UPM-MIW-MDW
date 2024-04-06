package usantatecla.movies.v25;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private String customerName;
	private String movieName;

	@Before
	public void setUp() {
		this.customerName = "customerName";
		this.movieName = "movieName";
	}

	@Test
	public void withoutRentalsTest() {

		Customer customer = new CustomerBuilder().name(this.customerName).build();
		String statement = customer.statement();
		String result = new StatementBuilder().customerName(this.customerName)
				.totalAmount(0).frequentRenterPoints(0).build();
		assertEquals(result, statement);
	}

	@Test
	public void totalChargeForRegular2DayTest() {
		Movie regularMovie = new RegularMovie(this.movieName);
		Rental rental = new RentalBuilder().movie(regularMovie).daysRented(2).build();
		Customer customer = new CustomerBuilder().name(this.customerName).rental(rental).build();

		assertTrue(customer.getTotalCharge() == 2);
	}

	@Test
	public void totalChargeForRegular3DayTest() {
		Movie regularMovie = new RegularMovie(this.movieName);
		Rental rental = new RentalBuilder().movie(regularMovie).daysRented(3).build();
		Customer customer = new CustomerBuilder().name(this.customerName).rental(rental).build();

		assertTrue(customer.getTotalCharge() == 3.5);
	}

	@Test
	public void totalFrequentRenterPointsForRegularTest() {
		Movie regularMovie = new RegularMovie(this.movieName);
		Rental rental = new RentalBuilder().movie(regularMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(this.customerName).rental(rental).build();

		assertTrue(customer.getTotalFrequentRenterPoints() == 1);
	}

	@Test
	public void totalChargeForChildren3DayTest() {
		Movie childrenMovie = new ChildrenMovie(this.movieName);
		Rental rental = new RentalBuilder().movie(childrenMovie).daysRented(3).build();
		Customer customer = new CustomerBuilder().name(this.customerName).rental(rental).build();

		assertTrue(customer.getTotalCharge() == 1.5);
	}

	@Test
	public void totalChargeForChildren4DayTest() {
		Movie childrenMovie = new ChildrenMovie(this.movieName);
		Rental rental = new RentalBuilder().movie(childrenMovie).daysRented(4).build();
		Customer customer = new CustomerBuilder().name(this.customerName).rental(rental).build();

		assertTrue(customer.getTotalCharge() == 6);
	}

	@Test
	public void totalFrequentRenterPointsForChildrenTest() {
		Movie childrenMovie = new ChildrenMovie(this.movieName);
		Rental rental = new RentalBuilder().movie(childrenMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(this.customerName).rental(rental).build();

		assertTrue(customer.getTotalFrequentRenterPoints() == 1);
	}

	@Test
	public void totalChargeForNewRelease1DayTest() {
		Movie newReleaseMovie = new NewReleaseMovie(this.movieName);
		Rental rental = new RentalBuilder().movie(newReleaseMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(this.customerName).rental(rental).build();

		assertTrue(customer.getTotalCharge() == 3);
	}

	@Test
	public void totalFrequentRenterPointsForNewRelease1DayTest() {
		Movie newReleaseMovie = new NewReleaseMovie(this.movieName);
		Rental rental = new RentalBuilder().movie(newReleaseMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(this.customerName).rental(rental).build();

		assertTrue(customer.getTotalFrequentRenterPoints() == 1);
	}

	@Test
	public void totalFrequentRenterPointsForNewRelease2DayTest() {
		Movie newReleaseMovie = new NewReleaseMovie(this.movieName);
		Rental rental = new RentalBuilder().movie(newReleaseMovie).daysRented(2).build();
		Customer customer = new CustomerBuilder().name(this.customerName).rental(rental).build();

		assertTrue(customer.getTotalFrequentRenterPoints() == 2);
	}

	@Test
	public void rentalTest() {
		String regularMovieName = "regularMovieName";
		Movie regularMovie = new RegularMovie(regularMovieName);
		Rental regularRental = new RentalBuilder().movie(regularMovie).daysRented(10).build();

		String newReleaseMovieName = "newReleaseMovieName";
		Movie newReleaseMovie = new NewReleaseMovie(newReleaseMovieName);
		Rental newReleaseRental = new RentalBuilder().movie(newReleaseMovie).daysRented(10).build();

		String childrensMovieName = "childrensMovieName";
		Movie childrenMovie = new ChildrenMovie(childrensMovieName);
		Rental childrensRental = new RentalBuilder().movie(childrenMovie).daysRented(10).build();

		Customer customer = new CustomerBuilder().name(this.customerName)
				.rental(regularRental).rental(newReleaseRental).rental(childrensRental).build();

		String statement = customer.statement();

		String result = new StatementBuilder().customerName(this.customerName)
				.movie(regularMovieName, 14).movie(newReleaseMovieName, 3).movie(childrensMovieName, 15)
				.totalAmount(32).frequentRenterPoints(4).build();
		assertEquals(result, statement);
	}


}