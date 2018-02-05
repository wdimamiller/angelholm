package org.angelholm.service;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;


import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Patient;

import java.util.ArrayList;

public class PatientService {

    IGenericClient client = FhirGenericClientService.getInstance();

    public ArrayList<Patient> getListPatiens(){

        ArrayList<Patient> listPatients = new ArrayList<>();

        Bundle results = client
                .search()
                .forResource(Patient.class)
                .returnBundle(Bundle.class)
                .execute();

        for (Bundle.BundleEntryComponent entry : results.getEntry()) {
            listPatients.add((Patient) entry.getResource());
            //System.out.println((Patient) entry.getResource());
        }


        return listPatients;
    }

    public int deletePatient(Patient patient){

         client.delete().resource(patient).execute();

        return 0;
    }

    public int updatePatient(Patient patient){

        client.update()
                .resource(patient)
                .execute();
        return 0;
    }
    public long createPatient(Patient patient){

        MethodOutcome outcome = client.create()
                .resource(patient)
                .prettyPrint()
                .encodedJson()
                .execute();


        IdType id = (IdType) outcome.getId();
        System.out.println("Got ID: " + id.getValue());

        return id.getIdPartAsLong();
    }
}
