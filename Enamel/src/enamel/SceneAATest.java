package enamel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class SceneAATest {

//	@Test
//	public void test01_ConstructorGetQuestion() {
//		final String questionConstant = "What letter do the pins display?";
//		SceneAA testScene = new SceneAA("", questionConstant, null, null);
//		assertEquals(questionConstant, testScene.getQuestion());
//	}
//
//	@Test
//	public void test02_setQuestion() {
//		final String question = "What letter do the pins display?";
//		SceneAA testScene = new SceneAA(null, "", null, null);
//		assertEquals("", testScene.getQuestion());
//		testScene.setQuestion(question);
//		assertEquals(question, testScene.getQuestion());
//	}
//
//	@Test
//	public void test03_getSceneName() {
//		final String sceneName = "Scene 1";
//		SceneAA testScene = new SceneAA("", null, null, null);
//		assertEquals("", testScene.getSceneName());
//		testScene.setName(sceneName);
//		assertEquals(sceneName, testScene.getSceneName());
//
//	}
//
//	@Test
//	public void test04_getButtonsNull() {
//		SceneAA testScene = new SceneAA(null, null, null, null);
//		assertEquals(null, testScene.getButtons());
//	}
//
//	@Test
//	public void test05_getButtonsEmpty() {
//		List<Integer> buttons = new ArrayList<>();
//		SceneAA testScene = new SceneAA(null, null, null, buttons);
//		assertEquals(buttons, testScene.getButtons());
//	}
//
//	@Test
//	public void test06_getButtonsNonEmpty() {
//		List<Integer> buttons = Arrays.asList(1, 2, 3, 4, 5);
//		SceneAA testScene = new SceneAA(null, null, null, buttons);
//		assertEquals(buttons, testScene.getButtons());
//	}
//
//	// testing if the pins correctly transfer from binary to boolean
//	// could do all the letters which is just copy and paste but thats way to much
//	// code for no reason
	@Test
	public void getPinsAsBooleanTest() throws InterruptedException {
		List<BrailleCell> list = new ArrayList<BrailleCell>();
		BrailleCell bCell1 = new BrailleCell();
		bCell1.displayCharacter('a');
		list.add(bCell1);

		List<Boolean> listAsBoolean = new ArrayList<Boolean>();
		listAsBoolean.add(true);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);

		SceneAA s = new SceneAA(5, 3);
		assertEquals(listAsBoolean, s.getPinsAsBoolean(bCell1));

		bCell1.displayCharacter('b');
		List<Boolean> listAsBoolean2 = new ArrayList<Boolean>();
		listAsBoolean2.add(true);
		listAsBoolean2.add(true);
		listAsBoolean2.add(false);
		listAsBoolean2.add(false);
		listAsBoolean2.add(false);
		listAsBoolean2.add(false);
		listAsBoolean2.add(false);
		listAsBoolean2.add(false);
		assertEquals(listAsBoolean2, s.getPinsAsBoolean(bCell1));

		bCell1.displayCharacter('s');
		List<Boolean> listAsBoolean3 = new ArrayList<Boolean>();
		listAsBoolean3.add(false);
		listAsBoolean3.add(true);
		listAsBoolean3.add(true);
		listAsBoolean3.add(true);
		listAsBoolean3.add(false);
		listAsBoolean3.add(false);
		listAsBoolean3.add(false);
		listAsBoolean3.add(false);
		assertEquals(listAsBoolean3, s.getPinsAsBoolean(bCell1));
	}

}
