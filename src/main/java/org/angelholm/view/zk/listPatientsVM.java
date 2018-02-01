package org.angelholm.view.zk;

import org.angelholm.service.PatientService;
import org.hl7.fhir.dstu3.model.Patient;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.SimpleListModel;

import java.util.ArrayList;

public class listPatientsVM {

    ArrayList<Patient> listPatients;
    ArrayList<String> lastNames;

    public String getSelectedLastName() {
        return selectedLastName;
    }

    public void setSelectedLastName(String selectedLastName) {
        this.selectedLastName = selectedLastName;
    }

    String selectedLastName;
    Patient selectedPatient;

    @Init
    public void init(){
        PatientService patientService = new PatientService();
        this.listPatients = patientService.getListPatiens();

        selectedPatient = listPatients.get(0);

       // selectedName
        lastNames = new ArrayList<String >();
        for( Patient patient: listPatients){
            lastNames.add( patient.getName().get(0).getFamily());
        }


        selectedLastName = lastNames.get(0);
       // listPatients.get(0).getName().


        //selectedPatient.getName().get(0).getFamily();
    }

    public ArrayList<Patient> getListPatients() {
        return listPatients;
    }

    public void setSelectedPatient(Patient selectedPatient) {
        this.selectedPatient = selectedPatient;
        selectedLastName = selectedPatient.getName().get(0).getFamily();
       // lastName = selectedPatient.getName().get(0).getFamily();
    }

    public ArrayList<String> getLastNames() {
        return lastNames;
    }

    public Patient getSelectedPatient() {
        return selectedPatient;
    }

    public String getFamily(){
        return this.selectedPatient.getName().get(0).getFamily();
    }
    /*
    @NotifyChange("selectedPatient")
    @Command
    public void savePatient(){
        //getService().save(selected);
    }

    @NotifyChange({"listPatients","selectedPatient"})
    @Command
    public void newPatient(){
        Patient order = new Patient();
       // getOrders().add(order);
        //selected = order;//select the new one
    }

    @NotifyChange("*")
    @Command
    public void deletePatient(){
        getService().delete(selected);//delete selected
        getOrders().remove(selected);
        selected = null; //clean the selected

    }*/
}
