package org.springframework.samples.petclinic.model.priceCalculator;

import org.junit.Test;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.UserType;
import org.springframework.samples.petclinic.model.priceCalculators.CustomerDependentPriceCalculator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDependentPriceCalculatorTest {
	@Test
	public void calcPriceTest() {
		List<Pet> pets = new ArrayList<>();
		Pet P1 = new Pet();
		Pet P2 = new Pet();
		Pet P3 = new Pet();

		PetType T1 = new PetType();

		P1.setType(T1);
		P2.setType(T1);

		P1.setBirthDate(new Date());
		P2.setBirthDate(new Date(974289794));
		P3.setBirthDate(new Date());

		pets.add(P1);
		pets.add(P2);
		pets.add(P2);
		pets.add(P2);
		pets.add(P2);
		pets.add(P2);
		pets.add(P2);
		pets.add(P2);
		pets.add(P3);
		double baseCharge = 15.5;
		double basePricePerPet  = 7.35;
		UserType userType = UserType.GOLD;

		CustomerDependentPriceCalculator C1 = new CustomerDependentPriceCalculator();
		double result = C1.calcPrice(pets, baseCharge, basePricePerPet, userType);


	}
}
