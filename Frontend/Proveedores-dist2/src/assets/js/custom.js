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
}	