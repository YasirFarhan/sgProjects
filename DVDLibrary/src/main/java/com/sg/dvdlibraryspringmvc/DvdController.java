 package com.sg.dvdlibraryspringmvc;

import dao.DvdDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import model.Director;
import model.DvdLibrary;
import model.Rating;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Farhan
 */
@Controller
public class DvdController {

    DvdDao dao;

    @Inject
    public DvdController(DvdDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayDvds", method = RequestMethod.GET)
    public String displayDvdsPage(Model model) {
        List<DvdLibrary> dvdList = dao.getAllDvds();
        model.addAttribute("dvdList", dvdList);
        return "dvds";
    }

    @RequestMapping(value = "/displayNewDvdForm")
    public String displayNewDvdForm() {
        return "createDvd";
    }

    @RequestMapping(value = "/deleteDvd", method = RequestMethod.GET)
    public String deleteDvd(HttpServletRequest request) {
        String dvdTitleParameter = request.getParameter("id");
        dao.removeDvd(Long.parseLong(dvdTitleParameter));
        return "redirect:displayDvds";
    }

    @RequestMapping(value = "/displayEditeditDvdForm", method = RequestMethod.GET)
    public String displayEditDvd(HttpServletRequest request, Model model) {
        String dvdId = request.getParameter("id");
        DvdLibrary dvd = dao.getDvdInfo(Long.parseLong(dvdId));
        model.addAttribute("dvd", dvd);
        return "editDVD";
    }

    @RequestMapping(value = "/editDvd", method = RequestMethod.POST)
    public String updateDvd(@Valid @ModelAttribute("dvd") DvdLibrary dvd, BindingResult result) {
        if (result.hasErrors()) {
            return "editDVD";
        }
        dao.editDvd(dvd);
        return "redirect:displayDvds";
    }
 
    @RequestMapping(value = "/addDvd", method = RequestMethod.POST)
    public String createDvd(@Valid HttpServletRequest request) throws ParseException //            throws ParseException, DvdLibraryPersistenceException 
    {
        DvdLibrary dvd = new DvdLibrary();
        Director director = new Director();
        Rating rating = new Rating();

        dvd.setTitle(request.getParameter("title"));

        try {

            String releaseYear = request.getParameter("releaseYear");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
            Date date = formatter.parse(releaseYear);
            LocalDate locaDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            dvd.setReleaseDate(locaDate);
        } catch (Exception e) {
            return "redirect:displayDvds";
        }

        director.setFirstName(request.getParameter("director"));
        dvd.setDirector(director);
        rating.setRating(request.getParameter("rating"));
        dvd.setRating(rating);
        dvd.setNotes(request.getParameter("notes"));
        dao.addDvd(dvd);
        return "redirect:displayDvds";
    }
}
