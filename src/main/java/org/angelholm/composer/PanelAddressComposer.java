package org.angelholm.composer;

import org.angelholm.service.ValueSetService;
import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.ValueSet;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.List;

public class PanelAddressComposer extends SelectorComposer<Panel> {

    Address address;

    @Wire
    Combobox lstCountry;
    @Wire
    Combobox lstState;
    @Wire
    Combobox lstCity;
    @Wire
    Textbox txtPostalCode;
    @Wire
    Textbox txtStreet;
    @Wire
    Textbox txtHouse;
    @Wire
    Textbox txtFlat;

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("PanelAddressComposer", this);
        //TODO hard control of entered values
        //This possible only in case of having all value sets with countries and states
        //or to add a method for adding them.

        renderListCountry();
    }

    public void renderListCountry(){

        //get value set with list of countries
        ValueSetService valueSetService = new ValueSetService();
        List<ValueSet.ValueSetExpansionContainsComponent> listComponent = valueSetService.getContains("uk_UA", "Countries" );

        //create and set model
        ListModelList<ValueSet.ValueSetExpansionContainsComponent> comboModel =
                new ListModelList<ValueSet.ValueSetExpansionContainsComponent>(listComponent);
        lstCountry.setModel(comboModel);

        //if want to have selected item
        /*
        Comboitem initialSelectedItem = new Comboitem();
        initialSelectedItem.setLabel(comboModel.get(0).getDisplay());
        lstCountry.appendChild(initialSelectedItem);
        lstCountry.setSelectedItem(initialSelectedItem);
        */

        //set renderer
        lstCountry.setItemRenderer((item, data, index)  -> {

            final ValueSet.ValueSetExpansionContainsComponent containsComponent = (ValueSet.ValueSetExpansionContainsComponent) data;
            item.setLabel(containsComponent.getDisplay());
        });

    }

    @Listen("onSelect=#lstCountry")
    public void renderListState(){

        //clear list
        lstState.setSelectedItem(null);
        lstState.setText(null);
        lstState.setModel(null);

        //value selected in lstCounry
        int selectedIndex = lstCountry.getSelectedIndex();

        //if value of writted country is not from valueSet
        if(selectedIndex == -1){
            return;
        }

        String countryCode = ((ValueSet.ValueSetExpansionContainsComponent) lstCountry.getModel().getElementAt(selectedIndex)).getCode();

        //search value set with states for selected country
        ValueSetService valueSetService = new ValueSetService();
        ValueSet valueSetStates = valueSetService.getValueSetByName(countryCode);

        if(valueSetStates == null){
            return;
        }

        //create list model
        ListModelList<ValueSet.ValueSetExpansionContainsComponent> comboModel =
                new ListModelList<ValueSet.ValueSetExpansionContainsComponent>(valueSetStates.getExpansion().getContains());

        //set this model
        lstState.setModel(comboModel);

        //set renderer
        lstState.setItemRenderer((item, data, index)  -> {

            final ValueSet.ValueSetExpansionContainsComponent containsComponent = (ValueSet.ValueSetExpansionContainsComponent) data;
            item.setLabel(containsComponent.getDisplay());
        });

    }
    public void setAddress(Address address) {
        //TODO fill values in panels components
        this.address = address;
    }

    public Address getAddress() {

        address = new Address();
        address.setCountry(lstCountry.getText());
        address.setState(lstState.getText());
        address.setCity(lstCity.getText());
        address.setPostalCode(txtPostalCode.getText());
        address.addLine(txtStreet.getText());
        address.addLine(txtHouse.getText());
        address.addLine(txtFlat.getText());

        return address;
    }
}
