package com.example.ds_interface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class HelloApplication extends Application {

    public static boolean arePair(char open, char close) {
        if (open == '(' && close == ')') {
            return true;
        } else if (open == '{' && close == '}') {
            return true;
        } else return open == '[' && close == ']';
    }
    public static boolean areBalanced(String exp) {
        Stack<Character> S = new Stack<>();
        int length = exp.length();
        for (int i = 0; i < length; i++) {
            if (exp.charAt(i) == '(' || exp.charAt(i) == '{' || exp.charAt(i) == '[') {
                S.push(exp.charAt(i));
            } else if (exp.charAt(i) == ')' || exp.charAt(i) == '}' || exp.charAt(i) == ']') {
                if (S.empty() || !arePair(S.peek(), exp.charAt(i))) {
                    return false;
                } else {
                    S.pop();
                }
            }
        }
        return S.empty();
    }
    private static boolean isOperand(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }
    public static Integer evaluatePostfix(ArrayList<String> postfix){
        boolean f=true;
        Stack<Integer> stack = new Stack<>();
        for (String nextcharacter:postfix) {
            if (isOperand(nextcharacter))
                stack.push(Integer.parseInt(nextcharacter));
            else {
                int value1 = stack.pop();
                int value2 = stack.pop();
                int result =0;

                if (nextcharacter.equals("+")){
                    result=value2+value1;
                    stack.push(result);
                }
                if (nextcharacter.equals("-")){
                    result=value2-value1;
                    stack.push(result);
                }
                if (nextcharacter.equals("*")){
                    result=value2*value1;
                    stack.push(result);
                }
                if (nextcharacter.equals("/")){
                    if (value1==0) {
                        Alert alert=new Alert(Alert.AlertType.ERROR, "Cannot Divide by zero");
                        alert.setTitle("Invalid operation");
                        alert.showAndWait();
                        f=false;
                        break;
                    }
                    result=value2/value1;
                    stack.push(result);
                }
                if (nextcharacter.equals("^")){
                    result= (int) Math.pow(value2,value1);
                    stack.push(result);
                }
            }
        }
        if (f)
            return stack.peek();
        return -1;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Label enter = new Label("Enter Your Exp");
        TextField infixTF = new TextField();
        infixTF.setPromptText("Expression");
        Button ShowPostfix = new Button("Show Postfix");
        Button evaluate = new Button("Evaluate");
        Button clear = new Button("Clear");
        TextField outPut = new TextField();
        outPut.setPromptText("Output");

        GridPane gridPane = new GridPane();
        gridPane.add(enter , 0, 0,2, 1);
        gridPane.add(infixTF, 0, 1);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));

        HBox hBox=new HBox(ShowPostfix,evaluate);
        hBox.setSpacing(15);

        GridPane gridPane1=new GridPane();
        gridPane1.add(gridPane , 0, 0,2, 1);
        gridPane1.add(hBox,0,1);
        gridPane1.add(outPut, 0, 2);
        gridPane1.add(clear,0,3);
        gridPane1.setAlignment(Pos.CENTER);
        gridPane1.setHgap(10);
        gridPane1.setVgap(10);
        gridPane.setPadding(new Insets(10));

        //Action of Showpostfix button
        ShowPostfix.setOnAction((ActionEvent event) -> {

            String result="";
            String exp=infixTF.getText();
            if (areBalanced(exp)) {
                StackCharacter stackArray = new StackCharacter();
                char character;
                ArrayList<String> arrayList = new ArrayList<>();
                for (int i = 0; i < exp.length(); i++) {
                    character = exp.charAt(i);
                    //*******
                    if (Character.isDigit(character) || character == '.') {
                        StringBuilder sb = new StringBuilder();
                        while (i < exp.length() && (Character.isLetter(exp.charAt(i)) || Character.isDigit(exp.charAt(i)) || exp.charAt(i) == '.')) {
                            sb.append(exp.charAt(i));
                            i++;
                        }
                        arrayList.add(sb.toString());
                        i--;
                    }
                    else if (character == '(') {
                        stackArray.push(character);
                    }
                    else if (character == ')') {
                        while (!stackArray.isEmpty() && stackArray.peek() != '(') {
                            char ch = stackArray.pop();
                            arrayList.add(Character.toString(ch));
                        }
                        if (!stackArray.isEmpty() && stackArray.peek() == '(') {
                            stackArray.pop();
                        }
                    }
                    else {
                        while (!stackArray.isEmpty() && stackArray.peek() != '(' && precedence(character) <= precedence(stackArray.peek())) {
                            char ch = stackArray.pop();
                            arrayList.add(Character.toString(ch));
                        }
                        stackArray.push(character);
                    }
                }
                while (!stackArray.isEmpty()) {
                    char ch = stackArray.pop();
                    arrayList.add(Character.toString(ch));
                }
                for (String i : arrayList) {
                    result += i;
                }
                outPut.setText(result);

        }
        else {
        Alert alert= new Alert(Alert.AlertType.ERROR, "Mismatched parentheses");
                alert.setTitle("Invalid");
                alert.showAndWait();
        }
        });

        //Action of Evaluate button
        evaluate.setOnAction((ActionEvent event) -> {

            String exp = infixTF.getText();
            if (areBalanced(exp)) {
                StackCharacter stackArray = new StackCharacter();
                char character;
                ArrayList<String> arrayList = new ArrayList<>();
                for (int i = 0; i < exp.length(); i++) {
                    character = exp.charAt(i);
                    if (Character.isDigit(character) || character == '.') {
                        StringBuilder sb = new StringBuilder();
                        while (i < exp.length() && (Character.isLetter(exp.charAt(i)) || Character.isDigit(exp.charAt(i)) || exp.charAt(i) == '.')) {
                            sb.append(exp.charAt(i));
                            i++;
                        }
                        arrayList.add(sb.toString());
                        i--;
                    }
                    else if (character == '(') {
                        stackArray.push(character);
                    }
                    else if (character == ')') {
                        while (!stackArray.isEmpty() && stackArray.peek() != '(') {
                            char ch = stackArray.pop();
                            arrayList.add(Character.toString(ch));
                        }
                        if (!stackArray.isEmpty() && stackArray.peek() == '(') {
                            stackArray.pop();
                        } else {
                            Alert a1 = new Alert(Alert.AlertType.ERROR, "Mismatched parentheses");
                        }
                    }
                    else {
                        while (!stackArray.isEmpty() && stackArray.peek() != '(' && precedence(character) <= precedence(stackArray.peek())) {
                            char ch = stackArray.pop();
                            arrayList.add(Character.toString(ch));
                        }
                        stackArray.push(character);
                    }
                }
                while (!stackArray.isEmpty()) {
                    char ch = stackArray.pop();
                    arrayList.add(Character.toString(ch));
                }
                int result = evaluatePostfix(arrayList);
                outPut.setText(String.valueOf(result));
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Mismatched parentheses or Additional operator");
                alert.setTitle("Invalid");
                alert.showAndWait();
            }
        });

        // Action of clear button
        clear.setOnAction((ActionEvent event) -> {
            infixTF.setText("");
            outPut.setText("");
        });

        Scene scene = new Scene(gridPane1, 320, 240);
        scene.getStylesheets().add(new File("src/main/java/com/example/ds_interface/Scene.css").toURI().toString());
        stage.setTitle("Data Structure Project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

//        StackCharacter stackCharacter=new StackCharacter(10);
//        stackCharacter.push('1');
//        stackCharacter.push('2');
//        stackCharacter.push('3');
//        stackCharacter.push('4');
//        System.out.println(stackCharacter.pop());
//        System.out.println(stackCharacter.peek());
//        String ch="";
//        for (int i=0;i<stackCharacter.getSize();i++){
//            ch +=stackCharacter.pop();
//        }
//        System.out.println(ch);
        launch();
    }
}