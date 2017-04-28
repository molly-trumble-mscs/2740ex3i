package ex3i;

import javax.swing.DefaultListModel;

public class DriverExam {
	private char[] answers;
	private char[] responses;
	private final double requiredPCT= 0.7;
	
	public DriverExam(char[] answers){
		int i = 0;
		this.answers = new char[answers.length];
		for (i=0; i<answers.length; i++){
			 this.answers[i] = answers[i];
		}
	}
	
	public DriverExam(DefaultListModel answers){
		this.answers = new char[answers.getSize()];
		for(int i=0; i<answers.getSize(); i++){
			String r = (String) answers.get(i);
			this.answers[i] = r.charAt(0);
		}
	}

	public void setResponses(DefaultListModel responses){
		this.responses = new char[responses.getSize()];
		for(int i=0; i<responses.getSize(); i++){
			String r = (String) responses.get(i);
			this.responses[i] = r.charAt(0);
		}
	}
	
	public DefaultListModel getAnswers(){
		DefaultListModel answersListModel = new DefaultListModel();
		
		for(int i=0; i<this.answers.length; i++){
			String answer = String.valueOf(this.answers[i]);
			answersListModel.addElement(answer);
		}
		
		return answersListModel;
	}
	
	public int validate(){
		int i = 0, invalid = -1;
		while(i < responses.length){
			if (responses[i] != 'A' && responses[i] != 'B' && responses[i] != 'C' && responses[i] != 'D')
				invalid = i;
			i++;
		}
		return invalid;
	}
	
	public boolean passed(){
		boolean pass = false;
		if (totalCorrect() >= requiredPCT * answers.length)
			pass = true;
		return pass;
	}
	
	public int totalCorrect(){
		int i = 0, correct = 0;
		
		for (i=0; i<answers.length; i++){
			if(responses[i] == answers[i])
				correct++;
		}
		return correct;
	}

	public int totalIncorrect(){
		int i = 0, incorrect = 0;
		
		for (i=0; i<answers.length; i++){
			if(responses[i] != answers[i])
				incorrect++;
		}
		return incorrect;
	}
	
	public int[] questionsMissed(){
		int[] missedQs = {0,0,0,0,0,0,0,0,0,0};
		int i=0, m=0;
		
		for (i=0; i<missedQs.length; i++){
			if(responses[i] != answers[i]){
				missedQs[m] = i+1;
				m++;
			}
		}
		return missedQs;
	}
}
