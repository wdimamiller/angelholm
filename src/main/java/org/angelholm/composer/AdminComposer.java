package org.angelholm.composer;

import org.angelholm.service.PractitionerService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;

public class AdminComposer extends SelectorComposer {

    @Wire("include #gridPractitioner")
    Grid gridPractitioners;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        PractitionerService practitionerService = new PractitionerService();
        practitionerService.getListPractitioner();
    }

    public void renderGridPractitioners(){





    }
}
