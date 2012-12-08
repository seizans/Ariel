package com.gmail.seizans.algorithm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class PresidentTest {
	@Test
	public void hireAssistantTest() throws Exception {
		int n = 20; // numbers of candidate
		int[] intArray = new int[n];
		for (int i = 0; i < n; i++) {
			intArray[i] = i;
		}
		List<Assistant> list = new ArrayList<Assistant>();
		for (int i = 0; i < n; i++) {
			list.add(new Assistant(intArray[i]) );
		}
		President pres = new President();
		pres.hireAssistant(list);
		//assertThat(pres.evaluateAssistant(assistant), matcher)
	}

	@Test
	public void hireAssistantByArrayTest() throws Exception {
		int n = 20; // numbers of candidate
		int[] intArray = new int[n];
		for (int i = 0; i < n; i++) {
			intArray[i] = i;
		}
		Assistant[] array = new Assistant[n];
		for (int i = 0; i < n; i++) {
			array[i] = new Assistant(intArray[i] );
		}
		President pres = new President();
		pres.hireAssistantByArray(array);
		//assertThat(pres.evaluateAssistant(assistant), matcher)
	}

}
