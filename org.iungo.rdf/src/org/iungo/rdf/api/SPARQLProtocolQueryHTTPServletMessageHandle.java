package org.iungo.rdf.api;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;

import org.iungo.http.api.AbstractHTTPServletMessageHandle;
import org.iungo.http.api.AbstractHTTPServlet;
import org.iungo.http.api.MIME;
import org.iungo.message.api.Message;
import org.iungo.result.api.Result;

public class SPARQLProtocolQueryHTTPServletMessageHandle extends AbstractHTTPServletMessageHandle {

	public SPARQLProtocolQueryHTTPServletMessageHandle(final AsyncContext asyncContext) {
		super(asyncContext);
	}

	@Override
	public Result go(final Message message) {
		final AsyncContext asyncContext = getAsyncContext();
		final HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
		try{
	//		final U4GoSPARQLQuery.Result asSPARQLResult = new U4GoSPARQLQuery.Result(message.getProperties());
	//		if (asSPARQLResult.isTrue()) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType(MIME.TEXT_HTML);
	//			U4HTTPServlet.responseWrite(response, asSPARQLResult.getValue(byte[].class));
				AbstractHTTPServlet.responsePrint(response, message.toString());
	//		} else {
	//			U4HTTPServlet.badRequest((HttpServletRequest) asyncContext.getRequest(), response, asSPARQLResult.getText());
	//		}
			return Result.TRUE;
		} catch (final Exception exception) {
			AbstractHTTPServlet.internalServerError(response);
			return Result.valueOf(exception); 
		} finally {
			asyncContext.complete();
		}
	}

}
