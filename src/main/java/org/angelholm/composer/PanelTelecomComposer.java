package org.angelholm.composer;

import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.Patient;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;

import java.util.ArrayList;
import java.util.List;


public class PanelTelecomComposer extends SelectorComposer<Panel> {

    List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    @Wire
    Textbox txtEmail;
    @Wire
    Textbox txtPhone;

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("PanelTelecomComposer", this);
    }

    public void setTelecom(List<ContactPoint> telecom){
        this.telecom = telecom;
    }

    public List<ContactPoint> getTelecom() {
        telecom.add(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.EMAIL).setValue(txtEmail.getValue()));
        telecom.add(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.PHONE).setValue(txtPhone.getValue()));
        return telecom;
    }
}
