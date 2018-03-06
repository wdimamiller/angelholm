package org.angelholm.composer;

import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;

public class NewPractitionerComposer extends SelectorComposer{

    @Wire
    Textbox txtEmail;

    public void createPractitioner(){

        Practitioner practitioner = new Practitioner();

        practitioner.addTelecom().setSystem(ContactPoint.ContactPointSystem.EMAIL).setValue(txtEmail.getValue());
      /*  contactPoint.setSystem(ContactPoint.ContactPointSystem.EMAIL);
        contactPoint.setValue("wdimamiller@gmail.com");*/
    }
}
