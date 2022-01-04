package bdd;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;

public class PetServiceSteps {

	@Autowired
	PetTypeRepository petTypeRepo;
	@Autowired
	OwnerRepository ownerRepo;
	@Autowired
	PetService petService;

	private ArrayList<Owner> owners = new ArrayList<Owner>();
	private ArrayList<Pet> pets = new ArrayList<Pet>();
	private ArrayList<PetType> petTypes = new ArrayList<PetType>();
	private Owner owner;
	private Pet pet;
	private PetType petType;

	private Owner getLocalOwner(String inFirstName) {
		for (Owner o: this.owners) {
			if (o.getFirstName().equals(inFirstName)) {
				return o;
			}
		}
		return null;
	}

	private void addLocalPet(Pet inPet) {
		this.pets.add(inPet);
	}

	private void addLocalPetType(PetType inPetType) {
		this.petType = inPetType;
		this.petTypes.add(inPetType);
		this.petTypeRepo.save(inPetType);
	}

	private void addLocalOwner(Owner inOwner) {
		this.owners.add(inOwner);
		this.ownerRepo.save(inOwner);
	}

	private Pet getLocalPet(String inPetName) {
		for (Pet p: this.pets) {
			if (p.getName().equals(inPetName)) {
				return p;
			}
		}
		return null;
	}

	PetServiceSteps () {
		this.owners = new ArrayList<>();
		this.pets = new ArrayList<>();
		this.petTypes = new ArrayList<PetType>();
	}


	@Given("There is owner {string} {string}")
	public void addOwner(String inFirstName, String inLastName) {
		Owner o1 = new Owner();

		o1.setFirstName(inFirstName);
		o1.setLastName(inLastName);
		o1.setAddress("16 azar street");
		o1.setCity("tehran");
		o1.setTelephone("09213340314");

		this.addLocalOwner(o1);
	}
	@When("Find owner {string} with petService")
	public void getOwner(String inOwnerName) {
		Owner localOwner = this.getLocalOwner(inOwnerName);

		this.owner = petService.findOwner(localOwner.getId());
	}


	@Given("There is pet {string} with owner {string}")
	public void addPet(String inPetName, String inOwnerName) {
		Owner o1 = this.getLocalOwner(inOwnerName);

		Pet p1 = petService.newPet(o1);

		p1.setType(petType);
		p1.setName(inPetName);

		this.addLocalPet(p1);
		this.petService.savePet(p1, o1);
	}
	@When("Find pet {string} with petService")
	public void getPet(String inPetName) {
		Pet localPet = this.getLocalPet(inPetName);

		this.pet = petService.findPet(localPet.getId());
	}


	@Given("There is petType {string}")
	public void addPetType(String petTypeName) {
		PetType pt1 = new PetType();
		pt1.setName(petTypeName);

		this.addLocalPetType(pt1);
	}

	@Then("Returned owner equals {string}")
	public void retOwnerAssertEquals(String inOwnerName) {
		assertEquals(inOwnerName, this.owner.getFirstName());
	}
	@Then("Returned owner not equals {string}")
	public void retOwnerAssertNotEquals(String inOwnerName) {
		assertNotEquals(inOwnerName, this.owner.getFirstName());
	}


	@Then("Returned pet equals {string}")
	public void retPetAssertEquals(String inPetName) {
		assertEquals(inPetName, this.pet.getName());
	}
	@Then("Returned pet not equals {string}")
	public void retPetAssertNotEquals(String inPetName) {
		assertNotEquals(inPetName, this.pet.getName());
	}
}
