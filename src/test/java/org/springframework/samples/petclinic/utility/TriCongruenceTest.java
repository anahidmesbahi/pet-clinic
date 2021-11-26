package org.springframework.samples.petclinic.utility;

import com.github.mryf323.tractatus.*;
import com.github.mryf323.tractatus.experimental.extensions.ReportingExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(ReportingExtension.class)
class TriCongruenceTest {

	private static final Logger log = LoggerFactory.getLogger(TriCongruenceTest.class);

	@Test
	public void sampleTest() {
		Triangle t1 = new Triangle(2, 3, 7);
		Triangle t2 = new Triangle(7, 2, 3);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@Test
	public void line15Test1() {
		// CC
		// TT
		Triangle t1 = new Triangle(-1, 2, 9);
		Triangle t2 = new Triangle(2, -1, 9);

		boolean areCongruent = TriCongruence.areCongruent(t1, t2);

		Assertions.assertFalse(areCongruent)
	}

	@Test
	public void line15Test2() {
		// CC
		// FF
		Triangle t1 = new Triangle(5, 6, 4);
		Triangle t2 = new Triangle(4, 5, 6);

		boolean areCongruent = TriCongruence.areCongruent(t1, t2);

		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent)
	}

	@Test
	public void line15Test3() {
		// CACC
		// Major t1arr[0] < 0
		// Major is false
		// Can't happen

		Triangle t1 = new Triangle(5, 3, 7);
		Triangle t2 = new Triangle(7, 5, 3);
		
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);

		// Can't happen when major is True
	}

	@Test
	public void line15Test4() {
		// CACC
		// Major (t1arr[0] + t1arr[1] < t1arr[2])
		// Major false
		Triangle t1 = new Triangle(6, 4, 7);
		Triangle t2 = new Triangle(4, 7, 6);
		
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);

		// Major true
		Triangle t3 = new Triangle(2, 9, 2);
		Triangle t4 = new Triangle(2, 2, 9);
		
		boolean areCongruent2 = TriCongruence.areCongruent(t3, t4);
		
		log.debug("Triangles identified as '{}'.", areCongruent2 ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent2);
	}

	@Test
	public void line14Test1() {
		// FFF
		Triangle t1 = new Triangle(1,2,4);
		Triangle t2 = new Triangle(3,5,6);

		boolean areCongruent = TriCongruence.areCongruent(t1,t2);

		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@Test
	public void line14Test1() {
		// TFF
		Triangle t1 = new Triangle(3,4,4);
		Triangle t2 = new Triangle(3,5,6);

		boolean areCongruent = TriCongruence.areCongruent(t1,t2);

		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@Test
	public void line14Test1() {
		// TTF
		Triangle t1 = new Triangle(3,5,7);
		Triangle t2 = new Triangle(3,5,6);

		boolean areCongruent = TriCongruence.areCongruent(t1,t2);

		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}



	/**
	 * TODO
	 * explain your answer here
	 * f = ab + cd
	 * ab = {TTFT, TFFT, FTFT}
	 * cd = {FTTT, FTTF, FTFT}
	 * CUTPNFP = {TTFT, TFFT, FTFT, FTTT, FTTF}
	 * f` = a`c` + a`d` + b`c` + b`d`
	 * prime implicants f = 2, f` = 4
	 * 6 Tests needed
	 * CUTPNFP set has only 5
	 * CUTPNFPhas less so can't satisfy UTPC
	 */
	private static boolean questionTwo(boolean a, boolean b, boolean c, boolean d, boolean e) {
		boolean predicate = false;
//		predicate = a predicate with any number of clauses
		return predicate;
	}
}
