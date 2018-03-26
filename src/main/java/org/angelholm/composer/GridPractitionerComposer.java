package org.angelholm.composer;

import org.hl7.fhir.dstu3.model.Practitioner;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelArray;

import java.util.ArrayList;

public class GridPractitionerComposer extends SelectorComposer<Grid>{

    @Wire
    Grid gridPractitioner;

    Practitioner selectedPractitioner;
    ArrayList<Practitioner> listPractitioners;

    @Override
    public void doAfterCompose(Grid comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("GridPractitionerComposer", this);

    }

    public ArrayList<Practitioner> getListPractitioners() {
        return listPractitioners;
    }

    public void setListPractitioners(ArrayList<Practitioner> listPractitioners) {
        this.listPractitioners = listPractitioners;

        renderGrid();
    }

    public void setSelectedPractitioner(Practitioner selectedPractitioner) {
        this.selectedPractitioner = selectedPractitioner;
    }

    public Practitioner getSelectedPractitioner() {
        return selectedPractitioner;
    }

    public void init(){

    }

    private void renderGrid(){

        ListModelArray<Practitioner> listModelArray = new ListModelArray<>(listPractitioners);
        gridPractitioner.setModel(listModelArray);

        gridPractitioner.setRowRenderer((row, data, index) ->{

            Practitioner practitioner = (Practitioner) data;

            row.appendChild(new Label(practitioner.getName().get(0).getFamily()));
            row.appendChild(new Label(practitioner.getName().get(0).getGiven().get(0).getValue()));

        } );


    }
}
