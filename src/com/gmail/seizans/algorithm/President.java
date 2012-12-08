package com.gmail.seizans.algorithm;

import java.util.List;

public class President {
	private Assistant assistant;
	private InterviewResult latestCandidateResult = new InterviewResult();
	
	private void interviewAssistant(Assistant assistant) {
		latestCandidateResult.evaluation = evaluateAssistant(assistant);
	}
	
	private int evaluateAssistant(Assistant assistant) {
		return assistant.getIntelligence();
	}
	
	private boolean isLatestCandidateBetter() {
		return latestCandidateResult.evaluation > evaluateAssistant(assistant);
	}
		
	private void setAssistant(Assistant assistant) {
		this.assistant = assistant;
		System.out.println("Assistant is changed!!");
		System.out.println("New assistant's eval = " + evaluateAssistant(this.assistant));
	}
	
	public void hireAssistant(List<Assistant> list) {
		this.assistant = new Assistant(0);
		for (Assistant assistant: list) {
			interviewAssistant(assistant);
			if (isLatestCandidateBetter()) {
				setAssistant(assistant);
			}
		}
	}
	
	public void hireAssistantByArray(Assistant[] array) {
		Utility.randomizeArray(array);
		int n = array.length;
		this.assistant = new Assistant(0);
		for (int i = 0; i < n; i++) {
			interviewAssistant(array[i] );
			if (isLatestCandidateBetter() ) {
				setAssistant(array[i] );
			}
		}
	}
	
	public int testGetEvaluationOfAssistant() {
		return evaluateAssistant(this.assistant);
	}
	
	private class InterviewResult {
		public int evaluation;
	}

}
