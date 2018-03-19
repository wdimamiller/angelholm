package org.angelholm.composer;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Panel;

public class PanelUserComposer extends SelectorComposer<Panel> {

    @Wire
    Panel panelUser;



    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
    }
}
