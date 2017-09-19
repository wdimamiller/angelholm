package org.angelholm.view.zk;

import org.angelholm.model.User;
import org.angelholm.service.UserService;

import org.angelholm.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MyViewModel {

	@WireVariable
	private MyService myService;
	private String answer;

    @Autowired
	private UserService userService;

	@Init
	public void init() {
		answer = "?";
	}

	@Command
	@NotifyChange("answer")
	public void cmd() {

	    User user = userService.getUser("admin") ;
	    answer= user.getPassword();
	}

	public String getAnswer() {
		return answer;
	}
}
