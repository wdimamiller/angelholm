package org.angelholm.composer;

import org.angelholm.service.PatientService;
import org.angelholm.service.PractitionerService;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

import java.util.Date;


public class NewPractitionerComposer extends SelectorComposer <Window>{

    @Wire
    Window windowNewPractitioner;
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


    PractitionerService practitionerService;

    @Listen("onClick=#btnCreate")
    public void createPractitioner(){

        Practitioner practitioner = new Practitioner();
        practitioner.addIdentifier().setSystem("urn:system").setValue("123456");

        HumanName name = ((PanelHumanNameComposer)  panelHumanName.getAttribute("PanelHumanNameComposer")).getHumanName();
        practitioner.addName(name);

        practitioner.setTelecom(( (PanelTelecomComposer) panelTelecom.getAttribute("PanelTelecomComposer")).getTelecom());

        practitioner.setBirthDate(new Date());

        if(radioMale.isSelected()){
            practitioner.setGender(Enumerations.AdministrativeGender.MALE);
        }
        else{
            practitioner.setGender(Enumerations.AdministrativeGender.FEMALE);
        }


        long status  = practitionerService.createPractitioner(practitioner);

        if(status != 0){
            Clients.showNotification(Labels.getLabel("windowNewPractitioner.notificationSuccessfullyCreated"),"info",btnCreate,"end_center",3000);
        }
        else{
            Clients.showNotification(Labels.getLabel("windowNewPractitioner.notificationUnsuccessfullyCreated"),"info",btnCreate,"end_center",3000);
        }
    }

    @Listen("onClick=#btnReset")
    public void closeWindow(){
        windowNewPractitioner.detach();
    }

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        practitionerService = new PractitionerService();

    }
}
