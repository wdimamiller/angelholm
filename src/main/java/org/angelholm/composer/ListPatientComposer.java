package org.angelholm.composer;

import org.angelholm.model.User;
import org.angelholm.service.PatientService;
import org.hl7.fhir.dstu3.model.Patient;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.ArrayList;

public class ListPatientComposer extends SelectorComposer {

    @Wire
    Grid gridPatients;
    ArrayList<Patient> listPatients;

    @AfterCompose
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        //fill grid
        PatientService patientService = new PatientService();
        listPatients = patientService.getListPatiens();

        ListModelList<Patient> gridListModel = new ListModelList<>(listPatients);

        gridPatients.setModel(gridListModel);

        gridPatients.setRowRenderer((RowRenderer) (row, data, index) -> {
            final Patient patient = (Patient) data;

            row.appendChild(new Label(patient.getName().get(0).getFamily()));
            row.appendChild(new Label(patient.getName().get(0).getGiven().get(0).toString()));
            row.appendChild(new Label("qqqq"));
            row.appendChild(new Label("qqqq"));
            row.appendChild(new Label("qqqq"));

        });

        //add event
        for (Component row : gridPatients.getRows().getChildren()) {
            row.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    Row row = (Row) arg0.getTarget();
                    Boolean rowSelected = (Boolean) row.getAttribute("Selected");

                    if (rowSelected != null && rowSelected) {
                        row.setAttribute("Selected", false);
                        // row.setStyle("");
                        row.setSclass("");
                    } else {
                        row.setAttribute("Selected", true);
                        // row.setStyle("background-color: #CCCCCC !important");   // inline style
                        row.setSclass("z-row-background-color-on-select");         // sclass
                    }
                    
                }
            });
        }


    }
}

