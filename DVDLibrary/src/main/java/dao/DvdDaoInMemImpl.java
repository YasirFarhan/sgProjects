 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.DvdLibrary;


/**
 *
 * @author Farhan
 */ 
public class DvdDaoInMemImpl implements DvdDao {

    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    private static long id = 1;

    private Map<Long, DvdLibrary> dvdRecords = new HashMap<>();

    @Override
    public DvdLibrary addDvd(DvdLibrary information) {
        id++;
        information.setId(id);

        DvdLibrary newDvd = dvdRecords.put(id, information);
        return newDvd;
    }

    @Override
    public List<DvdLibrary> getAllDvds() {
        return new ArrayList<>(dvdRecords.values());
    }

    @Override
    public DvdLibrary getDvdInfo(Long id) {
        return dvdRecords.get(id);
    }

    @Override
    public void removeDvd(Long id) {
        dvdRecords.remove(id);

    }

    @Override
    public DvdLibrary editDvd(DvdLibrary information) {
        DvdLibrary editDvdRecord = dvdRecords.put(information.getId(), information);
        return editDvdRecord;
    }

    @Override
    public List<DvdLibrary> getDvdByAge(int ageInYears) {
        return dvdRecords.values()
                .stream()
                .filter(s -> s.getDvdAge() < ageInYears)
                .collect(Collectors.toList());
    }

    @Override
    public List<DvdLibrary> getDvdByMPAARating(String MPAARating) {
        return null;
//        return dvdRecords.values()
//                .stream()
//                .filter(s -> s.getRating().equalsIgnoreCase(MPAARating))
//                .collect(Collectors.toList());
    }

//    @Override
//    public Map<String, List<DvdLibrary>> groupDvdByMPAARatingandByDirector(String MPAARating) {
//        return dvdRecords.values()
//                .stream()
//                .filter(s -> s.getRating().equalsIgnoreCase(MPAARating))
//                .collect(Collectors.groupingBy(DvdLibrary::getDirectorName));
//    }
//    @Override
//    public List<DvdLibrary> getDvdByStudio(String Studio) {
//        return dvdRecords.values()
//                .stream()
//                .filter(s -> s.getStudio().equalsIgnoreCase(Studio))
//                .collect(Collectors.toList());
//    }
}
