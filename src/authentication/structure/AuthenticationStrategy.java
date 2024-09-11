package src.authentication.structure;

import javax.naming.AuthenticationException;

public interface AuthenticationStrategy {
    boolean authenticate(Subject subject, Credential credential)throws AuthenticationException;

}