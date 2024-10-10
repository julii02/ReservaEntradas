
package com.Entradas.Entradas.exceptions;

public class EntradasException extends RuntimeException{
   private ExceptionDetails details;

    // Constructor que solo acepta el mensaje de error
    public EntradasException(String message) {
        super(message);
    }

    // Constructor que acepta tanto el mensaje de error como detalles adicionales
    public EntradasException(String message, ExceptionDetails details) {
        super(message);
        this.details = details;
    }
    
    public EntradasException(String message, ExceptionDetails details, Throwable cause) {
        super(message, cause); // Llamada al constructor de RuntimeException
        this.details = details;
    }
   

    public ExceptionDetails getDetails() {
        return details;
    }

    public void setDetails(ExceptionDetails details) {
        this.details = details;
    }
}
