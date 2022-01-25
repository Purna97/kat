// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.DTO;

import java.util.List;

public class QuestionsAndAnsDTO
{
    private String applicationName;
    private String question;
    private List<String> answer;
    private String correctAnsOption;
    
    public String getApplicationName() {
        return this.applicationName;
    }
    
    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }
    
    public String getQuestion() {
        return this.question;
    }
    
    public void setQuestion(final String question) {
        this.question = question;
    }
    
    public List<String> getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(final List<String> answer) {
        this.answer = answer;
    }

	public String getCorrectAnsOption() {
		return correctAnsOption;
	}

	public void setCorrectAnsOption(String correctAnsOption) {
		this.correctAnsOption = correctAnsOption;
	}
}
