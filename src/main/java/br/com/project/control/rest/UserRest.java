package br.com.project.control.rest;

import br.com.project.control.entity.pojo.User;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.factory.hql.ObjMapper;
import br.com.project.control.factory.hql.entity.UserFactory;
import br.com.project.control.security.CripMD5;
import br.com.project.control.service.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

public class UserRest  extends ObjMapper {

    public UserRest() {
    }

    @POST
    @Consumes("application/*")
    public Response add(String userStr) throws GlobalException {
        try {
            User user = new UserFactory(userStr).getUser();
            CripMD5 cripmd5 = new CripMD5();

            String base64 = user.getPassword();
            String desconvertido = cripmd5.decode64(base64);
            String hashd5 = CripMD5.encrypt(desconvertido);

            user.setPassword(hashd5);
            if (user != null) {
                new UserService(user).adicionar();
            }
            return this.buildResponse("Cadastrado com sucesso.");
        } catch (Throwable e) {
            e.printStackTrace();
            return this.buildErrorResponse(e.getMessage());
        }
    }
}
