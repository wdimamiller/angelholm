package org.angelholm.composer;

import org.angelholm.service.PractitionerService;
import org.hl7.fhir.dstu3.model.*;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;




public class NewPractitionerComposer extends SelectorComposer <Window>{

    @Wire
    Window windowNewPractitioner;
    @Wire
    Button btnCreate;

    @Wire("include #panelHumanName")
    Panel panelHumanName;
    @Wire("include #panelTelecom")
    Panel panelTelecom;
    @Wire("include #panelAddress")
    Panel panelAddress;
    @Wire("include #panelAdministrativeData")
    Panel panelAdministrativeData;


    PractitionerService practitionerService;

    @Listen("onClick=#btnCreate")
    public void createPractitioner(){

        //create Practitioner
        Practitioner practitioner = new Practitioner();

        //TODO normal human identifier
        practitioner.addIdentifier().setSystem("urn:system").setValue("123456");

        //human name
        HumanName name = ((PanelHumanNameComposer)  panelHumanName.getAttribute("PanelHumanNameComposer")).getHumanName();
        practitioner.addName(name);

        //telecom
        practitioner.setTelecom(( (PanelTelecomComposer) panelTelecom.getAttribute("PanelTelecomComposer")).getTelecom());

        //administrative data
        practitioner.setBirthDate ( ( (PanelAdministrativeDataComposer) panelAdministrativeData.getAttribute("PanelAdministrativeDataComposer")).getDateOfBirth());
        String gender = ((PanelAdministrativeDataComposer) panelAdministrativeData.getAttribute("PanelAdministrativeDataComposer")).getGender();
        practitioner.setGender( new Enumerations.AdministrativeGenderEnumFactory().fromCode(gender));

        //address
        Address address = ((PanelAddressComposer) panelAddress.getAttribute("PanelAddressComposer")).getAddress();
        practitioner.addAddress(address);

        long status  = practitionerService.createPractitioner(practitioner);

        if(status != 0){
            Clients.showNotification(Labels.getLabel("windowNewPatient.notificationSuccessfullyCreated"),"info",btnCreate,"end_center",3000);
        }
        else{
            Clients.showNotification(Labels.getLabel("windowNewPatient.notificationUnsuccessfullyCreated"),"info",btnCreate,"end_center",3000);
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
