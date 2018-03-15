package org.angelholm.composer;

import ca.uhn.fhir.model.base.composite.BaseCodingDt;
import org.angelholm.service.ValueSetService;
import org.hl7.fhir.dstu3.model.*;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;


public class PanelTelecomComposer extends SelectorComposer<Panel> {

    @Wire
    Listbox listContactPointCandidate;
    @Wire
    Listbox listContactPointChosen;
    @Wire
    Button btnAddContactPoint;

    ListModelList<ValueSet.ValueSetExpansionContainsComponent> listModelCandidate;
    ListModelList<ValueSet.ValueSetExpansionContainsComponent> listModelChosen;
    List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("PanelTelecomComposer", this);

        init();
        renderListCandidate();
        renderListChosen();
    }

    private void init(){

        String strLocale = Locales.getCurrent().getLanguage() + "_" +  Locales.getCurrent().getCountry();
        ValueSetService valueSetService = new ValueSetService();
        ValueSet contactPointValueSet = valueSetService.getValueSet(strLocale, "ContactPointSystem");

        listModelCandidate = new ListModelList<>(contactPointValueSet.getExpansion().getContains());
        listModelChosen =  new ListModelList<>();


    }

    private void renderListCandidate(){

        listContactPointCandidate.setModel(listModelCandidate);
        listContactPointCandidate.setItemRenderer((item, data, index)  -> {
            final ValueSet.ValueSetExpansionContainsComponent containsComponent = (ValueSet.ValueSetExpansionContainsComponent) data;
            item.setLabel(containsComponent.getDisplay());
        });

        if(listContactPointCandidate.getModel().getSize()!=0) {
            listContactPointCandidate.setSelectedIndex(0);
        }
        else{
            listContactPointCandidate.setVisible(false);
            btnAddContactPoint.setVisible(false);
        }
    }

    private void renderListChosen(){

        listContactPointChosen.setModel(listModelChosen);
        listContactPointChosen.setItemRenderer((item, data, index)  -> {
            final ValueSet.ValueSetExpansionContainsComponent containsComponent = (ValueSet.ValueSetExpansionContainsComponent) data;
            Listcell cellLabel = new Listcell();
            cellLabel.appendChild((new Label(containsComponent.getDisplay())));
            cellLabel.setParent(item);

            Listcell cellValue = new Listcell();
            cellValue.appendChild(new Textbox());
            cellValue.setParent(item);

        });
    }

    @Listen("onClick=#btnAddContactPoint")
    public void addContactPoint(){

        int selected = listContactPointCandidate.getSelectedIndex();
        ValueSet.ValueSetExpansionContainsComponent selectedContactPoint = (ValueSet.ValueSetExpansionContainsComponent) listContactPointCandidate.getModel().getElementAt(selected);
        listModelChosen.add(selectedContactPoint);
        listModelCandidate.remove(selectedContactPoint);

        renderListCandidate();

    }

    public void setTelecom(List<ContactPoint> telecom){
        this.telecom = telecom;
    }

    public List<ContactPoint> getTelecom() {

        int size = listModelChosen.getSize();
        for(int i = 0; i < size; i++){

            String code = listModelChosen.getElementAt(i).getCode();
            String value = ((Textbox) listContactPointChosen.getItemAtIndex(i).getChildren().get(1).getChildren().get(0) ).getValue();

            ContactPoint contactPoint = new ContactPoint();

                contactPoint
                        .setSystem(new ContactPoint.ContactPointSystemEnumFactory().fromCode(code))
                        .setValue(value);

            telecom.add(contactPoint);
        }

        return telecom;
    }
}
