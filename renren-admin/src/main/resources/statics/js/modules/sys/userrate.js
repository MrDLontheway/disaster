$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/userrate/list',
        datatype: "json",
        colModel: [			
			{ label: 'die', name: 'die', index: 'die', width: 50, key: true },
			{ label: '', name: 'general', index: 'general', width: 80 }, 			
			{ label: '', name: 'senior', index: 'senior', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		userRate: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.userRate = {};
		},
		update: function (event) {
			var die = getSelectedRow();
			if(die == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(die)
		},
		saveOrUpdate: function (event) {
			var url = vm.userRate.die == null ? "sys/userrate/save" : "sys/userrate/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.userRate),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var dies = getSelectedRows();
			if(dies == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/userrate/delete",
                    contentType: "application/json",
				    data: JSON.stringify(dies),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(die){
			$.get(baseURL + "sys/userrate/info/"+die, function(r){
                vm.userRate = r.userRate;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});