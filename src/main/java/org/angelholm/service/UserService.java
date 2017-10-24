package org.angelholm.service;

import org.angelholm.model.Role;
import org.angelholm.model.User;

import java.util.Set;

public interface UserService {

    User getUser(String login);

}
