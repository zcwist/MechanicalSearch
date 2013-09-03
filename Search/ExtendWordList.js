function grid(data){

		var xdoc;

    	
    	var domParser = new DOMParser();
    	xdoc = domParser.parseFromString(data, 'application/xml');
    	domParser = null;
    	
    	Ext.define('Stuff', {
			    extend: 'Ext.data.Model',
			    fields: ['word','descn']
			});

	    var store = Ext.create('Ext.data.Store', {
	        model: 'Stuff',
	        autoLoad: true,
	        data: xdoc,
	        proxy: {
	            // load using HTTP
	            type: 'memory',
	            // the return will be XML, so lets set up a reader
	            reader: {
	                type: 'xml',
	                // records will have an "Item" tag
	                record: 'record'
	            }
	        }
	    });
	

		var grid = Ext.create('Ext.grid.Panel', {
		    title: 'ExtendWord',
		    store: store,
		    columns: [
		    	{ header: 'word',  dataIndex: 'word' },
		        { header: 'descn', dataIndex: 'descn' }
		    ],
		    height: 200,
		    width: 400,
		    renderTo: Ext.getBody()
		});
}
