package org.angelholm.composer;

import org.hl7.fhir.dstu3.model.Practitioner;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class WindowPractitionerComposer extends SelectorComposer<Window> {

    @Wire
    Window windowPractitioner;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
    }

    public void createPractitioner(){


    }
}
