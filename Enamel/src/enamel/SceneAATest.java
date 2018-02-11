package enamel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class SceneAATest {

	@Test
	public void test01_ConstructorGetQuestion() {
		final String questionConstant = "What letter do the pins display?";
		SceneAA testScene = new SceneAA("", questionConstant, null, null);
		assertEquals(questionConstant, testScene.getQuestion());
	}
	@Test
	public void test02_setQuestion() {
		final String question = "What letter do the pins display?";
		SceneAA testScene = new SceneAA(null, "", null, null);
		assertEquals("", testScene.getQuestion());
		testScene.setQuestion(question);
		assertEquals(question, testScene.getQuestion());
	}
	
	@Test
	public void test03_getSceneName() {
		final String sceneName = "Scene 1";
		SceneAA testScene = new SceneAA("", null, null, null);
		assertEquals("", testScene.getSceneName());
		testScene.setName(sceneName);
		assertEquals(sceneName, testScene.getSceneName());

	}
	
	@Test
	public void test04_getButtonsNull() {
		SceneAA testScene = new SceneAA(null, null, null, null);
		assertEquals(null, testScene.getButtons());
	}
	
	@Test
	public void test05_getButtonsEmpty() {
		List<Integer> buttons = new ArrayList<>();
		SceneAA testScene = new SceneAA(null, null, null, buttons);
		assertEquals(buttons, testScene.getButtons());
	}
	@Test
	public void test06_getButtonsNonEmpty() {
		List<Integer> buttons = Arrays.asList(1,2,3,4,5);
		SceneAA testScene = new SceneAA(null, null, null, buttons);
		assertEquals(buttons, testScene.getButtons());
	}
}


















