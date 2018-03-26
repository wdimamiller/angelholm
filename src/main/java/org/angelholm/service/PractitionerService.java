package org.angelholm.service;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.*;


import java.util.ArrayList;

public class PractitionerService {

    IGenericClient client = FhirGenericClientService.getInstance();

    public Practitioner getPractitionerByIdentifier(){

        Identifier identifier = new Identifier();
        identifier.setType(
                new CodeableConcept().addCoding(
                        new Coding()
                                .setSystem("http://angelholm.com.ua/ValueSet/Identifier")
                                .setCode("login")));
        identifier.setValue("admin");

        Bundle results = client
                .search()
                .forResource(Practitioner.class)
                .where(Practitioner.IDENTIFIER.exactly().identifier("admin")  )
                .returnBundle(Bundle.class)
                .execute();

        Practitioner practitioner = (Practitioner) results.getEntryFirstRep().getResource();


        return practitioner;

    }
    public Practitioner readPractitioner(){

        Practitioner practitioner = new Practitioner();

        return practitioner;
    }

    public ArrayList<Practitioner> getListPractitioner(){

        ArrayList<Practitioner> listPractitioners = null;

        Bundle result =  client.search().forResource(Practitioner.class)
                .returnBundle(Bundle.class)
                .execute();

        Practitioner[] a = (Practitioner[]) result.getEntry().toArray();

        for(int i = 0; i < a.length; i++){
            System.out.println( a[i].getName().get(0).getFamily());
        }


        return listPractitioners;
    }

    public int createPractitioner( Practitioner practitioner){

        return 1;
    }
}
