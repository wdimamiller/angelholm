package org.angelholm.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.model.api.IQueryParameterAnd;
import ca.uhn.fhir.model.api.IQueryParameterType;
import ca.uhn.fhir.model.base.composite.BaseHumanNameDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;


import ca.uhn.fhir.rest.gclient.ICriterion;
import ca.uhn.fhir.rest.gclient.IParam;
import ca.uhn.fhir.rest.gclient.TokenClientParam;
import ca.uhn.fhir.rest.param.DateParam;
import ca.uhn.fhir.rest.param.DateRangeParam;
import org.hl7.fhir.dstu3.model.*;
import org.hl7.fhir.dstu3.model.codesystems.Appointmentstatus;
import org.hl7.fhir.dstu3.model.codesystems.OrganizationType;
import org.hl7.fhir.dstu3.model.codesystems.OrganizationTypeEnumFactory;
import org.hl7.fhir.exceptions.FHIRException;
import org.zkoss.zhtml.Code;

import java.util.*;

public class PatientService {

    IGenericClient client = FhirGenericClientService.getInstance();


    public ArrayList<Patient> getListPatiens(){

        ArrayList<Patient> listPatients = new ArrayList<>();

        Bundle results = client
                .search()
                .forResource(Patient.class)
                .count(50)
                .returnBundle(Bundle.class)
                .sort().ascending(Patient.FAMILY)
                .execute();

        for (Bundle.BundleEntryComponent entry : results.getEntry()) {
            listPatients.add((Patient) entry.getResource());
        }

        return listPatients;
    }

    public ArrayList<Patient> getListPatients(String lastName, String firstName, String secondName , Date dateFrom, Date dateTo, Date dateBirth, String identifier, String gender){

        ArrayList<Patient> listPatients = new ArrayList<>();

        DateRangeParam rangeParam = new DateRangeParam();
        rangeParam.setRangeFromDatesInclusive(dateFrom, dateTo);


        Bundle results = client
                .search()
                .forResource(Patient.class)
                .where(Patient.FAMILY.matches().value(lastName))
                .and(Patient.GIVEN.matches().values(firstName,secondName))
                .and(Patient.BIRTHDATE.exactly().day(dateBirth))
                .and(Patient.GENDER.exactly().code(gender))
                .lastUpdated(rangeParam)
                .count(50)
                .returnBundle(Bundle.class)
                .sort().ascending(Patient.FAMILY)
                .execute();

        for (Bundle.BundleEntryComponent entry : results.getEntry()) {
            listPatients.add((Patient) entry.getResource());
        }

        return listPatients;

    }


    public ArrayList<Patient> getListPatient( List<IQueryParameterType> listParameter){
        ArrayList<Patient> listPatients = new ArrayList<>();


        Map<String, List<IQueryParameterType>> map = new HashMap<>();


        //List<IQueryParameterType> listParameter = new ArrayList<>();

        map.put("name", listParameter);
        Bundle results = client
                .search()
                .forResource(Patient.class)
                .where(map)
                .count(50)
                .returnBundle(Bundle.class)
                .sort().ascending(Patient.FAMILY)
                .execute();

        for (Bundle.BundleEntryComponent entry : results.getEntry()) {
            listPatients.add((Patient) entry.getResource());
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

        Provenance prov = new Provenance();
        prov.addTarget().setReferenceElement(patient.getIdElement());
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
