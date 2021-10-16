package org.springframework.samples.petclinic.owner;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.samples.petclinic.utility.SimpleDI;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceTest extends TestCase {
	private PetService petService;

	public PetServiceTest () {
		super();
	}

	@BeforeEach
	static void setup() {
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // six numbers
	public void testFindPet(int petId){
		this.petService = new PetService(null, null, null);

		Pet p1 = this.petService.findPet(petId);

		assertTrue(p1 != null);
	}

}
