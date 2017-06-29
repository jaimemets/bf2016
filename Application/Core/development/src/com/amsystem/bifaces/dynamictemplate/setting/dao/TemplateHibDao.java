package com.amsystem.bifaces.dynamictemplate.setting.dao;

import com.amsystem.bifaces.dynamictemplate.setting.model.Template;

/**
 * Title: TemplateHibDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 15/06/2017.
 */


public interface TemplateHibDao {

    /**
     * Carga de la base de datos la plantilla que coincida con el identificador que recibe por parametro
     *
     * @param idTr identificador de la plantilla que sera cargada
     * @return <tt>Template</tt>
     */
    Template loadTemplateById(Integer idTr);

    /**
     * Carga de la base de datos la plantilla que coincida con el nombre que recibe por parametro
     *
     * @param templateName nombre de la plantilla
     * @return <tt>Template</tt>
     */
    Template loadTemplateByName(String templateName);


}
