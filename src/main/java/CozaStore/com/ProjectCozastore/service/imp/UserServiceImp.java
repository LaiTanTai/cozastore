package CozaStore.com.ProjectCozastore.service.imp;

import CozaStore.com.ProjectCozastore.payload.Request.SignUpRequest;

public interface UserServiceImp {
    boolean addUser(SignUpRequest request);
}
