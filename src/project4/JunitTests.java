package project4;

import static org.junit.Assert.*;

import org.junit.Test;

public class JunitTests {

	@Test 
	public void testUndoProcessCommand() {
		// Creates the file for testing, 
		// this code could be removed if the file already exist
		Mix message = new Mix();
		message.setInitialMessage ("This is a secret message");
		message.processCommand("b a 2");
		message.processCommand("s testIt");

		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile ("testIt", "Tahis is a secret message");
		assertEquals(original, "This is a secret message");
	}
	
	@Test
	public void delete(){
		Mix message = new Mix();
		message.setInitialMessage ("Hello");
		message.processCommand("r 3");
		message.processCommand("s testIt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile ("testIt", "Helo");
		assertEquals(original, "Hello");
	}
	
	@Test
	public void testCut(){
		Mix message = new Mix();
		message.setInitialMessage ("This is a secret message");
		message.processCommand("x 3 5");
		message.processCommand("s testIt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile ("testIt", "This a secret message");
		assertEquals(original, "This is a secret message");
	}
	
	@Test
	public void TestCutPaste(){
		Mix message = new Mix();
		message.setInitialMessage ("This is a secret message");
		message.processCommand("x 1 3");
		message.processCommand("p 21");
		message.processCommand("s testIt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile ("testIt", "s is ecret messageThia s");
		assertEquals(original, "This is a secret message");
	}
}
