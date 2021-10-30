package com.intellias.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class StudentApplicationResults implements JavaDelegate {

    static {
        System.out.println("Hello guys");
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("execute");

        String firstName = (String) delegateExecution.getVariable("firstName");
        String lastName = (String) delegateExecution.getVariable("lastName");

        System.out.println(firstName + " " + lastName);

        String ieltsScoreString = "0.0";
        ieltsScoreString = (String) delegateExecution.getVariable("ieltsScore");

        double ieltsScore = Double.parseDouble(ieltsScoreString);

        System.out.println(ieltsScore);

        if(ieltsScore >= 6.5){

            String examSubjects = (String) delegateExecution.getVariable("subject");

            int examScore = (Integer) delegateExecution.getVariable("score");
            if(examScore >= 86){

                delegateExecution.setVariable("applicationResult", "Candidate accepted");

            }

            else{ // examScore < 86
                delegateExecution.setVariable("applicationResult", "Candidate rejected due to low Exam score");
            }
        }
        else{ // ieltsScore < 6.5
            System.out.println("Candidate rejected due to low IELTS score");
            delegateExecution.setVariable("applicationResult", "Candidate rejected due to low IELTS score");
        }
    }
}
