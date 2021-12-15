function chkSubmit(item, msg){
	if(item.val().replace(/\s/g,"")==""){
		alert(msg+" 입력해 주세요.");
		item.val("");
		item.focus();
		return false;
	}else{
		return true;
	}
}