package org.angelholm.service;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import org.apache.commons.configuration2.Configuration;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.ValueSet;


public class ValueSetService {

    private IGenericClient client = FhirGenericClientService.getInstance();

    public ValueSet getValueSet(String language, String className){

        Configuration config =  ConfigurationFilesService.getConfigFile();

        if(config==null){
            //TODO Notification about this
            return null;
        }

        Bundle bundle = client.search()
                .forResource(ValueSet.class)
                .where(ValueSet.NAME.matchesExactly().value(config.getString(className)))
                .and(new StringClientParam(ValueSet.SP_RES_LANGUAGE).matches().value(language))
                .returnBundle(Bundle.class)
                .execute();

        if(bundle.getTotal() == 0)
            return null;
        else
            return (ValueSet) bundle.getEntryFirstRep().getResource();
    }

}
