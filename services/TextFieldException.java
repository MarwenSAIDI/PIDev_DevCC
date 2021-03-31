/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.lang.NumberFormatException;

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
    
    public static void verifInt(String text) throws TextFieldException{
        
        try{
            Integer.parseInt(text);
        }catch(NumberFormatException ex)
        {
            
            throw new TextFieldException("No integer allowed");
        }
    }
    public static void verifDouble(String text) throws TextFieldException{
        
        try{
            Double.parseDouble(text);
        }catch(NumberFormatException ex)
        {
            
            throw new TextFieldException("No double allowed");
        }
    }
    
    
    
    
    public static void verifString(String text) throws TextFieldException{
        String clean = text.replaceAll("\\D+","");
        if(!clean.trim().isEmpty()){
            throw new TextFieldException("No integer allowed !");
        }
    }
    
}
