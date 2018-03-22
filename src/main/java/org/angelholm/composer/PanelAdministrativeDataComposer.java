package org.angelholm.composer;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Panel;

import java.util.Date;

public class PanelAdministrativeDataComposer extends SelectorComposer<Panel>{

    Date dateOfBirth;
    String gender;

    @Wire
    Combobox lstGender;
    @Wire
    Datebox txtDateOfBirth;


    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);

        comp.setAttribute("PanelAdministrativeDataComposer", this);
    }

    public void setDateOfBirth(Date dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
        txtDateOfBirth.setValue(dateOfBirth);
    }

    public Date getDateOfBirth() {

        return txtDateOfBirth.getValue();
    }

    public String getGender() {
        return lstGender.getText();
    }

    public void setGender(String gender) {
        lstGender.appendChild(new Comboitem(gender));
        this.gender = gender;
    }
}
