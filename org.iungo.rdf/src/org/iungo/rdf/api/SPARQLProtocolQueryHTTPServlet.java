package org.iungo.rdf.api;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iungo.http.api.AbstractHTTPServlet;
import org.iungo.http.api.HTTPRequest;
import org.iungo.http.api.HTTPServletContext;
import org.iungo.http.api.MIME;
import org.iungo.message.api.Message;
import org.iungo.message.api.MessageHandle;
import org.iungo.message.api.ReplyMessage;
import org.iungo.message.api.SendAndReceiveResultMessage;
import org.iungo.result.api.Result;

import com.unit4.daas.message.U4AsMessage;
import com.unit4.daas.message.U4Message;
import com.unit4.daas.node.U4ReplyToMessageHandle;
import com.unit4.daas.node.http.U4HTTP;
import com.unit4.daas.node.rdf.sparql.U4GoSPARQLQuery;
import com.unit4.daas.node.rdf.sparql.U4QueryServletHandle;
import com.unit4.daas.node.rdf.sparql.U4SPARQLProtocol;
import com.unit4.daas.property.as.result.U4AsResult;
import com.unit4.daas.util.U4Arrays;

public class SPARQLProtocolQueryHTTPServlet extends AbstractHTTPServlet {

	public SPARQLProtocolQueryHTTPServlet(final HTTPServletContext httpServletContext) {
		super(httpServletContext);
	}

	private static final long serialVersionUID = 1L;

	/**
	 * Get SPARQL Protocol query parameters and create a SPARQLProtocolQueryMessage and SPARQLProtocolQueryResultMessageHandle.
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		try {
			final AsyncContext asyncContext = request.startAsync(request, response);
			
			final String query = getParameter(request, SPARQLProtocol.HTTP_PARAMETER_QUERY);
			if (query == null) {
				badRequest(request, response, String.format("Expected parameter [%s].", SPARQLProtocol.HTTP_PARAMETER_QUERY));
			} else{
//		        // TODO HTTP 400 if protocol specified RDF Dataset?
//				U4GoSPARQLQuery asSPARQLQuery = new U4GoSPARQLQuery(asMessage.getProperties());
//				asSPARQLQuery.setGo();
//				asSPARQLQuery.setQuery(query);
//				asSPARQLQuery.setLanguage(language);
//				asSPARQLQuery.setDefaultGraphs(U4Arrays.asList(request.getParameterValues(U4SPARQLProtocol.DEFAULT_GRAPH_URI)));
//				asSPARQLQuery.setNamedGraphs(U4Arrays.asList(request.getParameterValues(U4SPARQLProtocol.NAMED_GRAPH_URI)));
//		        result.setTrue();
				SPARQLProtocolQueryMessage sparqlProtocolQueryMessage = new SPARQLProtocolQueryMessage(id, from, to, priority, key)
				SPARQLProtocolQueryMessage
				Result result = SendAndReceiveResultMessage
			}

		} catch ()
		sendMessage(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		sendMessage(request, response);
	}

	@Override
	protected Result createMessage(final AsyncContext asyncContext) {
		Result result;
		
		final HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();
		final HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();

		/*
		 * WARNING Calling getParameter(request, HTTP_REQUEST_HEADER_LANGUAGE); will mean that getInputStream() and getReader() fail silently!
		 * See http://docs.oracle.com/javaee/6/api/javax/servlet/ServletRequest.html#getParameter(java.lang.String)
		 */
		
		switch (request.getMethod()) {
			case HTTPRequest.METHOD_GET:
				final String query = getParameter(request, SPARQLProtocol.HTTP_PARAMETER_QUERY);
				
				if (query == null) {
//					badRequest(request, response, String.format("Expected parameter [%s].", SPARQLProtocol.HTTP_PARAMETER_QUERY));
					result = new Result(false, String.format("Expected parameter [%s].", SPARQLProtocol.HTTP_PARAMETER_QUERY), null);
				} else{
//			        // TODO HTTP 400 if protocol specified RDF Dataset?
//					U4GoSPARQLQuery asSPARQLQuery = new U4GoSPARQLQuery(asMessage.getProperties());
//					asSPARQLQuery.setGo();
//					asSPARQLQuery.setQuery(query);
//					asSPARQLQuery.setLanguage(language);
//					asSPARQLQuery.setDefaultGraphs(U4Arrays.asList(request.getParameterValues(U4SPARQLProtocol.DEFAULT_GRAPH_URI)));
//					asSPARQLQuery.setNamedGraphs(U4Arrays.asList(request.getParameterValues(U4SPARQLProtocol.NAMED_GRAPH_URI)));
//			        result.setTrue();
					SPARQLProtocolQueryMessage sparqlProtocolQueryMessage = new SPARQLProtocolQueryMessage(id, from, to, priority, key)
					result = new Result(true, null, sparqlProtocolQueryMessage);
				}
		        break;
			case HTTPRequest.METHOD_POST:
				final String contentType = request.getContentType();
				if (contentType == null) {
					badRequest(request, response, String.format("Expected parameter [%s].", U4HTTP.HTTP_HEADER_CONTENT_TYPE));
				} else {
					switch (contentType) {
//					case U4HTTP.MIME_CONTENT_TYPE_FORM:
//					
//						break;
		
					case MIME.APPLICATION_SPARQL_QUERY:
						try {
							StringBuilder sw = new StringBuilder();
							char[] buffer = new char[1024 * 4];
							int n = 0;
							while (-1 != (n = request.getReader().read(buffer))) {
							    sw.append(buffer, 0, n);
							}
							query = sw.toString();
							logger.info(String.format("query %s", query));
							U4GoSPARQLQuery asSPARQLQuery = new U4GoSPARQLQuery(asMessage.getProperties());
							asSPARQLQuery.setGo();
							asSPARQLQuery.setQuery(query);
							asSPARQLQuery.setLanguage(language);
							asSPARQLQuery.setDefaultGraphs(U4Arrays.asList(request.getParameterValues(U4SPARQLProtocol.DEFAULT_GRAPH_URI)));
							asSPARQLQuery.setNamedGraphs(U4Arrays.asList(request.getParameterValues(U4SPARQLProtocol.NAMED_GRAPH_URI)));
					        result.setTrue();							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
					break;
				}
				result = Result.FALSE;
				break;
			default:
				result = Result.FALSE;
				break;
			}
//		}
//			U4ReplyToMessageHandle handle = null;
//			if (result.isTrue()) {
//				final U4Message message = new U4Message(asMessage);
//				handle = new U4QueryServletHandle(message, asyncContext);
//			}
		
		return result;

	}

	@Override
	protected MessageHandle createMessageHandle(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
