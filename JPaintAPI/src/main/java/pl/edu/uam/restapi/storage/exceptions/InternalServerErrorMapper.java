package pl.edu.uam.restapi.storage.exceptions;

import pl.edu.uam.restapi.storage.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(createEntity(exception))
                .build();
    }

    private ErrorMessage createEntity(Exception exception) {
        return new ErrorMessage(exception.getMessage(), "Serwer ma gorsze chwile");
    }
}
