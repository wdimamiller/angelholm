package org.angelholm.composer;

import org.hl7.fhir.dstu3.model.HumanName;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Panel;

public class PanelHumanNameComposer extends SelectorComposer<Panel> {

    HumanName humanName;

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);

        if(humanName != null){

        }
    }

    public void setHumanName(HumanName humanName) {
        this.humanName = humanName;
    }

    public HumanName getHumanName() {
        return humanName;
    }
}
