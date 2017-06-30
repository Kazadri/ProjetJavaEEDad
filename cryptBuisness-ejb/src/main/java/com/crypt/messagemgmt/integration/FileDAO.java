package com.crypt.messagemgmt.integration;

import com.crypt.messagemgmt.model.File;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author imtek
 */
@Stateless
public class FileDAO {

    @PersistenceContext(unitName = "FilePU")
    private EntityManager em;
    
     //stockage d'un mot en base
    public File insert(File f){
        em.persist(f);
        em.flush();
        return f;
    }
    
    //suppression d'un fichier
    public Boolean delete(Long id){

        if(id != null){
            File f = em.find(File.class, id);
            if( f != null){
                em.remove(f);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
          
    }
    
    //rechercher d'un File
    public List<File> find(String pattern){
       
        if (pattern != null && !pattern.isEmpty()) {
            TypedQuery<File> query = (TypedQuery<File>) 
                                     em.createQuery("SELECT f FROM File f WHERE w.nameFile LIKE :pattern")
                                       .setParameter("pattern", "%" + pattern + "%");
            List<File> words = query.getResultList();
          
            return words;
        }
        return null;
    }
    
    //liste de tous les mots
    public List<File> getAllStoredFiles(){
        return em.createQuery("SELECT f FROM File f")
                 .getResultList();
    } 
    
}
