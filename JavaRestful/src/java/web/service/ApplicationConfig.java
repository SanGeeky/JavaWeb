/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author sangeeky
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(web.service.AniosFacadeREST.class);
        resources.add(web.service.CursosFacadeREST.class);
        resources.add(web.service.EstudianteMateriasFacadeREST.class);
        resources.add(web.service.EstudiantesFacadeREST.class);
        resources.add(web.service.MateriasFacadeREST.class);
        resources.add(web.service.PeriodosFacadeREST.class);
    }
    
}
