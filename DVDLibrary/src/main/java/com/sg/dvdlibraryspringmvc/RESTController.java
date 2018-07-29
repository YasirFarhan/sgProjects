 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc;

import dao.DvdDao;
import java.util.List;
import javax.inject.Inject;
import model.DvdLibrary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Farhan
 */
@CrossOrigin
@Controller
public class RESTController {

    private DvdDao dao;

    @Inject
    public RESTController(DvdDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DvdLibrary getDvd(@PathVariable("id") long id) {
        return dao.getDvdInfo(id);
    }

    @RequestMapping(value = "/dvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DvdLibrary createDvd(@RequestBody DvdLibrary dvd) {
        return dao.addDvd(dvd); 
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable("id") long id) {
        dao.removeDvd(id);
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDvd(@PathVariable("id") long id, @RequestBody DvdLibrary dvd) {
        dvd.setId(id);
        dao.editDvd(dvd);
    }

    @RequestMapping(value = "/dvds", method = RequestMethod.GET)
    @ResponseBody
    public List<DvdLibrary> getAllDvds() {
        return dao.getAllDvds();
    }
}
