package org.springframework.samples.petclinic.utility;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.visit.Visit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceCalculatorTest {
	private static final LocalDate matureBirth = LocalDate.of(LocalDate.now().getYear() - 2 - 1, 1, 1);
	private static final LocalDate infantBirth = LocalDate.now();

	@Test
	public void noPetsPriceTest() {
		List<Pet> pets = new ArrayList<>();

		double price = PriceCalculator.calcPrice(
			pets,
			9999999,
			9999999
		);

		assertEquals(0.0, price);
	}

	@Test
	public void noVisitPetsPriceTest() {
		List<Pet> pets = new ArrayList<>();
		Pet p1 = mock(Pet.class);
		when(p1.getBirthDate()).thenReturn(infantBirth);

		pets.add(p1);
		double price = PriceCalculator.calcPrice(pets, 15, 15);

		assertEquals(25.2, price);
	}


	@Test
	public void counterForInfantPetsBiggerWithRecentVisitTest() {
		List<Pet> pets = new ArrayList<>();
		Pet p1 = mock(Pet.class);
		when(p1.getBirthDate()).thenReturn(infantBirth);

		LocalDate visitDate = LocalDate.now().minusDays(15);
		List<Visit> visits = new ArrayList<>();
		Visit v1 = mock(Visit.class);
		visits.add(v1);

		when(p1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(v1.getDate()).thenReturn(visitDate);

		for (int i = 0 ; i < 5 ; i++) {
			pets.add(p1);
		}

		double price = PriceCalculator.calcPrice(pets, 20, 20);

		assertEquals(322.4, price);
	}

	@Test
	public void counterForInfantPetsBiggerWithoutRecentVisitTest() {
		List<Pet> pets = new ArrayList<>();
		Pet p1 = mock(Pet.class);
		when(p1.getBirthDate()).thenReturn(infantBirth);

		LocalDate visitDate = LocalDate.now().minusDays(365);
		List<Visit> visits = new ArrayList<>();
		Visit v1 = mock(Visit.class);
		visits.add(v1);

		when(p1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(v1.getDate()).thenReturn(visitDate);

		for (int i = 0 ; i < 5 ; i++) {
			pets.add(p1);
		}

		double price = PriceCalculator.calcPrice(pets, 40, 40);

		assertEquals(1302.3999999999999, price);
	}

	@Test
	public void counterForInfantPetsSmallerWithoutRecentVisitTest() {
		List<Pet> pets = new ArrayList<>();
		Pet p1 = mock(Pet.class);
		when(p1.getBirthDate()).thenReturn(infantBirth);

		LocalDate visitDate = LocalDate.now().minusDays(365);
		List<Visit> visits = new ArrayList<>();
		Visit v1 = mock(Visit.class);
		visits.add(v1);

		when(p1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(v1.getDate()).thenReturn(visitDate);

		for (int i = 0 ; i < 2 ; i++) {
			pets.add(p1);
		}

		double price = PriceCalculator.calcPrice(pets, 40, 40);

		assertEquals(134.39999999999998, price);
	}

	@Test
	public void counterForInfantSmallerWithRecentVisitTest() {
		List<Pet> pets = new ArrayList<>();
		Pet p1 = mock(Pet.class);
		when(p1.getBirthDate()).thenReturn(infantBirth);

		LocalDate visitDate = LocalDate.now().minusDays(20);
		List<Visit> visits = new ArrayList<>();
		Visit v1 = mock(Visit.class);
		visits.add(v1);

		when(p1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(v1.getDate()).thenReturn(visitDate);

		for (int i = 0 ; i < 2 ; i++) {
			pets.add(p1);
		}

		double price = PriceCalculator.calcPrice(pets, 40, 40);

		assertEquals(134.39999999999998, price);
	}

	@Test
	public void counterForMatureBiggerWithRecentVisitTest() {
		List<Pet> pets = new ArrayList<>();
		Pet p1 = mock(Pet.class);
		when(p1.getBirthDate()).thenReturn(matureBirth);

		LocalDate visitDate = LocalDate.now().minusDays(30);
		List<Visit> visits = new ArrayList<>();
		Visit v1 = mock(Visit.class);
		visits.add(v1);

		when(p1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(v1.getDate()).thenReturn(visitDate);

		for (int i = 0 ; i < 2 ; i++) {
			pets.add(p1);
		}

		double price = PriceCalculator.calcPrice(pets, 40, 40);
		assertEquals(96.0, price);
	}

	@Test
	public void noBaseChargeCounterForInfantBiggerWithRecentVisitTest() {
		List<Pet> pets = new ArrayList<>();
		Pet p1 = mock(Pet.class);
		when(p1.getBirthDate()).thenReturn(infantBirth);

		LocalDate visitDate = LocalDate.now().minusDays(25);
		List<Visit> visits = new ArrayList<>();
		Visit v1 = mock(Visit.class);
		visits.add(v1);

		when(p1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(v1.getDate()).thenReturn(visitDate);

		for (int i = 0 ; i < 2 ; i++) {
			pets.add(p1);
		}

		double price = PriceCalculator.calcPrice(pets, 0, 40);
		assertEquals(134.39999999999998, price);
	}

	@Test
	public void noBaseChargeCounterForMatureSmallerWithoutRecentVisitTest() {
		List<Pet> pets = new ArrayList<>();
		Pet p1 = mock(Pet.class);
		when(p1.getBirthDate()).thenReturn(matureBirth);

		LocalDate visitDate = LocalDate.now().minusDays(200);
		List<Visit> visits = new ArrayList<>();
		Visit v1 = mock(Visit.class);
		visits.add(v1);

		when(p1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(v1.getDate()).thenReturn(visitDate);

		for (int i = 0 ; i < 2 ; i++) {
			pets.add(p1);
		}

		double price = PriceCalculator.calcPrice(pets, 40, 0.0);

		assertEquals(0.0, price);
	}
}
