package org.angelholm.composer;

import org.angelholm.service.PatientService;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.Patient;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

public class NewPatientComposer extends SelectorComposer <Window>{

    @Wire
    Textbox txtLastName;
    @Wire
    Textbox txtFirstName;
    @Wire
    Textbox txtSecondName;
    @Wire
    Datebox dtbDateOfBirth;
    @Wire
    Button btnCreate;
    @Wire
    Button btnReset;
    @Wire
    Radio radioMale;

    PatientService patientService;

    @Listen("onClick=#btnCreate")
    public void aaa(){

        System.out.println("I am in btn Create");
        Patient patient = new Patient();

        patient.addIdentifier().setSystem("urn:system").setValue("123456");
        patient
                .addName()
                .setFamily(txtLastName.getValue())
                .addGiven(txtFirstName.getValue())
                .addGiven(txtSecondName.getValue());
        patient.setBirthDate(dtbDateOfBirth.getValue());

        if(radioMale.isSelected()){
            patient.setGender(Enumerations.AdministrativeGender.MALE);
        }
        else{
            patient.setGender(Enumerations.AdministrativeGender.FEMALE);
        }


        patientService.createPatient(patient);
    }

    @Listen("onClick=#btnReset")
    public void bbb(){
        System.out.println("I am in btn Reset");

    }

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        patientService = new PatientService();
    }
}
