package org.angelholm.service;

import ca.uhn.fhir.rest.client.api.IGenericClient;


import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Patient;

import java.util.ArrayList;

public class PatientService {

    public ArrayList<Patient> getListPatiens(){

        ArrayList<Patient> listPatients = new ArrayList<>();

        IGenericClient client = FhirGenericClientService.getInstance();

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
}
