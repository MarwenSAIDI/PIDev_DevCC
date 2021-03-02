/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

/**
 *
 * @author Occurence
 */
public class TextFieldException extends Exception {

    public TextFieldException() {
        super();
    }
    public TextFieldException(String message) {
        super(message);
    }
    
    public static void verifEmpty(String text) throws TextFieldException{
        if(text == null || text.trim().isEmpty()){
            throw new TextFieldException("Empty field found !");
        }
    }
    
}
