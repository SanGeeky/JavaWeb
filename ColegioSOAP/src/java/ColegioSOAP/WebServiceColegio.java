/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColegioSOAP;

import java.sql.*;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sange
 */
@WebService(serviceName = "WebServiceColegio")
public class WebServiceColegio extends Conexion {

    private final Conexion conexion_db = new Conexion();

    private final Connection db = conexion_db.pruebaconexion();
    
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") final String txt) {
        conexion_db.pruebaconexion();
        return "Hello " + txt + " !";
    }

    

    /**
     * Web service operation
     * 
     * @param codigo
     * @return
     */
    @WebMethod(operationName = "ConsultarEstudiantePorCodigo")
    public String ConsultarEstudiantePorCodigo(@WebParam(name = "codigo") String codigo) {
        
        String resultado_consulta = "";
        
        try{ 
            if (db != null) {

                System.out.println("la conexion fue realizada con exito");

                Statement sentencia = db.createStatement();
                ResultSet Rs = sentencia.executeQuery(
                    "SELECT estudiantes.id_estudiante, nombres, apellidos, cursos.descripcion, avg(nota) "
                    + "FROM estudiantes inner join matriculas "
                    + "on estudiantes.id_estudiante = matriculas.id_estudiante inner join  cursos "
                    + "on matriculas.id_curso = cursos.id_curso INNER JOIN anios "
                    + "on matriculas.id_anio = anios.id_anio INNER JOIN estudiante_materias "
                    + "on estudiantes.id_estudiante=estudiante_materias.id_estudiante inner join calificaciones "
                    + "on estudiante_materias.id_estudiante_materia = calificaciones.id_estudiante_materia "
                    + "WHERE TRIM(estudiantes.id_estudiante) = '" + codigo.trim() + "'" + " and anios.actual = '1'");

                
                while (Rs.next()) 
                {
                    System.out.println( " Nombres: " + Rs.getString(2) + " Apellidos: "
                                + Rs.getString(3) + " Materia: " + Rs.getString(4) + " Nota: " + Rs.getString(5));

                    resultado_consulta += 
                        
                        "Codigo:" + Rs.getString(1) 
                        + " Nombres: " + Rs.getString(2) + " Apellidos: " + Rs.getString(3) 
                        + " Curso: " + Rs.getString(4) 
                        + " Nota: " + Rs.getString(5);
                }

            }
        } 
        catch (final Exception ex) {
            System.out.println("Error"+ex.getMessage());
        }

        return resultado_consulta ;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "EstudianteCurso")
    public String EstudianteCurso(@WebParam(name = "grado") String grado, @WebParam(name = "grupo") String grupo) {
        
        String cadena="";
           
        try{
            
        if(db != null)
        {
            System.out.println("la conexion fue realizada con exito");
           
            Statement sentencia=db.createStatement();
            ResultSet Rs= sentencia.executeQuery("SELECT cursos.id_curso,cursos.grado,cursos.grupo,estudiantes.id_estudiante,estudiantes.nombres,estudiantes.apellidos from cursos INNER JOIN matriculas on cursos.id_curso=matriculas.id_curso INNER JOIN estudiantes"
                    + " on estudiantes.id_estudiante=matriculas.id_estudiante WHERE TRIM(cursos.grado)='"+grado.trim()+"'"
                    + "and TRIM(cursos.grupo)='"+grupo.trim()+"'");
            
            while (Rs.next())
            {
                
                System.out.println("codigo_Curso:" +Rs.getString(1)+" Grado: "+Rs.getString(2)+ " Grupo: "+Rs.getString(3)+ " id_Estudiante: "+Rs.getString(4)+ " Nombres: "+Rs.getString(5)+ "Apellidos:"+Rs.getString(6));
                cadena+="codigo_Curso:" +Rs.getString(1)+" Grado: "+Rs.getString(2)+ " Grupo: "+Rs.getString(3)+ " id_Estudiante: "+Rs.getString(4)+ " Nombres: "+Rs.getString(5)+ "Apellidos:"+Rs.getString(6);
                
            }
                
        }
        }catch (Exception ex){
            System.out.println("Error "+ex.getMessage());
        }
        return cadena;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ConsultarMateriasMatriculadasPorEstudiante")
    public String ConsultarMateriasMatriculadasPorEstudiante(@WebParam(name = "codigo") String codigo) {
        String resultado_consulta = "";
        
        try{ 
            if (db != null) {

                System.out.println("la conexion fue realizada con exito");

                Statement sentencia = db.createStatement();
                ResultSet Rs = sentencia.executeQuery(
                    "SELECT estudiantes.id_estudiante, nombres, apellidos, materias.materia, anios.anio " 
                    +"FROM estudiantes inner join estudiante_materias "
                    +"on estudiantes.id_estudiante = estudiante_materias.id_estudiante inner join  materias "
                   	+"on estudiante_materias.id_materia = materias.id_materia inner JOIN anios "
                    +"on estudiante_materias.id_anio = anios.id_anio " 
                    
                    +"WHERE TRIM(estudiantes.id_estudiante) = '" + codigo.trim() + "' ORDER BY anios.anio");
                            
                while (Rs.next()) 
                {
                    System.out.println( " Nombres: " + Rs.getString(2) + " Apellidos: "
                                + Rs.getString(3) + " Materia: " + Rs.getString(4) + " Anio: " + Rs.getString(5));

                    resultado_consulta += 
                        
                          " Codigo:" + Rs.getString(1) 
                        + " Nombres: " + Rs.getString(2) + " Apellidos: " + Rs.getString(3) 
                        + " Materia: " + Rs.getString(4) 
                        + " Anio: " + Rs.getString(5);
                }

            }
        } 
        catch (final Exception ex) {
            System.out.println("Error"+ex.getMessage());
        }

        return resultado_consulta ;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ConsultarPromedioPorCurso")
    public String ConsultarPromedioPorCurso() {
        String resultado_consulta = "";
        
        try{ 
            if (db != null) {

                System.out.println("la conexion fue realizada con exito");

                Statement sentencia = db.createStatement();
                ResultSet Rs = sentencia.executeQuery(
                    "SELECT anios.id_anio,cursos.id_curso,cursos.grado,cursos.grupo, AVG(nota) " 
                    +"from cursos INNER JOIN matriculas on cursos.id_curso=matriculas.id_curso INNER JOIN anios "
                    +"on matriculas.id_anio=anios.id_anio INNER JOIN estudiante_materias "
                   	+"on estudiante_materias.id_anio=anios.id_anio INNER JOIN calificaciones "
                    +"on calificaciones.id_estudiante_materia=estudiante_materias.id_estudiante_materia " 
                    +"GROUP BY(cursos.id_curso)");

                            
                while (Rs.next()) 
                {
                    System.out.println( " Anio: " + Rs.getString(1) + " Id_Curso: " + Rs.getString(2) + " Grado: " + Rs.getString(3) + " Grupo: " + Rs.getString(4) + "Nota: " + Rs.getString(5));
                    resultado_consulta += " Anio: " + Rs.getString(1) + " Id_Curso: " + Rs.getString(2) + " Grado: " + Rs.getString(3) + " Grupo: " + Rs.getString(4) + "Nota: " + Rs.getString(5);
                }

            }
        } 
        catch (final Exception ex) {
            System.out.println("Error"+ex.getMessage());
        }

        return resultado_consulta ;    
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ConsultarPromedioPorAnio")
    public String ConsultarPromedioPorAnio() {
        String resultado_consulta = "";
        
        try{ 
            if (db != null) {

                System.out.println("la conexion fue realizada con exito");

                Statement sentencia = db.createStatement();
                ResultSet Rs = sentencia.executeQuery(
                    "select anios.anio, avg(nota) " 
                    +"from calificaciones inner join estudiante_materias "
                    +"on calificaciones.id_estudiante_materia = estudiante_materias.id_estudiante_materia inner join anios "
                    +"on estudiante_materias.id_anio = anios.id_anio "
                    +"where anios.actual = 1");

                            
                while (Rs.next()) 
                {
                    System.out.println( " Anio: " + Rs.getString(1) + " Promedio Global: " + Rs.getString(2));

                    resultado_consulta += 
            
                          " Anio:" + Rs.getString(1) 
                        + " Promedio Global: " + Rs.getString(2) ;
                }

            }
        } 
        catch (final Exception ex) {
            System.out.println("Error"+ex.getMessage());
        }

        return resultado_consulta ;
    }

    
}
