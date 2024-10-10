
package com.Entradas.Entradas.exceptions;


public class ExceptionDetails {
    private String userMessage;    // Mensaje para mostrar al usuario
    private String severity;       // Nivel de severidad del error (ej: "Error", "Advertencia")

    public ExceptionDetails(String userMessage, String severity) {
        this.userMessage = userMessage;
        this.severity = severity;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
