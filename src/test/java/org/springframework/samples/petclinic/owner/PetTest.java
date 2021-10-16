package org.springframework.samples.petclinic.owner;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.visit.Visit;
import static org.junit.Assume.*;


import java.time.LocalDate;

public class PetTest extends TestCase {
	private Pet pet;

	public PetTest() {
		super();
	}

	@BeforeAll
	void setup() {
		this.pet = new Pet();
	}

	public void testGetVisit() throws Exception{
		this.pet = new Pet();

		Visit v1 = new Visit();
		Visit v2 = new Visit();

		assumeTrue(v1 != null);
		assumeTrue(v2 != null);

		this.pet.addVisit(v1);
		this.pet.addVisit(v2);

		this.pet.removeVisit(v2);

		assertTrue(!this.pet.getVisits().contains(v2));

	}
}
