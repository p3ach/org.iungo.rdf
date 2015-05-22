package org.iungo.rdf.api;

import org.iungo.context.api.SimpleContext;
import org.iungo.http.api.HTTPServletContext;
import org.iungo.id.api.ID;
import org.iungo.node.api.NodeContext;

public class SPARQLProtocolNodeContext extends NodeContext {

	private static final long serialVersionUID = 1L;

	public static final String DATASET_ID_KEY = new ID(SPARQLProtocolNodeContext.class.getSimpleName(), "Dataset", "ID").toString();
	
	public static final String HTTP_ID_KEY = new ID(SPARQLProtocolNodeContext.class.getSimpleName(), "HTTP", "ID").toString();
	
	public static final String QUERY_KEY = new ID(SPARQLProtocolNodeContext.class.getSimpleName(), "Query", "Context").toString();
	
	public static final String UPDATE_KEY = new ID(SPARQLProtocolNodeContext.class.getSimpleName(), "Update", "Context").toString();

	public SPARQLProtocolNodeContext() {
		super();
	}

	public SPARQLProtocolNodeContext(final NodeContext nodeContext) {
		super(nodeContext);
	}

	public ID getDatasetID() {
		return (ID) get(DATASET_ID_KEY);
	}

	public Object putDatasetID(final ID datasetID) {
		return put(DATASET_ID_KEY, datasetID);
	}

	public ID getHTTPID() {
		return (ID) get(HTTP_ID_KEY);
	}

	public Object putHTTPID(final ID httpID) {
		return put(HTTP_ID_KEY, httpID);
	}
	
	public SimpleContext getQuery() {
		return (SimpleContext) get(QUERY_KEY);
	}
}
