package org.angelholm.composer;

import org.angelholm.service.PatientService;
import org.hl7.fhir.dstu3.model.Patient;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import java.util.ArrayList;

public class ListPatientComposer extends SelectorComposer {

    Grid gridPatients;
    ArrayList<Patient> listPatients;

    @AfterCompose
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        PatientService patientService = new PatientService();
        this.listPatients = patientService.getListPatiens();


        ListModelArray<Patient> gridListModel = new ListModelArray<>(listPatients);

        System.out.println(gridListModel.toString());

        gridPatients = new Grid();
        if (gridListModel.getSize() > 0) {
            gridPatients.setModel(gridListModel);

        }
        gridPatients.setRowRenderer(new RowRenderer() {
            @Override
            public void render(Row row, Object data, int index) throws Exception {
                final Patient patient = (Patient) data;
               // initOperation(prod).setParent(row);



            }

/*
            public void render(Row row, Object data) throws Exception {
                final Product prod = (Product) data;
                 
                Image img = new Image(prod.getImgPath());
                img.setWidth("70px");
                img.setHeight("70px");
                img.setParent(row);
                new Label(prod.getName()).setParent(row);
                new Label("" + prod.getPrice()).setParent(row);
                new Label("" + prod.getQuantity()).setParent(row);
                new Label(YYYY_MM_DD_hh_ss.format(prod.getCreateDate())).setParent(row);
                initOperation(prod).setParent(row);
            }

*/
        });


    }
}

