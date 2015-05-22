package org.iungo.rdf.api;

import org.iungo.context.api.SimpleContext;
import org.iungo.http.api.AddHTTPServletContext;
import org.iungo.http.api.AddHTTPServletMessage;
import org.iungo.http.api.HTTPNode;
import org.iungo.http.api.HTTPServletContext;
import org.iungo.logger.api.ClassLogger;
import org.iungo.logger.api.Loggers;
import org.iungo.message.api.AbstractMessageHandle;
import org.iungo.message.api.Message;
import org.iungo.node.api.AbstractNode;
import org.iungo.node.api.MessageSequence;
import org.iungo.node.api.NodeContext;
import org.iungo.node.api.ResultMessage;
import org.iungo.node.api.SimpleMessageSequence;
import org.iungo.result.api.Result;

/**
 * 
 * Send add child message to dataset.
 * Send add child message to http.
 * Send add servlet message to http.
 * 
 * @author dick
 *
 */
public class SPARQLProtocolNode extends AbstractNode {
	
	private static final ClassLogger logger = Loggers.valueOf(SPARQLProtocolNode.class);

	private final MessageSequence create;
	
	private final SPARQLProtocolNodeContext sparqlProtocolNodeContext;
	
	public SPARQLProtocolNode(final NodeContext nodeContext) {
		super(nodeContext);
		
		sparqlProtocolNodeContext = new SPARQLProtocolNodeContext(nodeContext);
		
		/*
		 * We need to be able to add and remove servlets.
		 */
		rootMessageLoop.getSendMessageLoopQueue().getMessageHandles().add(HTTPNode.ADD_SERVLET_KEY, sendMessageHandle);
		
		rootMessageLoop.getSendMessageLoopQueue().getMessageHandles().add(HTTPNode.REMOVE_SERVLET_KEY, sendMessageHandle);
		
		create = new SimpleMessageSequence() {
			@Override
			public Result go() {
				logger.info("Add this as a child of the RDF Dataset node.");
				Result result = addObserver(
					sparqlProtocolNodeContext.getDatasetID(),
					NODE_CLOSED_OBSERVATION,
					new AbstractMessageHandle() {
						@Override
						public Result go(final Message message) {
							return close();
						}
					},
					create
				);
				return result;
			}
		};
		
		/*
		 * Handle the ResultMessage for the HTTP AddObserverMessage.
		 */
		create.add(new AbstractMessageHandle() {
			@Override
			public Result go(final Message message) {
				logger.info("Add this as a child of the HTTP node.");
				final ResultMessage resultMessage = (ResultMessage) message;
				Result result = resultMessage.getResult();
				if (result.isTrue()) {
					result = addObserver(sparqlProtocolNodeContext.getHTTPID(), NODE_CLOSED_OBSERVATION, new AbstractMessageHandle() {
						@Override
						public Result go(final Message message) {
							return close();
						}
					}, create);
				}
				return result;
			}
		});
		
		/*
		 * Handle the ResultMessage for the Dataset AddObserverMessage.
		 */
		create.add(new AbstractMessageHandle() {
			@Override
			public Result go(final Message message) {
				logger.info("Add the SPARQL Protocol Query Servlet to the HTTP node.");
				final ResultMessage resultMessage = (ResultMessage) message;
				Result result = resultMessage.getResult();
				if (result.isTrue()) {
					
					SimpleContext simpleContext = SPARQLProtocolNode.this.sparqlProtocolNodeContext.getQuery();
					if (simpleContext == null) {
						result = new Result(true, "Query not defined.", null);
					} else {
						final AddHTTPServletContext addHTTPServletContext = new AddHTTPServletContext(simpleContext);
						addHTTPServletContext.setServletClassName(SPARQLProtocolQueryHTTPServlet.class.getName());
						final AddHTTPServletMessage addHTTPServletMessage = new AddHTTPServletMessage(SPARQLProtocolNode.this.nextMessageID(), SPARQLProtocolNode.this.getID(), message.getFrom(), Message.NORMAL_PRIORITY, addHTTPServletContext);
						result = sendAndReceiveResult(addHTTPServletMessage, create);
					}
				}
				return result;
			}
		});

		create.go();
	}
}
