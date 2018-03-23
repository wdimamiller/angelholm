package org.angelholm.composer;


import org.angelholm.service.PatientService;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.StringType;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListPatientComposer extends SelectorComposer {

    @Wire
    Grid gridPatients;
    @Wire
    Textbox editFirstName;
    @Wire
    Textbox editLastName;
    @Wire
    Textbox editSecondName;
    @Wire
    Button btnEdit;

    ArrayList<Patient> listPatients;
    Patient selectedPatient;
    PatientService patientService;


    @AfterCompose
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        patientService = new PatientService();

        fillGrig();
        addEvents();


    }

    @Listen("onClick=#btnEdit")
    public void updatePatient(){
        selectedPatient.getName().get(0).setFamily(editLastName.getValue());
        selectedPatient.getName().get(0).getGiven().get(0).setValue(editFirstName.getValue());
        selectedPatient.getName().get(0).getGiven().get(1).setValue(editSecondName.getValue());

        patientService.updatePatient(selectedPatient);
        fillGrig();
    }


    public void addEvents(){
        for (Component row : gridPatients.getRows().getChildren()) {
            row.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    Row row = (Row) arg0.getTarget();

                    int index = row.getIndex();
                    selectedPatient = (Patient) gridPatients.getModel().getElementAt(index);
                    fillEditPanel();

                }
            });
        }
    }

    public  void fillEditPanel(){

        editLastName.setValue(selectedPatient.getName().get(0).getFamily());
        editFirstName.setValue(selectedPatient.getName().get(0).getGiven().get(0).toString());
        editSecondName.setValue(selectedPatient.getName().get(0).getGiven().get(1).toString());

    }

    public void fillGrig(){
        listPatients = patientService.getListPatiens();

        ListModelList<Patient> gridListModel = new ListModelList<>(listPatients);

        gridPatients.setModel(gridListModel);

        gridPatients.setRowRenderer((RowRenderer) (row, data, index) -> {

            final Patient patient = (Patient) data;

            row.appendChild(new Label(patient.getName().get(0).getFamily()));
            row.appendChild(new Label(patient.getName().get(0).getGiven().get(0).toString()));
            row.appendChild(new Label(patient.getName().get(0).getGiven().get(1).toString()));
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            row.appendChild(new Label(df.format(patient.getBirthDate())));
            row.appendChild(new Label(patient.getGender().name()));

        });
    }
}

