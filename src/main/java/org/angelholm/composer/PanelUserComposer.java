package org.angelholm.composer;

import org.angelholm.model.UserPrincipal;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        init();

    }

    private void init(){

        Authentication auth = SecurityContextHolder
                .getContext().getAuthentication();

        if (auth != null) {

            Object principal = auth.getPrincipal();

            if (principal instanceof UserPrincipal)
                setPractitioner  ( ( (UserPrincipal) principal).getPractitioner());

        }
    }

    public void setPractitioner(Practitioner practitioner){
        this.practitioner = practitioner;
        btnUser.setLabel(practitioner.getName().get(0).getText());
    }

    public Practitioner getPractitioner() {
        return practitioner;
    }
}
