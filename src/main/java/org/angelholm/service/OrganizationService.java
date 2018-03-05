package org.angelholm.service;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.angelholm.service.FhirGenericClientService;
import org.hl7.fhir.dstu3.model.*;

import java.util.ArrayList;
import java.util.List;

public class OrganizationService {

    IGenericClient client = FhirGenericClientService.getInstance();

    public int createOrganization(Organization org){

        /*CodeSystem organizationCodeSystem;
        organizationCodeSystem = client.read()
                .resource(CodeSystem.class)
                .withId("44952")
                .execute();*/

        ValueSet valueSet  = client.read()
                .resource(ValueSet.class)
                .withUrl("")
                .execute();



      //  ValueSet.ValueSetComposeComponent  composeComponent = valueSet.
      //  composeComponent.get


        ValueSet.ValueSetExpansionComponent a = valueSet.getExpansion();
        List<Extension> extensionList =     a.getExtension();
        System.out.println(extensionList.get(0).toString());

        return 0;
    }
}
