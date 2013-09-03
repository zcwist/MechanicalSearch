Ext.onReady(function(){
//	var xdoc;
//	ExtendWordXML = '';
//	data = ExtendWordXML;
//	var domParser = new DOMParser();
//	xdoc = domParser.parseFromString(data, 'application/xml');
//	domParser = null;
	
	Ext.define('Stuff', {
		    extend: 'Ext.data.Model',
		    fields: ['word','descn']
		});
	var content;
	var filelist; //文件表
    var store = Ext.create('Ext.data.Store', {
        model: 'Stuff',
        autoLoad: true,
        //data: xdoc,
        proxy: {
            // load using HTTP
            type: 'memory',
            reader: {
                type: 'json',
                record: 'extendWord'
            }
        }
    });
    
    var fileListStore = Ext.create('Ext.data.Store', {
        fields:['id','fileName'],
        autoLoad: true,
        proxy: {
            type: 'memory',
            reader: {
                type: 'json',
                record: 'fileList'
            }
        }
    });


	var grid = Ext.create('Ext.grid.Panel', {
	    title: "Extended Word",
	    region: 'west',
	    collapsible:true,
	    store: store,
	    columns: [
	    	{ header: 'word',  dataIndex: 'word' },
	        { header: 'descn', dataIndex: 'descn' }
	    ],
	    height: 200,
	    width: 200
	});
	
	var contentShow = new Ext.panel.Panel({
		region: 'center',
		title: 'result',
		html: content,
        autoScroll: true
	});
	

	
	var fileContent = new Ext.panel.Panel({
		region: 'east',
		title: 'fileContent',
		html: '',
        autoScroll: true,
        collapsible:true,
        width: 400
	});
	
	
	var filelist = Ext.create('Ext.grid.Panel', {
	    title: "file",
	    region: 'west',
	    collapsible:true,
	    store: fileListStore,
	    columns: [
	    	{ header: 'id', dataIndex: 'id', hidden: true},
	        { header: 'fileName', dataIndex: 'fileName',width:400}   
	    ]
	    
	});
	
	filelist.addListener('itemclick',onRowDoubleClick);
	
	function onRowDoubleClick(filelist, rowIndex,e){
		
		var sm = filelist.getSelectionModel(); 

		var fileRecord = sm.getSelection(); 
		console.log(fileRecord[0].data.id);
	Ext.Ajax.request({
	    url: 'getFileContent.jsp',
	    params: {
	        'id': fileRecord[0].data.id
	    },
	    success: function(response){
	        var text = response.responseText;
	        console.log(text);
	        // process server response here
	        fileContent.update(text);
	    }
	});
	};
	
	

	var inputForm = new Ext.form.FormPanel({
	
		defaultType: 'textfield',
		region: 'north',
		labelAlign: 'center',
		title:'input query',
		labelWidth: 50,
		frame:true,
		width: 220,
		url: 'submitQuery.jsp',
		items:[{
			fieldLabel:'',
			name: 'text'
		}],
		buttons: [{
		
			text: 'Search',
			handler: function(){
//					EXT默认提交方式
				inputForm.getForm().submit({
				success: function(inputForm,action){
					//Ext.Msg.alert('message',action.result.content);
					data = action.result.extendWord;
					store.loadData(data);
					//console.log(data);
					
					content = action.result.content;
					contentShow.update(content);
					
					filelist = action.result.fileList; 
					fileListStore.loadData(filelist);
					//console.log(action.result.fileList);
					
				}
					
					});
					}
				
		}]
	});


	
	
	var viewport = new Ext.Viewport({
		layout: 'border',
		items:[
		{
			region: 'west',
			width:200,
			layout: 'accordion',
			layoutConfig:{
				titleCollapse:true,
				animate:true,
				activeOnTop: false
			},
			items:[
			grid,
			filelist
			]
			
		},inputForm,{
			region: 'north'
		},
		contentShow,
		fileContent,{
			region: 'south',
			html: 'Mechanical design Search System'
		}]
	});
});
