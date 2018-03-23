package org.angelholm.service;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.*;

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


    public int createPractitioner( Practitioner practitioner){

        return 1;
    }
}
