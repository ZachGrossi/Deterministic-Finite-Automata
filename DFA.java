/****************************
* Title:    DFA.java        *
* Name:     Zach Grossi     *
* Date:     10/01/2013      *
*                           *
*---------------------------*
*                           *
* Language Specs.:          *
* r = {a,b,c,d,...,y,z}     *
* E = r U {.}               *
*                           *
* S1 = {www.}               *
* S2 = rr*                  *
* S3 = {.org}               *
*                           *
* L1 = S1S2S3               *
* L2 = S2S3                 *
*                           *
* L = L1 U L2               *
*                           *
*---------------------------*
*                           *
* Input Strings:            *
* www.organization.org  - Y *
* www.or.org            - Y *
* www.x.org             - Y *
* njit.org              - Y *
* wwww.stanford.org     - N *
* www.org.org           - Y *
* ww.org.org            - N *
* or.org                - Y *
* www.www.org.org       - N *
* ww.org                - Y *
* www.org               - Y *
* wwd.org               - Y *
* wwwb.org              - Y *
* www..org              - N *
* www.abcd.orga         - N *
*                           *
****************************/
import java.util.Scanner;

public class DFA{
    public static void main(String[] args){
        while(true){
            //initialize an object to read in strings
            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to enter a string? (y/n): ");
            String question = scanner.nextLine();

            //check input for yes to continue or no to exit
            if(question.equals("n")){
                System.exit(0);
            }
            else if(question.equals("y")){
                //ask for input and then process the inputted string
                System.out.println("Please enter a string: ");
                String inputString = scanner.nextLine();
                processInput(inputString);
            }else{
                System.out.println("Invalid response.");
            }
        }
    }

    public static void processInput(String s){
        //initialize the input, the start state (1) and the accept/reject var
        String input = s.toLowerCase(); //input:        self explanatory - the inputted string
        int curState = 1;               //currentState: the current DFA state we are in as we process input
        int nextState = 1;              //nextState:    the next DFA state that we travel to as we process input
        int index = 0;                  //index:        the position index of the string being processed

        System.out.println("Start - State: q" + curState);  //output start state

        String language = "abcdefghijklmnopqrstuvwxyz";
        String langNoW = "abcdefghijklmnopqrstuvxyz";
        String langNoO = "abcdefghijklmnpqrstuvwxyz";
        String langNoR = "abcdefghijklmnopqstuvwxyz";
        String langNoG = "abcdefhijklmnopqrstuvwxyz";

        for(index = 0; index < input.length(); index++){
            //assign character to variable for easy reference
            char curChar = input.charAt(index);

            switch (curState){
                //state q1
                case 1:
                    //if character is w, go to next state
                    if(curChar == 'w'){
                        nextState = 2;
                    //if character is ., go to trap state
                    }else if(curChar == '.'){
                        nextState = 11;
                    }else{
                        //if character is other accepted character, go to correct state
                        if (langNoW.indexOf(String.valueOf(curChar)) != -1){
                            nextState = 6;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q2
                case 2:
                    //if character is w, go to next state
                    if(curChar == 'w'){
                        nextState = 3;
                    //if character is ., go to correct state
                    }else if(curChar == '.'){
                        nextState = 7;
                    }else{
                        //if character is other accepted character, go to correct state
                        if (langNoW.indexOf(String.valueOf(curChar)) != -1){
                            nextState = 6;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q3
                case 3:
                    //if character is w, go to next state
                    if(curChar == 'w'){
                        nextState = 4;
                    //if character is ., go to correct state
                    }else if(curChar == '.'){
                        nextState = 7;
                    }else{
                        //if character is other accepted character, go to correct state
                        if (langNoW.indexOf(String.valueOf(curChar)) != -1){
                            nextState = 6;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q4
                case 4:
                    //if character is ., go to next state
                    if(curChar == '.'){
                        nextState = 5;
                    }else{
                        //if character is other accepted character, go to correct state
                        if (language.indexOf(String.valueOf(curChar)) != -1){
                            nextState = 6;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q5
                case 5:
                    //if character is o, go to next state
                    if(curChar == 'o'){
                        nextState = 12;
                    //if character is ., go to trap state
                    }else if(curChar == '.'){
                        nextState = 11;
                    }else{
                        //if character is other accepted character, go to correct state
                        if (langNoO.indexOf(String.valueOf(curChar)) != -1){
                            nextState = 6;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q6
                case 6:
                    //if character is ., go to next state
                    if(curChar == '.'){
                        nextState = 7;
                    }else{
                        //if character is other accepted character, go to correct state
                        if (language.indexOf(String.valueOf(curChar)) != -1){
                            nextState = 6;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q7
                case 7:
                    //if character is o, go to next state
                    if(curChar == 'o'){
                        nextState = 8;
                    }else{
                        //if character is other accepted character or ., go to trap state
                        if ((langNoO.indexOf(String.valueOf(curChar)) != -1) || (curChar == '.')){
                            nextState = 11;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q8
                case 8:
                    //if character is r, go to next state
                    if(curChar == 'r'){
                        nextState = 9;
                    }else{
                        //if character is other accepted character or ., go to trap state
                        if ((langNoR.indexOf(String.valueOf(curChar)) != -1) || (curChar == '.')){
                            nextState = 11;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q9
                case 9:
                    //if character is g, go to next state
                    if(curChar == 'g'){
                        nextState = 10;
                    }else{
                        //if character is other accepted character or ., go to trap state
                        if ((langNoG.indexOf(String.valueOf(curChar)) != -1) || (curChar == '.')){
                            nextState = 11;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q10
                case 10:
                    //if character is any accepted character or ., go to trap state
                    if ((language.indexOf(String.valueOf(curChar)) != -1) || (curChar == '.')){
                        nextState = 11;
                    }else{
                        System.out.println("Current character not an accepted character");
                    }
                    break;

                //state q11
                case 11:
                    //if character is any accepted character or ., go to trap state
                    if ((language.indexOf(String.valueOf(curChar)) != -1) || (curChar == '.')){
                        nextState = 11;
                    }else{
                        System.out.println("Current character not an accepted character");
                    }
                    break;

                //state q12
                case 12:
                    //if character is r, go to next state
                    if(curChar == 'r'){
                        nextState = 13;
                    //if character is ., go to correct state
                    }else if(curChar == '.'){
                        nextState = 7;
                    }else{
                        //if character is other accepted character, go to correct state
                        if (langNoR.indexOf(String.valueOf(curChar)) != -1){
                            nextState = 6;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q13
                case 13:
                    //if character is g, go to next state
                    if(curChar == 'g'){
                        nextState = 14;
                    //if character is ., go to correct state
                    }else if(curChar == '.'){
                        nextState = 7;
                    }else{
                        //if character is other accepted character, go to correct state
                        if (langNoG.indexOf(String.valueOf(curChar)) != -1){
                            nextState = 6;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;

                //state q14
                case 14:
                    //if character is ., go to next state
                    if(curChar == '.'){
                        nextState = 7;
                    }else{
                        //if character is other accepted character or ., go to trap state
                        if (language.indexOf(String.valueOf(curChar)) != -1){
                            nextState = 6;
                        }else{
                            System.out.println("Current character not an accepted character");
                        }
                    }
                    break;
            }

            //move from the current state to the next state
            curState = nextState;

            //output the letter and the current state that we are in
            System.out.println(input.charAt(index) + " - State: q" + curState);
        }

        //check the end state for whether or not it is and accept state
        if ((curState == 10) || (curState == 14)){
            System.out.println("The string " + input + " is in the language.");
        }else{
            System.out.println("The string " + input + " is NOT in the language.");
        }
    }
}