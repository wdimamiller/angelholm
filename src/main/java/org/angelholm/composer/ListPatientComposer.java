package org.angelholm.composer;


import ca.uhn.fhir.model.api.IQueryParameterType;
import ca.uhn.fhir.model.primitive.StringDt;
import org.angelholm.service.PatientService;
import org.hl7.fhir.dstu3.model.Patient;
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
import java.util.List;

public class ListPatientComposer extends SelectorComposer {

    @Wire
    Textbox txtFirstName;
    @Wire
    Textbox txtLastName;
    @Wire
    Textbox txtSecondName;
    @Wire
    Datebox dateFrom;
    @Wire
    Datebox dateTo;
    @Wire
    Datebox dateBirth;
    @Wire
    Combobox lstGender;
    @Wire
    Button btnClearFilter;


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
    @Wire
    Button btnSearch;

    @Wire("include #panelPatientFilter")
    Panel panelPatientFilter;


    ArrayList<Patient> listPatients;
    Patient selectedPatient;
    PatientService patientService;
    List<IQueryParameterType> listQueryParameterTypes;


    @AfterCompose
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);


        init();
        addEvents();

    }

    private void init(){

        listQueryParameterTypes = new ArrayList<>();
        patientService = new PatientService();
        listPatients = patientService.getListPatiens();
        fillGrig();

    }
    @Listen("onClick=#btnClearFilter")
    public void clearFilter(){
        txtFirstName.setValue(null);
        txtLastName.setValue(null);
        txtSecondName.setValue(null);
        dateFrom.setValue(null);
        dateTo.setValue(null);
        dateBirth.setValue(null);
        lstGender.setSelectedItem(null);
    }

    @Listen("onClick=#btnEdit")
    public void updatePatient(){
        selectedPatient.getName().get(0).setFamily(editLastName.getValue());
        selectedPatient.getName().get(0).getGiven().get(0).setValue(editFirstName.getValue());
        selectedPatient.getName().get(0).getGiven().get(1).setValue(editSecondName.getValue());

        patientService.updatePatient(selectedPatient);
        fillGrig();
    }

    @Listen("onClick=#btnSearch")
    public void searchPatient(){

        String gender = "";
        if(lstGender.getSelectedItem()!=null){
            gender = lstGender.getSelectedItem().getValue();
        }

        listPatients = patientService.getListPatients(  txtLastName.getValue(),
                                                        txtFirstName.getValue(),
                                                        txtSecondName.getValue(),
                                                        dateFrom.getValue(),
                                                        dateTo.getValue(),
                                                        dateBirth.getValue(),
                                                        null,
                                                        gender
                                                            );

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

        ListModelList<Patient> gridListModel = new ListModelList<>(listPatients);
        gridPatients.setModel(gridListModel);

        gridPatients.setRowRenderer((RowRenderer) (row, data, index) -> {

            final Patient patient = (Patient) data;

            row.appendChild(new Label(patient.getName().get(0).getFamily()));
            row.appendChild(new Label(patient.getName().get(0).getGiven().get(0).toString()));
            row.appendChild(new Label(patient.getName().get(0).getGiven().get(1).toString()));
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            row.appendChild(new Label(df.format(patient.getBirthDate())));

        });
    }
}

