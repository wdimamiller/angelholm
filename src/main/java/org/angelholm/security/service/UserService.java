package org.angelholm.security.service;

import org.angelholm.security.entity.User;

public interface UserService {

    User getUser(String login);

}
