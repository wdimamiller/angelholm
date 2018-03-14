package org.angelholm.composer;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import org.angelholm.service.FhirGenericClientService;
import org.hl7.fhir.dstu3.model.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;


public class PanelTelecomComposer extends SelectorComposer<Panel> {

    List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    @Wire
    Textbox txtEmail;
    @Wire
    Textbox txtPhone;
    @Wire
    Listbox listCodePointsSystem;

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
        comp.setAttribute("PanelTelecomComposer", this);
        renderList();
    }

    private void renderList(){

        IGenericClient client = FhirGenericClientService.getInstance();


        Bundle bundle = client.search()
                .forResource(ValueSet.class)
                .where(ValueSet.NAME.matchesExactly().value("ContactPointSystem"))
                .and(new StringClientParam(ValueSet.SP_RES_LANGUAGE).matches().value("uk_UA"))
                .returnBundle(Bundle.class)
                .execute();

        ValueSet contactPointValueSet = (ValueSet) bundle.getEntry().get(0).getResource();

        int size = contactPointValueSet.getExpansion().getTotal();
        ValueSet.ValueSetExpansionContainsComponent containsComponent = contactPointValueSet.getExpansion().getContains().get(0);


       //ListModelList<ValueSet.ValueSetExpansionContainsComponent> listModel = new ListModelList<ValueSet.ValueSetExpansionContainsComponent>(contactPointValueSet.getExpansion().getContains());
        ListModelList<String> listModel = new ListModelList<>();
        contactPointValueSet.getExpansion().getContains().forEach(valueSetExpansionContainsComponent -> listModel.add(valueSetExpansionContainsComponent.getDisplay()));

        listCodePointsSystem.setModel(listModel);

    }
    public void setTelecom(List<ContactPoint> telecom){
        this.telecom = telecom;
    }

    public List<ContactPoint> getTelecom() {
        telecom.add(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.EMAIL).setValue(txtEmail.getValue()));
        telecom.add(new ContactPoint().setSystem(ContactPoint.ContactPointSystem.PHONE).setValue(txtPhone.getValue()));
        return telecom;
    }
}
