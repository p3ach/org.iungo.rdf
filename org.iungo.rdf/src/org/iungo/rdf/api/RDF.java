package org.iungo.rdf.api;

import org.iungo.id.api.ID;
import org.iungo.id.api.IDFactory;

public class RDF {

	private static final IDFactory idFactory = new IDFactory(RDF.class.getSimpleName(), null);
	
	public static final ID RDF_SPARQL_KEY = idFactory.create(SPARQL.class.getSimpleName());
}
