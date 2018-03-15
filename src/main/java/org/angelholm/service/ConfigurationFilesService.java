package org.angelholm.service;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class ConfigurationFilesService {


    public static Configuration  getConfigFile(){

        Configurations configs = new Configurations();
        try
        {
            return configs.properties(
                    new File("/configValueSet.properties"));

        }
        catch (ConfigurationException ex)
        {
            // TODO Normal notification about this trouble
        }
        return null;

    }

}
