 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heros;

import Dao.SearchTerm;
import Service.CharacterService;
import Service.LocationService;
import Service.OrganizationService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import model.CharacterDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Farhan
 */
@Controller
public class SearchController {

    CharacterService characterService;
    LocationService locationService;
    OrganizationService orgService;

    @Inject
    public SearchController(CharacterService characterService, LocationService locationService, OrganizationService orgService) {
        this.characterService = characterService;
        this.locationService = locationService;
        this.orgService = orgService;
    }

    @RequestMapping(value = "/search/characters", method = RequestMethod.POST)
    @ResponseBody
    public List<CharacterDetails> searchCharacters(@RequestBody Map<String, String> searchMap) {
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
        String currentTerm = searchMap.get("CharacterName");
        if (currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.CharacterName, currentTerm);
        }
        currentTerm = searchMap.get("CharacterType");
        if (currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.CharacterType, currentTerm);
        }

        currentTerm = searchMap.get("SuperPower");
        if (currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.SuperPower, currentTerm);
        }
        currentTerm = searchMap.get("LocationName");
        if (currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.LocationName, currentTerm);
        }
        currentTerm = searchMap.get("SightingTimeStamp");
        if (currentTerm != null && !currentTerm.isEmpty()) {
            currentTerm = formatDate(currentTerm).toString();
            criteriaMap.put(SearchTerm.SightingTimeStamp, currentTerm);
        }
        currentTerm = searchMap.get("SightingTime");
        if (currentTerm != null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.SightingTime, currentTerm);
        }
        currentTerm = searchMap.get("OrganizationName");
        if (currentTerm != null && !currentTerm.isEmpty()) {

            criteriaMap.put(SearchTerm.OrganizationName, currentTerm);
        }

        List<CharacterDetails> cList = characterService.search(criteriaMap);
        locationService.findCharacterLocations(cList);
        orgService.findCharacterOrganization(cList);
        return cList;
    }

    // helper method 
    private LocalDate formatDate(String dateInString) {
        String year = dateInString.substring(0, 4);
        String month = dateInString.substring(5, 7);
        String day = dateInString.substring(8);
        String formattedDate = month + day + year;
        return LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("MMddyyyy"));
    }
}
