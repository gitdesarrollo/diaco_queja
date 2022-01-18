
function myclick(thisobject){
	var myClass = $(thisobject).attr("class");
    var ClassCopy;
	if (myClass.substring(0,15) == 'btn btn-primary'){
		ClassCopy = myClass.replace("primary","success");		
	}else{
		ClassCopy = myClass.replace("success","primary");
	}
	$(thisobject).removeClass(myClass);
	$(thisobject).addClass(ClassCopy);
	
	/*if (myClass == 'btn btn-primary btn-image VerifyN'){
		$(thisobject).removeClass("btn btn-primary btn-image VerifyN");
		$(thisobject).addClass("btn btn-success btn-image VerifyN");
	}else{
		$(thisobject).removeClass("btn btn-success btn-image VerifyN");
		$(thisobject).addClass("btn btn-primary btn-image VerifyN");
	}*/
}

