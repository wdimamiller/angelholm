package org.angelholm.composer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Include;

public class DoctorComposer extends SelectorComposer {

    @Wire
    Include includeContent;
    @Wire
    Button btnPatients;
    @Wire
    Button btnCalendar;


    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
    }

    @Listen("onClick=#btnPatients")
    public void showPagePatients(){
        includeContent.setSrc("/zul/patient/managePatients.zul");
        System.out.println("btnPatientClick");

        //Executions.createComponents("/zul/patient/listpatient.zul", includeContent, null);
    }

    @Listen("onClick=#btnCalendar")
    public void showPageCalendar(){

        includeContent.setSrc("/zul/templates/calendar.zul");
        System.out.println("btnCalendar");
        //Executions.createComponents("/zul/templates/calendar.zul", includeContent, null);*/

    }

}
