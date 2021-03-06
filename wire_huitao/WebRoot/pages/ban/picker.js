// JavaScript Document
$(function() {
  $(document).on("pageInit", function() {
    $("#picker").picker({
      toolbarTemplate: '<header class="bar bar-nav">\
      <button class="button button-link pull-right close-picker">确定</button>\
      <h1 class="title">请选择你的手机</h1>\
      </header>',
      cols: [
        {
          textAlign: 'center',
          values: ['iPhone 4', 'iPhone 4S', 'iPhone 5', 'iPhone 5S', 'iPhone 6', 'iPhone 6 Plus', 'iPad 2', 'iPad Retina', 'iPad Air', 'iPad mini', 'iPad mini 2', 'iPad mini 3']
        }
      ]
    });
    $("#picker-name").picker({
      toolbarTemplate: '<header class="bar bar-nav">\
      <button class="button button-link pull-right close-picker">确定</button>\
      <h1 class="title">请选择称呼</h1>\
      </header>',
      cols: [
        {
          textAlign: 'center',
          values: ['赵', '钱', '孙', '李', '周', '吴', '郑', '王']
        },
        {
          textAlign: 'center',
          values: ['杰伦', '磊', '明', '小鹏', '燕姿', '菲菲', 'Baby']
        },
        {
          textAlign: 'center',
          values: ['先生', '小姐']
        }
      ]
    });
    $("#datetime-picker").datetimePickerDuan({
      value: ['2016', '12', '04', '10', '30','12','30']
    });
	$("#startdatetime-picker").datetimePicker({
      value: ['2016', '12', '04', '10', '30']
    });
	$("#enddatetime-picker").datetimePicker({
      value: ['2016', '12', '04', '10', '30']
    });
	$("#datetime-pickers").datetimePicker({
	  value: ['2016', '12', '04', '10', '30']
	});
	$("#datetime-pickere").datetimePicker({
	  value: ['2016', '12', '04', '10', '30']
	});
/*	$("#datetime-picker").picker({
      toolbarTemplate: '<header class="bar bar-nav">\
      <button class="button button-link pull-right close-picker">确定</button>\
      <h1 class="title">请选择称呼</h1>\
      </header>',
      cols: [
        {
          textAlign: 'center',
          values: ['赵', '钱', '孙', '李', '周', '吴', '郑', '王']
        },
        {
          textAlign: 'center',
          values: ['杰伦', '磊', '明', '小鹏', '燕姿', '菲菲', 'Baby']
        },
        {
          textAlign: 'center',
          values: ['先生', '小姐']
        }
      ]
    });*/
    $("#city-picker").cityPicker({
      toolbarTemplate: '<header class="bar bar-nav">\
      <button class="button button-link pull-right close-picker">确定</button>\
      <h1 class="title">选择收货地址</h1>\
      </header>'
    });
  });
  $.init();
});
function getDateArr(date){
	if(typeof date==='undefined') date=undefined;
		
	date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var hour=date.getHours();
	var mins=date.getMinutes();
	console.log(date,year,month,day,hour,mins);
	return [year,month,day,hour,mins,hour+2,mins];
	
	}