package com.example.back;

/**
 * Implementations of this interface will provide the actual logic to fetch data from the market service.
 */
public interface BackInterface {
    public String getMercadoService(String function,String symbol, String interval);
}  
   

