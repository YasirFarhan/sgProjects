/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.heros;

import Service.CharacterService;
import Service.LocationService;
import Service.OrganizationService;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import model.CharacterLocationBridgeTable;
import model.CharacterDetails;
import model.Location;
import model.Organization;
import model.RecentSightings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Farhan
 */
@Controller
public class HeroController {

    CharacterService characterService;
    LocationService locationService;
    OrganizationService orgService;

    @Inject
    public HeroController(CharacterService characterService, LocationService locationService, OrganizationService orgService) {
        this.characterService = characterService;
        this.locationService = locationService;
        this.orgService = orgService;
    }

    @RequestMapping(value = "/DisplayCharacterDetails", method = RequestMethod.GET)
    public String DisplayCharacters(Model model) {
        List<CharacterDetails> characterDetails = characterService.getAllCharacters();
        List<Location> location = locationService.getAllLocation();
        List<Organization> organization = orgService.getAllOrganizations();
        List<RecentSightings> recentSightings = characterService.getRecentSightings();
        locationService.findCharacterLocations(characterDetails);
        orgService.findCharacterOrganization(characterDetails);
        model.addAttribute("characterDetails", characterDetails);
        model.addAttribute("locations", location);
        model.addAttribute("organization", organization);
        model.addAttribute("recentSightings", recentSightings);
        return "SuperHeros";
    }

    @RequestMapping(value = "/DiplayOrganizationPage", method = RequestMethod.GET)
    public String diplayOrganizationPage(Model model) {
        List<Organization> organizationDetails = orgService.getAllOrganizations();
        List<CharacterDetails> c = characterService.getAllCharacters();
        characterService.findOrganizationCharacter(organizationDetails);
        model.addAttribute("organizationDetails", organizationDetails);
        model.addAttribute("characterDetails", c);
        return "Organization";
    }

    @RequestMapping(value = "/DiplayLocationPage", method = RequestMethod.GET)
    public String DiplayLocationPage(Model model) {
        List<Location> locationDetails = locationService.getAllLocation();
        List<CharacterDetails> c = characterService.getAllCharacters();
        characterService.findLocationCharacter(locationDetails);
        model.addAttribute("locationDetails", locationDetails);
        model.addAttribute("characterDetails", c);
        return "Location";
    }

    @RequestMapping(value = "/DiplaySearchPage", method = RequestMethod.GET)
    public String DiplaySearchPage(Model model) {
        return "SearchPage";
    }

    @RequestMapping(value = "createCharacter", method = RequestMethod.POST)
    public String createCharacter(HttpServletRequest request) {
        CharacterDetails c = new CharacterDetails();
        c.setCharacterName(request.getParameter("name"));
        c.setCharacterDescription(request.getParameter("description"));
        c.setSuperPower(request.getParameter("superPower"));
        c.setCharacterType(request.getParameter("characterType"));
        characterService.createCharacter(c);
        return "redirect:DisplayCharacterDetails";
    }

