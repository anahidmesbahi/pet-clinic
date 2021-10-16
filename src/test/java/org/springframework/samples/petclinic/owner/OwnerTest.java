package org.springframework.samples.petclinic.owner;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.samples.petclinic.visit.Visit;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(Owner.class)
public class OwnerTest extends TestCase {
	Owner owner;

	public OwnerTest() {
		super();
	}

	@BeforeAll
	void setup() {
		this.owner = new Owner();
	}

	@Test
	public void testGetAddress() throws Exception{
		owner = new Owner();

		owner.setAddress("a");

		assertTrue("Owners address should be set as a",
			owner.getAddress().equals("a"));
	}

	@Test
	public void testGetCity() throws Exception {
		owner = new Owner();

		owner.setCity("a");

		assertTrue("Owners City should be set as a",
			owner.getCity().equals("a"));
	}

	@Test
	public void testGetTelephone() throws Exception {
		owner = new Owner();

		owner.setTelephone("09121231212");

		assertTrue("Owners telephone should be set as 09121231212",
			owner.getTelephone().equals("09121231212"));
	}

	@Test
	public void testGetPets() throws Exception {
		owner = new Owner();

		Pet p1 = new Pet();
		Pet p2 = new Pet();
		Pet p3 = new Pet();
		owner.addPet(p1);
		owner.addPet(p2);
		owner.addPet(p3);

		assertTrue("Owners should have 3 pets",
			owner.getPets().size() == (3));
	}

	@Test
	public void testRemovePets() throws Exception {
		owner = new Owner();

		Pet p1 = new Pet();
		Pet p2 = new Pet();
		Pet p3 = new Pet();
		owner.addPet(p1);
		owner.addPet(p2);
		owner.addPet(p3);

		owner.removePet(p2);


		assertTrue("Owners should not have p2",
			!(owner.getPets().contains(p2)));
	}

	@Test
	public void testGetPet() throws Exception {
		owner = new Owner();

		Pet p1 = new Pet();
		p1.setName("Looky");

		Pet p2 = new Pet();
		p2.setName("Treny");

		owner.addPet(p1);
		owner.addPet(p2);

		Pet gotPet = owner.getPet("Looky");
		assertTrue("Should return Looky",
			gotPet.getName().equals(p1.getName()));

	}

	@Test
	public void testGetPetNotNew() throws Exception {
		owner = new Owner();

		Pet p1 = new Pet();
		p1.setName("Looky");
		p1.setId(null);

		owner.addPet(p1);

		Pet gotPet = owner.getPet("Looky", true);

		assertNull("Should not return Looky",
			gotPet);

	}

	@Theory
	public void testGetPetTheory () throws Exception {
		owner = new Owner();
		Pet p1 = new Pet();

		Visit v1 = new Visit();
		p1.addVisit(v1);

		assumeTrue(v1 != null);
		assumeTrue(owner != null);
		assumeTrue(p1.getVisits().contains(v1));

		p1.removeVisit(v1);
		p1.addVisit(v1);

		assertTrue(p1.getVisits().contains(v1));

	}
}
