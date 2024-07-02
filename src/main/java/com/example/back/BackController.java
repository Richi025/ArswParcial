package com.example.back;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
*REST controller for handling data.
*/
@RestController
public class BackController {

    private HashMap<String, String> cache = new HashMap<>();

    @Autowired
    private BackService service;
    
    
    /**
     * Handles GET requests to fetch market data.
     *
     * @param function the function to perform (EJ: TIME_SERIES_INTRADAY)
     * @param symbol the company  symbol(eje: IBM)
     * @param interval the time interval for the data (optional)
     * @return the market data as a JSON string
     */
    @RequestMapping(
            value = "/mercado",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public String getMercado(@RequestParam String function,@RequestParam String symbol, @RequestParam(required = false) String interval){

        String clave =  function + symbol + interval;
        if(cache.get(clave) != null){
            String cacheRespuesta = cache.get(clave);
            System.out.println("cache = " + cacheRespuesta);
            return cacheRespuesta;
        }else{
            String respuesta = service.getMercadoService(function, symbol, interval);
            cache.put(clave, respuesta);
            
            return respuesta;
        }
    
    }
}
