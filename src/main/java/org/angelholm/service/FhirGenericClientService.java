package org.angelholm.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class FhirGenericClientService {

    private static IGenericClient client;

    private FhirGenericClientService(){

    }

    public static IGenericClient getInstance(){
        if(client == null){
            synchronized (FhirGenericClientService.class) {
                if (client == null) {

                    FhirContext ctx = new FhirContext(FhirVersionEnum.DSTU3);
                    String serverBase = "http://localhost:8090/baseDstu3/";
                    client = ctx.newRestfulGenericClient(serverBase);

                }
            }
        }
        return client;
    }
}
