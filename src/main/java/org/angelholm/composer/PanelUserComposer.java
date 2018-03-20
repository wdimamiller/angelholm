package org.angelholm.composer;

import org.hl7.fhir.dstu3.model.Practitioner;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Panel;

public class PanelUserComposer extends SelectorComposer<Panel> {

    @Wire
    Panel panelUser;
    @Wire
    Button btnUser;
    @Wire
    Button btnUserHome;

    Practitioner practitioner;

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("PanelUserComposer", this);
    }

    public void setPractitioner(Practitioner practitioner){
        this.practitioner = practitioner;
    }

    public Practitioner getPractitioner() {
        return practitioner;
    }
}
