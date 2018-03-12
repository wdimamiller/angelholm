package org.angelholm.composer;

import org.hl7.fhir.dstu3.model.HumanName;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;

public class PanelHumanNameComposer extends SelectorComposer<Panel> {

    HumanName humanName;

    @Wire
    Textbox txtLastName;
    @Wire
    Textbox txtFirstName;
    @Wire
    Textbox txtSecondName;

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("PanelHumanNameComposer", this);
    }

    public void setHumanName(HumanName humanName) {

        this.humanName = humanName;
        txtLastName.setValue( humanName.getFamily());
        txtFirstName.setValue( humanName.getGiven().get(0).getValue());
        txtSecondName.setValue( humanName.getGiven().get(1).getValue());

    }

    public HumanName getHumanName() {
        humanName = new HumanName();
        humanName.setFamily(txtLastName.getValue())
                .addGiven(txtFirstName.getValue())
                .addGiven(txtSecondName.getValue());
        return humanName;

    }


}
