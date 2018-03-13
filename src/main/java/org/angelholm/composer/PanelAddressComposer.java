package org.angelholm.composer;

import org.hl7.fhir.dstu3.model.Address;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;

public class PanelAddressComposer extends SelectorComposer<Panel> {

    Address address;

    @Wire
    Textbox txtCountry;
    @Wire
    Textbox txtPostalCode;
    @Wire
    Textbox txtState;
    @Wire
    Textbox txtCity;

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("PanelAddressComposer", this);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {

        address.setCountry(txtCountry.getValue());
        address.setPostalCode(txtPostalCode.getValue());
        address.setCity(txtCity.getValue());
        address.setState(txtState.getValue());

        return address;
    }
}
