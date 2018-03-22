package org.angelholm.composer;

import org.angelholm.service.PatientService;
import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

import java.util.Date;


public class NewPatientComposer extends SelectorComposer <Window>{

    @Wire
    Window windowNewPatient;
    @Wire
    Datebox dtbDateOfBirth;
    @Wire
    Button btnCreate;
    @Wire
    Button btnReset;
    @Wire
    Radio radioMale;
    @Wire("include #panelHumanName")
    Panel panelHumanName;
    @Wire("include #panelTelecom")
    Panel panelTelecom;
    @Wire("include #panelAddress")
    Panel panelAddress;
    @Wire("include #panelAdministrativeData")
    Panel panelAdministrativeData;


    PatientService patientService;

    @Listen("onClick=#btnCreate")
    public void createPatient(){

        //create patient
        Patient patient = new Patient();

        //TODO normal human identifier
        patient.addIdentifier().setSystem("urn:system").setValue("123456");

        //human name
        HumanName name = ((PanelHumanNameComposer)  panelHumanName.getAttribute("PanelHumanNameComposer")).getHumanName();
        patient.addName(name);

        //telecom
        patient.setTelecom(( (PanelTelecomComposer) panelTelecom.getAttribute("PanelTelecomComposer")).getTelecom());

        //administrative data
        patient.setBirthDate ( ( (PanelAdministrativeDataComposer) panelAdministrativeData.getAttribute("PanelAdministrativeDataComposer")).getDateOfBirth());
        String gender = ((PanelAdministrativeDataComposer) panelAdministrativeData.getAttribute("PanelAdministrativeDataComposer")).getGender();
        patient.setGender( new Enumerations.AdministrativeGenderEnumFactory().fromCode(gender));

        //address
        Address address = ((PanelAddressComposer) panelAddress.getAttribute("PanelAddressComposer")).getAddress();
        patient.addAddress(address);

        long status  = patientService.createPatient(patient);

        if(status != 0){
            Clients.showNotification(Labels.getLabel("windowNewPatient.notificationSuccessfullyCreated"),"info",btnCreate,"end_center",3000);
        }
        else{
            Clients.showNotification(Labels.getLabel("windowNewPatient.notificationUnsuccessfullyCreated"),"info",btnCreate,"end_center",3000);
        }
    }

    @Listen("onClick=#btnReset")
    public void closeWindow(){
        windowNewPatient.detach();
    }

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        patientService = new PatientService();

    }
}
