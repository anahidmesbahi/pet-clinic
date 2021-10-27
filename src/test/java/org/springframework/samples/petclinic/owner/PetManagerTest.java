package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import org.springframework.samples.petclinic.utility.SimpleDI;
import org.springframework.samples.petclinic.visit.Visit;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest()
@ExtendWith(MockitoExtension.class)
class PetManagerTest {

	@Mock
	private PetTimedCache pets;

	@Mock
	private OwnerRepository owners;

	@Mock
	private Logger log;


	@Test
	private void testFindOwnerState() {
		// in this test we have used Spy
		// that is a State verification method
		PetManager pm = new PetManager(pets, owners, log);

		Owner o1 = new Owner();
		o1.setId(1);
		OwnerRepository ownersSpy = spy(owners);
		ownersSpy.save(o1);

		assertEquals(pm.findOwner(1), o1);
	}
	// Q3 here
	@Test
	private void testFindOwnerBehavior(@Mock OwnerRepository ownerRepository) {
		// in this test we have used Mock
		// that is a Behavior verification method
		PetManager pm = new PetManager(pets, owners, log);

		Owner o1 = new Owner();
		doReturn(o1).when(ownerRepository).findById(1);

		assertEquals(pm.findOwner(1), o1);
	}

	@Test
	private void testNewPet(@Mock Owner o1) {
		// int this test we have used mocking
		// that is a Behavior verification method
		PetManager pm = new PetManager(pets, owners, log);

		pm.newPet(o1);

		assertEquals(o1.getPets().size(), 1);
	}

	@Test
	private void testFindPet(@Mock PetTimedCache pets) {
		// again the mocking solution is implemented
		PetManager pm = new PetManager(pets, owners, log);

		Pet p1 = new Pet();
		when(pets.get(1)).thenReturn(p1);

		assertEquals(p1, pm.findPet(1));
	}

	@Test
	private void testSavePet(@Mock Pet pet) {
		// here mocking and spy
		PetManager pm = new PetManager(pets, owners, log);
		PetManager pmSpy = spy(pm);
		Owner o1 = new Owner();

		pmSpy.savePet(pet, o1);

		assertEquals(o1.getPets().size(), 1);
	}

	@Test
	private void testGetOwnersPets() {
		// in this function stub spy is used
		// that is a State verification method

		PetManager pm = new PetManager(pets, owners, log);
		Owner o1 = new Owner();
		Owner ownerSpy = spy(o1);
		ownerSpy.setId(1);
		Pet p1 = new Pet();
		Pet p2 = new Pet();

		ownerSpy.addPet(p1);
		ownerSpy.addPet(p2);

		List<Pet> plist = pm.getOwnerPets(1);

		doReturn(plist).when(ownerSpy).getPets();
	}

	@Test
	private void testGetOwnerPetTypes(@Mock Owner owner) {
		// in this function mock is used
		// that is a Behavior verification method

		PetManager pm = new PetManager(pets, owners, log);
		owner.setId(1);
		Pet p1 = new Pet();
		PetType t1 = new PetType();
		p1.setType(t1);

		Pet p2 = new Pet();
		PetType t2 = new PetType();
		p2.setType(t2);

		owner.addPet(p1);
		owner.addPet(p2);

		Set<PetType> petTypes = new HashSet<>();
		petTypes.add(t1);
		petTypes.add(t2);

		Set<PetType> plist = pm.getOwnerPetTypes(1);

		assertEquals(plist, petTypes);
	}

	@Test
	private void testGetVisitsBetween() {
		// in this function dummy is used
		// that is a State verification method
		PetManager pm = new PetManager(pets, owners, log);

		Pet p1 = new Pet();
		p1.setId(1);

		Visit v1 = new Visit();
		v1.setDate(LocalDate.of(2021, 6, 1));
		Visit v2 = new Visit();
		v2.setDate(LocalDate.of(2021, 6, 10));

		p1.addVisit(v1);
		p1.addVisit(v2);


		List<Visit> vList = pm.getVisitsBetween(1, LocalDate.of(2021, 6, 5), LocalDate.of(2021, 6, 15));


		assertEquals(vList.get(0), v2);
	}

}
