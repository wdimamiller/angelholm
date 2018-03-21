package org.angelholm.composer;

import org.angelholm.service.ValueSetService;
import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PanelAddressComposer extends SelectorComposer<Panel> {

    Address address;

    @Wire
    Combobox lstCountry;
    @Wire
    Combobox lstState;

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("PanelAddressComposer", this);

        renderListCountry();
        renderListState();
    }

    public void renderListCountry(){

        ValueSetService valueSetService = new ValueSetService();

        List<ValueSet.ValueSetExpansionContainsComponent> listComponent = valueSetService.getContains("uk_UA", "Countries" );

        ListModelList<ValueSet.ValueSetExpansionContainsComponent> comboModel = new ListModelList<ValueSet.ValueSetExpansionContainsComponent>(listComponent);


        lstCountry.setModel(comboModel);

        Comboitem initialSelectedItem = new Comboitem();
        initialSelectedItem.setLabel(comboModel.get(0).getDisplay());
        lstCountry.appendChild(initialSelectedItem);
        lstCountry.setSelectedItem(initialSelectedItem);

        lstCountry.setItemRenderer((item, data, index)  -> {

            final ValueSet.ValueSetExpansionContainsComponent containsComponent = (ValueSet.ValueSetExpansionContainsComponent) data;
            item.setLabel(containsComponent.getDisplay());
        });



    }

    @Listen("onSelect=#lstCountry")
    public void renderListState(){

        //clear list
        lstState.setModel(null);
        
        //value selected in lstCounry
        int selectedIndex = lstCountry.getSelectedIndex();
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

        //set default item (the first one)
        Comboitem initialSelectedItem = new Comboitem();
        initialSelectedItem.setLabel(comboModel.get(0).getDisplay());
        lstState.appendChild(initialSelectedItem);
        lstState.setSelectedItem(initialSelectedItem);

        //set renderer
        lstState.setItemRenderer((item, data, index)  -> {

            final ValueSet.ValueSetExpansionContainsComponent containsComponent = (ValueSet.ValueSetExpansionContainsComponent) data;
            item.setLabel(containsComponent.getDisplay());
        });

    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
