package org.iungo.rdf.api;

import org.iungo.message.api.AbstractMessageHandle;
import org.iungo.message.api.Message;
import org.iungo.node.api.AbstractNode;
import org.iungo.node.api.NodeContext;
import org.iungo.result.api.Result;

public class AbstractDatasetNode extends AbstractNode {

	public AbstractDatasetNode(final NodeContext nodeContext) {
		super(nodeContext);

		rootMessageLoop.getReceiveMessageLoopQueue().getMessageHandles().add(RDF.RDF_SPARQL_KEY, new AbstractMessageHandle() {
			@Override
			public Result go(final Message message) {
				logger.info(message.toString());
				return Result.TRUE;
			}
		});
	}

}
