package br.com.api.fatec.apifatec.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {

	//http://localhost:8090/api/exercicios/animals/cachorro,gato
	@GetMapping("/animals/{types}")
    public List<String> getAnimalSounds(@PathVariable String[] types) {
     
		
        List<String> sounds = new ArrayList<>();
        
        //FORMA 1: USANDO FOR
        for (String type : types) {
            sounds.add(getSoundByAnimalType(type));
        }
        return sounds;
        
        //FORMA 2: USANDO MAP
        /*return Arrays.stream(types)
        .map(this::getSoundByAnimalType)
        .collect(Collectors.toList());*/
        
    }
	
	private String getSoundByAnimalType(String type) {
        switch (type.toLowerCase()) {
            case "cachorro":
                return "AuAu";
            case "gato":
                return "Miauu";
            case "vaca":
                return "Moo";
            default:
                return "animal desconhecido";
        }
    }
	
}
