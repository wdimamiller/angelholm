package org.angelholm.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
//import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;


import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.*;
import org.hl7.fhir.dstu3.model.Patient;
import org.zkoss.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TestService {

    public void test(){

        PatientService patientService = new PatientService();
        FhirContext ctx = FhirContext.forDstu3();

        ArrayList<Patient> listPatiens = patientService.getListPatiens();

        for(Patient patient : listPatiens){
            String encoded = ctx.newXmlParser().encodeResourceToString(patient);



            System.out.println(encoded);
        }
        /*
        IGenericClient client = FhirGenericClientService.getInstance();
        Patient patient = new Patient();

        patient.addIdentifier().setSystem("urn:system").setValue("123456");
        patient
                .addName()
                .setFamily("Ivanov");



        MethodOutcome outcome = client.create()
                .resource(patient)
                .prettyPrint()
                .encodedJson()
                .execute();


        IdType id = (IdType) outcome.getId();
        System.out.println("Got ID: " + id.getValue());
        */


    }
}