    @RequestMapping(value = "addOrganization", method = RequestMethod.POST)
    public String addOrganization(@ModelAttribute("organization") Organization org, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:DiplayOrganizationPage";
        }
        orgService.createOrganization(org);
        return "redirect:DiplayOrganizationPage";
    }

    @RequestMapping(value = "addLocation", method = RequestMethod.POST)
    public String addLocation(@ModelAttribute("location") Location loc, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:DiplayLocationPage";
        }
        locationService.createLocation(loc);
        return "redirect:DiplayLocationPage";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String orgrIdParameter = request.getParameter("orgId");
        long orgrId = Long.parseLong(orgrIdParameter);
        orgService.removeOrganization(orgrId);
        return "redirect:DiplayOrganizationPage";
    }

    @RequestMapping(value = "/deleteCharacter", method = RequestMethod.GET)
    public String deleteCharacter(HttpServletRequest request) {
        String characterIdParameter = request.getParameter("characterId");
        long characterId = Long.parseLong(characterIdParameter);
        characterService.removeCharacter(characterId);
        return "redirect:DisplayCharacterDetails";
    }

    @RequestMapping(value = "/deleteLocationFromCharacter", method = RequestMethod.GET)
    public String deleteLocationFromCharacter(HttpServletRequest request) {
        String locationIdParameter = request.getParameter("locationId");
        String characterIdParameter = request.getParameter("characterId");
        long locationId = Long.parseLong(locationIdParameter);
        long characterId = Long.parseLong(characterIdParameter);
        characterService.removeLocationFromCharacter(characterId, locationId);
        return "redirect:DisplayCharacterDetails";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIdString = request.getParameter("locationId");
        long LocationId = Long.parseLong(locationIdString);
        locationService.removeLocation(LocationId);
        return "redirect:DiplayLocationPage";
    }

    @RequestMapping(value = "/displayCharacterEditForm", method = RequestMethod.GET)
    public String displayCharacterEditForm(HttpServletRequest request, Model model) {
        String characterIdParameter = request.getParameter("characterId");
        CharacterDetails c = characterService.getSingleCharacter(Long.parseLong(characterIdParameter));
        model.addAttribute("character", c);
        return "EditCharacterForm";
    }

    @RequestMapping(value = "/editCharacter", method = RequestMethod.POST)
    public String editCharacter(@Valid @ModelAttribute("character") CharacterDetails character, BindingResult result) {
        if (result.hasErrors()) {
            return "EditCharacterForm";
        }
        characterService.updateCharacter(character);
        return "redirect:DisplayCharacterDetails";
    }

    @RequestMapping(value = "/displayLocationEditForm", method = RequestMethod.GET)
    public String displayLocationEditForm(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        Location loc = locationService.getSingleLocation(Long.parseLong(locationIdParameter));
        model.addAttribute("location", loc);
        return "EditLocationForm";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location loc, BindingResult result) {
        if (result.hasErrors()) {
            return "EditLocationForm";
        }
        locationService.updateLocation(loc);
        return "redirect:DiplayLocationPage";
    }

    @RequestMapping(value = "/displayOrganizationEditForm", method = RequestMethod.GET)
    public String displayOrganizationEditForm(HttpServletRequest request, Model model) {
        String orgIdParameter = request.getParameter("orgId");
        Organization org = orgService.getSingleOrganization(Long.parseLong(orgIdParameter));
        model.addAttribute("organization", org);
        return "EditOrganizationForm";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization org, BindingResult result) {
        if (result.hasErrors()) {
            return "EditOrganizationForm";
        }
        orgService.updateOrganization(org);
        return "redirect:DiplayOrganizationPage";
    }

    @RequestMapping(value = "/deleteOrgFromCharacter", method = RequestMethod.GET)
    public String deleteOrgFromCharacter(HttpServletRequest request) {
        String orgIdParameter = request.getParameter("orgId");
        String characterIdParameter = request.getParameter("characterId");
        long orgId = Long.parseLong(orgIdParameter);
        long characterId = Long.parseLong(characterIdParameter);
        characterService.removeOrgFromCharacter(characterId, orgId);
        return "redirect:DisplayCharacterDetails";
    }

    @RequestMapping(value = "addCharacterSightingOrganization", method = RequestMethod.POST)
    public String addCharacterSighting(HttpServletRequest request) throws ParseException {
        CharacterLocationBridgeTable bt = new CharacterLocationBridgeTable();

        try {
            bt.setCharacterId(Long.parseLong(request.getParameter("characterId")));
        } catch (NumberFormatException e) {
            return "redirect:DisplayCharacterDetails";
        }

        try {
            bt.setLocationId(Long.parseLong(request.getParameter("locationId")));
            String sightingDateParameter = request.getParameter("sightingDate");
            bt.setSightingDate(formatDate(sightingDateParameter));
            bt.setSightingTime(request.getParameter("sightingTime"));
            characterService.insertCharacterSighting(bt);
        } catch (NumberFormatException e) {
        }
        try {
            bt.setOrgId(Long.parseLong(request.getParameter("organizationId")));
            characterService.insertCharacterOrganization(bt);
        } catch (NumberFormatException e) {
            return "redirect:DisplayCharacterDetails";
        }
        return "redirect:DisplayCharacterDetails";
    }

    @RequestMapping(value = "/displaySearchPage", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "SearchPage";
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
