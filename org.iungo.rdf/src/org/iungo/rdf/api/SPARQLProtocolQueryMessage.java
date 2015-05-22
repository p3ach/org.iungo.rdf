package org.iungo.rdf.api;

import org.iungo.id.api.ID;
import org.iungo.message.api.Message;

public class SPARQLProtocolQueryMessage extends Message {

	private static final long serialVersionUID = 1L;

	public SPARQLProtocolQueryMessage(ID id, ID from, ID to, Integer priority, ID key) {
		super(id, from, to, priority, key);
	}

}
