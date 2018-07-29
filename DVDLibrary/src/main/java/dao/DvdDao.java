 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DvdLibrary;

/**
 *
 * @author Farhan
 */
public interface DvdDao {

    DvdLibrary addDvd(DvdLibrary information);

    List<DvdLibrary> getAllDvds();

    DvdLibrary getDvdInfo(Long id);

    public void removeDvd(Long id);

    DvdLibrary editDvd(DvdLibrary Information);

    public List<DvdLibrary> getDvdByAge(int ageInYears);

    public List<DvdLibrary> getDvdByMPAARating(String MPAARating);

}
