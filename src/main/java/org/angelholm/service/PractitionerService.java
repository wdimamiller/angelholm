package org.angelholm.service;
import org.hl7.fhir.dstu3.model.Practitioner;

public class PractitionerService {


    public Practitioner readPractitioner(){

        Practitioner practitioner = new Practitioner();

        return practitioner;
    }


    public int createPractitioner( Practitioner practitioner){

        return 1;
    }
}
